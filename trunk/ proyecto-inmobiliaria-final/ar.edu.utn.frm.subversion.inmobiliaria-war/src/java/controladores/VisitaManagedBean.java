package controladores;

import com.icesoft.faces.component.ext.RowSelectorEvent;
import entidades.Visita;
import entidadesAuxiliares.ClienteFila;
import entidadesAuxiliares.EmpleadoFila;
import entidadesAuxiliares.VisitaFila;
import expertos.EstadoVisitaSessionBean;
import util.JsfUtil;
import expertos.VisitaSessionBean;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "visitaController")
@SessionScoped
public class VisitaManagedBean implements Serializable {

    private Visita visita;
    private List<VisitaFila> visitaFilas;
    private boolean popupVisible = false;
    @ManagedProperty("#{inmuebleMB}")
    private InmuebleManagedBean inmuebleController;
    @ManagedProperty("#{empleadoMB}")
    private EmpleadoManagedBean empleadoController;
    @ManagedProperty("#{cliente}")
    private ClienteManagedBean clienteManagedBean;
    @EJB
    private VisitaSessionBean visitaSessionBean;
    @EJB
    private EstadoVisitaSessionBean estadoVisitaSessionBean;

    public VisitaManagedBean() {
    }

    public Visita getSelected() {
        if (visita == null) {
            visita = new Visita();
        }
        return visita;
    }

    public void setSelected(Visita visita) {
        this.visita = visita;
    }

    private VisitaSessionBean getSessionBean() {
        return visitaSessionBean;
    }

    public String prepareList() {
        return "List";
    }

    public String buscarInmueble() {
        return "BuscarInmueble";
    }

    public String buscarEmpleado() {
        return "BuscarEmpleado";
    }

    public String buscarCliente() {
        return "BuscarCliente";
    }

    public String aceptarInmueble() {
        if(this.inmuebleController.getSelectedInmueble() != null){
            this.visita.setInmueble(this.inmuebleController.getSelectedInmueble());
        }
        
        return "Create";
    }

    public String aceptarEmpleado() {
        for (EmpleadoFila item : this.getEmpleadoController().getEmpleadoFilas()) {
            if (item.isSelected()) {
                this.visita.setEmpleado(item.getEmpleado());
                item.setSelected(false);
            }
        }
        return "Create";
    }

    public String aceptarCliente() {
        for (ClienteFila item : this.getClienteManagedBean().getClientesFilas()) {
            if (item.isSelected()) {
                this.visita.setCliente(item.getCliente());
                item.setSelected(false);
            }
        }
        return "Create";
    }

    public String prepareView() {
        return "View";
    }

    public String prepareCreate() {
        visita = new Visita();
        return "Create";
    }

    public String create() {
        try {
            visita.setEstadoVisita(estadoVisitaSessionBean.obtenerEstadoVisita("Pendiente"));
            getSessionBean().save(visita);
            recargarTabla();
            JsfUtil.addSuccessMessage("Visita creada exitosamente");
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Error de persistencia");
            return null;
        }
    }

    public String prepareEdit() {
        return "Create";
    }

    public String destroy() {
        performDestroy();
        recargarTabla();
        return "List";
    }

    private void performDestroy() {
        try {
            getSessionBean().remove(visita);
            JsfUtil.addSuccessMessage("Visita Eliminada");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Error de persistencia");
        }
    }

    private void recargarTabla() {
        visitaFilas = new ArrayList<VisitaFila>();
        for (Visita v : visitaSessionBean.findAll()) {
            visitaFilas.add(new VisitaFila(v));
        }
    }

    public boolean isPopupVisible() {
        return popupVisible;
    }

    public void setPopupVisible(boolean popupVisible) {
        this.popupVisible = popupVisible;
    }

    public void closePopup() {
        popupVisible = false;
    }

    public void openPopup() {
        popupVisible = true;
    }

    public List<VisitaFila> getItems() {
        if (visitaFilas == null || visitaFilas.isEmpty()) {
            recargarTabla();
        }
        return visitaFilas;
    }

    public void setItems(List<VisitaFila> visitaFilas) {
        this.visitaFilas = visitaFilas;
    }

    public void rowSelectionListener(RowSelectorEvent event) {
        try {
            if (event.isSelected()) {
                this.setSelected(this.visitaFilas.get(event.getRow()).getVisita());
            } else {
                this.visitaFilas.get(event.getRow()).setSelected(false);
                this.setSelected(null);
            }
        } catch (Exception e) {
            System.out.println("--> Error rowSelectionListener: " + e.toString());
        }
    }

    /**
     * @return the inmuebleController
     */
    public InmuebleManagedBean getInmuebleController() {
        return inmuebleController;
    }

    /**
     * @param inmuebleController the inmuebleController to set
     */
    public void setInmuebleController(InmuebleManagedBean inmuebleController) {
        this.inmuebleController = inmuebleController;
    }

    /**
     * @return the empleadoController
     */
    public EmpleadoManagedBean getEmpleadoController() {
        return empleadoController;
    }

    /**
     * @param empleadoController the empleadoController to set
     */
    public void setEmpleadoController(EmpleadoManagedBean empleadoController) {
        this.empleadoController = empleadoController;
    }


    public ClienteManagedBean getClienteManagedBean() {
        return clienteManagedBean;
    }


    public void setClienteManagedBean(ClienteManagedBean clienteMB) {
        this.clienteManagedBean = clienteMB;
    }

    @FacesConverter(forClass = Visita.class)
    public static class VisitaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VisitaManagedBean controller = (VisitaManagedBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "visitaController");
            return controller.visitaSessionBean.find(value);
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Visita) {
                Visita o = (Visita) object;
                return getStringKey(o.getIdVisita());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + VisitaManagedBean.class.getName());
            }
        }
    }
}
