
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
 *         &lt;element name="datosUsuario" type="{https://www.appfacturainteligente.com/ConexionRemotaCFDI32}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="ListaACancelar" type="{https://www.appfacturainteligente.com/ConexionRemotaCFDI32}ArrayOfString" minOccurs="0"/>
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
    "datosUsuario",
    "listaACancelar"
})
@XmlRootElement(name = "CancelarCFDI")
public class CancelarCFDI {

    protected ArrayOfString datosUsuario;
    @XmlElement(name = "ListaACancelar")
    protected ArrayOfString listaACancelar;

    /**
     * Obtiene el valor de la propiedad datosUsuario.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getDatosUsuario() {
        return datosUsuario;
    }

    /**
     * Define el valor de la propiedad datosUsuario.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setDatosUsuario(ArrayOfString value) {
        this.datosUsuario = value;
    }

    /**
     * Obtiene el valor de la propiedad listaACancelar.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getListaACancelar() {
        return listaACancelar;
    }

    /**
     * Define el valor de la propiedad listaACancelar.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setListaACancelar(ArrayOfString value) {
        this.listaACancelar = value;
    }

}
