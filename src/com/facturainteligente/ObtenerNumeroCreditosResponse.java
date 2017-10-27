
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
 *         &lt;element name="ObtenerNumeroCreditosResult" type="{https://www.appfacturainteligente.com/ConexionRemotaCFDI32}ArrayOfString" minOccurs="0"/>
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
    "obtenerNumeroCreditosResult"
})
@XmlRootElement(name = "ObtenerNumeroCreditosResponse")
public class ObtenerNumeroCreditosResponse {

    @XmlElement(name = "ObtenerNumeroCreditosResult")
    protected ArrayOfString obtenerNumeroCreditosResult;

    /**
     * Obtiene el valor de la propiedad obtenerNumeroCreditosResult.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getObtenerNumeroCreditosResult() {
        return obtenerNumeroCreditosResult;
    }

    /**
     * Define el valor de la propiedad obtenerNumeroCreditosResult.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setObtenerNumeroCreditosResult(ArrayOfString value) {
        this.obtenerNumeroCreditosResult = value;
    }

}
