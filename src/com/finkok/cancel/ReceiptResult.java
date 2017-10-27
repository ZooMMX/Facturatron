
package com.finkok.cancel;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ReceiptResult complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ReceiptResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="uuid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="success" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="receipt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taxpayer_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="error" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReceiptResult", namespace = "apps.services.soap.core.views", propOrder = {
    "uuid",
    "success",
    "receipt",
    "taxpayerId",
    "error",
    "date"
})
public class ReceiptResult {

    @XmlElementRef(name = "uuid", namespace = "apps.services.soap.core.views", type = JAXBElement.class, required = false)
    protected JAXBElement<String> uuid;
    @XmlElementRef(name = "success", namespace = "apps.services.soap.core.views", type = JAXBElement.class, required = false)
    protected JAXBElement<Boolean> success;
    @XmlElementRef(name = "receipt", namespace = "apps.services.soap.core.views", type = JAXBElement.class, required = false)
    protected JAXBElement<String> receipt;
    @XmlElementRef(name = "taxpayer_id", namespace = "apps.services.soap.core.views", type = JAXBElement.class, required = false)
    protected JAXBElement<String> taxpayerId;
    @XmlElementRef(name = "error", namespace = "apps.services.soap.core.views", type = JAXBElement.class, required = false)
    protected JAXBElement<String> error;
    @XmlElementRef(name = "date", namespace = "apps.services.soap.core.views", type = JAXBElement.class, required = false)
    protected JAXBElement<String> date;

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
     * Obtiene el valor de la propiedad success.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public JAXBElement<Boolean> getSuccess() {
        return success;
    }

    /**
     * Define el valor de la propiedad success.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public void setSuccess(JAXBElement<Boolean> value) {
        this.success = value;
    }

    /**
     * Obtiene el valor de la propiedad receipt.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getReceipt() {
        return receipt;
    }

    /**
     * Define el valor de la propiedad receipt.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setReceipt(JAXBElement<String> value) {
        this.receipt = value;
    }

    /**
     * Obtiene el valor de la propiedad taxpayerId.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTaxpayerId() {
        return taxpayerId;
    }

    /**
     * Define el valor de la propiedad taxpayerId.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTaxpayerId(JAXBElement<String> value) {
        this.taxpayerId = value;
    }

    /**
     * Obtiene el valor de la propiedad error.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getError() {
        return error;
    }

    /**
     * Define el valor de la propiedad error.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setError(JAXBElement<String> value) {
        this.error = value;
    }

    /**
     * Obtiene el valor de la propiedad date.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDate() {
        return date;
    }

    /**
     * Define el valor de la propiedad date.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDate(JAXBElement<String> value) {
        this.date = value;
    }

}
