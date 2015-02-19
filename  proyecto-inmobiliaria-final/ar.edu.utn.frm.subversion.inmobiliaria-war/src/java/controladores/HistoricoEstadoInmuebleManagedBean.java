/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package controladores;

import entidades.HistoricoEstadoInmueble;
import expertos.HistoricoEstadoInmuebleSessionBean;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import util.JsfUtil;

/**
 *
 * @author Sebastian
 */

@ManagedBean(name = "historicoEstadoInmuebleMB")
@SessionScoped
public class HistoricoEstadoInmuebleManagedBean implements Serializable{
    private HistoricoEstadoInmueble historicoEstado;              

    @EJB
    private HistoricoEstadoInmuebleSessionBean historicoEstadoSessionBean;
   
//    @ManagedProperty("#{inmuebleMB}")
//    private InmuebleManagedBean inmuebleManagedBean;
    
    
    public HistoricoEstadoInmuebleManagedBean() {
    }

    public void create() {
        try {
            getHistoricoEstadoSessionBean().create(getHistoricoEstado());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Ocurrio un error en la persistencia");
        }
    }


    public void update() {
        try {
            getHistoricoEstadoSessionBean().edit(getHistoricoEstado());

        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Ocurrio un error en la persistencia"); //JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));

        }
    }

    private void destroy() {
        try {
            getHistoricoEstadoSessionBean().remove(getHistoricoEstado());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Ocurrio un error en la persistencia"); 
        }
    }

    public HistoricoEstadoInmuebleSessionBean getHistoricoEstadoSessionBean() {
        return historicoEstadoSessionBean;
    }

    public HistoricoEstadoInmueble getHistoricoEstado() {
        return historicoEstado;
    }

    public void setHistoricoEstado(HistoricoEstadoInmueble historicoEstado) {
        this.historicoEstado = historicoEstado;
    }

//    public InmuebleManagedBean getInmuebleManagedBean() {
//        return inmuebleManagedBean;
//    }
//
//    public void setInmuebleManagedBean(InmuebleManagedBean inmuebleManagedBean) {
//        this.inmuebleManagedBean = inmuebleManagedBean;
//    }

}
