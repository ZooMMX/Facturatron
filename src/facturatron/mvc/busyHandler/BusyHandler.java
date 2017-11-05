/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.mvc.busyHandler;

/**
 *
 * @author Octavio
 */
public abstract class BusyHandler implements InterfaceListener, InterfaceNotifier {

    public enum Monitor {BUSY,IDLE};
    private Monitor monitor = Monitor.IDLE;

    @Override
    synchronized public void notifyBusy() {
        if(getMonitor()==Monitor.IDLE) {
            setMonitor(Monitor.BUSY);
            onBusy();
        }
    }

    @Override
    synchronized public void notifyIdle() {
        if(getMonitor()==Monitor.BUSY) {
            setMonitor(Monitor.IDLE);
            onIdle();
        }
    }

    /**
     * @return the monitor
     */
    public Monitor getMonitor() {
        return monitor;
    }

    /**
     * @param monitor the monitor to set
     */
    synchronized private void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }
}
