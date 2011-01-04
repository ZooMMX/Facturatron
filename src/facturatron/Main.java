/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron;

import facturatron.facturacion.FacturaControl;
import facturatron.facturacion.FacturaForm;
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
        // TODO code application logic here
        //JFrame frame = new JFrame();
        //Menu menu = new Menu();
        Thread.setDefaultUncaughtExceptionHandler(new UEHandler());
        Logger.getLogger("").addHandler(new CEHandler());
        facturatron.Principal.Main frame = new facturatron.Principal.Main();
        
        //frame.getContentPane().add(menu);
        //frame.setVisible(true);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

}
