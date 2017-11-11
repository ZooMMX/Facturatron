/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.config;

import facturatron.Dominio.Configuracion;
import facturatron.mvc.DAO;
import facturatron.mvc.JDBCDAOSupport;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Octavio
 */
public class ConfiguracionDao extends Configuracion implements DAO<Integer, Configuracion> {

    String configFile = "config.dat";
    @Override
    public void persist() {
        ConfiguracionDao c = this;
        FileOutputStream fos = null;
        ObjectOutputStream outStream = null;
        try {
            fos = new FileOutputStream(configFile);
            outStream = new ObjectOutputStream(fos);
            outStream.writeObject(c);
        } catch (IOException ex) {
            Logger.getLogger(ConfiguracionDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(ConfiguracionDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                outStream.close();
            } catch (IOException ex) {
                Logger.getLogger(ConfiguracionDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        reloadConfig();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Configuracion findBy(Integer id) {
        return load();
    }

    @Override
    public List<Configuracion> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JDBCDAOSupport getBD() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Carga el archivo de configuración en un objeto facturatron.dominio.Configuracion, 
     * si el archivo no exíste, se crea uno con los valores default de la
     * clase facturatron.dominio.Configuracion
     * @return 
     */
    public Configuracion load() {
        FileInputStream fis = null;
        Configuracion serialObject;
        ObjectInputStream inStream = null;
        try {
            //En caso de que el archivo de configuración no exista, crearlo
            try {
                fis = new FileInputStream(configFile);
            } catch(FileNotFoundException fntf) {
                FileOutputStream fos = new FileOutputStream(configFile);
                fos.close();
                persist();
            }
            
            if(fis==null) fis = new FileInputStream(configFile);
            inStream = new ObjectInputStream(fis);
            serialObject = (ConfiguracionDao) inStream.readObject();
            setPassBd(serialObject.getPassBd());
            setUrlBd(serialObject.getUrlBd());
            setUserBd(serialObject.getUserBd());
            setpassCer(serialObject.getpassCer());
            setpathCer(serialObject.getpathCer());
            setpathKey(serialObject.getpathKey());
            setPathPdf(serialObject.getPathPdf());
            setPathPlantilla(serialObject.getPathPlantilla());
            setPathLogo(serialObject.getPathLogo());
            setPathXml(serialObject.getPathXml());
            setSmtpHost(serialObject.getSmtpHost());
            setUsuarioSmtp(serialObject.getUsuarioSmtp());
            setClaveSmtp(serialObject.getClaveSmtp());
            setSeguridad(serialObject.getSeguridad());
            setPuertoSmtp(serialObject.getPuertoSmtp());
            setVisorPDF(serialObject.getVisorPDF());
            setTituloCorreo(serialObject.getTituloCorreo());
            setMensajeCorreo(serialObject.getMensajeCorreo());
            setCorreoRemitente(serialObject.getCorreoRemitente());
            
            setUsuarioPAC(serialObject.getUsuarioPAC());
            setPasswordPAC(serialObject.getPasswordPAC());
            setConectorPAC(serialObject.getConectorPAC());
            
            setUsuarioDatasource(serialObject.getUsuarioDatasource());
            setPasswordDatasource(serialObject.getPasswordDatasource());
            setConectorDatasource(serialObject.getConectorDatasource());
            setUrlDatasource(serialObject.getUrlDatasource());
            
            if(serialObject.getModuloProductosActivo() != null)
                setModuloProductosActivo(serialObject.getModuloProductosActivo());
            
            if(serialObject.getModuloUnidadesActivo() != null)
                setModuloUnidadesActivo(serialObject.getModuloUnidadesActivo());
            
            setChanged();
            notifyObservers();
            return serialObject;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConfiguracionDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConfiguracionDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(ConfiguracionDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                inStream.close();
            } catch (IOException ex) {
                Logger.getLogger(ConfiguracionDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;

    }

}
