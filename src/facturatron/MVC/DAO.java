/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.MVC;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Octavio
 */
public interface DAO<K,E> {
    public void persist() throws SQLException;
    public void remove();
    public E findBy(K id);
    public List<E> findAll();
    public JDBCDAOSupport getBD();


}
