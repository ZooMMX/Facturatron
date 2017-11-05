/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron;

import com.phesus.facturatron.presentation.mvc.view.ExceptionWindow;
import org.apache.log4j.Appender;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Priority;
import org.apache.log4j.spi.LoggingEvent;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 *
 * @author mora
 */
public class CEAppender extends AppenderSkeleton {

    @Override
    public void close() {
        //Nothing
    }

    @Override
    public boolean requiresLayout() {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void append(final LoggingEvent event) {
        if ( event.getLevel().isGreaterOrEqual(Priority.WARN) ) {
            excepcion(event);
        } else if(event.getLevel().isGreaterOrEqual(Priority.INFO)) {
            aviso(event);
        }
    }


    private void excepcion(LoggingEvent record) {
        Throwable thrown;
        if(record.getThrowableInformation().getThrowable() == null) 
            thrown = new Exception("Sin stacktrace");
        else
            thrown = record.getThrowableInformation().getThrowable();
        
        JFrame mainJFrame = (JFrame) JFrame.getFrames()[0];
        ExceptionWindow ew = new ExceptionWindow(mainJFrame);
        ew.getLblTituloError().setText((String) thrown.getMessage());
        ew.getTxtExcepcion().setText(Misc.getStackTraceString(thrown));
        ew.setLblDescripcion(thrown.getMessage());
        ew.setModal(true);
        ew.setLocationByPlatform(true);
        ew.setLocationRelativeTo(mainJFrame);
        ew.setVisible(true);
    }

    private void aviso(LoggingEvent record) {
        JOptionPane.showMessageDialog(null, record.getMessage());
    }

}
