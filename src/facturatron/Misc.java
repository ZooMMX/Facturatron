/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron;

import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.PrintStream;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Octavio
 */
public class Misc {
    public static String getStackTraceString(java.lang.Throwable exc)
        {
            if(exc == null) return "Sin stacktrace";
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream print = new PrintStream(baos);
            exc.printStackTrace(print);
            String salida = baos.toString();

            return salida;
        }
    
    public static XMLGregorianCalendar dateToXMLGregorianCalendar(java.util.Date date) throws DatatypeConfigurationException {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        XMLGregorianCalendar xmlgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
        xmlgc.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
        xmlgc.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
        return xmlgc;
    }
}
