/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Accion;
import expertos.AccionSessionBean;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author Dario
 */
@ManagedBean(name="accion")
@RequestScoped
public class AccionManagedBean {

    private List<Accion> acciones;
    private Accion accion;
    @EJB
    private AccionSessionBean ejbAccionSessionBean;
    
    public AccionManagedBean() {
    }

    public List<Accion> getAcciones() {
        return acciones;
    }

    public void setAcciones(List<Accion> acciones) {
        this.acciones = acciones;
    }

    public Accion getAccion() {
        return accion;
    }

    public void setAccion(Accion accion) {
        this.accion = accion;
    }

    public AccionSessionBean getEjbAccionSessionBean() {
        return ejbAccionSessionBean;
    }

    public void setEjbAccionSessionBean(AccionSessionBean ejbAccionSessionBean) {
        this.ejbAccionSessionBean = ejbAccionSessionBean;
    }
    
    public List<SelectItem> buscarAcciones(){
        List<SelectItem> nombresAcciones = new ArrayList<SelectItem>();
        acciones = this.getEjbAccionSessionBean().findAll();
        nombresAcciones.add(new SelectItem(0,"Seleccione..."));
        for (Accion accion1 : acciones) {
            /*nombresAcciones.add(new SelectItem(Integer.parseInt(accion1.getId()), 
                    accion1.getNombreAccion()));*/
        }
        return nombresAcciones;
    }
    
}