/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.Dominio;

import facturatron.MVC.Model;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author saul
 */
public class Factura extends Model implements Serializable {

    /**
     * @return the estado
     */
    public Estado getEstadoComprobante() {
        return estadoComprobante;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstadoComprobante(Estado estado) {
        this.estadoComprobante = estado;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the hora
     */
    public Time getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(Time hora) {
        this.hora = hora;
    }

    public enum Estado {CANCELADO, VIGENTE};
    private Integer id;
    private String version;
    private Date fecha;
    private Time hora;
    private String serie;
    private BigInteger folio;
    private String sello;
    private String noCertificado;
    private BigInteger noAprobacion;
    private Integer anoAprobacion;
    private String formaDePago           = "UNA SOLA EXHIBICIÓN";
    private BigDecimal subtotal              = new BigDecimal("0.0");
    private BigDecimal total                 = new BigDecimal("0.0");
    private BigDecimal descuentoTasa0        = new BigDecimal("0.0");
    private BigDecimal descuentoTasa16       = new BigDecimal("0.0");
    private Estado estadoComprobante     = Estado.VIGENTE;
    private String observaciones         = "";

    private String tipoDeComprobante;
    private Persona emisor               = new Persona();
    private Persona emisorSucursal       = new Persona();
    private Persona receptor             = new Persona();
    private BigDecimal ivaTrasladado         = new BigDecimal("0.0");
    private String certificado;
    private String motivoDescuento;
    private String xml;
    private ArrayList<Renglon> renglones = new ArrayList<Renglon>();

    public Factura() {
        subtotal.setScale(2);
        total.setScale(2);
        descuentoTasa0.setScale(2);
        descuentoTasa16.setScale(2);
    }
    
  public static List createBeanCollection(){

    ArrayList <Factura> ret = new ArrayList<Factura>();
    /*
    Factura fb = new Factura();
    fb.setRazon("TRESARI SERVICIO,S.A. DE C.V");
    fb.setDireccion("CALLE RADAMES TREVIÑO No. 4802 COL. MEXICO 68 C.P. 72300");
    fb.setRfc("TSE-091013-9C5");
    fb.setFolio(123);
    fb.setObservaciones("computadora toshiba");
    ArrayList <Renglon> renglones = new ArrayList <Renglon>();
    fb.setRenglones(renglones); //renglones de la factura
    Renglon rec = new Renglon();
    rec.setCantidad(2d);
    rec.setDescripcion("laptop toshiba");
    rec.setPu(2000d);
    rec.setImporte(4000d);
    renglones.add(rec);
    rec = new Renglon();
    rec.setCantidad(3d);
    rec.setDescripcion("laptop accer");
    rec.setPu(4000d);
    rec.setImporte(12000d);
    renglones.add(rec);

    ret.add(fb);

    fb = new Factura();
    fb.setRazon("SAUL ROMERO HARO");
    fb.setDireccion("CALLE RADAMES TREVIÑO No. 4802 COL. MEXICO 68 C.P. 72300");
    fb.setRfc("TSE-091013-9C5");
    fb.setFolio(123);
    fb.setObservaciones("computadora toshiba");
    ret.add(fb);

    */
    return ret;

  }


    /**
     * @return the folio
     */
    public BigInteger getFolio() {
        return folio;
    }

    /**
     * @param folio the folio to set
     */
    public void setFolio(BigInteger folio) {
        this.folio = folio;
    }

    /**
     * @return the renglones
     */
    public ArrayList<Renglon> getRenglones() {
        return renglones;
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
        this.subtotal = subtotal;
    }

    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the serie
     */
    public String getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(String serie) {
        if(serie == null || serie.isEmpty()) { serie = null; }
        this.serie = serie;
    }

    /**
     * @return the sello
     */
    public String getSello() {
        return sello;
    }

    /**
     * @param sello the sello to set
     */
    public void setSello(String sello) {
        this.sello = sello;
    }

    /**
     * @return the noCertificado
     */
    public String getNoCertificado() {
        return noCertificado;
    }

    /**
     * @param noCertificado the noCertificado to set
     */
    public void setNoCertificado(String noCertificado) {
        this.noCertificado = noCertificado;
    }

    /**
     * @return the noAprobacion
     */
    public BigInteger getNoAprobacion() {
        return noAprobacion;
    }

    /**
     * @param noAprobacion the noAprobacion to set
     */
    public void setNoAprobacion(BigInteger noAprobacion) {
        this.noAprobacion = noAprobacion;
    }

    /**
     * @return the anoAprobacion
     */
    public Integer getAnoAprobacion() {
        return anoAprobacion;
    }

    /**
     * @param anoAprobacion the anoAprobacion to set
     */
    public void setAnoAprobacion(Integer anoAprobacion) {
        this.anoAprobacion = anoAprobacion;
    }

    /**
     * @return the formaDePago
     */
    public String getFormaDePago() {
        return formaDePago;
    }

    /**
     * @param formaDePago the formaDePago to set
     */
    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }


    /**
     * @return the tipoDeComprobante
     */
    public String getTipoDeComprobante() {
        return tipoDeComprobante;
    }

    /**
     * @param tipoDeComprobante the tipoDeComprobante to set
     */
    public void setTipoDeComprobante(String tipoDeComprobante) {
        this.tipoDeComprobante = tipoDeComprobante;
    }

    /**
     * @return the emisor
     */
    public Persona getEmisor() {
        return emisor;
    }

    /**
     * @param emisor the emisor to set
     */
    public void setEmisor(Persona emisor) {
        this.emisor = emisor;
    }

    /**
     * @return the receptor
     */
    public Persona getReceptor() {
        return receptor;
    }

    /**
     * @param receptor the receptor to set
     */
    public void setReceptor(Persona receptor) {
        this.receptor = receptor;
    }

    /**
     * @return the ivaTrasladado
     */
    public BigDecimal getIvaTrasladado() {
        return ivaTrasladado;
    }

    /**
     * @param ivaTrasladado the ivaTrasladado to set
     */
    public void setIvaTrasladado(BigDecimal ivaTrasladado) {
        this.ivaTrasladado = ivaTrasladado;
    }

    /**
     * @return the certificado
     */
    public String getCertificado() {
        return certificado;
    }

    /**
     * @param certificado the certificado to set
     */
    public void setCertificado(String certificado) {
        this.certificado = certificado;
    }



    /**
     * @param renglones the renglones to set
     */
    public void setRenglones(ArrayList<Renglon> renglones) {
        this.renglones = renglones;
    }

    /**
     * @return the descuento
     */
    public BigDecimal getDescuentoTasa0() {
        return descuentoTasa0;
    }

    /**
     * @param descuento the descuento to set
     */
    public void setDescuentoTasa0(BigDecimal descuento) {
        this.descuentoTasa0 = descuento;
    }

    /**
     * @return the descuento
     */
    public BigDecimal getDescuentoTasa16() {
        return descuentoTasa16;
    }

    /**
     * @param descuento the descuento to set
     */
    public void setDescuentoTasa16(BigDecimal descuento) {
        this.descuentoTasa16 = descuento;
    }

    /**
     * @return the motivoDescuento
     */
    public String getMotivoDescuento() {
        return motivoDescuento;
    }

    /**
     * @param motivoDescuento the motivoDescuento to set
     */
    public void setMotivoDescuento(String motivoDescuento) {
        this.motivoDescuento = motivoDescuento;
    }

    /**
     * @return the xml
     */
    public String getXml() {
        return xml;
    }

    /**
     * @param xml the xml to set
     */
    public void setXml(String xml) {
        this.xml = xml;
    }

    /**
     * @return the emisorSucursal
     */
    public Persona getEmisorSucursal() {
        return emisorSucursal;
    }

    /**
     * @param emisorSucursal the emisorSucursal to set
     */
    public void setEmisorSucursal(Persona emisorSucursal) {
        this.emisorSucursal = emisorSucursal;
    }
    
}