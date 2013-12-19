/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron;

import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.PrintStream;

/**
 *
 * @author Octavio
 */
public class Misc {
    public static String getStackTraceString(java.lang.Throwable exc)
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream print = new PrintStream(baos);
            exc.printStackTrace(print);
            String salida = baos.toString();

            return salida;
        }
}
