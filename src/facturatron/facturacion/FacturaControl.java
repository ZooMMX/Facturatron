/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.facturacion;

import com.alee.laf.label.WebLabel;
import facturatron.Dominio.Configuracion;
import static facturatron.Dominio.Configuracion.getConfig;
import facturatron.Dominio.Factura;
import facturatron.Dominio.Medida;
import facturatron.Dominio.Persona;
import facturatron.Dominio.Producto;
import facturatron.Dominio.Renglon;
import java.awt.event.ActionEvent;
import java.sql.Time;
import java.util.ArrayList;
import facturatron.MVC.Controller;
import facturatron.cliente.ClienteDao;
import facturatron.config.ConfigFiscalDao;
import facturatron.datasource.DatasourceContext;
import facturatron.datasource.DatasourceException;
import facturatron.facturacion.PAC.PACException;
import facturatron.datasource.IDatasourceService;
import facturatron.datasource.RenglonTicket;
import facturatron.datasource.Ticket;
import facturatron.datasource.TicketGlobal;
import facturatron.datasource.omoikane.TicketOmoikane;
import facturatron.facturacion.PopupBuscarCliente.ClienteAction;
import facturatron.lib.Java2sAutoComboBox;
import facturatron.lib.entities.CFDv3Tron;
import facturatron.lib.entities.ComprobanteTron;
import facturatron.omoikane.exceptions.TicketFacturadoException;
import facturatron.producto.ProductoDao;
import facturatron.unidad.UnidadDao;
import java.awt.Frame;
import java.awt.List;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.PersistenceException;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import mx.bigdata.sat.cfdi.v32.schema.Comprobante;
import mx.bigdata.sat.cfdi.v32.schema.ObjectFactory;
import mx.bigdata.sat.cfdi.v32.schema.TimbreFiscalDigital;
import org.xml.sax.SAXParseException;

/**
 *
 * @author Octavio Ruiz @ Phesus
 * @author saul @ Phesus
 */
public class FacturaControl extends Controller<FacturaDao, FacturaForm> {  //solo controlador

  ConfigFiscalDao configFiscal;
  /* se usa como bandera para determinar si se realiz'o el timbrado con el PAC */
  boolean timbrado = false;
  public FacturaControl(){
      try {
          setModel(setupAndInstanceModel());
          setView(new FacturaForm());
          init();
          setupAddons();
      } catch (SQLException ex) {
          Logger.getLogger(FacturaControl.class.getName()).log(Level.SEVERE, "Error de BD al iniciar facturación", ex);
      }
  }

  private void setupAddons() {
      Configuracion cfg = getConfig();
      PopupBuscarCliente popupBuscarCliente = new PopupBuscarCliente(
              getView().getBtnBuscarCliente(),
              new ClienteAction() {
                 @Override
                 public void run() {
                     getView().getTxtIdCliente().setText(getCliente().getId().toString());
                     cargarCliente();
                     getView().getTxtIdCliente().requestFocus();
                 }                  
              }
      );
      popupBuscarCliente.install();
      
      getView().getTabConceptos().setSurrendersFocusOnKeystroke(true);
      
      new CeldaDescripcion(this).install();
      if(cfg.getModuloUnidadesActivo() != null && cfg.getModuloUnidadesActivo())
        new CeldaBuscadorUnidades(this).install();
      if(cfg.getModuloProductosActivo() != null && cfg.getModuloProductosActivo())
        new CeldaBuscadorProductos(this).install();
  }
  
  public FacturaDao setupAndInstanceModel() throws SQLException {
      FacturaDao      dao          = new FacturaDao();
      configFiscal = new ConfigFiscalDao();
      configFiscal.load();
      dao.setSerie(configFiscal.getSerie());
      dao.setFolio(configFiscal.getFolioActual());

      return dao;
  }

  public void txtDescuentoChanged() {
      String txtDesc0  = getView().getTxtDescuentoTasa0().getText();
      String txtDesc16 = getView().getTxtDescuentoTasa16().getText();
            
      try {
          getModel().setDescuentoTasa0 (new BigDecimal(txtDesc0 .replaceAll(",", "")));
          getModel().setDescuentoTasa16(new BigDecimal(txtDesc16.replaceAll(",", "")));
      } catch(NumberFormatException nfe) {
          txtDesc0  = "0";
          txtDesc16 = "0";
      }
      renglonesActualizados();
  }
  public void cargarCliente() {
      try {
          int id = Integer.valueOf(getView().getTxtIdCliente().getText());
          ClienteDao cliente = (new ClienteDao()).findBy(id);
          if(cliente != null) {
                getModel().setReceptor(cliente);
          } else {
                JOptionPane.showMessageDialog(getView(), "Cliente no encontrado");
          }
      } catch(NumberFormatException nfe) {
          Logger.getLogger(FacturaControl.class.getName()).log(Level.INFO, "Número de cliente inválido", nfe);
      } catch (SQLException ex) {
          Logger.getLogger(FacturaControl.class.getName()).log(Level.SEVERE, "Error cargando cliente", ex);
      }
  }

  public void renglonesActualizados() {
      MathContext mc = MathContext.DECIMAL128;

      BigDecimal subtotal    = new BigDecimal("0.00", mc);
      BigDecimal iva         = new BigDecimal("0.00", mc);
      BigDecimal ieps        = new BigDecimal("0.00", mc);
      BigDecimal total       = new BigDecimal("0.00", mc);
      BigDecimal descuento0  = new BigDecimal("0.00", mc);
      BigDecimal descuento16 = new BigDecimal("16.00", mc);
      BigDecimal importe0    = new BigDecimal("0.00", mc);
      BigDecimal importe16   = new BigDecimal("0.00", mc);

      subtotal   .setScale(2);
      iva        .setScale(2);
      ieps       .setScale(2);
      total      .setScale(2);
      descuento0 .setScale(2);
      descuento16.setScale(2);
      importe0   .setScale(2);
      importe16  .setScale(2);

      for (Renglon renglon : getModel().getRenglones()) {
          if(renglon.getTasa0()) {
              importe0 = importe0.add(renglon.getImporte());
          } else {
              importe16 = importe16.add(renglon.getImporte());
          }
          ieps     = ieps.add( renglon.getIEPS() );
          subtotal = subtotal.add(renglon.getImporte());
      }

      descuento0 = getModel().getDescuentoTasa0();
      descuento16= getModel().getDescuentoTasa16();

      if(descuento0  .compareTo(importe0)  < 0) { importe0  = importe0 .subtract(descuento0 ); }
      if(descuento16 .compareTo(importe16) < 0) { importe16 = importe16.subtract(descuento16); }

      iva        = importe16.multiply(new BigDecimal("0.16"), mc);
      total      = importe0.add(importe16, mc).add(iva, mc);
      total      = total.add( ieps , mc );
      
      getModel().setSubtotal(subtotal);
      getModel().setIvaTrasladado(iva);
      getModel().setIEPSTrasladado(ieps);
      getModel().setTotal(total);

  }
  public void btnObservaciones() {
      String observaciones = getModel().getObservaciones();
      FacturaObservacionesDialog dialog = new FacturaObservacionesDialog(getView().getJFrameParent(), observaciones);
      String newObs = dialog.lanzar();
      if(newObs != null) { observaciones = newObs; }
      getModel().setObservaciones(observaciones);
  }
  /*
   * Éste método no importa el importe de cada renglón ni de la venta general, su cálculo es responsabilidad de los modelos
   * de la factura
   */
  public void btnAddTicket() {
      JFrame mainJFrame = (JFrame) JFrame.getFrames()[0];
      AddTicketDialog dialog = new AddTicketDialog(mainJFrame);
      notifyBusy();
      new ThreadAddTicket(dialog).start();
  }
  
  public void btnRango () {
      JFrame mainJFrame = (JFrame) JFrame.getFrames()[0];
      AddTicketRangoDialog dialog = new AddTicketRangoDialog(mainJFrame);
      notifyBusy();
      new ThreadRangoTicket(dialog).start();
  }  
  
  public void btnFacturaDia() {
      JFrame mainJFrame = (JFrame) JFrame.getFrames()[0];
      AddFacturaGlobalizadaDialog dialog = new AddFacturaGlobalizadaDialog(mainJFrame);
      
      notifyBusy();
      new ThreadAddCorteZ(dialog).start();
  }
  
  public Boolean validarForm() {
      //Validar Forma de pago
      if( getView().getTxtFormaDePago().getText().isEmpty() ) { 
          JOptionPane.showMessageDialog(getView(), "El campo forma de pago no puede estar vacío");
          return false;
      }
      
      //Validar Metodo de pago
      if( getView().getTxtMetodoPago().getText().isEmpty() ) { 
          JOptionPane.showMessageDialog(getView(), "El campo método de pago no puede estar vacío");
          return false;
      }
      
      //Validar IVA
      if( getView().getTxtTotal().getText().isEmpty() ) {
          JOptionPane.showMessageDialog(getView(), "El campo total no puede estar vacío");
          return false;
      }
      
      //Validar cliente
      if( getView().getTxtIdCliente().getText().isEmpty() ) {
          JOptionPane.showMessageDialog(getView(), "El campo ID cliente no puede estar vacío");
          return false;
      }
      try {
        Integer x = Integer.valueOf(getView().getTxtIdCliente().getText());
      } catch(NumberFormatException nfe) {
          JOptionPane.showMessageDialog(getView(), "El campo cliente no tiene un ID válido");
          return false;
      }
      
      //Validación de conceptos
      ArrayList<Renglon> renglones = ((FacturaTableModel) getView().getTabConceptos().getModel()).getData();
      //Validar cada renglon
      Integer filas = 0;
      for (Renglon renglon : renglones) {
          /** Validar cantidad; la cantidad también sirve como bandera para indicar si el renglón se toma en cuenta
           * o no
           */
          BigDecimal cantidad = renglon.getCantidad();
          if(cantidad != null && cantidad.compareTo(new BigDecimal(0)) <= 0) {
              //Esta fila tiene una cantidad en cero, no es tomada en cuenta
              continue;
          } else {
              //Esta se toma en cuenta
              filas++;
          }
          //Validar valor unitario
          BigDecimal valor = renglon.getValorUniario();
          if(valor != null && valor.compareTo(new BigDecimal(0)) <= 0) {
              JOptionPane.showMessageDialog(getView(), "La columna PU (precio unitario) tiene un contenido inválido en la fila " + filas);
              return false;
          }                  
          //Validar unidad
          String unidad = renglon.getUnidad();
          if(unidad != null && unidad.isEmpty()) {
              JOptionPane.showMessageDialog(getView(), "La columna unidad tiene un contenido inválido o vacío en la fila " + filas);
              return false;
          }                  
          //Validar descripcion
          String descripcion = renglon.getDescripcion();
          if(descripcion != null && descripcion.isEmpty()) {
              JOptionPane.showMessageDialog(getView(), "La columna descripción tiene un contenido inválido o vacío en la fila " + filas);
              return false;
          } 
          //Validar código
          String codigo = renglon.getNoIdentificacion();
          if(codigo != null && codigo.isEmpty()) {
              JOptionPane.showMessageDialog(getView(), "La columna código tiene un contenido inválido o vacío en la fila " + filas);
              return false;
          } 
      }
      
      //Validar cantidad de conceptos en la factura, de acuerdo al contador de filas tomadas en cuenta en la validación anterior
      if(filas < 1) {
          JOptionPane.showMessageDialog(getView(), "No se han agregado conceptos al comprobante");
              return false;
      }
      
            
      return true;
  }
  
  public void btnGuardar(){

    if(!validarForm()) return;
    notifyBusy();    
    timbrado = false;
    try {
        Calendar time = Calendar.getInstance();
        getModel().setCertificado("NULO");
        getModel().setEmisor((new ClienteDao()).findBy(1));
        getModel().setEmisorSucursal((new ClienteDao()).findBy(2));
        getModel().setFecha(time.getTime());
        getModel().setHora(new Time(time.getTime().getTime()));
        getModel().setFormaDePago(getView().getTxtFormaDePago().getText());
        getModel().setMetodoDePago(getView().getTxtMetodoPago().getText());
        getModel().setIvaTrasladado(new BigDecimal(getView().getTxtIva().getText().replaceAll(",", "")));
        getModel().setMotivoDescuento(getView().getTxtMotivoDescuento().getText());
        getModel().setReceptor((new ClienteDao()).findBy(Integer.valueOf(getView().getTxtIdCliente().getText())));
        getModel().setTipoDeComprobante(getView().getTipoComprobante().getEfectoString());
        getModel().setVersion("3.2");

        getView().getBtnGuardar().setEnabled(false);

        getModel().persist();
        
        timbrado = true;
        marcarTicketsFacturados(getModel().getId());
    } catch (SAXParseException e) {
        Logger.getLogger(FacturaControl.class.getName()).log(Level.SEVERE, "Datos erróneos.", e);
    } catch (PACException pa) {
        Logger.getLogger(FacturaControl.class.getName()).log(Level.SEVERE, pa.getMessage(), pa.getCause());
    } catch (DatasourceException de) {
        Logger.getLogger(FacturaControl.class.getName()).log(Level.SEVERE, de.getMessage(), de);
    } catch (Exception ex) {
        Logger.getLogger(FacturaControl.class.getName()).log(Level.SEVERE, "Error desconocido al generar factura", ex);
    } finally {
        getView().getBtnGuardar().setEnabled(true);
        notifyIdle();
    }
    
  }

  public void marcarTicketsFacturados(Integer idFactura) throws DatasourceException {
      IDatasourceService ds = DatasourceContext.instanceDatasourceInstance();
      ArrayList<Object> idsTickets = new ArrayList();
      
      for(Ticket t : getModel().getTickets()) {
          idsTickets.add(t.getId());
      }
      
      ds.setTicketsFacturados(idsTickets, idFactura);
  }
  
  public void btnVistaPrevia() {
      try {
        
        if(getView().getTxtIdCliente().getText().equals("")) {
            Logger.getLogger(FacturaDao.class.getName()).log(Level.INFO, "Necesita especificar un cliente");
            return ;
        }  
          
        Calendar time = Calendar.getInstance();
        getModel().setCertificado("NULO");
        getModel().setEmisor((new ClienteDao()).findBy(1));
        getModel().setEmisorSucursal((new ClienteDao()).findBy(2));
        getModel().setFecha(time.getTime());
        getModel().setHora(new Time(time.getTime().getTime()));
        getModel().setFormaDePago(getView().getTxtFormaDePago().getText());
        getModel().setMetodoDePago(getView().getTxtMetodoPago().getText());
        getModel().setIvaTrasladado(new BigDecimal(getView().getTxtIva().getText().replaceAll(",", "")));
        getModel().setMotivoDescuento(getView().getTxtMotivoDescuento().getText());
        getModel().setReceptor((new ClienteDao()).findBy(Integer.valueOf(getView().getTxtIdCliente().getText())));
        getModel().setTipoDeComprobante(getView().getTipoComprobante().getEfectoString());
        getModel().setVersion("3.2");  
          
        ObjectFactory of = new ObjectFactory();
        TimbreFiscalDigital timbre = of.createTimbreFiscalDigital();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            
        timbre.setFechaTimbrado( dateFormat.parse("2000-01-01T00:00:00") );
        timbre.setNoCertificadoSAT( "30001000000100000801" );
        timbre.setVersion("1.0");
        timbre.setUUID( "ad662d33-6934-459c-a128-bdf0393e0f44" );
        timbre.setSelloSAT( "j5bSpqM3w0+shGtlmqOwqqy6+d659O78ckfstu5vTSFa+2CVMj6Awfr18x4yMLGBwk6ruYbjBIVURodEII6nJIhTTUtYQV1cbRDG9kvvhaNAakx qaSOnOnOx79nHxqFPRVoqh10CsjocS9PZkSM2jz1uwLgaF0knf1g8pjDkLYwlk=" );
        timbre.setSelloCFD( "tOSe+Ex/wvn33YIGwtfmrJwQ31Crd7II9VcH63TGjHfxk5cfb3q9uSbDUGk9TXvo70ydOpikRVw+9B2Six0mbu3PjoPpO909oAYITrRyomdeUGJ 4vmA2/12L86EJLWpU7vlt4cL8HpkEw7TOFhSdpzb/890+jP+C1adBsHU1VHc=" );

        Comprobante.Complemento complemento = of.createComprobanteComplemento();
        complemento.getAny().add(timbre);
        
        Configuracion cfg = getConfig();
        ComprobanteTron ct = getModel().toComprobanteTron();
        CFDv3Tron cfd = new CFDv3Tron();
        cfd.setComprobante(ct);  
        
        ct.setComplemento(complemento);

        String serie = cfd.getComprobante().getSerie();
        String folio = cfd.getComprobante().getFolio();
        cfd.getComprobante().setPathLogo(cfg.getPathLogo());
        
        //Visor Java
        cfd.showPreview(cfg.getPathPlantilla());
        
      } catch(IOException io) {
          Logger.getLogger(FacturaDao.class.getName()).log(Level.SEVERE, "Excepción en visor PDF", io);
      } catch (Exception ex) {
          Logger.getLogger(FacturaControl.class.getName()).log(Level.SEVERE, "No fue posible mostrar la vista previa", ex);
      }
  }

    @Override
    public void asignarEventos() {
        getView().getTxtIdCliente().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(!getView().getTxtIdCliente().getText().equals(""))
                    cargarCliente();
            }
            
        });
        getView().getTxtMetodoPago().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(!getView().getTxtMetodoPago().getText().equals(""))
                    getModel().setMetodoDePago(getView().getTxtMetodoPago().getText());
            }
        });
        getView().getTxtFormaDePago().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(!getView().getTxtFormaDePago().getText().equals(""))
                    getModel().setFormaDePago(getView().getTxtFormaDePago().getText());
            }
        });
        getView().getBtnGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Factura> rep = new ArrayList<Factura>();
                new Thread() {
                    public void run() {
                        btnGuardar();
                        if (timbrado) {
                            int opc = JOptionPane.showConfirmDialog(getView(), "Factura Timbrada desea cerrar la pestaña", "Alert", JOptionPane.YES_NO_OPTION);
                            if (opc == 0) {
                                getView().getParent().remove(getView());
                            } else {
                                getView().getBtnGuardar().setEnabled(false);
                            }
                        }
                        
                    }
                }.start();

            }
        });
        getView().getBtnVistaPrevia().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnVistaPrevia();
            }
            
        });
        getView().getTabConceptos().getModel().addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                renglonesActualizados();
            }
        });
        getView().getBtnObservaciones().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnObservaciones();
            }
        } );
        getView().getBtnTicket().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAddTicket();
            }
        });
        getView().getBtnFacturaDia().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnFacturaDia();
            }
        });
        
        DocumentListener dl = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                txtDescuentoChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                txtDescuentoChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                txtDescuentoChanged();
            }
        };
        getView().getTxtDescuentoTasa0().getDocument().addDocumentListener(dl);
        getView().getTxtDescuentoTasa16().getDocument().addDocumentListener(dl);
    }

    @Override
    public void enlazarModeloVista() {
        FacturaTableModel ftm = new FacturaTableModel();
        ftm.setData(getModel().getRenglones());
        ftm.addRow();
        getView().getTabConceptos().setModel(ftm);
        getView().getTabConceptos().setDefaultRenderer(BigDecimal.class, new FacturaTableModel.DecimalFormatRenderer());
        getView().setModelo(getModel());
        getModel().addObserver(getView());
    }            
    
    //TODO Volver ésta clase un handler
    private class ThreadAddTicket extends Thread {

        private final AddTicketDialog dialog;

        public ThreadAddTicket(AddTicketDialog dialog) {
            this.dialog = dialog;
        }

        @Override
        public void run() {
            try {
                String idTicket = dialog.lanzar();
                if (idTicket != null) {
                    
                    FacturaTableModel modelo = (FacturaTableModel) getView().getTabConceptos().getModel();
                    
                    Ticket<?> t = DatasourceContext.instanceDatasourceInstance().getTicket(idTicket);
                    //Comprobación de no duplicación de tickets en factura
                    if(!esTicketApto(t))
                        throw new TicketFacturadoException("Ticket ya agregado a ésta factura");                    
                    
                    for (RenglonTicket renglon : t) {
                        BigDecimal cantidad = renglon.cantidad;
                        BigDecimal ieps     = renglon.ieps;
                        BigDecimal precioUni= renglon.precioUnitario;
                        cantidad .setScale(2, RoundingMode.HALF_EVEN);
                        ieps     .setScale(2, RoundingMode.HALF_EVEN);
                        precioUni.setScale(2, RoundingMode.HALF_EVEN);
                        cantidad .round(MathContext.DECIMAL64);
                        ieps     .round(MathContext.DECIMAL64);
                        precioUni.round(MathContext.DECIMAL64);
                        
                        modelo.setValueAt(cantidad           , modelo.getRowCount() - 1, 0); //0 = Columna cantidad
                        modelo.setValueAt(!renglon.impuestos , modelo.getRowCount() - 1, 5); //5 = Impuestos 0%
                        modelo.setValueAt(renglon.codigo     , modelo.getRowCount() - 1, 1); //1 = Código
                        modelo.setValueAt(renglon.descripcion, modelo.getRowCount() - 1, 2); //2 = Descripción
                        modelo.setValueAt(renglon.unidad     , modelo.getRowCount() - 1, 3); //3 = Unidad
                        modelo.setValueAt(ieps               , modelo.getRowCount() - 1, 6); //6 = % IEPS
                        //Al editar el campo "precioUnitario" se agrega una nueva fila, por este
                        //  "comportamiento sincronizado" coloco al final este SET
                        modelo.setValueAt(precioUni          , modelo.getRowCount() - 1, 4); //4 = Precio unitario con descuento                        
                    }
                    
                    //Agrego el ticket a la relación de tickets agregados a la factura
                    FacturaControl.this.getModel().getTickets().add(t);                    
                }
            } catch (DatasourceException ex) {
                Logger.getLogger(FacturaControl.class.getName()).log(Level.SEVERE, "Problema al importar datos del ticket", ex);
            } catch (NumberFormatException nfe) {
                Logger.getLogger(FacturaControl.class.getName()).log(Level.SEVERE, "ID mal escrito, el formato correcto es #-#-#. Por ejemplo 1-2-653527", nfe);
            } catch (ArrayIndexOutOfBoundsException ae) {
                Logger.getLogger(FacturaControl.class.getName()).log(Level.SEVERE, "ID mal escrito, el formato correcto es #-#-#. Por ejemplo 1-2-653528", ae);
            } catch (PersistenceException pe) {
                Logger.getLogger(FacturaControl.class.getName()).log(Level.SEVERE, "No se pudo conectar a la base de datos del punto de venta", pe);
            } catch (TicketFacturadoException ex) {
                Logger.getLogger(FacturaControl.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            } finally {
                notifyIdle();
            }
        }

        //Comprueba (secuencialmente...) si el ticket ha sido añadido previamente a la factura en edición
        private boolean esTicketApto(Ticket<?> t) {
            for (Ticket ticket : FacturaControl.this.getModel().getTickets()) {
                if(ticket.getId().equals(t.getId())) return false;
            }
            return true;
        }
    }
    private class ThreadRangoTicket extends Thread {

        private final AddTicketRangoDialog dialog;

        public ThreadRangoTicket(AddTicketRangoDialog dialog) {
            this.dialog = dialog;
        }

        @Override
        public void run() {
            try {
                int[] tickets;
                tickets = new int [2];
                tickets = dialog.lanzar();
                if (tickets != null) {
                    
                    FacturaTableModel modelo = (FacturaTableModel) getView().getTabConceptos().getModel();                    
                    Ticket<?> t = DatasourceContext.instanceDatasourceInstance().getTickets(tickets[0], tickets[1]);                    
                    
                    for (RenglonTicket renglon : t) {
                        modelo.setValueAt(renglon.cantidad, modelo.getRowCount() - 1, 0); //0 = Columna cantidad
                        modelo.setValueAt(!renglon.impuestos, modelo.getRowCount() - 1, 5); //5 = Impuestos 0%
                        modelo.setValueAt(renglon.codigo, modelo.getRowCount() - 1, 1); //1 = Código
                        modelo.setValueAt(renglon.descripcion, modelo.getRowCount() - 1, 2); //2 = Descripción
                        modelo.setValueAt(renglon.unidad, modelo.getRowCount() - 1, 3); //3 = Unidad
                        modelo.setValueAt(renglon.precioUnitario, modelo.getRowCount() - 1, 4); //4 = Precio unitario con descuento
                    }
                }
            } catch (DatasourceException ex) {
                Logger.getLogger(FacturaControl.class.getName()).log(Level.SEVERE, "Problema al importar datos del ticket", ex);
            } catch (NumberFormatException nfe) {
                Logger.getLogger(FacturaControl.class.getName()).log(Level.SEVERE, "ID mal escrito, el formato correcto es #-#-#. Por ejemplo 1-2-653527", nfe);
            } catch (ArrayIndexOutOfBoundsException ae) {
                Logger.getLogger(FacturaControl.class.getName()).log(Level.SEVERE, "ID mal escrito, el formato correcto es #-#-#. Por ejemplo 1-2-653528", ae);
            } catch (PersistenceException pe) {
                Logger.getLogger(FacturaControl.class.getName()).log(Level.SEVERE, "No se pudo conectar a la base de datos del punto de venta", pe);
            } finally {
                notifyIdle();
            }
        }
    }
    //TODO Volverlo esta clase un handler
    private class ThreadAddCorteZ extends Thread {

        private final AddFacturaGlobalizadaDialog dialog;
        
        public ThreadAddCorteZ(AddFacturaGlobalizadaDialog d) {
            dialog = d;
        }

        @Override
        public void run() {
            try { 
                //Pregunta al usuario que fecha utilizar para la realización de la factura globalizada
                Date fecha = dialog.lanzar();
                if(fecha == null) return;
                Calendar fechaInicial = Calendar.getInstance();
                fechaInicial.setTime(fecha);
                Calendar fechaFinal = (Calendar) fechaInicial.clone();
                fechaFinal.add(Calendar.DAY_OF_MONTH, 1);
                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String fechaIniciaTxt = sdf.format(fechaInicial.getTime());
                String fechaFinalTxt  = sdf.format(fechaFinal.getTime());
                
                //La factura globalizada es representada como un tipo Ticket, aunque en realidad es 
                //  un resumen (porque contiene conceptos genéricos agrupadores) de uno o más tickets                                
                TicketGlobal facturaGlobalizada = DatasourceContext.instanceDatasourceInstance().getTicketGlobal(fechaIniciaTxt, fechaFinalTxt);
                
                FacturaTableModel modelo = (FacturaTableModel) getView().getTabConceptos().getModel();                    
                    
                //Representa los montos de descuento sobre ventas con IVA a tasa 0%
                BigDecimal descuentos0  = new BigDecimal("0", MathContext.DECIMAL64);
                //Representa los montos de descuento sobre ventas con IVA a tasa 16% 
                BigDecimal descuentos16 = new BigDecimal("0", MathContext.DECIMAL64);
                
                descuentos0 .setScale(2, RoundingMode.HALF_EVEN);
                descuentos16.setScale(2, RoundingMode.HALF_EVEN);
                
                //Llenar el formulario de facturación con información del ticket
                Ticket<String> ticket = facturaGlobalizada.getResumenGlobal();
                for (RenglonTicket renglon : ticket) {
                    modelo.setValueAt(renglon.cantidad, modelo.getRowCount() - 1, 0); //0 = Columna cantidad
                        modelo.setValueAt(!renglon.impuestos, modelo.getRowCount() - 1, 5); //5 = Impuestos 0%
                        modelo.setValueAt(renglon.codigo, modelo.getRowCount() - 1, 1); //1 = Código
                        modelo.setValueAt(renglon.descripcion, modelo.getRowCount() - 1, 2); //2 = Descripción
                        modelo.setValueAt(renglon.unidad, modelo.getRowCount() - 1, 3); //3 = Unidad
                        modelo.setValueAt(renglon.ieps          , modelo.getRowCount() - 1, 6); //6 = % IEPS
                        //Al editar el campo "precioUnitario" se agrega una nueva fila, por este
                        //  "comportamiento sincronizado" coloco al final este SET
                        modelo.setValueAt(renglon.precioUnitario, modelo.getRowCount() - 1, 4); //4 = Precio unitario con descuento
                        
                        if(!renglon.impuestos) 
                            descuentos0 = descuentos0.add(renglon.descuento, MathContext.DECIMAL64);
                        else
                            descuentos16 = descuentos16.add(renglon.descuento, MathContext.DECIMAL64);
                }
                NumberFormat nf = NumberFormat.getIntegerInstance();
                nf.setGroupingUsed(true);
                nf.setMinimumFractionDigits(2);
                nf.setMaximumFractionDigits(2);
                
                getView().getTxtDescuentoTasa0() .setText(nf.format(descuentos0));
                getView().getTxtDescuentoTasa16().setText(nf.format(descuentos16));
                
                //Agrego los tickets a la relación de tickets en la factura
                for (Ticket ticket1 : facturaGlobalizada.getTickets()) {
                    FacturaControl.this.getModel().getTickets().add(ticket1);
                }
                
                //Por defecto hago un refresco manual de las sumas
                renglonesActualizados();
                              
                JOptionPane.showMessageDialog(getView(), "Por favor verifique que los datos de la factura del día sean correctos antes de guardarla.");

            } catch (DatasourceException ex) {
                Logger.getLogger(FacturaControl.class.getName()).log(Level.SEVERE, "Error al obtener información", ex);
            } finally {
                notifyIdle();
            }
        }
    }
}
