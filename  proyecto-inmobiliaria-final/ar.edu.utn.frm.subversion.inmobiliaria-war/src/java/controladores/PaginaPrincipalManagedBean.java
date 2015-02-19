/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Dario
 */
@ManagedBean(name="principal")
@RequestScoped
public class PaginaPrincipalManagedBean {
    private String nombreUsuario;

     public PaginaPrincipalManagedBean() {
         
    }

    public String getNombreUsuario() {
        nombreUsuario = nombreUsuario = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogueado");
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
