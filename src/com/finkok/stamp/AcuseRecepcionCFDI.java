
package com.finkok.stamp;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para AcuseRecepcionCFDI complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="AcuseRecepcionCFDI">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="xml" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UUID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="faultstring" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fecha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodEstatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="faultcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SatSeal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Incidencias" type="{apps.services.soap.core.views}IncidenciaArray" minOccurs="0"/>
 *         &lt;element name="NoCertificadoSAT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AcuseRecepcionCFDI", namespace = "apps.services.soap.core.views", propOrder = {
    "xml",
    "uuid",
    "faultstring",
    "fecha",
    "codEstatus",
    "faultcode",
    "satSeal",
    "incidencias",
    "noCertificadoSAT"
})
public class AcuseRecepcionCFDI {

    @XmlElementRef(name = "xml", namespace = "apps.services.soap.core.views", type = JAXBElement.class, required = false)
    protected JAXBElement<String> xml;
    @XmlElementRef(name = "UUID", namespace = "apps.services.soap.core.views", type = JAXBElement.class, required = false)
    protected JAXBElement<String> uuid;
    @XmlElementRef(name = "faultstring", namespace = "apps.services.soap.core.views", type = JAXBElement.class, required = false)
    protected JAXBElement<String> faultstring;
    @XmlElementRef(name = "Fecha", namespace = "apps.services.soap.core.views", type = JAXBElement.class, required = false)
    protected JAXBElement<String> fecha;
    @XmlElementRef(name = "CodEstatus", namespace = "apps.services.soap.core.views", type = JAXBElement.class, required = false)
    protected JAXBElement<String> codEstatus;
    @XmlElementRef(name = "faultcode", namespace = "apps.services.soap.core.views", type = JAXBElement.class, required = false)
    protected JAXBElement<String> faultcode;
    @XmlElementRef(name = "SatSeal", namespace = "apps.services.soap.core.views", type = JAXBElement.class, required = false)
    protected JAXBElement<String> satSeal;
    @XmlElementRef(name = "Incidencias", namespace = "apps.services.soap.core.views", type = JAXBElement.class, required = false)
    protected JAXBElement<IncidenciaArray> incidencias;
    @XmlElementRef(name = "NoCertificadoSAT", namespace = "apps.services.soap.core.views", type = JAXBElement.class, required = false)
    protected JAXBElement<String> noCertificadoSAT;

    /**
     * Obtiene el valor de la propiedad xml.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getXml() {
        return xml;
    }

    /**
     * Define el valor de la propiedad xml.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setXml(JAXBElement<String> value) {
        this.xml = value;
    }

    /**
     * Obtiene el valor de la propiedad uuid.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUUID() {
        return uuid;
    }

    /**
     * Define el valor de la propiedad uuid.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUUID(JAXBElement<String> value) {
        this.uuid = value;
    }

    /**
     * Obtiene el valor de la propiedad faultstring.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFaultstring() {
        return faultstring;
    }

    /**
     * Define el valor de la propiedad faultstring.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFaultstring(JAXBElement<String> value) {
        this.faultstring = value;
    }

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFecha() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFecha(JAXBElement<String> value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad codEstatus.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodEstatus() {
        return codEstatus;
    }

    /**
     * Define el valor de la propiedad codEstatus.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodEstatus(JAXBElement<String> value) {
        this.codEstatus = value;
    }

    /**
     * Obtiene el valor de la propiedad faultcode.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFaultcode() {
        return faultcode;
    }

    /**
     * Define el valor de la propiedad faultcode.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFaultcode(JAXBElement<String> value) {
        this.faultcode = value;
    }

    /**
     * Obtiene el valor de la propiedad satSeal.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSatSeal() {
        return satSeal;
    }

    /**
     * Define el valor de la propiedad satSeal.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSatSeal(JAXBElement<String> value) {
        this.satSeal = value;
    }

    /**
     * Obtiene el valor de la propiedad incidencias.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link IncidenciaArray }{@code >}
     *     
     */
    public JAXBElement<IncidenciaArray> getIncidencias() {
        return incidencias;
    }

    /**
     * Define el valor de la propiedad incidencias.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link IncidenciaArray }{@code >}
     *     
     */
    public void setIncidencias(JAXBElement<IncidenciaArray> value) {
        this.incidencias = value;
    }

    /**
     * Obtiene el valor de la propiedad noCertificadoSAT.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNoCertificadoSAT() {
        return noCertificadoSAT;
    }

    /**
     * Define el valor de la propiedad noCertificadoSAT.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNoCertificadoSAT(JAXBElement<String> value) {
        this.noCertificadoSAT = value;
    }

}
