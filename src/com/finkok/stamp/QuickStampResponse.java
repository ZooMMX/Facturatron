
package com.finkok.stamp;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para quick_stampResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="quick_stampResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="quick_stampResult" type="{apps.services.soap.core.views}AcuseRecepcionCFDI" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "quick_stampResponse", propOrder = {
    "quickStampResult"
})
public class QuickStampResponse {

    @XmlElementRef(name = "quick_stampResult", namespace = "http://facturacion.finkok.com/stamp", type = JAXBElement.class, required = false)
    protected JAXBElement<AcuseRecepcionCFDI> quickStampResult;

    /**
     * Obtiene el valor de la propiedad quickStampResult.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AcuseRecepcionCFDI }{@code >}
     *     
     */
    public JAXBElement<AcuseRecepcionCFDI> getQuickStampResult() {
        return quickStampResult;
    }

    /**
     * Define el valor de la propiedad quickStampResult.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AcuseRecepcionCFDI }{@code >}
     *     
     */
    public void setQuickStampResult(JAXBElement<AcuseRecepcionCFDI> value) {
        this.quickStampResult = value;
    }

}
