/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facturatron.facturacion.PAC;

import facturatron.Dominio.Factura;
import facturatron.lib.entities.CFDv3Tron;

/**
 *
 * @author octavioruizcastillo
 */
public interface IPACService {
    
    public CFDv3Tron timbrar(CFDv3Tron cfdi) throws PACException;
    public Boolean cancelar(Factura comprobante) throws PACException;
    public IStatusTimbre getStatusTimbre(Factura comprobante);
    public IStatusTimbre getStatusCancelacion(Factura comprobante);
}
