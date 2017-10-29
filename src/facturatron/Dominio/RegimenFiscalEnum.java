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
public enum RegimenFiscalEnum {
    r601("601","General de Ley Personas Morales"),
    r603("603","Personas Morales con Fines no Lucrativos"),
    r605("605","Sueldos y Salarios e Ingresos Asimilados a Salarios"),
    r606("606","Arrendamiento"),
    r607("620","Régimen de Enajenación o Adquisición de Bienes"),
    r608("608","Demás ingresos"),
    r609("609","Consolidación"),
    r610("610","Residentes en el Extranjero sin Establecimiento Permanente en México"),
    r611("611","Ingresos por Dividendos (socios y accionistas)"),
    r612("612","Personas Físicas con Actividades Empresariales y Profesionales"),
    r614("614","Ingresos por intereses"),
    r615("615","Régimen de los ingresos por obtención de premios"),
    r616("616","Sin obligaciones fiscales"),
    r620("620","Sociedades Cooperativas de Producción que optan por diferir sus ingresos"),
    r621("621","Incorporación Fiscal"),
    r622("622","Actividades Agrícolas, Ganaderas, Silvícolas y Pesqueras"),
    r623("623","Opcional para Grupos de Sociedades"),
    r624("624","Coordinados"),
    r628("628","Hidrocarburos"),
    r629("629","De los Regímenes Fiscales Preferentes y de las Empresas Multinacionales"),
    r630("630","Enajenación de acciones en bolsa de valores");
    
    
    String descripcion;
    String codeSat;

    RegimenFiscalEnum(String codeSat, String descripcion) {
        this.descripcion = descripcion;
        this.codeSat = codeSat;
    }
   
    public String toString() {
        return descripcion;
    }
    
    public static String getHumanText(int codeSatNumber){
        return getHumanText(""+codeSatNumber);
    }
    
    public static String getHumanText(String codeSatring){
        for (RegimenFiscalEnum b : RegimenFiscalEnum.values()) {
          if (b.codeSat.equalsIgnoreCase(codeSatring)) {
            return b.descripcion;
          }
        }
        return "Código inexistente";
    }
    
    public CMetodoPago getSatConstant() {
        return CMetodoPago.valueOf(name());
    }   
}