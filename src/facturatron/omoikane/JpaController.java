/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.omoikane;

import facturatron.Dominio.Configuracion;
import com.phesus.facturatron.persistence.dao.ConfiguracionDao;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author octavioruizcastillo
 */
public class JpaController {

    public JpaController() {
        Configuracion config = ConfiguracionDao.getConfig();

        Map<String, Object> props = new HashMap<String, Object>();

        props.put("javax.persistence.jdbc.url", config.getUrlDatasource());
        props.put("javax.persistence.jdbc.password", config.getPasswordDatasource());
        props.put("javax.persistence.jdbc.user", config.getUsuarioDatasource());
        emf = Persistence.createEntityManagerFactory("FacturatronPU", props);
    }
    protected EntityManagerFactory emf = null;
}
