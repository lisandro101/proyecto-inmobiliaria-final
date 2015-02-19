/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package controladores;

import entidades.DetalleCaracteristica;
import expertos.DetalleCaracteristicaSessionBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import util.JsfUtil;

/**
 *
 * @author Sebastian
 */
@ManagedBean(name = "detalleCaracteristicaMB")
@SessionScoped
public class DetalleCaracteristicaManagedBean {
    private DetalleCaracteristica detalleCaracteristica;              

    @EJB
    private DetalleCaracteristicaSessionBean detalleCaracteristicaSessionBean;
   
    public DetalleCaracteristicaManagedBean() {
    }

    public void create() {
        try {
            getDetalleCaracteristicaSessionBean().create(getDetalleCaracteristica());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Ocurrio un error en la persistencia");
        }
    }


    public void update() {
        try {
            getDetalleCaracteristicaSessionBean().edit(getDetalleCaracteristica());

        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Ocurrio un error en la persistencia"); //JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));

        }
    }

    private void destroy() {
        try {
            getDetalleCaracteristicaSessionBean().remove(getDetalleCaracteristica());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Ocurrio un error en la persistencia"); 
        }
    }

    public DetalleCaracteristicaSessionBean getDetalleCaracteristicaSessionBean() {
        return detalleCaracteristicaSessionBean;
    }

    public DetalleCaracteristica getDetalleCaracteristica() {
        return detalleCaracteristica;
    }

    public void setDetalleCaracteristica(DetalleCaracteristica detalleCaracteristica) {
        this.detalleCaracteristica = detalleCaracteristica;
    }

}
