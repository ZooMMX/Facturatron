/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.facturacion.InformeMensual;

import facturatron.MVC.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Octavio
 */
public class InformeControl extends Controller<InformeDao, InformeForm> {

    public InformeControl() {
        setView(new InformeForm());
        setModel(new InformeDao());
        init();
    }

    @Override
    public void asignarEventos() {
        getView().getBtnGenInforme().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    getModel().setAnio(getView().getTxtAnio().getValue());
                    getModel().setMes(getView().getTxtMes().getMonth());
                    getModel().generar();
                    JOptionPane.showMessageDialog(getView(), "Informe generado y guardado");
                } catch (Exception ex) {
                    Logger.getLogger(InformeControl.class.getName()).log(Level.SEVERE, "Error durante la generación del informe", ex);
                }
            }
        });
    }

    @Override
    public void enlazarModeloVista() {

    }


}
