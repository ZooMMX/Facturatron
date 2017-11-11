/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phesus.facturatron.presentation.mvc.controller;

import facturatron.config.ConfigDatasourceAndPACForm;
import facturatron.config.ConfiguracionDao;
import facturatron.mvc.Controller;
import facturatron.datasource.DatasourceContext.DATASOURCE;
import facturatron.facturacion.PAC.PACContext.PACS;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Octavio
 */
public class ConfigDatasourceAndPACControl extends Controller<ConfiguracionDao, ConfigDatasourceAndPACForm> {
    public ConfigDatasourceAndPACControl() {
        setModel(new ConfiguracionDao());
        setView(new ConfigDatasourceAndPACForm());
        
        //Establecer los conectores PAC y Datasource disponibles en los comboBox
        getView().getPacComboBox().setModel( new DefaultComboBoxModel<PACS>( PACS.values() ) );
        getView().getOrigenComboBox().setModel( new DefaultComboBoxModel<DATASOURCE>( DATASOURCE.values() ) );
        
        //Iniciar GUI
        init();
        
        //Cargar configuración en la GUI
        getModel().load();
        
    }

    public void btnGuardar() {
        getModel().setUsuarioPAC(getView().getTxtUsuario().getText());
        getModel().setPasswordPAC(String.valueOf( getView().getTxtPassword().getPassword() ));
        getModel().setConectorPAC((PACS) getView().getPacComboBox().getSelectedItem());
        
        getModel().setUsuarioDatasource(getView().getTxtUsuarioOrigen().getText());
        getModel().setPasswordDatasource(String.valueOf( getView().getTxtPasswordOrigen().getPassword() ));
        getModel().setConectorDatasource((DATASOURCE) getView().getOrigenComboBox().getSelectedItem() );
        getModel().setUrlDatasource( getView().getTxtUrlOrigen().getText() );
        
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
