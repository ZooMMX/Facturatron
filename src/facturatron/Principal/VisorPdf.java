/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.Principal;

import java.io.IOException;

/**
 *
 * @author Octavio
 */
public class VisorPdf {
    public static void abrir(String path) throws IOException, InterruptedException {
        Process p =
          Runtime.getRuntime()
        .exec("rundll32 url.dll,FileProtocolHandler "+path);
        p.waitFor();
    }
}
