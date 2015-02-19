/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package controladores;

import entidades.DetalleServicio;
import expertos.DetalleServicioSessionBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import util.JsfUtil;

/**
 *
 * @author Sebastian
 */

@ManagedBean(name = "detalleServicioMB")
@SessionScoped
public class DetalleServicioManagedBean {
    private DetalleServicio detalleServicio;              

    @EJB
    private DetalleServicioSessionBean detalleServicioSessionBean;
   
    public DetalleServicioManagedBean() {
    }

    public void create() {
        try {
            getDetalleServicioSessionBean().create(getDetalleServicio());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Ocurrio un error en la persistencia");
        }
    }


    public void update() {
        try {
            getDetalleServicioSessionBean().edit(getDetalleServicio());

        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Ocurrio un error en la persistencia"); //JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));

        }
    }

    private void destroy() {
        try {
            getDetalleServicioSessionBean().remove(getDetalleServicio());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Ocurrio un error en la persistencia"); 
        }
    }

    public DetalleServicioSessionBean getDetalleServicioSessionBean() {
        return detalleServicioSessionBean;
    }

    public DetalleServicio getDetalleServicio() {
        return detalleServicio;
    }

    public void setDetalleServicio(DetalleServicio detalleServicio) {
        this.detalleServicio = detalleServicio;
    }

}
