/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.omoikane;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

/**
 *
 * @author octavioruizcastillo
 */
@Entity
@Table(name = "ventas_detalles")
@NamedQueries({
    @NamedQuery(name = "VentasDetalles.findAll", query = "SELECT v FROM VentasDetalles v"),
    @NamedQuery(name = "VentasDetalles.findByIdRenglon", query = "SELECT v FROM VentasDetalles v WHERE v.idRenglon = :idRenglon"),
    @NamedQuery(name = "VentasDetalles.findByIdVenta", query = "SELECT v FROM VentasDetalles v WHERE v.idVenta = :idVenta"),
    @NamedQuery(name = "VentasDetalles.findByIdCaja", query = "SELECT v FROM VentasDetalles v WHERE v.idCaja = :idCaja"),
    @NamedQuery(name = "VentasDetalles.findByIdAlmacen", query = "SELECT v FROM VentasDetalles v WHERE v.idAlmacen = :idAlmacen"),
    @NamedQuery(name = "VentasDetalles.findByIdArticulo", query = "SELECT v FROM VentasDetalles v WHERE v.idArticulo = :idArticulo"),
    @NamedQuery(name = "VentasDetalles.findByPrecio", query = "SELECT v FROM VentasDetalles v WHERE v.precio = :precio"),
    @NamedQuery(name = "VentasDetalles.findByCantidad", query = "SELECT v FROM VentasDetalles v WHERE v.cantidad = :cantidad"),
    @NamedQuery(name = "VentasDetalles.findByTipoSalida", query = "SELECT v FROM VentasDetalles v WHERE v.tipoSalida = :tipoSalida"),
    @NamedQuery(name = "VentasDetalles.findBySubtotal", query = "SELECT v FROM VentasDetalles v WHERE v.subtotal = :subtotal"),
    @NamedQuery(name = "VentasDetalles.findByImpuestos", query = "SELECT v FROM VentasDetalles v WHERE v.impuestos = :impuestos"),
    @NamedQuery(name = "VentasDetalles.findByDescuento", query = "SELECT v FROM VentasDetalles v WHERE v.descuento = :descuento"),
    @NamedQuery(name = "VentasDetalles.findByTotal", query = "SELECT v FROM VentasDetalles v WHERE v.total = :total"),
    @NamedQuery(name = "VentasDetalles.findByIdLinea", query = "SELECT v FROM VentasDetalles v WHERE v.idLinea = :idLinea"),
    
    @NamedQuery(name = "VentasDetalles.sumTotalIntervalOfIDs", query = "SELECT sum(v.total) FROM VentasDetalles v WHERE v.venta.id between :idinicial and :idfinal"),
    @NamedQuery(name = "VentasDetalles.sumSubtotalIntervalOfIDs", query = "SELECT sum(v.subtotal) FROM VentasDetalles v WHERE v.venta.id between :idinicial and :idfinal"),
    @NamedQuery(name = "VentasDetalles.sumImpuestosIntervalOfIDs", query = "SELECT sum(v.impuestos) FROM VentasDetalles v WHERE v.venta.id between :idinicial and :idfinal")})
public class VentasDetalles implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_renglon")
    private int idRenglon;
    @ManyToOne
    @JoinColumn(name = "id_venta")
    private Ventas venta;
    @Column(name = "id_venta",updatable = false,insertable = false)
    private Long idVenta;
    @Basic(optional = false)
    @Column(name = "id_caja")
    private int idCaja;
    @Basic(optional = false)
    @Column(name = "id_almacen")
    private int idAlmacen;
    @Column(name = "id_articulo")
    private Integer idArticulo;
    @Basic(optional = false)
    @Column(name = "precio")
    private double precio;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad")
    private Double cantidad;
    @Column(name = "tipo_salida")
    private String tipoSalida;
    @Column(name = "subtotal")
    private Double subtotal;
    @Column(name = "impuestos")
    private Double impuestos;
    @Column(name = "descuento")
    private Double descuento;
    @Column(name = "total")
    private Double total;
    @Column(name = "id_linea")
    private Integer idLinea;

    @ElementCollection()
    @CollectionTable(
            name="ventas_detalles_impuestos",
            joinColumns=@JoinColumn(name="id_renglon")
    )
    @OrderColumn
    private List<VentaDetalleImpuesto> ventaDetalleImpuestos;

    public VentasDetalles() {

    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public int getIdRenglon() {
        return idRenglon;
    }

    public void setIdRenglon(int idRenglon) {
        this.idRenglon = idRenglon;
    }

    public Ventas getVenta() {
        return venta;
    }

    public void setVenta(Ventas venta) {
        this.venta = venta;
    }

    public int getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(int idCaja) {
        this.idCaja = idCaja;
    }

    public int getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(int idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipoSalida() {
        return tipoSalida;
    }

    public void setTipoSalida(String tipoSalida) {
        this.tipoSalida = tipoSalida;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(Integer idLinea) {
        this.idLinea = idLinea;
    }

    public void setVentaDetalleImpuestos(List<VentaDetalleImpuesto> ventaDetalleImpuestos) {
        this.ventaDetalleImpuestos = ventaDetalleImpuestos;
    }

    public List<VentaDetalleImpuesto> getVentaDetalleImpuestos() {
        return ventaDetalleImpuestos;
    }

    public Double getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(Double impuestos) {
        this.impuestos = impuestos;
    }

}
