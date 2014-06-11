/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.datasource.omoikane;

import facturatron.omoikane.ImpuestoJpaController;
import facturatron.datasource.DatasourceException;
import facturatron.datasource.IDatasourceService;
import facturatron.datasource.RenglonTicket;
import facturatron.datasource.Ticket;
import facturatron.omoikane.Articulo;
import facturatron.omoikane.ArticulosJpaController;
import facturatron.datasource.CorteZ;
import facturatron.omoikane.CorteZDao;
import facturatron.omoikane.Impuesto;
import facturatron.omoikane.Ventas;
import facturatron.omoikane.VentasDetalles;
import facturatron.omoikane.VentasDetallesJpaController;
import facturatron.omoikane.VentasJpaController;
import facturatron.omoikane.VentasPK;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author octavioruizcastillo
 */
public class TicketOmoikane extends Ticket<String> {
    
    private Integer idAlmacen;
    private Integer idCaja;
    private Integer idVenta;
    
    public void load(Object id) { getTicket(id); }
    
    public Ticket getTicket(Object id) {
        
        String idString = (String) id;
        setId(idString);
        
        String[] args = idString.split("-");
        setIdAlmacen ( Integer.valueOf(args[0]) );
        setIdCaja    ( Integer.valueOf(args[1]) );
        setIdVenta   ( Integer.valueOf(args[2]) );
        return getTicketData(idAlmacen, idCaja, idVenta);
    }
    
    public void setTicketFacturado(int estado) throws Exception {
        VentasJpaController         ventaJpa    = new VentasJpaController();
        
        String[] args = getId().split("-");
        setIdAlmacen ( Integer.valueOf(args[0]) );
        setIdCaja    ( Integer.valueOf(args[1]) );
        setIdVenta   ( Integer.valueOf(args[2]) );
        
        VentasPK pk = new VentasPK(idVenta, idCaja, idAlmacen);
        Ventas v = ventaJpa.findVentas(pk);
        v.setFacturada(estado);
        
        ventaJpa.edit(v);
    }
    
    private Map<String, BigDecimal> getTiposImpuesto(Articulo art) {
        HashMap<String, BigDecimal> map = new HashMap<String, BigDecimal>();
        
        Collection<Impuesto> impList = art.getImpuestos();
        for (Impuesto impuesto : impList) {
            String etiquetaImpuesto = "";
            if(impuesto.getDescripcion().contains("16") && impuesto.getDescripcion().contains("IVA")) 
                etiquetaImpuesto = "IVA";
            else if(impuesto.getDescripcion().contains("IEPS")) 
                etiquetaImpuesto = "IEPS";
            map.put(etiquetaImpuesto, impuesto.getPorcentaje());
        }
        
        return map;
    }
    
    public TicketOmoikane getTicketData(Integer idAlmacen, Integer idCaja, Integer idVenta) {

        MathContext                 mc          = MathContext.DECIMAL64;
        TicketOmoikane              ticket      = this; //new TicketOmoikane();
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
            final BigDecimal    descuento     = new BigDecimal(vd.getDescuento(), mc).round(mc).setScale(4, BigDecimal.ROUND_UP);
            final BigDecimal    cantidad      = new BigDecimal(vd.getCantidad(), mc).round(mc).setScale(4, BigDecimal.ROUND_HALF_EVEN);
            final BigDecimal    subtotal      = new BigDecimal(vd.getSubtotal(), mc).round(mc).setScale(4, BigDecimal.ROUND_HALF_EVEN);
                        
            //precio unitario = subtotal / cantidad
            BigDecimal pUnitario = subtotal.divide( cantidad, BigDecimal.ROUND_HALF_EVEN );
            
            Map<String, BigDecimal>  tasasImpuesto = getTiposImpuesto(producto);
            renglon.cantidad       = cantidad;
            renglon.codigo         = producto.getCodigo();
            renglon.descripcion    = producto.getDescripcion();
            
            renglon.impuestos      = tasasImpuesto.containsKey("IVA"); //IVA al 0 es ignorado en getTiposImpuesto()
            renglon.ieps           = tasasImpuesto.get("IEPS");
            renglon.ieps           = renglon.ieps == null ? new BigDecimal("0") : renglon.ieps;
            renglon.precioUnitario = pUnitario;
            renglon.unidad         = producto.getUnidad();
            renglon.importe        = new BigDecimal(vd.getTotal(), mc).round(mc).setScale(4, BigDecimal.ROUND_UP);
            renglon.descuento      = descuento;

            ticket.add(renglon);
        }

        return ticket;
    }

    /**
     * @return the idAlmacen
     */
    public Integer getIdAlmacen() {
        return idAlmacen;
    }

    /**
     * @param idAlmacen the idAlmacen to set
     */
    public void setIdAlmacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    /**
     * @return the idCaja
     */
    public Integer getIdCaja() {
        return idCaja;
    }

    /**
     * @param idCaja the idCaja to set
     */
    public void setIdCaja(Integer idCaja) {
        this.idCaja = idCaja;
    }

    /**
     * @return the idVenta
     */
    public Integer getIdVenta() {
        return idVenta;
    }

    /**
     * @param idVenta the idVenta to set
     */
    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
    
}
