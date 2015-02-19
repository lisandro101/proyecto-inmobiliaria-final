package controladores;

import entidades.Departamento;
import entidades.Direccion;
import entidades.Localidad;
import entidades.Provincia;
import expertos.DepartamentoSessionBean;
import expertos.DireccionSessionBean;
import expertos.LocalidadSessionBean;
import expertos.ProvinciaSessionBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author mariodante
 */
@ManagedBean(name = "direccionMB")
@SessionScoped
public class DireccionManagedBean implements Serializable {

    @EJB
    private ProvinciaSessionBean provinciaSessionBean;
    @EJB
    private DepartamentoSessionBean departamentoSessionBean;
    @EJB
    private LocalidadSessionBean localidadSessionBean;
    @EJB
    private DireccionSessionBean direccionSessionBean;
    private Direccion direccion;
    private List<Provincia> provinciasPosibles;
    private List<Departamento> departamentosPosibles;
    private List<Localidad> localidadesPosibles;

    public DireccionManagedBean() {
    }

    public Direccion getDireccion() {
        if (direccion == null) {
            direccion = new Direccion();
        }
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public List<Provincia> getProvinciasPosibles() {
        if (provinciasPosibles == null) {
            provinciasPosibles = new ArrayList<Provincia>();
            provinciasPosibles = provinciaSessionBean.findAll();
        }

        return provinciasPosibles;
    }

    public void setProvinciasPosibles(List<Provincia> provinciasPosibles) {
        this.provinciasPosibles = provinciasPosibles;
    }

    public List<Departamento> getDepartamentosPosibles() {
        if (departamentosPosibles == null) {
            departamentosPosibles = new ArrayList<Departamento>();
            departamentosPosibles.add(departamentoSessionBean.find("-1"));
        }
        return departamentosPosibles;
    }

    public void setDepartamentosPosibles(List<Departamento> departamentosPosibles) {
        this.departamentosPosibles = departamentosPosibles;
    }

    public List<Localidad> getLocalidadesPosibles() {
        if (localidadesPosibles == null) {
            localidadesPosibles = new ArrayList<Localidad>();
            localidadesPosibles.add(localidadSessionBean.find("-1"));
        }
        return localidadesPosibles;
    }

    public void setLocalidadesPosibles(List<Localidad> localidadesPosibles) {
        this.localidadesPosibles = localidadesPosibles;
    }

    public Provincia getProvinciaSelected() {
        if (getDireccion().getProvincia() == null) {
            direccion.setProvincia(provinciaSessionBean.find("-1"));
        }
        return direccion.getProvincia();
    }

    public void setProvinciaSelected(Provincia select) {
        direccion.setProvincia(select);
    }

    public Departamento getDepartamentoSelected() {
        if (direccion.getDepartamento() == null) {
            direccion.setDepartamento(departamentoSessionBean.find("-1"));
        }
        return direccion.getDepartamento();
    }

    public void setDepartamentoSelected(Departamento select) {
        direccion.setDepartamento(select);
    }

    public Localidad getLocalidadSelected() {
        if (direccion.getLocalidad() == null) {
            direccion.setLocalidad(localidadSessionBean.find("-1"));
        }
        return direccion.getLocalidad();
    }

    public void setLocalidadSelected(Localidad select) {
        direccion.setLocalidad(select);
    }

    public void seleccionoProvincia(ValueChangeEvent event) {
        setProvinciaSelected((Provincia) event.getNewValue());

        provinciasPosibles.remove(provinciaSessionBean.find("-1"));
        departamentosPosibles = getProvinciaSelected().getDepartamentos();
        departamentosPosibles.add(0, departamentoSessionBean.find("-1"));

        localidadesPosibles.clear();
        localidadesPosibles.add(localidadSessionBean.find("-1"));
        setLocalidadSelected(localidadSessionBean.find("-1"));
    }

    public void seleccionoDepartamento(ValueChangeEvent event) {
        setDepartamentoSelected((Departamento) event.getNewValue());

        departamentosPosibles.remove(departamentoSessionBean.find("-1"));
        localidadesPosibles = getDepartamentoSelected().getLocalidades();
        localidadesPosibles.add(0, localidadSessionBean.find("-1"));
    }

    public void seleccionoLocalidad(ValueChangeEvent event) {
        localidadesPosibles.remove(localidadSessionBean.find("-1"));
    }

    public DireccionSessionBean getDireccionSessionBean() {
        return direccionSessionBean;
    }

    public void setDireccionSessionBean(DireccionSessionBean direccionSessionBean) {
        this.direccionSessionBean = direccionSessionBean;
    }

    public DepartamentoSessionBean getDepartamentoSessionBean() {
        return departamentoSessionBean;
    }

    public void setDepartamentoSessionBean(DepartamentoSessionBean departamentoSessionBean) {
        this.departamentoSessionBean = departamentoSessionBean;
    }

    @FacesConverter(forClass = Provincia.class)
    public static class ProvinciaControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DireccionManagedBean controller = (DireccionManagedBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "direccionMB");
            return controller.provinciaSessionBean.find(value);
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Provincia) {
                Provincia o = (Provincia) object;
                return o.getIdProvincia();
            } else {
                throw new IllegalArgumentException("El objeto " + object + " es de tipo "
                        + object.getClass().getName() + "; tipo esperado: " + DireccionManagedBean.class.getName());
            }
        }
    }

    @FacesConverter(forClass = Departamento.class)
    public static class DepartamentoControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DireccionManagedBean controller = (DireccionManagedBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "direccionMB");
            return controller.departamentoSessionBean.find(value);
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Departamento) {
                Departamento o = (Departamento) object;
                return o.getIdDepartamento();
            } else {
                throw new IllegalArgumentException("El objeto " + object + " es de tipo "
                        + object.getClass().getName() + "; tipo esperado: " + DireccionManagedBean.class.getName());
            }
        }
    }

    @FacesConverter(forClass = Localidad.class)
    public static class LocalidadControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DireccionManagedBean controller = (DireccionManagedBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "direccionMB");
            return controller.localidadSessionBean.find(value);
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Localidad) {
                Localidad o = (Localidad) object;
                return o.getIdLocalidad();
            } else {
                throw new IllegalArgumentException("El objeto " + object + " es de tipo "
                        + object.getClass().getName() + "; tipo esperado: " + DireccionManagedBean.class.getName());
            }
        }
    }

    public void cargarPantallaDireccion(Direccion direccionNueva) {
        Provincia provinciaSelected;
        Departamento departamentoSelected;
        this.direccion = direccionNueva;
        provinciaSelected = this.provinciaSessionBean.find(direccionNueva.getProvincia().getIdProvincia());
        departamentoSelected = this.departamentoSessionBean.find(direccionNueva.getDepartamento().getIdDepartamento());
        this.departamentosPosibles = provinciaSelected.getDepartamentos();
        this.localidadesPosibles = departamentoSelected.getLocalidades();
    }

    @FacesConverter(forClass = Direccion.class)
    public static class DireccionControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DireccionManagedBean controller = (DireccionManagedBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "direccionMB");
            return controller.getDireccionSessionBean().find(value);
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Direccion) {
                Direccion o = (Direccion) object;
                return o.getIdDireccion();
            } else {
                throw new IllegalArgumentException("El objeto " + object + " es de tipo "
                        + object.getClass().getName() + "; tipo esperado: " + DireccionManagedBean.class.getName());
            }
        }
    }

    public void limpiarDireccion() {
        direccion = new Direccion();
        setProvinciaSelected(null);
        setProvinciasPosibles(null);
        setDepartamentoSelected(null);
        setDepartamentosPosibles(null);
        setLocalidadSelected(null);
        setLocalidadesPosibles(null);
    }
}
