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
    private BigDecimal importeIeps;
    private BigDecimal importeIva;
    private BigDecimal subtotal;
    public Boolean exento;
    
    public RenglonTicket() {
        ieps            = new BigDecimal("0.00");
        importeIva      = new BigDecimal("0.00");
        importeIeps     = new BigDecimal("0.00");
        subtotal        = new BigDecimal("0.00");
        descuento       = new BigDecimal("0.00");
        ieps            .setScale(2, RoundingMode.HALF_EVEN);
        importeIva      .setScale(2, RoundingMode.HALF_EVEN);
        importeIeps     .setScale(2, RoundingMode.HALF_EVEN);
        subtotal        .setScale(2, RoundingMode.HALF_EVEN);   
        descuento       .setScale(2, RoundingMode.HALF_EVEN);   ;
    }
    
    public BigDecimal getImporteIva(){
        if(importeIva.compareTo(BigDecimal.ZERO)==0){
            importeIva=new BigDecimal("0.00");
            if(impuestos)
            importeIva=(getSubtotal().multiply(new BigDecimal("0.16")));
        }
        
        return importeIva;
    }
    
    //TODO subtotal
    
    public void setImporteIva(BigDecimal importe){
        importeIva=importe;
    }
    
    public void setSubtotal(BigDecimal subtotal){
        this.subtotal=subtotal;
    }
    
    public BigDecimal getSubtotal(){
       if(subtotal.compareTo(BigDecimal.ZERO)==0)
           subtotal=cantidad.multiply(precioUnitario).subtract(descuento);
       return subtotal;
    }
    
    public void setImporteIeps(BigDecimal importe){
        importeIeps=importe;
    }
    
    public BigDecimal getImporteIeps(){
        if(importeIeps.compareTo(BigDecimal.ZERO)==0)
            importeIeps=(getSubtotal()).multiply(ieps);
        return importeIeps;
    }
    
    public String toString() {
        String impuestosString="0%";
        if(impuestos)
            impuestosString="16%";
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
                .append("impuestos IVA: ")      .append(impuestosString)               .append(", ")
                .append("importe IVA: ")        .append(importeIva)                    .append(", ")
                .append("importe IEPS: ")       .append(importeIeps)                   .append(", ")
                .append("tasa IEPS: ")          .append(ieps.toString())               .append(", ")
                .append("descuento: ")          .append(descuento.toPlainString())     .append(", ")
                .append("importe: ")            .append(importe.toPlainString())       .append(", ")
                .append("subtotal: ")           .append(subtotal.toString())           
                .append("]");
        
        return builder.toString();
    }
}
