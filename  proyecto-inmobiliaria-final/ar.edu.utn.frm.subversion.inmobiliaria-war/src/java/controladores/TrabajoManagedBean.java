/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Trabajo;
import expertos.TrabajoSessionBean;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import util.JsfUtil;

/**
 *
 * @author mariodante
 */
@ManagedBean
@SessionScoped
public class TrabajoManagedBean {

    @EJB
    private TrabajoSessionBean trabajoSessionBean;       
    private Trabajo trabajo;
    
    @ManagedProperty("#{analisisCrediticioManagedBean}")
    private AnalisisCrediticioManagedBean analisisCrediticioManagedBean;

    public TrabajoSessionBean getTrabajoSessionBean() {
        return trabajoSessionBean;
    }

    public void setTrabajoSessionBean(TrabajoSessionBean trabajoSessionBean) {
        this.trabajoSessionBean = trabajoSessionBean;
    }

    public Trabajo getTrabajo() {
        return trabajo == null ? trabajo = new Trabajo() : trabajo;
    }

    public void setTrabajo(Trabajo trabajo) {
        this.trabajo = trabajo;
    }
    
    public void save() {
        try {
            trabajoSessionBean.save(trabajo);
        } catch (Exception e) {
            System.out.println("Error al persistir Trabajo: " + e.toString());
        }
    }   

    public String botonVolverAAnalisisCred(){
        return "analisisCrediticio";
    }
    
    public String botonAgregarTrabajoAAnalisisCred(){
//        trabajo.setFechaActualizacion(new Date());    
        getAnalisisCrediticioManagedBean().agregarTrabajoATabla(trabajo);
        return "analisisCrediticio";
    }
    
    public List<Trabajo> obtenerTrabajosEjemplo() {

        if (trabajoSessionBean.findAll().isEmpty()) {
            trabajo = new Trabajo();
            trabajo.setEmpleador("TAC SRL");
            trabajo.setCuit("32-264644-6");
            trabajo.setPuestoLaboral("Ejecutivo");
            save();

            trabajo = new Trabajo();
            trabajo.setEmpleador("Andesmar");
            trabajo.setCuit("38-111111-6");
            trabajo.setPuestoLaboral("Operrario");
            save();

            trabajo = new Trabajo();
            trabajo.setEmpleador("Coctal");
            trabajo.setCuit("25-888884-6");
            trabajo.setPuestoLaboral("Contable");
            save();

            trabajo = null;
        }
        return trabajoSessionBean.findAll();
    }

    public AnalisisCrediticioManagedBean getAnalisisCrediticioManagedBean() {
        if(analisisCrediticioManagedBean == null){
            analisisCrediticioManagedBean = (AnalisisCrediticioManagedBean) JsfUtil.obtenerManejador("analisisCrediticioManagedBean");
        }
        return analisisCrediticioManagedBean;
    }

    public void setAnalisisCrediticioManagedBean(AnalisisCrediticioManagedBean analisisCrediticioManagedBean) {
        this.analisisCrediticioManagedBean = analisisCrediticioManagedBean;
    }
}
