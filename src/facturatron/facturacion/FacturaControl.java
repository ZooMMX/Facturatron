/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.facturacion;

import facturatron.Dominio.Configuracion;
import facturatron.Dominio.Factura;
import facturatron.Dominio.Renglon;
import java.awt.event.ActionEvent;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import facturatron.MVC.Controller;
import facturatron.cliente.ClienteDao;
import facturatron.config.ConfigFiscalDao;
import facturatron.config.ConfiguracionDao;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
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
          getModel().setDescuentoTasa0(Double.valueOf(txtDesc0.replaceAll(",", "")));
          getModel().setDescuentoTasa16(Double.valueOf(txtDesc16.replaceAll(",", "")));
      } catch(NumberFormatException nfe) {
          txtDesc0  = "0";
          txtDesc16 = "0";
      }
      renglonesActualizados();
  }
  public void btnBuscarCliente() {
      int id = Integer.valueOf(getView().getTxtIdCliente().getText());
      ClienteDao cliente = (new ClienteDao()).findBy(id);
      if(cliente != null) {
            getModel().setReceptor(cliente);
      } else {
            JOptionPane.showMessageDialog(getView(), "Cliente no encontrado");
      }
  }

  public void renglonesActualizados() {
      Double subtotal = 0d, impuesto = 0d, total = 0d, descuento0 = 0d, descuento16 = 16d, importe0 = 0d, importe16 = 0d;

      for (Renglon renglon : getModel().getRenglones()) {
          if(renglon.getTasa0()) {
              importe0 += renglon.getImporte();
          } else {
              importe16 += renglon.getImporte();
          }

          subtotal += renglon.getImporte();
      }

      descuento0 = getModel().getDescuentoTasa0();
      descuento16= getModel().getDescuentoTasa16();

      if(descuento0 < importe0) { importe0 = importe0 - descuento0; }
      if(descuento16 < importe16) { importe16 = importe16 - descuento16; }

      impuesto   = redondear(importe16 * 0.16);
      total      = importe0 + importe16 + impuesto;
      
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
        getModel().setIvaTrasladado(Double.valueOf(getView().getTxtIva().getText().replaceAll(",", "")));
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
        getView().setModelo(getModel());
        getModel().addObserver(getView());
    }

    private Double redondear(double d) {
        return Math.ceil(d*100)/100;
    }

}