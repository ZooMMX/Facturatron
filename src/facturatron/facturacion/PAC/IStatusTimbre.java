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
public interface IStatusTimbre {
    public enum EstadoEnSAT {RECIBIDO, NO_RECIBIDO, ERROR_DESCONOCIDO};
    public String getMensaje();
    public EstadoEnSAT getEstadoEnSAT();
}
