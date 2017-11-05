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
    private BigDecimal importe;
    private BigDecimal base;
    private BigDecimal cantidad;
    private String descripcion = "";
    private BigDecimal valorUnitario;
    private Boolean tasa0 = true;
    private BigDecimal tasaIEPS;
    private BigDecimal ieps;
    private BigDecimal descuento;
    private BigDecimal iva;
    private Boolean iepsUpdated=false;
    private MathContext mc = MathContext.DECIMAL64;
    private BigDecimal total;

    public Renglon() {
        importe=new BigDecimal("0.00",mc);
        cantidad=new BigDecimal("0.00",mc);
        valorUnitario=new BigDecimal("0.00",mc);
        tasaIEPS=new BigDecimal("0.00",mc);
        ieps=new BigDecimal("0.00",mc);
        base=new BigDecimal("0.00",mc);
        total=new BigDecimal("0.00",mc);
        descuento=new BigDecimal("0.00",mc);
        iva=new BigDecimal("0.00",mc);
        importe.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        cantidad.setScale(4, BigDecimal.ROUND_HALF_EVEN);
        valorUnitario.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        tasaIEPS.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        ieps.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        base.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        total.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        descuento.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        iva.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        tasa0=true;
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
    
    private void updateRenglon(){        
        importe=cantidad.multiply(valorUnitario);
        base=importe.subtract(descuento, mc);
        ieps=base.multiply(tasaIEPS);
        if(tasa0)
            iva=BigDecimal.ZERO;
        else
            iva=base.multiply(new BigDecimal("0.16"));
        total=base.add(ieps, mc).add(iva, mc);
        
        base     = base.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        importe  = importe.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        ieps     = ieps.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        iva      = iva.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        total    = total.setScale(2, BigDecimal.ROUND_HALF_EVEN);
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
        c1.setValorUnitario(getValorUnitario());
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
        if(cantidad==null)
            cantidad=new BigDecimal("0.00", mc);
        this.cantidad = cantidad.setScale(2, BigDecimal.ROUND_HALF_EVEN);;
        updateRenglon();
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
    //El importe se calcula como resultado de updateRenglon
    /*
    public void setImporte(BigDecimal importe) {
        if(importe==null)
            importe=new BigDecimal("0.00", mc);        
        this.importe = importe;
        //updateRenglon(); 
    }*/

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
    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    /**
     * @param valorUniario the valorUniario to set
     */
    public void setValorUnitario(BigDecimal valorUniario) {
        if(valorUniario==null)
            new BigDecimal("0.00", mc);
        this.valorUnitario = valorUniario.setScale(2, BigDecimal.ROUND_HALF_EVEN);;
        updateRenglon();
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
        if(tasa0==null)
            tasa0=true;
        this.tasa0 = tasa0;
        updateRenglon();
    }

    public BigDecimal getTasaIEPS() {
        return tasaIEPS;
    }

    public void setTasaIEPS(BigDecimal tasa) {
        if(tasa==null)
            tasa=new BigDecimal("0.00", mc);
        else
        {
            tasa = tasa.setScale(2, BigDecimal.ROUND_HALF_EVEN);
            tasaIEPS = tasa.divide(new BigDecimal("100.00", mc),mc);
            tasaIEPS = tasaIEPS.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        }
        updateRenglon();
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
        if(ieps==null)
            ieps=new BigDecimal("0.00", mc);
        this.ieps=ieps;
        updateRenglon();
    }

    //Calcula el IVA de este renglón
    public BigDecimal getIVA() {
        return iva;             
    }

    private Concepto.Impuestos getImpuestos() {
        
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
            t1.setBase(getBase());
            t1.setImporte(new BigDecimal(0d).setScale(2, RoundingMode.HALF_EVEN));
            t1.setImpuesto("002"); // Catálogo c_Impuesto: 002 = IVA
            t1.setTipoFactor(CTipoFactor.TASA);
            t1.setTasaOCuota(new BigDecimal("0.000000").setScale(6, RoundingMode.HALF_EVEN));        
            list.add(t1);
        } else {            
            t1.setBase(getBase());
            t1.setImporte(getIVA().setScale(2, RoundingMode.HALF_EVEN));
            t1.setImpuesto("002"); // Catálogo c_Impuesto: 002 = IVA
            t1.setTipoFactor(CTipoFactor.TASA);
            t1.setTasaOCuota(new BigDecimal("0.160000").setScale(6, RoundingMode.HALF_EVEN));
            list.add(t1);
        }
        //Agrega IEPS
        if(getTasaIEPS() != null && getTasaIEPS().compareTo(BigDecimal.ZERO)!=0) {
            Concepto.Impuestos.Traslados.Traslado t2 = of.createComprobanteConceptosConceptoImpuestosTrasladosTraslado();
            t2.setBase(getBase());
            t2.setImpuesto("003"); // Catálogo c_Impuesto: 003 = IEPS
            t2.setImporte(getIEPS().setScale(2, RoundingMode.HALF_EVEN));
            t2.setTasaOCuota(getTasaIEPS().setScale(6, RoundingMode.HALF_EVEN)); //Lo divido entre 100 porque en CFDIv3.3 Tasa en realidad es un factor
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
        if(claveUnidadSat==null)
            claveUnidadSat="";
        return claveUnidadSat;
    }

    /**
     * @param claveUnidadSat the claveUnidadSat to set
     */
    public void setClaveUnidadSat(String claveUnidadSat) {
        if(claveUnidadSat==null)
            claveUnidadSat="";
        this.claveUnidadSat = claveUnidadSat;
    }

    /**
     * @return the claveProductoSat
     */
    public String getClaveProductoSat() {
        if(claveProductoSat==null)
            claveProductoSat="";
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
        if(descuento==null)
            descuento=new BigDecimal("0.00", mc);
        this.descuento = descuento.setScale(2, RoundingMode.HALF_EVEN);
        updateRenglon();
    }

    /**
     * @return the subtotal
     */
    public BigDecimal getBase() {
        return base;
    }

    /** Esto se establece en updateRenglon
     * @param subtotal the subtotal to set
     */
    /* Esto se establece en updateRenglon
    public void setSubtotal(BigDecimal subtotal) {
        if(subtotal==null)
            this.base=new BigDecimal("0.00", mc);
        this.base=subtotal;
        updateRenglon();
    }*/
     
}