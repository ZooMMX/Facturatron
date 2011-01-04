/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.cliente;


import facturatron.Dominio.Persona;
import java.util.*;
import javax.swing.table.*;


/**
 *
 * @author saul
 */
public class ClienteTableModel extends AbstractTableModel {
    String[] columnNames = {"ID",
                        "Nombre",
                        "RFC",
                        "Calle",
                        "Colonia",
                        "Ciudad"};
    Class[] columnClasses = {
        Integer.class,
        String.class,
        String.class,
        String.class,
        String.class,
        String.class
    };
    private ArrayList<Persona> data = new ArrayList<Persona>();
    public ClienteTableModel() {
        fill();
    }

    public void fill() {
        setData((new ClienteDao()).findAll());
        fireTableRowsUpdated(0, getRowCount()-1);
    }

    public void addRow() {
        getData().add(new Persona());
        fireTableRowsInserted(getData().size()-1, getData().size()-1);
        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int row, int col) {

        return false;
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
        if(row < 0) { row = 0; }
        Persona fila = getData().get(row);
        switch(col) {
            case 0:
                return fila.getId();
            case 1:
                return fila.getNombre();
            case 2:
                return fila.getRfc();
            case 3:
                return fila.getCalle();
            case 4:
                return fila.getColonia();
            case 5:
                return fila.getMunicipio();
        }
        return null;
    }

    @Override
    public Class getColumnClass(int c) {
        return columnClasses[c];
    }



    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */

    /**
     * @return the data
     */
    public ArrayList<Persona> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(ArrayList<Persona> data) {
        this.data = data;
    }

}
