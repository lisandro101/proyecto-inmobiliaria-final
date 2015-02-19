/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.EstadoAnalisisCrediticio;
import expertos.EstadoAnalisisCredSessionBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Sebastian
 */
@ManagedBean
@RequestScoped
public class EstadoAnalisisCrediticioManagedBean {

    @EJB
    private EstadoAnalisisCredSessionBean estadoAnalisisCrediticioSessionBean;

    public List<EstadoAnalisisCrediticio> getEstadosPosibles() {
        return getEstadoAnalisisCrediticioSessionBean().findAll();
    }

    public EstadoAnalisisCredSessionBean getEstadoAnalisisCrediticioSessionBean() {
        return estadoAnalisisCrediticioSessionBean;
    }

    public void setEstadoAnalisisCrediticioSessionBean(EstadoAnalisisCredSessionBean estadoAnalisisCrediticioSessionBean) {
        this.estadoAnalisisCrediticioSessionBean = estadoAnalisisCrediticioSessionBean;
    }
    
    public EstadoAnalisisCrediticio obetenerEstadoAnalisisCrediticio(String idEstado){
        return estadoAnalisisCrediticioSessionBean.find(idEstado);
    }
}
