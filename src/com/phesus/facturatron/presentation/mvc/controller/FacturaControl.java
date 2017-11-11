/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.phesus.facturatron.presentation.mvc.controller;

import com.phesus.facturatron.presentation.mvc.view.CeldaBuscadorUnidades;
import com.phesus.facturatron.presentation.mvc.view.CeldaBuscadorProductos;
import com.phesus.facturatron.presentation.mvc.view.CeldaDescripcion;
import com.phesus.facturatron.presentation.mvc.view.PopupBuscarCliente;
import com.phesus.facturatron.presentation.mvc.view.dialog.AddTicketRangoDialog;
import com.phesus.facturatron.presentation.mvc.view.dialog.AddTicketDialog;
import com.phesus.facturatron.presentation.mvc.view.AddFacturaGlobalizadaDialog;
import com.phesus.facturatron.presentation.mvc.view.FacturasRelacionadasDialog;
import com.phesus.facturatron.presentation.mvc.view.FacturaObservacionesDialog;
import com.phesus.facturatron.presentation.mvc.view.FacturaForm;
import com.phesus.facturatron.presentation.mvc.model.FacturaTableModel;
import com.alee.laf.label.WebLabel;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import facturatron.Dominio.Configuracion;
import static facturatron.Dominio.Configuracion.getConfig;
import facturatron.Dominio.Factura;
import facturatron.Dominio.FormaDePago;
import facturatron.Dominio.Medida;
import facturatron.Dominio.MetodoDePagoEnum;
import facturatron.Dominio.Persona;
import facturatron.Dominio.Producto;
import com.phesus.facturatron.presentation.mvc.model.RenglonModel;
import facturatron.Dominio.UsoCFDIEnum;
import java.awt.event.ActionEvent;
import java.sql.Time;
import java.util.ArrayList;
import facturatron.mvc.Controller;
import com.phesus.facturatron.persistence.dao.ClienteDao;
import facturatron.config.ConfigFiscalDao;
import facturatron.datasource.DatasourceContext;
import facturatron.datasource.DatasourceException;
import facturatron.facturacion.PAC.PACException;
import facturatron.datasource.IDatasourceService;
import facturatron.datasource.RenglonTicket;
import facturatron.datasource.Ticket;
import facturatron.datasource.TicketGlobal;
import facturatron.datasource.omoikane.TicketOmoikane;
import com.phesus.facturatron.presentation.mvc.view.PopupBuscarCliente.ClienteAction;
import com.phesus.facturatron.persistence.dao.FacturaDao;
import facturatron.lib.Java2sAutoComboBox;
import facturatron.lib.entities.CFDv3Tron;
import facturatron.lib.entities.ComprobanteTron;
import facturatron.omoikane.exceptions.TicketFacturadoException;
import com.phesus.facturatron.persistence.dao.ProductoDao;
import com.phesus.facturatron.persistence.dao.UnidadDao;
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
import java.util.Map;
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
import javax.xml.datatype.XMLGregorianCalendar;
import mx.bigdata.sat.cfdi.v33.schema.CMetodoPago;
import mx.bigdata.sat.cfdi.v33.schema.CTipoDeComprobante;

import mx.bigdata.sat.cfdi.v33.schema.Comprobante;
import mx.bigdata.sat.cfdi.v33.schema.ObjectFactory;
import mx.bigdata.sat.cfdi.v33.schema.TimbreFiscalDigital;
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
      dao.getEmisor().setRegimen(configFiscal.getContribuyente().getRegimen());

      return dao;
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

  /**
   * Actualiza las sumas y la información de tickets
   */
  public void renglonesActualizados() {
      MathContext mc = MathContext.DECIMAL128;

      BigDecimal subtotal    = new BigDecimal("0.00", mc);
      BigDecimal iva         = new BigDecimal("0.00", mc);
      BigDecimal ieps        = new BigDecimal("0.00", mc);
      BigDecimal total       = new BigDecimal("0.00", mc);
      BigDecimal descuento   = new BigDecimal("0.00", mc);

      subtotal   .setScale(2);
      iva        .setScale(2);
      ieps       .setScale(2);
      total      .setScale(2);
      descuento  .setScale(2);

      int i=0;
      for (RenglonModel renglon : getModel().getRenglones()) {
          i++;
          descuento= descuento.add ( renglon.getDescuento() );
          iva      = iva.add( renglon.getIVA() );
          ieps     = ieps.add( renglon.getIEPS() );
          subtotal = subtotal.add(renglon.getImporte());
          
      }

      total=subtotal.subtract(descuento,mc).add(ieps,mc).add(iva,mc);
      //total      = total.add( ieps , mc ).add( iva, mc ).add( subtotal, mc ).subtract( descuento, mc );
      
      getModel().setSubtotal(subtotal);
      getModel().setIvaTrasladado(iva);
      getModel().setIEPSTrasladado(ieps);
      getModel().setDescuento(descuento);
      getModel().setTotal(total);


  }
  public void btnObservaciones() {
      String observaciones = getModel().getObservaciones();
      FacturaObservacionesDialog dialog = new FacturaObservacionesDialog(getView().getJFrameParent(), observaciones);
      String newObs = dialog.lanzar();
      if(newObs != null) { observaciones = newObs; }
      getModel().setObservaciones(observaciones);
  }
  
  public void btnFacturasRelacionadas(){
      String facturasRelacionadas=getModel().getFacturasRelacionadas()==null?"":getModel().getFacturasRelacionadas();
      String tipoRelacionDeFacturasRelacionadas=getModel().getTipoRelacionDeFacturaRelacionada()==null||getModel().getTipoRelacionDeFacturaRelacionada().contentEquals("")?"1":getModel().getTipoRelacionDeFacturaRelacionada();
      FacturasRelacionadasDialog dialog = new FacturasRelacionadasDialog(getView().getJFrameParent(), facturasRelacionadas, tipoRelacionDeFacturasRelacionadas);
      java.util.List<String> newObs = dialog.lanzar();
      if(newObs != null) { 
          facturasRelacionadas = newObs.get(0); 
          tipoRelacionDeFacturasRelacionadas = newObs.get(1);       
      }
      getModel().setTipoRelacionDeFacturaRelacionada(tipoRelacionDeFacturasRelacionadas);
      getModel().setFacturasRelacionadas(facturasRelacionadas);
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
      if( getView().getjComboBoxFormaPago().getSelectedItem() == null ) { 
          JOptionPane.showMessageDialog(getView(), "El campo forma de pago no puede estar vacío");
          return false;
      }
      
      //Validar Metodo de pago
      if( getView().getComboMetodoDePago() == null) { 
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
      ArrayList<RenglonModel> renglones = ((FacturaTableModel) getView().getTabConceptos().getModel()).getData();
      //Validar cada renglon
      Integer filas = 0;
      ArrayList<RenglonModel> vacio=new ArrayList<>();
      Boolean vacios=false;
      for (RenglonModel renglon : renglones) {
          /** Validar cantidad; la cantidad también sirve como bandera para indicar si el renglón se toma en cuenta
           * o no
           */
          BigDecimal cantidad = renglon.getCantidad();
          if(cantidad == null || cantidad.compareTo(new BigDecimal(0)) == 0) {
              //Esta fila tiene una cantidad en cero, no es tomada en cuenta
              vacio.add(renglon);
              vacios=true;
              continue;
          } else {
              //Esta se toma en cuenta
              filas++;
          }
          //Validar valor unitario
          BigDecimal valor = renglon.getValorUnitario();
          /*
          if(valor != null) {
              JOptionPane.showMessageDialog(getView(), valor.toPlainString()+"---La columna PU (precio unitario) tiene un contenido inválido en la fila " + filas);
              return false;
          } */                 
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
      if(vacios){
          for(RenglonModel renglon:vacio){
              renglones.remove(renglon);
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

    if(getModel().getEmisor().getRegimen()==null||getModel().getEmisor().getRegimen().isEmpty()||getModel().getEmisor().getRegimen().contentEquals("NA")){
        Logger.getLogger(FacturaControl.class.getName()).log(Level.SEVERE, "El régimen fiscal del emisor no debe estar vacío!");
        return;
    }
    
    /*if(getModel().getReceptor().getRegimen()==null||getModel().getReceptor().getRegimen().isEmpty()||getModel().getReceptor().getRegimen().contentEquals("NA")){
        Logger.getLogger(FacturaControl.class.getName()).log(Level.SEVERE, "El régimen fiscal del receptor no debe estar vacío!");
        return;
    }*/
        
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
        getModel().setFormaDePago((FormaDePago) getView().getjComboBoxFormaPago().getSelectedItem());
        getModel().setMetodoDePago((MetodoDePagoEnum) getView().getComboMetodoDePago().getSelectedItem());
        getModel().setIvaTrasladado(new BigDecimal(getView().getTxtIva().getText().replaceAll(",", "")));
        getModel().setMotivoDescuento(getView().getTxtMotivoDescuento().getText());
        getModel().setReceptor((new ClienteDao()).findBy(Integer.valueOf(getView().getTxtIdCliente().getText())));
        getModel().getReceptor().setUsoCFDI((UsoCFDIEnum) getView().getComboUsoCFDI().getSelectedItem());
        
        //Tomo la primera letra para hacerlo retrocompatible, antes se usaba "INGRESO" ahora "I", "EGRESO" ahora "E", etc.
        CTipoDeComprobante tipo = getView().getTipoComprobante().getEfectoComprobante(); 
        getModel().setTipoDeComprobante(tipo);
        getModel().setVersion("3.3");

        getView().getBtnGuardar().setEnabled(false);
        getModel().persist();
        
        timbrado = true;
        marcarTicketsFacturados(getModel().getId());
    } catch (SAXParseException e) {
        Logger.getLogger(FacturaControl.class.getName()).log(Level.SEVERE, "'Clave producto SAT', 'Clave unidad SAT', o 'Régimen' incorrectos.", e);
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
  
  public void updateTicketInfo() {
      NumberFormat nf = NumberFormat.getNumberInstance();
      nf.setMinimumFractionDigits(2);
      nf.setMaximumFractionDigits(2);
      
      StringBuilder ticketInfo = new StringBuilder("\n");
      StringBuilder renglonInfo = new StringBuilder("");
      Boolean first = true;
      for(Ticket<RenglonTicket> t : getModel().getTickets()) {
          if(first) 
              first = false; 
          else
          renglonInfo = new StringBuilder("");
          renglonInfo.append(t.getFolio());
          renglonInfo.append(":");
          renglonInfo.append(nf.format(t.getImporte()));
          renglonInfo.append("(");
              
          Boolean comma = true;
          for(Map.Entry<String, BigDecimal> entry : t.getImportesImpuestos().entrySet()) {
              if(comma) { comma = false; } else { renglonInfo.append("; "); }
                String impuesto = entry.getKey();
                BigDecimal importe = entry.getValue();
                renglonInfo.append(impuesto+": "+importe);
          } 
          ticketInfo.append(String.format("%-70s", renglonInfo.toString()));
      }
      //String.format("%-124.124s", ticketInfo.toString());
      getModel().setTicketInfo(ticketInfo.toString());
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
        getModel().setFormaDePago((FormaDePago) getView().getjComboBoxFormaPago().getSelectedItem());
        getModel().setMetodoDePago((MetodoDePagoEnum) getView().getComboMetodoDePago().getSelectedItem());
        getModel().setIvaTrasladado(new BigDecimal(getView().getTxtIva().getText().replaceAll(",", "")));
        getModel().setMotivoDescuento(getView().getTxtMotivoDescuento().getText());
        getModel().setReceptor((new ClienteDao()).findBy(Integer.valueOf(getView().getTxtIdCliente().getText())));
        //El USO del CFDI se guarda a nivel de comprobante y luego se aplica al receptor        
        getModel().getReceptor().setUsoCFDI( UsoCFDIEnum.D_03 );
        
        CTipoDeComprobante tipo = getView().getTipoComprobante().getEfectoComprobante(); 
        getModel().setTipoDeComprobante(tipo);
        getModel().setVersion("3.3");  
          
        ObjectFactory of = new ObjectFactory();
        TimbreFiscalDigital timbre = of.createTimbreFiscalDigital();       
        
        timbre.setFechaTimbrado( XMLGregorianCalendarImpl.createDateTime(2000, 1, 1, 0, 0, 0) );
        timbre.setNoCertificadoSAT( "30001000000100000801" );
        timbre.setVersion("1.1");
        timbre.setUUID( "ad662d33-6934-459c-a128-bdf0393e0f44" );
        timbre.setSelloSAT( "j5bSpqM3w0+shGtlmqOwqqy6+d659O78ckfstu5vTSFa+2CVMj6Awfr18x4yMLGBwk6ruYbjBIVURodEII6nJIhTTUtYQV1cbRDG9kvvhaNAakx qaSOnOnOx79nHxqFPRVoqh10CsjocS9PZkSM2jz1uwLgaF0knf1g8pjDkLYwlk=" );
        timbre.setSelloCFD( "tOSe+Ex/wvn33YIGwtfmrJwQ31Crd7II9VcH63TGjHfxk5cfb3q9uSbDUGk9TXvo70ydOpikRVw+9B2Six0mbu3PjoPpO909oAYITrRyomdeUGJ 4vmA2/12L86EJLWpU7vlt4cL8HpkEw7TOFhSdpzb/890+jP+C1adBsHU1VHc=" );

        Comprobante.Complemento complemento = of.createComprobanteComplemento();
        complemento.getAny().add(timbre);
        
        Configuracion cfg = getConfig();
        ComprobanteTron ct = getModel().toComprobanteTron();
        CFDv3Tron cfd = new CFDv3Tron();
        cfd.setComprobante(ct);  
        
        ct.getComplemento().add(complemento);

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
        getView().getComboMetodoDePago().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(getView().getComboMetodoDePago().getSelectedItem()!=null)
                    getModel().setMetodoDePago((MetodoDePagoEnum) getView().getComboMetodoDePago().getSelectedItem());
            }
        });
        getView().getjComboBoxFormaPago().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(getView().getjComboBoxFormaPago().getSelectedItem()!=null)
                    getModel().setFormaDePago((FormaDePago) getView().getjComboBoxFormaPago().getSelectedItem());
            }
        });
        getView().getComboUsoCFDI().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(getView().getComboUsoCFDI().getSelectedItem()!=null)
                    getModel().getReceptor().setUsoCFDI((UsoCFDIEnum) getView().getComboUsoCFDI().getSelectedItem());
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
        getView().getBtnFacturasRelacionadas().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                btnFacturasRelacionadas();
            }
        });
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
        
        /* Este bloque desencadenaba cambios cuando los descuentos eran decididos por campos globales,
                al cambiar un descuento global se recalculaban varios campos, entre ellos los impuestos.
            Lo dejo aquí por que puede ser de interés general. */
        /*
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
        */
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
        getView().setTableWidth(); // Establecer anchos de columnas
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
                    
                    Ticket<RenglonTicket> t = DatasourceContext.instanceDatasourceInstance().getTicket(idTicket);
                    //Comprobación de no duplicación de tickets en factura
                    if(!esTicketApto(t))
                        throw new TicketFacturadoException("Ticket ya agregado a ésta factura");                    
                    
                    for (RenglonTicket renglon : t) {
                        BigDecimal cantidad = renglon.cantidad;
                        BigDecimal ieps     = renglon.ieps;
                        BigDecimal precioUni= renglon.precioUnitario;
                        BigDecimal descuento= renglon.descuento;
                        Boolean isExento = renglon.exento==null?false:renglon.exento;
                        cantidad .setScale(2, RoundingMode.HALF_EVEN);
                        ieps     .setScale(2, RoundingMode.HALF_EVEN);
                        precioUni.setScale(2, RoundingMode.HALF_EVEN);
                        descuento.setScale(2, RoundingMode.HALF_EVEN);
                        cantidad .round(MathContext.DECIMAL64);
                        ieps     .round(MathContext.DECIMAL64);
                        precioUni.round(MathContext.DECIMAL64);
                        descuento.round(MathContext.DECIMAL64);                        
                        
                        modelo.setValueAt(cantidad                  , modelo.getRowCount() - 1, 0);     //0 = Columna cantidad  
                        modelo.setValueAt(isExento                  , modelo.getRowCount() - 1, 7);     //7 = Exento
                        modelo.setValueAt(!renglon.impuestos        , modelo.getRowCount() - 1, 8);     //8 = Impuestos 0%
                        modelo.setValueAt(renglon.codigo            , modelo.getRowCount() - 1, 1);     //1 = Código
                        modelo.setValueAt(renglon.claveProductoSAT  , modelo.getRowCount() - 1, 2);     //2 = Clave producto SAT
                        modelo.setValueAt(renglon.descripcion       , modelo.getRowCount() - 1, 3);     //3 = Descripción
                        modelo.setValueAt(renglon.unidad            , modelo.getRowCount() - 1, 4);     //4 = Unidad
                        modelo.setValueAt(renglon.claveUnidadSAT    , modelo.getRowCount() - 1, 5);     //5 = Clave Unidad SAT
                        modelo.setValueAt(ieps                      , modelo.getRowCount() - 1, 9);     //9 = % IEPS
                        modelo.setValueAt(descuento                 , modelo.getRowCount() - 1, 10);    //10 = Descuento
                        
                        
                        //Al editar el campo "precioUnitario" se agrega una nueva fila, por este
                        //  "comportamiento sincronizado" coloco al final este SET
                        modelo.setValueAt(precioUni                 , modelo.getRowCount() - 1, 6);     //6 = Precio unitario con descuento
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
                        modelo.setValueAt(renglon.cantidad          , modelo.getRowCount() - 1, 0); //0 = Columna cantidad
                        modelo.setValueAt(!renglon.impuestos        , modelo.getRowCount() - 1, 7); //7 = Impuestos 0%
                        modelo.setValueAt(renglon.codigo            , modelo.getRowCount() - 1, 1); //1 = Código
                        modelo.setValueAt(renglon.claveProductoSAT  , modelo.getRowCount() - 1, 2); //2 = Clave producto SAT
                        modelo.setValueAt(renglon.descripcion       , modelo.getRowCount() - 1, 3); //3 = Descripción
                        modelo.setValueAt(renglon.unidad            , modelo.getRowCount() - 1, 4); //4 = Unidad
                        modelo.setValueAt(renglon.claveUnidadSAT    , modelo.getRowCount() - 1, 5); //5 = Clave Unidad SAT
                        modelo.setValueAt(renglon.ieps              , modelo.getRowCount() - 1, 8); //8 = % IEPS
                        modelo.setValueAt(renglon.descuento         , modelo.getRowCount() - 1, 9); //9 = Descuento
                        //Al editar el campo "precioUnitario" se agrega una nueva fila, por este
                        //  "comportamiento sincronizado" coloco al final este SET
                        modelo.setValueAt(renglon.precioUnitario    , modelo.getRowCount() - 1, 6); //6 = Precio unitario con descuento
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
                BigDecimal descuentos   = new BigDecimal("0", MathContext.DECIMAL64);
                
                //Llenar el formulario de facturación con información del ticket
                Ticket<String> ticket = facturaGlobalizada.getResumenGlobal();
                for (RenglonTicket renglon : ticket) {
                    
                    Boolean isExento = renglon.exento==null?false:renglon.exento;
                    
                    modelo.setValueAt(renglon.cantidad          , modelo.getRowCount() - 1, 0);     //0 = Columna cantidad
                    modelo.setValueAt(isExento                  , modelo.getRowCount() - 1, 7);     //7 = Exento
                    modelo.setValueAt(!renglon.impuestos        , modelo.getRowCount() - 1, 8);     //8 = Impuestos 0%
                    modelo.setValueAt(renglon.codigo            , modelo.getRowCount() - 1, 1);     //1 = Código
                    modelo.setValueAt(renglon.claveProductoSAT  , modelo.getRowCount() - 1, 2);     //2 = Clave producto SAT
                    modelo.setValueAt(renglon.descripcion       , modelo.getRowCount() - 1, 3);     //3 = Descripción
                    modelo.setValueAt(renglon.unidad            , modelo.getRowCount() - 1, 4);     //4 = Unidad
                    modelo.setValueAt(renglon.claveUnidadSAT    , modelo.getRowCount() - 1, 5);     //5 = Clave Unidad SAT
                  
                    modelo.setValueAt(renglon.ieps              , modelo.getRowCount() - 1, 9);     //9 = % IEPS
                    modelo.setValueAt(renglon.descuento         , modelo.getRowCount() - 1, 10);    //10 = Descuento
                    //Al editar el campo "precioUnitario" se agrega una nueva fila, por este
                    //  "comportamiento sincronizado" coloco al final este SET
                    modelo.setValueAt(renglon.precioUnitario    , modelo.getRowCount() - 1, 6);     //6 = Precio unitario con descuento
                        
                }
                NumberFormat nf = NumberFormat.getIntegerInstance();
                nf.setGroupingUsed(true);
                nf.setMinimumFractionDigits(2);
                nf.setMaximumFractionDigits(2);
                
                getView().getTxtDescuento() .setText(nf.format(descuentos));
                
                //Agrego los tickets a la relación de tickets en la factura
                for (Ticket ticket1 : facturaGlobalizada.getTickets()) {
                    FacturaControl.this.getModel().getTickets().add(ticket1);
                }
                
                //Por defecto hago un refresco manual de las sumas
                renglonesActualizados();
                      
                updateTicketInfo(); //Concatena información de cada ticket en attr ticketInfo
                              
                JOptionPane.showMessageDialog(getView(), "Por favor verifique que los datos de la factura del día sean correctos antes de guardarla.");

            } catch (DatasourceException ex) {
                Logger.getLogger(FacturaControl.class.getName()).log(Level.SEVERE, "Error al obtener información", ex);
            } finally {
                notifyIdle();
            }
        }
    }
}
