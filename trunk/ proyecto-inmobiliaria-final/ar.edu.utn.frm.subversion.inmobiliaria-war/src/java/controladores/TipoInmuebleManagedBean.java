/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package controladores;

import entidades.TipoInmueble;
import expertos.TipoInmuebleSessionBean;
import java.io.Serializable;
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
@ManagedBean(name = "tipoInmuebleManagedBean")
@SessionScoped
public class TipoInmuebleManagedBean implements Serializable {

    private TipoInmueble tipoInmueble;
    @EJB
    private TipoInmuebleSessionBean tipoInmuebleSessionBean;
    private List<TipoInmueble> tiposInmuebles;

    public TipoInmuebleManagedBean() {
    }

    private TipoInmuebleSessionBean getFacade() {
        return tipoInmuebleSessionBean;
    }

    public void create() {
        try {
            getFacade().create(getTipoInmueble());
            JsfUtil.addSuccessMessage("Tipo de Inmueble creado");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Ocurrio un error en la persistencia");
        }
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(tipoInmuebleSessionBean.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(tipoInmuebleSessionBean.findAll(), true);
    }

    public TipoInmueble getTipoInmueble() {
        if (tipoInmueble == null) {
            tipoInmueble = new TipoInmueble();
            tipoInmueble.setNombreTipo(" ");
            tipoInmueble.setIdTipoInmueble("-1");
//            tipoInmueble = tipoInmuebleSessionBean.findAll().get(0);

        }
        return tipoInmueble;
    }

    public void setTipoInmueble(TipoInmueble tipoInmueble) {
        this.tipoInmueble = tipoInmueble;
    }

    @FacesConverter(forClass = TipoInmueble.class)
    public static class TipoInmuebleControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoInmuebleManagedBean controller = (TipoInmuebleManagedBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoInmuebleManagedBean");
            return controller.tipoInmuebleSessionBean.find(value);
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TipoInmueble) {
                TipoInmueble o = (TipoInmueble) object;
                return o.getIdTipoInmueble();
            } else {
                throw new IllegalArgumentException("El objeto " + object + " es de tipo "
                        + object.getClass().getName() + "; tipo esperado: " + TipoInmuebleManagedBean.class.getName());
            }
        }
    }

    public List<TipoInmueble> getTiposInmueble() {
//        cargarEjemplos();
        if (tiposInmuebles == null || tiposInmuebles.isEmpty()) {
            tiposInmuebles = tipoInmuebleSessionBean.findAll();
        }
        return tiposInmuebles;
    }

    public void seleccionoTipoInmueble(ValueChangeEvent event) {
        tipoInmueble = (TipoInmueble) event.getNewValue();
    }
}
