/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.lib.entities;

import java.math.BigDecimal;
import java.net.URI;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante;

import facturatron.lib.NumeroConLetra;
import java.text.DecimalFormat;
import mx.bigdata.sat.cfdi.v32.schema.ObjectFactory;
import mx.bigdata.sat.cfdi.v32.schema.TimbreFiscalDigital;

/**
 *
 * @author Octavio
 */
public class ComprobanteTron extends Comprobante {
    private String passKey;
    private URI URICer;
    private URI URIKey;
    private String cadenaOriginal;
    private boolean estadoComprobante = true;
    private ConceptosTron conceptosTron;
    private BigDecimal subtotalExento;
    private BigDecimal subtotalGravado0;
    private BigDecimal subtotalGravado16;
    private String importeConLetra;
    private String observaciones;

    public ComprobanteTron(Comprobante c) {
        super.setAddenda(c.getAddenda());
        super.setCertificado(c.getCertificado());
        super.setComplemento(c.getComplemento());
        super.setConceptos(c.getConceptos());
        super.setCondicionesDePago(c.getCondicionesDePago());
        super.setDescuento(c.getDescuento());
        super.setEmisor(c.getEmisor());
        super.setFecha(c.getFecha());
        super.setFechaFolioFiscalOrig(c.getFechaFolioFiscalOrig());
        super.setFolio(c.getFolio());
        super.setFolioFiscalOrig(c.getFolioFiscalOrig());
        super.setFormaDePago(c.getFormaDePago());
        super.setImpuestos(c.getImpuestos());
        super.setLugarExpedicion(c.getLugarExpedicion());
        super.setMetodoDePago(c.getMetodoDePago());
        super.setMoneda(c.getMoneda());
        super.setMontoFolioFiscalOrig(c.getMontoFolioFiscalOrig());
        super.setMotivoDescuento(c.getMotivoDescuento());
        super.setNoCertificado(c.getNoCertificado());
        super.setNumCtaPago(c.getNumCtaPago());
        super.setReceptor(c.getReceptor());
        super.setSello(c.getSello());
        super.setSerie(c.getSerie());
        super.setSerieFolioFiscalOrig(c.getSerieFolioFiscalOrig());
        super.setSubTotal(c.getSubTotal());
        super.setTipoCambio(c.getTipoCambio());
        super.setTipoDeComprobante(c.getTipoDeComprobante());
        super.setTotal(c.getTotal());
        super.setVersion(c.getVersion());
    }
    
    public ComprobanteTron() { }
    
    @Override
    public void setTotal(BigDecimal total) {
        super.setTotal(total);
        setImporteConLetra((new NumeroConLetra()).aCifra(total));
    }
    /**
     * @return the passKey
     */
    public String getPassKey() {
        return passKey;
    }

    /**
     * @param passKey the passKey to set
     */
    public void setPassKey(String passKey) {
        this.passKey = passKey;
    }

    /**
     * @return the URICer
     */
    public URI getURICer() {
        return URICer;
    }

    /**
     * @param URICer the URICer to set
     */
    public void setURICer(URI URICer) {
        this.URICer = URICer;
    }

    /**
     * @return the URIKey
     */
    public URI getURIKey() {
        return URIKey;
    }

    /**
     * @param URIKey the URIKey to set
     */
    public void setURIKey(URI URIKey) {
        this.URIKey = URIKey;
    }

    /**
     * @return the cadenaOriginal
     */
    public String getCadenaOriginal() {
        return cadenaOriginal;
    }

    /**
     * @param cadenaOriginal the cadenaOriginal to set
     */
    public void setCadenaOriginal(String cadenaOriginal) {
        this.cadenaOriginal = cadenaOriginal;
    }

    /**
     * @return the estadoComprobante
     */
    public boolean isEstadoComprobante() {
        return estadoComprobante;
    }

    /**
     * @param estadoComprobante the estadoComprobante to set
     */
    public void setEstadoComprobante(boolean estadoComprobante) {
        this.estadoComprobante = estadoComprobante;
    }

    /**
     * @return the conceptosTron
     */
    public ConceptosTron getConceptosTron() {
        return conceptosTron;
    }

    /**
     * @param conceptosTron the conceptosTron to set
     */
    public void setConceptosTron(ConceptosTron conceptosTron) {
        this.conceptosTron = conceptosTron;
    }

    /**
     * @return the subtotalExento
     */
    public BigDecimal getSubtotalExento() {
        return subtotalExento;
    }

    /**
     * @param subtotalExento the subtotalExento to set
     */
    public void setSubtotalExento(BigDecimal subtotalExento) {
        this.subtotalExento = subtotalExento;
    }

    /**
     * @return the subtotalGravado0
     */
    public BigDecimal getSubtotalGravado0() {
        return subtotalGravado0;
    }

    /**
     * @param subtotalGravado0 the subtotalGravado0 to set
     */
    public void setSubtotalGravado0(BigDecimal subtotalGravado0) {
        this.subtotalGravado0 = subtotalGravado0;
    }

    /**
     * @return the subtotalGravado16
     */
    public BigDecimal getSubtotalGravado16() {
        return subtotalGravado16;
    }

    /**
     * @param subtotalGravado16 the subtotalGravado16 to set
     */
    public void setSubtotalGravado16(BigDecimal subtotalGravado16) {
        this.subtotalGravado16 = subtotalGravado16;
    }

    /**
     * @return the numeroConLetra
     */
    public String getImporteConLetra() {
        return importeConLetra;
    }

    /**
     * @param numeroConLetra the numeroConLetra to set
     */
    private void setImporteConLetra(String numeroConLetra) {
        this.importeConLetra = numeroConLetra;
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

    public TimbreFiscalDigital getTimbre() {
        for (Object object : getComplemento().getAny()) {
            if(object instanceof TimbreFiscalDigital) return (TimbreFiscalDigital) object;
        }
        return null;
    }
    
    public String getFolioFiscal() {
        return getTimbre().getUUID();
    }
    
    public void addTimbreFiscalDigital(TimbreFiscalDigital timbre) {
        ObjectFactory of = new ObjectFactory();
        if( getComplemento() == null ) 
            setComplemento(of.createComprobanteComplemento());
        
        getComplemento().getAny().add(timbre);
    }
    
    public String getQrstring() {
        DecimalFormat formatter = new DecimalFormat("0000000000.000000");
        
        String rfcEmisor   = getEmisor().getRfc();
        String rfcReceptor = getReceptor().getRfc();
        String granTotal   = formatter.format( getTotal().doubleValue() );
        String uuid        = getFolioFiscal();
        
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("?re=").append(rfcEmisor)
                .append("&rr=").append(rfcReceptor)
                .append("&tt=").append(granTotal)
                .append("&id=").append(uuid);
        return stringBuilder.toString();
    }
}
