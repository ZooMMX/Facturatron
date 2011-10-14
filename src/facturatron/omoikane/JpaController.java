/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.omoikane;

import facturatron.config.ConfiguracionDao;
import java.util.HashMap;
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
        ConfiguracionDao config = new ConfiguracionDao();
        config.load();

        HashMap props           = new HashMap();
        Pattern pattern         = Pattern.compile("(^jdbc:mysql://.*?)/[0-9|A-Z|a-z|_|#|$]{1,16}$");
        Matcher matcher         = pattern.matcher(config.getUrlBd());
        String  urlBD           = matcher.replaceAll("$1/Omoikane");

        props.put("javax.persistence.jdbc.url", urlBD);
        emf = Persistence.createEntityManagerFactory("FacturatronPU", props);
    }
    protected EntityManagerFactory emf = null;
}
