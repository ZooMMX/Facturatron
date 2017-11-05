/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facturatron.email;

import facturatron.Dominio.Configuracion;
import com.phesus.facturatron.persistence.dao.ConfiguracionDao;
import javax.swing.JFrame;
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
    public EmailFacturaCliente(String emailAddressTo) {
        super(emailAddressTo);
        Configuracion configuracion = new ConfiguracionDao().load();
        setMsgSubject(configuracion.getTituloCorreo());
        setMsgContent(configuracion.getMensajeCorreo());
    }

    @Override
    public void run() {
        boolean res = sendEmail();
        if (!res) {
            JFrame mainJFrame = (JFrame) JFrame.getFrames()[0];
            if (getErrorStatus() == WRONG_SMTP) {
                JOptionPane.showMessageDialog(mainJFrame, "No ha configurado el Servidor SMTP para el envío de Correos!");
            } else if (getErrorStatus() == WRONG_TO_ADDRESS) {
                JOptionPane.showMessageDialog(mainJFrame, "La Dirección de Correo del Cliente no es válida!");
            }
        }
    }
}
