/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron;

import facturatron.facturacion.FacturaControl;
import facturatron.facturacion.FacturaForm;
import facturatron.omoikane.Ticket;
import java.util.Locale;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author saul
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Locale.setDefault(Locale.US);
        Thread.setDefaultUncaughtExceptionHandler(new UEHandler());
        Logger.getLogger("").addHandler(new CEHandler());
        facturatron.Principal.Main frame = new facturatron.Principal.Main();
        
        //prueba();

    }
    public static void prueba() {
        Integer idAlmacen = 1;
        Integer idCaja = 2;
        Integer idVenta = 653527;
        Ticket expResult = null;
        Ticket result = Ticket.getTicketData(idAlmacen, idCaja, idVenta);
        System.out.println("just it");
    }

}
