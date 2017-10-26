/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturatron.Dominio;

import mx.bigdata.sat.cfdi.v33.schema.CMetodoPago;

/**
 *
 * @author octavioruizcastillo
 */
public enum MetodoDePagoEnum {
    PUE("Pago en una sola exhibición"),
    PPD("Pago en parcialidades o diferido");
    
    String descripcion;

    MetodoDePagoEnum(String descripcion) {
        this.descripcion = descripcion;
    }
   
    public String toString() {
        return descripcion;
    }
    
    public CMetodoPago getSatConstant() {
        return CMetodoPago.valueOf(name());
    }
    
}
