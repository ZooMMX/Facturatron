/*
 * Copyright (C) 2013 Phesus - Octvio Ruiz Castillo
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

package facturatron.datasource.opentpv

import facturatron.Dominio.Configuracion
import facturatron.datasource.CorteZ
import facturatron.datasource.DatasourceException
import facturatron.datasource.IDatasourceService
import facturatron.datasource.opentpv.TicketOpenTPV
import facturatron.datasource.RenglonTicket
import facturatron.datasource.Ticket;
import groovy.sql.Sql;

/**
 * Esquema de BD OpenTPV: {@code 
describe tickets;
+---------------+--------------+------+-----+---------+-------+
| Field         | Type         | Null | Key | Default | Extra |
+---------------+--------------+------+-----+---------+-------+
| ID            | varchar(255) | NO   | PRI | NULL    |       |
| TICKETID      | int(11)      | NO   | MUL | NULL    |       |
| PERSON        | varchar(255) | NO   | MUL | NULL    |       |
| CUSTOMER      | varchar(255) | YES  | MUL | NULL    |       |
| STATUS        | int(11)      | NO   |     | 0       |       |
| DISCOUNTNAME  | varchar(255) | YES  |     | NULL    |       |
| DISCOUNTVALUE | varchar(255) | YES  |     | NULL    |       |
| TARIFFAREA    | varchar(255) | YES  | MUL | NULL    |       |
+---------------+--------------+------+-----+---------+-------+
describe ticketlines;
+------------+--------------+------+-----+---------+-------+
| Field      | Type         | Null | Key | Default | Extra |
+------------+--------------+------+-----+---------+-------+
| TICKET     | varchar(255) | NO   | PRI | NULL    |       |
| LINE       | int(11)      | NO   | PRI | NULL    |       |
| PRODUCT    | varchar(255) | YES  | MUL | NULL    |       |
| NAME       | varchar(255) | YES  |     | NULL    |       |
| ISCOM      | bit(1)       | YES  |     | NULL    |       |
| ISDISCOUNT | bit(1)       | YES  |     | NULL    |       |
| UNITS      | double       | NO   |     | NULL    |       |
| PRICE      | double       | NO   |     | NULL    |       |
| TAXID      | varchar(255) | NO   | MUL | NULL    |       |
| ATTRIBUTES | mediumblob   | YES  |     | NULL    |       |
+------------+--------------+------+-----+---------+-------+
 * }
 * @author octavioruizcastillo
 */
public class OpenTPVDatasourceImpl implements IDatasourceService {
    public static void main(String[] args) {
        OpenTPVDatasourceImpl ds = new OpenTPVDatasourceImpl()
        ds.getTicket(25);
    }
    def db;
    Sql sql;
    public void conectar() {
        Configuracion config = Configuracion.getConfig();
        db = [url:config.getUrlDatasource(), user:config.getUsuarioDatasource(), password:config.getPasswordDatasource(), driver:'com.mysql.jdbc.Driver']
        sql = Sql.newInstance(db.url, db.user, db.password, db.driver)
    }
    
    @Override
    public Ticket getTicket(Object idObj) throws DatasourceException {
        TicketOpenTPV ticket = new TicketOpenTPV();     
        try {
            conectar();
            String id = (String) idObj;
            def ticketResultSet = sql.firstRow( 'SELECT * FROM tickets WHERE ticketid=?' , [ id ]);

            ticket.setId( ticketResultSet.id );

            def rows = sql.rows("""
                    SELECT tl.units as units, p.code as code, p.name as name, tl.price as price, t.rate 
                    FROM ticketlines tl, taxes t, products p 
                    WHERE tl.product = p.id AND tl.taxid = t.id AND tl.ticket = ?""", [ ticket.getId() ]);
            
            rows.each { renglonResultSet ->
                
                RenglonTicket renglonTicket  = new RenglonTicket();
                renglonTicket.cantidad       = renglonResultSet.units;
                renglonTicket.codigo         = renglonResultSet.code;
                renglonTicket.descripcion    = renglonResultSet.name;
                renglonTicket.descuento      = new BigDecimal(0d);
                renglonTicket.importe        = new BigDecimal(renglonResultSet.price);
                //println(renglonResultSet.rate);
                renglonTicket.impuestos      = renglonResultSet.rate == 0.16d;
                renglonTicket.precioUnitario = new BigDecimal(renglonResultSet.price);
                renglonTicket.unidad         = "PZA";

                //println renglonTicket;
                ticket.add( renglonTicket );
            }
        } catch(com.mysql.jdbc.exceptions.jdbc4.CommunicationsException ex) {
            throw new DatasourceException("No se puede conectar con el origen de datos (OpenTPV/OpenBravo)", ex);
        } catch(java.sql.SQLException ex) {
            if(ex.getMessage().contains("Access denied"))
                throw new DatasourceException("Falla de configuración del origen de datos (OpenTPV/OpenBravo)", ex);
            else
                throw new DatasourceException("Compruebe la configuración del origen de datos (OpenTPV/OpenBravo)", ex);
        }
        
        return ticket;        
    }
    
    @Override
    public CorteZ getCorteZ(Date fecha) throws DatasourceException {
        throw new DatasourceException("Operación no implementada para OpenTPV/OpenBravo");
    }
	
    @Override
    public void setTicketFacturado(Object id) { }
}

