/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Esquema de BD UnicentaMx Vr 3.04: {@code 
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
 * @author jach
 */
package facturatron.datasource.unicenta

import facturatron.datasource.IDatasourceService
import facturatron.Dominio.Configuracion
import facturatron.datasource.DatasourceException
import facturatron.datasource.RenglonTicket
import facturatron.datasource.Ticket;
import facturatron.datasource.unicenta.TicketUnicenta
import facturatron.datasource.CorteZ
//import facturatron.datasource.Ticket;

import groovy.sql.Sql;
/**
 *
 * @author jach
 */
public class UnicentaDatasourceImpl implements IDatasourceService {
	public static void main(String[] args) {
            UnicentaDatasourceImpl ds = new UnicentaDatasourceImpl();
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
        public CorteZ getCorteZ(Date fecha) throws DatasourceException {
                  
            return corte;
        }
    
        @Override
        public Ticket getTicket(Object idObj) throws DatasourceException {
           TicketUnicenta ticket = new TicketUnicenta();
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
            throw new DatasourceException("No se puede conectar con el origen de datos (Unicenta/OpenBravo)", ex);
        } catch(java.sql.SQLException ex) {
            if(ex.getMessage().contains("Acceso denegado"))
                throw new DatasourceException("Falla de configuración del origen de datos (Unicenta/OpenBravo)", ex);
            else
                throw new DatasourceException("Compruebe la configuración del origen de datos (Unicenta/OpenBravo)", ex);
        }        
        return ticket;        
    }
    
    // Metodo para obtener un rango de tickets
    @Override
        public Ticket getTickets(Object idObj, Object idObjFin) throws DatasourceException {
           TicketUnicenta ticket = new TicketUnicenta();          
        try {
            conectar();
            String id = (Integer) idObj;
            String idFinal = (Integer) idObjFin;
            def ticketResultSet = sql.firstRow( 'SELECT * FROM tickets WHERE ticketid=?' , [ id ]);
            ticket.setId( ticketResultSet.id );           
           /*
            def rows = sql.rows("""
                SELECT SUM(IF(t.rate > 0,tl.units,0)) as units, p.code as code, p.name as name, IF(t.rate >0,SUM(tl.price) /units,0) as price,\n\
                 t.rate, SUM(units * price) / SUM(IF(t.rate > 0,tl.units,0)) as punitario, SUM(units * price) as subtotal FROM ticketlines tl, taxes t, products p, tickets tk 
                WHERE tl.product = p.id AND tl.taxid = t.id AND tk.ticketid >= ? AND tk.ticketid <= ? AND tk.id = tl.ticket ORDER BY tk.ticketid""",
                [id,idFinal]
                    );
           */
            def rows = sql.rows("""
                SELECT tl.units as units, p.code as code, p.name as name, tl.price as price, t.rate FROM ticketlines tl, taxes t, products p, tickets tk
                WHERE tl.product = p.id AND tl.taxid = t.id AND tk.ticketid >= ? AND tk.ticketid <= ? AND tk.id = tl.ticket 
                ORDER BY tk.ticketid""",[id,idFinal]
                );
            rows.each { renglonResultSet -> RenglonTicket renglonTicket  = new RenglonTicket();
                renglonTicket.cantidad       = renglonResultSet.units;
                renglonTicket.codigo         = "No Aplica"
                renglonTicket.descripcion    = "Operación con el público en general";
                renglonTicket.descuento      = new BigDecimal(0d);
                renglonTicket.importe        = new BigDecimal(renglonResultSet.price);
                //println(renglonResultSet.rate);
                renglonTicket.impuestos      = renglonResultSet.rate == 0.16d;
                renglonTicket.precioUnitario = new BigDecimal(renglonResultSet.price);
                //renglonTicket.precioUnitario = new BigDecimal (renglonResultSet.price/renglonResultSet.units);
                renglonTicket.unidad         = "PZA";
                //println renglonTicket;
                ticket.add( renglonTicket );
            }
        } catch(com.mysql.jdbc.exceptions.jdbc4.CommunicationsException ex) {
            throw new DatasourceException("No se puede conectar con el origen de datos (Unicenta/OpenBravo)", ex);
        } catch(java.sql.SQLException ex) {
            if(ex.getMessage().contains("Acceso denegado"))
                throw new DatasourceException("Falla de configuración del origen de datos (Unicenta/OpenBravo)", ex);
            else
                throw new DatasourceException("Compruebe la configuración del origen de datos (Unicenta/OpenBravo)", ex);
        }        
        return ticket;        
    }
}

