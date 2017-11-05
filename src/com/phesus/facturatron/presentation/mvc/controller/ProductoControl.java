/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.phesus.facturatron.presentation.mvc.controller;
import com.phesus.facturatron.persistence.dao.ProductoDao;
import com.phesus.facturatron.presentation.mvc.view.ProductoForm;
import facturatron.mvc.Controller;
import com.phesus.facturatron.presentation.mvc.model.ProductoTableModel;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.jdesktop.swingx.JXTable;
/**
 *
 * @author gnujach
 */
public class ProductoControl extends Controller<ProductoDao, ProductoForm>{
    public ProductoControl() {
        setModel(new ProductoDao());
        setView (new ProductoForm());
        init();
    }
    
    public void updateListado() {
        ((ProductoTableModel)getView().getTablaProducto().getModel()).fill();
        getView().getTablaProducto().updateUI();
    }
    public Boolean validaForm() {
        if (getView().getTxtNombre().getText().isEmpty()) {
            JOptionPane.showMessageDialog(getView(), "El campo nombre no puede estar vacío");
            return false;
        }
        if (getView().getTxtClave().getText().isEmpty()) {
            JOptionPane.showMessageDialog(getView(), "El campo clave no puede estar vacío");
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
        getView().getTablaProducto().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    JTable m = getView().getTablaProducto();
                    int idProducto = (Integer) m.getValueAt(m.getSelectedRow(),0);
                    productoSeleccionado(idProducto);
                }
            }
        });
        getView().getBtnNuevo().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
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

    @Override
    public void enlazarModeloVista() {
        new Thread() {
            @Override
            public void run() {
                notifyBusy();
                ProductoTableModel tabModel = new ProductoTableModel();
                getView().getTablaProducto().setModel(tabModel);
                /*
                getView().getTablaProducto().setAutoResizeMode(JXTable.AUTO_RESIZE_OFF);
                getView().getTablaProducto().getColumnModel().getColumn(0).setPreferredWidth(40);
                getView().getTablaProducto().getColumnModel().getColumn(1).setPreferredWidth(260);
                getView().getTablaProducto().getColumnModel().getColumn(2).setPreferredWidth(60);
                */
                getModel().addObserver((Observer) getView());
                getView().setModelo(getModel());
                notifyIdle();
            }        
    }.start();
    }
    public void btnBuscar() {        
        String searchString = getView().getTxtBusqueda().getText();        
        ((ProductoTableModel)getView().getTablaProducto().getModel()).search(searchString);
    }
    public void btnEliminar() {
        if (JOptionPane.showConfirmDialog(getView(),"¿Está seguro de eliminar este producto?")==JOptionPane.YES_OPTION) {
            getModel().remove();
            updateListado();
            getView().getTablaProducto().clearSelection();
        }
    }
    public void productoSeleccionado(int id) {
        try {
            getModel().findBy(id);
        } catch (SQLException ex ) {
            Logger.getLogger(ProductoControl.class.getName()).log(Level.SEVERE, "Error de BD obteniendo info de producto", ex);
        }
    }
    public void btnGuardar () {
        getModel().setNombre(getView().getTxtNombre().getText());
        getModel().setClave(getView().getTxtClave().getText());
        getModel().setPrecio(new BigDecimal(getView().getTxtPrecio().getText()));       
        getModel().setActivo(getView().getTxtActivo().isSelected());
        getModel().setNotas(getView().getTxtNotas().getText());
        try {
            if (validaForm()){
            getModel().persist();
            updateListado();
            JOptionPane.showMessageDialog(getView(), "Producto Almacenado");
            } else {
                return;
            }            
        } catch (SQLException ex){
             Logger.getLogger(ProductoControl.class.getName()).log(Level.SEVERE, "Error al guardar Producto", ex);
        } catch (Exception ex) {
            Logger.getLogger(ProductoControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void btnNuevo() {
        getModel().clearDataModel();        
        getView().getTxtNombre().requestFocus();
    }
   
}
