/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.facturacion;


import facturatron.Dominio.Configuracion;
import facturatron.Dominio.Factura;
import facturatron.Dominio.Persona;
import facturatron.Dominio.Renglon;
import facturatron.MVC.DAO;
import facturatron.MVC.JDBCDAOSupport;
import facturatron.Principal.Main;
import facturatron.Principal.VisorPdf;
import facturatron.cliente.ClienteDao;
import facturatron.config.ConfiguracionDao;
import facturatron.email.EmailFacturaCliente;
import facturatron.facturacion.PAC.IPACService;
import facturatron.facturacion.PAC.PACContext;
import facturatron.facturacion.PAC.PACException;
import facturatron.facturacion.PAC.finkok.FinkokPACServiceImpl;
import facturatron.lib.CFDFactory;
import facturatron.lib.entities.CFDv3Tron;
import facturatron.lib.entities.ComprobanteTron;
import facturatron.lib.entities.ConceptosTron;
import java.awt.print.Book;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.xml.bind.MarshalException;
import mx.bigdata.sat.cfdi.CFDv32;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Impuestos;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Impuestos.Traslados;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Impuestos.Traslados.Traslado;
import mx.bigdata.sat.cfdi.v32.schema.ObjectFactory;

/**
 *
 * @author Octavio
 */
public class FacturaDao extends Factura implements DAO<Integer,Factura>{

    public void validateConstraints() throws ValidationException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<FacturaDao>> violations = validator.validate(this);
        String violaciones = "";
        for (ConstraintViolation<FacturaDao> violation : violations) {
           violaciones = violaciones.concat( String.format("%s: %s%n",violation.getPropertyPath(), violation.getMessage()) );
        }
        if(!violations.isEmpty()) throw new ValidationException(violaciones);
    }

    private static class FolioDuplicadoException extends Exception {

        public FolioDuplicadoException() {
            super("Error grave, el folio configurado es incorrecto, por favor llame a soporte técnico");
        }
    }

    Calendar cal = Calendar.getInstance();
    TimeZone tz = TimeZone.getTimeZone("America/Mexico_City");

    public FacturaDao() {
        cal.setTimeZone(tz);
    }

    private Configuracion getConfig() { return Configuracion.getConfig(); }     
    
    String getReciboName() {
        return getReciboName(getSerie(), getFolio().toString());
    }

    String getReciboName(String serie, String folio) {
        serie                = (serie!=null?serie:"");
        String nombreRecibo  = "Factura"+serie+folio;
        return nombreRecibo;
    }

    String getPdfPath() {
        return getConfig().getPathPdf()+getReciboName()+".pdf";
    }

    String getXmlPath() {
        return getConfig().getPathXml()+getReciboName()+".xml";
    }
    String getPdfPath(String s, String f) { return getConfig().getPathPdf()+getReciboName(s,f)+".pdf"; }
    String getXmlPath(String s, String f) { return getConfig().getPathXml()+getReciboName(s,f)+".xml"; }


    private CFDv3Tron sellarFirmarYTimbrar() throws Exception {
        try {
            ComprobanteTron comp = toComprobanteTron();
            
            //Sellar, firmar y timbrar mediante CFDFactory
            CFDFactory cfdf = new CFDFactory();
            CFDv3Tron cfd = cfdf.toCFDI(comp);
            
            setSello(comp.getSello());
            setXml(cfd.getXML());
            setFolioFiscal(cfd.getComprobante().getFolioFiscal());
            
            return cfd;
        } catch (URISyntaxException u) {
            throw new Exception("Dirección(es) URI del certificado y/o llave tienen una sintaxis incorrecta.", u);
        } catch (Exception ex) {
            throw ex;
        }
     }
    

     public ComprobanteTron toComprobanteTron() throws Exception {

        MathContext mc = MathContext.DECIMAL64;
        ComprobanteTron comp = new ComprobanteTron();
                /* CFDv32.newComprobante( new ByteArrayInputStream(getXml().getBytes("UTF-8")) );
        ComprobanteTron compTron = new ComprobanteTron(comp); */

        comp.setVersion(getVersion());

        Calendar dCal = Calendar.getInstance();
        dCal.setTime(getFecha());
        Calendar tCal = Calendar.getInstance();
        tCal.setTime(getHora());
        dCal.set(Calendar.HOUR_OF_DAY, tCal.get(Calendar.HOUR_OF_DAY));
        dCal.set(Calendar.MINUTE, tCal.get(Calendar.MINUTE));
        dCal.set(Calendar.SECOND, tCal.get(Calendar.SECOND));
        dCal.set(Calendar.MILLISECOND, tCal.get(Calendar.MILLISECOND));

        comp.setFecha(dCal.getTime());
        comp.setSerie(getSerie());
        comp.setFolio(String.valueOf(getFolio()));
        comp.setFormaDePago(getFormaDePago());
        comp.setMetodoDePago(getMetodoDePago());
        comp.setLugarExpedicion(getEmisor().getMunicipio()+", "+getEmisor().getEstado());

        comp.setSubTotal(getSubtotal().setScale(2,RoundingMode.HALF_EVEN));
        comp.setTotal(getTotal().setScale(2,RoundingMode.HALF_EVEN));
        comp.setDescuento(getDescuentoTasa0().add(getDescuentoTasa16()));
        comp.setTipoDeComprobante(getTipoDeComprobante());
        comp.setObservaciones(getObservaciones());
        Persona emSucursal = getEmisorSucursal();
        if(emSucursal.getEstado().isEmpty()) { emSucursal = null; }
        comp.setEmisor(getEmisor().toEmisor(emSucursal));
        comp.setReceptor(getReceptor().toReceptor());
        ConceptosTron cTron = getConceptosTron();
        comp.setConceptos(cTron.toConceptos());
        comp.setConceptosTron(cTron);
        comp.setImpuestos(getImpuestos());
        comp.setSubtotalGravado16(getSubtotalGravado16());
        comp.setSubtotalGravado0(getSubtotalGravado0());
        comp.setSubtotalExento(getSubtotalExento());
        comp.setEstadoComprobante(getEstadoComprobante()==getEstadoComprobante().VIGENTE?true:false);       
         
        return comp;
     }

     public ConceptosTron getConceptosTron() {
        ConceptosTron cps = new ConceptosTron();
        
        for (Renglon renglon : getRenglones()) {
            if(renglon.getImporte().equals(new BigDecimal(0d)) || renglon.getImporte() == null) { continue; }
            cps.add(renglon.toConceptoTron());
        }
        return cps;
     }

     public Impuestos getImpuestos() {
        
        ObjectFactory of = new ObjectFactory();
        Impuestos imps = of.createComprobanteImpuestos();
        Traslados trs = of.createComprobanteImpuestosTraslados();
        List<Traslado> list = trs.getTraslado();

        Traslado t1 = of.createComprobanteImpuestosTrasladosTraslado();
        Traslado t2 = of.createComprobanteImpuestosTrasladosTraslado();
        t1.setImporte(getIvaTrasladado());
        t1.setImpuesto("IVA");
        t1.setTasa(new BigDecimal("16.00"));
        list.add(t1);
        t2.setImporte(new BigDecimal(0d));
        t2.setImpuesto("IVA");
        t2.setTasa(new BigDecimal("0.00"));
        list.add(t2);
        imps.setTraslados(trs);
        imps.setTotalImpuestosTrasladados(getIvaTrasladado());
        return imps;
     }

     public BigDecimal getSubtotalGravado16() {
        BigDecimal gravado = new BigDecimal(0d);
        for (Renglon renglon : getRenglones()) {
            if(!renglon.getTasa0()) { gravado = gravado.add(renglon.getImporte()); }
        }
        return gravado;
     }

     public BigDecimal getSubtotalExento() {
         return new BigDecimal(0d);
     }

     public BigDecimal getSubtotalGravado0() {
        BigDecimal gravado0 = new BigDecimal(0d);
        for (Renglon renglon : getRenglones()) {
            if(renglon.getTasa0()) { gravado0 = gravado0.add(renglon.getImporte()); }
        }
        return gravado0;
     }

     @Override
     public void setTotal(BigDecimal total) {
        super.setTotal(total);
        setChanged();
        notifyObservers();
     }

    @Override
     public void setDescuentoTasa0(BigDecimal descuento) {
         super.setDescuentoTasa0(descuento);
         setChanged();
         notifyObservers();
     }

    @Override
     public void setDescuentoTasa16(BigDecimal descuento) {
         super.setDescuentoTasa16(descuento);
         setChanged();
         notifyObservers();
     }

     @Override
     public void setReceptor(Persona receptor) {
        super.setReceptor(receptor);
        setChanged();
        notifyObservers();
     }
     
     @Override
     public void setMetodoDePago(String metodoDePago) {
        super.setMetodoDePago(metodoDePago);
        setChanged();
        notifyObservers();
     }

     public ArrayList<FacturaDao> findAll(Date fechaInicial, Date fechaFinal){
         JDBCDAOSupport bd = getBD();
         try {

            bd.conectar();
            PreparedStatement ps = bd.getCon().prepareStatement("SELECT * FROM comprobante WHERE fecha >= ? and fecha <= ?");
            ps.setDate(1, fechaInicial);
            ps.setDate(2, fechaFinal);
            ResultSet rs = ps.executeQuery();
            ArrayList<FacturaDao> ret = new ArrayList<FacturaDao>();
            FacturaDao bean;
            while (rs.next()) {

                bean = new FacturaDao();
                bean.setId(rs.getInt("id"));
                bean.setVersion(rs.getString("version"));
                bean.setFecha(rs.getDate("fecha"));
                bean.setHora(rs.getTime("hora",cal));
                bean.setSerie(rs.getString("serie"));
                bean.setFolio(BigInteger.valueOf(rs.getLong("folio")));
                bean.setSello(rs.getString("sello"));
                bean.setNoCertificado(rs.getString("noCertificado"));
                bean.setNoAprobacion(BigInteger.valueOf(rs.getInt("noAprobacion")));
                bean.setAnoAprobacion(rs.getInt("anoAprobacion"));
                bean.setFormaDePago(rs.getString("formaDePago"));
                //bean.setMetodoDePago(rs.getString( "metodoDePago" ));
                bean.setSubtotal(rs.getBigDecimal("subtotal"));
                bean.setTotal(rs.getBigDecimal("total"));
                bean.setDescuentoTasa0(rs.getBigDecimal("descuentoTasa0"));
                bean.setDescuentoTasa16(rs.getBigDecimal("descuentoTasa16"));
                bean.setTipoDeComprobante(rs.getString("tipoDeComprobante"));
                bean.setEmisor((new ClienteDao()).findBy(rs.getInt("idemisor")));
                bean.setReceptor((new ClienteDao()).findBy(rs.getInt("idReceptor")));
                bean.setIvaTrasladado(rs.getBigDecimal("ivaTrasladado"));
                bean.setCertificado(rs.getString("certificado"));
                bean.setMotivoDescuento(rs.getString("motivoDescuento"));
                bean.setXml(rs.getString("xml"));
                bean.setEstadoComprobante(rs.getString("estadoComprobante").equals("VIGENTE")?Estado.VIGENTE:Estado.CANCELADO);
                bean.setObservaciones(rs.getString("observaciones"));
                bean.setFolioFiscal(rs.getString("folioFiscal"));
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

    /** Actualiza éste comprobante, pocos atributos serán editables
     *
     * @throws SQLException
     */
    public void update() throws SQLException {
        JDBCDAOSupport bd = getBD();
        bd.conectar(true);
        
        PreparedStatement ps = bd.getCon().prepareStatement("update comprobante SET " +
                "estadoComprobante=? " +
                "WHERE id=?");

        ps.setString(1, getEstadoComprobante()==Estado.VIGENTE?"VIGENTE":"CANCELADO");
        ps.setInt(2, getId());

        ps.executeUpdate();
    }
    
    @Override
    public void persist() throws PACException, SQLException, Exception {
        
        Boolean timbrado = false;
        JDBCDAOSupport bd = null;
        CFDv3Tron comprobanteSelladoFirmadoTimbrado;
        
        try {                    
            
            bd = getBD();
            bd.conectar(true);
            bd.getCon().setAutoCommit(false);

            setFolio(SerieDao.nextId(bd.getCon()));

            if(findByFolio(getFolio())!=null) { throw new FolioDuplicadoException(); }

            comprobanteSelladoFirmadoTimbrado = sellarFirmarYTimbrar();
            timbrado = true; //Necesario para posible rollback

            PreparedStatement ps = bd.getCon().prepareStatement("insert into comprobante " +
                    "(version,fecha,serie,folio,sello,noCertificado,noAprobacion,anoAprobacion," +
                    "formaDePago,subtotal,total,descuentoTasa0,descuentoTasa16,tipoDeComprobante,idEmisor, idReceptor," +
                    "ivaTrasladado,certificado,motivoDescuento,xml,estadoComprobante,observaciones,hora, folioFiscal) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            ps.setString(1, getVersion());
            ps.setDate(2, new java.sql.Date(getFecha().getTime()));
            ps.setString(3, getSerie());
            ps.setLong(4, getFolio().longValue());
            ps.setString(5, getSello());
            ps.setString(6, getNoCertificado());
            ps.setInt(7, getNoAprobacion().intValue());
            ps.setInt(8, getAnoAprobacion().intValue());
            ps.setString(9, getFormaDePago());
            ps.setBigDecimal(10, getSubtotal());
            ps.setBigDecimal(11, getTotal());
            ps.setBigDecimal(12, getDescuentoTasa0());
            ps.setBigDecimal(13, getDescuentoTasa16());
            ps.setString(14, getTipoDeComprobante());
            ps.setInt(15, getEmisor().getId());
            ps.setInt(16, getReceptor().getId());
            ps.setBigDecimal(17, getIvaTrasladado());
            ps.setString(18, getCertificado());
            ps.setString(19, getMotivoDescuento());
            ps.setString(20, getXml());
            ps.setString(21, getEstadoComprobante()==Estado.VIGENTE?"VIGENTE":"CANCELADO");
            ps.setString(22, getObservaciones());
            ps.setTime  (23, getHora());
            ps.setString(24, getFolioFiscal());
            //ps.setString(24, getMetodoDePago());

            ps.execute();

            ResultSet keys = ps.getGeneratedKeys();
            keys.next();
            int idfactura = keys.getInt(1);
            keys.close();

            for (Renglon renglon : getRenglones()) {  //
                if(renglon.getImporte().compareTo(new BigDecimal(0d))<=0) { continue; }
                ps = bd.getCon().prepareStatement("insert into concepto (idComprobante,unidad,noIdentificacion,importe," +
                        "cantidad,descripcion,valorunitario,tasa0) VALUES (?,?,?,?,?,?,?,?)");
                ps.setInt(1, idfactura);
                ps.setString(2, renglon.getUnidad());
                ps.setString(3, renglon.getNoIdentificacion());
                ps.setBigDecimal(4, renglon.getImporte());
                ps.setBigDecimal(5, renglon.getCantidad());
                ps.setString(6, renglon.getDescripcion());
                ps.setBigDecimal(7, renglon.getValorUniario());
                ps.setInt(8, renglon.getTasa0()?1:0);
                ps.execute();
            }
            //Distribuir (xml, pdf, mail) utilizando el handler apropiado para el PAC seleccionado
            PACContext.instancePACService().getDistribucionHandler().distribuir(this, comprobanteSelladoFirmadoTimbrado);
            bd.getCon().commit();
        } catch (PACException ex) {            
            throw ex;
        } catch (SQLException ex) {
            //En caso de que haya quedado registrado el timbre, es necesario cancelarlo
            if(bd != null && timbrado) { rollBackTimbre(this); }
            throw ex;
        } catch (Exception ex) {
            if(bd != null && timbrado) { rollBackTimbre(this); }
            throw ex;
        } finally {
            //TODO Rollback del timbre?
            if(bd != null) {
                bd.getCon().rollback();
                bd.desconectar();
            }
        }
    }
    private void rollBackTimbre(Factura c) {
        try {
            IPACService pacService = PACContext.instancePACService();
            pacService.cancelar(c);
        } catch (PACException ex) {
            Logger.getLogger(FacturaDao.class.getName()).log(Level.SEVERE, "Error descartando timbre. ¡Esta factura puede haber quedar registrada en el PAC y SAT!", ex);
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Factura findByFolio(BigInteger folio) {
        try {
            JDBCDAOSupport bd = getBD();
            FacturaDao dao = new FacturaDao();
            bd.conectar();
            ResultSet rs = bd.getStmt().executeQuery("select id from comprobante where folio = " + folio);
            if(rs.next()) {
                Factura fact = findBy(rs.getInt("id"));
                rs.close();
                bd.desconectar();
                return fact;
            } else {
                return null;
            }
        } catch(Exception e) {
            Logger.getLogger(FacturaDao.class.getName()).log(Level.SEVERE, "Excepción buscando por folio", e);
        }
        return null;
    }

    @Override
    public Factura findBy(Integer id) {
     try {
            JDBCDAOSupport bd = getBD();
            FacturaDao dao = new FacturaDao();
            bd.conectar();
            ResultSet rs = bd.getStmt().executeQuery("select * from comprobante where id = " + id);
            rs.next();
            dao.setId(id);
            dao.setVersion(rs.getString("version"));
            dao.setFecha(rs.getDate("fecha"));
            dao.setHora(rs.getTime("hora", Calendar.getInstance()));
            dao.setSerie(rs.getString("serie"));
            dao.setFolio(BigInteger.valueOf(rs.getLong("folio")));
            dao.setSello(rs.getString("sello"));
            dao.setNoCertificado(rs.getString("noCertificado"));
            dao.setNoAprobacion(BigInteger.valueOf(rs.getInt("noAprobacion")));
            dao.setAnoAprobacion(rs.getInt("anoAprobacion"));
            dao.setFormaDePago(rs.getString("formaDePago"));
            dao.setSubtotal(rs.getBigDecimal("subtotal"));
            dao.setTotal(rs.getBigDecimal("total"));
            dao.setDescuentoTasa0(rs.getBigDecimal("descuentoTasa0"));
            dao.setDescuentoTasa16(rs.getBigDecimal("descuentoTasa16"));
            dao.setTipoDeComprobante(rs.getString("tipoDeComprobante"));
            dao.setEmisor((new ClienteDao()).findBy(rs.getInt("idemisor")));
            dao.setReceptor((new ClienteDao()).findBy(rs.getInt("idReceptor")));
            dao.setIvaTrasladado(rs.getBigDecimal("ivaTrasladado"));
            dao.setCertificado(rs.getString("certificado"));
            dao.setMotivoDescuento(rs.getString("motivoDescuento"));
            dao.setXml(rs.getString("xml"));
            dao.setEstadoComprobante(rs.getString("estadoComprobante").equals("VIGENTE")?Estado.VIGENTE:Estado.CANCELADO);
            dao.setObservaciones(rs.getString("observaciones"));
            dao.setFolioFiscal(rs.getString("folioFiscal"));

            rs = bd.getStmt().executeQuery("select * from concepto where id = "+id);//
            ArrayList <Renglon> renglones = new ArrayList <Renglon>();
            Renglon rb = new Renglon();
            while(rs.next()){
                rb = new Renglon();
                rb.setId(rs.getInt("id"));
                rb.setUnidad(rs.getString("unidad"));
                rb.setNoIdentificacion(rs.getString("noIdentificacion"));
                rb.setImporte(rs.getBigDecimal("importe"));
                rb.setCantidad(rs.getBigDecimal("cantidad"));
                rb.setDescripcion(rs.getString("descripcion"));
                rb.setValorUniario(rs.getBigDecimal("valorUnitario"));
                rb.setTasa0(rs.getInt("tasa0")==1);
                renglones.add(rb);
            }
            dao.setRenglones(renglones);
            bd.desconectar();
            rs.close();
            return dao;
        } catch (SQLException ex) {
            Logger.getLogger(FacturaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public JDBCDAOSupport getBD() {
       return new JDBCDAOSupport();
    }

    @Override
    public List<Factura> findAll() {
        throw new UnsupportedOperationException("Not supported. Instead use findAll(Date fechaInicial, Date fechaFinal)");
    }


    void cancelar() throws SQLException, PACException {
        
        Estado estadoOriginal = getEstadoComprobante();
        try {
            setEstadoComprobante(Estado.CANCELADO);
            update();
        } catch(SQLException ex) {
            //Si falló el almacenamiento de la cancelación, no continuar
            throw ex;
        }
        
        try {            
            Factura comprobante = this;

            IPACService impl = PACContext.instancePACService();
            Boolean isCancelado = impl.cancelar(comprobante);

        } catch(PACException pe) {
            //Rollback DB manual
            try {
                setEstadoComprobante(estadoOriginal);
                update();
            } catch(SQLException ex) {
                Logger.getLogger(FacturaDao.class.getName()).log(Level.SEVERE, "Error rolling back, el comprobante quedó en un estado inconsistente.", ex);
            }

            //Exception to be thrown
            throw pe;
        }
        
    }

}
