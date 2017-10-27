
package com.finkok.cancel;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para cancelResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="cancelResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cancelResult" type="{apps.services.soap.core.views}CancelaCFDResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cancelResponse", propOrder = {
    "cancelResult"
})
public class CancelResponse {

    @XmlElementRef(name = "cancelResult", namespace = "http://facturacion.finkok.com/cancel", type = JAXBElement.class, required = false)
    protected JAXBElement<CancelaCFDResult> cancelResult;

    /**
     * Obtiene el valor de la propiedad cancelResult.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CancelaCFDResult }{@code >}
     *     
     */
    public JAXBElement<CancelaCFDResult> getCancelResult() {
        return cancelResult;
    }

    /**
     * Define el valor de la propiedad cancelResult.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CancelaCFDResult }{@code >}
     *     
     */
    public void setCancelResult(JAXBElement<CancelaCFDResult> value) {
        this.cancelResult = value;
    }

}
