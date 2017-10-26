/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.datasource;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author octavioruizcastillo
 */
public class RenglonTicket {
    public BigDecimal cantidad;
    public String     codigo;
    public String     claveProductoSAT;
    public BigDecimal precioUnitario;
    public String     descripcion;
    public String     unidad;
    public String     claveUnidadSAT;
    public Boolean    impuestos;
    public BigDecimal ieps;
    public BigDecimal descuento;
    public BigDecimal importe;
    
    public RenglonTicket() {
        ieps = new BigDecimal("0.00");
        ieps     .setScale(2, RoundingMode.HALF_EVEN);
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
                .append("[")
                .append("cantidad: ")           .append(cantidad.toPlainString())      .append(", ")
                .append("codigo: ")             .append(codigo)                        .append(", ")
                .append("clave producto SAT: ") .append(claveProductoSAT)              .append(", ")
                .append("precioUnitario: ")     .append(precioUnitario.toPlainString()).append(", ")
                .append("descripcion: ")        .append(descripcion)                   .append(", ")
                .append("unidad: ")             .append(unidad)                        .append(", ")
                .append("clave unidad SAT: ")   .append(claveUnidadSAT)                .append(", ")
                .append("impuestos IVA: ")      .append(impuestos.toString())          .append(", ")
                .append("impuestos IEPS: ")     .append(ieps.toString())               .append(", ")
                .append("descuento: ")          .append(descuento.toPlainString())     .append(", ")
                .append("importe: ")            .append(importe.toPlainString())      
                .append("]");
        
        return builder.toString();
    }
}
