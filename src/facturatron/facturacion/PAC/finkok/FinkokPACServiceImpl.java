/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facturatron.facturacion.PAC.finkok;

import com.finkok.cancel.Application;
import com.finkok.cancel.CancelSOAP;
import com.finkok.cancel.CancelaCFDResult;
import com.finkok.cancel.Folio;
import com.finkok.cancel.FolioArray;
import com.finkok.cancel.ReceiptResult;
import com.finkok.cancel.StringArray;
import com.finkok.cancel.UUIDS;
import facturatron.Dominio.Configuracion;
import facturatron.Dominio.Factura;
import facturatron.facturacion.DefaultDistribucionHandler;
import facturatron.facturacion.IDistribucionHandler;
import facturatron.facturacion.PAC.FinkokIncidenciasException;
import facturatron.facturacion.PAC.IPACService;
import facturatron.facturacion.PAC.IStatusTimbre;
import facturatron.facturacion.PAC.PACException;
import facturatron.lib.entities.CFDv3Tron;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import javax.xml.bind.JAXBElement;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante.Complemento;
import mx.bigdata.sat.cfdi.v33.schema.ObjectFactory;
import mx.bigdata.sat.cfdi.v33.schema.TimbreFiscalDigital;
import mx.bigdata.sat.security.KeyLoader;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMWriter;
import com.finkok.stamp.AcuseRecepcionCFDI;
import com.finkok.stamp.QueryPendingResult;
import com.finkok.stamp.StampSOAP;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import facturatron.Misc;
import java.util.GregorianCalendar;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante;
import mx.bigdata.sat.security.KeyLoaderEnumeration;
import mx.bigdata.sat.security.factory.KeyLoaderFactory;

/**
 *
 * @author octavioruizcastillo
 */
public class FinkokPACServiceImpl implements IPACService {
  
    final public static String PROVIDER = BouncyCastleProvider.PROVIDER_NAME;
    
    /**
    * Carga el proveedor BC si no ha sido cargado antes
    */
    public static void initializeBCProvider() {
        try {
            if (Security.getProvider(PROVIDER) == null) {
                Security.addProvider(new BouncyCastleProvider());
            }
        } catch (Exception e) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }
    
    @Override
    public CFDv3Tron timbrar(CFDv3Tron cfdi) throws PACException {
        
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            
            String xml = cfdi.getXML();
            Configuracion config = Configuracion.getConfig();
            
            AcuseRecepcionCFDI acuse = stamp(xml.getBytes(), 
                    config.getUsuarioPAC(), 
                    config.getPasswordPAC());
            
            //El acuse traé una copia completa ya timbrada del CFDI lista para enviarse.
            
            
            ObjectFactory of = new ObjectFactory();            
            TimbreFiscalDigital timbre = of.createTimbreFiscalDigital();            
            Date fechaTimbre = dateFormat.parse( acuse.getFecha().getValue() );
            //Convierto fechaTimbre a XMLGregorianCalendar
            timbre.setFechaTimbrado( Misc.dateToXMLGregorianCalendar(fechaTimbre) );
            timbre.setNoCertificadoSAT(acuse.getNoCertificadoSAT().getValue());
            timbre.setVersion("1.1");
            timbre.setUUID(acuse.getUUID().getValue());
            timbre.setSelloSAT(acuse.getSatSeal().getValue());
            timbre.setSelloCFD(cfdi.getComprobante().getSello());
            timbre.setRfcProvCertif("FIN1203015JA");

            Complemento complemento = of.createComprobanteComplemento();
            complemento.getAny().add(timbre);
            cfdi.getComprobante().getComplemento().add(complemento);
            
        } catch (ParseException ex) {
            throw new PACException( "ParseException en fecha de timbre del proveedor finkok", ex);
        } catch (FinkokIncidenciasException ex) {
            throw new PACException( "Excepción timbrando con finkok", ex );
        } catch(Exception ex) {
            PACException pace;
            if(ex.getCause() instanceof UnknownHostException) 
                pace = new PACException("Error, no hay conexión a internet", new PACException("Es necesario conectarse para continuar con el timbrado en el PAC", ex));
            else
                pace = new PACException("Excepción desconocida", ex);
            throw pace;
        }
        
        return cfdi;
                    
    }
    private static final Logger LOG = Logger.getLogger(FinkokPACServiceImpl.class.getName());

    private static AcuseRecepcionCFDI stamp(byte[] xml, java.lang.String username, java.lang.String password) throws FinkokIncidenciasException {
        StampSOAP service = new StampSOAP();
        
        com.finkok.stamp.Application port = (com.finkok.stamp.Application) service.getApplication();
        AcuseRecepcionCFDI acuseCFDI = port.stamp(xml, username, password);
        
        if (acuseCFDI.getIncidencias() != null && acuseCFDI.getIncidencias().getValue().getIncidencia().size() > 0) {
                throw new FinkokIncidenciasException(acuseCFDI.getIncidencias().getValue());
        }
        
        return acuseCFDI;
    }

    @Override
    public Boolean cancelar(Factura comprobante) throws PACException {
        try {
                        initializeBCProvider();
            CancelSOAP cancelSOAP = new CancelSOAP();
            Application application = cancelSOAP.getApplication();

            com.finkok.cancel.ObjectFactory ob = new com.finkok.cancel.ObjectFactory();

            UUIDS uuids = ob.createUUIDS(); 
            StringArray stringArray = ob.createStringArray();
            stringArray.getString().add( comprobante.getFolioFiscal() );

            JAXBElement<StringArray> array = ob.createUUIDSUuids(stringArray);
            uuids.setUuids(array);

            //Obtener usuario y constraseña para servicio de Finkok
            Configuracion config = Configuracion.getConfig();
            String userFinkok = config.getUsuarioPAC();
            String passFinkok = config.getPasswordPAC();

            //Obtener cer y key
            String passKey = config.getpassCer();
            URI URIKey = new URI("file:///"+config.getpathKey().replace("\\", "/"));
            URI URICer = new URI("file:///"+config.getpathCer().replace("\\", "/"));
            PrivateKey key = ClassicKeyLoader.loadPKCS8PrivateKey(new FileInputStream(new File( URIKey )), passKey );
            X509Certificate cert = ClassicKeyLoader
              .loadX509Certificate(new FileInputStream(new File( URICer )));

            //Convertir certificado a PEM (incluye transformación a Base64) y a bytes
            StringWriter sw = new StringWriter();
            PEMWriter pw = new PEMWriter(sw);
            pw.writeObject( cert );
            pw.flush();
            pw.close();
            byte[] certificadoBytes = sw.toString().getBytes("UTF-8");

            //Convertir llave a PEM encriptado con DESEDE (tripleDES) y en bytes;
            StringWriter sw2 = new StringWriter();
            PEMWriter pw2 = new PEMWriter(sw2);
            pw2.writeObject( key, "DESEDE", passFinkok.toCharArray(), new SecureRandom() );
            pw2.flush();
            pw2.close();
            byte[] keyBytes = sw2.toString().getBytes("UTF-8");

            //Obtener RFC del emisor
            String rfcEmisor = comprobante.getEmisor().getRfc();              

            //---- Invocar método de cancelación ---
            CancelaCFDResult acuse = application.cancel(uuids, userFinkok, passFinkok, rfcEmisor, certificadoBytes, keyBytes, true);

            //---- Validación de la respuesta (acuse) del PAC
            if (acuse.getFolios() != null) {
                FolioArray folioArray = acuse.getFolios().getValue();
                for (Folio f : folioArray.getFolio()) {
                    if (f.getEstatusUUID() != null) {
                        //Validación de códigos de error
                        validacionFinkok(f.getEstatusUUID().getValue());
                        //Sólo en caso de obtener un código 201 "UUID Cancelado exitosamente" se devuelve TRUE
                        if(f.getEstatusUUID().getValue().contains("201")) 
                            return true;                                                    
                    }
                }
            } else {
                throw new PACException(acuse.getCodEstatus().getValue());
            }

            throw new IllegalStateException("Falla desconocida al cancelar comprobante");
        } catch(PACException pe) {
            throw pe;
        } catch(FileNotFoundException fnf) {
            PACException pace = new PACException("No se encontraron los archivos de la llave y/o el certificado", fnf);
            throw pace;
        } catch (URISyntaxException ex) {
            PACException pace = new PACException("Sintaxis de ruta URI (de llave o certificado) incorrecta", ex);
            throw pace;
        } catch (IOException ex) {
            PACException pace = new PACException("Error de lectura de la llave o el certificado", ex);
            throw pace;
        } catch (Exception ex) {
            PACException pace;
            if(ex.getCause() instanceof UnknownHostException) 
                pace = new PACException("No hay conexión a internet, es necesario conectarse para continuar", ex);
            else
                pace = new PACException("Error desconocido en métodos loadPKCS8PrivateKey o loadX509Certificate", ex);
            throw pace;
        }
    }

    @Override
    public IStatusTimbre getStatusTimbre(Factura comprobante) {
        Configuracion config = Configuracion.getConfig();
        
        com.finkok.stamp.StampSOAP service = new com.finkok.stamp.StampSOAP();
        com.finkok.stamp.Application port = service.getApplication();
        final QueryPendingResult result = port.queryPending(
                config.getUsuarioPAC(), 
                config.getPasswordPAC(),
                comprobante.getFolioFiscal()
                ); 
        
        IStatusTimbre returnable = new IStatusTimbre() {

            @Override
            public String getMensaje() {
                StringBuilder mensaje = new StringBuilder();
                mensaje.append("\r\n---- Status del timbre en el PAC ----\r\n");
                
                //Si no existe respuesta terminar el método con el mensaje correpondiente
                if(result == null) { 
                    mensaje.append( "El PAC no respondió la solicitud de status" );
                    return mensaje.toString();
                }
                
                mensaje.append("Status: ").append(getEstadoEnSAT()).append("\r\n");
                if(result.getAttempts()    != null) mensaje.append("Intentos de envío: ").append( result.getAttempts().getValue()).append("\r\n");
                if(result.getDate()        != null) mensaje.append("Fecha: ").append(result.getDate().getValue()).append("\r\n");
                if(result.getError()       != null) mensaje.append("Error: ").append(result.getError().getValue()).append("\r\n");
                if(result.getNextAttempt() != null) mensaje.append("Siguiente intento: ").append(result.getNextAttempt().getValue()).append("\r\n");
                if(result.getUuid()        != null) mensaje.append("UUID: ").append(result.getUuid().getValue()).append("\r\n");
                if(result.getUuidStatus()  != null) mensaje.append("UUID status: ").append(result.getUuidStatus().getValue()).append("\r\n");
                return mensaje.toString();
            }

            @Override
            public IStatusTimbre.EstadoEnSAT getEstadoEnSAT() {
                String status = result.getStatus().getValue();
                if(status.equals("S"))
                    return IStatusTimbre.EstadoEnSAT.NO_RECIBIDO;
                else if(status.equals("F"))
                    return IStatusTimbre.EstadoEnSAT.RECIBIDO;
                else
                    return IStatusTimbre.EstadoEnSAT.ERROR_DESCONOCIDO;
            }
        };
        return returnable;
    }

    @Override
    public IStatusTimbre getStatusCancelacion(Factura comprobante) {
        Configuracion config = Configuracion.getConfig();
        
        CancelSOAP cancelSOAP = new CancelSOAP();
        Application cancelApp = cancelSOAP.getApplication();
        
        final com.finkok.cancel.QueryPendingResult result = cancelApp.queryPendingCancellation(
                config.getUsuarioPAC(), 
                config.getPasswordPAC(),
                comprobante.getFolioFiscal()
                );
        
        final ReceiptResult recibo = cancelApp.getReceipt(
                config.getUsuarioPAC(), 
                config.getPasswordPAC(), 
                comprobante.getEmisor().getRfc(), 
                comprobante.getFolioFiscal(), 
                "C" //Cadena determinada por el PAC para solicitar recibo de cancelación
                );
        
        IStatusTimbre returnable = new IStatusTimbre() {

            @Override
            public String getMensaje() {
                StringBuilder mensaje = new StringBuilder();
                mensaje.append("\r\n---- Status de la cancelación en el PAC ----\r\n");
                
                //Si no existe respuesta terminar el método con el mensaje correpondiente
                if(result == null) { 
                    mensaje.append( "El PAC no respondió la solicitud de status" );
                    return mensaje.toString();
                }
                
                mensaje.append("Status: ").append(getEstadoEnSAT()).append("\r\n");
                if(result.getAttempts()    != null) mensaje.append("Intentos de envío: ").append( result.getAttempts().getValue()).append("\r\n");
                if(result.getDate()        != null) mensaje.append("Fecha: ").append(result.getDate().getValue()).append("\r\n");
                if(result.getError()       != null) mensaje.append("Error: ").append(result.getError().getValue()).append("\r\n");
                if(result.getNextAttempt() != null) mensaje.append("Siguiente intento: ").append(result.getNextAttempt().getValue()).append("\r\n");
                if(result.getUuid()        != null) mensaje.append("UUID: ").append(result.getUuid().getValue()).append("\r\n");
                if(result.getUuidStatus()  != null) mensaje.append("UUID status: ").append(result.getUuidStatus().getValue()).append("\r\n");
                
                mensaje.append("\r\n---- Status del recibo de cancelación en el PAC ----\r\n");
                if(recibo.getDate()        != null) mensaje.append("Fecha recibo: ").append( recibo.getDate().getValue() ).append("\r\n");
                if(recibo.getError()       != null) mensaje.append("Error: ").append( recibo.getError().getValue() ).append("\r\n");
                if(recibo.getReceipt()     != null) mensaje.append("Recibo XML disponible").append("\r\n");
                if(recibo.getSuccess()     != null) mensaje.append(recibo.getSuccess().getValue() ? "Cancelación exitosa" : "Cancelación insatisfactoria").append("\r\n");
                if(recibo.getTaxpayerId()  != null) mensaje.append("RFC emisor: ").append(recibo.getTaxpayerId().getValue()).append("\r\n");
                return mensaje.toString();
            }

            @Override
            public IStatusTimbre.EstadoEnSAT getEstadoEnSAT() {
                String status = result.getStatus().getValue();
                if(status.equals("C"))
                    return IStatusTimbre.EstadoEnSAT.NO_RECIBIDO;
                else if(status.equals("F"))
                    return IStatusTimbre.EstadoEnSAT.RECIBIDO;
                else
                    return IStatusTimbre.EstadoEnSAT.ERROR_DESCONOCIDO;
            }
        };
        return returnable;
    }

    private void validacionFinkok(String codigo) throws PACException {
        
        //Validación de la cancelación del CFDI
        //Código 201 corresponde a UUID Cancelado exitosamente
        if(codigo.contains("201")) return;
        if(codigo.contains("202")) throw new PACException("Folio Previamente Cancelado");
        if(codigo.contains("203")) throw new PACException("No corresponde el RFC del Emisor y de quien solicita la cancelación");
        if(codigo.contains("205")) throw new PACException("Folio No exise");
        //Validación de timbrado y cancelación del CFDI
        if(codigo.contains("300")) throw new PACException("Usuario y contraseña de PAC inválidos");
        if(codigo.contains("301")) throw new PACException("XML mal formado");
        if(codigo.contains("302")) throw new PACException("Sello mal formado o inválido");
        if(codigo.contains("303")) throw new PACException("Sello no corresponde al emisor");
        if(codigo.contains("304")) throw new PACException("Certificado Revocado o caduco");
        if(codigo.contains("305")) throw new PACException("La fecha de emisión no esta dentro de la vigencia del CSD del Emisor");
        if(codigo.contains("306")) throw new PACException("El certificado no es de tipo CSD");
        if(codigo.contains("307")) throw new PACException("El CFDI contiene un timbre previo");
        if(codigo.contains("308")) throw new PACException("Certificado no expedido por el SAT");
        //Validación de negocio de CFD
        if(codigo.contains("401")) throw new PACException("Fecha y hora de generación fuera de rango");
        if(codigo.contains("402")) throw new PACException("RFC del emisor no se encuentra en el régimen de contribuyentes");
        if(codigo.contains("403")) throw new PACException("La fecha de emisión no es posterior al 01 de enero de 2012");
        //Códigos de Validación Finkok
        if(codigo.contains("501")) throw new PACException("Autenticación no válida");
        if(codigo.contains("702")) throw new PACException("No ha registrado el RFC emisor bajo la cuenta de Finkok");
        if(codigo.contains("703")) throw new PACException("Cuenta suspendida");
        if(codigo.contains("704")) throw new PACException("Error con la contraseña de la llave Privada");
        if(codigo.contains("705")) throw new PACException("XML estructura inválida");
        if(codigo.contains("706")) throw new PACException("Socio Inválido");
        if(codigo.contains("707")) throw new PACException("XML ya contiene un nodo TimbreFiscalDigital");
        if(codigo.contains("708")) throw new PACException("No se pudo conectar al SAT");
        if(codigo.contains("711")) throw new PACException("Error con el certificado al cancelar");
        //Ningun código desconocido, podría ser un error
        throw new PACException("Código de Finkok desconocido: " + codigo);
    }

    @Override
    public boolean getRequiereSellado() {
        return true;
    }
    
    @Override
    public IDistribucionHandler getDistribucionHandler() {
        return new DefaultDistribucionHandler();
    }
}
