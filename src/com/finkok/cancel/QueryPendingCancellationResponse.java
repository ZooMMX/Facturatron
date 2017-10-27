
package com.finkok.cancel;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para query_pending_cancellationResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="query_pending_cancellationResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="query_pending_cancellationResult" type="{apps.services.soap.core.views}QueryPendingResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "query_pending_cancellationResponse", propOrder = {
    "queryPendingCancellationResult"
})
public class QueryPendingCancellationResponse {

    @XmlElementRef(name = "query_pending_cancellationResult", namespace = "http://facturacion.finkok.com/cancel", type = JAXBElement.class, required = false)
    protected JAXBElement<QueryPendingResult> queryPendingCancellationResult;

    /**
     * Obtiene el valor de la propiedad queryPendingCancellationResult.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link QueryPendingResult }{@code >}
     *     
     */
    public JAXBElement<QueryPendingResult> getQueryPendingCancellationResult() {
        return queryPendingCancellationResult;
    }

    /**
     * Define el valor de la propiedad queryPendingCancellationResult.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link QueryPendingResult }{@code >}
     *     
     */
    public void setQueryPendingCancellationResult(JAXBElement<QueryPendingResult> value) {
        this.queryPendingCancellationResult = value;
    }

}
