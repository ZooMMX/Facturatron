/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.mvc;

import facturatron.Dominio.Configuracion;
import facturatron.config.ConfiguracionDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Octavio
 */
public class JDBCDAOSupport {
    private Statement stmt;
    private Connection con;
    private Configuracion cacheConfig;
    private static ArrayList<Connection> pool = new ArrayList<Connection>();
    private int poolSize = 3;

     public void conectar() throws SQLException {
         conectar(false);
     }
     public void conectar(boolean newConnRequired) throws SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            assignConnection(newConnRequired);

            setStmt(getCon().createStatement());
        } catch (SQLException ex) {
            throw ex;
        } catch (ClassNotFoundException ex) {
            throw new SQLException("Driver mysql no encontrado en el classpath", ex);
        }

     }

    /**
     * @return the stmt
     */
    public Statement getStmt() {
        return stmt;
    }

    /**
     * @param stmt the stmt to set
     */
    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    /**
     * @return the con
     */
    public Connection getCon() {
        return con;
    }

    /**
     * @param con the con to set
     */
    public void setCon(Connection con) {
        this.con = con;
    }

    private Configuracion getConfig() {
        if(cacheConfig == null) {
            cacheConfig = new ConfiguracionDao().load();
        }
        return cacheConfig;
    }

    private void assignConnection(boolean newRequired) throws SQLException {
        Configuracion config = getConfig();
        String url = config.getUrlBd();
        String user= config.getUserBd();
        String pass= config.getPassBd();
        if(!newRequired && JDBCDAOSupport.pool.size() > 0 && pool.get(0).isValid(50)) {
            setCon(JDBCDAOSupport.pool.remove(0));
        } else {
            setCon(DriverManager.getConnection(url, user, pass));
        }
    }

    public void desconectar() {
        try {
            if(getStmt() != null)
                getStmt().close();
            if(JDBCDAOSupport.pool.size() < poolSize) {
                JDBCDAOSupport.pool.add(con);
            } else {
                getCon().close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDAOSupport.class.getName()).log(Level.SEVERE, "Error al desconectar a la base de datos", ex);
        }

    }
}

