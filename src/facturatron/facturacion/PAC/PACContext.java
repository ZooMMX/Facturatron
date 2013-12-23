/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.facturacion.PAC;

import facturatron.Dominio.Configuracion;
import facturatron.facturacion.PAC.facturainteligente.FacturaInteligentePACServiceImpl;
import facturatron.facturacion.PAC.finkok.FinkokPACServiceImpl;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author octavioruizcastillo
 */
public class PACContext {
    public static enum PACS { Finkok, FacturaInteligente };
    
    public static IPACService instancePACService() throws PACException {
        Configuracion config = Configuracion.getConfig();
        if(config.getConectorPAC() == PACS.Finkok)
            return new FinkokPACServiceImpl();
        else if(config.getConectorPAC() == PACS.FacturaInteligente)
            return new FacturaInteligentePACServiceImpl();
        throw new PACException("Se estableció un conector de PAC inválido");
    }
}
