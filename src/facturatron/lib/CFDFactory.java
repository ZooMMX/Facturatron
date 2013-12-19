/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.lib;

import facturatron.facturacion.PAC.PACException;
import facturatron.facturacion.cfdi.finkok.FinkokPACServiceImpl;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante;
import mx.bigdata.sat.security.KeyLoader;
import facturatron.lib.entities.CFDv3Tron;
import facturatron.lib.entities.ComprobanteTron;
import mx.bigdata.sat.cfdi.CFDv32;

/**
 *
 * @author Octavio
 */
public class CFDFactory {
    public CFDv3Tron toCFDI(ComprobanteTron comprobante) throws Exception {
        return digitalizar(comprobante);
    }


    private CFDv3Tron digitalizar(ComprobanteTron comprobante) throws Exception {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);

        PrivateKey key = KeyLoader.loadPKCS8PrivateKey(new FileInputStream(new File(comprobante.getURIKey())),
                               comprobante.getPassKey());
        X509Certificate cert = KeyLoader
          .loadX509Certificate(new FileInputStream(new File(comprobante.getURICer())));
        
        CFDv32 cfd = new CFDv32(comprobante);        
        Comprobante sellado;
        
        sellado = cfd.sellarComprobante(key, cert);        
        cfd.validar();
        cfd.verificar();
        cfd.guardar(ps);        
                
        CFDv3Tron cfdtron =  new CFDv3Tron();

        comprobante.setNoCertificado(sellado.getNoCertificado());
        comprobante.setSello(sellado.getSello());
        comprobante.setCadenaOriginal(cfd.getCadenaOriginal());
        comprobante.setCertificado(sellado.getCertificado());
        cfdtron.setComprobante(comprobante);
        cfdtron.setXML(baos.toString());
        
        cfdtron = firmar(cfdtron);
        CFDv32 cfdi = new CFDv32(cfdtron.getComprobante());
        ps.flush();
        cfdi.validar();
        cfdi.verificar();
        cfdi.guardar(ps);
        cfdtron.setXML(baos.toString());
        
        return cfdtron;
    }
    
    public CFDv3Tron firmar(CFDv3Tron cfdi) throws PACException {
        FinkokPACServiceImpl instance = new FinkokPACServiceImpl();
        CFDv3Tron cfdiFirmado = instance.timbrar(cfdi);
        
        return cfdiFirmado;
    }
    
}
