/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package controladores;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Sebastian
 */

@SessionScoped
@ManagedBean
public class ConfiguracionManagedBean {
    
    public String irAServicios(){
        return "servicio";
    }
    
    public String irACaracteristicas(){
        return "caracteristica";
    }
}
