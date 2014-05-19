/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.unidad;
import facturatron.MVC.Controller;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author @gnujach
 */
public class UnidadControl  extends Controller<UnidadDao, UnidadForm>{
    public UnidadControl() {
        setModel(new UnidadDao());
        setView(new UnidadForm());
        init();
    }
    public void updateListado() {
        ((UnidadTableModel)getView().getTablaUnidad().getModel()).fill();
        getView().getTablaUnidad().updateUI();        
    }
    
    public Boolean validarForm() {
        //Validar Catalogo de Unidades de Medida
        if (getView().getTxtNombre().getText().isEmpty()) {
            JOptionPane.showMessageDialog(getView(), "El campo nombre no puede estar vacío");
            return false;
        }
        return true;
    }

    @Override
    public void asignarEventos() {
        getView().getBtnGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnGuardar();
            }
        });
        getView().getTablaUnidad().getSelectionModel().addListSelectionListener(new ListSelectionListener (){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()) {
                    JTable m = getView().getTablaUnidad();
                    int idUnidad = (Integer) m.getValueAt(m.getSelectedRow(), 0);
                    unidadSeleccionado(idUnidad);
                }
            }
        });
        getView().getBtnNuevo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                btnNuevo();
            }
        });
        getView().getBtnEliminar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnEliminar();
            }
        });
        getView().getBtnBuscar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnBuscar();
            }
        });
        getView().getTxtBusqueda().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) btnBuscar();
            }
        });
        
    }

    public void btnNuevo() {
        getModel().clearDataModel();
        getView().getTxtNombre().requestFocus();
    }
    @Override
    public void enlazarModeloVista() {
        new Thread() {
            public void run() {
                notifyBusy();
                UnidadTableModel tabModel = new UnidadTableModel();
                getView().getTablaUnidad().setModel(tabModel);
                getModel().addObserver(getView());
                getView().setModelo(getModel());
                notifyIdle();
            }
        }.start();
    }
    public void btnBuscar() {
        String searchString = getView().getTxtBusqueda().getText();
        ((UnidadTableModel)getView().getTablaUnidad().getModel()).search(searchString);        
    }
    public void btnEliminar() {
        if (JOptionPane.showConfirmDialog(getView(),"¿Está seguro de eliminar esta unidad de medida?")==JOptionPane.YES_OPTION) {
            getModel().remove();
            updateListado();
            getView().getTablaUnidad().clearSelection();
        }
    }
    public void unidadSeleccionado(int id) {
        try {
            getModel().findBy(id);
        } catch (SQLException ex) {
            Logger.getLogger(UnidadControl.class.getName()).log(Level.SEVERE, "Error de BD obteniendo info de unidad", ex);
        }
    }
    public void btnGuardar() {
        getModel().setNombre((getView().getTxtNombre().getText()));
        try {
            if(!validarForm()) return;
            getModel().persist();
            updateListado();
            JOptionPane.showMessageDialog(getView(), "Unidad Almacenada");
        } catch (SQLException ex) {
            Logger.getLogger(UnidadControl.class.getName()).log(Level.SEVERE, "Error al guardar Unidad de Medida", ex);
        }
    }
    
    
}
