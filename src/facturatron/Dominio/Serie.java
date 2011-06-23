/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.Dominio;

import facturatron.MVC.Model;
import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * @author Octavio
 */
public class Serie extends Model implements Serializable {
    private BigInteger noAprobacion;
    private String serie;
    private Integer anoAprobacion;
    private String noCertificado;
    private Integer folioInicial;
    private Integer folioFinal;
    private BigInteger folioActual;
    private String tipo = "ingreso";

    /**
     * @return the noAprobacion
     */
    public BigInteger getNoAprobacion() {
        return noAprobacion;
    }

    /**
     * @param noAprobacion the noAprobacion to set
     */
    public void setNoAprobacion(BigInteger noAprobacion) {
        this.noAprobacion = noAprobacion;
    }

    /**
     * @return the serie
     */
    public String getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(String serie) {
        this.serie = serie;
    }

    /**
     * @return the anoAprobacion
     */
    public Integer getAnoAprobacion() {
        return anoAprobacion;
    }

    /**
     * @param anoAprobacion the anoAprobacion to set
     */
    public void setAnoAprobacion(Integer anoAprobacion) {
        this.anoAprobacion = anoAprobacion;
    }

    /**
     * @return the noCertificado
     */
    public String getNoCertificado() {
        return noCertificado;
    }

    /**
     * @param noCertificado the noCertificado to set
     */
    public void setNoCertificado(String noCertificado) {
        this.noCertificado = noCertificado;
    }

    /**
     * @return the folioInicial
     */
    public Integer getFolioInicial() {
        return folioInicial;
    }

    /**
     * @param folioInicial the folioInicial to set
     */
    public void setFolioInicial(Integer folioInicial) {
        this.folioInicial = folioInicial;
    }

    /**
     * @return the folioFinal
     */
    public Integer getFolioFinal() {
        return folioFinal;
    }

    /**
     * @param folioFinal the folioFinal to set
     */
    public void setFolioFinal(Integer folioFinal) {
        this.folioFinal = folioFinal;
    }

    /**
     * @return the folioActual
     */
    public BigInteger getFolioActual() {
        return folioActual;
    }

    /**
     * @param folioActual the folioActual to set
     */
    public void setFolioActual(BigInteger folioActual) {
        this.folioActual = folioActual;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
}
