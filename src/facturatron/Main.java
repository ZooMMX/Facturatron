/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron;

import com.alee.laf.WebLookAndFeel;
import facturatron.datasource.DatasourceContext;
import facturatron.datasource.DatasourceException;
import facturatron.omoikane.CorteZDao;
import facturatron.datasource.omoikane.TicketOmoikane;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author saul
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //prueba2(); 
        
        // You should work with UI (including installing L&F) inside EDT
        SwingUtilities.invokeLater ( new Runnable ()
        {
            public void run ()
            {
                WebLookAndFeel.install ();
                Locale.setDefault(Locale.US);
                Thread.setDefaultUncaughtExceptionHandler(new UEHandler());
                Logger.getLogger("").addHandler(new CEHandler());
                facturatron.Principal.Main frame = new facturatron.Principal.Main();
            }
        } );        
        

    }
    public static void prueba() throws DatasourceException {
        Integer idAlmacen = 1;
        Integer idCaja = 2;
        Integer idVenta = 653527;
        TicketOmoikane expResult = null;
        TicketOmoikane result = (TicketOmoikane) DatasourceContext.instanceDatasourceInstance().getTicket("1-2-653527");
        System.out.println("just that");
    }
    
    public static void prueba2() throws DatasourceException {
        CorteZDao dao = new CorteZDao();
        dao.load(Calendar.getInstance().getTime());
    }

}
