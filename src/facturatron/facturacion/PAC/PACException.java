/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.facturacion.PAC;

/**
 *
 * @author octavioruizcastillo
 */
public class PACException extends Exception {
    public PACException(String msj) {
        super(msj);
    }
    public PACException(String msj, Throwable t) {
        super(msj, t);
    }
    
}
