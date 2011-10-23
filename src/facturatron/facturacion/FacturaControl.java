/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.facturacion;

import facturatron.Dominio.Factura;
import facturatron.Dominio.Renglon;
import java.awt.event.ActionEvent;
import java.sql.Time;
import java.util.ArrayList;
import facturatron.MVC.Controller;
import facturatron.cliente.ClienteDao;
import facturatron.config.ConfigFiscalDao;
import facturatron.omoikane.RenglonTicket;
import facturatron.omoikane.Ticket;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author saul
 */
public class FacturaControl extends Controller<FacturaDao, FacturaForm> {  //solo controlador

  ConfigFiscalDao configFiscal;

  public FacturaControl(){
      setModel(setupAndInstanceModel());
      setView(new FacturaForm());
      init();
  }

  public FacturaDao setupAndInstanceModel() {
      FacturaDao      dao          = new FacturaDao();
      configFiscal = new ConfigFiscalDao();
      configFiscal.load();
      dao.setAnoAprobacion(configFiscal.getAnoAprobacion());
      dao.setNoAprobacion(configFiscal.getNoAprobacion());
      dao.setNoCertificado(configFiscal.getNoCertificado());
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
  public void btnBuscarCliente() {
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
      }
  }

  public void renglonesActualizados() {
      MathContext mc = MathContext.DECIMAL128;

      BigDecimal subtotal    = new BigDecimal("0.00", mc);
      BigDecimal impuesto    = new BigDecimal("0.00", mc);
      BigDecimal total       = new BigDecimal("0.00", mc);
      BigDecimal descuento0  = new BigDecimal("0.00", mc);
      BigDecimal descuento16 = new BigDecimal("16.00", mc);
      BigDecimal importe0    = new BigDecimal("0.00", mc);
      BigDecimal importe16   = new BigDecimal("0.00", mc);

      subtotal   .setScale(2);
      impuesto   .setScale(2);
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
          subtotal = subtotal.add(renglon.getImporte());
      }

      descuento0 = getModel().getDescuentoTasa0();
      descuento16= getModel().getDescuentoTasa16();

      if(descuento0  .compareTo(importe0)  < 0) { importe0  = importe0 .subtract(descuento0 ); }
      if(descuento16 .compareTo(importe16) < 0) { importe16 = importe16.subtract(descuento16); }

      impuesto   = importe16.multiply(new BigDecimal("0.16"), mc);
      total      = importe0.add(importe16, mc).add(impuesto, mc);
      
      getModel().setSubtotal(subtotal);
      getModel().setIvaTrasladado(impuesto);
      getModel().setTotal(total);

  }
  public void btnObservaciones() {
      String observaciones = getModel().getObservaciones();
      FacturaObservacionesDialog dialog = new FacturaObservacionesDialog(null, observaciones);
      String newObs = dialog.lanzar();
      if(newObs != null) { observaciones = newObs; }
      getModel().setObservaciones(observaciones);
  }
  /*
   * Éste método no importa el importe de cada renglón ni de la venta general, su cálculo es responsabilidad de los modelos
   * de la factura
   */
  public void btnAddTicket() {
      AddTicketDialog dialog = new AddTicketDialog(null);
      notifyBusy();
      new ThreadAddTicket(dialog).start();
  }
  public void btnGuardar(){

    notifyBusy();
    try {
        Calendar time = Calendar.getInstance();
        getModel().setCertificado("NULO");
        getModel().setEmisor((new ClienteDao()).findBy(1));
        getModel().setEmisorSucursal((new ClienteDao()).findBy(2));
        getModel().setFecha(time.getTime());
        getModel().setHora(new Time(time.getTime().getTime()));
        getModel().setFormaDePago(getView().getTxtFormaDePago().getText());
        getModel().setIvaTrasladado(new BigDecimal(getView().getTxtIva().getText().replaceAll(",", "")));
        getModel().setMotivoDescuento(getView().getTxtMotivoDescuento().getText());
        getModel().setReceptor((new ClienteDao()).findBy(Integer.valueOf(getView().getTxtIdCliente().getText())));
        getModel().setTipoDeComprobante("ingreso");
        getModel().setVersion("2.0");

        getView().getBtnGuardar().setEnabled(false);
        Integer folio    = getModel().getFolio().intValue();
        Integer folioMin = configFiscal.getFolioInicial();
        Integer folioFin = configFiscal.getFolioFinal();

        if(folio < folioMin  || folio > folioFin) {
            JOptionPane.showMessageDialog(getView(), "Folio fuera de rango. Posiblemente se terminaron los folios asignados por el SAT. Revise su configuración fiscal.");
        } else {
            getModel().persist();
        }
    } catch (Exception ex) {
        getView().getBtnGuardar().setEnabled(true);
        Logger.getLogger(FacturaControl.class.getName()).log(Level.SEVERE, "Error al generar factura", ex);
    } finally {
        notifyIdle();
    }
    
  }


    @Override
    public void asignarEventos() {
     getView().getBtnGuardar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Factura> rep = new ArrayList<Factura>();
                new Thread() {
                    public void run() {
                        btnGuardar();
                    }
                }.start();
                //  new Reporte("facturatron/factura.jasper",FacturaDao.findById(10)); //buscamos la ruta donde se encuentra el reporte jasper
                // reporte.lanzarPreview(null);
            }
        });
        getView().getTabConceptos().getModel().addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                renglonesActualizados();
            }
        });
        getView().getBtnBuscarCliente().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnBuscarCliente();
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

    private class ThreadAddTicket extends Thread {

        private final AddTicketDialog dialog;

        public ThreadAddTicket(AddTicketDialog dialog) {
            this.dialog = dialog;
        }

        public void run() {
            try {
                String idTicket = dialog.lanzar();
                if (idTicket != null) {
                    String[] args = idTicket.split("-");
                    Integer idAlmacen = Integer.valueOf(args[0]);
                    Integer idCaja = Integer.valueOf(args[1]);
                    Integer idVenta = Integer.valueOf(args[2]);
                    FacturaTableModel modelo = (FacturaTableModel) getView().getTabConceptos().getModel();
                    Ticket t = Ticket.getTicketData(idAlmacen, idCaja, idVenta);
                    for (RenglonTicket renglon : t) {
                        modelo.setValueAt(renglon.cantidad, modelo.getRowCount() - 1, 0); //0 = Columna cantidad
                        modelo.setValueAt(!renglon.impuestos, modelo.getRowCount() - 1, 5); //5 = Impuestos 0%
                        modelo.setValueAt(renglon.codigo, modelo.getRowCount() - 1, 1); //1 = Código
                        modelo.setValueAt(renglon.descripcion, modelo.getRowCount() - 1, 2); //2 = Descripción
                        modelo.setValueAt(renglon.unidad, modelo.getRowCount() - 1, 3); //3 = Unidad
                        modelo.setValueAt(renglon.precioUnitario, modelo.getRowCount() - 1, 4); //4 = Precio unitario con descuento
                    }
                }
            } catch (NumberFormatException nfe) {
                Logger.getLogger(FacturaControl.class.getName()).log(Level.SEVERE, "ID mal escrito, el formato correcto es #-#-#. Por ejemplo 1-2-653527", nfe);
            } catch (ArrayIndexOutOfBoundsException ae) {
                Logger.getLogger(FacturaControl.class.getName()).log(Level.SEVERE, "ID mal escrito, el formato correcto es #-#-#. Por ejemplo 1-2-653527", ae);
            } catch (PersistenceException pe) {
                Logger.getLogger(FacturaControl.class.getName()).log(Level.SEVERE, "No se pudo conectar a la base de datos del punto de venta", pe);
            } finally {
                notifyIdle();
            }
        }
    }


}