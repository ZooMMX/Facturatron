/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.Dominio;
import facturatron.MVC.Model;
import java.io.Serializable;

/**
 *
 * @author gnujach
 */
public class Medida extends Model implements Serializable {
    private Integer id = -1;
    private String nombre       = "";
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
