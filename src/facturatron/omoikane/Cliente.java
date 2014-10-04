/*
 * Copyright (C) 2014 octavioruizcastillo
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package facturatron.omoikane;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author octavioruizcastillo
 */
@Entity
@Table(name="Cliente")
public class Cliente {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private int id = 0;

    @Column(length = 255)
    @NotEmpty
    private String nombre = "";

    @Column(name = "saldo")
    @Basic
    @NotNull
    private BigDecimal saldo = new BigDecimal("0.00");

    @Column(name = "RFC")
    @Basic
    @NotEmpty
    private String rfc;

    @Column(name = "actualizacion")
    @Basic
    @NotNull
    private Timestamp actualizacion;

    @Column(name = "creacion")
    @Basic
    @NotNull
    private Timestamp creacion;

    @Column(name = "listaDePrecios_id")
    private Integer listaDePreciosId = 0;

    public Integer getListaDePreciosId() {
        return listaDePreciosId;
    }

    public void setListaDePreciosId(Integer listaDePreciosId) {
        this.listaDePreciosId = listaDePreciosId;
    }

    @PrePersist
    protected void onCreate() {
        creacion = new Timestamp(Calendar.getInstance().getTime().getTime());
        actualizacion = new Timestamp(Calendar.getInstance().getTime().getTime());
    }

    @PreUpdate
    protected void onUpdate() {
        actualizacion = new Timestamp(Calendar.getInstance().getTime().getTime());
    }

    public int getId() {
        return id;
    }

    public void setId(int clienteId) {
        this.id = clienteId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Timestamp getActualizacion() {
        return actualizacion;
    }

    public void setActualizacion(Timestamp umodificacion) {
        this.actualizacion = umodificacion;
    }

    public Timestamp getCreacion() {
        return creacion;
    }

    public void setCreacion(Timestamp creacion) {
        this.creacion = creacion;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;

        if (id != cliente.id) return false;
        if (cliente.saldo.compareTo(saldo) != 0) return false;
        if (nombre.equals(cliente.getNombre())) return false;
        if (actualizacion != null ? !actualizacion.equals(cliente.actualizacion) : cliente.actualizacion != null)
            return false;
        if (creacion != null ? !creacion.equals(cliente.creacion) : cliente.creacion != null)
                    return false;

        return true;
    }

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Collection<Ventas> ventas;

    public Collection<Ventas> getVentas() {
        return ventas;
    }

    public void setVentas(Collection<Ventas> ventas) {
        this.ventas = ventas;
    }
}
