/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron;

import com.phesus.facturatron.presentation.mvc.view.ExceptionWindow;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/** Manejador de excepciones controladas (Controlled Exception Handler)
 * 
 * @author mora
 */
public class CEHandler extends Handler {

    private static class CEFilter implements Filter {
        @Override
        public boolean isLoggable(LogRecord record) {
            return record.getLevel().intValue() >= Level.INFO.intValue();
        }
    }

    public CEHandler() {
        setFilter(new CEFilter());
    }

    @Override
    public void publish(LogRecord record) {
        if(record.getLevel() == Level.INFO)
            aviso(record);
        else
            excepcion(record);

    }

    private void excepcion(LogRecord record) {
        if(record.getThrown() == null) record.setThrown(new Exception("Sin stacktrace"));
        JFrame mainJFrame = (JFrame) JFrame.getFrames()[0];
        ExceptionWindow ew = new ExceptionWindow(mainJFrame);
        ew.getLblTituloError().setText(record.getMessage());
        ew.getTxtExcepcion().setText(Misc.getStackTraceString(record.getThrown()));
        ew.setLblDescripcion(record.getThrown().getMessage());
        ew.setModal(true);
        ew.setLocationByPlatform(true);
        ew.setLocationRelativeTo(mainJFrame);
        ew.setVisible(true);
        record.getThrown().printStackTrace();
    }

    private void aviso(LogRecord record) {
        JOptionPane.showMessageDialog(null, record.getMessage());
    }

    @Override
    public void flush() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void close() throws SecurityException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
