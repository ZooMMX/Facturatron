
package com.finkok.cancel;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.finkok.cancel package. 
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

    private final static QName _OutCancelResponse_QNAME = new QName("http://facturacion.finkok.com/cancel", "out_cancelResponse");
    private final static QName _StringArray_QNAME = new QName("http://facturacion.finkok.com/cancellation", "stringArray");
    private final static QName _FolioArray_QNAME = new QName("apps.services.soap.core.views", "FolioArray");
    private final static QName _QueryPendingCancellation_QNAME = new QName("http://facturacion.finkok.com/cancel", "query_pending_cancellation");
    private final static QName _CancelSignature_QNAME = new QName("http://facturacion.finkok.com/cancel", "cancel_signature");
    private final static QName _Cancel_QNAME = new QName("http://facturacion.finkok.com/cancel", "cancel");
    private final static QName _CancelResponse_QNAME = new QName("http://facturacion.finkok.com/cancel", "cancelResponse");
    private final static QName _CancelaCFDResult_QNAME = new QName("apps.services.soap.core.views", "CancelaCFDResult");
    private final static QName _QueryPendingResult_QNAME = new QName("apps.services.soap.core.views", "QueryPendingResult");
    private final static QName _QueryPendingCancellationResponse_QNAME = new QName("http://facturacion.finkok.com/cancel", "query_pending_cancellationResponse");
    private final static QName _GetReceipt_QNAME = new QName("http://facturacion.finkok.com/cancel", "get_receipt");
    private final static QName _GetReceiptResponse_QNAME = new QName("http://facturacion.finkok.com/cancel", "get_receiptResponse");
    private final static QName _UUIDS_QNAME = new QName("apps.services.soap.core.views", "UUIDS");
    private final static QName _OutCancel_QNAME = new QName("http://facturacion.finkok.com/cancel", "out_cancel");
    private final static QName _SignCancelResponse_QNAME = new QName("http://facturacion.finkok.com/cancel", "sign_cancelResponse");
    private final static QName _Folio_QNAME = new QName("apps.services.soap.core.views", "Folio");
    private final static QName _CancelSignatureResponse_QNAME = new QName("http://facturacion.finkok.com/cancel", "cancel_signatureResponse");
    private final static QName _SignCancel_QNAME = new QName("http://facturacion.finkok.com/cancel", "sign_cancel");
    private final static QName _ReceiptResult_QNAME = new QName("apps.services.soap.core.views", "ReceiptResult");
    private final static QName _OutCancelKey_QNAME = new QName("http://facturacion.finkok.com/cancel", "key");
    private final static QName _OutCancelUsername_QNAME = new QName("http://facturacion.finkok.com/cancel", "username");
    private final static QName _OutCancelXml_QNAME = new QName("http://facturacion.finkok.com/cancel", "xml");
    private final static QName _OutCancelTaxpayerId_QNAME = new QName("http://facturacion.finkok.com/cancel", "taxpayer_id");
    private final static QName _OutCancelCer_QNAME = new QName("http://facturacion.finkok.com/cancel", "cer");
    private final static QName _OutCancelStorePending_QNAME = new QName("http://facturacion.finkok.com/cancel", "store_pending");
    private final static QName _OutCancelPassword_QNAME = new QName("http://facturacion.finkok.com/cancel", "password");
    private final static QName _SignCancelResponseSignCancelResult_QNAME = new QName("http://facturacion.finkok.com/cancel", "sign_cancelResult");
    private final static QName _QueryPendingResultUuid_QNAME = new QName("apps.services.soap.core.views", "uuid");
    private final static QName _QueryPendingResultUuidStatus_QNAME = new QName("apps.services.soap.core.views", "uuid_status");
    private final static QName _QueryPendingResultError_QNAME = new QName("apps.services.soap.core.views", "error");
    private final static QName _QueryPendingResultStatus_QNAME = new QName("apps.services.soap.core.views", "status");
    private final static QName _QueryPendingResultAttempts_QNAME = new QName("apps.services.soap.core.views", "attempts");
    private final static QName _QueryPendingResultNextAttempt_QNAME = new QName("apps.services.soap.core.views", "next_attempt");
    private final static QName _QueryPendingResultDate_QNAME = new QName("apps.services.soap.core.views", "date");
    private final static QName _QueryPendingResultXml_QNAME = new QName("apps.services.soap.core.views", "xml");
    private final static QName _CancelUUIDS_QNAME = new QName("http://facturacion.finkok.com/cancel", "UUIDS");
    private final static QName _CancelResponseCancelResult_QNAME = new QName("http://facturacion.finkok.com/cancel", "cancelResult");
    private final static QName _GetReceiptResponseGetReceiptResult_QNAME = new QName("http://facturacion.finkok.com/cancel", "get_receiptResult");
    private final static QName _QueryPendingCancellationUuid_QNAME = new QName("http://facturacion.finkok.com/cancel", "uuid");
    private final static QName _OutCancelResponseOutCancelResult_QNAME = new QName("http://facturacion.finkok.com/cancel", "out_cancelResult");
    private final static QName _ReceiptResultReceipt_QNAME = new QName("apps.services.soap.core.views", "receipt");
    private final static QName _ReceiptResultTaxpayerId_QNAME = new QName("apps.services.soap.core.views", "taxpayer_id");
    private final static QName _ReceiptResultSuccess_QNAME = new QName("apps.services.soap.core.views", "success");
    private final static QName _UUIDSUuids_QNAME = new QName("apps.services.soap.core.views", "uuids");
    private final static QName _FolioUUID_QNAME = new QName("apps.services.soap.core.views", "UUID");
    private final static QName _FolioEstatusUUID_QNAME = new QName("apps.services.soap.core.views", "EstatusUUID");
    private final static QName _SignCancelSerial_QNAME = new QName("http://facturacion.finkok.com/cancel", "serial");
    private final static QName _CancelSignatureResponseCancelSignatureResult_QNAME = new QName("http://facturacion.finkok.com/cancel", "cancel_signatureResult");
    private final static QName _CancelaCFDResultCodEstatus_QNAME = new QName("apps.services.soap.core.views", "CodEstatus");
    private final static QName _CancelaCFDResultFolios_QNAME = new QName("apps.services.soap.core.views", "Folios");
    private final static QName _CancelaCFDResultRfcEmisor_QNAME = new QName("apps.services.soap.core.views", "RfcEmisor");
    private final static QName _CancelaCFDResultFecha_QNAME = new QName("apps.services.soap.core.views", "Fecha");
    private final static QName _CancelaCFDResultAcuse_QNAME = new QName("apps.services.soap.core.views", "Acuse");
    private final static QName _QueryPendingCancellationResponseQueryPendingCancellationResult_QNAME = new QName("http://facturacion.finkok.com/cancel", "query_pending_cancellationResult");
    private final static QName _GetReceiptType_QNAME = new QName("http://facturacion.finkok.com/cancel", "type");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.finkok.cancel
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Cancel }
     * 
     */
    public Cancel createCancel() {
        return new Cancel();
    }

    /**
     * Create an instance of {@link SignCancelResponse }
     * 
     */
    public SignCancelResponse createSignCancelResponse() {
        return new SignCancelResponse();
    }

    /**
     * Create an instance of {@link CancelResponse }
     * 
     */
    public CancelResponse createCancelResponse() {
        return new CancelResponse();
    }

    /**
     * Create an instance of {@link CancelSignatureResponse }
     * 
     */
    public CancelSignatureResponse createCancelSignatureResponse() {
        return new CancelSignatureResponse();
    }

    /**
     * Create an instance of {@link SignCancel }
     * 
     */
    public SignCancel createSignCancel() {
        return new SignCancel();
    }

    /**
     * Create an instance of {@link QueryPendingCancellation }
     * 
     */
    public QueryPendingCancellation createQueryPendingCancellation() {
        return new QueryPendingCancellation();
    }

    /**
     * Create an instance of {@link CancelSignature }
     * 
     */
    public CancelSignature createCancelSignature() {
        return new CancelSignature();
    }

    /**
     * Create an instance of {@link OutCancel }
     * 
     */
    public OutCancel createOutCancel() {
        return new OutCancel();
    }

    /**
     * Create an instance of {@link QueryPendingCancellationResponse }
     * 
     */
    public QueryPendingCancellationResponse createQueryPendingCancellationResponse() {
        return new QueryPendingCancellationResponse();
    }

    /**
     * Create an instance of {@link GetReceipt }
     * 
     */
    public GetReceipt createGetReceipt() {
        return new GetReceipt();
    }

    /**
     * Create an instance of {@link GetReceiptResponse }
     * 
     */
    public GetReceiptResponse createGetReceiptResponse() {
        return new GetReceiptResponse();
    }

    /**
     * Create an instance of {@link OutCancelResponse }
     * 
     */
    public OutCancelResponse createOutCancelResponse() {
        return new OutCancelResponse();
    }

    /**
     * Create an instance of {@link FolioArray }
     * 
     */
    public FolioArray createFolioArray() {
        return new FolioArray();
    }

    /**
     * Create an instance of {@link CancelaCFDResult }
     * 
     */
    public CancelaCFDResult createCancelaCFDResult() {
        return new CancelaCFDResult();
    }

    /**
     * Create an instance of {@link ReceiptResult }
     * 
     */
    public ReceiptResult createReceiptResult() {
        return new ReceiptResult();
    }

    /**
     * Create an instance of {@link Folio }
     * 
     */
    public Folio createFolio() {
        return new Folio();
    }

    /**
     * Create an instance of {@link QueryPendingResult }
     * 
     */
    public QueryPendingResult createQueryPendingResult() {
        return new QueryPendingResult();
    }

    /**
     * Create an instance of {@link UUIDS }
     * 
     */
    public UUIDS createUUIDS() {
        return new UUIDS();
    }

    /**
     * Create an instance of {@link StringArray }
     * 
     */
    public StringArray createStringArray() {
        return new StringArray();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OutCancelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "out_cancelResponse")
    public JAXBElement<OutCancelResponse> createOutCancelResponse(OutCancelResponse value) {
        return new JAXBElement<OutCancelResponse>(_OutCancelResponse_QNAME, OutCancelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StringArray }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancellation", name = "stringArray")
    public JAXBElement<StringArray> createStringArray(StringArray value) {
        return new JAXBElement<StringArray>(_StringArray_QNAME, StringArray.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FolioArray }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "FolioArray")
    public JAXBElement<FolioArray> createFolioArray(FolioArray value) {
        return new JAXBElement<FolioArray>(_FolioArray_QNAME, FolioArray.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryPendingCancellation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "query_pending_cancellation")
    public JAXBElement<QueryPendingCancellation> createQueryPendingCancellation(QueryPendingCancellation value) {
        return new JAXBElement<QueryPendingCancellation>(_QueryPendingCancellation_QNAME, QueryPendingCancellation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelSignature }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "cancel_signature")
    public JAXBElement<CancelSignature> createCancelSignature(CancelSignature value) {
        return new JAXBElement<CancelSignature>(_CancelSignature_QNAME, CancelSignature.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Cancel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "cancel")
    public JAXBElement<Cancel> createCancel(Cancel value) {
        return new JAXBElement<Cancel>(_Cancel_QNAME, Cancel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "cancelResponse")
    public JAXBElement<CancelResponse> createCancelResponse(CancelResponse value) {
        return new JAXBElement<CancelResponse>(_CancelResponse_QNAME, CancelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelaCFDResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "CancelaCFDResult")
    public JAXBElement<CancelaCFDResult> createCancelaCFDResult(CancelaCFDResult value) {
        return new JAXBElement<CancelaCFDResult>(_CancelaCFDResult_QNAME, CancelaCFDResult.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryPendingCancellationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "query_pending_cancellationResponse")
    public JAXBElement<QueryPendingCancellationResponse> createQueryPendingCancellationResponse(QueryPendingCancellationResponse value) {
        return new JAXBElement<QueryPendingCancellationResponse>(_QueryPendingCancellationResponse_QNAME, QueryPendingCancellationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetReceipt }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "get_receipt")
    public JAXBElement<GetReceipt> createGetReceipt(GetReceipt value) {
        return new JAXBElement<GetReceipt>(_GetReceipt_QNAME, GetReceipt.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetReceiptResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "get_receiptResponse")
    public JAXBElement<GetReceiptResponse> createGetReceiptResponse(GetReceiptResponse value) {
        return new JAXBElement<GetReceiptResponse>(_GetReceiptResponse_QNAME, GetReceiptResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UUIDS }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "UUIDS")
    public JAXBElement<UUIDS> createUUIDS(UUIDS value) {
        return new JAXBElement<UUIDS>(_UUIDS_QNAME, UUIDS.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OutCancel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "out_cancel")
    public JAXBElement<OutCancel> createOutCancel(OutCancel value) {
        return new JAXBElement<OutCancel>(_OutCancel_QNAME, OutCancel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignCancelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "sign_cancelResponse")
    public JAXBElement<SignCancelResponse> createSignCancelResponse(SignCancelResponse value) {
        return new JAXBElement<SignCancelResponse>(_SignCancelResponse_QNAME, SignCancelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Folio }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "Folio")
    public JAXBElement<Folio> createFolio(Folio value) {
        return new JAXBElement<Folio>(_Folio_QNAME, Folio.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelSignatureResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "cancel_signatureResponse")
    public JAXBElement<CancelSignatureResponse> createCancelSignatureResponse(CancelSignatureResponse value) {
        return new JAXBElement<CancelSignatureResponse>(_CancelSignatureResponse_QNAME, CancelSignatureResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignCancel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "sign_cancel")
    public JAXBElement<SignCancel> createSignCancel(SignCancel value) {
        return new JAXBElement<SignCancel>(_SignCancel_QNAME, SignCancel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReceiptResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "ReceiptResult")
    public JAXBElement<ReceiptResult> createReceiptResult(ReceiptResult value) {
        return new JAXBElement<ReceiptResult>(_ReceiptResult_QNAME, ReceiptResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "key", scope = OutCancel.class)
    public JAXBElement<byte[]> createOutCancelKey(byte[] value) {
        return new JAXBElement<byte[]>(_OutCancelKey_QNAME, byte[].class, OutCancel.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "username", scope = OutCancel.class)
    public JAXBElement<String> createOutCancelUsername(String value) {
        return new JAXBElement<String>(_OutCancelUsername_QNAME, String.class, OutCancel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "xml", scope = OutCancel.class)
    public JAXBElement<byte[]> createOutCancelXml(byte[] value) {
        return new JAXBElement<byte[]>(_OutCancelXml_QNAME, byte[].class, OutCancel.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "taxpayer_id", scope = OutCancel.class)
    public JAXBElement<String> createOutCancelTaxpayerId(String value) {
        return new JAXBElement<String>(_OutCancelTaxpayerId_QNAME, String.class, OutCancel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "cer", scope = OutCancel.class)
    public JAXBElement<byte[]> createOutCancelCer(byte[] value) {
        return new JAXBElement<byte[]>(_OutCancelCer_QNAME, byte[].class, OutCancel.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "store_pending", scope = OutCancel.class)
    public JAXBElement<Boolean> createOutCancelStorePending(Boolean value) {
        return new JAXBElement<Boolean>(_OutCancelStorePending_QNAME, Boolean.class, OutCancel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "password", scope = OutCancel.class)
    public JAXBElement<String> createOutCancelPassword(String value) {
        return new JAXBElement<String>(_OutCancelPassword_QNAME, String.class, OutCancel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelaCFDResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "sign_cancelResult", scope = SignCancelResponse.class)
    public JAXBElement<CancelaCFDResult> createSignCancelResponseSignCancelResult(CancelaCFDResult value) {
        return new JAXBElement<CancelaCFDResult>(_SignCancelResponseSignCancelResult_QNAME, CancelaCFDResult.class, SignCancelResponse.class, value);
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
        return new JAXBElement<String>(_QueryPendingResultXml_QNAME, String.class, QueryPendingResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UUIDS }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "UUIDS", scope = Cancel.class)
    public JAXBElement<UUIDS> createCancelUUIDS(UUIDS value) {
        return new JAXBElement<UUIDS>(_CancelUUIDS_QNAME, UUIDS.class, Cancel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "key", scope = Cancel.class)
    public JAXBElement<byte[]> createCancelKey(byte[] value) {
        return new JAXBElement<byte[]>(_OutCancelKey_QNAME, byte[].class, Cancel.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "username", scope = Cancel.class)
    public JAXBElement<String> createCancelUsername(String value) {
        return new JAXBElement<String>(_OutCancelUsername_QNAME, String.class, Cancel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "taxpayer_id", scope = Cancel.class)
    public JAXBElement<String> createCancelTaxpayerId(String value) {
        return new JAXBElement<String>(_OutCancelTaxpayerId_QNAME, String.class, Cancel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "cer", scope = Cancel.class)
    public JAXBElement<byte[]> createCancelCer(byte[] value) {
        return new JAXBElement<byte[]>(_OutCancelCer_QNAME, byte[].class, Cancel.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "store_pending", scope = Cancel.class)
    public JAXBElement<Boolean> createCancelStorePending(Boolean value) {
        return new JAXBElement<Boolean>(_OutCancelStorePending_QNAME, Boolean.class, Cancel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "password", scope = Cancel.class)
    public JAXBElement<String> createCancelPassword(String value) {
        return new JAXBElement<String>(_OutCancelPassword_QNAME, String.class, Cancel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "username", scope = CancelSignature.class)
    public JAXBElement<String> createCancelSignatureUsername(String value) {
        return new JAXBElement<String>(_OutCancelUsername_QNAME, String.class, CancelSignature.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "xml", scope = CancelSignature.class)
    public JAXBElement<byte[]> createCancelSignatureXml(byte[] value) {
        return new JAXBElement<byte[]>(_OutCancelXml_QNAME, byte[].class, CancelSignature.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "store_pending", scope = CancelSignature.class)
    public JAXBElement<Boolean> createCancelSignatureStorePending(Boolean value) {
        return new JAXBElement<Boolean>(_OutCancelStorePending_QNAME, Boolean.class, CancelSignature.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "password", scope = CancelSignature.class)
    public JAXBElement<String> createCancelSignaturePassword(String value) {
        return new JAXBElement<String>(_OutCancelPassword_QNAME, String.class, CancelSignature.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelaCFDResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "cancelResult", scope = CancelResponse.class)
    public JAXBElement<CancelaCFDResult> createCancelResponseCancelResult(CancelaCFDResult value) {
        return new JAXBElement<CancelaCFDResult>(_CancelResponseCancelResult_QNAME, CancelaCFDResult.class, CancelResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReceiptResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "get_receiptResult", scope = GetReceiptResponse.class)
    public JAXBElement<ReceiptResult> createGetReceiptResponseGetReceiptResult(ReceiptResult value) {
        return new JAXBElement<ReceiptResult>(_GetReceiptResponseGetReceiptResult_QNAME, ReceiptResult.class, GetReceiptResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "uuid", scope = QueryPendingCancellation.class)
    public JAXBElement<String> createQueryPendingCancellationUuid(String value) {
        return new JAXBElement<String>(_QueryPendingCancellationUuid_QNAME, String.class, QueryPendingCancellation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "username", scope = QueryPendingCancellation.class)
    public JAXBElement<String> createQueryPendingCancellationUsername(String value) {
        return new JAXBElement<String>(_OutCancelUsername_QNAME, String.class, QueryPendingCancellation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "password", scope = QueryPendingCancellation.class)
    public JAXBElement<String> createQueryPendingCancellationPassword(String value) {
        return new JAXBElement<String>(_OutCancelPassword_QNAME, String.class, QueryPendingCancellation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelaCFDResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "out_cancelResult", scope = OutCancelResponse.class)
    public JAXBElement<CancelaCFDResult> createOutCancelResponseOutCancelResult(CancelaCFDResult value) {
        return new JAXBElement<CancelaCFDResult>(_OutCancelResponseOutCancelResult_QNAME, CancelaCFDResult.class, OutCancelResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "receipt", scope = ReceiptResult.class)
    public JAXBElement<String> createReceiptResultReceipt(String value) {
        return new JAXBElement<String>(_ReceiptResultReceipt_QNAME, String.class, ReceiptResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "uuid", scope = ReceiptResult.class)
    public JAXBElement<String> createReceiptResultUuid(String value) {
        return new JAXBElement<String>(_QueryPendingResultUuid_QNAME, String.class, ReceiptResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "error", scope = ReceiptResult.class)
    public JAXBElement<String> createReceiptResultError(String value) {
        return new JAXBElement<String>(_QueryPendingResultError_QNAME, String.class, ReceiptResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "date", scope = ReceiptResult.class)
    public JAXBElement<String> createReceiptResultDate(String value) {
        return new JAXBElement<String>(_QueryPendingResultDate_QNAME, String.class, ReceiptResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "taxpayer_id", scope = ReceiptResult.class)
    public JAXBElement<String> createReceiptResultTaxpayerId(String value) {
        return new JAXBElement<String>(_ReceiptResultTaxpayerId_QNAME, String.class, ReceiptResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "success", scope = ReceiptResult.class)
    public JAXBElement<Boolean> createReceiptResultSuccess(Boolean value) {
        return new JAXBElement<Boolean>(_ReceiptResultSuccess_QNAME, Boolean.class, ReceiptResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StringArray }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "uuids", scope = UUIDS.class)
    public JAXBElement<StringArray> createUUIDSUuids(StringArray value) {
        return new JAXBElement<StringArray>(_UUIDSUuids_QNAME, StringArray.class, UUIDS.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "UUID", scope = Folio.class)
    public JAXBElement<String> createFolioUUID(String value) {
        return new JAXBElement<String>(_FolioUUID_QNAME, String.class, Folio.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "EstatusUUID", scope = Folio.class)
    public JAXBElement<String> createFolioEstatusUUID(String value) {
        return new JAXBElement<String>(_FolioEstatusUUID_QNAME, String.class, Folio.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UUIDS }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "UUIDS", scope = SignCancel.class)
    public JAXBElement<UUIDS> createSignCancelUUIDS(UUIDS value) {
        return new JAXBElement<UUIDS>(_CancelUUIDS_QNAME, UUIDS.class, SignCancel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "username", scope = SignCancel.class)
    public JAXBElement<String> createSignCancelUsername(String value) {
        return new JAXBElement<String>(_OutCancelUsername_QNAME, String.class, SignCancel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "serial", scope = SignCancel.class)
    public JAXBElement<String> createSignCancelSerial(String value) {
        return new JAXBElement<String>(_SignCancelSerial_QNAME, String.class, SignCancel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "taxpayer_id", scope = SignCancel.class)
    public JAXBElement<String> createSignCancelTaxpayerId(String value) {
        return new JAXBElement<String>(_OutCancelTaxpayerId_QNAME, String.class, SignCancel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "store_pending", scope = SignCancel.class)
    public JAXBElement<Boolean> createSignCancelStorePending(Boolean value) {
        return new JAXBElement<Boolean>(_OutCancelStorePending_QNAME, Boolean.class, SignCancel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "password", scope = SignCancel.class)
    public JAXBElement<String> createSignCancelPassword(String value) {
        return new JAXBElement<String>(_OutCancelPassword_QNAME, String.class, SignCancel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelaCFDResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "cancel_signatureResult", scope = CancelSignatureResponse.class)
    public JAXBElement<CancelaCFDResult> createCancelSignatureResponseCancelSignatureResult(CancelaCFDResult value) {
        return new JAXBElement<CancelaCFDResult>(_CancelSignatureResponseCancelSignatureResult_QNAME, CancelaCFDResult.class, CancelSignatureResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "CodEstatus", scope = CancelaCFDResult.class)
    public JAXBElement<String> createCancelaCFDResultCodEstatus(String value) {
        return new JAXBElement<String>(_CancelaCFDResultCodEstatus_QNAME, String.class, CancelaCFDResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FolioArray }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "Folios", scope = CancelaCFDResult.class)
    public JAXBElement<FolioArray> createCancelaCFDResultFolios(FolioArray value) {
        return new JAXBElement<FolioArray>(_CancelaCFDResultFolios_QNAME, FolioArray.class, CancelaCFDResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "RfcEmisor", scope = CancelaCFDResult.class)
    public JAXBElement<String> createCancelaCFDResultRfcEmisor(String value) {
        return new JAXBElement<String>(_CancelaCFDResultRfcEmisor_QNAME, String.class, CancelaCFDResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "Fecha", scope = CancelaCFDResult.class)
    public JAXBElement<String> createCancelaCFDResultFecha(String value) {
        return new JAXBElement<String>(_CancelaCFDResultFecha_QNAME, String.class, CancelaCFDResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apps.services.soap.core.views", name = "Acuse", scope = CancelaCFDResult.class)
    public JAXBElement<String> createCancelaCFDResultAcuse(String value) {
        return new JAXBElement<String>(_CancelaCFDResultAcuse_QNAME, String.class, CancelaCFDResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryPendingResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "query_pending_cancellationResult", scope = QueryPendingCancellationResponse.class)
    public JAXBElement<QueryPendingResult> createQueryPendingCancellationResponseQueryPendingCancellationResult(QueryPendingResult value) {
        return new JAXBElement<QueryPendingResult>(_QueryPendingCancellationResponseQueryPendingCancellationResult_QNAME, QueryPendingResult.class, QueryPendingCancellationResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "type", scope = GetReceipt.class)
    public JAXBElement<String> createGetReceiptType(String value) {
        return new JAXBElement<String>(_GetReceiptType_QNAME, String.class, GetReceipt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "uuid", scope = GetReceipt.class)
    public JAXBElement<String> createGetReceiptUuid(String value) {
        return new JAXBElement<String>(_QueryPendingCancellationUuid_QNAME, String.class, GetReceipt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "username", scope = GetReceipt.class)
    public JAXBElement<String> createGetReceiptUsername(String value) {
        return new JAXBElement<String>(_OutCancelUsername_QNAME, String.class, GetReceipt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "taxpayer_id", scope = GetReceipt.class)
    public JAXBElement<String> createGetReceiptTaxpayerId(String value) {
        return new JAXBElement<String>(_OutCancelTaxpayerId_QNAME, String.class, GetReceipt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.finkok.com/cancel", name = "password", scope = GetReceipt.class)
    public JAXBElement<String> createGetReceiptPassword(String value) {
        return new JAXBElement<String>(_OutCancelPassword_QNAME, String.class, GetReceipt.class, value);
    }

}
