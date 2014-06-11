/*
 * Copyright (C) 2013 octavioruizcastillo @ Phesus
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

package facturatron.datasource;

import java.util.Date;

/**
 *
 * @author octavioruizcastillo
 */
public interface IDatasourceService  {
    public Ticket getTicket(Object id) throws DatasourceException;
    public Ticket getTickets(Object idInicial, Object idFinal) throws DatasourceException;
    public CorteZ getCorteZ(Date fecha) throws DatasourceException;
    public void setTicketFacturado(Object id) throws DatasourceException;
}
