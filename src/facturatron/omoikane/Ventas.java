/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.omoikane;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author octavioruizcastillo
 */
@Entity
@Table(name = "ventas")
@NamedQueries({
    @NamedQuery(name = "Ventas.findAll", query = "SELECT v FROM Ventas v"),
    @NamedQuery(name = "Ventas.findByIdVenta", query = "SELECT v FROM Ventas v WHERE v.ventasPK.idVenta = :idVenta"),
    @NamedQuery(name = "Ventas.findByIdCaja", query = "SELECT v FROM Ventas v WHERE v.ventasPK.idCaja = :idCaja"),
    @NamedQuery(name = "Ventas.findByIdAlmacen", query = "SELECT v FROM Ventas v WHERE v.ventasPK.idAlmacen = :idAlmacen"),
    @NamedQuery(name = "Ventas.findByIdCliente", query = "SELECT v FROM Ventas v WHERE v.idCliente = :idCliente"),
    @NamedQuery(name = "Ventas.findByFechaHora", query = "SELECT v FROM Ventas v WHERE v.fechaHora = :fechaHora"),
    @NamedQuery(name = "Ventas.findByUModificacion", query = "SELECT v FROM Ventas v WHERE v.uModificacion = :uModificacion"),
    @NamedQuery(name = "Ventas.findByFacturada", query = "SELECT v FROM Ventas v WHERE v.facturada = :facturada"),
    @NamedQuery(name = "Ventas.findByCompletada", query = "SELECT v FROM Ventas v WHERE v.completada = :completada"),
    @NamedQuery(name = "Ventas.findBySubtotal", query = "SELECT v FROM Ventas v WHERE v.subtotal = :subtotal"),
    @NamedQuery(name = "Ventas.findByDescuento", query = "SELECT v FROM Ventas v WHERE v.descuento = :descuento"),
    @NamedQuery(name = "Ventas.findByImpuestos", query = "SELECT v FROM Ventas v WHERE v.impuestos = :impuestos"),
    @NamedQuery(name = "Ventas.findByTotal", query = "SELECT v FROM Ventas v WHERE v.total = :total"),
    @NamedQuery(name = "Ventas.findByIdUsuario", query = "SELECT v FROM Ventas v WHERE v.idUsuario = :idUsuario"),
    @NamedQuery(name = "Ventas.findByEfectivo", query = "SELECT v FROM Ventas v WHERE v.efectivo = :efectivo"),
    @NamedQuery(name = "Ventas.findByCambio", query = "SELECT v FROM Ventas v WHERE v.cambio = :cambio"),
    @NamedQuery(name = "Ventas.findByCentecimosredondeados", query = "SELECT v FROM Ventas v WHERE v.centecimosredondeados = :centecimosredondeados"),
    @NamedQuery(name = "Ventas.findByFolio", query = "SELECT v FROM Ventas v WHERE v.folio = :folio")})
public class Ventas implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VentasPK ventasPK;
    @Column(name = "id_cliente")
    private Integer idCliente;
    @Basic(optional = false)
    @Column(name = "fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @Basic(optional = false)
    @Column(name = "uModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uModificacion;
    @Column(name = "facturada")
    private Integer facturada;
    @Column(name = "completada")
    private Integer completada;
    @Column(name = "subtotal")
    private Double subtotal;
    @Column(name = "descuento")
    private Double descuento;
    @Column(name = "impuestos")
    private Double impuestos;
    @Column(name = "total")
    private Double total;
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private int idUsuario;
    @Basic(optional = false)
    @Column(name = "efectivo")
    private double efectivo;
    @Basic(optional = false)
    @Column(name = "cambio")
    private double cambio;
    @Basic(optional = false)
    @Column(name = "centecimosredondeados")
    private double centecimosredondeados;
    @Basic(optional = false)
    @Column(name = "folio")
    private long folio;

    public Ventas() {
    }

    public Ventas(VentasPK ventasPK) {
        this.ventasPK = ventasPK;
    }

    public Ventas(VentasPK ventasPK, Date fechaHora, Date uModificacion, byte[] eliminar, int idUsuario, double efectivo, double cambio, double centecimosredondeados, long folio) {
        this.ventasPK = ventasPK;
        this.fechaHora = fechaHora;
        this.uModificacion = uModificacion;
        this.idUsuario = idUsuario;
        this.efectivo = efectivo;
        this.cambio = cambio;
        this.centecimosredondeados = centecimosredondeados;
        this.folio = folio;
    }

    public Ventas(int idVenta, int idCaja, int idAlmacen) {
        this.ventasPK = new VentasPK(idVenta, idCaja, idAlmacen);
    }

    public VentasPK getVentasPK() {
        return ventasPK;
    }

    public void setVentasPK(VentasPK ventasPK) {
        this.ventasPK = ventasPK;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Date getUModificacion() {
        return uModificacion;
    }

    public void setUModificacion(Date uModificacion) {
        this.uModificacion = uModificacion;
    }

    public Integer getFacturada() {
        return facturada;
    }

    public void setFacturada(Integer facturada) {
        this.facturada = facturada;
    }

    public Integer getCompletada() {
        return completada;
    }

    public void setCompletada(Integer completada) {
        this.completada = completada;
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

    public Double getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(Double impuestos) {
        this.impuestos = impuestos;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getEfectivo() {
        return efectivo;
    }

    public void setEfectivo(double efectivo) {
        this.efectivo = efectivo;
    }

    public double getCambio() {
        return cambio;
    }

    public void setCambio(double cambio) {
        this.cambio = cambio;
    }

    public double getCentecimosredondeados() {
        return centecimosredondeados;
    }

    public void setCentecimosredondeados(double centecimosredondeados) {
        this.centecimosredondeados = centecimosredondeados;
    }

    public long getFolio() {
        return folio;
    }

    public void setFolio(long folio) {
        this.folio = folio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ventasPK != null ? ventasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ventas)) {
            return false;
        }
        Ventas other = (Ventas) object;
        if ((this.ventasPK == null && other.ventasPK != null) || (this.ventasPK != null && !this.ventasPK.equals(other.ventasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "facturatron.omoikane.Ventas[ventasPK=" + ventasPK + "]";
    }

}
