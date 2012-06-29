/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.Dominio;

/**
 *
 * @author octavioruizcastillo
 */
public enum MetodoDePago {
    CHEQUE ("Cheque"),
    TARJETA ("Tarjeta de crédito o débito"),
    DEPOSITO ("Depósito en cuenta"),
    EFECTIVO ("Efectivo");

    private String texto;

    MetodoDePago(String texto) {
        this.texto = texto;
    }

    public String toString() {
        return texto;
    }

}
