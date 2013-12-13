/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.lib;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Conceptos;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Conceptos.Concepto;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Emisor;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Impuestos;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Impuestos.Traslados;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Impuestos.Traslados.Traslado;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Receptor;
import mx.bigdata.sat.cfdi.v32.schema.ObjectFactory;
import mx.bigdata.sat.cfdi.v32.schema.TUbicacion;
import mx.bigdata.sat.cfdi.v32.schema.TUbicacionFiscal;
import facturatron.lib.entities.CFDv3Tron;
import facturatron.lib.entities.ComprobanteTron;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante;
import mx.bigdata.sat.cfdi.v32.schema.TimbreFiscalDigital;

/**
 *
 * @author Octavio
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        /*
    CFDv2 cfd = new CFDv2(createComprobante());
    PrivateKey key = KeyLoader.loadPKCS8PrivateKey(new FileInputStream("C:/Users/Octavio/desarrollo/Java/Cer_Sellos/aaa010101aaa_csd_01.key"),
                           "a0123456789");
    X509Certificate cert = KeyLoader
      .loadX509Certificate(new FileInputStream("C:/Users/Octavio/desarrollo/Java/Cer_Sellos/aaa010101aaa_csd_01.cer"));
    cfd.sellar(key, cert);
    cfd.validar();
    cfd.verificar();
    cfd.guardar(System.err);*/
        /*
        ComprobanteTron comp = createComprobante();
        comp.setPassKey("a0123456789");
        comp.setURIKey(new URI("file:///C:/Users/Octavio/desarrollo/Java/Cer_Sellos/aaa010101aaa_csd_01.key"));
        comp.setURICer(new URI("file:///C:/Users/Octavio/desarrollo/Java/Cer_Sellos/aaa010101aaa_csd_01.cer"));

        CFDFactory cfdf = new CFDFactory();
        CFDv2Tron cfd = cfdf.toCFD(comp);

        System.out.println(cfd.getXML());
         *
         */
         Calendar periodo = Calendar.getInstance();
         periodo.set(2010, 12, 1);
         InformeMensual reporte = new InformeMensual(InformeMensual.Esquema.CFD, createEmisor(new ObjectFactory()), periodo);
         ArrayList<ComprobanteTron> comps = new ArrayList<ComprobanteTron>();

         ComprobanteTron[] compsArray = createComprobantesPrueba();

         comps.addAll(Arrays.asList(compsArray));
         reporte.cargarComprobantes(comps);
         reporte.toTXTFile("C:\\Users\\Octavio\\Documents\\CFD\\");

    }
    /**
     *
     * @return
     * @throws Exception
     */
    public static facturatron.lib.entities.ComprobanteTron[] createComprobantesPrueba() throws Exception {
        ComprobanteTron[] comp = createComprobantes();
        comp[0].setPassKey("12345678a");
        comp[0].setURIKey(new URI("file:////Users/octavioruizcastillo/AppDev/Proyectos/CFD/CFDI/aad990814bp7_1210261233s.cer"));
        comp[0].setURICer(new URI("file:////Users/octavioruizcastillo/AppDev/Proyectos/CFD/CFDI/aad990814bp7_1210261233s.key"));

        CFDFactory cfdf = new CFDFactory();
        CFDv3Tron cfd = cfdf.toCFDI(comp[0]);
        
        cfd.toPDFFile("C:\\Users\\Octavio\\Documents\\CFD\\FacturaDigital.jasper", "C:\\Users\\Octavio\\Documents\\CFD\\prueba.pdf");
        cfd.toXMLFILE("C:\\Users\\Octavio\\Documents\\CFD\\factura.xml");
        
        return comp;
    }
    public static facturatron.lib.entities.ComprobanteTron[] createComprobantes() throws Exception {
        ComprobanteTron[] comps = new facturatron.lib.entities.ComprobanteTron[1];
        comps[0] = createComprobante();
        comps[0].setPassKey("12345678a");
        comps[0].setURIKey(new URI("file:////Users/octavioruizcastillo/AppDev/Proyectos/CFD/CFDI/aad990814bp7_1210261233s.key"));
        comps[0].setURICer(new URI("file:////Users/octavioruizcastillo/AppDev/Proyectos/CFD/CFDI/aad990814bp7_1210261233s.cer"));
        
        return comps;
    }
    public static facturatron.lib.entities.ComprobanteTron[] createComprobantesFirmados() throws Exception {
        ComprobanteTron[] comps = new facturatron.lib.entities.ComprobanteTron[1];
        comps[0] = createComprobante();
        comps[0].setPassKey("12345678a");
        comps[0].setURIKey(new URI("file:////Users/octavioruizcastillo/AppDev/Proyectos/CFD/CFDI/aad990814bp7_1210261233s.key"));
        comps[0].setURICer(new URI("file:////Users/octavioruizcastillo/AppDev/Proyectos/CFD/CFDI/aad990814bp7_1210261233s.cer"));
        
        ObjectFactory of = new ObjectFactory();
        TimbreFiscalDigital timbre = of.createTimbreFiscalDigital();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            
        timbre.setFechaTimbrado( dateFormat.parse("2012-01-02T20:20:00") );
        timbre.setNoCertificadoSAT( "30001000000100000801" );
        timbre.setVersion("1.0");
        timbre.setUUID( "ad662d33-6934-459c-a128-bdf0393e0f44" );
        timbre.setSelloSAT( "j5bSpqM3w0+shGtlmqOwqqy6+d659O78ckfstu5vTSFa+2CVMj6Awfr18x4yMLGBwk6ruYbjBIVURodEII6nJIhTTUtYQV1cbRDG9kvvhaNAakx qaSOnOnOx79nHxqFPRVoqh10CsjocS9PZkSM2jz1uwLgaF0knf1g8pjDkLYwlk=" );
        timbre.setSelloCFD( "tOSe+Ex/wvn33YIGwtfmrJwQ31Crd7II9VcH63TGjHfxk5cfb3q9uSbDUGk9TXvo70ydOpikRVw+9B2Six0mbu3PjoPpO909oAYITrRyomdeUGJ 4vmA2/12L86EJLWpU7vlt4cL8HpkEw7TOFhSdpzb/890+jP+C1adBsHU1VHc=" );

        Comprobante.Complemento complemento = of.createComprobanteComplemento();
        complemento.getAny().add(timbre);
        comps[0].setComplemento(complemento);
        return comps;
    }
    private static facturatron.lib.entities.ComprobanteTron createComprobante() {
        ObjectFactory of = new ObjectFactory();
        facturatron.lib.entities.ComprobanteTron comp = new facturatron.lib.entities.ComprobanteTron();
        comp.setVersion("3.2");
        comp.setFecha(new Date());
        comp.setSerie("A");
        comp.setFolio("2");
        comp.setFormaDePago("UNA SOLA EXHIBICION");
        comp.setSubTotal(new BigDecimal("2000.00"));
        comp.setTotal(new BigDecimal("2320.00"));
        comp.setDescuento(new BigDecimal("0.00"));
        comp.setTipoDeComprobante("ingreso");
        comp.setEmisor(createEmisor(of));
        comp.setReceptor(createReceptor(of));
        comp.setConceptos(createConceptos(of));
        comp.setImpuestos(createImpuestos(of));
        comp.setMetodoDePago("EFECTIVO");
        comp.setLugarExpedicion("Tlatlauquitepec, Puebla");
        comp.setCertificado(null);

        return comp;
    }
    private static Emisor createEmisor(ObjectFactory of) {
        Emisor emisor = of.createComprobanteEmisor();
        emisor.setNombre("ASOCIACION DE AGRICULTORES DEL DISTRITO DE RIEGO 004 DON MARTIN COAHUILA Y NUEVO LEON AC");
        emisor.setRfc("AAD990814BP7");
        TUbicacionFiscal uf = of.createTUbicacionFiscal();
        uf.setCalle("Av. Hidalgo");
        uf.setCodigoPostal("06300");
        uf.setColonia("Guerrero");
        uf.setEstado("Distrito Federal");
        uf.setReferencia("En el centro de la ciudad de M\u00E9xico");
        uf.setMunicipio("Cuauhtemoc");
        uf.setNoExterior("77");
        uf.setPais("Mexico");
        
        emisor.setDomicilioFiscal(uf);
        TUbicacion u = of.createTUbicacion();
        u.setCalle("AV. UNIVERSIDAD");
        u.setCodigoPostal("03910");
        u.setColonia("OXTOPULCO");
        u.setEstado("DISTRITO FEDERAL");
        u.setNoExterior("1858");
        u.setPais("Mexico");
        emisor.setExpedidoEn(u); 
        
        Emisor.RegimenFiscal rf = new Emisor.RegimenFiscal();
        rf.setRegimen("Persona física con actividad empresarial");
        emisor.getRegimenFiscal().add(rf);
        return emisor;
    }

    private static Receptor createReceptor(ObjectFactory of) {
        Receptor receptor = of.createComprobanteReceptor();
        receptor.setNombre("JUAN PEREZ PEREZ");
        receptor.setRfc("PEPJ8001019Q8");
        TUbicacion uf = of.createTUbicacion();
        uf.setCalle("AV UNIVERSIDAD");
        uf.setCodigoPostal("04360");
        uf.setColonia("COPILCO UNIVERSIDAD");
        uf.setEstado("DISTRITO FEDERAL");
        uf.setMunicipio("COYOACAN");
        uf.setNoExterior("16 EDF 3");
        uf.setNoInterior("DPTO 101");
        uf.setPais("Mexico");
        receptor.setDomicilio(uf);
        return receptor;
    }

    private static Conceptos createConceptos(ObjectFactory of) {
        Conceptos cps = of.createComprobanteConceptos();
        List<Concepto> list = cps.getConcepto();
        Concepto c1 = of.createComprobanteConceptosConcepto();

        c1.setUnidad("CAPSULAS");
        c1.setImporte(new BigDecimal("244.00"));
        c1.setCantidad(new BigDecimal("1.0"));
        c1.setDescripcion("VIBRAMICINA 100MG 10");
        c1.setValorUnitario(new BigDecimal("244.00"));
        list.add(c1);
        Concepto c2 = of.createComprobanteConceptosConcepto();
        c2.setUnidad("BOTELLA");
        c2.setImporte(new BigDecimal("137.93"));
        c2.setCantidad(new BigDecimal("1.0"));
        c2.setDescripcion("CLORUTO 500M");
        c2.setValorUnitario(new BigDecimal("137.93"));
        list.add(c2);
        Concepto c3 = of.createComprobanteConceptosConcepto();
        c3.setUnidad("TABLETAS");
        c3.setImporte(new BigDecimal("84.50"));
        c3.setCantidad(new BigDecimal("1.0"));
        c3.setDescripcion("SEDEPRON 250MG 10");
        c3.setValorUnitario(new BigDecimal("84.50"));
        list.add(c3);
        return cps;
    }

    private static Impuestos createImpuestos(ObjectFactory of) {
        Impuestos imps = of.createComprobanteImpuestos();
        Traslados trs = of.createComprobanteImpuestosTraslados();
        List<Traslado> list = trs.getTraslado();
        Traslado t1 = of.createComprobanteImpuestosTrasladosTraslado();
        t1.setImporte(new BigDecimal("20.00"));
        t1.setImpuesto("IVA");
        t1.setTasa(new BigDecimal("16.00"));
        list.add(t1);
        Traslado t2 = of.createComprobanteImpuestosTrasladosTraslado();
        t2.setImporte(new BigDecimal("22.07"));
        t2.setImpuesto("IVA");
        t2.setTasa(new BigDecimal("16.00"));
        list.add(t2);
        imps.setTraslados(trs);
        imps.setTotalImpuestosTrasladados(new BigDecimal("42.07"));
        return imps;
    }

    }
