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

 package facturatron.datasource.omoikane;



import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import facturatron.datasource.DatasourceException;
import facturatron.datasource.IDatasourceService;
import facturatron.datasource.Ticket;
import facturatron.datasource.CorteZ;
import facturatron.omoikane.CorteZDao;
import facturatron.omoikane.Ventas;
import facturatron.omoikane.VentasJpaController;
import facturatron.omoikane.exceptions.TicketFacturadoException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author octavioruizcastillo
 */
public class OmoikaneDatasourceImpl implements IDatasourceService {

    @Override
    public Ticket getTicket(Object id) throws TicketFacturadoException {
        return new TicketOmoikane().getTicket(id);
    }
    
    @Override
    public Ticket getTickets(Object idInicial, Object idFinal) {
        //return new TicketOmoikane().getTickets(idInicial);
        return null;
    }

    @Override
    public CorteZ getCorteZ(Date fecha) throws DatasourceException {
        CorteZDao dao = new CorteZDao();
        CorteZ corte = dao.load(Calendar.getInstance().getTime());
        return corte;
    }

    @Override
    public void setTicketFacturado(Object idTicket, Object idFactura) throws DatasourceException {
        try {
            TicketOmoikane ticket = new TicketOmoikane();
            ticket.load(idTicket);
            ticket.setTicketFacturado((Integer) idFactura);
        } catch (Exception ex) {
            throw new DatasourceException("No se pudo marcar ticket facturado", ex);
        }
    }

    @Override
    public void cancelarFactura(Integer idFactura) throws DatasourceException {
        try {
            VentasJpaController         ventaJpa    = new VentasJpaController();
            List<Ventas> ventas = ventaJpa.findVentasByFactura(idFactura);
            for (Ventas venta : ventas) {
                venta.setFacturada(0);
                ventaJpa.edit(venta);
            }
        } catch (CommunicationsException ex) {
            throw new DatasourceException("No hay conexión con el origen de datos", ex);
        } catch (Exception ex) {
            throw new DatasourceException("Hubo un problema al rehabilitar tickets contenidos en la factura", ex);
        }
    }

    
}
