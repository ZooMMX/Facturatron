/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.facturacion;

import facturatron.Dominio.Factura;
import facturatron.MVC.Model;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;

/**
 *
 * @author Octavio
 */
public class ListadoModel extends Model {
    private FacturaDao dao;
    private ArrayList<Factura> facturas = new ArrayList<Factura>();
    private Date fechaInicial  ;
    private Date fechaFinal;
    private Double subtotal = 0d;
    private Double iva = 0d;
    private Double total = 0d;

    ListadoModel() {
        setDao(new FacturaDao());
    }

    public void load() {
        setFacturas(getDao().findAll(getFechaInicial(), getFechaFinal()));
        updateSumas();
        setChanged();
        notifyObservers();
    }

    /**
     * @return the dao
     */
    public FacturaDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(FacturaDao dao) {
        this.dao = dao;
    }

    /**
     * @return the facturas
     */
    public ArrayList<Factura> getFacturas() {
        return facturas;
    }

    /**
     * @param facturas the facturas to set
     */
    public void setFacturas(ArrayList<Factura> facturas) {
        if(this.facturas != null) {
            this.facturas.clear();
            this.facturas.addAll( facturas );
        } else {
            this.facturas = facturas;
        }
    }

    private void updateSumas() {
        Double sub, imp, tot;
        sub=imp=tot=0d;
        for (Factura factura : this.facturas) {
            sub   += factura.getSubtotal();
            imp   += factura.getIvaTrasladado();
            tot   += factura.getTotal();
        }
        setSubtotal(sub);
        setIva(imp);
        setTotal(tot);
    }

    /**
     * @return the fechaInicial
     */
    public Date getFechaInicial() {
        if(fechaInicial == null) {
            Calendar hoy = Calendar.getInstance();
            hoy.add(Calendar.MONTH, -1);
            setFechaInicial(new Date(hoy.getTimeInMillis()));
        }
        return fechaInicial;
    }

    /**
     * @param fechaInicial the fechaInicial to set
     */
    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
        load();
    }

    /**
     * @return the fechaFinal
     */
    public Date getFechaFinal() {
        if(fechaFinal == null) {
            setFechaFinal(new Date(Calendar.getInstance().getTimeInMillis()));
        }
        return fechaFinal;
    }

    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
        load();
    }

    /**
     * @return the subtotal
     */
    public Double getSubtotal() {
        return subtotal;
    }

    /**
     * @param subtotal the subtotal to set
     */
    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * @return the iva
     */
    public Double getIva() {
        return iva;
    }

    /**
     * @param iva the iva to set
     */
    public void setIva(Double iva) {
        this.iva = iva;
    }

    /**
     * @return the total
     */
    public Double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Double total) {
        this.total = total;
    }

}
