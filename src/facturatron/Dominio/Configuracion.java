package facturatron.Dominio;

import facturatron.mvc.Model;
import facturatron.config.ConfiguracionDao;
import facturatron.datasource.DatasourceContext.DATASOURCE;
import facturatron.facturacion.PAC.PACContext.PACS;
import java.io.Serializable;

/**
 *
 * @author Octavio
 */
public class Configuracion extends Model implements Serializable {

    private String pathKey = ".\\";
    private String pathCer = ".\\";
    private String passCer = "";
    private String urlBd = "jdbc:mysql://localhost/facturatron";
    private String userBd = "root";
    private String passBd = "";
    private String pathPdf = ".\\CasaDeLasFacturas\\CFDI_";
    private String pathLogo = ".\\logoejemplo.jpg";
    private String pathPlantilla = ".\\FacturaCFDIGenerica.jasper";
    private String pathXml = ".\\CasaDeLasFacturas\\CFDI_";
    private String smtpHost = "";
    private String usuarioSmtp = "";
    private String claveSmtp = "";
    private String seguridad = "";
    private String puertoSmtp = "25";
    private String visorPDF = "VISOR_NATIVO";
    private String correoRemitente = "";
    private String tituloCorreo = "";
    private String mensajeCorreo = "";
    private String usuarioPAC = "";
    private String passwordPAC = "";
    private PACS   conectorPAC = PACS.Finkok;
    private String usuarioDatasource = "";
    private String passwordDatasource = "";
    private String urlDatasource = "";
    private DATASOURCE conectorDatasource = DATASOURCE.Omoikane;
    private Boolean moduloProductosActivo = false;
    private Boolean moduloUnidadesActivo  = false;
    private static final long serialVersionUID = -3524646329180295196L;

    private static Configuracion config;
    
    /** (Singleton) Carga el archivo de configuración y lo almacena en memoria.
     * Importante: Sólo carga el archivo de configuración una vez por instancia, las demás
     * las toma del atributo config (caché)
     * @return
     */
    public static Configuracion getConfig() {
        if(config == null) { config = (new ConfiguracionDao()).load(); }
        return config;
    }
    
    public static void reloadConfig() {
        config = null;
        getConfig();
    }
    
    /**
     * @return the pathKey
     */
    public String getpathKey() {
        return getPathKey();
    }

    /**
     * @param pathKey the pathKey to set
     */
    public void setpathKey(String pathKey) {
        this.setPathKey(pathKey);
    }

    /**
     * @return the pathCer
     */
    public String getpathCer() {
        return getPathCer();
    }

    /**
     * @param pathCer the pathCer to set
     */
    public void setpathCer(String pathCer) {
        this.setPathCer(pathCer);
    }

    /**
     * @return the passCer
     */
    public String getpassCer() {
        return getPassCer();
    }

    /**
     * @param passCer the passCer to set
     */
    public void setpassCer(String passCer) {
        this.setPassCer(passCer);
    }

    /**
     * @return the urlBd
     */
    public String getUrlBd() {
        return urlBd;
    }

    /**
     * @param urlBd the urlBd to set
     */
    public void setUrlBd(String urlBd) {
        this.urlBd = urlBd;
    }

    /**
     * @return the userBd
     */
    public String getUserBd() {
        return userBd;
    }

    /**
     * @param userBd the userBd to set
     */
    public void setUserBd(String userBd) {
        this.userBd = userBd;
    }

    /**
     * @return the passBd
     */
    public String getPassBd() {
        return passBd;
    }

    /**
     * @param passBd the passBd to set
     */
    public void setPassBd(String passBd) {
        this.passBd = passBd;
    }

    /**
     * @return the pathPdf
     */
    public String getPathPdf() {
        return pathPdf;
    }

    /**
     * @param pathPdf the pathPdf to set
     */
    public void setPathPdf(String pathPdf) {
        this.pathPdf = pathPdf;
    }

    /**
     * @return the pathPlantilla
     */
    public String getPathPlantilla() {
        return pathPlantilla;
    }

    /**
     * @param pathPlantilla the pathPlantilla to set
     */
    public void setPathPlantilla(String pathPlantilla) {
        this.pathPlantilla = pathPlantilla;
    }

    /**
     * @return the pathXml
     */
    public String getPathXml() {
        return pathXml;
    }

    /**
     * @param pathXml the pathXml to set
     */
    public void setPathXml(String pathXml) {
        this.pathXml = pathXml;
    }

    /**
     * @return the stmpHost
     */
    public String getSmtpHost() {
        return smtpHost;
    }

    /**
     * @param stmpHost the stmpHost to set
     */
    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    /**
     * @return the usuarioSmtp
     */
    public String getUsuarioSmtp() {
        return usuarioSmtp;
    }

    /**
     * @param usuarioSmtp the usuarioSmtp to set
     */
    public void setUsuarioSmtp(String usuarioSmtp) {
        this.usuarioSmtp = usuarioSmtp;
    }

    /**
     * @return the claveSmtp
     */
    public String getClaveSmtp() {
        return claveSmtp;
    }

    /**
     * @param claveSmtp the claveSmtp to set
     */
    public void setClaveSmtp(String claveSmtp) {
        this.claveSmtp = claveSmtp;
    }

    /**
     * @return the seguridad
     */
    public String getSeguridad() {
        return seguridad;
    }

    /**
     * @param seguridad the seguridad to set
     */
    public void setSeguridad(String seguridad) {
        this.seguridad = seguridad;
    }

    /**
     * @return the puertoSmtp
     */
    public String getPuertoSmtp() {
        return puertoSmtp;
    }

    /**
     * @param puertoSmtp the puertoSmtp to set
     */
    public void setPuertoSmtp(String puertoSmtp) {
        this.puertoSmtp = puertoSmtp;
    }

    /**
     * @return the visorPDF
     */
    public String getVisorPDF() {
        return visorPDF;
    }

    /**
     * @param visorPDF the visorPDF to set
     */
    public void setVisorPDF(String visorPDF) {
        this.visorPDF = visorPDF;
    }

    /**
     * @return the correoRemitente
     */
    public String getCorreoRemitente() {
        return correoRemitente;
    }

    /**
     * @param correoRemitente the correoRemitente to set
     */
    public void setCorreoRemitente(String correoRemitente) {
        this.correoRemitente = correoRemitente;
    }

    /**
     * @return the tituloCorreo
     */
    public String getTituloCorreo() {
        return tituloCorreo;
    }

    /**
     * @param tituloCorreo the tituloCorreo to set
     */
    public void setTituloCorreo(String tituloCorreo) {
        this.tituloCorreo = tituloCorreo;
    }

    /**
     * @return the mensajeCorreo
     */
    public String getMensajeCorreo() {
        return mensajeCorreo;
    }

    /**
     * @param mensajeCorreo the mensajeCorreo to set
     */
    public void setMensajeCorreo(String mensajeCorreo) {
        this.mensajeCorreo = mensajeCorreo;
    }

    /**
     * @return the usuarioPAC
     */
    public String getUsuarioPAC() {
        return usuarioPAC;
    }

    /**
     * @param usuarioPAC the usuarioPAC to set
     */
    public void setUsuarioPAC(String usuarioPAC) {
        this.usuarioPAC = usuarioPAC;
    }

    /**
     * @return the passwordPAC
     */
    public String getPasswordPAC() {
        return passwordPAC;
    }

    /**
     * @param passwordPAC the passwordPAC to set
     */
    public void setPasswordPAC(String passwordPAC) {
        this.passwordPAC = passwordPAC;
    }

    /**
     * @return the conectorPAC
     */
    public PACS getConectorPAC() {
        return conectorPAC;
    }

    /**
     * @param conectorPAC the conectorPAC to set
     */
    public void setConectorPAC(PACS conectorPAC) {
        this.conectorPAC = conectorPAC;
    }

    /**
     * @return the pathKey
     */
    public String getPathKey() {
        return pathKey;
    }

    /**
     * @param pathKey the pathKey to set
     */
    public void setPathKey(String pathKey) {
        this.pathKey = pathKey;
    }

    /**
     * @return the pathCer
     */
    public String getPathCer() {
        return pathCer;
    }

    /**
     * @param pathCer the pathCer to set
     */
    public void setPathCer(String pathCer) {
        this.pathCer = pathCer;
    }

    /**
     * @return the passCer
     */
    public String getPassCer() {
        return passCer;
    }

    /**
     * @param passCer the passCer to set
     */
    public void setPassCer(String passCer) {
        this.passCer = passCer;
    }

    /**
     * @return the usuarioDatasource
     */
    public String getUsuarioDatasource() {
        return usuarioDatasource;
    }

    /**
     * @param usuarioDatasource the usuarioDatasource to set
     */
    public void setUsuarioDatasource(String usuarioDatasource) {
        this.usuarioDatasource = usuarioDatasource;
    }

    /**
     * @return the passwordDatasource
     */
    public String getPasswordDatasource() {
        return passwordDatasource;
    }

    /**
     * @param passwordDatasource the passwordDatasource to set
     */
    public void setPasswordDatasource(String passwordDatasource) {
        this.passwordDatasource = passwordDatasource;
    }

    /**
     * @return the conectorDatasource
     */
    public DATASOURCE getConectorDatasource() {
        return conectorDatasource;
    }

    /**
     * @param conectorDatasource the conectorDatasource to set
     */
    public void setConectorDatasource(DATASOURCE conectorDatasource) {
        this.conectorDatasource = conectorDatasource;
    }

    /**
     * @return the urlDatasource
     */
    public String getUrlDatasource() {
        return urlDatasource;
    }

    /**
     * @param urlDatasource the urlDatasource to set
     */
    public void setUrlDatasource(String urlDatasource) {
        this.urlDatasource = urlDatasource;
    }

    /**
     * @return the pathLogo
     */
    public String getPathLogo() {
        return pathLogo;
    }

    /**
     * @param pathLogo the pathLogo to set
     */
    public void setPathLogo(String pathLogo) {
        this.pathLogo = pathLogo;
    }

    /**
     * @return the moduloProductosActivo
     */
    public Boolean getModuloProductosActivo() {
        return moduloProductosActivo;
    }

    /**
     * @param moduloProductosActivo the moduloProductosActivo to set
     */
    public void setModuloProductosActivo(Boolean moduloProductosActivo) {
        //Rechaza cambios a valor nulo
        if(moduloProductosActivo == null) return;
        this.moduloProductosActivo = moduloProductosActivo;
    }

    /**
     * @return the moduloUnidadesActivo
     */
    public Boolean getModuloUnidadesActivo() {
        return moduloUnidadesActivo;
    }

    /**
     * @param moduloUnidadesActivo the moduloUnidadesActivo to set
     */
    public void setModuloUnidadesActivo(Boolean moduloUnidadesActivo) {
        //Rechaza cambios a valor nulo
        if(moduloUnidadesActivo == null) return;
        this.moduloUnidadesActivo = moduloUnidadesActivo;
    }
}
