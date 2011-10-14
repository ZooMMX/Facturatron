/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.omoikane;

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
public class TicketTest {

    public TicketTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getTicketData method, of class Ticket.
     */
    @Test
    public void testGetTicketData() {
        System.out.println("getTicketData");
        Integer idAlmacen = 1;
        Integer idCaja = 5;
        Integer idVenta = 653523;
        Ticket expResult = null;
        Ticket result = Ticket.getTicketData(idAlmacen, idCaja, idVenta);
        //assertEquals(expResult, result);
        
    }

}