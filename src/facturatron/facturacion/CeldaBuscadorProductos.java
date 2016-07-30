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

package facturatron.facturacion;

import facturatron.facturacion.controles.BuscadorProductosController;
import facturatron.facturacion.controles.BuscadorProductosView;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableColumn;

/**
 *
 * @author octavioruizcastillo
 */
class CeldaBuscadorProductos {
    
    FacturaControl fc;
        
    public CeldaBuscadorProductos(FacturaControl fc) {
        this.fc = fc;
    }

    public void install() {

        TableColumn columnaCodigo = fc.getView().getTabConceptos().getColumnModel().getColumn(1);            

        ProductoCellEditor editor = new ProductoCellEditor(fc.getView().getTabConceptos());
        columnaCodigo.setCellEditor(editor);   
        columnaCodigo.setCellRenderer(new CellRendererDotDotDot());

   }
    
    class ProductoCellEditor extends CellEditorDotDotDot {

        public ProductoCellEditor(JTable table) {
            super(table);
        }

        @Override
        protected void editCell(JTable table, int row, int column) {
            String oldValor = (String) table.getValueAt(row, column);            
            String newValor = showTextEditDialog("Descripción", table, row, 300, 150, oldValor);
            
            if(newValor != null)
                table.setValueAt(newValor, row, column);
        }
        
        private String showTextEditDialog(final String dialogTitle,
			JTable table, final int row, final int width, final int height, final String textToEdit) {

            // This panel holds the only edit control
            BuscadorProductosController controller = new BuscadorProductosController();
            BuscadorProductosView panel = controller.getView();

            // Use JOptionPane for a pane-less (ha!) way to create a
            // dialog in just a few lines. Also get system L&F.
            JOptionPane optPane = new JOptionPane();
            optPane.setMessage(panel);
            optPane.setMessageType(JOptionPane.PLAIN_MESSAGE);
            optPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
            JDialog dialog = optPane.createDialog(
                            fc.getView(), dialogTitle);
            // This resizable setting is critical;
            // by default the dialog is quite small
            dialog.setResizable(true);

            // Show it already!
            dialog.setVisible(true);

            // Get the value and decide if it was "OK"
            Object selectedValue = optPane.getValue();
            int n = -1;
            if (selectedValue != null && selectedValue instanceof Integer)
                    n = ((Integer) selectedValue).intValue();
            String result = null;
            if (n == JOptionPane.OK_OPTION) {                    
                    result = controller.getCodigoSeleccionado();
                    table.setValueAt(controller.getDescripcionSeleccionado(), row, 2);
                    table.setValueAt(controller.getPrecioUnitario(), row, 4);
            }

            // Might be good text, might be null
            return result;
	}
        
    }
}
