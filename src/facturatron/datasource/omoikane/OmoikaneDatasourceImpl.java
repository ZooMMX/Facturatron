/*
 * Copyright (C) 2013 octavioruizcastillo
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

 package facturatron.datasource.omoikane;



import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import facturatron.datasource.CorteZ;
import facturatron.datasource.DatasourceException;
import facturatron.datasource.IDatasourceService;
import facturatron.datasource.RenglonTicket;
import facturatron.datasource.Ticket;
import facturatron.datasource.TicketGlobal;
import facturatron.facturacion.ListadoControl;
import facturatron.omoikane.Impuesto;
import facturatron.omoikane.Ventas;
import facturatron.omoikane.VentasDetallesJpaController;
import facturatron.omoikane.VentasDetallesJpaController.SumaVentas;
import facturatron.omoikane.VentasJpaController;
import facturatron.omoikane.exceptions.TicketFacturadoException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author octavioruizcastillo
 */
public class OmoikaneDatasourceImpl implements IDatasourceService {

    @Override
    public Ticket getTicket(Object id) throws TicketFacturadoException {
        return new TicketOmoikane().getTicket(id);
    }
    
    @Override
    public Ticket getTickets(Object idInicial, Object idFinal) {
        //return new TicketOmoikane().getTickets(idInicial);
        return null;
    }

    @Override
    public TicketGlobal getTicketGlobal(Object desde, Object hasta) throws DatasourceException {
        
        //Cargar suma de ventas_detalles, subtotal, impuestos y total
        VentasDetallesJpaController jpaDetalles = new VentasDetallesJpaController();
        List<SumaVentas> gruposVentas = jpaDetalles.sumaVentasDetalles((String)desde, (String)hasta);
        
        //Emulo un ticket para agregar la venta global a la factura
        TicketOmoikane ticket = new TicketOmoikane();
        for (SumaVentas sumaVentas : gruposVentas) {
            //Declaración de la partida del pseudo ticket
            RenglonTicket rt = new RenglonTicket();
            
            //Definición de los impuestos
            String descripcionImpuestos = "";
            rt.precioUnitario = sumaVentas.subtotal;
            rt.cantidad = BigDecimal.ONE;
            rt.descuento = sumaVentas.descuentos;
            rt.ieps = BigDecimal.ZERO;
            rt.setImporteIva(BigDecimal.ZERO);
            rt.setImporteIeps(BigDecimal.ZERO);

                        
            rt.impuestos = false;
            for (Impuesto impuesto : sumaVentas.impuestos) {
                if(!descripcionImpuestos.isEmpty()) descripcionImpuestos += ",";
                descripcionImpuestos += " "+impuesto.getDescripcion();
                if(impuesto.getDescripcion().contains("IVA")){
                    rt.impuestos = true;
                    //rt.setImporteIva(impuesto.getImpuesto());
                }
                if(impuesto.getDescripcion().contains("IEPS")) {
                    rt.ieps = rt.ieps=impuesto.getPorcentaje();
                    //rt.setImporteIeps(impuesto.getImpuesto());
                }
            }
            //Si no hay impuestos definir un mensaje genérico de impuestos
            if(descripcionImpuestos.isEmpty()) descripcionImpuestos = " exención de impuestos";           
            
            //Definición del resto de atributos
            rt.codigo = "0";
            rt.claveProductoSAT="01010101";
            rt.claveUnidadSAT="ACT";
            rt.descripcion = "Venta con"+descripcionImpuestos;
            rt.unidad = "NA";
            ticket.add(rt);
        }
        
        
        
        //Cargo los tickets correspondientes a éste resumen de ventas, únicamente se cargan los IDs
        List<Ticket> ticketsEnGlobal = new ArrayList();
        
        List<Ticket> idsEnGlobal = jpaDetalles.getIdsNoFacturados((String)desde, (String)hasta);       

        //Construyo el objeto que se retornará
        
        TicketGlobal tg = new TicketGlobal();
        tg.setResumenGlobal(ticket);
        tg.setTickets(idsEnGlobal);
        
        return tg;
    }

    @Override
    public void setTicketsFacturados(java.util.List<Object> idsVenta, Object idFactura) throws DatasourceException {
        try {
            VentasJpaController         ventaJpa    = new VentasJpaController();
            List<Ventas> ventas = new ArrayList<Ventas>();
            for(Object idVenta : idsVenta) {
                
                //Utilizo la clase TicketOmoikane para importar la lógica de IDs
                TicketOmoikane ticket = new TicketOmoikane();
                ticket.setId((String) idVenta);
                //Ésta instancia únicamente será un contenedor del ID de venta
                Ventas venta = new Ventas();
                venta.setId(ticket.getIdVenta());
                ventas.add(venta);
            }
            //Método optimizado, únicamente sirve para ésto            
            if(ventas.size() > 0)
                ventaJpa.editManySetFacturada(ventas, (Integer) idFactura);
        } catch (Exception ex) {
            throw new DatasourceException("No se pudo marcar ticket facturado", ex);
        }
    }

    @Override
    public void cancelarFactura(Integer idFactura) throws DatasourceException {
        try {
            VentasJpaController         ventaJpa    = new VentasJpaController();
            List<Ventas> ventas = ventaJpa.findVentasByFactura(idFactura);
            for (Ventas venta : ventas) {
                venta.setFacturada(0);
            }
            ventaJpa.editMany(ventas);
        } catch (CommunicationsException ex) {
            throw new DatasourceException("No hay conexión con el origen de datos", ex);
        } catch (Exception ex) {
            if(ex.getMessage().contains("Unable to acquire a connection"))
                    throw new DatasourceException("Revisa los datos de conexión a la BD de Omoikane", ex);
                else
                    throw new DatasourceException("Hubo un problema al rehabilitar tickets contenidos en la factura", ex);
        }
    }

    @Override
    public void setTicketFacturado(Object idTicket, Object idFactura) throws DatasourceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CorteZ getCorteZ(Date fecha) throws DatasourceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
