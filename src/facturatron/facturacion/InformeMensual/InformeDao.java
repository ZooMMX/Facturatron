/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.facturacion.InformeMensual;

import facturatron.Dominio.Configuracion;
import facturatron.Dominio.Factura;
import facturatron.MVC.Model;
import facturatron.cliente.ClienteDao;
import facturatron.config.ConfiguracionDao;
import facturatron.facturacion.FacturaDao;
import java.util.ArrayList;
import java.util.Calendar;
import phesus.facturatron.lib.InformeMensual;
import java.sql.Date;
import java.util.regex.Pattern;
import phesus.facturatron.lib.entities.ComprobanteTron;

/**
 *
 * @author Octavio
 */
public class InformeDao extends Model {
    private int anio;
    private int mes;
    private ArrayList<ComprobanteTron> comprobantes;
    private InformeMensual informe;

    private Configuracion config;

    /** Carga el archivo de configuración y lo almacena en memoria.
     * Importante: Sólo carga el archivo de configuración una vez por instancia, las demás
     * las toma del atributo config (caché)
     * @return
     */
    Configuracion getConfig() {
        if(config == null) { config = (new ConfiguracionDao()).load(); }
        return config;
    }

    /**
     * @return the anio
     */
    public int getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

    /**
     * @return the mes
     */
    public int getMes() {
        return mes;
    }

    /** Consulta la base de datos y rellena el informe mensual para el SAT
     *
     */
    public void generar() throws Exception {
        Calendar desde = Calendar.getInstance();
        Calendar hasta;
        desde.clear();

        desde.set(Calendar.MONTH, mes);
        desde.set(Calendar.YEAR, anio);
        hasta = (Calendar) desde.clone();
        hasta.set(Calendar.DAY_OF_MONTH,
                hasta.getActualMaximum(Calendar.DAY_OF_MONTH));
        ArrayList<FacturaDao> facturas = (new FacturaDao()).findAll(
                new Date(desde.getTime().getTime()),
                new Date(hasta.getTime().getTime()));
        ArrayList<ComprobanteTron> comps = new ArrayList<ComprobanteTron>();
        for (Factura fact : facturas) {
            comps.add(((FacturaDao)fact).toComprobanteTron());
        }
        setComprobantes(comprobantes);
        ClienteDao p = (new ClienteDao()).findBy(1);
        //Cliente 1 = Emisor
        InformeMensual im = new InformeMensual(
                InformeMensual.Esquema.CFD,
                p.toEmisor(p), 
                desde);
        im.cargarComprobantes(comps);
        im.toTXTFile(getFolderDest());
    }

    public String getFolderDest() {
        return getConfig().getPathPdf().replaceAll("([_.0-9a-zA-Z]+)$", "");
    }
    /**
     * @param mes the mes to set
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    /**
     * @return the comprobantes
     */
    public ArrayList<ComprobanteTron> getComprobantes() {
        return comprobantes;
    }

    /**
     * @param comprobantes the comprobantes to set
     */
    private void setComprobantes(ArrayList<ComprobanteTron> comprobantes) {
        this.comprobantes = comprobantes;
    }

    /**
     * @return the informe
     */
    public InformeMensual getInforme() {
        return informe;
    }

    /**
     * @param informe the informe to set
     */
    private void setInforme(InformeMensual informe) {
        this.informe = informe;
    }

}
