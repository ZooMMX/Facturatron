/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron;

import com.phesus.facturatron.presentation.mvc.view.ExceptionWindow;

/**
 *
 * @author mora
 */
public class UEHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread th, Throwable ex) {
        ExceptionWindow.show(ex);
        ex.printStackTrace();
    }
}
