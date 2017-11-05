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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author octavioruizcastillo
 */
public class CellRendererDotDotDot extends DefaultTableCellRenderer {

    JButton moreButton;
    
    public CellRendererDotDotDot() {
        super();
        moreButton = new JButton("...");
        moreButton.setMinimumSize(new Dimension(15,10));
        moreButton.setPreferredSize(new Dimension(15,10));
        moreButton.setMaximumSize(new Dimension(15,10));
                
    }
    
    @Override
    public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
        
        
        // This component should be a JLabel
        Component component = table.getDefaultRenderer(String.class)
                        .getTableCellRendererComponent(table, value,
                                        isSelected, hasFocus, row, column);

        // Just check for sanity, this is overkill.
        if (!(component instanceof JLabel))
                throw new RuntimeException(
                                "Programmer error, wrong type");

        // The component is a label
        JLabel label = (JLabel) component;

        // This label must show no icon.
        label.setIcon(null);

        // Build a little panel to hold the controls
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

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
        label.setMinimumSize(new Dimension(150,10));
        panel.add(label, BorderLayout.CENTER);

        // The panel should be displayed
        return panel;
    }
    
    public JButton getMoreButton() {
        return moreButton;
    }
    
}
