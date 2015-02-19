/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Menu;
import entidades.Perfil;
import entidades.Permiso;
import expertos.AsignacionSessionBean;
import expertos.MenuSessionBean;
import expertos.PerfilSessionBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import util.JsfUtil;

/**
 *
 * @author Dario
 */ 
@ManagedBean(name="perfil")
@SessionScoped
public class PerfilManagedBean {
    
    private Perfil perfil;
    private List<Perfil> perfiles;
    private int indice;
    private List<Menu> menues;
    private String idPerfil = null;
    @EJB
    private PerfilSessionBean ejbPerfilSession;
    @EJB
    private AsignacionSessionBean ejbAsignacionSessionBean;
    @EJB
    private MenuSessionBean ejbMenuSessionBean;

    /** Creates a new instance of PerfilManagedBean */
    public PerfilManagedBean() {
    }

    public Perfil getPerfil() {
          if(perfil == null){
              perfil = new Perfil();
              listarFunciones();
          }  
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }
    
    public AsignacionSessionBean getEjbAsignacionSessionBean() {
        return ejbAsignacionSessionBean;
    }

    public void setEjbAsignacionSessionBean(AsignacionSessionBean ejbAsignacionSessionBean) {
        this.ejbAsignacionSessionBean = ejbAsignacionSessionBean;
    }

    public PerfilSessionBean getEjbPerfilSession() {
        return ejbPerfilSession;
    }

    public void setEjbPerfilSession(PerfilSessionBean ejbPerfilSession) {
        this.ejbPerfilSession = ejbPerfilSession;
    }
    
     public MenuSessionBean getEjbPermisoSessionBean() {
        return getEjbMenuSessionBean();
    }

    public void setEjbMenuSessionBean(MenuSessionBean ejbMenuSessionBean) {
        this.ejbMenuSessionBean = ejbMenuSessionBean;
    }
    
     public List<Menu> getMenues() {
        return menues;
    }

    public void setMenues(List<Menu> menues) {
        this.setMenues(menues);
    }
    
     public String getIdPerfil() {
        return idPerfil = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idPerfil").toString();
    }

    public void setIdPerfil(String idPerfil) {
        this.idPerfil = idPerfil;
    }
    
    /**
     * Este método define el indice que se va a mostrar en el combo del perfil
     * @return indice que le indica al combo cual es la opción que debe mostrar
     */
    public int getIndice() {
        Object id = FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().get("idPerfil");
        if (id != null){
            indice = Integer.parseInt(id.toString());
            return indice;
        }else
            return 0;
    }

    public void setIndice (int indice) {
        this.indice = indice;
        FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().put("idPerfil",null);
    }
    
     /**
     * Este método busca en la base de datos todos los perfiles existentes para mostrarlos en un combo
     * @return Listado de perfiles a asignar al combo
     */
    public List<SelectItem> buscarNombresPerfiles(){
        List<SelectItem> nombres = new ArrayList<SelectItem>();
        this.setPerfiles(getEjbPerfilSession().findAll());
        nombres.add(new SelectItem(0,"Seleccione..."));
        for (Perfil perfil1 : perfiles) {
            nombres.add(new SelectItem(Integer.parseInt(perfil1.getIdPerfil()), 
                    perfil1.getNombre()));
        }
        return nombres;
    }
    
     /**
     * Este método se ejecuta cuando el usuario administrador elige un perfil 
     * del combo para asignarle al usuario que esta dando de alta.
     * @param evento designa la ejecución del evento de seleccion
     */
    public void asignarPerfil(ValueChangeEvent evento){
        if(evento.getNewValue() != null){
            this.setPerfil(perfiles.get(Integer.parseInt(evento.getNewValue().toString()) - 1));
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idPerfil", perfil.getIdPerfil());
        }
    }

    public void borrarPerfil(){
        String idPerfil = FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().get("perfilAModificar").toString();
        if(this.getEjbAsignacionSessionBean().buscarUsoDePerfil(idPerfil)){
            JsfUtil.addErrorMessage("No se puede borrar el perfil porque esta siendo usado");
        }else{
            this.getEjbPerfilSession().remove( perfiles.get(Integer.parseInt(idPerfil) - 1));
            JsfUtil.addSuccessMessage("El perfil fue borrado exitosamente");
        }
    }

    public void listarFunciones(){
        List<Menu> menues = this.getEjbMenuSessionBean().findAll();
        
        this.getPerfil().setPermiso(new ArrayList<Permiso>(menues.size()));
        for (int i = 0; i < menues.size(); i++) {
            this.getPerfil().getPermiso().add(new Permiso());
            this.getPerfil().getPermiso().get(i).setMenu(menues.get(i));
        }
    }
    
    public void editarPerfil(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                .put("idPerfil", this.perfil.getIdPerfil());
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("editarPerfil.xhtml");
        } catch (IOException ex) {
            JsfUtil.addErrorMessage("No se puede editar el perfil");
        }
    }

    public void buscarPerfil(String idPerfil) {
        this.setPerfil(this.getEjbPerfilSession().buscarPorId(idPerfil));
    }

    public MenuSessionBean getEjbMenuSessionBean() {
        return ejbMenuSessionBean;
    }
    
    public void agregarPermisos(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idPerfil", perfil.getIdPerfil());
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("agregarPermisos.xhtml");
        } catch (IOException ex) {
            JsfUtil.addErrorMessage("No se puede agregar Funciones al Perfil");
        }
    }

    public void grabarPerfil() {
        if(this.perfil != null){
            this.getEjbPerfilSession().save(perfil);
            JsfUtil.addErrorMessage("El Perfil fue modificado");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idPerfil", null);
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("administrarPerfil.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(PerfilManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void agregarVer(ValueChangeEvent evento){
        if(evento.getNewValue()!= null){
            this.getPerfil().getPermiso()
                    .get(0).getAccion()
                    .setVer(Boolean.parseBoolean(evento.getNewValue().toString()));
        }
    }
    
    public void agregarCrear(ValueChangeEvent evento){
        if(evento.getNewValue()!= null){
            this.getPerfil().getPermiso()
                    .get(0).getAccion()
                    .setCrear(Boolean.parseBoolean(evento.getNewValue().toString()));
        }
    }
    
    public void agregarModificar(ValueChangeEvent evento){
        if(evento.getNewValue()!= null){
            this.getPerfil().getPermiso()
                    .get(0).getAccion()
                    .setModificar(Boolean.parseBoolean(evento.getNewValue().toString()));
        }
    }
    
    public void agregarEliminar(ValueChangeEvent evento){
        if(evento.getNewValue()!= null){
            this.getPerfil().getPermiso().get(0).getAccion()
                    .setEliminar(Boolean.parseBoolean(evento.getNewValue().toString()));
        }
    }
       
}
