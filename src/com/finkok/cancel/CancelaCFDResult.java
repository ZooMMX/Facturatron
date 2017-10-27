
package com.finkok.cancel;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CancelaCFDResult complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CancelaCFDResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Folios" type="{apps.services.soap.core.views}FolioArray" minOccurs="0"/>
 *         &lt;element name="Acuse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fecha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RfcEmisor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodEstatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CancelaCFDResult", namespace = "apps.services.soap.core.views", propOrder = {
    "folios",
    "acuse",
    "fecha",
    "rfcEmisor",
    "codEstatus"
})
public class CancelaCFDResult {

    @XmlElementRef(name = "Folios", namespace = "apps.services.soap.core.views", type = JAXBElement.class, required = false)
    protected JAXBElement<FolioArray> folios;
    @XmlElementRef(name = "Acuse", namespace = "apps.services.soap.core.views", type = JAXBElement.class, required = false)
    protected JAXBElement<String> acuse;
    @XmlElementRef(name = "Fecha", namespace = "apps.services.soap.core.views", type = JAXBElement.class, required = false)
    protected JAXBElement<String> fecha;
    @XmlElementRef(name = "RfcEmisor", namespace = "apps.services.soap.core.views", type = JAXBElement.class, required = false)
    protected JAXBElement<String> rfcEmisor;
    @XmlElementRef(name = "CodEstatus", namespace = "apps.services.soap.core.views", type = JAXBElement.class, required = false)
    protected JAXBElement<String> codEstatus;

    /**
     * Obtiene el valor de la propiedad folios.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link FolioArray }{@code >}
     *     
     */
    public JAXBElement<FolioArray> getFolios() {
        return folios;
    }

    /**
     * Define el valor de la propiedad folios.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link FolioArray }{@code >}
     *     
     */
    public void setFolios(JAXBElement<FolioArray> value) {
        this.folios = value;
    }

    /**
     * Obtiene el valor de la propiedad acuse.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAcuse() {
        return acuse;
    }

    /**
     * Define el valor de la propiedad acuse.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAcuse(JAXBElement<String> value) {
        this.acuse = value;
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

}
