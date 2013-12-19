/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.omoikane;

import facturatron.facturacion.FacturaDao;
import facturatron.facturacion.ListadoModel;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/** Clase encargada del acceso a los datos necesarios para generar Cortes Z
 *
 * @author octavioruizcastillo
 */
public class CorteZDao {
    public CorteZ load(Date day) {
        //Por defecto, determinar inicio y fin del día... Comportamiento no deseado
        Calendar dayC = Calendar.getInstance();
        dayC.setTime(day);
        
        Calendar dayIni = Calendar.getInstance();
        dayIni.set(dayC.get(Calendar.YEAR), dayC.get(Calendar.MONTH), dayC.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
       
        Calendar dayFin = Calendar.getInstance();
        dayFin.set(dayC.get(Calendar.YEAR), dayC.get(Calendar.MONTH), dayC.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        
        //Cargar intervalo de ids de ventas del día
        VentasJpaController jpa = new VentasJpaController();
        int[] intervaloIDs = jpa.getIDIntervalFromDay(dayIni.getTime(), dayFin.getTime());
        
        //Cargar suma de ventas_detalles, subtotal, impuestos y total
        VentasDetallesJpaController jpaDetalles = new VentasDetallesJpaController();
        VentasDetallesJpaController.SumaVentas sumaVentas = jpaDetalles.sumaVentasDetalles(intervaloIDs[0], intervaloIDs[1]);
        System.out.println(sumaVentas);
        
        //Cargar suma de facturas del día        
        ListadoModel listadoModel = new ListadoModel();
        
        listadoModel.setFechaInicial(new java.sql.Date(dayIni.getTimeInMillis()));
        listadoModel.setFechaFinal  (new java.sql.Date(dayFin.getTimeInMillis()));
        listadoModel.load();
        
        //CorteZ = ventas del día - facturas del día
        BigDecimal subtotal   = sumaVentas.subtotal .subtract( listadoModel.getSubtotal() );
        BigDecimal impuestos  = sumaVentas.impuestos.subtract( listadoModel.getIva()      );
        BigDecimal total      = sumaVentas.total    .subtract( listadoModel.getTotal()    );        
        
        CorteZ corteZ = new CorteZ();
        corteZ.setSubtotal(subtotal);
        corteZ.setImpuestos(impuestos);
        corteZ.setTotal(total);
        
        return corteZ;
    }
}
