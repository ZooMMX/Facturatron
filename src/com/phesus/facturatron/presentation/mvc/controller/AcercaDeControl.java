package com.phesus.facturatron.presentation.mvc.controller;

import facturatron.mvc.Controller;
import com.phesus.facturatron.presentation.mvc.view.ListadoForm;
import com.phesus.facturatron.presentation.mvc.model.AcercaDeModel;
import com.phesus.facturatron.presentation.mvc.view.AcercaDeForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que se encarga de ejercer como controlador de la sección AcercaDe.
 *
 * @author Ulises Estecche
 */
public class AcercaDeControl extends Controller<AcercaDeModel, AcercaDeForm> {

    public AcercaDeControl() {
        setView(new AcercaDeForm());
        setModel(new AcercaDeModel());
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
