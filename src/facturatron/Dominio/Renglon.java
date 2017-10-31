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
import mx.bigdata.sat.cfdi.v33.schema.Comprobante.Conceptos.Concepto;
import mx.bigdata.sat.cfdi.v33.schema.ObjectFactory;
import facturatron.lib.entities.ConceptoTron;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;
import mx.bigdata.sat.cfdi.v33.schema.CTipoFactor;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante;
import java.text.NumberFormat;

/**
 *
 * @author saul
 */
public class Renglon extends Model implements Serializable {
    private Integer id;
    private String unidad = "";
    private String claveUnidadSat = "";
    private String noIdentificacion = "";
    private String claveProductoSat = "";
    private BigDecimal importe  = new BigDecimal(0d).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    private BigDecimal subtotal  = new BigDecimal(0d).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    private BigDecimal cantidad = new BigDecimal(0d).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    private String descripcion = "";
    private BigDecimal valorUniario = new BigDecimal(0d);
    private Boolean tasa0 = true;
    private BigDecimal tasaIEPS = new BigDecimal(0d);
    private BigDecimal ieps     = new BigDecimal(0d);
    private BigDecimal descuento = new BigDecimal(0d).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    private Boolean iepsUpdated=false;

    public Renglon() {
        importe.setScale(2);
        cantidad.setScale(2);
        valorUniario.setScale(2);
        tasaIEPS.setScale(2);
        ieps.setScale(2);
        subtotal.setScale(2);
        descuento.setScale(2);
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
        c1.setClaveUnidad( getUnidad() );
        c1.setClaveProdServ( getNoIdentificacion() );
        c1.setImpuestos(getImpuestos());
        c1.setClaveProdServ(getClaveProductoSat());
        c1.setClaveUnidad(getClaveUnidadSat());
        c1.setDescuento(getDescuento());
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
        //La etiqueta de impuestos está compuesta por %_IVA + "/" + %_IEPS
        String etiquetaIVA = getTasa0()?"0":"16";
        
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMinimumFractionDigits(0);
        nf.setMaximumFractionDigits(0);
        String etiquetaIEPS= getTasaIEPS().equals(BigDecimal.ZERO) ? "-" : nf.format((getTasaIEPS().multiply(BigDecimal.valueOf(100)))).toString();
        ct1.setEtiquetaImpuestos(etiquetaIVA+"/"+etiquetaIEPS);
        ct1.setIEPS(getIEPS());
        ct1.setClaveProdServ(c1.getClaveProdServ());
        ct1.setClaveUnidad(c1.getClaveUnidad());
        ct1.setImpuestos(c1.getImpuestos());
        ct1.setDescuento(c1.getDescuento());

        return ct1;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad.setScale(2, BigDecimal.ROUND_HALF_EVEN);
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
        updateIEPS();
        
        this.importe = importe.setScale(2, RoundingMode.HALF_EVEN);
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
        this.valorUniario = valorUniario.setScale(2, BigDecimal.ROUND_HALF_EVEN);
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

    public BigDecimal getTasaIEPS() {
        return tasaIEPS.setScale(6, BigDecimal.ROUND_HALF_EVEN);
    }

    public void setTasaIEPS(BigDecimal tasa) {
        if(!iepsUpdated){
            MathContext mc = MathContext.DECIMAL64;
            tasaIEPS = tasa.divide(new BigDecimal("100.00", mc),mc).setScale(2,RoundingMode.HALF_EVEN);
            updateIEPS();
            iepsUpdated=true;
        }else{
            if(tasaIEPS.compareTo(tasa)!=0){
                iepsUpdated=false;
                setTasaIEPS(tasa);
            }
        }
    }

    /**
     * @return the ieps
     */
    public BigDecimal getIEPS() {
        return ieps;
    }

    /**
     * @param ieps the ieps to set
     */
    public void setIEPS(BigDecimal ieps) {
        this.ieps=ieps.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    private void updateIEPS() {
        BigDecimal ieps1 = getValorUniario().multiply(getCantidad()).multiply( tasaIEPS );
        this.setIEPS( ieps1 );
    }
    
    //Calcula el IVA de este renglón
    public BigDecimal getIVA() {
        MathContext mc = MathContext.DECIMAL64;
        if(getTasa0()) {
              return new BigDecimal("0.00", mc).setScale(2,RoundingMode.HALF_EVEN);
          } else {
              return getImporte().multiply(new BigDecimal("0.16"), mc).setScale(2,RoundingMode.HALF_EVEN);
          }               
    }

    private Concepto.Impuestos getImpuestos() {
        MathContext mc = MathContext.DECIMAL64;
        
        ObjectFactory of = new ObjectFactory();
        Concepto.Impuestos imps = of.createComprobanteConceptosConceptoImpuestos();
        Concepto.Impuestos.Traslados trs = of.createComprobanteConceptosConceptoImpuestosTraslados();
        List<Concepto.Impuestos.Traslados.Traslado> list = trs.getTraslado();

        Concepto.Impuestos.Traslados.Traslado t1 = of.createComprobanteConceptosConceptoImpuestosTrasladosTraslado();
        
        //Establezco la escala "6" con el fin de que en el XML las cantidades aparezcan
        //  con los 6 dígitos decimales que establece el SAT en el Anexo 20, sin embargo
        //  es posible que algunas de las operaciones no se hagan con una precisión de más
        //  de dos dígitos
        //Update Oct/2017. Las nuevas reglas indican que se debe establecer como máximo el
        //  número de deciimales que soporta la moneda, en pesos mexicanos eso es 2
        //Agrega IVA
        if(getTasa0()) {   
            t1.setBase(getImporte());
            t1.setImporte(new BigDecimal(0d).setScale(2, RoundingMode.HALF_EVEN));
            t1.setImpuesto("002"); // Catálogo c_Impuesto: 002 = IVA
            t1.setTipoFactor(CTipoFactor.TASA);
            t1.setTasaOCuota(new BigDecimal("0.000000").setScale(6, RoundingMode.HALF_EVEN));        
            list.add(t1);
        } else {            
            t1.setBase(getImporte());
            t1.setImporte(getIVA());
            t1.setImpuesto("002"); // Catálogo c_Impuesto: 002 = IVA
            t1.setTipoFactor(CTipoFactor.TASA);
            t1.setTasaOCuota(new BigDecimal("0.160000").setScale(6, RoundingMode.HALF_EVEN));
            list.add(t1);
        }
        //Agrega IEPS
        if(getTasaIEPS() != null && getTasaIEPS().compareTo(BigDecimal.ZERO)!=0) {
            Concepto.Impuestos.Traslados.Traslado t2 = of.createComprobanteConceptosConceptoImpuestosTrasladosTraslado();
            t2.setBase(getImporte());
            t2.setImpuesto("003"); // Catálogo c_Impuesto: 003 = IEPS
            t2.setImporte(getIEPS());
            t2.setTasaOCuota(getTasaIEPS()); //Lo divido entre 100 porque en CFDIv3.3 Tasa en realidad es un factor
            t2.setTipoFactor(CTipoFactor.TASA);
            list.add(t2);
        }
       
        imps.setTraslados(trs);        
        return imps;
     }

    /**
     * @return the claveUnidadSat
     */
    public String getClaveUnidadSat() {
        return claveUnidadSat;
    }

    /**
     * @param claveUnidadSat the claveUnidadSat to set
     */
    public void setClaveUnidadSat(String claveUnidadSat) {
        this.claveUnidadSat = claveUnidadSat;
    }

    /**
     * @return the claveProductoSat
     */
    public String getClaveProductoSat() {
        return claveProductoSat;
    }

    /**
     * @param claveProductoSat the claveProductoSat to set
     */
    public void setClaveProductoSat(String claveProductoSat) {
        if(claveProductoSat!=null)
            this.claveProductoSat = claveProductoSat.trim();
    }

    /**
     * @return the descuento
     */
    public BigDecimal getDescuento() {
        return descuento;
    }

    /**
     * @param descuento the descuento to set
     */
    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento.setScale(2, RoundingMode.HALF_EVEN);
    }

    /**
     * @return the subtotal
     */
    public BigDecimal getSubtotal() {
        return subtotal;
    }

    /**
     * @param subtotal the subtotal to set
     */
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal.setScale(2, RoundingMode.HALF_EVEN);
    }
     
}
