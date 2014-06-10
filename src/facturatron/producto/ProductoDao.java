/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.producto;
import facturatron.Dominio.Producto;
import facturatron.MVC.DAO;
import facturatron.MVC.JDBCDAOSupport;
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
public class ProductoDao extends Producto implements DAO <Integer, Producto>{
    private JDBCDAOSupport bd;
    public ProductoDao () {
        setChanged();
    }

    @Override
    public void persist() throws Exception {
        try {
            JDBCDAOSupport bd = getBD();
            bd.conectar();
            PreparedStatement ps;
            if (getId()!= null) {
                ps = bd.getCon().prepareStatement("UPDATE producto SET nombre=?,clave=?,precio=?,activo=?,notas=? WHERE id = ?");
                ps.setInt(6, getId());
            } else {
                ps = bd.getCon().prepareStatement("insert into producto (nombre, clave, precio, activo, notas) VALUES (?,?,?,?,?) ");
            }
            ps.setString(1, getNombre());
            ps.setString(2, getClave());            
            ps.setDouble(3, getPrecio());            
            //ps.setBoolean(4, true);
            ps.setBoolean(4, getActivo());
            ps.setString(5, getNotas());
            ps.execute();
            bd.desconectar();
            //Only for trace
            //Logger.getAnonymousLogger().log(Level.INFO,getActivo().toString());
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, "Error al guardar producto", ex);
            throw ex;
        }
    }

    @Override
    public void remove() {
        try {
            JDBCDAOSupport bd = getBD();
            bd.conectar();
            PreparedStatement ps;
            if (getId()!= null)
            {
                ps = bd.getCon().prepareStatement("DELETE  FROM producto WHERE id = ?");
                ps.setInt(1,getId());
                ps.execute();
            }
            bd.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log (Level.SEVERE, "Error aliminar producto", ex);
        }
        clearDataModel();
    }

    @Override
    public Producto findBy(Integer id) throws SQLException {
        JDBCDAOSupport bd  = getBD();
        try {
            bd.conectar();
            PreparedStatement ps = bd.getCon().prepareStatement("SELECT * FROM producto WHERE id = ?");
            ps.setInt(1, id);            
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            }
            this.setId(rs.getInt("id"));
            this.setNombre(rs.getString("nombre"));
            this.setClave(rs.getString("clave"));
            this.setPrecio(rs.getDouble("precio"));
            this.setActivo(rs.getBoolean("activo" ));
            this.setNotas(rs.getString("notas"));
            setChanged();
            notifyObservers();
            return this;
        }catch (SQLException se ) {
            throw se;
        } finally {
            if (bd != null)
                bd.desconectar();                   
        }
    }

    @Override
    public ArrayList<Producto> findAll() {
        JDBCDAOSupport bd = getBD();
        try {
            bd.conectar();
            ResultSet rs = bd.getStmt().executeQuery("SELECT * FROM producto ");
            ArrayList<Producto> ret = new ArrayList<Producto>();
            Producto bean;
            while (rs.next()) {
                bean = new Producto();
                bean.setId(rs.getInt("id"));                
                bean.setNombre(rs.getString("nombre"));
                bean.setClave(rs.getString("clave"));
                bean.setPrecio(rs.getDouble("precio"));
                bean.setActivo(rs.getBoolean("activo"));
                bean.setNotas(rs.getString("notas"));
                ret.add(bean);
            }
            return ret;
        } catch (Exception ex) {
            Logger.getLogger(FacturaDao.class.getName()).log(Level.SEVERE, "Error al consultar productos", ex);
        } finally {
            bd.desconectar();
        }
        return null;        
    }
    public ArrayList<Producto> find(String searchString) {
        JDBCDAOSupport bd = getBD();
        try {
            bd.conectar();
            PreparedStatement ps = bd.getCon().prepareStatement("SELECT * FROM producto WHERE (nombre like ? OR clave like ?) LIMIT 25");
            searchString = "%"+searchString+"%";
            ps.setString(1, searchString);
            ps.setString(2, searchString);
            ResultSet rs = ps.executeQuery();
            ArrayList<Producto> ret = new ArrayList<>();
            Producto bean;
            while (rs.next()) {
                bean = new Producto();
                bean.setId(rs.getInt("id"));
                bean.setNombre(rs.getString("nombre"));
                bean.setClave(rs.getString("clave"));
                bean.setPrecio(rs.getDouble("precio"));
                bean.setActivo(rs.getBoolean("activo"));
                bean.setNotas(rs.getString("notas"));
                ret.add(bean);
            }
            return ret;
        }catch (Exception ex) {
            Logger.getLogger(FacturaDao.class.getName()).log(Level.SEVERE, "Error al consultar producto", ex);
        } finally {
            bd.desconectar();
        }
        return null;
    }
    @Override
    public JDBCDAOSupport getBD() {
        return new JDBCDAOSupport();
    }
    public void clearDataModel() {
        setId(null);
        setNombre(null);
        setClave(null);
        setActivo(true);
        setPrecio(0.0);
        setNotas(null);
        setChanged();
        notifyObservers();
    }
    
}