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

    public EmailFacturaCliente(String emailAddressTo) {
        super(emailAddressTo);
        setEmailAddressFrom("facturatron@facturatron.com");
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
