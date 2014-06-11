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

package facturatron.datasource.artemisa

import facturatron.Dominio.Configuracion
import facturatron.datasource.CorteZ
import facturatron.datasource.DatasourceException
import facturatron.datasource.IDatasourceService
import facturatron.datasource.RenglonTicket
import facturatron.datasource.Ticket
import groovy.sql.Sql;

/**
 * Esquema de BD Artemisa: {@code 
describe transaccion;
+----------------------+---------------+------+-----+---------+----------------+
| Field                | Type          | Null | Key | Default | Extra          |
+----------------------+---------------+------+-----+---------+----------------+
| DTYPE                | varchar(31)   | NO   |     | NULL    |                |
| id                   | int(11)       | NO   | PRI | NULL    | auto_increment |
| abono                | decimal(19,2) | YES  |     | NULL    |                |
| cargo                | decimal(19,2) | YES  |     | NULL    |                |
| concepto             | varchar(255)  | YES  |     | NULL    |                |
| fecha                | datetime      | YES  |     | NULL    |                |
| cantidad             | decimal(19,2) | YES  |     | NULL    |                |
| paciente_id          | int(11)       | YES  | MUL | NULL    |                |
| producto_id_articulo | int(11)       | YES  | MUL | NULL    |                |
+----------------------+---------------+------+-----+---------+----------------+
describe articulos;
+---------------+------------------+------+-----+-------------------+-----------------------------+
| Field         | Type             | Null | Key | Default           | Extra                       |
+---------------+------------------+------+-----+-------------------+-----------------------------+
| id_articulo   | int(11)          | NO   | PRI | NULL              | auto_increment              |
| codigo        | varchar(64)      | YES  | UNI | NULL              |                             |
| id_linea      | int(11)          | YES  |     | 0                 |                             |
| descripcion   | varchar(64)      | YES  |     | NULL              |                             |
| unidad        | varchar(8)       | YES  |     | NULL              |                             |
| impuestos     | double           | YES  |     | NULL              |                             |
| uModificacion | timestamp        | NO   |     | CURRENT_TIMESTAMP | on update CURRENT_TIMESTAMP |
| version       | int(11)          | YES  |     | 0                 |                             |
| id_grupo      | int(10) unsigned | NO   |     | 0                 |                             |
| esPaquete     | bit(1)           | YES  |     | NULL              |                             |
+---------------+------------------+------+-----+-------------------+-----------------------------+
describe articulos_Impuesto;
+-----------------------+------------+------+-----+---------+-------+
| Field                 | Type       | Null | Key | Default | Extra |
+-----------------------+------------+------+-----+---------+-------+
| articulos_id_articulo | int(11)    | NO   | PRI | NULL    |       |
| impuestos_id          | bigint(20) | NO   | PRI | NULL    |       |
+-----------------------+------------+------+-----+---------+-------+
describe Impuesto;
+-------------+---------------+------+-----+---------+----------------+
| Field       | Type          | Null | Key | Default | Extra          |
+-------------+---------------+------+-----+---------+----------------+
| id          | bigint(20)    | NO   | PRI | NULL    | auto_increment |
| descripcion | varchar(255)  | YES  |     | NULL    |                |
| porcentaje  | decimal(19,2) | YES  |     | NULL    |                |
+-------------+---------------+------+-----+---------+----------------+
 * }
 * @author octavioruizcastillo
 */
public class ArtemisaDatasourceImpl implements IDatasourceService {
    public static void main(String[] args) {
        ArtemisaDatasourceImpl ds = new ArtemisaDatasourceImpl()
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
        TicketArtemisa ticket = new TicketArtemisa();
        try {
            conectar();
            Long id = Long.valueOf( (String) idObj );
            
            ticket.setId( id );

            def rows = sql.rows("""
                    SELECT t.DTYPE as tipo,  t.cargo as cargo, t.concepto as concepto, t.cantidad as cantidad, 
                        (t.cargo / t.cantidad) as punitario, a.codigo as codigo, a.unidad as unidad, a.id_articulo as idarticulo
                    FROM Transaccion t, articulos a
                    WHERE a.id_articulo = t.producto_id_articulo AND t.paciente_id = ? AND t.DTYPE = 'Cargo'"""
                , [ ticket.getId() ]);
            def mTemp = [:]; //Mapa  temporal            
            
            rows.each { renglonResultSet ->
                
                def iva = sql.firstRow("""SELECT i.descripcion
                                FROM Impuesto i, articulos_Impuesto ai 
                                WHERE ai.articulos_id_articulo = ? AND ai.impuestos_id = i.id 
                                AND i.descripcion LIKE '%IVA%' AND i.porcentaje > 0""", [renglonResultSet.idarticulo]);
                
                if(mTemp[renglonResultSet.idarticulo] != null) {
                    mTemp[renglonResultSet.idarticulo].cantidad += renglonResultSet.cantidad;
                    mTemp[renglonResultSet.idarticulo].importe  += renglonResultSet.cargo;
                } else {
                    RenglonTicket renglonTicket  = new RenglonTicket();
                    renglonTicket.impuestos      = iva == null ? false : true;
                    renglonTicket.cantidad       = renglonResultSet.cantidad;
                    renglonTicket.codigo         = renglonResultSet.codigo;
                    renglonTicket.descripcion    = renglonResultSet.concepto[4..-1];
                    renglonTicket.descuento      = new BigDecimal(0d);
                    renglonTicket.importe        = new BigDecimal(renglonResultSet.cargo);
                    renglonTicket.ieps           = new BigDecimal(0d);
                    //println(renglonResultSet.rate);                    
                    renglonTicket.precioUnitario = new BigDecimal(renglonResultSet.punitario);
                    renglonTicket.unidad         = renglonResultSet.unidad;

                    //println renglonTicket;
                    mTemp[renglonResultSet.idarticulo] = renglonTicket;
                }
            }
            
            mTemp.each { k, renglon ->
                renglon.importe        = renglon.impuestos ? renglon.importe.divide(new BigDecimal("1.16"), 2, BigDecimal.ROUND_HALF_EVEN) : renglon.importe;
                renglon.precioUnitario = renglon.impuestos ? renglon.precioUnitario.divide(new BigDecimal("1.16"), 2, BigDecimal.ROUND_HALF_EVEN) : renglon.precioUnitario;
                ticket.add( renglon );                
            }
            
        } catch(com.mysql.jdbc.exceptions.jdbc4.CommunicationsException ex) {
            throw new DatasourceException("No se puede conectar con el origen de datos (Artemisa)", ex);
        } catch(java.sql.SQLException ex) {
            if(ex.getMessage().contains("Access denied"))
                throw new DatasourceException("Falla de configuración del origen de datos (Artemisa)", ex);
            else
                throw new DatasourceException("Compruebe la configuración del origen de datos (Artemisa)", ex);
        }
        
        return ticket;        
    }
    
    @Override
    public Ticket getTickets(Object idInicial, Object idFinal) {
        return getTicket(idInicial);
    }
    
    @Override
    public CorteZ getCorteZ(Date fecha) throws DatasourceException {
        throw new DatasourceException("Operación no implementada para Artemisa");
    }
    
    @Override
    public void setTicketFacturado(Object id) {
        
    }
	
}

