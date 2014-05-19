/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.unidad;
import facturatron.Dominio.Medida;
import java.util.*;
import javax.swing.table.*;

/**
 *
 * @author gnujach
 */
public class UnidadTableModel extends AbstractTableModel{
    String[] columnNames = { "ID",
                        "Nombre"
    };
    Class[] columnClasses = {
        Integer.class,
        String.class
    };    
   private ArrayList<Medida> data = new ArrayList<Medida>();
   public UnidadTableModel() {
       fill();
   }
   public void search(String searchString) {
       setData ((new UnidadDao().find(searchString)));
       fireTableRowsUpdated(0, getRowCount()-1);
               
   }
   public void addRow() {
       getData().add(new Medida());
       fireTableRowsInserted(getData().size()-1, getData().size()-1);
       fireTableDataChanged();
   }
   public void fill() {
       setData ((new UnidadDao()).findAll());
       fireTableRowsUpdated(0, getRowCount()-1);
   }
   public ArrayList<Medida> getData() {
       return data;
   }
   public void setData(ArrayList<Medida> data) {
       this.data = data;
   }
   @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    @Override
    public int getRowCount() {
        return getData().size();
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
    public Object getValueAt(int row, int col) {
        if (row < 0) { row = 0; }
        Medida fila = getData().get(row);
        switch (col) {
            case 0:
                return fila.getId();
            case 1:
                return fila.getNombre();
        }
        return null;
    }
    @Override
    public Class getColumnClass(int c) {
        return columnClasses[c];
    }
}