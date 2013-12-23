/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.omoikane;

import facturatron.datasource.DatasourceContext;
import facturatron.datasource.DatasourceException;
import facturatron.datasource.Ticket;
import facturatron.datasource.omoikane.TicketOmoikane;
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
    public void testGetTicketData() throws DatasourceException {
        System.out.println("getTicketData");
        Integer idAlmacen = 1;
        Integer idCaja = 5;
        Integer idVenta = 653523;
        Ticket expResult = null;
        Ticket result = DatasourceContext.instanceDatasourceInstance().getTicket("1-5-653523");
        //assertEquals(expResult, result);
        
    }

}