
package com.finkok.cancel;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para cancel_signatureResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="cancel_signatureResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cancel_signatureResult" type="{apps.services.soap.core.views}CancelaCFDResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cancel_signatureResponse", propOrder = {
    "cancelSignatureResult"
})
public class CancelSignatureResponse {

    @XmlElementRef(name = "cancel_signatureResult", namespace = "http://facturacion.finkok.com/cancel", type = JAXBElement.class, required = false)
    protected JAXBElement<CancelaCFDResult> cancelSignatureResult;

    /**
     * Obtiene el valor de la propiedad cancelSignatureResult.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CancelaCFDResult }{@code >}
     *     
     */
    public JAXBElement<CancelaCFDResult> getCancelSignatureResult() {
        return cancelSignatureResult;
    }

    /**
     * Define el valor de la propiedad cancelSignatureResult.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CancelaCFDResult }{@code >}
     *     
     */
    public void setCancelSignatureResult(JAXBElement<CancelaCFDResult> value) {
        this.cancelSignatureResult = value;
    }

}
