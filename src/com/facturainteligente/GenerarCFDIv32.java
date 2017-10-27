
package com.facturainteligente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="datosReceptor" type="{https://www.appfacturainteligente.com/ConexionRemotaCFDI32}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="datosCFDI" type="{https://www.appfacturainteligente.com/ConexionRemotaCFDI32}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="etiquetas" type="{https://www.appfacturainteligente.com/ConexionRemotaCFDI32}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="conceptos" type="{https://www.appfacturainteligente.com/ConexionRemotaCFDI32}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="informacionAduanera" type="{https://www.appfacturainteligente.com/ConexionRemotaCFDI32}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="retenciones" type="{https://www.appfacturainteligente.com/ConexionRemotaCFDI32}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="traslados" type="{https://www.appfacturainteligente.com/ConexionRemotaCFDI32}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="retencionesLocales" type="{https://www.appfacturainteligente.com/ConexionRemotaCFDI32}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="trasladosLocales" type="{https://www.appfacturainteligente.com/ConexionRemotaCFDI32}ArrayOfString" minOccurs="0"/>
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
    "datosReceptor",
    "datosCFDI",
    "etiquetas",
    "conceptos",
    "informacionAduanera",
    "retenciones",
    "traslados",
    "retencionesLocales",
    "trasladosLocales"
})
@XmlRootElement(name = "GenerarCFDIv32")
public class GenerarCFDIv32 {

    protected ArrayOfString datosUsuario;
    protected ArrayOfString datosReceptor;
    protected ArrayOfString datosCFDI;
    protected ArrayOfString etiquetas;
    protected ArrayOfString conceptos;
    protected ArrayOfString informacionAduanera;
    protected ArrayOfString retenciones;
    protected ArrayOfString traslados;
    protected ArrayOfString retencionesLocales;
    protected ArrayOfString trasladosLocales;

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
     * Obtiene el valor de la propiedad datosReceptor.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getDatosReceptor() {
        return datosReceptor;
    }

    /**
     * Define el valor de la propiedad datosReceptor.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setDatosReceptor(ArrayOfString value) {
        this.datosReceptor = value;
    }

    /**
     * Obtiene el valor de la propiedad datosCFDI.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getDatosCFDI() {
        return datosCFDI;
    }

    /**
     * Define el valor de la propiedad datosCFDI.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setDatosCFDI(ArrayOfString value) {
        this.datosCFDI = value;
    }

    /**
     * Obtiene el valor de la propiedad etiquetas.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getEtiquetas() {
        return etiquetas;
    }

    /**
     * Define el valor de la propiedad etiquetas.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setEtiquetas(ArrayOfString value) {
        this.etiquetas = value;
    }

    /**
     * Obtiene el valor de la propiedad conceptos.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getConceptos() {
        return conceptos;
    }

    /**
     * Define el valor de la propiedad conceptos.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setConceptos(ArrayOfString value) {
        this.conceptos = value;
    }

    /**
     * Obtiene el valor de la propiedad informacionAduanera.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getInformacionAduanera() {
        return informacionAduanera;
    }

    /**
     * Define el valor de la propiedad informacionAduanera.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setInformacionAduanera(ArrayOfString value) {
        this.informacionAduanera = value;
    }

    /**
     * Obtiene el valor de la propiedad retenciones.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getRetenciones() {
        return retenciones;
    }

    /**
     * Define el valor de la propiedad retenciones.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setRetenciones(ArrayOfString value) {
        this.retenciones = value;
    }

    /**
     * Obtiene el valor de la propiedad traslados.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getTraslados() {
        return traslados;
    }

    /**
     * Define el valor de la propiedad traslados.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setTraslados(ArrayOfString value) {
        this.traslados = value;
    }

    /**
     * Obtiene el valor de la propiedad retencionesLocales.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getRetencionesLocales() {
        return retencionesLocales;
    }

    /**
     * Define el valor de la propiedad retencionesLocales.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setRetencionesLocales(ArrayOfString value) {
        this.retencionesLocales = value;
    }

    /**
     * Obtiene el valor de la propiedad trasladosLocales.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getTrasladosLocales() {
        return trasladosLocales;
    }

    /**
     * Define el valor de la propiedad trasladosLocales.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setTrasladosLocales(ArrayOfString value) {
        this.trasladosLocales = value;
    }

}
