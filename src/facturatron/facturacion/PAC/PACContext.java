/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.facturacion.PAC;

import facturatron.facturacion.cfdi.finkok.FinkokPACServiceImpl;

/**
 *
 * @author octavioruizcastillo
 */
public class PACContext {
    public static IPACService instancePACService() {
        return new FinkokPACServiceImpl();
    }
}
