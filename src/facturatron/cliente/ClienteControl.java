/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.cliente;
import facturatron.MVC.Controller;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author saul
 */
public class ClienteControl extends Controller<ClienteDao, ClienteForm> {

    public ClienteControl(){
        setModel(new ClienteDao());
        setView(new ClienteForm());
        init();
    }

    public void updateListado() {
        ((ClienteTableModel)getView().getTablaCliente().getModel()).fill();
        getView().getTablaCliente().updateUI();
    }
    public void btnBuscar() {
        String searchString = getView().getTxtBusqueda().getText();
        ((ClienteTableModel)getView().getTablaCliente().getModel()).search(searchString);
        getView().getTablaCliente().updateUI();
    }
    public void btnEliminar() {
        if(JOptionPane.showConfirmDialog(getView(), "¿Está seguro de eliminar al cliente?")==JOptionPane.YES_OPTION) {
            getModel().remove();
            updateListado();
            getView().getTablaCliente().clearSelection();
        }
    }
    public void clienteSeleccionado(int id) {
        try {
            getModel().findBy(id);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteControl.class.getName()).log(Level.SEVERE, "Error de BD obteniendo info de cliente", ex);
        }
    }
    public void btnNuevo() {
        getModel().clearDataModel();
    }
    public void btnGuardar() {
        getModel().setNombre(getView().getTxtNombre().getText());
        getModel().setRfc(getView().getTxtRfc().getText());
        getModel().setTelefono(getView().getTxtTelefono().getText());
        getModel().setCalle(getView().getTxtCalle().getText());
        getModel().setCodigoPostal(getView().getTxtCodigoPostal().getText());
        getModel().setColonia(getView().getTxtColonia().getText());
        getModel().setMunicipio(getView().getTxtMunicipio().getText());
        getModel().setEstado(getView().getTxtEstado().getText());
        getModel().setNoExterior(getView().getTxtNoExterior().getText());
        getModel().setNoInterior(getView().getTxtNoInterior().getText());
        getModel().setPais(getView().getTxtPais().getText());
        getModel().setCorreoElectronico(getView().getTxtCorreoElectronico().getText());

        try {
            getModel().persist();
            updateListado();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteControl.class.getName()).log(Level.SEVERE, "Error al guardar cliente", ex);
        }
        
    }


    @Override
    public void asignarEventos() {
        getView().getBtnGuardar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               btnGuardar();
            }
        });
        getView().getTablaCliente().getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()) {
                    JTable m = getView().getTablaCliente();
                    //String txtIdCliente = (String) m.getValueAt(e.getFirstIndex(), 0);
                    int idCliente =  (Integer) m.getValueAt(m.getSelectedRow(), 0);
                    clienteSeleccionado(idCliente);
                }
            }
        });
        getView().getBtnNuevo().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnNuevo();
            }
        });
        getView().getBtnEliminar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnEliminar();
            }
        });
        getView().getBtnBuscar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnBuscar();
            }
            
        });
        getView().getTxtBusqueda().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) btnBuscar();
            }
        });
        
    }

    @Override
    public void enlazarModeloVista() {
        new Thread() {
            public void run() {
                notifyBusy();
                ClienteTableModel tabModel = new ClienteTableModel();
                getView().getTablaCliente().setModel(tabModel);
                getModel().addObserver(getView());
                getView().setModelo(getModel());
                notifyIdle();
            }
        }.start();
    }

}
