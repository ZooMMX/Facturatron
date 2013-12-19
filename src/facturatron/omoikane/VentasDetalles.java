/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.omoikane;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author octavioruizcastillo
 */
@Entity
@Table(name = "ventas_detalles")
@NamedQueries({
    @NamedQuery(name = "VentasDetalles.findAll", query = "SELECT v FROM VentasDetalles v"),
    @NamedQuery(name = "VentasDetalles.findByIdRenglon", query = "SELECT v FROM VentasDetalles v WHERE v.ventasDetallesPK.idRenglon = :idRenglon"),
    @NamedQuery(name = "VentasDetalles.findByIdVenta", query = "SELECT v FROM VentasDetalles v WHERE v.ventasDetallesPK.idVenta = :idVenta"),
    @NamedQuery(name = "VentasDetalles.findByIdCaja", query = "SELECT v FROM VentasDetalles v WHERE v.ventasDetallesPK.idCaja = :idCaja"),
    @NamedQuery(name = "VentasDetalles.findByIdAlmacen", query = "SELECT v FROM VentasDetalles v WHERE v.ventasDetallesPK.idAlmacen = :idAlmacen"),
    @NamedQuery(name = "VentasDetalles.findByIdArticulo", query = "SELECT v FROM VentasDetalles v WHERE v.idArticulo = :idArticulo"),
    @NamedQuery(name = "VentasDetalles.findByPrecio", query = "SELECT v FROM VentasDetalles v WHERE v.precio = :precio"),
    @NamedQuery(name = "VentasDetalles.findByCantidad", query = "SELECT v FROM VentasDetalles v WHERE v.cantidad = :cantidad"),
    @NamedQuery(name = "VentasDetalles.findByTipoSalida", query = "SELECT v FROM VentasDetalles v WHERE v.tipoSalida = :tipoSalida"),
    @NamedQuery(name = "VentasDetalles.findBySubtotal", query = "SELECT v FROM VentasDetalles v WHERE v.subtotal = :subtotal"),
    @NamedQuery(name = "VentasDetalles.findByImpuestos", query = "SELECT v FROM VentasDetalles v WHERE v.impuestos = :impuestos"),
    @NamedQuery(name = "VentasDetalles.findByDescuento", query = "SELECT v FROM VentasDetalles v WHERE v.descuento = :descuento"),
    @NamedQuery(name = "VentasDetalles.findByTotal", query = "SELECT v FROM VentasDetalles v WHERE v.total = :total"),
    @NamedQuery(name = "VentasDetalles.findByIdLinea", query = "SELECT v FROM VentasDetalles v WHERE v.idLinea = :idLinea"),
    @NamedQuery(name = "VentasDetalles.sumTotalIntervalOfIDs", query = "SELECT sum(v.total) FROM VentasDetalles v WHERE v.ventasDetallesPK.idVenta between :idinicial and :idfinal"),
    @NamedQuery(name = "VentasDetalles.sumSubtotalIntervalOfIDs", query = "SELECT sum(v.subtotal) FROM VentasDetalles v WHERE v.ventasDetallesPK.idVenta between :idinicial and :idfinal"),
    @NamedQuery(name = "VentasDetalles.sumImpuestosIntervalOfIDs", query = "SELECT sum(v.impuestos) FROM VentasDetalles v WHERE v.ventasDetallesPK.idVenta between :idinicial and :idfinal")})
public class VentasDetalles implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VentasDetallesPK ventasDetallesPK;
    @Column(name = "id_articulo")
    private Integer idArticulo;
    @Basic(optional = false)
    @Column(name = "precio")
    private double precio;
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

    public VentasDetalles() {
    }

    public VentasDetalles(VentasDetallesPK ventasDetallesPK) {
        this.ventasDetallesPK = ventasDetallesPK;
    }

    public VentasDetalles(VentasDetallesPK ventasDetallesPK, double precio) {
        this.ventasDetallesPK = ventasDetallesPK;
        this.precio = precio;
    }

    public VentasDetalles(int idRenglon, int idVenta, int idCaja, int idAlmacen) {
        this.ventasDetallesPK = new VentasDetallesPK(idRenglon, idVenta, idCaja, idAlmacen);
    }

    public VentasDetallesPK getVentasDetallesPK() {
        return ventasDetallesPK;
    }

    public void setVentasDetallesPK(VentasDetallesPK ventasDetallesPK) {
        this.ventasDetallesPK = ventasDetallesPK;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
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

    public Double getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(Double impuestos) {
        this.impuestos = impuestos;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ventasDetallesPK != null ? ventasDetallesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentasDetalles)) {
            return false;
        }
        VentasDetalles other = (VentasDetalles) object;
        if ((this.ventasDetallesPK == null && other.ventasDetallesPK != null) || (this.ventasDetallesPK != null && !this.ventasDetallesPK.equals(other.ventasDetallesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "facturatron.omoikane.VentasDetalles[ventasDetallesPK=" + ventasDetallesPK + "]";
    }

}
