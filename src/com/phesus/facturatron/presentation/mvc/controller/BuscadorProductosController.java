/*
 * Copyright (C) 2014 octavioruizcastillo
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package com.phesus.facturatron.presentation.mvc.controller;

import com.phesus.facturatron.presentation.mvc.view.BuscadorProductosView;
import facturatron.mvc.Controller;
import com.phesus.facturatron.persistence.dao.ProductoDao;
import com.phesus.facturatron.presentation.mvc.view.ProductoForm;
import com.phesus.facturatron.presentation.mvc.model.ProductoTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author octavioruizcastillo
 */
public class BuscadorProductosController extends Controller<ProductoDao, BuscadorProductosView>{
    private String codigoSeleccionado;
    private String descripcionSeleccionado;
    private BigDecimal precioUnitario;
    public BuscadorProductosController() {
        setModel(new ProductoDao());
        setView (new BuscadorProductosView());
        init();
    }
    
    public void updateListado() {
        ((ProductoTableModel)getView().getTablaProducto().getModel()).fill();
        getView().getTablaProducto().updateUI();
    }

    @Override
    public void asignarEventos() {
        
        getView().getTablaProducto().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    JTable m = getView().getTablaProducto();
                    String codigo = (String) m.getValueAt(m.getSelectedRow(),2);
                    String descripcion = (String) m.getValueAt(m.getSelectedRow(),1);
                    BigDecimal pu = (BigDecimal) m.getValueAt(m.getSelectedRow(),3);
                    productoSeleccionado(codigo, descripcion, pu);
                }
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
    
    public void productoSeleccionado(String codigo, String descripcion, BigDecimal pu) {
        this.codigoSeleccionado = codigo;
        this.descripcionSeleccionado = descripcion;
        this.precioUnitario = pu;
    }

    /**
     * @return the codigoSeleccionado
     */
    public String getCodigoSeleccionado() {
        return codigoSeleccionado;
    }

    /**
     * @return the descripcionSeleccionado
     */
    public String getDescripcionSeleccionado() {
        return descripcionSeleccionado;
    }

    /**
     * @return the precioUnitario
     */
    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }
}
