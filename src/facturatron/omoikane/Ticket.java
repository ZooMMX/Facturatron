/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.omoikane;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author octavioruizcastillo
 */
public class Ticket extends ArrayList<RenglonTicket> {


    public static Ticket getTicketData(Integer idAlmacen, Integer idCaja, Integer idVenta) {

        MathContext                 mc          = MathContext.DECIMAL64;
        Ticket                      ticket      = new Ticket();
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
