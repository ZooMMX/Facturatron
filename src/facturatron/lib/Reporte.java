
 /* Author Phesus        //////////////////////////////
 *  ORC                 /////////////
 *                     /////////////
 *                    /////////////
 *                   /////////////
 * //////////////////////////////                   */

package facturatron.lib;

import java.io.*;
import java.util.List;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import facturatron.lib.entities.ComprobanteTron;

public class Reporte {
    JasperPrint jp;
    
    public Reporte (String reporteJasper, List<ComprobanteTron> beans) throws Exception
    {
        try {
            InputStream stream = cargarPlantilla(reporteJasper);
            JasperReport jr;
            if(getExtension(reporteJasper).equalsIgnoreCase("jrxml"))
            {
                //Compilar el jrxml
                jr = JasperCompileManager.compileReport(stream);
                jp = JasperFillManager.fillReport(jr, new java.util.HashMap(), new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(beans));
            } else {
                //El reporte ya estaba compilado (.jasper)
                jp = JasperFillManager.fillReport(stream, new java.util.HashMap(), new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(beans));                
            }                

        } catch(Exception e) {
            throw e;
        }
    }
    
    private String getExtension(String path) {
        String extension = "";
        int i = path.lastIndexOf('.');
        if (i > 0) {
            extension = path.substring(i+1);
        }
        return extension;
    }


    public InputStream cargarPlantilla(String archivoReporte) throws Exception
    {
        InputStream stream = new FileInputStream(archivoReporte);
        if(stream == null) { throw new Exception("Plantilla de reporte no encontrada. " + archivoReporte); }
        return stream;
    }
    public void toPDFFile(String pdfFile) throws JRException
    {
        JasperExportManager.exportReportToPdfFile(jp, pdfFile);
    }
    public void lanzarPreview()
    {
        JasperViewer jr = new net.sf.jasperreports.view.JasperViewer(jp, false);
        jr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jr.setVisible(true);
        jr.toFront();
        jr.setAlwaysOnTop(true);
    }
	
}

