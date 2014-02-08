/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.lib.entities;

import java.math.BigDecimal;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Conceptos.Concepto;



/**
 *
 * @author Octavio
 */
public class ConceptoTron extends Concepto {
    private String etiquetaImpuestos;
    private BigDecimal ieps;


    /**
     * @return the etiquetaImpuestos
     */
    public String getEtiquetaImpuestos() {
        return etiquetaImpuestos;
    }

    /**
     * @param etiquetaImpuestos the etiquetaImpuestos to set
     */
    public void setEtiquetaImpuestos(String etiquetaImpuestos) {
        this.etiquetaImpuestos = etiquetaImpuestos;
    }

    public void setIEPS(BigDecimal ieps) {
        this.ieps = ieps;
    }
    
    public BigDecimal getIEPS() {
        return ieps;
    }
}
