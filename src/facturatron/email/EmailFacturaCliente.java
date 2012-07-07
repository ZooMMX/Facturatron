/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facturatron.email;

import javax.swing.JOptionPane;

/**
 * Clase que se encarga de configurar y enviar la Factura al Cliente.
 *
 * @author Ulises Estecche
 */
public class EmailFacturaCliente extends EmailMgmt implements Runnable {

    /**
     * Constructor de la Clase.
     *
     * @param emailAddressTo La dirección del receptor del correo electrónico.
     * @param subject Parte del subject del Correo.
     */
    public EmailFacturaCliente(String emailAddressTo, String subject) {
        super(emailAddressTo);
        setEmailAddressFrom("facturatron@facturatron.com");
        setMsgSubject("Facturas PDF y XML " + subject);
        setMsgContent("Anexadas Factura en PDF y XML");
    }

    @Override
    public void run() {
        boolean res = sendEmail();
        if (!res) {
            if (getErrorStatus() == WRONG_SMTP) {
                JOptionPane.showMessageDialog(null, "No ha configurado el Servidor SMTP para el envío de Correos!");
            } else if (getErrorStatus() == WRONG_TO_ADDRESS) {
                JOptionPane.showMessageDialog(null, "La Dirección de Correo del Cliente no es válida!");
            }
        }
    }
}
