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

import facturatron.Dominio.Configuracion;
import facturatron.datasource.omoikane.OmoikaneDatasourceImpl;

/**
 *
 * @author octavioruizcastillo
 */
public class DatasourceContext {
    public static enum DATASOURCE { Omoikane, OpenTPV, Artemisa, Unicenta, Ninguno }
    public static IDatasourceService instanceDatasourceInstance() throws DatasourceException {
        Configuracion config = Configuracion.getConfig();
        if(config.getConectorDatasource() == DATASOURCE.Omoikane)
            return new OmoikaneDatasourceImpl();
        else if(config.getConectorDatasource() == DATASOURCE.OpenTPV)
            return new facturatron.datasource.opentpv.OpenTPVDatasourceImpl();
        else if(config.getConectorDatasource() == DATASOURCE.Artemisa)
            return new facturatron.datasource.artemisa.ArtemisaDatasourceImpl();
        else if(config.getConectorDatasource() == DATASOURCE.Unicenta)
            return new facturatron.datasource.unicenta.UnicentaDatasourceImpl();
        throw new DatasourceException("Se estableció un conector de origen de datos inválido");
    }
}
