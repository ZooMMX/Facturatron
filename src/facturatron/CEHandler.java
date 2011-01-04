/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 *
 * @author mora
 */
public class CEHandler extends Handler {

    @Override
    public void publish(LogRecord record) {
        ExceptionWindow.show(record.getThrown());
        record.getThrown().printStackTrace();
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
