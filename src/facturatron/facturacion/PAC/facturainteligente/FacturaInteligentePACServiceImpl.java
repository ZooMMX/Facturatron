/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.facturacion.PAC.facturainteligente;

import com.facturainteligente.ArrayOfString;
import com.facturainteligente.ConexionRemota32;
import com.facturainteligente.ConexionRemota32Soap;
import com.facturainteligente.GenerarCFDIv32;
import com.google.common.base.Joiner;
import facturatron.Dominio.Configuracion;
import facturatron.Dominio.Factura;
import facturatron.facturacion.FacturaDao;
import facturatron.facturacion.IDistribucionHandler;
import facturatron.facturacion.PAC.IPACService;
import facturatron.facturacion.PAC.IStatusTimbre;
import facturatron.facturacion.PAC.PACException;
import facturatron.lib.entities.CFDv3Tron;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.bigdata.sat.cfdi.CFDv32;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Complemento;
import mx.bigdata.sat.cfdi.v32.schema.ObjectFactory;
import mx.bigdata.sat.cfdi.v32.schema.TimbreFiscalDigital;
import sun.misc.BASE64Decoder;

/**
 *
 * @author octavioruizcastillo
 */
public class FacturaInteligentePACServiceImpl implements IPACService {

    @Override
    public CFDv3Tron timbrar(CFDv3Tron cfdi) throws PACException {
        FIAdapter adapter = new FIAdapter(cfdi.getComprobante());
        
        try {
            ConexionRemota32Soap conexion = new ConexionRemota32().getConexionRemota32Soap();

            TimbreFiscalDigital timbre = timbreRemoteCall(conexion, adapter);
            
            cfdi.getComprobante().addTimbreFiscalDigital(timbre);
        } catch (PACException pe) {
            throw pe;
        } catch (Exception ex) {
            PACException pace;
            if(ex.getCause() instanceof UnknownHostException) 
                pace = new PACException("Error, no hay conexión a internet", new PACException("Es necesario conectarse para continuar con el timbrado en el PAC", ex));
            else
                pace = new PACException("Excepción desconocida", ex);
            throw pace;
        }
        return cfdi;
    }

    public TimbreFiscalDigital timbreRemoteCall(ConexionRemota32Soap conexion, FIAdapter adapter) throws PACException {
        ArrayOfString datosConceptos = adapter.getDatosConceptos();
        
        ArrayOfString respuesta = conexion.generarCFDIv32(
                    adapter.getDatosUsuario(), 
                    adapter.getDatosReceptor(), 
                    adapter.getDatosCFDI(), 
                    adapter.getDatosEtiquetas(), 
                    datosConceptos, 
                    adapter.getDatosInfoAduanera(datosConceptos), 
                    adapter.getDatosRetenidos(), 
                    adapter.getDatosTraslados(), 
                    adapter.getDatosRetenidosLocales(), 
                    adapter.getDatosTrasladosLocales());
        
        Boolean exito = Boolean.parseBoolean( respuesta.getString().get(0) );
        if(exito) {
            try {
                //Retornar el timbre o el xml o el comprobante
                String xmlTimbradoString = respuesta.getString().get(3);                
                
                InputStream is = new ByteArrayInputStream( xmlTimbradoString.getBytes() );
                Comprobante compTimbrado = CFDv32.newComprobante( is );
                for (Object complemento : compTimbrado.getComplemento().getAny()) {
                    if(complemento instanceof TimbreFiscalDigital) {
                        TimbreFiscalDigital timbre = (TimbreFiscalDigital) complemento;
                        return timbre;
                    }
                }
                throw new PACException("Timbre incorrecto");

            } catch (Exception ex) {
                throw new PACException("Error leyendo timbre", ex);
            }
        } else {
            String mensajePrincipal  = respuesta.getString().get(1);
            String mensajeSecundario = respuesta.getString().get(2);
            throw new PACException("Error enviado por el PAC", new PACException(mensajePrincipal + ". " + mensajeSecundario));
        }
        
    }
    
    @Override
    public Boolean cancelar(Factura comprobante) throws PACException {
        
        try {
            Configuracion config          = Configuracion.getConfig();
            ConexionRemota32Soap conexion = new ConexionRemota32().getConexionRemota32Soap12();
            
            ArrayOfString datosUsuario = new ArrayOfString();
            datosUsuario.getString().add(0, comprobante.getEmisor().getRfc());
            datosUsuario.getString().add(1, config.getUsuarioPAC());
            datosUsuario.getString().add(2, config.getPasswordPAC());
            
            ArrayOfString foliosFiscales = new ArrayOfString();
            foliosFiscales.getString().add(0, comprobante.getFolioFiscal() );
            foliosFiscales.getString().add(1, comprobante.getFolioFiscal() );

            ArrayOfString respuesta = conexion.cancelarCFDI(datosUsuario, foliosFiscales);
            if(respuesta == null || respuesta.getString() == null || respuesta.getString().size() < 1) 
                throw new PACException("PAC no devolvió respuesta al cancelar folio");
            
            Boolean exito = Boolean.parseBoolean( respuesta.getString().get(0) );
            if(!exito) {
                throw new PACException(respuesta.getString().get(1));
            }

        } catch (PACException pe) {
            throw pe;
        } catch (Exception ex) {
            PACException pace;
            if(ex.getCause() instanceof UnknownHostException) 
                pace = new PACException("Error, no hay conexión a internet", new PACException("Es necesario conectarse para continuar", ex));
            else
                pace = new PACException("Excepción desconocida", ex);
            throw pace;
        }
        return true;
    }

    @Override
    public IStatusTimbre getStatusTimbre(Factura comprobante) {
        return new IStatusTimbre() {

            @Override
            public String getMensaje() {
                return "Este PAC (FacturaInteligente) no provee mecanismos para conocer el estado de un folio timbrado";
            }

            @Override
            public IStatusTimbre.EstadoEnSAT getEstadoEnSAT() {
                return IStatusTimbre.EstadoEnSAT.ERROR_DESCONOCIDO;
            }
            
        };
    }

    @Override
    public IStatusTimbre getStatusCancelacion(Factura comprobante) {
        return new IStatusTimbre() {

            @Override
            public String getMensaje() {
                return "Este PAC (FacturaInteligente) no provee mecanismos para conocer el estado de un folio cancelado";
            }

            @Override
            public IStatusTimbre.EstadoEnSAT getEstadoEnSAT() {
                return IStatusTimbre.EstadoEnSAT.ERROR_DESCONOCIDO;
            }
            
        };
    }
    
    @Override
    public boolean getRequiereSellado() {
        return false;
    }

    @Override
    public IDistribucionHandler getDistribucionHandler() {
        return new FIDistribucionHandler();
    }

    public static class FIDistribucionHandler implements IDistribucionHandler {

        public FIDistribucionHandler() {
        }

        @Override
        public void distribuir(Factura fact, CFDv3Tron cfd) throws Exception {                        
            
            try {
                //Guardar archivo XML
                cfd.toXMLFILE(getXmlPath(fact.getSerie(), fact.getFolio().toString()));               
                
                //Conectarse al WS
                
                Configuracion config          = Configuracion.getConfig();
                ConexionRemota32Soap conexion = new ConexionRemota32().getConexionRemota32Soap();

                ArrayOfString datosUsuario = new ArrayOfString();
                datosUsuario.getString().add(0, fact.getEmisor().getRfc());
                datosUsuario.getString().add(1, config.getUsuarioPAC());
                datosUsuario.getString().add(2, config.getPasswordPAC());

                //Enviar por EMAIL
                
                ArrayOfString respuesta = conexion.enviarCFDI(datosUsuario, cfd.getComprobante().getFolioFiscal(), fact.getReceptor().getCorreoElectronico());
                if(respuesta == null || respuesta.getString() == null || respuesta.getString().size() < 1) 
                    throw new PACException("PAC no devolvió respuesta al enviar CFDI por correo");

                Boolean exito = Boolean.parseBoolean( respuesta.getString().get(0) );
                if(!exito) {
                    throw new PACException(respuesta.getString().get(1));
                }                     
                
                //Guardar PDF tomado del WS
                ArrayOfString respuestaPDF = conexion.obtenerPDF(datosUsuario, cfd.getComprobante().getFolioFiscal());
                decodeToPDF(getPdfPath(fact.getSerie(), fact.getFolio().toString()), respuestaPDF.getString().get(3));

                Logger.getLogger(FacturaInteligentePACServiceImpl.class.getName()).log(Level.INFO, "Comprobante generado y enviado por correo electrónico");
                
            } catch (PACException pe) {
                throw pe;
            } catch (Exception ex) {
                PACException pace;
                if(ex.getCause() instanceof UnknownHostException) 
                    pace = new PACException("Error, no hay conexión a internet", new PACException("Es necesario conectarse para continuar", ex));
                else
                    pace = new PACException("Excepción desconocida", ex);
                throw pace;
            }
        }
        
        public void decodeToPDF(String filename, String pdfString) throws IOException {

           
            byte[] imageByte;
            try {
                BASE64Decoder decoder = new BASE64Decoder();
                byte[] pdfBytes = decoder.decodeBuffer(pdfString);
                ByteArrayInputStream bis = new ByteArrayInputStream(pdfBytes);
                FileOutputStream fos = new FileOutputStream(filename);
                fos.write(pdfBytes);
                bis.close();
                fos.close();
            } catch (IOException e) {
                throw e;
            }
        }
        
        private Configuracion getConfig() { return Configuracion.getConfig(); } 
        String getReciboName(String serie, String folio) {
            serie                = (serie!=null?serie:"");
            String nombreRecibo  = "Factura"+serie+folio;
            return nombreRecibo;
        }

        String getPdfPath(String s, String f) { return getConfig().getPathPdf()+getReciboName(s,f)+".pdf"; }
        String getXmlPath(String s, String f) { return getConfig().getPathXml()+getReciboName(s,f)+".xml"; }
    }
}
