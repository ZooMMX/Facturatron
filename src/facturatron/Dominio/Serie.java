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
    private String serie;
    private BigInteger folioActual;
    private String tipo = "ingreso";   

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
