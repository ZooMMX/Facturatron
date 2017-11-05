/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phesus.facturatron.presentation.mvc.controller;

import com.phesus.facturatron.presentation.mvc.view.ConfiguracionForm;
import com.phesus.facturatron.persistence.dao.ConfiguracionDao;
import facturatron.mvc.Controller;
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
public class ConfiguracionControl extends Controller<ConfiguracionDao, ConfiguracionForm> {

    public ConfiguracionControl() {
        setModel(new ConfiguracionDao());
        setView(new ConfiguracionForm());
        init();
        getModel().load();
    }

    public void btnBuscarCertificado() {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new FileFilter() {

            @Override
            public boolean accept(File f) {
                return (f.getName().matches(".*\\.cer") || f.isDirectory());
            }

            @Override
            public String getDescription() {
                return "Certificado Digital";
            }
        });
        if (jfc.showOpenDialog(getView()) == JFileChooser.APPROVE_OPTION) {
            getView().getTxtPathCer().setText(jfc.getSelectedFile().getPath());
        }
    }

    public void btnBuscarLlave() {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new FileFilter() {

            @Override
            public boolean accept(File f) {
                return (f.getName().matches(".*\\.key") || f.isDirectory());
            }

            @Override
            public String getDescription() {
                return "Certificado Digital";
            }
        });
        if (jfc.showOpenDialog(getView()) == JFileChooser.APPROVE_OPTION) {
            getView().getTxtPathKey().setText(jfc.getSelectedFile().getPath());
        }
    }

    public void btnBuscarPdf() {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(jfc.DIRECTORIES_ONLY);

        jfc.setFileFilter(new FileFilter() {

            @Override
            public boolean accept(File f) {
                return (f.isDirectory());
            }

            @Override
            public String getDescription() {
                return "Carpeta de Almacenamiento";
            }
        });
        if (jfc.showOpenDialog(getView()) == JFileChooser.APPROVE_OPTION) {
            getView().getTxtPathPdf().setText(jfc.getSelectedFile().getPath());
        }
    }

    public void btnBuscarPlantilla() {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new FileFilter() {

            @Override
            public boolean accept(File f) {
                return (f.getName().matches(".*\\.jasper") || f.isDirectory());
            }

            @Override
            public String getDescription() {
                return "Plantilla de Jasper";
            }
        });
        if (jfc.showOpenDialog(getView()) == JFileChooser.APPROVE_OPTION) {
            getView().getTxtPathPlantilla().setText(jfc.getSelectedFile().getPath());
        }
    }
    
    public void btnBuscarLogotipo() {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new FileFilter() {

            @Override
            public boolean accept(File f) {
                return (f.getName().matches(".*\\.png") || f.isDirectory());
            }

            @Override
            public String getDescription() {
                return "Imagen PNG";
            }
        });
        if (jfc.showOpenDialog(getView()) == JFileChooser.APPROVE_OPTION) {
            getView().getTxtPathLogo().setText(jfc.getSelectedFile().getPath());
        }
    }

    public void btnBuscarXml() {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(jfc.DIRECTORIES_ONLY);
        jfc.setFileFilter(new FileFilter() {

            @Override
            public boolean accept(File f) {
                return (f.isDirectory());
            }

            @Override
            public String getDescription() {
                return "Carpeta de Almacenamiento";
            }
        });
        if (jfc.showOpenDialog(getView()) == JFileChooser.APPROVE_OPTION) {
            getView().getTxtPathXml().setText(jfc.getSelectedFile().getPath());
        }
    }

    public void btnGuardar() {
        getModel().setpathCer(getView().getTxtPathCer().getText());
        getModel().setpathKey(getView().getTxtPathKey().getText());
        getModel().setpassCer(String.valueOf(getView().getTxtPassCer().getPassword()));
        getModel().setUrlBd(getView().getTxtUrlBd().getText());
        getModel().setUserBd(getView().getTxtUserBd().getText());
        getModel().setPassBd(String.valueOf(getView().getTxtPassBd().getPassword()));
        getModel().setPathPdf(getView().getTxtPathPdf().getText());
        getModel().setPathPlantilla(getView().getTxtPathPlantilla().getText());
        getModel().setPathLogo(getView().getTxtPathLogo().getText());
        getModel().setPathXml(getView().getTxtPathXml().getText());
        getModel().setSmtpHost(getView().getTxtSmtpHost().getText());
        getModel().setUsuarioSmtp(getView().getTxtUsuarioSmtp().getText());
        getModel().setClaveSmtp(String.valueOf(getView().getTxtClaveSmtp().getPassword()));
        getModel().setPuertoSmtp(getView().getTxtPuertoSmtp().getText());
        if (getView().getRbnSinSeguridad().isSelected()) {
            getModel().setSeguridad(ConfiguracionForm.SIN_SEGURIDAD);
        } else if (getView().getRbnUsaSSL().isSelected()) {
            getModel().setSeguridad(ConfiguracionForm.USA_SSL);
        } else if (getView().getRbnUsaTLS().isSelected()) {
            getModel().setSeguridad(ConfiguracionForm.USA_TLS);
        }
        getModel().setVisorPDF(getView().getRbnVisorPDFJava().isSelected()
                ? ConfiguracionForm.VISOR_PDF_JAVA : ConfiguracionForm.VISOR_PDF_NATIVO);
        getModel().setTituloCorreo(getView().getTxtTituloCorreo().getText());
        getModel().setMensajeCorreo(getView().getTxtMensajeCorreo().getText());
        getModel().setCorreoRemitente(getView().getTxtCorreoRemitente().getText());
        getModel().setModuloProductosActivo(getView().getBoxProductos().isSelected());
        getModel().setModuloUnidadesActivo(getView().getBoxUnidades().isSelected());
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
        getView().getBtnBuscarCer().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnBuscarCertificado();
            }
        });
        getView().getBtnBuscarKey().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnBuscarLlave();
            }
        });
        getView().getBtnBuscarPdf().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                btnBuscarPdf();
            }
        });
        getView().getBtnBuscarPlantilla().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                btnBuscarPlantilla();
            }
        });
        getView().getBtnBuscarLogo().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                btnBuscarLogotipo();
            }
        });
        getView().getBtnBuscarXml().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                btnBuscarXml();
            }
        });
        getView().getBtnProbarConfiguracion().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnProbarConfiguracionClicked();
            }
        });
    }

    private void btnProbarConfiguracionClicked() {
        EmailPruebaConf emailPruebaConf = new EmailPruebaConf(getView().getTxtCorreoRemitente().getText());
        boolean res = emailPruebaConf.enviarCorreoPrueba();
        if (res) {
            JOptionPane.showMessageDialog(getView(), "Correo enviado satisfactoriamente!", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(getView(), "Ocurrió un Error enviando el Correo!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void enlazarModeloVista() {
        getView().setModel(getModel());
        getModel().addObserver(getView());
    }
}
