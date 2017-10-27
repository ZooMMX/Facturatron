
package com.finkok.stamp;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.finkok.stamp package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _StampResponse_QNAME = new QName("http://facturacion.finkok.com/stamp", "stampResponse");
    private final static QName _AcuseRecepcionCFDI_QNAME = new QName("apps.services.soap.core.views", "AcuseRecepcionCFDI");
    private final static QName _StampedResponse_QNAME = new QName("http://facturacion.finkok.com/stamp", "stampedResponse");
    private final static QName _QueryPending_QNAME = new QName("http://facturacion.finkok.com/stamp", "query_pending");
    private final static QName _Incidencia_QNAME = new QName("apps.services.soap.core.views", "Incidencia");
    private final static QName _Stamp_QNAME = new QName("http://facturacion.finkok.com/stamp", "stamp");
    private final static QName _Stamped_QNAME = new QName("http://facturacion.finkok.com/stamp", "stamped");
    private final static QName _SignStampResponse_QNAME = new QName("http://facturacion.finkok.com/stamp", "sign_stampResponse");
    private final static QName _QueryPendingResponse_QNAME = new QName("http://facturacion.finkok.com/stamp", "query_pendingResponse");
    private final static QName _QueryPendingResult_QNAME = new QName("apps.services.soap.core.views", "QueryPendingResult");
    private final static QName _SignStamp_QNAME = new QName("http://facturacion.finkok.com/stamp", "sign_stamp");
    private final static QName _IncidenciaArray_QNAME = new QName("apps.services.soap.core.views", "IncidenciaArray");
    private final static QName _QuickStamp_QNAME = new QName("http://facturacion.finkok.com/stamp", "quick_stamp");
    private final static QName _QuickStampResponse_QNAME = new QName("http://facturacion.finkok.com/stamp", "quick_stampResponse");
    private final static QName _SignStampUsername_QNAME = new QName("http://facturacion.finkok.com/stamp", "username");
    private final static QName _SignStampXml_QNAME = new QName("http://facturacion.finkok.com/stamp", "xml");
    private final static QName _SignStampPassword_QNAME = new QName("http://facturacion.finkok.com/stamp", "password");
    private final static QName _StampResponseStampResult_QNAME = new QName("http://facturacion.finkok.com/stamp", "stampResult");
    private final static QName _QueryPendingResponseQueryPendingResult_QNAME = new QName("http://facturacion.finkok.com/stamp", "query_pendingResult");
    private final static QName _IncidenciaRfcEmisor_QNAME = new QName("apps.services.soap.core.views", "RfcEmisor");
    private final static QName _IncidenciaCodigoError_QNAME = new QName("apps.services.soap.core.views", "CodigoError");
    private final static QName _IncidenciaExtraInfo_QNAME = new QName("apps.services.soap.core.views", "ExtraInfo");
    private final static QName _IncidenciaWorkProcessId_QNAME = new QName("apps.services.soap.core.views", "WorkProcessId");
    private final static QName _IncidenciaUuid_QNAME = new QName("apps.services.soap.core.views", "Uuid");
    private final static QName _IncidenciaNoCertificadoPac_QNAME = new QName("apps.services.soap.core.views", "NoCertificadoPac");
    private final static QName _IncidenciaFechaRegistro_QNAME = new QName("apps.services.soap.core.views", "FechaRegistro");
    private final static QName _IncidenciaIdIncidencia_QNAME = new QName("apps.services.soap.core.views", "IdIncidencia");
    private final static QName _IncidenciaMensajeIncidencia_QNAME = new QName("apps.services.soap.core.views", "MensajeIncidencia");
    private final static QName _StampedResponseStampedResult_QNAME = new QName("http://facturacion.finkok.com/stamp", "stampedResult");
    private final static QName _AcuseRecepcionCFDICodEstatus_QNAME = new QName("apps.services.soap.core.views", "CodEstatus");
    private final static QName _AcuseRecepcionCFDIUUID_QNAME = new QName("apps.services.soap.core.views", "UUID");
    private final static QName _AcuseRecepcionCFDIFaultstring_QNAME = new QName("apps.services.soap.core.views", "faultstring");
    private final static QName _AcuseRecepcionCFDIFaultcode_QNAME = new QName("apps.services.soap.core.views", "faultcode");
    private final static QName _AcuseRecepcionCFDIFecha_QNAME = new QName("apps.services.soap.core.views", "Fecha");
    private final static QName _AcuseRecepcionCFDIIncidencias_QNAME = new QName("apps.services.soap.core.views", "Incidencias");
    private final static QName _AcuseRecepcionCFDINoCertificadoSAT_QNAME = new QName("apps.services.soap.core.views", "NoCertificadoSAT");
    private final static QName _AcuseRecepcionCFDIXml_QNAME = new QName("apps.services.soap.core.views", "xml");
    private final static QName _AcuseRecepcionCFDISatSeal_QNAME = new QName("apps.services.soap.core.views", "SatSeal");
    private final static QName _SignStampResponseSignStampResult_QNAME = new QName("http://facturacion.finkok.com/stamp", "sign_stampResult");
    private final static QName _QueryPendingUuid_QNAME = new QName("http://facturacion.finkok.com/stamp", "uuid");
    private final static QName _QueryPendingResultUuid_QNAME = new QName("apps.services.soap.core.views", "uuid");
    private final static QName _QueryPendingResultUuidStatus_QNAME = new QName("apps.services.soap.core.views", "uuid_status");
    private final static QName _QueryPendingResultError_QNAME = new QName("apps.services.soap.core.views", "error");
    private final static QName _QueryPendingResultStatus_QNAME = new QName("apps.services.soap.core.views", "status");
    private final static QName _QueryPendingResultAttempts_QNAME = new QName("apps.services.soap.core.views", "attempts");
    private final static QName _QueryPendingResultNextAttempt_QNAME = new QName("apps.services.soap.core.views", "next_attempt");
    private final static QName _QueryPendingResultDate_QNAME = new QName("apps.services.soap.core.views", "date");
    private final static QName _QuickStampResponseQuickStampResult_QNAME = new QName("http://facturacion.finkok.com/stamp", "quick_stampResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.finkok.stamp
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link QuickStampResponse }
     * 
     */
    public QuickStampResponse createQuickStampResponse() {
        return new QuickStampResponse();
    }

    /**
     * Create an instance of {@link SignStampResponse }
     * 
     */
    public SignStampResponse createSignStampResponse() {
        return new SignStampResponse();
    }

    /**
     * Create an instance of {@link Stamp }
     * 
     */
    public Stamp createStamp() {
        return new Stamp();
    }

    /**
     * Create an instance of {@link Stamped }
     * 
     */
    public Stamped createStamped() {
        return new Stamped();
    }

    /**
     * Create an instance of {@link QuickStamp }
     * 
     */
    public QuickStamp createQuickStamp() {
        return new QuickStamp();
    }

    /**
     * Create an instance of {@link SignStamp }
     * 
     */
    public SignStamp createSignStamp() {
        return new SignStamp();
    }

    /**
     * Create an instance of {@link StampResponse }
     * 
     */
    public StampResponse createStampResponse() {
        return new StampResponse();
    }

    /**
     * Create an instance of {@link QueryPendingResponse }
     * 
     */
    public QueryPendingResponse createQueryPendingResponse() {
        return new QueryPendingResponse();
    }

    /**
     * Create an instance of {@link StampedResponse }
     * 
     */
    public StampedResponse createStampedResponse() {
        return new StampedResponse();
    }

    /**
     * Create an instance of {@link QueryPending }
     * 
     */
    public QueryPending createQueryPending() {
        return new QueryPending();
    }

    /**
     * Create an instance of {@link QueryPendingResult }
     * 
     */
    public QueryPendingResult createQueryPendingResult() {
        return new QueryPendingResult();
    }

    /**
     * Create an instance of {@link AcuseRecepcionCFDI }
     * 
     */
    public AcuseRecepcionCFDI createAcuseRecepcionCFDI() {
        return new AcuseRecepcionCFDI();
    }

    /**
     * Create an instance of {@link Incidencia }
     * 
     */
    public Incidencia createIncidencia() {
        return new Incidencia();
    }

    /**
     * Create an instance of {@link IncidenciaArray }
     * 
     */
    public IncidenciaArray createIncidenciaArray() {
        return new IncidenciaArray();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StampResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "stampResponse")
    public JAXBElement<StampResponse> createStampResponse(StampResponse value) {
        return new JAXBElement<StampResponse>(_StampResponse_QNAME, StampResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcuseRecepcionCFDI }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "AcuseRecepcionCFDI")
    public JAXBElement<AcuseRecepcionCFDI> createAcuseRecepcionCFDI(AcuseRecepcionCFDI value) {
        return new JAXBElement<AcuseRecepcionCFDI>(_AcuseRecepcionCFDI_QNAME, AcuseRecepcionCFDI.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StampedResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "stampedResponse")
    public JAXBElement<StampedResponse> createStampedResponse(StampedResponse value) {
        return new JAXBElement<StampedResponse>(_StampedResponse_QNAME, StampedResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryPending }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "query_pending")
    public JAXBElement<QueryPending> createQueryPending(QueryPending value) {
        return new JAXBElement<QueryPending>(_QueryPending_QNAME, QueryPending.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Incidencia }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "Incidencia")
    public JAXBElement<Incidencia> createIncidencia(Incidencia value) {
        return new JAXBElement<Incidencia>(_Incidencia_QNAME, Incidencia.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Stamp }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "stamp")
    public JAXBElement<Stamp> createStamp(Stamp value) {
        return new JAXBElement<Stamp>(_Stamp_QNAME, Stamp.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Stamped }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "stamped")
    public JAXBElement<Stamped> createStamped(Stamped value) {
        return new JAXBElement<Stamped>(_Stamped_QNAME, Stamped.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignStampResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "sign_stampResponse")
    public JAXBElement<SignStampResponse> createSignStampResponse(SignStampResponse value) {
        return new JAXBElement<SignStampResponse>(_SignStampResponse_QNAME, SignStampResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryPendingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "query_pendingResponse")
    public JAXBElement<QueryPendingResponse> createQueryPendingResponse(QueryPendingResponse value) {
        return new JAXBElement<QueryPendingResponse>(_QueryPendingResponse_QNAME, QueryPendingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryPendingResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "QueryPendingResult")
    public JAXBElement<QueryPendingResult> createQueryPendingResult(QueryPendingResult value) {
        return new JAXBElement<QueryPendingResult>(_QueryPendingResult_QNAME, QueryPendingResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignStamp }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "sign_stamp")
    public JAXBElement<SignStamp> createSignStamp(SignStamp value) {
        return new JAXBElement<SignStamp>(_SignStamp_QNAME, SignStamp.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IncidenciaArray }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "IncidenciaArray")
    public JAXBElement<IncidenciaArray> createIncidenciaArray(IncidenciaArray value) {
        return new JAXBElement<IncidenciaArray>(_IncidenciaArray_QNAME, IncidenciaArray.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QuickStamp }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "quick_stamp")
    public JAXBElement<QuickStamp> createQuickStamp(QuickStamp value) {
        return new JAXBElement<QuickStamp>(_QuickStamp_QNAME, QuickStamp.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QuickStampResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "quick_stampResponse")
    public JAXBElement<QuickStampResponse> createQuickStampResponse(QuickStampResponse value) {
        return new JAXBElement<QuickStampResponse>(_QuickStampResponse_QNAME, QuickStampResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "username", scope = SignStamp.class)
    public JAXBElement<String> createSignStampUsername(String value) {
        return new JAXBElement<String>(_SignStampUsername_QNAME, String.class, SignStamp.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "xml", scope = SignStamp.class)
    public JAXBElement<byte[]> createSignStampXml(byte[] value) {
        return new JAXBElement<byte[]>(_SignStampXml_QNAME, byte[].class, SignStamp.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "password", scope = SignStamp.class)
    public JAXBElement<String> createSignStampPassword(String value) {
        return new JAXBElement<String>(_SignStampPassword_QNAME, String.class, SignStamp.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "username", scope = Stamp.class)
    public JAXBElement<String> createStampUsername(String value) {
        return new JAXBElement<String>(_SignStampUsername_QNAME, String.class, Stamp.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "xml", scope = Stamp.class)
    public JAXBElement<byte[]> createStampXml(byte[] value) {
        return new JAXBElement<byte[]>(_SignStampXml_QNAME, byte[].class, Stamp.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "password", scope = Stamp.class)
    public JAXBElement<String> createStampPassword(String value) {
        return new JAXBElement<String>(_SignStampPassword_QNAME, String.class, Stamp.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcuseRecepcionCFDI }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "stampResult", scope = StampResponse.class)
    public JAXBElement<AcuseRecepcionCFDI> createStampResponseStampResult(AcuseRecepcionCFDI value) {
        return new JAXBElement<AcuseRecepcionCFDI>(_StampResponseStampResult_QNAME, AcuseRecepcionCFDI.class, StampResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryPendingResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "query_pendingResult", scope = QueryPendingResponse.class)
    public JAXBElement<QueryPendingResult> createQueryPendingResponseQueryPendingResult(QueryPendingResult value) {
        return new JAXBElement<QueryPendingResult>(_QueryPendingResponseQueryPendingResult_QNAME, QueryPendingResult.class, QueryPendingResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "RfcEmisor", scope = Incidencia.class)
    public JAXBElement<String> createIncidenciaRfcEmisor(String value) {
        return new JAXBElement<String>(_IncidenciaRfcEmisor_QNAME, String.class, Incidencia.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "CodigoError", scope = Incidencia.class)
    public JAXBElement<String> createIncidenciaCodigoError(String value) {
        return new JAXBElement<String>(_IncidenciaCodigoError_QNAME, String.class, Incidencia.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "ExtraInfo", scope = Incidencia.class)
    public JAXBElement<String> createIncidenciaExtraInfo(String value) {
        return new JAXBElement<String>(_IncidenciaExtraInfo_QNAME, String.class, Incidencia.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "WorkProcessId", scope = Incidencia.class)
    public JAXBElement<String> createIncidenciaWorkProcessId(String value) {
        return new JAXBElement<String>(_IncidenciaWorkProcessId_QNAME, String.class, Incidencia.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "Uuid", scope = Incidencia.class)
    public JAXBElement<String> createIncidenciaUuid(String value) {
        return new JAXBElement<String>(_IncidenciaUuid_QNAME, String.class, Incidencia.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "NoCertificadoPac", scope = Incidencia.class)
    public JAXBElement<String> createIncidenciaNoCertificadoPac(String value) {
        return new JAXBElement<String>(_IncidenciaNoCertificadoPac_QNAME, String.class, Incidencia.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "FechaRegistro", scope = Incidencia.class)
    public JAXBElement<String> createIncidenciaFechaRegistro(String value) {
        return new JAXBElement<String>(_IncidenciaFechaRegistro_QNAME, String.class, Incidencia.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "IdIncidencia", scope = Incidencia.class)
    public JAXBElement<String> createIncidenciaIdIncidencia(String value) {
        return new JAXBElement<String>(_IncidenciaIdIncidencia_QNAME, String.class, Incidencia.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "MensajeIncidencia", scope = Incidencia.class)
    public JAXBElement<String> createIncidenciaMensajeIncidencia(String value) {
        return new JAXBElement<String>(_IncidenciaMensajeIncidencia_QNAME, String.class, Incidencia.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "username", scope = Stamped.class)
    public JAXBElement<String> createStampedUsername(String value) {
        return new JAXBElement<String>(_SignStampUsername_QNAME, String.class, Stamped.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "xml", scope = Stamped.class)
    public JAXBElement<byte[]> createStampedXml(byte[] value) {
        return new JAXBElement<byte[]>(_SignStampXml_QNAME, byte[].class, Stamped.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "password", scope = Stamped.class)
    public JAXBElement<String> createStampedPassword(String value) {
        return new JAXBElement<String>(_SignStampPassword_QNAME, String.class, Stamped.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcuseRecepcionCFDI }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "stampedResult", scope = StampedResponse.class)
    public JAXBElement<AcuseRecepcionCFDI> createStampedResponseStampedResult(AcuseRecepcionCFDI value) {
        return new JAXBElement<AcuseRecepcionCFDI>(_StampedResponseStampedResult_QNAME, AcuseRecepcionCFDI.class, StampedResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "CodEstatus", scope = AcuseRecepcionCFDI.class)
    public JAXBElement<String> createAcuseRecepcionCFDICodEstatus(String value) {
        return new JAXBElement<String>(_AcuseRecepcionCFDICodEstatus_QNAME, String.class, AcuseRecepcionCFDI.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "UUID", scope = AcuseRecepcionCFDI.class)
    public JAXBElement<String> createAcuseRecepcionCFDIUUID(String value) {
        return new JAXBElement<String>(_AcuseRecepcionCFDIUUID_QNAME, String.class, AcuseRecepcionCFDI.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "faultstring", scope = AcuseRecepcionCFDI.class)
    public JAXBElement<String> createAcuseRecepcionCFDIFaultstring(String value) {
        return new JAXBElement<String>(_AcuseRecepcionCFDIFaultstring_QNAME, String.class, AcuseRecepcionCFDI.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "faultcode", scope = AcuseRecepcionCFDI.class)
    public JAXBElement<String> createAcuseRecepcionCFDIFaultcode(String value) {
        return new JAXBElement<String>(_AcuseRecepcionCFDIFaultcode_QNAME, String.class, AcuseRecepcionCFDI.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "Fecha", scope = AcuseRecepcionCFDI.class)
    public JAXBElement<String> createAcuseRecepcionCFDIFecha(String value) {
        return new JAXBElement<String>(_AcuseRecepcionCFDIFecha_QNAME, String.class, AcuseRecepcionCFDI.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IncidenciaArray }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "Incidencias", scope = AcuseRecepcionCFDI.class)
    public JAXBElement<IncidenciaArray> createAcuseRecepcionCFDIIncidencias(IncidenciaArray value) {
        return new JAXBElement<IncidenciaArray>(_AcuseRecepcionCFDIIncidencias_QNAME, IncidenciaArray.class, AcuseRecepcionCFDI.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "NoCertificadoSAT", scope = AcuseRecepcionCFDI.class)
    public JAXBElement<String> createAcuseRecepcionCFDINoCertificadoSAT(String value) {
        return new JAXBElement<String>(_AcuseRecepcionCFDINoCertificadoSAT_QNAME, String.class, AcuseRecepcionCFDI.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "xml", scope = AcuseRecepcionCFDI.class)
    public JAXBElement<String> createAcuseRecepcionCFDIXml(String value) {
        return new JAXBElement<String>(_AcuseRecepcionCFDIXml_QNAME, String.class, AcuseRecepcionCFDI.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "SatSeal", scope = AcuseRecepcionCFDI.class)
    public JAXBElement<String> createAcuseRecepcionCFDISatSeal(String value) {
        return new JAXBElement<String>(_AcuseRecepcionCFDISatSeal_QNAME, String.class, AcuseRecepcionCFDI.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcuseRecepcionCFDI }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "sign_stampResult", scope = SignStampResponse.class)
    public JAXBElement<AcuseRecepcionCFDI> createSignStampResponseSignStampResult(AcuseRecepcionCFDI value) {
        return new JAXBElement<AcuseRecepcionCFDI>(_SignStampResponseSignStampResult_QNAME, AcuseRecepcionCFDI.class, SignStampResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "uuid", scope = QueryPending.class)
    public JAXBElement<String> createQueryPendingUuid(String value) {
        return new JAXBElement<String>(_QueryPendingUuid_QNAME, String.class, QueryPending.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "username", scope = QueryPending.class)
    public JAXBElement<String> createQueryPendingUsername(String value) {
        return new JAXBElement<String>(_SignStampUsername_QNAME, String.class, QueryPending.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "password", scope = QueryPending.class)
    public JAXBElement<String> createQueryPendingPassword(String value) {
        return new JAXBElement<String>(_SignStampPassword_QNAME, String.class, QueryPending.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "username", scope = QuickStamp.class)
    public JAXBElement<String> createQuickStampUsername(String value) {
        return new JAXBElement<String>(_SignStampUsername_QNAME, String.class, QuickStamp.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "xml", scope = QuickStamp.class)
    public JAXBElement<byte[]> createQuickStampXml(byte[] value) {
        return new JAXBElement<byte[]>(_SignStampXml_QNAME, byte[].class, QuickStamp.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "password", scope = QuickStamp.class)
    public JAXBElement<String> createQuickStampPassword(String value) {
        return new JAXBElement<String>(_SignStampPassword_QNAME, String.class, QuickStamp.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "uuid", scope = QueryPendingResult.class)
    public JAXBElement<String> createQueryPendingResultUuid(String value) {
        return new JAXBElement<String>(_QueryPendingResultUuid_QNAME, String.class, QueryPendingResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "uuid_status", scope = QueryPendingResult.class)
    public JAXBElement<String> createQueryPendingResultUuidStatus(String value) {
        return new JAXBElement<String>(_QueryPendingResultUuidStatus_QNAME, String.class, QueryPendingResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "error", scope = QueryPendingResult.class)
    public JAXBElement<String> createQueryPendingResultError(String value) {
        return new JAXBElement<String>(_QueryPendingResultError_QNAME, String.class, QueryPendingResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "status", scope = QueryPendingResult.class)
    public JAXBElement<String> createQueryPendingResultStatus(String value) {
        return new JAXBElement<String>(_QueryPendingResultStatus_QNAME, String.class, QueryPendingResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "attempts", scope = QueryPendingResult.class)
    public JAXBElement<String> createQueryPendingResultAttempts(String value) {
        return new JAXBElement<String>(_QueryPendingResultAttempts_QNAME, String.class, QueryPendingResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "next_attempt", scope = QueryPendingResult.class)
    public JAXBElement<String> createQueryPendingResultNextAttempt(String value) {
        return new JAXBElement<String>(_QueryPendingResultNextAttempt_QNAME, String.class, QueryPendingResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "date", scope = QueryPendingResult.class)
    public JAXBElement<String> createQueryPendingResultDate(String value) {
        return new JAXBElement<String>(_QueryPendingResultDate_QNAME, String.class, QueryPendingResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "xml", scope = QueryPendingResult.class)
    public JAXBElement<String> createQueryPendingResultXml(String value) {
        return new JAXBElement<String>(_AcuseRecepcionCFDIXml_QNAME, String.class, QueryPendingResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcuseRecepcionCFDI }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/stamp", name = "quick_stampResult", scope = QuickStampResponse.class)
    public JAXBElement<AcuseRecepcionCFDI> createQuickStampResponseQuickStampResult(AcuseRecepcionCFDI value) {
        return new JAXBElement<AcuseRecepcionCFDI>(_QuickStampResponseQuickStampResult_QNAME, AcuseRecepcionCFDI.class, QuickStampResponse.class, value);
    }

}
