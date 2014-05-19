/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.unidad;
import facturatron.Dominio.Medida;
import facturatron.MVC.DAO;
import facturatron.MVC.JDBCDAOSupport;
import facturatron.cliente.ClienteDao;
import facturatron.facturacion.FacturaDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
/**
 *
 * @author gnujach
 */
public class UnidadDao extends Medida implements DAO<Integer, Medida>  {
    private JDBCDAOSupport bd;
    public UnidadDao () {
        setChanged();
    }
    @Override
    public ArrayList<Medida> findAll() {
        JDBCDAOSupport bd = getBD();
        try {
            bd.conectar();
            ResultSet rs = bd.getStmt().executeQuery("SELECT * FROM medida WHERE id > 0");
            ArrayList<Medida> ret = new ArrayList<Medida>();
            Medida bean;
            while (rs.next()) {
                bean = new Medida();
                bean.setId(rs.getInt("id"));
                bean.setNombre(rs.getString("nombre"));
                ret.add(bean);                
            }
            return ret;            
        } catch (Exception ex) {
            Logger.getLogger(FacturaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            bd.desconectar();
        }
        return null;
    }
    @Override
    public JDBCDAOSupport getBD() {

        return new JDBCDAOSupport();
    }

    @Override
    public void persist() throws SQLException {
        try {
            JDBCDAOSupport bd = getBD();
            bd.conectar();
            PreparedStatement ps;
            if (getId() != null) {
                ps = bd.getCon().prepareStatement("update medida set nombre=? WHERE id = ?");
                ps.setInt(2, getId());
            } else {
                ps = bd.getCon().prepareStatement("insert into medida (nombre) VALUES (?)");
            }
            ps.setString(1, getNombre());
            ps.execute();
            bd.desconectar();            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, "Error al guardar unidad", ex);
            throw ex;
        }
    }
    public ArrayList<Medida> find(String searchString) {
        JDBCDAOSupport bd = getBD();
        try {
            bd.conectar();
            PreparedStatement ps = bd.getCon().prepareStatement("SELECT * FROM medida WHERE id > 0 AND (nombre like ? ) LIMIT 25");
            searchString = "%"+searchString+"%";            
            ps.setString(1, searchString);
            ResultSet rs = ps.executeQuery();
            ArrayList<Medida> ret = new ArrayList<Medida>();
            Medida bean;
            while (rs.next()) {
                bean = new Medida();
                bean.setId(rs.getInt("id"));
                bean.setNombre(rs.getString("nombre"));
                ret.add(bean);
            }
            return ret;
        }catch (Exception ex) {
            Logger.getLogger(FacturaDao.class.getName()).log(Level.SEVERE, "Error al consultar cliente/persona", ex);
        } finally{ 
            bd.desconectar();
        }
        return null;
    }
    @Override
    public void remove() {
        try {
            JDBCDAOSupport bd = getBD();
            bd.conectar();
            PreparedStatement ps;
            if (getId() != null) {
                ps = bd.getCon().prepareStatement("delete from medida where id = ?");
                ps.setInt(1, getId());
                ps.execute();
            }
            bd.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, "Error al eliminar medida ", ex);
        }
        clearDataModel();
    }

    @Override
    public Medida findBy(Integer id) throws SQLException {
        JDBCDAOSupport bd = getBD();
        try {
            bd.conectar();
            PreparedStatement ps = bd.getCon().prepareStatement("SELECT * FROM medida WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(!rs.next()) {
                return null;
            }
            this.setId(rs.getInt("id"));
            this.setNombre(rs.getString("nombre"));
            setChanged();
            notifyObservers();
            return this;
        } catch (SQLException se) {
            throw se;
        } finally {
            if (bd != null)
                bd.desconectar();
        }
    }
    public void clearDataModel () {
        setId(null);
        setNombre(null);
        setChanged();
        notifyObservers();
    }
}
