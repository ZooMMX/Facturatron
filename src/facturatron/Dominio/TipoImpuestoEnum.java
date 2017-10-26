/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturatron.Dominio;

import mx.bigdata.sat.cfdi.v33.schema.CMetodoPago;
import mx.bigdata.sat.cfdi.v33.schema.CTipoFactor;

/**
 *
 * @author octavioruizcastillo
 */
public enum TipoImpuestoEnum {
    ISR("001"),
    IVA("002"),
    IEPS("003");
    
    String codigoSat;

    TipoImpuestoEnum(String codigoSat) {
        this.codigoSat = codigoSat;
    }
   
    public String toString() {
        return name();
    }
    
    public String getSatConstant() {
        return codigoSat;
    }
    
    public static TipoImpuestoEnum fromSatConstant(String text) {
        for (TipoImpuestoEnum b : TipoImpuestoEnum.values()) {
          if (b.codigoSat.equalsIgnoreCase(text)) {
            return b;
          }
        }
        return null;
    }
}
