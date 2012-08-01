/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.facturacion;

import facturatron.Dominio.Factura;
import facturatron.MVC.Model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;

/**
 *
 * @author Octavio
 */
public class ListadoModel extends Model {
    private FacturaDao dao;
    private ArrayList<FacturaDao> facturas = new ArrayList<FacturaDao>();
    private Date fechaInicial  ;
    private Date fechaFinal;
    private BigDecimal subtotal = new BigDecimal(0d);
    private BigDecimal iva      = new BigDecimal(0d);
    private BigDecimal total    = new BigDecimal(0d);

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
    public ArrayList<FacturaDao> getFacturas() {
        return facturas;
    }

    /**
     * @param facturas the facturas to set
     */
    public void setFacturas(ArrayList<FacturaDao> facturas) {
        if(this.facturas != null) {
            this.facturas.clear();
            this.facturas.addAll( facturas );
        } else {
            this.facturas = facturas;
        }
    }

    private void updateSumas() {
        BigDecimal sub, imp, tot;
        sub = new BigDecimal(0d);
        imp = new BigDecimal(0d);
        tot = new BigDecimal(0d);
        for (Factura factura : this.facturas) {
            sub = sub.add(factura.getSubtotal());
            imp = imp.add(factura.getIvaTrasladado());
            tot = tot.add(factura.getTotal());
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
    public BigDecimal getSubtotal() {
        return subtotal;
    }

    /**
     * @param subtotal the subtotal to set
     */
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * @return the iva
     */
    public BigDecimal getIva() {
        return iva;
    }

    /**
     * @param iva the iva to set
     */
    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
