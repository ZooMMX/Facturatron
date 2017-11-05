/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.Principal;

import facturatron.mvc.BusyHandler;
import javax.swing.JFrame;
import net.java.swingfx.waitwithstyle.InfiniteProgressPanel;

/**
 *
 * @author Octavio
 */
public class ProgressPanelBusyHandler extends BusyHandler {

    private JFrame frame;
    private InfiniteProgressPanel glassPane;
    
    ProgressPanelBusyHandler(JFrame frame) {
        setFrame(frame);
        setGlassPane(new InfiniteProgressPanel());
        getFrame().setGlassPane(getGlassPane());
    }

    @Override
    public void onBusy() {
        getGlassPane().start();
    }

    @Override
    public void onIdle() {
        getGlassPane().stop();
    }

    /**
     * @return the frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * @param frame the frame to set
     */
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    /**
     * @return the glasspane
     */
    public InfiniteProgressPanel getGlassPane() {
        return glassPane;
    }

    /**
     * @param glasspane the glasspane to set
     */
    private void setGlassPane(InfiniteProgressPanel glassPane) {
        this.glassPane = glassPane;
    }


}
