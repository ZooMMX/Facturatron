/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.Dominio;

import facturatron.MVC.Model;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import mx.bigdata.sat.cfd.v22.schema.Comprobante.Conceptos.Concepto;
import mx.bigdata.sat.cfd.v22.schema.ObjectFactory;
import phesus.facturatron.lib.entities.ConceptoTron;

/**
 *
 * @author saul
 */
public class Renglon extends Model implements Serializable {
    private Integer id;
    private String unidad = "";
    private String noIdentificacion = "";
    private BigDecimal importe  = new BigDecimal(0d);
    private BigDecimal cantidad = new BigDecimal(0d);
    private String descripcion = "";
    private BigDecimal valorUniario = new BigDecimal(0d);
    private Boolean tasa0 = true;

    public Renglon() {
        importe.setScale(2);
        cantidad.setScale(2);
        valorUniario.setScale(2);
    }

    public static List createBeanCollection(){

        ArrayList<Renglon> rb = new ArrayList<Renglon>();
        /*
        Renglon renglon = new Renglon();

        renglon.setCantidad(15d);
        renglon.setDescripcion("compu accer");
        renglon.setImporte(30d);
        renglon.setPu(2d);
        rb.add(renglon);
        renglon = new Renglon();
        renglon.setCantidad(4d);
        renglon.setDescripcion("cable usb");
        renglon.setPu(3d);
        renglon.setImporte(12d);
        rb.add(renglon);

        */
        return rb;
    }
    /**
     * @return the cantidad
     */
    public BigDecimal getCantidad() {
        return cantidad;
    }

    public Concepto toConcepto() {
        //MathContext mc = MathContext.DECIMAL32;

        Concepto c1 = (new ObjectFactory()).createComprobanteConceptosConcepto();

        c1.setUnidad(getUnidad());
        c1.setNoIdentificacion(getNoIdentificacion());
        //c1.setImporte(new BigDecimal(getImporte()).round(mc));
        c1.setImporte(getImporte());
        //c1.setCantidad(new BigDecimal(getCantidad()).round(mc));
        c1.setCantidad(getCantidad());
        c1.setDescripcion(getDescripcion());
        //c1.setValorUnitario(new BigDecimal(getValorUniario()).round(mc));
        c1.setValorUnitario(getValorUniario());
        return c1;
    }

    public ConceptoTron toConceptoTron() {
        Concepto c1 = toConcepto();
        ConceptoTron ct1 = new ConceptoTron();
        ct1.setCantidad(c1.getCantidad().setScale(2,RoundingMode.HALF_EVEN));
        ct1.setComplementoConcepto(c1.getComplementoConcepto());
        ct1.setCuentaPredial(c1.getCuentaPredial());
        ct1.setDescripcion(c1.getDescripcion());
        ct1.setImporte(c1.getImporte().setScale(2,RoundingMode.HALF_EVEN));
        ct1.setNoIdentificacion(c1.getNoIdentificacion());
        ct1.setUnidad(c1.getUnidad());
        ct1.setValorUnitario(c1.getValorUnitario().setScale(2,RoundingMode.HALF_EVEN));
        ct1.setEtiquetaImpuestos(getTasa0()?"":"im");

        return ct1;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the importe
     */
    public BigDecimal getImporte() {
        return importe;
    }

    /**
     * @param importe the importe to set
     */
    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the unidad
     */
    public String getUnidad() {
        return unidad;
    }

    /**
     * @param unidad the unidad to set
     */
    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    /**
     * @return the noIdentificacion
     */
    public String getNoIdentificacion() {
        return noIdentificacion;
    }

    /**
     * @param noIdentificacion the noIdentificacion to set
     */
    public void setNoIdentificacion(String noIdentificacion) {
        this.noIdentificacion = noIdentificacion;
    }

    /**
     * @return the valorUniario
     */
    public BigDecimal getValorUniario() {
        return valorUniario;
    }

    /**
     * @param valorUniario the valorUniario to set
     */
    public void setValorUniario(BigDecimal valorUniario) {
        this.valorUniario = valorUniario;
    }

    /**
     * @return the tasa0
     */
    public Boolean getTasa0() {
        return tasa0;
    }

    /**
     * @param tasa0 the tasa0 to set
     */
    public void setTasa0(Boolean tasa0) {
        this.tasa0 = tasa0;
    }
}
