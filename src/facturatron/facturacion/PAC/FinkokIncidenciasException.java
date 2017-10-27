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
    String mensaje;
    public FinkokIncidenciasException(IncidenciaArray ia) {
        super("Incidencia envíada por el proveedor Finkok al intentar timbrar la factura");
        mensaje="<html>Ocurrieron uno o más errores:<br>1. "+ia.getIncidencia().get(0).getMensajeIncidencia().getValue()+"<br>";
        for(int i=1;i<ia.getIncidencia().size();i++){
            mensaje+=(i+1)+". "+ia.getIncidencia().get(i).getMensajeIncidencia().getValue()+"<br>";
        }
        mensaje+="</html>";
    }
    
    @Override
    public String getMessage() {
        return mensaje;
    }
    
}
