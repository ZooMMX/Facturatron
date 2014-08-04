/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facturatron.facturacion;

import facturatron.Dominio.Configuracion;
import facturatron.Dominio.Factura;
import facturatron.MVC.Controller;
import facturatron.Principal.VisorPdf;
import facturatron.cliente.ClienteDao;
import facturatron.config.ConfiguracionDao;
import facturatron.datasource.DatasourceContext;
import facturatron.datasource.DatasourceContext.DATASOURCE;
import facturatron.datasource.DatasourceException;
import facturatron.datasource.IDatasourceService;
import facturatron.facturacion.PAC.IPACService;
import facturatron.facturacion.PAC.PACContext;
import facturatron.facturacion.PAC.PACException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author saul, ORC
 */
public class ListadoControl extends Controller<ListadoModel, ListadoForm> {

    public ListadoControl() {
        setView(new ListadoForm());
        setModel(new ListadoModel());
    }

    @Override
    public void init() {
        try {
            notifyBusy();
            super.init();
            getModel().load();
        } finally {
            notifyIdle();
        }
    }

    public void btnCancelarFactura() {
        int row = getView().getTablaListado().getSelectedRow();
        int id = getModel().getFacturas().get(row).getId();
        FacturaDao factura = (FacturaDao) getModel().getDao().findBy(id);

        String confirmMsg = "¿Realmente quiere cancelar la factura folio " + factura.getFolio() + "?";
        if (JOptionPane.showConfirmDialog(getView(), confirmMsg) == JOptionPane.YES_OPTION) {
            try {             
                // Cancelar la factura en omoikane y con el PAC
                factura.cancelar();                
                // Habilitar tickets para refacturar en el datasource, siempre que haya uno
                if(Configuracion.getConfig().getConectorDatasource() != DATASOURCE.Ninguno) {
                    IDatasourceService ds = DatasourceContext.instanceDatasourceInstance();
                    ds.cancelarFactura(factura.getId());
                }   
                // Recargar el modelo (la tabla de facturas)
                getModel().load();
                JOptionPane.showMessageDialog(getView(), "Factura cancelada");
            } catch (SQLException ex) {
                Logger.getLogger(ListadoControl.class.getName()).log(Level.SEVERE, "Error al almacenar comprobante cancelado", ex);
            } catch (PACException ex) {
                Logger.getLogger(ListadoControl.class.getName()).log(Level.SEVERE, "No se canceló el comprobante.", ex);
            } catch (DatasourceException ex) {
                Logger.getLogger(ListadoControl.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }
    
    public void btnCancelarFacturaManual() {

        String confirmMsg = JOptionPane.showInputDialog(
                getView(),
                "Con esta ventana usted puede cancelar un comprobante que no se encuentra en la lista, tan solo escribiendo su UUID",
                "Cancelación manual",
                JOptionPane.QUESTION_MESSAGE);
        if (confirmMsg != null && !confirmMsg.isEmpty()) {
            try {
                IPACService impl = PACContext.instancePACService();
                Factura comp = new Factura();
                comp.setFolioFiscal(confirmMsg);
                comp.setEmisor((new ClienteDao()).findBy(1));
                
                Boolean isCancelado = impl.cancelar(comp);
                
                getModel().load();
                JOptionPane.showMessageDialog(getView(), "Comprobante cancelado");
            } catch (PACException ex) {
                Logger.getLogger(ListadoControl.class.getName()).log(Level.SEVERE, "No se canceló el comprobante.", ex);
            } catch (SQLException ex) {
                Logger.getLogger(ListadoControl.class.getName()).log(Level.SEVERE, "Error relacionado con la BD al consultar emisor", ex);
            }
        }
    }

    public void btnVerFactura() {
        try {
            int row = getView().getTablaListado().getSelectedRow();
            int id = getModel().getFacturas().get(row).getId();
            FacturaDao factura = (FacturaDao) getModel().getDao().findBy(id);
            Configuracion cfg = new ConfiguracionDao().load();
            
            VisorPdf.abrir(factura.getPdfPath(), factura.toComprobanteTron(), cfg.getPathPlantilla());
        } catch (IOException ex) {
            Logger.getLogger(ListadoControl.class.getName()).log(Level.SEVERE, "Error de lectura IO", ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ListadoControl.class.getName()).log(Level.SEVERE, "Error en hilo de ejecución", ex);
        } catch (Exception ex) {
            Logger.getLogger(ListadoControl.class.getName()).log(Level.SEVERE, "Error de lectura o interpretación del XML", ex);
        }
    }
    /** Acción del botón status SAT
     * Muestra valores internos de un comprobante y cancelación, y su estado de envío del PAC al SAT
     */
    public void btnStatusSAT() {
        try {
            int row = getView().getTablaListado().getSelectedRow();
            int id = getModel().getFacturas().get(row).getId();
            FacturaDao factura = (FacturaDao) getModel().getDao().findBy(id);
            if(factura.getFolioFiscal() == null) { JOptionPane.showMessageDialog(getView(), "Comprobante almacenado erróneamente no contiene folio fiscal"); return; }
            IPACService pac = PACContext.instancePACService();
            
            StringBuilder statusString = new StringBuilder();
            statusString.append("Estado interno del comprobante: ").append( factura.getEstadoComprobante().toString()).append("\r\n");
            statusString.append("Folio fiscal del comprobante: ").append( factura.getFolioFiscal()).append("\r\n");
            statusString.append( pac.getStatusTimbre(factura).getMensaje() ).append("\r\n");
            if(factura.getEstadoComprobante() == Factura.Estado.CANCELADO)  statusString.append( pac.getStatusCancelacion(factura).getMensaje() ).append("\r\n");
            
            JFrame mainJFrame = (JFrame) JFrame.getFrames()[0];
            FacturaStatusDialog dialog = new FacturaStatusDialog(mainJFrame, statusString.toString());
            dialog.lanzar();
        } catch (PACException ex) {
            Logger.getLogger(ListadoControl.class.getName()).log(Level.SEVERE, "Error al contactar al PAC para obtener status", ex);
        }
    }
            
    /**
     * @return the form
     */
    @Override
    public ListadoForm getView() {

        return super.getView();
    }

    @Override
    public void asignarEventos() {
        getView().getBtnCancelarFactura().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread() {

                    @Override
                    public void run() {
                        notifyBusy();
                        btnCancelarFactura();
                        notifyIdle();
                    }
                }.start();
            }
        });
        getView().getBtnCancelarFacturaManual().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread() {

                    @Override
                    public void run() {
                        notifyBusy();
                        btnCancelarFacturaManual();
                        notifyIdle();
                    }
                }.start();
            }
        });
        getView().getBtnStatusSAT().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {   //se crea una nueva ventana con los datos de la factura seleccionada
                btnStatusSAT();
            }
        });
        getView().getBtnVerFactura().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {   //se crea una nueva ventana con los datos de la factura seleccionada
                btnVerFactura();
            }
        });
        getView().getDesde().addPropertyChangeListener("date", new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                new Thread() {

                    @Override
                    public void run() {
                        notifyBusy();
                        getModel().setFechaInicial(new java.sql.Date(getView().getDesde().getDate().getTime()));
                        notifyIdle();
                    }
                }.start();
            }
        });
        getView().getHasta().addPropertyChangeListener("date", new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                new Thread() {

                    @Override
                    public void run() {
                        notifyBusy();
                        getModel().setFechaFinal(new java.sql.Date(getView().getHasta().getDate().getTime()));
                        notifyIdle();
                    }
                }.start();
            }
        });
    }

    @Override
    public void enlazarModeloVista() {
        ListadoTableModel tableModel = new ListadoTableModel();
        tableModel.setData(getModel().getFacturas());
        getView().setModel(getModel());
        getView().setTableModel(tableModel);
        getView().getDesde().setDate(getModel().getFechaInicial());
        getView().getHasta().setDate(getModel().getFechaFinal());
        getModel().addObserver(getView());
    }
}
