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



import facturatron.datasource.DatasourceException;
import facturatron.datasource.IDatasourceService;
import facturatron.datasource.Ticket;
import facturatron.datasource.CorteZ;
import facturatron.omoikane.CorteZDao;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author octavioruizcastillo
 */
public class OmoikaneDatasourceImpl implements IDatasourceService {

    @Override
    public Ticket getTicket(Object id) {
        return new TicketOmoikane().getTicket(id);
    }
    
    @Override
    public Ticket getTickets(Object idInicial, Object idFinal) {
        return new TicketOmoikane().getTicket(idInicial);
    }

    @Override
    public CorteZ getCorteZ(Date fecha) throws DatasourceException {
        CorteZDao dao = new CorteZDao();
        CorteZ corte = dao.load(Calendar.getInstance().getTime());
        return corte;
    }

    @Override
    public void setTicketFacturado(Object id) throws DatasourceException {
        try {
            TicketOmoikane ticket = new TicketOmoikane();
            ticket.load(id);
            ticket.setTicketFacturado(1);
        } catch (Exception ex) {
            throw new DatasourceException("No se pudo marcar ticket facturado", ex);
        }
    }

    
}
