/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.lib.entities;

import java.util.ArrayList;
import java.util.List;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Conceptos;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Conceptos.Concepto;
import mx.bigdata.sat.cfdi.v32.schema.ObjectFactory;

/**
 *
 * @author Octavio
 */
public class ConceptosTron extends ArrayList<ConceptoTron> {

    public static ConceptosTron toConceptosTron(Conceptos conceptos) {
        ConceptosTron conceptosTron = new ConceptosTron();
        for (Concepto concepto : conceptos.getConcepto()) {
            ConceptoTron ct = new ConceptoTron();
            
            ct.setCantidad(concepto.getCantidad());
            ct.setComplementoConcepto(concepto.getComplementoConcepto());
            ct.setCuentaPredial(concepto.getCuentaPredial());
            ct.setDescripcion(concepto.getDescripcion());
            ct.setEtiquetaImpuestos("");
            ct.setImporte(concepto.getImporte());
            ct.setNoIdentificacion(concepto.getNoIdentificacion());
            ct.setUnidad(concepto.getUnidad());
            ct.setValorUnitario(concepto.getValorUnitario());
            
            conceptosTron.add(ct);
        }
        return conceptosTron;
    }

    public Conceptos toConceptos() {
        Conceptos cps = (new ObjectFactory()).createComprobanteConceptos();
        List<Concepto> list = cps.getConcepto();
        for (ConceptoTron conc : this) {
            list.add(conc);
        }
        return cps;
    }

}
