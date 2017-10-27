
package com.finkok.stamp;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para stampedResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="stampedResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="stampedResult" type="{apps.services.soap.core.views}AcuseRecepcionCFDI" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "stampedResponse", propOrder = {
    "stampedResult"
})
public class StampedResponse {

    @XmlElementRef(name = "stampedResult", namespace = "http://facturacion.finkok.com/stamp", type = JAXBElement.class, required = false)
    protected JAXBElement<AcuseRecepcionCFDI> stampedResult;

    /**
     * Obtiene el valor de la propiedad stampedResult.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AcuseRecepcionCFDI }{@code >}
     *     
     */
    public JAXBElement<AcuseRecepcionCFDI> getStampedResult() {
        return stampedResult;
    }

    /**
     * Define el valor de la propiedad stampedResult.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AcuseRecepcionCFDI }{@code >}
     *     
     */
    public void setStampedResult(JAXBElement<AcuseRecepcionCFDI> value) {
        this.stampedResult = value;
    }

}
