/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.MVC;

import java.awt.Container;

/**
 *
 * @author Octavio
 */
public abstract class Controller <M extends Model,V extends Container>{
    private V view;
    private M model;

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
}

