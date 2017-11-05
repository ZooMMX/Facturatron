/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.mvc;


/**
 *
 * @author Octavio
 */
public interface ViewInterface<M extends Model> {
    public M getModel();
    public void setModel(M model);
}
