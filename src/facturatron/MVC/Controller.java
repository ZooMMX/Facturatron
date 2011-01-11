/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.MVC;

import facturatron.MVC.BusyHandler.BusyHandler;
import facturatron.MVC.BusyHandler.InterfaceListener;
import facturatron.MVC.BusyHandler.InterfaceNotifier;
import java.awt.Container;

/**
 *
 * @author Octavio
 */
public abstract class Controller <M extends Model,V extends Container> implements InterfaceNotifier {
    private V view;
    private M model;
    private BusyHandler busyHandler;

    public abstract void asignarEventos();

    public abstract void enlazarModeloVista();

    public void init() {
        enlazarModeloVista();
        asignarEventos();
    }

    /**
     * @return the view
     */
    public V getView() {
        return view;
    }

    /**
     * @param view the view to set
     */
    public void setView(V view) {
        this.view = view;
    }

    /**
     * @return the modelo
     */
    public M getModel() {
        return model;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModel(M model) {
        this.model = model;
    }

    public void setBusyHandler(BusyHandler bh) {
        this.busyHandler = bh;
    }
    public BusyHandler getBusyHandler() {
        return busyHandler;
    }

    @Override
    public void notifyBusy() {
        if(getBusyHandler() != null) {
            getBusyHandler().notifyBusy();
        }
    }

    @Override
    public void notifyIdle() {
        if(getBusyHandler() != null) {
            getBusyHandler().notifyIdle();
        }
    }

}

