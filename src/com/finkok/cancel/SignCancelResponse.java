
package com.finkok.cancel;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para sign_cancelResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="sign_cancelResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sign_cancelResult" type="{apps.services.soap.core.views}CancelaCFDResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sign_cancelResponse", propOrder = {
    "signCancelResult"
})
public class SignCancelResponse {

    @XmlElementRef(name = "sign_cancelResult", namespace = "http://facturacion.finkok.com/cancel", type = JAXBElement.class, required = false)
    protected JAXBElement<CancelaCFDResult> signCancelResult;

    /**
     * Obtiene el valor de la propiedad signCancelResult.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CancelaCFDResult }{@code >}
     *     
     */
    public JAXBElement<CancelaCFDResult> getSignCancelResult() {
        return signCancelResult;
    }

    /**
     * Define el valor de la propiedad signCancelResult.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CancelaCFDResult }{@code >}
     *     
     */
    public void setSignCancelResult(JAXBElement<CancelaCFDResult> value) {
        this.signCancelResult = value;
    }

}
