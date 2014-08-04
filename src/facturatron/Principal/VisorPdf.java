/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facturatron.Principal;

import com.alee.utils.SystemUtils;
import facturatron.Dominio.Configuracion;
import facturatron.config.ConfiguracionDao;
import facturatron.config.ConfiguracionForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import facturatron.lib.Reporte;
import facturatron.lib.entities.ComprobanteTron;
import org.jdesktop.swingx.util.Utilities;

/**
 *
 * @author Octavio
 */
public class VisorPdf {

    /**
     * Método que se encarga de abrir el Reporte/Archivo con el visor escogido
     * por el Usuario.
     *
     * @param path
     * @param comprobante
     * @param pathPlantilla
     * @throws IOException
     * @throws InterruptedException
     */
    public static void abrir(String path, ComprobanteTron comprobante, String pathPlantilla)
            throws IOException, InterruptedException {
        Configuracion configuracion = new ConfiguracionDao().load();
        if (configuracion.getVisorPDF() == null || configuracion.getVisorPDF().equals(ConfiguracionForm.VISOR_PDF_NATIVO)) {
            if(SystemUtils.isWindows()) {
                Process p =
                        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + path);
                p.waitFor();
            } else if(SystemUtils.isMac()) {
                Process p =
                        Runtime.getRuntime().exec("open " + path);
                p.waitFor();
            }
        } else if (configuracion.getVisorPDF() != null && configuracion.getVisorPDF().equals(ConfiguracionForm.VISOR_PDF_JAVA)) {
            try {
                List<ComprobanteTron> beans = new ArrayList<ComprobanteTron>();
                beans.add(comprobante);
                Reporte reporte = new Reporte(pathPlantilla, beans);
                reporte.lanzarPreview();
            } catch (Exception ex) {
                Logger.getLogger(VisorPdf.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}