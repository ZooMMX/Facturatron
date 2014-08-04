/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.config;

import facturatron.MVC.Controller;
import facturatron.cliente.ClienteDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Octavio
 */
public class ConfigFiscalControl extends Controller<ConfigFiscalDao, ConfigFiscalForm> {

    public ConfigFiscalControl() {
        try {
            setModel(new ConfigFiscalDao());
            setView(new ConfigFiscalForm());
            init();
            getModel().load();
        } catch (SQLException ex) {
            Logger.getLogger(ConfigFiscalControl.class.getName()).log(Level.SEVERE, "Problema con la base de datos", ex);
        }
    }

    public void btnGuardar() {
        if(JOptionPane.showConfirmDialog(getView(), "¿Está seguro de guardar los cambios?")==JOptionPane.YES_OPTION) {
            try {
                getModel().setSerie(getView().getTxtSerie().getText());

                getModelContrib().setNombre(getView().getTxtNombre().getText());
                getModelContrib().setRfc(getView().getTxtRfc().getText());
                getModelContrib().setTelefono(getView().getTxtTelefono().getText());
                getModelContrib().setCalle(getView().getTxtCalle().getText());
                getModelContrib().setCodigoPostal(getView().getTxtCodigoPostal().getText());
                getModelContrib().setColonia(getView().getTxtColonia().getText());
                getModelContrib().setMunicipio(getView().getTxtMunicipio().getText());
                getModelContrib().setEstado(getView().getTxtEstado().getText());
                getModelContrib().setNoExterior(getView().getTxtNoExterior().getText());
                getModelContrib().setNoInterior(getView().getTxtNoInterior().getText());
                getModelContrib().setPais(getView().getTxtPais().getText());
                getModelContrib().setRegimen(getView().getTxtRegimen().getText());

                getModelContribSuc().setNombre("NA");
                getModelContribSuc().setRfc("NA");
                getModelContribSuc().setTelefono(getView().getTxtTelefonoSuc().getText());
                getModelContribSuc().setCalle(getView().getTxtCalleSuc().getText());
                getModelContribSuc().setCodigoPostal(getView().getTxtCodigoPostalSuc().getText());
                getModelContribSuc().setColonia(getView().getTxtColoniaSuc().getText());
                getModelContribSuc().setMunicipio(getView().getTxtMunicipioSuc().getText());
                getModelContribSuc().setEstado(getView().getTxtEstadoSuc().getText());
                getModelContribSuc().setNoExterior(getView().getTxtNoExteriorSuc().getText());
                getModelContribSuc().setNoInterior(getView().getTxtNoInteriorSuc().getText());
                getModelContribSuc().setPais(getView().getTxtPaisSuc().getText());

                getModel().persist();
                JOptionPane.showMessageDialog(getView(), "Cambios hechos");
            } catch (SQLException ex) {
                Logger.getLogger(ConfigFiscalControl.class.getName()).log(Level.SEVERE, "Error al guardar cambios en configuración fiscal dentro de la BD", ex);
            }
        }
    }

    public ClienteDao getModelContrib() {
        return getModel().getContribuyente();
    }

    public ClienteDao getModelContribSuc() {
        return getModel().getContribuyenteSuc();
    }

    @Override
    public void asignarEventos() {
        getView().getBtnGuardar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnGuardar();
            }
        });
    }

    @Override
    public void enlazarModeloVista() {
        getView().setModel(getModel());
        getModel().addObserver(getView());
    }

}
