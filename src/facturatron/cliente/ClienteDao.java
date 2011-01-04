/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.cliente;

import facturatron.Dominio.Persona;
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
 * @author saul
 */
public class ClienteDao extends Persona implements DAO<Integer,Persona> {
     private JDBCDAOSupport bd;

     public  ClienteDao( ){
         setChanged();
     }

    @Override
        public ArrayList<Persona> findAll(){
        JDBCDAOSupport bd = getBD();
         try{

            bd.conectar();

            //Sólo las personas con ID > 2 Son Clientes; La persona 1 es el contribuyente (emisor); La persona 2
            //  es la sucursal.
            ResultSet rs = bd.getStmt().executeQuery("SELECT * FROM Persona WHERE id > 2");
            ArrayList<Persona> ret = new ArrayList<Persona>();
            Persona bean;
            while (rs.next()) {

                bean = new Persona();
                bean.setId(rs.getInt("id"));
                bean.setNombre(rs.getString("nombre"));
                bean.setRfc(rs.getString("rfc"));
                bean.setTelefono(rs.getString("telefono"));
                bean.setCalle(rs.getString("calle"));
                bean.setCodigoPostal(rs.getString("codigoPostal"));
                bean.setColonia(rs.getString("colonia"));
                bean.setMunicipio(rs.getString("municipio"));
                bean.setEstado(rs.getString("estado"));
                bean.setNoExterior(rs.getString("noExterior"));
                bean.setNoInterior(rs.getString("noInterior"));
                bean.setPais(rs.getString("pais"));

                ret.add(bean);

             }
             return ret;
        }catch(Exception ex){

            Logger.getLogger(FacturaDao.class.getName()).log(Level.SEVERE, null, ex);

        } finally{  //si falla o no falla se tiene que desconectar
            bd.desconectar();
        }
        return null;
     }

    public void getClientesInterval(int IdInicial, int nFilas){

        
    }

    @Override
    public void persist() throws SQLException {
        try {
            JDBCDAOSupport bd = getBD();
            bd.conectar();
            PreparedStatement ps;

            if(getId()!=null) {
                ps = bd.getCon().prepareStatement("update persona set nombre=?,rfc=?,telefono=?,calle=?,codigoPostal=?,colonia=?,municipio=?,estado=?,noExterior=?,noInterior=?,pais=? WHERE id = ?");
                ps.setInt(12, getId());
            } else {
                ps = bd.getCon().prepareStatement("insert into persona (nombre,rfc,telefono,calle,codigoPostal,colonia,municipio,estado,noExterior,noInterior,pais) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            }
            
            ps.setString(1, getNombre());
            ps.setString(2, getRfc());
            ps.setString(3, getTelefono());
            ps.setString(4, getCalle());
            ps.setString(5, getCodigoPostal());
            ps.setString(6, getColonia());
            ps.setString(7, getMunicipio());
            ps.setString(8, getEstado());
            ps.setString(9, getNoExterior());
            ps.setString(10, getNoInterior());
            ps.setString(11, getPais());

            ps.execute();
            bd.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, "Error al guardar cliente/persona", ex);
            throw ex;
        }
    }

    @Override
    public void remove() {
        try {
            JDBCDAOSupport bd = getBD();
            bd.conectar();
            PreparedStatement ps;

            if(getId()!=null) {
                ps = bd.getCon().prepareStatement("delete from persona where id = ?");
                ps.setInt(1, getId());
                ps.execute();
            }

            bd.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, "Error al eliminar cliente/persona", ex);
        }
        clearDataModel();
    }


    public void clearDataModel() {
        setId(null);
        setNombre(null);
        setRfc(null);
        setTelefono(null);
        setCalle(null);
        setCodigoPostal(null);
        setColonia(null);
        setMunicipio(null);
        setEstado(null);
        setNoExterior(null);
        setNoInterior(null);
        setPais(null);
        setChanged();
        notifyObservers();
    }
    @Override
    public ClienteDao findBy(Integer id) {
         JDBCDAOSupport bd = getBD();
         try{

            bd.conectar();

            PreparedStatement ps = bd.getCon().prepareStatement("SELECT * FROM Persona WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(!rs.next()) { return null; }

            this.setId(rs.getInt("id"));
            this.setNombre(rs.getString("nombre"));
            this.setRfc(rs.getString("rfc"));
            this.setTelefono(rs.getString("telefono"));
            this.setCalle(rs.getString("calle"));
            this.setCodigoPostal(rs.getString("codigoPostal"));
            this.setColonia(rs.getString("colonia"));
            this.setMunicipio(rs.getString("municipio"));
            this.setEstado(rs.getString("estado"));
            this.setNoExterior(rs.getString("noExterior"));
            this.setNoInterior(rs.getString("noInterior"));
            this.setPais(rs.getString("pais"));

            setChanged();
            notifyObservers();

            return this;
        }catch(Exception ex){

            Logger.getLogger(FacturaDao.class.getName()).log(Level.SEVERE, "Error al consultar cliente/persona", ex);

        } finally{
            bd.desconectar();
        }
        return null;
        
    }

    @Override
    public JDBCDAOSupport getBD() {

        return new JDBCDAOSupport();
    }

   
}
