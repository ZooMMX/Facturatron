/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.datasource.unicenta;
import facturatron.datasource.Ticket;

/**
 *
 * @author np370
 */
public class TicketUnicenta extends Ticket{
    private String id;
    private String idFinal;

    public String getIdFinal() {
        return idFinal;
    }

    public void setIdFinal(String idFinal) {
        this.idFinal = idFinal;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
}
