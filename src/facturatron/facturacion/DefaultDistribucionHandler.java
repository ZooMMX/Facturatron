/*
 * Copyright (C) 2014 octavioruizcastillo
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package facturatron.facturacion;

import com.phesus.facturatron.persistence.dao.FacturaDao;
import facturatron.Dominio.Configuracion;
import facturatron.Dominio.Factura;
import facturatron.Principal.VisorPdf;
import com.phesus.facturatron.persistence.dao.ClienteDao;
import facturatron.email.EmailFacturaCliente;
import facturatron.lib.entities.CFDv3Tron;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author octavioruizcastillo
 */
public class DefaultDistribucionHandler implements IDistribucionHandler {
    private Configuracion getConfig() { return Configuracion.getConfig(); } 

    String getReciboName(String serie, String folio) {
        serie                = (serie!=null?serie:"");
        String nombreRecibo  = "Factura"+serie+folio;
        return nombreRecibo;
    }

    String getPdfPath(String s, String f) { return getConfig().getPathPdf()+getReciboName(s,f)+".pdf"; }
    String getXmlPath(String s, String f) { return getConfig().getPathXml()+getReciboName(s,f)+".xml"; }
    
    /**
     * Éste método genera y envía todos los archivos finales requeridos:
     * <li> Guarda archivo XML en el sistema de archivos listo para su uso
     * <li> Guarda archivo  PDF de comprobante fiscal digital por internet en el sistema de archivos
     * <li> Envía los archivos anteriores por correo electrónico
     * @param fact
     * @param cfd
     * @throws Exception 
     */
     @Override
     public void distribuir(Factura fact, CFDv3Tron cfd) throws Exception {
        Configuracion cfg = getConfig();
        String serie = cfd.getComprobante().getSerie();
        String folio = cfd.getComprobante().getFolio();
        cfd.getComprobante().setPathLogo(cfg.getPathLogo());
        cfd.toPDFFile(cfg.getPathPlantilla(), getPdfPath(serie, folio));
        cfd.toXMLFILE(getXmlPath(serie, folio));

        ClienteDao cliente = new ClienteDao().findBy(fact.getReceptor().getId());
        if (cliente != null) {
            EmailFacturaCliente emailFacturaCliente = new EmailFacturaCliente(cliente.getCorreoElectronico());
            emailFacturaCliente.addAttachment(getPdfPath(serie, folio), serie + folio + "PDF");
            emailFacturaCliente.addAttachment(getXmlPath(serie, folio), serie + folio + "XML");
            Thread thread = new Thread(emailFacturaCliente);
            thread.start();
        }
        
        //Visor Java
        //cfd.showPreview(cfg.getPathPlantilla());
        //Visor nativo para windows
        try {
            VisorPdf.abrir(getPdfPath(serie, folio), cfd.getComprobante(), cfg.getPathPlantilla());
        } catch(IOException io) {
            Logger.getLogger(FacturaDao.class.getName()).log(Level.SEVERE, "Excepción en visor PDF", io);
        }
     }
}
