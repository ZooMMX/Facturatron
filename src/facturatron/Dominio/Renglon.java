/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.Dominio;

import facturatron.MVC.Model;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import mx.bigdata.sat.cfd.schema.Comprobante.Conceptos.Concepto;
import mx.bigdata.sat.cfd.schema.ObjectFactory;
import phesus.facturatron.lib.entities.ConceptoTron;

/**
 *
 * @author saul
 */
public class Renglon extends Model implements Serializable {
    private Integer id;
    private String unidad = "";
    private String noIdentificacion = "";
    private Double importe = 0d;
    private Double cantidad = 0d;
    private String descripcion = "";
    private Double valorUniario = 0d;
    private Boolean tasa0 = true;

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
    public Double getCantidad() {
        return cantidad;
    }

    public Concepto toConcepto() {
        MathContext mc = MathContext.DECIMAL32;

        Concepto c1 = (new ObjectFactory()).createComprobanteConceptosConcepto();

        c1.setUnidad(getUnidad());
        c1.setNoIdentificacion(getNoIdentificacion());
        c1.setImporte(new BigDecimal(getImporte()).round(mc));
        c1.setCantidad(new BigDecimal(getCantidad()).round(mc));
        c1.setDescripcion(getDescripcion());
        c1.setValorUnitario(new BigDecimal(getValorUniario()).round(mc));
        return c1;
    }

    public ConceptoTron toConceptoTron() {
        Concepto c1 = toConcepto();
        ConceptoTron ct1 = new ConceptoTron();
        ct1.setCantidad(c1.getCantidad());
        ct1.setComplementoConcepto(c1.getComplementoConcepto());
        ct1.setCuentaPredial(c1.getCuentaPredial());
        ct1.setDescripcion(c1.getDescripcion());
        ct1.setImporte(c1.getImporte());
        ct1.setNoIdentificacion(c1.getNoIdentificacion());
        ct1.setUnidad(c1.getUnidad());
        ct1.setValorUnitario(c1.getValorUnitario());
        ct1.setEtiquetaImpuestos(getTasa0()?"":"im");

        return ct1;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the importe
     */
    public Double getImporte() {
        return importe;
    }

    /**
     * @param importe the importe to set
     */
    public void setImporte(Double importe) {
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
    public Double getValorUniario() {
        return valorUniario;
    }

    /**
     * @param valorUniario the valorUniario to set
     */
    public void setValorUniario(Double valorUniario) {
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
