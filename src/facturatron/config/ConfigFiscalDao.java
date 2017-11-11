/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.config;

import com.phesus.facturatron.persistence.dao.ClienteDao;
import facturatron.Dominio.Serie;
import facturatron.mvc.DAO;
import facturatron.mvc.JDBCDAOSupport;
import com.phesus.facturatron.persistence.dao.SerieDao;
import com.phesus.facturatron.persistence.dao.SerieDao;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Octavio
 */
public class ConfigFiscalDao extends SerieDao implements DAO<Integer,Serie>, Observer {

    private ClienteDao contribuyenteModel;
    private ClienteDao contribuyenteSucModel;

    public ConfigFiscalDao() {
        
    }

    /**
     * Carga a la primera persona, que representa siempre al contribuyente (matriz),
     * tambien carga los datos de la sucursal que son representados por la persona ID 2
     * Nota: Las personas del ID 3 en adelante son clientes
     * @return this
     * @throws java.sql.SQLException
     */
    @Override
    public Serie load() throws SQLException {
        try {
            loadEmisor();
            loadSerie();

            return this;
        } catch(SQLException se) {
            if(se.getMessage().contains("driver"))
                throw new SQLException("No se ha configurado adecuadamente la base de datos", se);
            else
                throw new SQLException("Error de base de datos", se);
        }
    }
    private void loadEmisor() throws SQLException {
        ClienteDao dao = new ClienteDao();
        dao.addObserver(this);
        setContribuyente(dao);
        dao.findBy(1);

        ClienteDao daoSuc = new ClienteDao();
        daoSuc.addObserver(this);
        setContribuyenteSuc(daoSuc);
        daoSuc.findBy(2);
    }

    @Override
    public void persist() throws SQLException {
        this.getContribuyente().persist();
        this.getContribuyenteSuc().persist();
        super.persist();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("La configuración no puede ser eliminada.");
    }

    @Override
    public Serie findBy(Integer id) throws SQLException {
        return load();
    }

    @Override
    public List<Serie> findAll() {
        throw new UnsupportedOperationException("La configuración sólo contiene una fila, usar método find en su lugar.");
    }

    @Override
    public JDBCDAOSupport getBD() {
        return new JDBCDAOSupport();
    }

    /**
     * @return the contribuyente
     */
    public ClienteDao getContribuyente() {
        return contribuyenteModel;
    }

    /**
     * @param contribuyente the contribuyente to set
     */
    public void setContribuyente(ClienteDao contribuyente) {
        this.contribuyenteModel = contribuyente;
    }
    /** Devuelve los datos de sucursal del contribuyente
     * 
     * @return
     */
    public ClienteDao getContribuyenteSuc() {
       return contribuyenteSucModel;
    }

    /**
     *
     * @param contrib
     */
    public void setContribuyenteSuc(ClienteDao contrib) {
       this.contribuyenteSucModel = contrib;
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }

    public void loadSerie() {
        super.loadSerie();
        
    }


}
