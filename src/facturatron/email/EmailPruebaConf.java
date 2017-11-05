package facturatron.email;

import facturatron.Dominio.Configuracion;
import com.phesus.facturatron.persistence.dao.ConfiguracionDao;

/**
 * Clase que se encarga de enviar un correo para probar la configuración del
 * Sistema.
 *
 * @author Ulises Estecche
 */
public class EmailPruebaConf extends EmailMgmt {

    /**
     * Constructor de la Clase.
     *
     * @param emailAddressTo
     */
    public EmailPruebaConf(String emailAddressTo) {
        super(emailAddressTo);
        Configuracion configuracion = new ConfiguracionDao().load();
        setMsgSubject(configuracion.getTituloCorreo());
        setMsgContent(configuracion.getMensajeCorreo());
    }

    /**
     * Método que envia el correo de prueba.
     *
     * @return Si el correo se envió de manera satisfactoria.
     */
    public boolean enviarCorreoPrueba() {
        return super.sendEmail();
    }
}
