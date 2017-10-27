
package com.finkok.cancel;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para FolioArray complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="FolioArray">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Folio" type="{apps.services.soap.core.views}Folio" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FolioArray", namespace = "apps.services.soap.core.views", propOrder = {
    "folio"
})
public class FolioArray {

    @XmlElement(name = "Folio", nillable = true)
    protected List<Folio> folio;

    /**
     * Gets the value of the folio property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the folio property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFolio().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Folio }
     * 
     * 
     */
    public List<Folio> getFolio() {
        if (folio == null) {
            folio = new ArrayList<Folio>();
        }
        return this.folio;
    }

}
