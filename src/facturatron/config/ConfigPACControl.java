/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facturatron.config;

import facturatron.MVC.Controller;
import facturatron.email.EmailPruebaConf;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Octavio
 */
public class ConfigPACControl extends Controller<ConfiguracionDao, ConfigPACForm> {

    public ConfigPACControl() {
        setModel(new ConfiguracionDao());
        setView(new ConfigPACForm());
        init();
        getModel().load();
    }

    public void btnGuardar() {
        getModel().setUsuarioPAC(getView().getTxtUsuario().getText());
        getModel().setPasswordPAC(String.valueOf( getView().getTxtPassword().getPassword() ));
        
        getModel().persist();
        JOptionPane.showMessageDialog(getView(), "Configuración actualizada");
    }

    @Override
    public void asignarEventos() {
        getView().getBtnGuardar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnGuardar();
            }
        });
    }

    @Override
    public void enlazarModeloVista() {
        getView().setModel(getModel());
        getModel().addObserver(getView());
    }
}
