package facturatron.facturacion;


import facturatron.Dominio.Medida;
import facturatron.facturacion.FacturaControl;
import facturatron.lib.Java2sAutoComboBox;
import facturatron.unidad.UnidadDao;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

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

/**
 *
 * @author octavioruizcastillo
 */
public class CeldaDescripcion {
        
        FacturaControl fc;
        
        public CeldaDescripcion(FacturaControl fc) {
            this.fc = fc;
        }
        
        public void install() {
            
            TableColumn columnaDescripcion = fc.getView().getTabConceptos().getColumnModel().getColumn(2);            

            //CellRendererDotDotDot prueba = new CellRendererDotDotDot();
            //columnaDescripcion.setCellRenderer(prueba);
            CellEditorDotDotDot editor = new CeldaDescripcionEditor(fc.getView().getTabConceptos());
            columnaDescripcion.setCellEditor(editor);            
            
       }
            
    private class CeldaDescripcionEditor extends CellEditorDotDotDot {

        public CeldaDescripcionEditor(JTable table) {
            super(table);
        }
        @Override
        protected void editCell(JTable table, int row, int column) {
            String oldValor = (String) table.getValueAt(row, column);
            String newValor = showTextEditDialog("Descripción", 300, 150, oldValor);
            table.setValueAt(newValor, row, column);
        }
        
        private String showTextEditDialog(final String dialogTitle,
			final int width, final int height, final String textToEdit) {

            // This panel holds the only edit control
            JPanel panel = new JPanel();
            BorderLayout layout = new BorderLayout();
            panel.setLayout(layout);
            JTextArea textArea = new JTextArea();
            textArea.setText(textToEdit);
            // Use a scroll pane so the text area can be scrolled
            JScrollPane jsp = new JScrollPane(textArea) {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public Dimension getPreferredSize() {
                            return new Dimension(width, height);
                    }
            };
            panel.add(jsp, BorderLayout.CENTER);

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
            if (n == JOptionPane.OK_OPTION)
                    result = textArea.getText();

            // Might be good text, might be null
            return result;
	}
    }
}
