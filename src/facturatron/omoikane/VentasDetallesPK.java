/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.omoikane;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author octavioruizcastillo
 */
@Embeddable
public class VentasDetallesPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_renglon")
    private int idRenglon;
    @Basic(optional = false)
    @Column(name = "id_venta")
    private int idVenta;
    @Basic(optional = false)
    @Column(name = "id_caja")
    private int idCaja;
    @Basic(optional = false)
    @Column(name = "id_almacen")
    private int idAlmacen;

    public VentasDetallesPK() {
    }

    public VentasDetallesPK(int idRenglon, int idVenta, int idCaja, int idAlmacen) {
        this.idRenglon = idRenglon;
        this.idVenta = idVenta;
        this.idCaja = idCaja;
        this.idAlmacen = idAlmacen;
    }

    public int getIdRenglon() {
        return idRenglon;
    }

    public void setIdRenglon(int idRenglon) {
        this.idRenglon = idRenglon;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idRenglon;
        hash += (int) idVenta;
        hash += (int) idCaja;
        hash += (int) idAlmacen;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentasDetallesPK)) {
            return false;
        }
        VentasDetallesPK other = (VentasDetallesPK) object;
        if (this.idRenglon != other.idRenglon) {
            return false;
        }
        if (this.idVenta != other.idVenta) {
            return false;
        }
        if (this.idCaja != other.idCaja) {
            return false;
        }
        if (this.idAlmacen != other.idAlmacen) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "facturatron.omoikane.VentasDetallesPK[idRenglon=" + idRenglon + ", idVenta=" + idVenta + ", idCaja=" + idCaja + ", idAlmacen=" + idAlmacen + "]";
    }

}
