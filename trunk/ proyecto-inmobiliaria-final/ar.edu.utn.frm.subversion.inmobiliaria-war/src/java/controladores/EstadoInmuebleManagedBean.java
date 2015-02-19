/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package controladores;

import entidades.EstadoInmueble;
import expertos.EstadoInmuebleSessionBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import util.JsfUtil;

/**
 *
 * @author Sebastian
 */
@ManagedBean(name = "estadoInmuebleMB")
@SessionScoped
public class EstadoInmuebleManagedBean implements Serializable {

    private EstadoInmueble estadoInmueble;
    @EJB
    private EstadoInmuebleSessionBean estadoInmuebleSessionBean;
    private List<EstadoInmueble> estadosInmueble;

    public EstadoInmuebleManagedBean() {
    }

    public EstadoInmuebleSessionBean getFacade() {
        return estadoInmuebleSessionBean;
    }

    public void create() {
        try {
            getFacade().create(estadoInmueble);
            JsfUtil.addSuccessMessage("EstadoInmueble creado");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Ocurrio un error en la persistencia");
        }
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(estadoInmuebleSessionBean.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(estadoInmuebleSessionBean.findAll(), true);
    }

    public EstadoInmueble getEstadoInmueble() {
        if (estadoInmueble == null) {
            estadoInmueble = new EstadoInmueble();
            estadoInmueble.setEstado(" ");
            estadoInmueble.setIdEstadoInmueble("-1");
        }
        return estadoInmueble;
    }

    public void setEstadoInmueble(EstadoInmueble estadoInmueble) {
        this.estadoInmueble = estadoInmueble;
    }

    @FacesConverter(forClass = EstadoInmueble.class)
    public static class EstadoInmuebleControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EstadoInmuebleManagedBean controller = (EstadoInmuebleManagedBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "estadoInmuebleMB");
            return controller.estadoInmuebleSessionBean.find(value);
        }

        String getStringKey(java.lang.Integer value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof EstadoInmueble) {
                EstadoInmueble o = (EstadoInmueble) object;
                return o.getIdEstadoInmueble();
            } else {
                throw new IllegalArgumentException("El objeto " + object + " es de tipo "
                        + object.getClass().getName() + "; tipo esperado: " + EstadoInmuebleManagedBean.class.getName());
            }
        }
    }

    public List<EstadoInmueble> getEstadosInmueble() {
//        cargarEjemplos();
        if (estadoInmueble != null) {
            estadosInmueble.clear();
        }
        estadosInmueble = estadoInmuebleSessionBean.findAll();
        return estadosInmueble;
    }

    public List<EstadoInmueble> getEstadosInmuebleAlta() {
        if (estadosInmueble == null) {
            estadosInmueble = new ArrayList<EstadoInmueble>();
        } else {
            estadosInmueble.clear();
        }
        estadosInmueble.add(estadoInmuebleSessionBean.obtenerEstadoInmueble("- seleccionar -"));
        estadosInmueble.add(estadoInmuebleSessionBean.obtenerEstadoInmueble("En venta"));
        estadosInmueble.add(estadoInmuebleSessionBean.obtenerEstadoInmueble("En alquiler"));
        return estadosInmueble;
    }

    public void seleccionoEstadoInmueble(ValueChangeEvent event) {
        estadoInmueble = (EstadoInmueble) event.getNewValue();
    }

    public void limpiarPantalla() {
        estadoInmueble = null;
        estadosInmueble = null;
    }

    public EstadoInmueble obtenerEstadoInmueble(String idEstadoInmueble) {
        return estadoInmuebleSessionBean.find(idEstadoInmueble);
    }
}
