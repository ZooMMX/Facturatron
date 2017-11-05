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

package com.phesus.facturatron.presentation.mvc.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.EventObject;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author octavioruizcastillo
 */
public abstract class CellEditorDotDotDot implements TableCellEditor {

    JButton moreButton;
    TableCellEditor originalEditor;
    private JTable table;
    private int row;
    private int column;
    private TableCellEditor editor;
    
    public CellEditorDotDotDot(JTable table) {
        super();
        moreButton = new JButton("...");
        moreButton.setMinimumSize(new Dimension(15,10));
        moreButton.setPreferredSize(new Dimension(15,10));
        moreButton.setMaximumSize(new Dimension(15,10));
        
        //  moreButton.setFocusable(false); 
        //moreButton.setFocusPainted(false); 
        
        moreButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                CellEditorDotDotDot.this.editor.cancelCellEditing(); 

                editCell(CellEditorDotDotDot.this.table, 
                        CellEditorDotDotDot.this.row, 
                        CellEditorDotDotDot.this.column);
            }
        });
        
        originalEditor = table.getDefaultEditor(String.class);
    }
    
    @Override
    public Component getTableCellEditorComponent(
                    JTable table, Object value, boolean isSelected,
                    int row, int column) {
        
        
        // This component should be a JLabel
        Component component = table.getDefaultEditor(String.class)
                        .getTableCellEditorComponent(table, value,
                                        isSelected, row, column);

        // Just check for sanity, this is overkill.
        if (!(component instanceof JTextField))
                throw new RuntimeException(
                                "Programmer error, wrong type");

        final JTextField textField = (JTextField) component;
        this.editor = (TableCellEditor) table.getDefaultEditor(String.class);

        // Build a little panel to hold the controls
        JPanel panel = new JPanel();
        panel.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e); //To change body of generated methods, choose Tools | Templates.
                textField.requestFocus();
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
        textField.setMinimumSize(new Dimension(150,10));
        panel.add(textField, BorderLayout.CENTER);

        
        this.table = table; 
        this.row = row; 
        this.column = column;                

        // The panel should be displayed
        return panel;
    }
    
    private JButton getMoreButton() {
        return moreButton;
    }
    
     protected abstract void editCell(JTable table, int row, int column);

    @Override
    public Object getCellEditorValue() {
            return originalEditor.getCellEditorValue();
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
            return originalEditor.isCellEditable(anEvent);
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
            return originalEditor.shouldSelectCell(anEvent);
    }

    @Override
    public boolean stopCellEditing() {
            return originalEditor.stopCellEditing();
    }

    @Override
    public void cancelCellEditing() {
            originalEditor.cancelCellEditing();
    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {
            originalEditor.addCellEditorListener(l);
    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {
            originalEditor.removeCellEditorListener(l);
    }
    
}
