/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.lib.entities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import facturatron.lib.Reporte;

/**
 *
 * @author Octavio
 */
public class CFDv3Tron  {
    private String XML;
    private ComprobanteTron comprobante;

    /**
     * @return the XML
     */
    public String getXML() {
        return XML;
    }

    /**
     * @param XML the XML to set
     */
    public void setXML(String XML) {
        this.XML = XML;
    }

    /**
     * @return the comprobante
     */
    public ComprobanteTron getComprobante() {
        return comprobante;
    }

    /**
     * @param comprobante the comprobante to set
     */
    public void setComprobante(ComprobanteTron comprobante) {
        this.comprobante = comprobante;
    }

    public void showPreview(String rutaPlantillaJasper) throws Exception {
        List<ComprobanteTron> beans = new ArrayList<ComprobanteTron>();
        beans.add(comprobante);
        Reporte reporte = new Reporte(rutaPlantillaJasper, beans);
        reporte.lanzarPreview();
    }
    public void toPDFFile(String rutaPlantillaJasper, String rutaPDF) throws Exception {
        List<ComprobanteTron> beans = new ArrayList<ComprobanteTron>();
        beans.add(comprobante);
        Reporte reporte = new Reporte(rutaPlantillaJasper, beans);
        reporte.toPDFFile(rutaPDF);
    }
    public void toXMLFILE(String rutaXML) throws IOException {
        FileWriter fstream = new FileWriter(rutaXML, false);
        BufferedWriter out = new BufferedWriter(fstream);
        out.write(XML);
        out.close();
    }

}
