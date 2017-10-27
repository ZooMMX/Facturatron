
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
 *         &lt;element name="GenerarCFDIv32Result" type="{https://www.appfacturainteligente.com/ConexionRemotaCFDI32}ArrayOfString" minOccurs="0"/>
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
    "generarCFDIv32Result"
})
@XmlRootElement(name = "GenerarCFDIv32Response")
public class GenerarCFDIv32Response {

    @XmlElement(name = "GenerarCFDIv32Result")
    protected ArrayOfString generarCFDIv32Result;

    /**
     * Obtiene el valor de la propiedad generarCFDIv32Result.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getGenerarCFDIv32Result() {
        return generarCFDIv32Result;
    }

    /**
     * Define el valor de la propiedad generarCFDIv32Result.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setGenerarCFDIv32Result(ArrayOfString value) {
        this.generarCFDIv32Result = value;
    }

}
