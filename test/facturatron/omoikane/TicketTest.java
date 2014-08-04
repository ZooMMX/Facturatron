/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.omoikane;

import facturatron.datasource.DatasourceContext;
import facturatron.datasource.DatasourceException;
import facturatron.datasource.Ticket;
import facturatron.datasource.omoikane.TicketOmoikane;
import facturatron.omoikane.exceptions.TicketFacturadoException;
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
    public void testGetTicketData() throws DatasourceException, TicketFacturadoException {
        System.out.println("getTicketData");
        Integer idAlmacen = 1;
        Integer idCaja = 1;
        Integer idVenta = 3275352;
        Ticket expResult = null;
        Ticket result = DatasourceContext.instanceDatasourceInstance().getTicket("1-1-3275352");
        //assertEquals(expResult, result);
        
    }

}