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
            final RenglonTicket renglon  = new RenglonTicket();
            final Articulos     producto = productsJpa.findArticulos(vd.getIdArticulo());

            renglon.cantidad       = new BigDecimal(vd.getCantidad(), mc).round(mc).setScale(2, BigDecimal.ROUND_HALF_EVEN);
            renglon.codigo         = producto.getCodigo();
            renglon.descripcion    = producto.getDescripcion();
            renglon.impuestos      = vd.getImpuestos() > 0d;
            renglon.precioUnitario = new BigDecimal(vd.getPrecio(), mc).round(mc).setScale(2, BigDecimal.ROUND_HALF_EVEN);
            renglon.unidad         = producto.getUnidad();
            renglon.importe        = new BigDecimal(vd.getTotal(), mc).round(mc).setScale(2, BigDecimal.ROUND_HALF_EVEN);

            ticket.add(renglon);
        }

        return ticket;
    }
}
