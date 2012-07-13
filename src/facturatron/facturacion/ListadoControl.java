/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facturatron.facturacion;

import facturatron.Dominio.Configuracion;
import facturatron.MVC.Controller;
import facturatron.Principal.VisorPdf;
import facturatron.config.ConfiguracionDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                factura.cancelar();
                getModel().load();
                JOptionPane.showMessageDialog(getView(), "Factura cancelada");
            } catch (SQLException ex) {
                Logger.getLogger(ListadoControl.class.getName()).log(Level.SEVERE, "Error al almacenar factura cancelada", ex);
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
            Logger.getLogger(ListadoControl.class.getName()).log(Level.SEVERE, "Error de lectura", ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ListadoControl.class.getName()).log(Level.SEVERE, "Error en hilo de ejecución", ex);
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
                btnCancelarFactura();
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
