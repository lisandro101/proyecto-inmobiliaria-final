/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.icesoft.faces.component.ext.RowSelectorEvent;
import entidades.Usuario;
import entidadesAuxiliares.UsuarioFila;
import expertos.UsuarioSessionBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ValueChangeEvent;
import util.JsfUtil;

/**
 *
 * @author Dario
 */
@ManagedBean(name="usuario")
@RequestScoped
public class UsuarioManagedBean {

    @EJB
    private UsuarioSessionBean ejbSessionUsuario;
    private Usuario usuario;
    private String contraseña = null;
    private String confirmacionContraseña;
    private String contraseñaAnterior;
    private List<UsuarioFila> usuariosFilas;
    private boolean vistaPopUp = false;
    private boolean activarPop = false;

    public UsuarioManagedBean() {
    }

    public UsuarioSessionBean getEjbSessionUsuario() {
        return ejbSessionUsuario;
    }

    public void setEjbSessionUsuario(UsuarioSessionBean ejbSessionUsuario) {
        this.ejbSessionUsuario = ejbSessionUsuario;
    }

    public Usuario getUsuario() {
        if(usuario == null)
            usuario = new Usuario();

        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Usuario crearUsuario(){
        if(getEjbSessionUsuario().validarNombreUsuario(usuario.getNombreUsuario(), usuario.getNombre())){
            JsfUtil.addErrorMessage("El Usuario ya existe");
        }else{
            try{
                usuario.setIdUsuario(UUID.randomUUID().toString());
                usuario.setContrasenia(JsfUtil.encriptarMD5(usuario.getContrasenia()));
                if(usuario.getContrasenia().equals(this.getConfirmacionContraseña())){
                    usuario.setClaveReseteada(true);
                    getEjbSessionUsuario().create(usuario);
                }else
                    JsfUtil.addErrorMessage("Confirme la contraseña");
            }catch(Exception e){
                JsfUtil.addErrorMessage("Error al Crear el Usuario");
            }
        }
        return this.getUsuario();
    }
      
    public void modificarUsuario(){
        if(this.usuario != null){
            this.usuario.setIdUsuario(obtenerIdUsuario());
            if(getContraseña()!= null){
                if(getContraseña().equals(this.confirmacionContraseña)){
                    this.usuario.setContrasenia(getContraseña());
                    this.usuario.setClaveReseteada(true);
                }else{
                    JsfUtil.addErrorMessage("Confirme la contraseña");
                    return;
                }
            }else{
                this.usuario.setContrasenia((String)FacesContext.getCurrentInstance().
                  getExternalContext().getSessionMap().get("contraseña"));
            }
            this.getEjbSessionUsuario().edit(usuario);
            JsfUtil.addErrorMessage("El usuario se modificó Correctamente");
            this.usuario = null;
            this.setContraseña(null);
        }else{
            JsfUtil.addErrorMessage("No se pudo modificar el Usuario");
        }
    }
    
    public void cambioContraseña(ValueChangeEvent evento){
        if(!(evento.getNewValue().toString().equals(""))){
            setContraseña(JsfUtil.encriptarMD5(evento.getNewValue().toString()));
            this.usuario.setClaveReseteada(true);
        }
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    /**
     * @return the confirmacionContraseña
     */
    public String getConfirmacionContraseña() {
        return confirmacionContraseña;
    }

    /**
     * @param confirmacionContraseña the confirmacionContraseña to set
     */
    public void setConfirmacionContraseña(String confirmacionContraseña) {
        this.confirmacionContraseña = JsfUtil.encriptarMD5(confirmacionContraseña);
    }
    
        public boolean isVistaPopUp() {
        return vistaPopUp;
    }

    public void setVistaPopUp(boolean vistaPopUp) {
        this.vistaPopUp = vistaPopUp;
    }

    public String getContraseñaAnterior() {
        return contraseñaAnterior;
    }

    public void setContraseñaAnterior(String contraseñaAnterior) {
        this.contraseñaAnterior = contraseñaAnterior;
    }

    private String obtenerIdUsuario() {
        String id = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idUsuario"); 
        return id;
    }
    
    public List<UsuarioFila> getUsuariosFilas() {
        if (usuariosFilas == null || usuariosFilas.isEmpty()) {
            usuariosFilas = new ArrayList<UsuarioFila>();
            for (Usuario u : ejbSessionUsuario.findAll()) {
                usuariosFilas.add(new UsuarioFila(u));
            }
        }
        return usuariosFilas;
    }

    public void setUsuariosFilas(List<UsuarioFila> usuarioFilas) {
        this.usuariosFilas = usuarioFilas;
    }

    public void rowSelectionListener(RowSelectorEvent event) {
        try {
            int row = event.getRow();
        } catch (Exception e) {
            System.out.println("--> Error rowSelectionListener: " + e.toString());
        }
    }
    
    public void cambiarContraseña(){
        String nombreUsuario = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogueado").toString();
        usuario = this.getEjbSessionUsuario().buscarPorNombreUsuario(nombreUsuario);
        if(usuario != null){
            if(JsfUtil.encriptarMD5(contraseña).equals(this.getConfirmacionContraseña())){
                usuario.setContrasenia(JsfUtil.encriptarMD5(contraseña));
                usuario.setClaveReseteada(false);
                this.getEjbSessionUsuario().save(usuario);
                JsfUtil.addSuccessMessage("La contraseña se Modificó exitosamente");
                this.setVistaPopUp(false);
            }
        }else{
            JsfUtil.addErrorMessage("No se pudo Cambiar la contraseña");
        }
    }
    
    public void cambiarContraseñaAnterior(){
        String nombreUsuario = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogueado").toString();
        usuario = this.getEjbSessionUsuario().buscarPorNombreUsuario(nombreUsuario);
        if(usuario != null){
            if(usuario.getContrasenia().equals(JsfUtil.encriptarMD5(contraseñaAnterior))){
                if(JsfUtil.encriptarMD5(contraseña).equals(this.getConfirmacionContraseña())){
                    usuario.setContrasenia(JsfUtil.encriptarMD5(contraseña));
                    usuario.setClaveReseteada(false);
                    this.getEjbSessionUsuario().save(usuario);
                    JsfUtil.addSuccessMessage("La contraseña se Modificó exitosamente");
                    this.setVistaPopUp(false);
                }else{
                    JsfUtil.addErrorMessage("No se pudo Cambiar la contraseña");
                }
            }else{
                JsfUtil.addErrorMessage("Verifique contraseña anterior");
            }
        }
    }
    
    public void cerrarPopUp() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("activo", false);
        FacesContext.getCurrentInstance().getExternalContext().redirect("../principal/principal.xhtml");
    }

    public boolean isActivarPop() {
        return Boolean.getBoolean(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("activo").toString());
    }

    public void setActivarPop(boolean activarPop) {
        this.activarPop = activarPop;
    }

    @FacesConverter(forClass = Usuario.class)
    public static class VisitaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioManagedBean controller = (UsuarioManagedBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuario");
            return controller.getEjbSessionUsuario().find(value);
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Usuario) {
                Usuario o = (Usuario) object;
                return getStringKey(o.getIdUsuario());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + VisitaManagedBean.class.getName());
            }
        }
        
        public boolean activarPop(){
            return Boolean.getBoolean(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("activo").toString());
        }
    }
}
