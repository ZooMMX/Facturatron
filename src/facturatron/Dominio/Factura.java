/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.Dominio;

import com.phesus.facturatron.presentation.mvc.model.RenglonModel;
import facturatron.mvc.Model;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import mx.bigdata.sat.cfdi.v33.schema.CMetodoPago;
import mx.bigdata.sat.cfdi.v33.schema.CTipoDeComprobante;

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
     * @return the ticket info
     */
    public String getTicketInfo() {
        return ticketInfo;
    }

    /**
     * @param ticket info the ticket info to set
     */
    public void setTicketInfo(String ticketInfo) {
        this.ticketInfo = ticketInfo;
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

    /**
     * @return the folioFiscal
     */
    public String getFolioFiscal() {
        return folioFiscal;
    }

    /**
     * @param folioFiscal the folioFiscal to set
     */
    public void setFolioFiscal(String folioFiscal) {
        this.folioFiscal = folioFiscal;
    }

    public enum Estado {CANCELADO, VIGENTE};
    @NotNull
    private Integer id;
    @NotNull
    private String version;
    @NotNull
    private Date fecha;
    @NotNull
    private Time hora;
    @NotNull
    private String serie;
    @NotNull
    private BigInteger folio;
    @NotNull
    private String folioFiscal;
    @NotNull
    private String sello;
    @NotNull
    private String noCertificado;
    @NotNull
    //private BigInteger noAprobacion         = new BigInteger("122123");
    private BigInteger noAprobacion;
    @NotNull
    private Integer anoAprobacion;
    @NotNull
    private FormaDePago formaDePago          = FormaDePago.EFECTIVO;
    @NotNull
    private MetodoDePagoEnum metodoDePago    = MetodoDePagoEnum.PUE;
    @NotNull
    private BigDecimal subtotal              = new BigDecimal("0.0");
    @NotNull
    private BigDecimal total                 = new BigDecimal("0.0");
    @NotNull
    private BigDecimal descuento             = new BigDecimal("0.0");
    @NotNull
    private Estado estadoComprobante     = Estado.VIGENTE;
    @NotNull
    private String observaciones         = "";
    private String facturasRelacionadas  = "";
    private String tipoRelacionDeFacturaRelacionada  = "";
    @NotNull
    private String usoCfdi="";
    @NotNull
    private String ticketInfo            = "";
    @NotNull
    private CTipoDeComprobante tipoDeComprobante;
    @NotNull
    private Persona emisor               = new Persona();
    @NotNull
    private Persona emisorSucursal       = new Persona();
    @NotNull
    private Persona receptor             = new Persona();
    @NotNull
    private BigDecimal ivaTrasladado         = new BigDecimal("0.0");
    @NotNull
    private BigDecimal iepsTrasladado        = new BigDecimal("0.0");
    @NotNull
    private String certificado;
    @NotNull
    private String motivoDescuento;
    @NotNull
    private String xml;
    @NotNull
    private ArrayList<RenglonModel> renglones = new ArrayList<RenglonModel>();

    public Factura() {
        subtotal.setScale(2);
        total.setScale(2);

    }
    
  public static List createBeanCollection(){

    ArrayList <Factura> ret = new ArrayList<Factura>();
    
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
    public ArrayList<RenglonModel> getRenglones() {
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
    public FormaDePago getFormaDePago() {
        return formaDePago;
    }

    /**
     * @param formaDePago the formaDePago to set
     */
    public void setFormaDePago(FormaDePago formaDePago) {
        this.formaDePago = formaDePago;
    }

    /**
     * @return the formaDePago
     */
    public MetodoDePagoEnum getMetodoDePago() {
        return metodoDePago;
    }

    /**
     * @param formaDePago the formaDePago to set
     */
    public void setMetodoDePago(MetodoDePagoEnum metodoDePago) {
        this.metodoDePago = metodoDePago;
    }


    /**
     * @return the tipoDeComprobante
     */
    public CTipoDeComprobante getTipoDeComprobante() {
        return tipoDeComprobante;
    }

    /**
     * @param tipoDeComprobante the tipoDeComprobante to set
     */
    public void setTipoDeComprobante(CTipoDeComprobante tipoDeComprobante) {
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
     * @return the iepsTrasladado
     */
    public BigDecimal getIEPSTrasladado() {
        return iepsTrasladado;
    }

    /**
     * @param ivaTrasladado the ivaTrasladado to set
     */
    public void setIEPSTrasladado(BigDecimal iepsTrasladado) {
        this.iepsTrasladado = iepsTrasladado;
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
    public void setRenglones(ArrayList<RenglonModel> renglones) {
        this.renglones = renglones;
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
        this.descuento = descuento;
    }

    /**
     * @return the facturasRelacionadas
     */
    public String getFacturasRelacionadas() {
        return facturasRelacionadas;
    }

    /**
     * @param facturasRelacionadas the facturasRelacionadas to set
     */
    public void setFacturasRelacionadas(String facturasRelacionadas) {
        this.facturasRelacionadas = facturasRelacionadas;
    }

    /**
     * @return the tipoRelacionDeFacturaRelacionada
     */
    public String getTipoRelacionDeFacturaRelacionada() {
        return tipoRelacionDeFacturaRelacionada;
    }

    /**
     * @param tipoRelacionDeFacturaRelacionada the tipoRelacionDeFacturaRelacionada to set
     */
    public void setTipoRelacionDeFacturaRelacionada(String tipoRelacionDeFacturaRelacionada) {
        this.tipoRelacionDeFacturaRelacionada = tipoRelacionDeFacturaRelacionada;
    }
    
}