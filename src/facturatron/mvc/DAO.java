/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.mvc;

import java.sql.SQLException;
import java.util.List;
import facturatron.DAOException;

/**
 *
 * @author Octavio
 */
public interface DAO<K,E> {
    public void persist() throws Exception;
    public void remove();
    public E findBy(K id) throws SQLException;
    public List<E> findAll() throws DAOException;
    public JDBCDAOSupport getBD();


}
