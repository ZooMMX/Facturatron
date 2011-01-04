/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.facturacion;

import facturatron.Dominio.Renglon;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Octavio
 */
public class FacturaTableModel extends AbstractTableModel {
    String[] columnNames = {"Cantidad",
                        "Código",
                        "Descripción",
                        "Unidad",
                        "PU",
                        "Tasa 0%",
                        "Importe"};
    Class[] columnClasses = {
        Double.class,
        String.class,
        String.class,
        String.class,
        Double.class,
        Boolean.class,
        Double.class
    };
    private ArrayList<Renglon> data = new ArrayList<Renglon>();
    public FacturaTableModel() {
    }

    public void addRow() {
        getData().add(new Renglon());
        fireTableRowsInserted(getData().size()-1, getData().size()-1);
        fireTableDataChanged();
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
        Renglon fila = getData().get(row);
        switch(col) {
            case 0:
                return fila.getCantidad();
            case 1:
                return fila.getNoIdentificacion();
            case 2:
                return fila.getDescripcion();
            case 3:
                return fila.getUnidad();
            case 4:
                return fila.getValorUniario();
            case 5:
                return fila.getTasa0();
            case 6:
                return fila.getImporte();
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
    @Override
    public void setValueAt(Object value, int row, int col) {
        Renglon fila = getData().get(row);
        switch(col) {
            case 0:
                fila.setCantidad((Double) value);
                updateImporte(fila);
                break;
            case 1:
                fila.setNoIdentificacion((String) value);
                break;
            case 2:
                fila.setDescripcion((String) value);
                break;
            case 3:
                fila.setUnidad((String) value);
                break;
            case 4:
                fila.setValorUniario((Double) value);
                updateImporte(fila);
                break;
            case 5:
                fila.setTasa0((Boolean) value);
                break;
        }
        if(row == getRowCount()-1 && col == getColumnCount()-3) {
            addRow();
        }
        fireTableCellUpdated(row, col);
    }
    public void updateImporte(Renglon renglon) {
        Double cantidad = renglon.getCantidad();
        Double valor    = renglon.getValorUniario();

        renglon.setImporte(cantidad * valor);
    }

    /**
     * @return the data
     */
    public ArrayList<Renglon> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(ArrayList<Renglon> data) {
        this.data = data;
    }

}