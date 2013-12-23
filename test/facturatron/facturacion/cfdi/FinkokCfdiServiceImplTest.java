/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.facturacion.cfdi;

import facturatron.facturacion.PAC.finkok.FinkokPACServiceImpl;
import facturatron.Dominio.Factura;
import facturatron.Dominio.Persona;
import facturatron.lib.CFDFactory;
import facturatron.lib.entities.CFDv3Tron;
import facturatron.lib.entities.ComprobanteTron;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.Security;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jfree.util.Log;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author octavioruizcastillo
 */
public class FinkokCfdiServiceImplTest {
    
    public FinkokCfdiServiceImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of timbrar method, of class FinkokCfdiServiceImpl.
     */
    @Test
    public void testTimbrar() {
        try {
            System.out.println("timbrar");
            
            ComprobanteTron ct = facturatron.lib.Main.createComprobantes()[0];
            CFDv3Tron cfdi = new CFDFactory().toCFDI(ct);
            
            System.out.println("------ Fin timbrado -----");
        } catch (Exception ex) {
            Logger.getLogger(FinkokCfdiServiceImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testCancelar() {
        try {
            
            ComprobanteTron ct = facturatron.lib.Main.createComprobantes()[0];
            CFDv3Tron cfdi = new CFDFactory().toCFDI(ct);
            
            System.out.println("cancelación");
            
            Factura comprobante = new Factura();
            comprobante.setFolioFiscal( cfdi.getComprobante().getFolioFiscal() );
            Persona p = new Persona();
            p.setRfc( cfdi.getComprobante().getEmisor().getRfc() );
            comprobante.setEmisor( p );
            
            FinkokPACServiceImpl impl = new FinkokPACServiceImpl();
            impl.cancelar(comprobante);
            
            System.out.println("------ Fin cancelacion -----");
        } catch (Exception ex) {
            Logger.getLogger(FinkokCfdiServiceImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String readFile(String ruta) {
        try {
            FileInputStream fstream = new FileInputStream(ruta);
            DataInputStream entrada = new DataInputStream(fstream);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
            String strLinea;
            StringBuffer sB = new StringBuffer();
            while ((strLinea = buffer.readLine()) != null) {
                sB.append(strLinea).append("\n");
            }
            entrada.close();
            return sB.toString();
        } catch (Exception e) {
            Log.error("Reading file", e);
        }
        return null;
    }
    
}
