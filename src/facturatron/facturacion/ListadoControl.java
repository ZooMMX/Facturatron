/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.facturacion;

import facturatron.MVC.Controller;
import facturatron.Principal.VisorPdf;
import facturatron.facturacion.FacturaDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author saul, ORC
 */
public class ListadoControl extends Controller<ListadoModel, ListadoForm> {

    public ListadoControl(){
        setView(new ListadoForm());
        setModel(new ListadoModel());
        init();
    }

    @Override
    public void init() {
        super.init();
        getModel().load();
    }

    public void btnVerFactura() {
        try {
            JFrame jf = new JFrame();
            FacturaControl fc = new FacturaControl();
            int row = getView().getTablaListado().getSelectedRow();
            int id = getModel().getFacturas().get(row).getId();
            FacturaDao factura = (FacturaDao) getModel().getDao().findBy(id);
            VisorPdf.abrir(factura.getPdfPath());
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
        getView().getBtnVerFactura().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {   //se crea una nueva ventana con los datos de la factura seleccionada
                btnVerFactura();
            }
        });
        getView().getDesde().addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                getModel().setFechaInicial(new java.sql.Date(getView().getDesde().getDate().getTime()));
            }
        });
        getView().getHasta().addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                getModel().setFechaFinal(new java.sql.Date(getView().getHasta().getDate().getTime()));
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
