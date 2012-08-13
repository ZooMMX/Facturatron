/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron;

import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import javax.swing.JOptionPane;

/**
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
        ExceptionWindow ew = new ExceptionWindow();
        ew.getLblTituloError().setText(record.getMessage());
        ew.getTxtExcepcion().setText(Misc.getStackTraceString(record.getThrown()));
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
