/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron;

import com.alee.laf.WebLookAndFeel;
import com.googlecode.flyway.core.Flyway;
import com.googlecode.flyway.core.api.FlywayException;
import com.googlecode.flyway.core.api.MigrationInfo;
import com.googlecode.flyway.core.api.MigrationState;
import facturatron.Dominio.Configuracion;
import facturatron.datasource.DatasourceContext;
import facturatron.datasource.DatasourceException;
import facturatron.omoikane.CorteZDao;
import facturatron.datasource.omoikane.TicketOmoikane;
import facturatron.facturacion.FacturaDao;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Level;
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
                
        // You should work with UI (including installing L&F) inside EDT
        SwingUtilities.invokeLater ( new Runnable ()
        {
            public void run ()
            {
                System.out.println(Charset.defaultCharset());
                WebLookAndFeel.install ();
                Locale.setDefault(Locale.US);
                Thread.setDefaultUncaughtExceptionHandler(new UEHandler());
                Logger.getLogger("").addHandler(new CEHandler());
                facturatron.Principal.Main frame = new facturatron.Principal.Main();
                checkDBMigrations();
            }
        } );        
        

    }
    /**
     * Comprueba base de datos y migraciones
     */
    public static void checkDBMigrations() {
 
        try {
            Configuracion config = Configuracion.getConfig();
            Flyway flyway = new Flyway();
            flyway.setDataSource(config.getUrlBd(), config.getUserBd(), config.getPassBd());

            //Inicializar la BD, el código comentado sirve para inicializar en la última versión
            if(flyway.info().current() == null) {              
                //MigrationInfo[] mi = flyway.info().pending();
                //MigrationInfo lastMigration = mi[mi.length-1];
                //flyway.setInitVersion(lastMigration.getVersion());
                flyway.setInitVersion("0");
                flyway.init();
            }
            flyway.migrate();
        } catch(FlywayException fe) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Configuración de base de datos incorrecta", fe); 
        }
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
