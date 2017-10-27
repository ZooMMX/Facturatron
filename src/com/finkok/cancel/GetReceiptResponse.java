
package com.finkok.cancel;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para get_receiptResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="get_receiptResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="get_receiptResult" type="{apps.services.soap.core.views}ReceiptResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "get_receiptResponse", propOrder = {
    "getReceiptResult"
})
public class GetReceiptResponse {

    @XmlElementRef(name = "get_receiptResult", namespace = "http://facturacion.finkok.com/cancel", type = JAXBElement.class, required = false)
    protected JAXBElement<ReceiptResult> getReceiptResult;

    /**
     * Obtiene el valor de la propiedad getReceiptResult.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ReceiptResult }{@code >}
     *     
     */
    public JAXBElement<ReceiptResult> getGetReceiptResult() {
        return getReceiptResult;
    }

    /**
     * Define el valor de la propiedad getReceiptResult.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ReceiptResult }{@code >}
     *     
     */
    public void setGetReceiptResult(JAXBElement<ReceiptResult> value) {
        this.getReceiptResult = value;
    }

}
