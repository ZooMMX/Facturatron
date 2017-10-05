/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.facturacion.PAC;

import com.finkok.stamp.IncidenciaArray;

/**
 *
 * @author octavioruizcastillo
 */
public class FinkokIncidenciasException extends PACException {
    IncidenciaArray incidenciaArray;
    public FinkokIncidenciasException(IncidenciaArray ia) {
        super("Incidencia envíada por el proveedor Finkok al intentar timbrar la factura");
        incidenciaArray = ia;
    }
    
    @Override
    public String getMessage() {
        return incidenciaArray.getIncidencia().get(0).getMensajeIncidencia().getValue();
    }
    
}
