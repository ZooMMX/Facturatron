/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.Dominio;

import facturatron.MVC.Model;
import java.io.Serializable;
import mx.bigdata.sat.cfdi.v33.schema.CPais;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante.Emisor;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante.Receptor;
import mx.bigdata.sat.cfdi.v33.schema.ObjectFactory;

/**
 *
 * @author saul
 */
public class Persona extends Model implements Serializable {
    private Integer id          = -1;
    private String nombre       = "";
    private String rfc          = "";
    private String telefono     = "";
    private String calle        = "";
    private String codigoPostal = "";
    private String colonia      = "";
    private String municipio    = "";
    private String localidad    = null;
    private String estado       = "";
    private String noExterior   = "";
    private String noInterior   = "";
    private String pais         = "";
    private String regimen      = "NA";
    private String correoElectronico = "";


    public Emisor toEmisor(Persona sucursal) {
        ObjectFactory of = new ObjectFactory();
        Emisor emisor = of.createComprobanteEmisor();
        emisor.setNombre(getNombre());
        emisor.setRfc(getRfc());

        //Deprecated. Se quitó en CFDI 3.3
        //TUbicacionFiscal uf = of.createTUbicacionFiscal();
        //uf.setCalle(getCalle());
        //uf.setCodigoPostal(getCodigoPostal());
        //uf.setColonia(getColonia());
        //uf.setEstado(getEstado());
        //uf.setReferencia("Vacío");
        //uf.setMunicipio(getMunicipio());
        //uf.setNoExterior(getNoExterior());
        //uf.setNoInterior(getNoInterior());
        //uf.setPais(getPais());
        //emisor.setDomicilioFiscal(uf);

        //RegimenFiscal rf = new RegimenFiscal();
        //rf.setRegimen(getRegimen());
        emisor.setRegimenFiscal(getRegimen());

        //if(sucursal != null) {
        //    TUbicacion u = of.createTUbicacion();
        //    u.setCalle(sucursal.getCalle());
        //    u.setCodigoPostal(sucursal.getCodigoPostal());
        //    u.setColonia(sucursal.getColonia());
        //    u.setEstado(sucursal.getEstado());
        //    u.setMunicipio(sucursal.getMunicipio());
        //    u.setNoExterior(sucursal.getNoExterior());
        //    u.setNoInterior(sucursal.getNoInterior());
        //    u.setPais(sucursal.getPais());
        //    emisor.setExpedidoEn(u);
        //}
        
        return emisor;
    }

    public Receptor toReceptor() {
        ObjectFactory of = new ObjectFactory();
        Receptor receptor = of.createComprobanteReceptor();
        receptor.setNombre(getNombre());
        receptor.setRfc(getRfc());
        receptor.setResidenciaFiscal(CPais.valueOf(getPais()));
        //El campo usoCFDI se llenará al realizar la factura
        //Campos que ya no se usan:
        //TUbicacion uf = of.createTUbicacion();
        //uf.setCalle(getCalle());
        //uf.setCodigoPostal(getCodigoPostal());
        //uf.setColonia(getColonia());
        //uf.setEstado(getEstado());
        //uf.setReferencia("Vacío");
        //uf.setMunicipio(getMunicipio());
        //uf.setLocalidad(getLocalidad());
        //uf.setNoExterior(getNoExterior());
        //uf.setNoInterior(getNoInterior());
        //uf.setPais(getPais());
        
        //receptor.setDomicilio(uf);

        return receptor;
    }

    /**
     * @return the rfc
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * @param rfc the rfc to set
     */
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the calle
     */
    public String getCalle() {
        return calle;
    }

    /**
     * @param calle the calle to set
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * @return the codigoPostal
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * @param codigoPostal the codigoPostal to set
     */
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * @return the colonia
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * @param colonia the colonia to set
     */
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    /**
     * @return the municipio
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * @param municipio the municipio to set
     */
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the noExterior
     */
    public String getNoExterior() {
        return noExterior;
    }

    /**
     * @param noExterior the noExterior to set
     */
    public void setNoExterior(String noExterior) {
        this.noExterior = noExterior;
    }

    /**
     * @return the noInterior
     */
    public String getNoInterior() {
        if(noInterior == null || noInterior.isEmpty()) { return "NA"; }
        return noInterior;
    }

    /**
     * @param noInterior the noInterior to set
     */
    public void setNoInterior(String noInterior) {
        this.noInterior = noInterior;
    }

    /**
     * @return the pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    /**
     * @return the correoElectronico
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * @param correoElectronico the correoElectronico to set
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * @return the localidad
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * @param localidad the localidad to set
     */
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
}
