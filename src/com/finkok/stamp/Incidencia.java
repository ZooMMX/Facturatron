
package com.finkok.stamp;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Incidencia complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Incidencia">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdIncidencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RfcEmisor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Uuid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WorkProcessId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MensajeIncidencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExtraInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NoCertificadoPac" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaRegistro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Incidencia", namespace = "apps.services.soap.core.views", propOrder = {
    "idIncidencia",
    "rfcEmisor",
    "uuid",
    "codigoError",
    "workProcessId",
    "mensajeIncidencia",
    "extraInfo",
    "noCertificadoPac",
    "fechaRegistro"
})
public class Incidencia {

    @XmlElementRef(name = "IdIncidencia", namespace = "apps.services.soap.core.views", type = JAXBElement.class, required = false)
    protected JAXBElement<String> idIncidencia;
    @XmlElementRef(name = "RfcEmisor", namespace = "apps.services.soap.core.views", type = JAXBElement.class, required = false)
    protected JAXBElement<String> rfcEmisor;
    @XmlElementRef(name = "Uuid", namespace = "apps.services.soap.core.views", type = JAXBElement.class, required = false)
    protected JAXBElement<String> uuid;
    @XmlElementRef(name = "CodigoError", namespace = "apps.services.soap.core.views", type = JAXBElement.class, required = false)
    protected JAXBElement<String> codigoError;
    @XmlElementRef(name = "WorkProcessId", namespace = "apps.services.soap.core.views", type = JAXBElement.class, required = false)
    protected JAXBElement<String> workProcessId;
    @XmlElementRef(name = "MensajeIncidencia", namespace = "apps.services.soap.core.views", type = JAXBElement.class, required = false)
    protected JAXBElement<String> mensajeIncidencia;
    @XmlElementRef(name = "ExtraInfo", namespace = "apps.services.soap.core.views", type = JAXBElement.class, required = false)
    protected JAXBElement<String> extraInfo;
    @XmlElementRef(name = "NoCertificadoPac", namespace = "apps.services.soap.core.views", type = JAXBElement.class, required = false)
    protected JAXBElement<String> noCertificadoPac;
    @XmlElementRef(name = "FechaRegistro", namespace = "apps.services.soap.core.views", type = JAXBElement.class, required = false)
    protected JAXBElement<String> fechaRegistro;

    /**
     * Obtiene el valor de la propiedad idIncidencia.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdIncidencia() {
        return idIncidencia;
    }

    /**
     * Define el valor de la propiedad idIncidencia.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdIncidencia(JAXBElement<String> value) {
        this.idIncidencia = value;
    }

    /**
     * Obtiene el valor de la propiedad rfcEmisor.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRfcEmisor() {
        return rfcEmisor;
    }

    /**
     * Define el valor de la propiedad rfcEmisor.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRfcEmisor(JAXBElement<String> value) {
        this.rfcEmisor = value;
    }

    /**
     * Obtiene el valor de la propiedad uuid.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUuid() {
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
    public void setUuid(JAXBElement<String> value) {
        this.uuid = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoError.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoError() {
        return codigoError;
    }

    /**
     * Define el valor de la propiedad codigoError.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoError(JAXBElement<String> value) {
        this.codigoError = value;
    }

    /**
     * Obtiene el valor de la propiedad workProcessId.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getWorkProcessId() {
        return workProcessId;
    }

    /**
     * Define el valor de la propiedad workProcessId.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setWorkProcessId(JAXBElement<String> value) {
        this.workProcessId = value;
    }

    /**
     * Obtiene el valor de la propiedad mensajeIncidencia.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMensajeIncidencia() {
        return mensajeIncidencia;
    }

    /**
     * Define el valor de la propiedad mensajeIncidencia.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMensajeIncidencia(JAXBElement<String> value) {
        this.mensajeIncidencia = value;
    }

    /**
     * Obtiene el valor de la propiedad extraInfo.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getExtraInfo() {
        return extraInfo;
    }

    /**
     * Define el valor de la propiedad extraInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setExtraInfo(JAXBElement<String> value) {
        this.extraInfo = value;
    }

    /**
     * Obtiene el valor de la propiedad noCertificadoPac.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNoCertificadoPac() {
        return noCertificadoPac;
    }

    /**
     * Define el valor de la propiedad noCertificadoPac.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNoCertificadoPac(JAXBElement<String> value) {
        this.noCertificadoPac = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaRegistro.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Define el valor de la propiedad fechaRegistro.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaRegistro(JAXBElement<String> value) {
        this.fechaRegistro = value;
    }

}
