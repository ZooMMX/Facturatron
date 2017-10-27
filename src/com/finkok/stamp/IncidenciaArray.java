
package com.finkok.stamp;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para IncidenciaArray complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="IncidenciaArray">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Incidencia" type="{apps.services.soap.core.views}Incidencia" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IncidenciaArray", namespace = "apps.services.soap.core.views", propOrder = {
    "incidencia"
})
public class IncidenciaArray {

    @XmlElement(name = "Incidencia", nillable = true)
    protected List<Incidencia> incidencia;

    /**
     * Gets the value of the incidencia property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the incidencia property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIncidencia().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Incidencia }
     * 
     * 
     */
    public List<Incidencia> getIncidencia() {
        if (incidencia == null) {
            incidencia = new ArrayList<Incidencia>();
        }
        return this.incidencia;
    }

}
