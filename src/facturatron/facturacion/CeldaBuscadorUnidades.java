package facturatron.facturacion;


import facturatron.Dominio.Medida;
import facturatron.facturacion.FacturaControl;
import facturatron.lib.Java2sAutoComboBox;
import facturatron.unidad.UnidadDao;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
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
class CeldaBuscadorUnidades {
        
        FacturaControl fc;
        
        public CeldaBuscadorUnidades(FacturaControl fc) {
            this.fc = fc;
        }
        
        public void install() {
            UnidadDao unidadDao = new UnidadDao();
            ArrayList<Medida> unidades =  unidadDao.findAll();

            TableColumn columnaUnidad = fc.getView().getTabConceptos().getColumnModel().getColumn(3);
            ArrayList<String> medidas = new ArrayList<String>();
            for (Medida unidad : unidades) {
                medidas.add(unidad.getNombre().toString());
            }
            Java2sAutoComboBox comboBox = new Java2sAutoComboBox(medidas);
            comboBox.setDataList(medidas);
            comboBox.setMaximumRowCount(5);

            columnaUnidad.setCellRenderer(new CellRendererDotDotDot());
            columnaUnidad.setCellEditor(new DefaultCellEditor(comboBox));

        }
    
    }
