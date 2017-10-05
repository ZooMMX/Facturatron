/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.facturacion;

import facturatron.Dominio.Factura;
import facturatron.Dominio.Persona;
import facturatron.MVC.JDBCDAOSupport;
import facturatron.lib.entities.ComprobanteTron;
import facturatron.lib.entities.ConceptosTron;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante;
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
public class FacturaDaoTest {
    
    public FacturaDaoTest() {
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
     * Test of toComprobanteTron method, of class FacturaDao.
     */
    @Test
    public void testToComprobanteTron() throws Exception {
        System.out.println("toComprobanteTron");
        FacturaDao instance = new FacturaDao();
        ComprobanteTron expResult = null;
        ComprobanteTron result = instance.toComprobanteTron();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConceptosTron method, of class FacturaDao.
     */
    @Test
    public void testGetConceptosTron() {
        System.out.println("getConceptosTron");
        FacturaDao instance = new FacturaDao();
        ConceptosTron expResult = null;
        ConceptosTron result = instance.getConceptosTron();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getImpuestos method, of class FacturaDao.
     */
    @Test
    public void testGetImpuestos() {
        System.out.println("getImpuestos");
        FacturaDao instance = new FacturaDao();
        Comprobante.Impuestos expResult = null;
        Comprobante.Impuestos result = instance.getImpuestos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSubtotalGravado16 method, of class FacturaDao.
     */
    @Test
    public void testGetSubtotalGravado16() {
        System.out.println("getSubtotalGravado16");
        FacturaDao instance = new FacturaDao();
        BigDecimal expResult = null;
        BigDecimal result = instance.getSubtotalGravado16();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSubtotalExento method, of class FacturaDao.
     */
    @Test
    public void testGetSubtotalExento() {
        System.out.println("getSubtotalExento");
        FacturaDao instance = new FacturaDao();
        BigDecimal expResult = null;
        BigDecimal result = instance.getSubtotalExento();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSubtotalGravado0 method, of class FacturaDao.
     */
    @Test
    public void testGetSubtotalGravado0() {
        System.out.println("getSubtotalGravado0");
        FacturaDao instance = new FacturaDao();
        BigDecimal expResult = null;
        BigDecimal result = instance.getSubtotalGravado0();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTotal method, of class FacturaDao.
     */
    @Test
    public void testSetTotal() {
        System.out.println("setTotal");
        BigDecimal total = null;
        FacturaDao instance = new FacturaDao();
        instance.setTotal(total);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDescuentoTasa0 method, of class FacturaDao.
     */
    @Test
    public void testSetDescuentoTasa0() {
        System.out.println("setDescuentoTasa0");
        BigDecimal descuento = null;
        FacturaDao instance = new FacturaDao();
        instance.setDescuentoTasa0(descuento);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDescuentoTasa16 method, of class FacturaDao.
     */
    @Test
    public void testSetDescuentoTasa16() {
        System.out.println("setDescuentoTasa16");
        BigDecimal descuento = null;
        FacturaDao instance = new FacturaDao();
        instance.setDescuentoTasa16(descuento);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setReceptor method, of class FacturaDao.
     */
    @Test
    public void testSetReceptor() {
        System.out.println("setReceptor");
        Persona receptor = null;
        FacturaDao instance = new FacturaDao();
        instance.setReceptor(receptor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class FacturaDao.
     */
    @Test
    public void testFindAll_Date_Date() {
        System.out.println("findAll");
        Date fechaInicial = null;
        Date fechaFinal = null;
        FacturaDao instance = new FacturaDao();
        ArrayList<FacturaDao> expResult = null;
        ArrayList<FacturaDao> result = instance.findAll(fechaInicial, fechaFinal);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class FacturaDao.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        FacturaDao instance = new FacturaDao();
        instance.update();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of persist method, of class FacturaDao.
     */
    @Test
    public void testPersist() throws Exception {
        System.out.println("persist");
        FacturaDao instance = new FacturaDao();
        instance.persist();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class FacturaDao.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        FacturaDao instance = new FacturaDao();
        instance.remove();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByFolio method, of class FacturaDao.
     */
    @Test
    public void testFindByFolio() {
        System.out.println("findByFolio");
        BigInteger folio = null;
        FacturaDao instance = new FacturaDao();
        Factura expResult = null;
        Factura result = instance.findByFolio(folio);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findBy method, of class FacturaDao.
     */
    @Test
    public void testFindBy() {
        System.out.println("findBy");
        Integer id = null;
        FacturaDao instance = new FacturaDao();
        Factura expResult = null;
        Factura result = instance.findBy(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBD method, of class FacturaDao.
     */
    @Test
    public void testGetBD() {
        System.out.println("getBD");
        FacturaDao instance = new FacturaDao();
        JDBCDAOSupport expResult = null;
        JDBCDAOSupport result = instance.getBD();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class FacturaDao.
     */
    @Test
    public void testFindAll_0args() {
        System.out.println("findAll");
        FacturaDao instance = new FacturaDao();
        List<Factura> expResult = null;
        List<Factura> result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReciboName method, of class FacturaDao.
     */
    @Test
    public void testGetReciboName_0args() {
        System.out.println("getReciboName");
        FacturaDao instance = new FacturaDao();
        String expResult = "";
        String result = instance.getReciboName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReciboName method, of class FacturaDao.
     */
    @Test
    public void testGetReciboName_String_String() {
        System.out.println("getReciboName");
        String serie = "";
        String folio = "";
        FacturaDao instance = new FacturaDao();
        String expResult = "";
        String result = instance.getReciboName(serie, folio);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPdfPath method, of class FacturaDao.
     */
    @Test
    public void testGetPdfPath_0args() {
        System.out.println("getPdfPath");
        FacturaDao instance = new FacturaDao();
        String expResult = "";
        String result = instance.getPdfPath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXmlPath method, of class FacturaDao.
     */
    @Test
    public void testGetXmlPath_0args() {
        System.out.println("getXmlPath");
        FacturaDao instance = new FacturaDao();
        String expResult = "";
        String result = instance.getXmlPath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPdfPath method, of class FacturaDao.
     */
    @Test
    public void testGetPdfPath_String_String() {
        System.out.println("getPdfPath");
        String s = "";
        String f = "";
        FacturaDao instance = new FacturaDao();
        String expResult = "";
        String result = instance.getPdfPath(s, f);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXmlPath method, of class FacturaDao.
     */
    @Test
    public void testGetXmlPath_String_String() {
        System.out.println("getXmlPath");
        String s = "";
        String f = "";
        FacturaDao instance = new FacturaDao();
        String expResult = "";
        String result = instance.getXmlPath(s, f);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cancelar method, of class FacturaDao.
     */
    @Test
    public void testCancelar() throws Exception {
        System.out.println("cancelar");
        FacturaDao instance = new FacturaDao();
        instance.cancelar();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateConstraints method, of class FacturaDao.
     */
    @Test
    public void testValidateConstraints() {
        System.out.println("validateConstraints");
        FacturaDao instance = new FacturaDao();
        instance.validateConstraints();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
