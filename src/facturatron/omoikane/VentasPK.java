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
public class VentasPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_venta")
    private int idVenta;
    @Basic(optional = false)
    @Column(name = "id_caja")
    private int idCaja;
    @Basic(optional = false)
    @Column(name = "id_almacen")
    private int idAlmacen;

    public VentasPK() {
    }

    public VentasPK(int idVenta, int idCaja, int idAlmacen) {
        this.idVenta = idVenta;
        this.idCaja = idCaja;
        this.idAlmacen = idAlmacen;
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
        hash += (int) idVenta;
        hash += (int) idCaja;
        hash += (int) idAlmacen;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentasPK)) {
            return false;
        }
        VentasPK other = (VentasPK) object;
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
        return "facturatron.omoikane.VentasPK[idVenta=" + idVenta + ", idCaja=" + idCaja + ", idAlmacen=" + idAlmacen + "]";
    }

}
