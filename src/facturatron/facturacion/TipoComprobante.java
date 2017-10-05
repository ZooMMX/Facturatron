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

import mx.bigdata.sat.cfdi.v33.schema.CTipoDeComprobante;

/**
 *
 * @author octavioruizcastillo
 */
public enum TipoComprobante {
    FACTURA("Factura", EfectoComprobante.INGRESO), 
    NOTA_DE_CREDITO("Nota de crédito", EfectoComprobante.EGRESO);
    
    private final EfectoComprobante efecto;
    private final String descripcion;
    
    TipoComprobante(String d, EfectoComprobante e) {
        efecto = e;
        descripcion = d;
    }      
    
    public CTipoDeComprobante getEfectoComprobante() throws Exception {
        switch(efecto) {
            case INGRESO:
                return CTipoDeComprobante.I;
            case EGRESO:
                return CTipoDeComprobante.E;
            case TRASLADO:
                return CTipoDeComprobante.T;
            default:
                throw new Exception("Tipo de comprobante inválido");
        }
    }
    
    @Override
    public String toString() {
        return descripcion;
    }
}
