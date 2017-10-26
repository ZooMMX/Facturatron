/*
 * Copyright (C) 2014 octavioruizcastillo
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

package facturatron.facturacion.PAC.dummy;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import facturatron.Dominio.Factura;
import facturatron.facturacion.DefaultDistribucionHandler;
import facturatron.facturacion.IDistribucionHandler;
import facturatron.facturacion.PAC.IPACService;
import facturatron.facturacion.PAC.IStatusTimbre;
import facturatron.facturacion.PAC.PACException;
import facturatron.lib.entities.CFDv3Tron;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import mx.bigdata.sat.cfdi.v33.schema.ObjectFactory;
import mx.bigdata.sat.cfdi.v33.schema.TimbreFiscalDigital;

/**
 *
 * @author octavioruizcastillo
 */
public class DummyServiceImpl implements IPACService {

    @Override
    public CFDv3Tron timbrar(CFDv3Tron cfdi) throws PACException {
        
        ObjectFactory of = new ObjectFactory();
        TimbreFiscalDigital timbre = of.createTimbreFiscalDigital();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try { 
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime( dateFormat.parse("2012-01-02T20:20:00") );
            timbre.setFechaTimbrado( DatatypeFactory.newInstance().newXMLGregorianCalendar(gc) );
        } catch (ParseException ex) {
            Logger.getLogger(DummyServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(DummyServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        timbre.setNoCertificadoSAT( "30001000000100000801" );
        timbre.setVersion("1.1");
        timbre.setUUID( "ad662d33-6934-459c-a128-bdf0393e0f44" );
        timbre.setSelloSAT( "j5bSpqM3w0+shGtlmqOwqqy6+d659O78ckfstu5vTSFa+2CVMj6Awfr18x4yMLGBwk6ruYbjBIVURodEII6nJIhTTUtYQV1cbRDG9kvvhaNAakx qaSOnOnOx79nHxqFPRVoqh10CsjocS9PZkSM2jz1uwLgaF0knf1g8pjDkLYwlk=" );
        timbre.setSelloCFD( "tOSe+Ex/wvn33YIGwtfmrJwQ31Crd7II9VcH63TGjHfxk5cfb3q9uSbDUGk9TXvo70ydOpikRVw+9B2Six0mbu3PjoPpO909oAYITrRyomdeUGJ 4vmA2/12L86EJLWpU7vlt4cL8HpkEw7TOFhSdpzb/890+jP+C1adBsHU1VHc=" );

        cfdi.getComprobante().addTimbreFiscalDigital(timbre);
        return cfdi;
    }

    @Override
    public Boolean cancelar(Factura comprobante) throws PACException {
        return true;
    }

    @Override
    public IStatusTimbre getStatusTimbre(Factura comprobante) {
        return new IStatusTimbre() {

            @Override
            public String getMensaje() {
                return "Timbre dummy del Servicio dummy para pruebas";
            }

            @Override
            public IStatusTimbre.EstadoEnSAT getEstadoEnSAT() {
                return IStatusTimbre.EstadoEnSAT.NO_RECIBIDO;
            }
            
        };
    }

    @Override
    public IStatusTimbre getStatusCancelacion(Factura comprobante) {
        return new IStatusTimbre() {

            @Override
            public String getMensaje() {
                return "Timbre dummy del Servicio dummy para pruebas";
            }

            @Override
            public IStatusTimbre.EstadoEnSAT getEstadoEnSAT() {
                return IStatusTimbre.EstadoEnSAT.NO_RECIBIDO;
            }
            
        };
    }

    @Override
    public boolean getRequiereSellado() {
        return false;
    }

    @Override
    public IDistribucionHandler getDistribucionHandler() {
        return new DefaultDistribucionHandler();
    }
    
}
