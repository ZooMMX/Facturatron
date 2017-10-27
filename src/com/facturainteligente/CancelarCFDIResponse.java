
package com.facturainteligente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CancelarCFDIResult" type="{https://www.appfacturainteligente.com/ConexionRemotaCFDI32}ArrayOfString" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "cancelarCFDIResult"
})
@XmlRootElement(name = "CancelarCFDIResponse")
public class CancelarCFDIResponse {

    @XmlElement(name = "CancelarCFDIResult")
    protected ArrayOfString cancelarCFDIResult;

    /**
     * Obtiene el valor de la propiedad cancelarCFDIResult.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getCancelarCFDIResult() {
        return cancelarCFDIResult;
    }

    /**
     * Define el valor de la propiedad cancelarCFDIResult.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setCancelarCFDIResult(ArrayOfString value) {
        this.cancelarCFDIResult = value;
    }

}
