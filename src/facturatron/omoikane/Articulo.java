/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.omoikane;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import static org.eclipse.persistence.config.ExclusiveConnectionMode.Transactional;

/**
 *
 * @author octavioruizcastillo
 */
@Entity
@Table(name = "articulos")
@NamedQueries({
    @NamedQuery(name = "Articulo.findAll", query = "SELECT a FROM Articulo a"),
    @NamedQuery(name = "Articulo.findByIdArticulo", query = "SELECT a FROM Articulo a WHERE a.idArticulo = :idArticulo"),
    @NamedQuery(name = "Articulo.findByCodigo", query = "SELECT a FROM Articulo a WHERE a.codigo = :codigo"),
    @NamedQuery(name = "Articulo.findByIdLinea", query = "SELECT a FROM Articulo a WHERE a.idLinea = :idLinea"),
    @NamedQuery(name = "Articulo.findByDescripcion", query = "SELECT a FROM Articulo a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "Articulo.findByUnidad", query = "SELECT a FROM Articulo a WHERE a.unidad = :unidad"),
    @NamedQuery(name = "Articulo.findByImpuestos", query = "SELECT a FROM Articulo a WHERE a.impuestos = :impuestos"),
    @NamedQuery(name = "Articulo.findByUModificacion", query = "SELECT a FROM Articulo a WHERE a.uModificacion = :uModificacion"),
    
    
    @NamedQuery(name = "Articulo.findByClaveProductoSat", query = "SELECT a FROM Articulo a WHERE a.claveProductoSat = :claveProductoSat"),
    @NamedQuery(name = "Articulo.findByClaveUnidadSat", query = "SELECT a FROM Articulo a WHERE a.claveUnidadSat = :claveUnidadSat"),
    
    @NamedQuery(name = "Articulo.findByVersion", query = "SELECT a FROM Articulo a WHERE a.version = :version"),
    @NamedQuery(name = "Articulo.findByIdGrupo", query = "SELECT a FROM Articulo a WHERE a.idGrupo = :idGrupo")})
public class Articulo implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "esPaquete")
    private boolean esPaquete;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_articulo")
    private Integer idArticulo;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "id_linea")
    private Integer idLinea;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "unidad")
    private String unidad;
    @Column(name = "impuestos")
    private Double sumaImpuestos;
    @Basic(optional = false)
    @Column(name = "uModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uModificacion;
    @Column(name = "version")
    private Integer version;
    @Basic(optional = false)
    @Column(name = "id_grupo")
    private int idGrupo;
    @Basic(optional = false)
    @Column(name = "CLAVE_PRODUCTO_SAT_CLAVE")
    private String claveProductoSat;
    @Basic(optional = false)
    @Column(name = "CLAVE_UNIDAD_SAT_CLAVE")
    private String claveUnidadSat;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(name = "articulos_Impuesto", joinColumns = @JoinColumn(name = "articulos_id_articulo", referencedColumnName = "id_articulo"))
    private Collection<Impuesto> impuestos;

    public Collection<Impuesto> getImpuestos() {
        Collection<Impuesto> i = impuestos;
        return i;
    }

    public void setImpuestos(Collection<Impuesto> impuestos) {
        this.impuestos = impuestos;
    }

    public Articulo() {
    }

    public Articulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Articulo(Integer idArticulo, Date uModificacion, int idGrupo) {
        this.idArticulo = idArticulo;
        this.uModificacion = uModificacion;
        this.idGrupo = idGrupo;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(Integer idLinea) {
        this.idLinea = idLinea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public Double getSumaImpuestos() {
        return sumaImpuestos;
    }

    public void setSumaImpuestos(Double sumaImpuestos) {
        this.sumaImpuestos = sumaImpuestos;
    }

    public Date getUModificacion() {
        return uModificacion;
    }

    public void setUModificacion(Date uModificacion) {
        this.uModificacion = uModificacion;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArticulo != null ? idArticulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articulo)) {
            return false;
        }
        Articulo other = (Articulo) object;
        if ((this.idArticulo == null && other.idArticulo != null) || (this.idArticulo != null && !this.idArticulo.equals(other.idArticulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "facturatron.omoikane.Articulos[idArticulo=" + idArticulo + "]";
    }

    public boolean getEsPaquete() {
        return esPaquete;
    }

    public void setEsPaquete(boolean esPaquete) {
        this.esPaquete = esPaquete;
    }

    /**
     * @return the claveProductoSat
     */
    public String getClaveProductoSat() {
        return claveProductoSat;
    }

    /**
     * @param claveProductoSat the claveProductoSat to set
     */
    public void setClaveProductoSat(String claveProductoSat) {
        this.claveProductoSat = claveProductoSat;
    }

    /**
     * @return the claveUnidadSat
     */
    public String getClaveUnidadSat() {
        return claveUnidadSat;
    }

    /**
     * @param claveUnidadSat the claveUnidadSat to set
     */
    public void setClaveUnidadSat(String claveUnidadSat) {
        this.claveUnidadSat = claveUnidadSat;
    }

}
