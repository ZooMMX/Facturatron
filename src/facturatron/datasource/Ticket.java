/*
 * Copyright (C) 2013 Phesus. Octavio Ruiz
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

import java.util.ArrayList;

/**
 *
 * @author octavioruizcastillo
 * @param <K>
 */
public abstract class Ticket<K extends Object> extends ArrayList<RenglonTicket> {
    protected K id;
    public K getId() { return id; }
    public void setId(K id) { this.id = id; }
}
