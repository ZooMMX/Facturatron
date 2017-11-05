/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.phesus.facturatron.presentation.mvc.model;

import facturatron.Dominio.Factura;
import facturatron.Dominio.Factura.Estado;
import com.phesus.facturatron.persistence.dao.FacturaDao;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Octavio
 */
public class ListadoTableModel extends AbstractTableModel {

    String[] columnNames = {"Fecha",
                        "Folio",
                        "RFC",
                        "Receptor",
                        "Impuesto",
                        "Importe",
                        "Cancelada"};
    Class[] columnClasses = {
        String.class,
        String.class,
        String.class,
        String.class,
        Double.class,
        Double.class,
        Boolean.class
    };
    private ArrayList<FacturaDao> data;
    public ListadoTableModel() {
        data = new ArrayList<FacturaDao>();
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        
        return (col != 6);
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return getData().size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Factura fila = getData().get(row);

        switch(col) {
            case 0:
                return fila.getFecha().toString();
            case 1:
                return fila.getSerie() + " " + fila.getFolio();
            case 2:
                return fila.getReceptor().getRfc();
            case 3:
                return fila.getReceptor().getNombre();
            case 4:
                return fila.getIvaTrasladado();
            case 5:
                return fila.getTotal();
            case 6:
                return fila.getEstadoComprobante()==Estado.CANCELADO;
        }
        return null;
    }

    @Override
    public Class getColumnClass(int c) {
        return columnClasses[c];
    }


    /**
     * @return the data
     */
    public ArrayList<FacturaDao> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(ArrayList<FacturaDao> data) {
        this.data = data;
    }

    public void notifyDataChange() {

    }

}