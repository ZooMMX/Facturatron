/*
 * Copyright (C) 2013 octavioruizcastillo
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package facturatron.facturacion.PAC.facturainteligente;

import com.facturainteligente.ArrayOfString;
import com.google.common.base.Joiner;
import facturatron.Dominio.Configuracion;
import facturatron.lib.entities.ComprobanteTron;
import facturatron.lib.entities.ConceptoTron;
import facturatron.lib.entities.ConceptosTron;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante;
import mx.bigdata.sat.cfdi.v32.schema.TUbicacion;

    /**
     * Adaptador de datos de formato ComprobanteTron a FacturaInteligente. Los atributos están previamente
     * validados.
     * @author octavioruizcastillo @ phesus
     */

    public class FIAdapter {
        ComprobanteTron comprobanteTron;
        Configuracion config;
        
        public FIAdapter(ComprobanteTron comprobante) {
            config          = Configuracion.getConfig();
            comprobanteTron = comprobante;
        }        
        
        //Layout: [0] RFC, [1] Cuenta, [2] Password
        public ArrayOfString getDatosUsuario() {
            Comprobante.Emisor emisor = comprobanteTron.getEmisor();
            
            ArrayOfString array = new ArrayOfString();
            List<String> datos  = array.getString();
            
            datos.add(0, emisor.getRfc());
            datos.add(1, config.getUsuarioPAC());
            datos.add(2, config.getPasswordPAC());
                    
            array.getString().addAll(datos);
            return array;
        }
        
        /**
         * Layout: [0] NombreCliente, [1] Contacto, [2] Telefono, [3] Email, [4] rfcReceptor, 
         * [5] nombreReceptor, [6] calleReceptor, [7] noExteriorReceptor, [8] noInteriorReceptor,
         * [9] coloniaReceptor, [10] localidadReceptor, [11] referenciaReceptor, [12] municipioReceptor,
         * [13] estadoReceptor, [14] paisReceptor, [15] codigoPostalReceptor.
         * Notas: 
         * - El atributo 0 lo igualo a la razón social del receptor.
         * - Con respecto a los atributos 1, 2 y 3 no los utilizamos porque no estamos tratando al PAC
         * como base de datos ni como cliente de correo electrónico, ni para generar el PDF o el código
         * de barras bidimensional, el PAC únicamente nos sirve para timbrar.
         */

        public ArrayOfString getDatosReceptor() {
            Comprobante.Receptor receptor = comprobanteTron.getReceptor();
            
            ArrayOfString array  = new ArrayOfString();
            List<String> datos   = array.getString();
            TUbicacion domicilio = receptor.getDomicilio();
            String localidad     = domicilio.getLocalidad(); 
            
            datos.add(0, receptor.getNombre());
            datos.add(1, "");
            datos.add(2, "");
            datos.add(3, "");
            datos.add(4, receptor.getRfc());
            datos.add(5, receptor.getNombre());
            datos.add(6, domicilio.getCalle());
            datos.add(7, domicilio.getNoExterior());
            datos.add(8, domicilio.getNoInterior());
            datos.add(9, domicilio.getColonia());
            datos.add(10, localidad == null ? "" : localidad);
            datos.add(11, domicilio.getReferencia());
            datos.add(12, domicilio.getMunicipio());
            datos.add(13, domicilio.getEstado());
            datos.add(14, domicilio.getPais());
            datos.add(15, domicilio.getCodigoPostal());
            
            return array;
        }
        
        /**
         * Notas:
         * - El atributo 0 generaliza el atributo TipoDeComprobante (requerido) en el atributo 
         * "claveCFDI" (inventado, no estándar de FacturaInteligente, solo reconoce FAC = ingreso, 
         * CRE = egreso y POR = traslado.
         * - Atributo 2 tampoco es estándar, por lo que lo dejo vacío, de acuerdo al anexo 20, las 
         * parcialidades se escriben en el atributo FormaDePago.
         * - Otros atributos no estándar no implementados: 6, 10
         * Layout: [0] ClaveCFDI, [1] formaDePago, [2] parcialidades, [3] condicionesDePago, 
         * [4] metodoDePago, [5] descuento, [6] porcentajeDescuento, [7] motivoDescuento, [8] moneda, 
         * [9] tipoCambio, [10] fechaTipoCambio, [11] totalImpuestosRetenidos, 
         * [12] totalImpuestosTrasladados, [13] subTotal, [14] total, [15] importeConLetra, 
         * [16] LugarExpedicion, [17] NumCuentaPago, [18] FolioFiscalOrig, [19] SerieFolioFiscalOrig, 
         * [20] FechaFolioFiscalOrig, [21] MontoFolioFiscalOrig
         * @param comprobante
         * @return 
         */
        public ArrayOfString getDatosCFDI() {
            ComprobanteTron comprobante = comprobanteTron;
            
            ArrayOfString array = new ArrayOfString();
            List<String> datos  = array.getString();
            DecimalFormat df    = new DecimalFormat("#0.######");
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            
            String claveCFDI = "";
            /* Java 7
            switch(comprobante.getTipoDeComprobante()) {
                case "ingreso":  claveCFDI = "FAC"; break;
                case "egreso":   claveCFDI = "CRE"; break;
                case "traslado": claveCFDI = "POR"; break;
            }
            */
            if(comprobante.getTipoDeComprobante().equals("ingreso"))  claveCFDI = "FAC";
            if(comprobante.getTipoDeComprobante().equals("egreso"))   claveCFDI = "CRE";
            if(comprobante.getTipoDeComprobante().equals("traslado")) claveCFDI = "POR";
            
            BigDecimal totalImpRetenidos   = comprobante.getImpuestos().getTotalImpuestosRetenidos();
            BigDecimal totalImpTrasladados = comprobante.getImpuestos().getTotalImpuestosTrasladados();
            BigDecimal zero                = new BigDecimal(0);
            String     condicionesDePago   = comprobante.getCondicionesDePago();
            String     motivoDescuento     = comprobante.getMotivoDescuento();
            String     moneda              = comprobante.getMoneda();
            String     tipoCambio          = comprobante.getTipoCambio();
            String     numCtaPago          = comprobante.getNumCtaPago();
            String     folioFiscalOrig     = comprobante.getFolioFiscalOrig();
            String     serieFolioFiscalOrig= comprobante.getSerieFolioFiscalOrig();
            Date       fechaFolioFiscalOrig= comprobante.getFechaFolioFiscalOrig();
            BigDecimal montoFolioFiscalOrig= comprobante.getMontoFolioFiscalOrig();
            
            datos.add(0,  claveCFDI);
            datos.add(1,  comprobante.getFormaDePago());
            datos.add(2,  "");
            datos.add(3,  condicionesDePago == null ? "" : condicionesDePago);
            datos.add(4,  comprobante.getMetodoDePago());
            datos.add(5,  df.format( comprobante.getDescuento() ));
            datos.add(6,  "0");
            datos.add(7,  motivoDescuento == null ? ""    : motivoDescuento);
            datos.add(8,  moneda          == null ? "MXN" : moneda );
            datos.add(9,  tipoCambio      == null ? ""    : tipoCambio);
            datos.add(10, "");
            datos.add(11, df.format( totalImpRetenidos  ==null ? zero : totalImpRetenidos ));
            datos.add(12, df.format( totalImpTrasladados==null ? zero : totalImpTrasladados ));
            datos.add(13, df.format( comprobante.getSubTotal() ));
            datos.add(14, df.format( comprobante.getTotal() ));
            datos.add(15, comprobante.getImporteConLetra());
            datos.add(16, comprobante.getLugarExpedicion());
            datos.add(17, numCtaPago           == null ? "" : numCtaPago);
            datos.add(18, folioFiscalOrig      == null ? "" : folioFiscalOrig);
            datos.add(19, serieFolioFiscalOrig == null ? "" : serieFolioFiscalOrig);
            datos.add(20, fechaFolioFiscalOrig == null ? "" : sdf.format( fechaFolioFiscalOrig ));
            datos.add(21, montoFolioFiscalOrig == null ? "0" : df .format( montoFolioFiscalOrig ));
            
            return array;
        }
        
        /**
         * Datos específicos de FacturaInteligente, no se llena pero se genera por requisito
         * @return 
         */
        public ArrayOfString getDatosEtiquetas() {
            ArrayOfString array = new ArrayOfString();
            return array;
        }
        
        /**
         * Layout: | Cantidad | Unidad | noIdentificacion | Descripcion | valorUnitario | Importe |
         */
        public ArrayOfString getDatosConceptos() {
            ConceptosTron conceptos = comprobanteTron.getConceptosTron();
            
            ArrayOfString array = new ArrayOfString();
            List<String> datos  = array.getString();
            
            DecimalFormat df    = new DecimalFormat("#0.######");
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            
            for (ConceptoTron c : conceptos) {
                final String[] cadena = {
                    df.format( c.getCantidad() ), 
                    c.getUnidad(),
                    c.getNoIdentificacion(),
                    c.getDescripcion(),
                    df.format( c.getValorUnitario() ),
                    df.format( c.getImporte() )
                };
                datos.add( "|" + Joiner.on("|").join(cadena) + "|" );
            }

            return array;
        }
        
        /**
         * La información sobre aduanas no ha sido implementada en Facturatron o no se ha actualizado
         * este conector. Por requerimiento de FacturaInteligente es necesario igualar el tamaño de la
         * colección de aduanas con la de conceptos.
         * @param conceptos
         * @return 
         */
        public ArrayOfString getDatosInfoAduanera(ArrayOfString conceptos) {
            ArrayOfString array = new ArrayOfString();
            for (String concepto : conceptos.getString()) {
                array.getString().add("");
            }
            return array;
        }
        
        /**
         * Los valores NombreImpuesto e impuesto están igualados al estándar, Por ejemplo: IVA
         * Layout: |NombreImpuesto|Impuesto|Importe|
         * @param impuestos
         * @return 
         */
        public ArrayOfString getDatosRetenidos() {
            Comprobante.Impuestos impuestos = comprobanteTron.getImpuestos();
            
            ArrayOfString array = new ArrayOfString();
            DecimalFormat df    = new DecimalFormat("#0.##");
            List<String> datos  = array.getString();

            //Nullsafe
            if(impuestos == null || impuestos.getRetenciones() == null || impuestos.getRetenciones().getRetencion() == null)
                return array;
            
            List<Comprobante.Impuestos.Retenciones.Retencion> retenciones = impuestos.getRetenciones().getRetencion();
            for (Comprobante.Impuestos.Retenciones.Retencion retencion : retenciones) {
                final String[] cadena = {
                    retencion.getImpuesto(),
                    retencion.getImpuesto(),
                    df.format( retencion.getImporte() )
                };
                datos.add( "|" + Joiner.on("|").join(cadena) + "|" );
            }
            
            return array;
        }
        
        /**
         * Los valores NombreImpuesto e impuesto están igualados al estándar, Por ejemplo: IVA
         * Layout: |NombreImpuesto|impuesto|tasa|importe|
         * @param impuestos
         * @return 
         */
        public ArrayOfString getDatosTraslados() {
            Comprobante.Impuestos impuestos = comprobanteTron.getImpuestos();
            
            ArrayOfString array = new ArrayOfString();
            DecimalFormat df    = new DecimalFormat("#0.##");
            List<String> datos  = array.getString();
            
            //Nullsafe
            if(impuestos == null || impuestos.getTraslados() == null || impuestos.getTraslados().getTraslado() == null)
                return array;
            
            List<Comprobante.Impuestos.Traslados.Traslado> traslados = impuestos.getTraslados().getTraslado();
            for (Comprobante.Impuestos.Traslados.Traslado traslado : traslados) {
                final String[] cadena = {
                    traslado.getImpuesto(),
                    traslado.getImpuesto(),
                    df.format( traslado.getTasa() ),
                    df.format( traslado.getImporte() )
                };
                datos.add( "|" + Joiner.on("|").join(cadena) + "|" );
            }

            return array;
        }
        
        /**
         * Este atributo no esta implementado por no encontrar la equivalencia con el estándar
         * definido en el anexo 20
         * Layout: |NombreImpuesto|impuesto|tasa|importe|
         * @param impuestos
         * @return 
         */
        public ArrayOfString getDatosRetenidosLocales() {
            Comprobante.Impuestos impuestos = comprobanteTron.getImpuestos();
            
            ArrayOfString array = new ArrayOfString();
            
            return array;
        }
        
        /**
         * Este atributo no esta implementado por no encontrar la equivalencia con el estándar
         * definido en el anexo 20
         * Layout: |NombreImpuesto|impuesto|tasa|importe|
         * @param impuestos
         * @return 
         */
        public ArrayOfString getDatosTrasladosLocales() {
            Comprobante.Impuestos impuestos = comprobanteTron.getImpuestos();
            
            ArrayOfString array = new ArrayOfString();
            
            return array;
        }
    }
