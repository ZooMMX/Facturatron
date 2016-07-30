/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.producto;
import facturatron.Dominio.Producto;
import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.*;

/**
 *
 * @author gnujach
 */
public class ProductoTableModel extends AbstractTableModel {
    String[] columnNames = {
        "ID",
        "Nombre",
        "Clave",
        "Precio",
        "Activo",
        "Notas"
    };
    Class[] columnClasses = {
        Integer.class,
        String.class,
        String.class,
        BigDecimal.class,
        Boolean.class,
        String.class
    };
    private ArrayList<Producto> data = new ArrayList<Producto>();
    public ProductoTableModel() {
        fill();
    }
    public void search(String searchString) {
        setData ((new ProductoDao().find(searchString)));
        try {
            fireTableDataChanged();
        } catch (Exception ex){
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, "Error al consultar producto", ex);
        }
    }
    public void addRow() {
       getData().add(new Producto());
       fireTableRowsInserted(getData().size()-1, getData().size()-1);
       fireTableDataChanged();
   }
    public void fill() {
       setData ((new ProductoDao()).findAll());       
       fireTableRowsUpdated(0, getRowCount() - 1);
   }
    public ArrayList<Producto> getData() {
        return  data;
    }
    public void setData (ArrayList<Producto> data) {
        this.data = data;
    }
    @Override
    public String getColumnName (int col) {
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
    public Class getColumnClass(int c) {
        return columnClasses[c];
    }
    @Override
    public Object getValueAt(int row, int col) {
        if (row < 0) { row = 0; }
        Producto fila = getData().get(row);
        switch (col) {
            case 0:
                return fila.getId();
            case 1:
                return fila.getNombre();
            case 2:
                return fila.getClave();
            case 3:
                return fila.getPrecio();
            case 4:
                return fila.getActivo();
            case 5:
                return fila.getNotas();
        }
        return null;
    }
}