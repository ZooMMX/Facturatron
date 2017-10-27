
package com.finkok.cancel;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para UUIDS complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="UUIDS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="uuids" type="{http://facturacion.finkok.com/cancellation}stringArray" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UUIDS", namespace = "apps.services.soap.core.views", propOrder = {
    "uuids"
})
public class UUIDS {

    @XmlElementRef(name = "uuids", namespace = "apps.services.soap.core.views", type = JAXBElement.class, required = false)
    protected JAXBElement<StringArray> uuids;

    /**
     * Obtiene el valor de la propiedad uuids.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StringArray }{@code >}
     *     
     */
    public JAXBElement<StringArray> getUuids() {
        return uuids;
    }

    /**
     * Define el valor de la propiedad uuids.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StringArray }{@code >}
     *     
     */
    public void setUuids(JAXBElement<StringArray> value) {
        this.uuids = value;
    }

}
