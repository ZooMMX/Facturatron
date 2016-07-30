package facturatron.facturacion;


import facturatron.Dominio.Medida;
import facturatron.facturacion.FacturaControl;
import facturatron.lib.Java2sAutoComboBox;
import facturatron.unidad.UnidadDao;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
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

            CeldaDescripcionEditor editor = new CeldaDescripcionEditor(fc.getView().getTabConceptos());
            columnaDescripcion.setCellEditor(editor);            
            
       }
        
     class CeldaDescripcionEditor extends AbstractCellEditor implements TableCellEditor {

        JButton moreButton;
        private JTable table;
        private int row;
        private int column;
        JLabel label;
        
         public CeldaDescripcionEditor(JTable table) {
            super();
            moreButton = new JButton("...");
            moreButton.setMinimumSize(new Dimension(15,10));
            moreButton.setPreferredSize(new Dimension(15,10));
            moreButton.setMaximumSize(new Dimension(15,10));

            moreButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    editCell(CeldaDescripcionEditor.this.table, 
                            CeldaDescripcionEditor.this.row, 
                            CeldaDescripcionEditor.this.column);
                }
            });

            this.table = table;
            label = new JLabel();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
        int rowIndex, int vColIndex) {
                       
            this.table = table; 
            this.row = rowIndex; 
            this.column = vColIndex;

            // Build a little panel to hold the controls
            JPanel panel = new JPanel();
            panel.addFocusListener(new FocusAdapter() {

                @Override
                public void focusGained(FocusEvent e) {
                    super.focusGained(e); //To change body of generated methods, choose Tools | Templates.
                    label.requestFocus();
                }

            });
            panel.setLayout(new BorderLayout());
            //panel.setFocusable(true);

            // Color appropriately for selection status
            if (isSelected) {
                    panel.setForeground(table.getSelectionForeground());
                    panel.setBackground(table.getSelectionBackground());
            } else {
                    panel.setForeground(table.getForeground());
                    panel.setBackground(table.getBackground());
            }

            panel.add(moreButton, BorderLayout.EAST);

            // Add the original JLabel renderer
            label.setText((String) getCellEditorValue());
            label.setMinimumSize(new Dimension(150,10));
            panel.add(label, BorderLayout.CENTER);                

            // The panel should be displayed
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            String valor = (String) table.getValueAt(row, column);
            return valor;
        }
        
        protected void editCell(JTable table, int row, int column) {
            
            String oldValor = (String) table.getValueAt(row, column);            
            String newValor = showTextEditDialog("Descripción", 300, 150, oldValor);
            
            if(newValor != null)
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
