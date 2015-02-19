/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Asignacion;
import entidades.Perfil;
import entidades.Usuario;
import expertos.AsignacionSessionBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import util.JsfUtil;

/**
 *
 * @author Dario
 */
@ManagedBean(name="asignacion")
@SessionScoped
public class AsignacionManagedBean  implements Serializable{

    @EJB
    private AsignacionSessionBean ejbSessionAsignacion;
    private Asignacion asignacion;
    private List<Asignacion> asignaciones = new ArrayList<Asignacion>();
    private List<SelectItem> nombresUsuarios = new ArrayList<SelectItem>();
    @ManagedProperty("#{usuario}")
    private UsuarioManagedBean usuarioMB;
    @ManagedProperty("#{perfil}")
    private PerfilManagedBean perfilMB;
    private String filtroNombreUsuario;
    private Date fechaDesde;
    private Date fechaHasta;

    public AsignacionManagedBean() {
    }

    public AsignacionSessionBean getEjbSessionAsignacion() {
        return ejbSessionAsignacion;
    }

    public void setEjbSessionAsignacion(AsignacionSessionBean ejbSessionAsignacion) {
        this.ejbSessionAsignacion = ejbSessionAsignacion;
    }

    public Asignacion getAsignacion() {
        if(asignacion == null)
            asignacion = new Asignacion();
        return asignacion;
    }

    /**
     * @param asignación: contiene al usuario junto con el perfil asignado
     */
    public void setAsignacion(Asignacion asignacion) {
        this.asignacion = asignacion;
    }
    
     /**
     * Este método asigna el usuario a guardar para una determinada asignación
     * @return usuario creado
     */
    private Usuario asignarUsuario() {
        return getUsuarioMB().crearUsuario();
    }

    private Perfil asignarPerfil() {
        return getPerfilMB().getPerfil();
    }

    private Date armarFechaHasta() {
        GregorianCalendar calendario = (GregorianCalendar) Calendar.getInstance();
        calendario.set(9999 ,11,31);
        
        return new java.sql.Date(calendario.getTime().getTime());
    }

    private java.util.Date armarFechaDesde() {
        return new java.sql.Date(Calendar.getInstance().getTime().getTime());
    }

    public UsuarioManagedBean getUsuarioMB() {
        return usuarioMB;
    }

    public void setUsuarioMB(UsuarioManagedBean usuarioMB) {
        this.usuarioMB = usuarioMB;
    }

    public PerfilManagedBean getPerfilMB() {
        return perfilMB;
    }

    public void setPerfilMB(PerfilManagedBean perfilMB) {
        this.perfilMB = perfilMB;
    }

    public String getFiltroNombreUsuario() {
           return filtroNombreUsuario;
    }

    public void setFiltroNombreUsuario(String filtroNombreUsuario) {
       this.filtroNombreUsuario = filtroNombreUsuario;
    }
    
    public Usuario getUsuario() {
        return this.getAsignacion().getUsuario();
    }

    public void setUsuario(Usuario usuario) {
        this.asignacion.setUsuario(usuario);
    }
        
    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return this.fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
     }
    
    public List<Asignacion> getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(List<Asignacion> asignaciones) {
        this.asignaciones = asignaciones;
    }
    
    public void buscarPorFiltro(){
        if(filtroNombreUsuario.trim().equals("")){
            JsfUtil.addErrorMessage("Ingrese nombre de Usuario");
        }else{
            asignacion = (Asignacion) this.getEjbSessionAsignacion().buscarPorNombreUsuario
                    (filtroNombreUsuario.trim());
           // this.setAsignaciones(this.getEjbSessionAsignacion().buscarPorNombreUsuario(filtroNombreUsuario.trim()));
            if(asignacion == null){
                JsfUtil.addErrorMessage("El Usuario ingresado no existe");
            }else{
                this.getUsuarioMB().setUsuario(asignacion.getUsuario());
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                        .put("idUsuario",this.getUsuario().getIdUsuario());
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                        .put("contraseña",this.getUsuario().getContrasenia());
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                        .put("idPerfil",this.asignacion.getPerfil().getIdPerfil());
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                        .put("idAsignacion",this.asignacion.getId());
                obtenerFechas();
            }
        }
    }
    
        /**
     * Este método asigna un usuario a un perfil específico durante un tiempo determinado
     */
    public void realizarAsignacion(){
        this.getAsignacion().setFechaDesde(armarFechaDesde());
        this.getAsignacion().setFechaHasta(armarFechaHasta());
        this.getAsignacion().setId(UUID.randomUUID().toString());
        this.getAsignacion().setUsuario(this.asignarUsuario());
        this.getAsignacion().setPerfil(this.asignarPerfil());
        this.getAsignacion().setActivo(true);
        try{
            this.getEjbSessionAsignacion().create(this.getAsignacion());
            JsfUtil.addErrorMessage("El Usuario se creo correctamente");
            getUsuarioMB().setUsuario(null);
            getPerfilMB().setIndice(0);
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Crear el Usuario");
        }
    }
    
    public void modificarUsuario(){
        Perfil perfil = asignarPerfil();
        this.recuperarAsignacion(FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                        .get("idAsignacion"));
               
        if(perfil.getIdPerfil() != null){
            this.getUsuarioMB().modificarUsuario();
            this.cerrarAsignacionAnterior();
            this.realizarAsignacionNueva();
        }else{
            this.getUsuarioMB().modificarUsuario();
        }
        try{
            this.getEjbSessionAsignacion().edit(this.asignacion);            
        }catch(Exception e){
            JsfUtil.addErrorMessage("No se pudo modificar el Perfil del Usuario");
        }

        if(this.asignacion == null)
            JsfUtil.addErrorMessage("No se pudo modificar el usuario, revise el perfil asignado");
        
        inicializar();
    }

   public void eliminarAsignacion(){
       this.buscarPorFiltro();
       if(asignacion!= null){
           this.getEjbSessionAsignacion().remove(asignacion);
           JsfUtil.addErrorMessage("El Usuario se eliminó Correctamente");
       }else{
           JsfUtil.addErrorMessage("No se pudo eliminar Usuario"); 
       }
       inicializar();
   }
   
    private void inicializar() {
        this.asignacion = null;
        this.filtroNombreUsuario = "";
        this.fechaDesde = null;
        this.fechaHasta = null;
        this.getUsuarioMB().setUsuario(null);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                        .put("idUsuario",null);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                        .put("contraseña",null);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                        .put("idPerfil",null);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                        .put("idAsignacion",null);
    }
     
    private void obtenerFechas() {
        fechaDesde = asignacion.getFechaDesde();
        fechaHasta = asignacion.getFechaHasta();
    }

    private void recuperarAsignacion(Object get) {
        this.asignacion = this.getEjbSessionAsignacion().buscarPorId(get.toString());
    }
    public void cambioFechaDesde(ValueChangeEvent evento){
        if(evento.getNewValue() != null)
            this.asignacion.setFechaDesde(new java.sql.Date(Long.getLong(evento.getNewValue().toString())));
    }
    
    public void cambioFechaHasta(ValueChangeEvent evento){
        if(evento.getNewValue() != null)
            this.asignacion.setFechaHasta((java.sql.Date)evento.getNewValue());
    }

    private void cerrarAsignacionAnterior() {
        this.getUsuarioMB().setUsuario(asignacion.getUsuario());
        this.asignacion.setActivo(false);
        this.asignacion.setFechaHasta(armarFechaDesde());
        try{
            this.getEjbSessionAsignacion().edit(this.asignacion);
        }catch(Exception e){
            JsfUtil.addErrorMessage("No se pudo cerrar la antigua Asignación");
        }
        this.asignacion = null;
    }

    private void realizarAsignacionNueva() {
        this.getAsignacion().setId(UUID.randomUUID().toString());
        this.getAsignacion().setActivo(true);
        this.getAsignacion().setFechaDesde(fechaDesde);
        this.getAsignacion().setFechaHasta(fechaHasta);
        this.getAsignacion().setPerfil(this.asignarPerfil());
        this.getAsignacion().setUsuario(this.getUsuarioMB().getUsuario());
                
    }
    
    public void obtenerUsuario(ValueChangeEvent evento){
        if(evento.getNewValue()!= null){
            filtroNombreUsuario = evento.getNewValue().toString();
            int i = 0;
            asignaciones = this.ejbSessionAsignacion.buscarPorCoincidencia(evento.getNewValue().toString());
            if(!asignaciones.isEmpty())
                if(asignaciones.size() == 1){
                    this.asignacion = asignaciones.get(0);
                    cargarUsuario();
                }else{
                    for (Asignacion asignacion1 : asignaciones) {
                        nombresUsuarios.add(new SelectItem(i++,asignacion1.getUsuario().getNombreUsuario()));
                    }     
                }
        }
    }
    
    public void cargarUsuario(){
        this.getUsuarioMB().setUsuario(asignacion.getUsuario());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                  .put("idUsuario",this.getUsuario().getIdUsuario());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                  .put("contraseña",this.getUsuario().getContrasenia());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                  .put("idPerfil",this.asignacion.getPerfil().getIdPerfil());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                  .put("idAsignacion",this.asignacion.getId());
        obtenerFechas();
    }

    public List<SelectItem> getNombresUsuarios() {
        return nombresUsuarios;
    }

    public void setNombresUsuarios(List<SelectItem> nombresUsuarios) {
        this.nombresUsuarios = nombresUsuarios;
    }

    String buscarPorUsuario(String nombreUsuario) {
        Asignacion asignacionEncontrada = getEjbSessionAsignacion().buscarPorNombreUsuario(nombreUsuario);
        return asignacionEncontrada.getPerfil().getIdPerfil();
    }
}