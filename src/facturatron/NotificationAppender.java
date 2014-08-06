/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron;

import com.alee.managers.notification.NotificationIcon;
import com.alee.managers.notification.NotificationManager;
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
public class NotificationAppender extends AppenderSkeleton {

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
        JFrame mainFrame = (JFrame) JFrame.getFrames()[0];
        if(mainFrame == null) return;
        
        if ( event.getLevel().isGreaterOrEqual(Priority.WARN) ) {
            excepcion(mainFrame, event);
        } else if(event.getLevel().isGreaterOrEqual(Priority.INFO)) {
            aviso(mainFrame, event);
        }
    }


    private void excepcion(JFrame mainFrame, LoggingEvent record) {
        NotificationManager.showNotification ( 
                mainFrame,
                (String) record.getMessage(), 
                NotificationIcon.error.getIcon () 
        );
    }

    private void aviso(JFrame mainFrame, LoggingEvent record) {
        NotificationManager.showNotification ( 
                mainFrame,
                (String) record.getMessage(), 
                NotificationIcon.tip.getIcon () 
        );
    }

}
