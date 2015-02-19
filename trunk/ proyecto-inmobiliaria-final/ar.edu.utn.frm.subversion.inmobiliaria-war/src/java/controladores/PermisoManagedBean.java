/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.icesoft.faces.component.ext.RowSelectorEvent;
import entidades.Accion;
import entidades.Menu;
import entidades.Permiso;
import expertos.MenuSessionBean;
import expertos.PermisoSessionBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import util.JsfUtil;
import util.PaginationHelper;

/**
 *
 * @author Dario
 */
@ManagedBean(name="permiso")
@RequestScoped
public class PermisoManagedBean {
    
    private List<Menu> menues;
    private Permiso permiso;
    private int indice = 0;
    private boolean filaElegida;
    private DataModel itemsMenues = null;
    private PaginationHelper paginacion;
    private DataModel itemsPermisos = null;
    private PaginationHelper paginacionPermisos;
    private Accion accion;
    @EJB
    private PermisoSessionBean ejbPermisoSessionBean;
    @EJB
    private MenuSessionBean ejbMenuSessionBean;
    @ManagedProperty("#{menu}")
    private MenuManagedBean menuMB;
    @ManagedProperty("#{perfil}")
    private PerfilManagedBean perfilMB;
    @ManagedProperty("#{accion}")
    private AccionManagedBean accionMB;
    
    
    public PermisoManagedBean() {       
    }

    public List<Menu> getMenues() {
        menues = this.getMenuMB().getEjbSessionMenu().findAll();
        return menues;
    }

    public void setMenues(List<Menu> menues) {
        this.menues = menues;
    }

    public Permiso getPermiso() {
        if(permiso == null)
            permiso = new Permiso();
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }
    
    public MenuSessionBean getEjbMenuSessionBean() {
        return ejbMenuSessionBean;
    }

    public void setEjbMenuSessionBean(MenuSessionBean ejbMenuSessionBean) {
        this.ejbMenuSessionBean = ejbMenuSessionBean;
    }

    public PermisoSessionBean getEjbPermisoSessionBean() {
        return ejbPermisoSessionBean;
    }

    public void setEjbPermisoSessionBean(PermisoSessionBean ejbPermisoSessionBean) {
        this.ejbPermisoSessionBean = ejbPermisoSessionBean;
    }

    public MenuManagedBean getMenuMB() {
        return menuMB;
    }

    public void setMenuMB(MenuManagedBean menuMB) {
        this.menuMB = menuMB;
    }

    public PerfilManagedBean getperfilMB() {
        return perfilMB;
    }

    public void setperfilMB(PerfilManagedBean perfilMB) {
        this.perfilMB = perfilMB;
    }
    
    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }
    
    public AccionManagedBean getAccionMB() {
        return accionMB;
    }

    public void setAccionMB(AccionManagedBean accionMB) {
        this.accionMB = accionMB;
    }
    
    public Accion getAccion() {
        if(accion == null)
            accion = new Accion();
        
        return accion;
    }

    public void setAccion(Accion accion) {
        this.accion = accion;
    }

    public void agregarPermiso(ValueChangeEvent evento){
        if(evento.getNewValue() != null){
            this.getPermiso().setIdPermiso(UUID.randomUUID().toString());
            this.getPermiso().setMenu(menues.get(Integer.parseInt(evento.getNewValue().toString()) - 1));
            this.getAccion().setId(UUID.randomUUID().toString());
            this.getPermiso().setAccion(this.getAccion());
            this.getPermiso().setPerfil(this.getperfilMB().getPerfil());
            this.perfilMB.getPerfil().getPermiso().add(permiso);
            this.setIndice(Integer.parseInt(evento.getNewValue().toString())- 1);
        }
    }
    
    public void agregarVer(ValueChangeEvent evento){
        if(evento.getNewValue()!= null){
            this.getperfilMB().getPerfil().getPermiso()
                    .get(this.getIndice()).getAccion()
                    .setVer(Boolean.parseBoolean(evento.getNewValue().toString()));
        }
    }
    
    public void agregarCrear(ValueChangeEvent evento){
        if(evento.getNewValue()!= null){
            this.getperfilMB().getPerfil().getPermiso()
                    .get(this.getIndice()).getAccion()
                    .setCrear(Boolean.parseBoolean(evento.getNewValue().toString()));
        }
    }
    
    public void agregarModificar(ValueChangeEvent evento){
        if(evento.getNewValue()!= null){
            this.getperfilMB().getPerfil().getPermiso()
                    .get(this.getIndice()).getAccion()
                    .setModificar(Boolean.parseBoolean(evento.getNewValue().toString()));
        }
    }
    
    public void agregarEliminar(ValueChangeEvent evento){
        if(evento.getNewValue()!= null){
            this.getperfilMB().getPerfil().getPermiso()
                    .get(this.getIndice()).getAccion()
                    .setEliminar(Boolean.parseBoolean(evento.getNewValue().toString()));
        }
    }

    public DataModel getItemsMenues() {
        if (itemsMenues == null) {
            itemsMenues = getPaginacion().createPageDataModel();
        }      
        
        return itemsMenues;
    }

    public void setItemsMenues(DataModel itemsMenues) {
        this.itemsMenues = itemsMenues;
    }
  
    public PaginationHelper getPaginacion() {
        if (paginacion == null) {
            paginacion = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getEjbMenuSessionBean().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    ListDataModel resultado = null;
                    Object idPerfil = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idPerfil");
                    if( idPerfil != null){
                        resultado = new ListDataModel(getEjbMenuSessionBean().buscarPorPerfil(idPerfil.toString()));
                    }else{
                        resultado = new ListDataModel(getEjbMenuSessionBean().findAll());
                    }
                    
                    return resultado;
                }
            };
        }
        
        return paginacion;
    }
    
    public void setPaginacion(PaginationHelper paginacion) {
        this.paginacion = paginacion;
    }
    
    public void volver() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idPerfil",null);
            FacesContext.getCurrentInstance().getExternalContext().redirect("editarPerfil.xhtml");
        } catch (IOException ex) {
            JsfUtil.addErrorMessage("No se puede volver a Editar Perfil");
        }
    }
    
    public List<SelectItem> buscarMenuesFaltantes(){
        int i = 1;
        List<SelectItem> nombresMenues = new ArrayList<SelectItem>();
        nombresMenues.add(new SelectItem(0, "Seleccione..."));
        menues = this.getEjbMenuSessionBean().buscarPorPerfil(this.getperfilMB().getPerfil().getIdPerfil());
        for (Menu menu : menues) {
            nombresMenues.add(new SelectItem(i++,menu.getNombre()));
        }
        
        return nombresMenues;
    }

    public DataModel getItemsPermisos() {
        if (itemsPermisos == null) {
            itemsPermisos = getPaginacionPermisos().createPageDataModel();
        }      
        
        return itemsPermisos;
    }

    public void setItemsPermisos(DataModel itemsPermisos) {
        this.itemsPermisos = itemsPermisos;
    }

    public PaginationHelper getPaginacionPermisos() {
        if (paginacionPermisos == null) {
            paginacionPermisos = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getEjbPermisoSessionBean().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    ListDataModel resultado = null;
                    Object idPerfil = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idPerfil");
                    if( idPerfil != null){
                        resultado = new ListDataModel(getEjbPermisoSessionBean().buscarPorPerfil(idPerfil.toString()));
                    }else{
                        resultado = new ListDataModel(getEjbPermisoSessionBean().findAll());
                    }
                    
                    return resultado;
                }
            };
        }
        
        return paginacionPermisos;
    }

    public void setPaginacionPermisos(PaginationHelper paginacionPermisos) {
        this.paginacionPermisos = paginacionPermisos;
    }

    public boolean isFilaElegida() {
        return filaElegida;
    }

    public void setFilaElegida(boolean filaElegida) {
        this.filaElegida = filaElegida;
    }
    
    public void permisoElegido(RowSelectorEvent evento){
        this.setIndice(evento.getRow());
    }
    
}
