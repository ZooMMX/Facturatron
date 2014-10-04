/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.omoikane;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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
    @NamedQuery(name = "Ventas.findByIdVenta", query = "SELECT v FROM Ventas v WHERE v.id = :idVenta"),
    @NamedQuery(name = "Ventas.findByIdCaja", query = "SELECT v FROM Ventas v WHERE v.idCaja = :idCaja"),
    @NamedQuery(name = "Ventas.findByIdAlmacen", query = "SELECT v FROM Ventas v WHERE v.idAlmacen = :idAlmacen"),
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
    @NamedQuery(name = "Ventas.findByFolio", query = "SELECT v FROM Ventas v WHERE v.folio = :folio"),
    
//Colección de todas las ventas en un intervalo de IDs y que no han sido facturadas
    @NamedQuery(name = "Ventas.findByIdIntervalANDNoFacturada", query = "SELECT v FROM Ventas v WHERE v.id between :idinicial and :idfinal AND v.facturada = 0"),
})
public class Ventas implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "id_venta")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "id_caja")
    private int idCaja;

    @Column(name = "id_almacen")
    private int idAlmacen;

    //Se usará el atributo idCliente para establecer el cliente relacionado, por causas de comodidad y performance, esa es la
    //  razón de usar updatable e insertable = false
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_cliente", updatable = false, insertable = false)
    private Cliente cliente;

    @Column(name = "id_cliente")
    private Integer idCliente = 1;

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
    private Boolean completada;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
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
    private Long folio;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "venta")
    List<VentasDetalles> items;

    public void addItem(VentasDetalles item) {
        if(getItems() == null) {
            setItems(new ArrayList<VentasDetalles>());
        }
        items.add(item);
        item.setVenta(this);
    }

    public Ventas() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    @PrePersist
    protected void onCreate() {
        setUModificacion( new Timestamp(Calendar.getInstance().getTime().getTime()) );
    }

    @PreUpdate
    protected void onUpdate() {
        setUModificacion(new Timestamp(Calendar.getInstance().getTime().getTime()));
    }

    public Integer getFacturada() {
        return facturada;
    }

    public void setFacturada(Integer facturada) {
        this.facturada = facturada;
    }

    public Boolean getCompletada() {
        return completada;
    }

    public void setCompletada(Boolean completada) {
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

    public Long getFolio() {
        return folio;
    }

    public void setFolio(Long folio) {
        this.folio = folio;
    }

    public List<VentasDetalles> getItems() {
        if(items == null) items = new ArrayList<VentasDetalles>();
        return items;
    }

    public void setItems(List<VentasDetalles> lvd) { items = lvd; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Ventas)) {
            return false;
        }
        Ventas other = (Ventas) object;
        if (this.id == other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.LegacyVenta[ legacyVentaPK=" + id + " ]";
    }


    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

}
