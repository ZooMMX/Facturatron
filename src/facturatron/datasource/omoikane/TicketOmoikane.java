/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.datasource.omoikane;

import facturatron.datasource.DatasourceException;
import facturatron.datasource.IDatasourceService;
import facturatron.datasource.RenglonTicket;
import facturatron.datasource.Ticket;
import facturatron.omoikane.Articulo;
import facturatron.omoikane.ArticulosJpaController;
import facturatron.datasource.CorteZ;
import facturatron.omoikane.CorteZDao;
import facturatron.omoikane.Ventas;
import facturatron.omoikane.VentasDetalles;
import facturatron.omoikane.VentasDetallesJpaController;
import facturatron.omoikane.VentasJpaController;
import facturatron.omoikane.VentasPK;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author octavioruizcastillo
 */
public class TicketOmoikane extends Ticket {

    public Ticket getTicket(Object id) {
        String idString = (String) id;
        String[] args = idString.split("-");
        Integer idAlmacen = Integer.valueOf(args[0]);
        Integer idCaja = Integer.valueOf(args[1]);
        Integer idVenta = Integer.valueOf(args[2]);
        return getTicketData(idAlmacen, idCaja, idVenta);
    }
    
    
    public TicketOmoikane getTicketData(Integer idAlmacen, Integer idCaja, Integer idVenta) {

        MathContext                 mc          = MathContext.DECIMAL64;
        TicketOmoikane                      ticket      = new TicketOmoikane();
        VentasJpaController         ventaJpa    = new VentasJpaController();
        VentasDetallesJpaController detallesJpa = new VentasDetallesJpaController();
        ArticulosJpaController      productsJpa = new ArticulosJpaController();

        VentasPK pk = new VentasPK(idVenta, idCaja, idAlmacen);

        Ventas               ventas   = ventaJpa.findVentas(pk);
        List<VentasDetalles> detalles = detallesJpa.findByVenta(idVenta);

        for (VentasDetalles vd : detalles) {
            final RenglonTicket renglon       = new RenglonTicket();
            final Articulo      producto      = productsJpa.findArticulos(vd.getIdArticulo());
            final BigDecimal    precio        = new BigDecimal(vd.getPrecio(), mc).round(mc).setScale(4, BigDecimal.ROUND_HALF_EVEN);
            final BigDecimal    impuestos     = new BigDecimal(vd.getImpuestos(), mc).round(mc).setScale(4, BigDecimal.ROUND_HALF_EVEN);
            final BigDecimal    impuestosPorc = new BigDecimal(producto.getImpuestos(), mc).round(mc).setScale(4, BigDecimal.ROUND_HALF_EVEN);
            final BigDecimal    descuento     = new BigDecimal(vd.getDescuento(), mc).round(mc).setScale(4, BigDecimal.ROUND_UP);
            final BigDecimal    cantidad      = new BigDecimal(vd.getCantidad(), mc).round(mc).setScale(4, BigDecimal.ROUND_HALF_EVEN);
            //Precio unitario con descuento
            /*
            final BigDecimal    pConDescu = cantidad.compareTo(new BigDecimal("0.0")) > 0 && descuento.compareTo(new BigDecimal("0.0")) > 0
                                          ? precio.subtract(descuento.divide(cantidad, mc),mc).setScale(4, BigDecimal.ROUND_UP)
                                          : precio;
            final BigDecimal    pUnitario = (producto.getImpuestos() > 0d)
                    ? pConDescu.divide(
                        new BigDecimal("1.00").add(impuestosPorc.divide(new BigDecimal("100.00"), 4, BigDecimal.ROUND_HALF_EVEN))
                     , 2, BigDecimal.ROUND_HALF_EVEN)
                    : pConDescu;
            */
            //precio unitario = precio - impuestos
            final BigDecimal pUnitario = precio.subtract( impuestos );
            renglon.cantidad       = cantidad;
            renglon.codigo         = producto.getCodigo();
            renglon.descripcion    = producto.getDescripcion();
            renglon.impuestos      = producto.getImpuestos() > 0d;
            renglon.precioUnitario = pUnitario;
            renglon.unidad         = producto.getUnidad();
            renglon.importe        = new BigDecimal(vd.getTotal(), mc).round(mc).setScale(4, BigDecimal.ROUND_UP);
            renglon.descuento      = descuento;

            ticket.add(renglon);
        }

        return ticket;
    }
    
}
