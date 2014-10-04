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

import facturatron.Dominio.Producto;
import facturatron.lib.Java2sAutoComboBox;
import facturatron.producto.ProductoDao;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.DefaultCellEditor;
import javax.swing.table.DefaultTableCellRenderer;
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
            ProductoDao productoDao = new ProductoDao();
            ArrayList<Producto> productos =  productoDao.findAll();
            final HashMap<String, Producto> pMap = new HashMap<String, Producto>();

            TableColumn columnaCodigo = fc.getView().getTabConceptos().getColumnModel().getColumn(1);

            for (Producto p : productos) {
                pMap.put(p.getClave(), p);
            }

            final Java2sAutoComboBox comboBox = new Java2sAutoComboBox(productos);
            comboBox.setDataList(productos);
            comboBox.setMaximumRowCount(5);
            comboBox.setStrict(false);

            columnaCodigo.setCellEditor(new DefaultCellEditor(comboBox));

            DefaultTableCellRenderer renderer = new CellRendererDotDotDot();
            columnaCodigo.setCellRenderer(renderer);

            comboBox.addItemListener(new ItemListener() {

                @Override
                public void itemStateChanged(ItemEvent e) {
                    if(e.getStateChange() == ItemEvent.SELECTED) {
                        int fila = fc.getView().getTabConceptos().getSelectedRow();
                        String clave = (String) e.getItem();
                        //Por defecto busca el producto por clave en un hashmap
                        Producto p   = pMap.get(clave);
                        FacturaTableModel tableModel = (FacturaTableModel) fc.getView().getTabConceptos().getModel();

                        //Si no hay celda seleccionada ignoramos silenciosamente la acción
                        if(fila == -1) return;
                        if(p == null) { 
                            //Si no hay productos vacía las celdas correspondientes
                            tableModel.setValueAt("", fila, 2);
                            tableModel.setValueAt(new BigDecimal(0), fila, 4);
                        } else {
                            //Rellena las celdas con los datos del producto
                            tableModel.setValueAt(p.getNombre(), fila, 2);
                            tableModel.setValueAt(p.getPrecio(), fila, 4);
                        }
                    }
                }
            });
        }
    }
