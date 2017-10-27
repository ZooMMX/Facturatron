/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.Dominio;

import org.junit.Test;

/**
 *
 * @author octavioruizcastillo
 */
public class MetodoDePagoTest {

    /**
     * Test of getTicketData method, of class Ticket.
     */
    @Test
    public void testEnumMetodoDePago() throws Exception {
        MetodoDePagoEnum mp = MetodoDePagoEnum.PPD;
        /*if(mp.toString() != "Depósito en cuenta") {
            throw new Exception("Error en test, msj. de enum incorrecto");
        }*/
    }

}