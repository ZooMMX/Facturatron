/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron;

/**
 *
 * @author Octavio
 */
public class Misc {
    public static String getStackTraceString(java.lang.Throwable exc)
        {
            String salida = exc.toString() + "\n";
            java.lang.StackTraceElement[] elementos = exc.getStackTrace();
            for(int i = 0; i < elementos.length; i++)
            {
                salida += elementos[i].toString() + " at \n" ;
            }
            return salida;
        }
}
