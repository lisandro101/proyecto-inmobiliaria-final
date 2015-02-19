/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import expertos.UsuarioSessionBean;
import javax.annotation.PostConstruct;
import util.JsfUtil;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Dario
 */
@ManagedBean(name="Login")
@RequestScoped
public class LoginManagedBean {
    
    @ManagedProperty("#{usuario}")
    private UsuarioManagedBean usuarioMB;
    @EJB(beanName="UsuarioSessionBean")
    private UsuarioSessionBean usuarioSessionBeans;
    private String nombreUsuario;
    private String contrasenia;
    
    public LoginManagedBean(){
        
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }
    
    public UsuarioSessionBean getUsuarioSessionBeans() {
        return usuarioSessionBeans;
    }

    public void setUsuarioSessionBeans(UsuarioSessionBean usuarioSessionBeans) {
        this.usuarioSessionBeans = usuarioSessionBeans;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = JsfUtil.encriptarMD5(contrasenia);
    }
    
        public UsuarioManagedBean getUsuarioMB() {
        return usuarioMB;
    }

    public void setUsuarioMB(UsuarioManagedBean usuarioMB) {
        this.usuarioMB = usuarioMB;
    }
    
    public String validarUsuario(){
       String resultado;
       if(getUsuarioSessionBeans().validarUsuario(nombreUsuario.trim(),contrasenia.trim())){
           FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogueado",nombreUsuario);
           if(getUsuarioSessionBeans().buscarPorNombreUsuario(nombreUsuario.trim()).isClaveReseteada())
                this.getUsuarioMB().setVistaPopUp(true);
           resultado = "success";
       }else{
           JsfUtil.addErrorMessage("Usuario o Contraseña incorrecto");
           resultado = "failed";
       }
   
       return resultado;            
    }
    
    @PostConstruct
    private void cargarEjemplos(){
        ((CargadorEjemplosManagedBean)JsfUtil.obtenerManejador("cargadorEjemplosManagedBean")).cargarTodosLosDatos();
    }

}
