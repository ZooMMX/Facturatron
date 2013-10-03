package facturatron.acercade;

import facturatron.MVC.Controller;
import facturatron.facturacion.ListadoForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que se encarga de ejercer como controlador de la sección AcercaDe.
 *
 * @author Ulises Estecche
 */
public class AcercaDeControl extends Controller<AcercaDeDao, AcercaDeForm> {

    public AcercaDeControl() {
        setView(new AcercaDeForm());
        setModel(new AcercaDeDao());
    }

    @Override
    public void init() {
        try {
            notifyBusy();
            super.init();
        } finally {
            notifyIdle();
        }
    }

    /**
     * @return the form
     */
    @Override
    public AcercaDeForm getView() {
        return super.getView();
    }

    @Override
    public void asignarEventos() {
    }

    @Override
    public void enlazarModeloVista() {
        getModel().addObserver(getView());
    }
}
