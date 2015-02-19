package controladores;

import com.icesoft.faces.component.ext.RowSelectorEvent;
import entidades.AnalisisCrediticio;
import entidades.Cliente;
import entidades.EstadoAnalisisCrediticio;
import entidades.Trabajo;
import entidadesAuxiliares.TrabajoFila;
import expertos.AnalisisCrediticioSessionBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import util.JsfUtil;

/**
 *
 * @author mariodante
 */
@ManagedBean
@SessionScoped
public class AnalisisCrediticioManagedBean implements Serializable {

    @EJB
    private AnalisisCrediticioSessionBean analisisCrediticioSessionBean;
    private AnalisisCrediticio analisis;
    private List<TrabajoFila> trabajosFila;
    private boolean mostrarPopupCliente = false;
    private boolean dniValido = true;
    private String dniCliente;
    @ManagedProperty("#{cliente}")
    private ClienteManagedBean clienteManagedBean;
    private Cliente cliente;

    public AnalisisCrediticio getAnalisis() {
        if (analisis == null) {
            analisis = new AnalisisCrediticio();
            analisis.setCliente(new Cliente());
            EstadoAnalisisCrediticio estado = ((EstadoAnalisisCrediticioManagedBean) JsfUtil.obtenerManejador("estadoAnalisisCrediticioManagedBean")).obetenerEstadoAnalisisCrediticio("-1");
            analisis.setEstadoAnalisisCrediticio(estado);
        }
        return analisis;
    }

    public void setAnalisis(AnalisisCrediticio analisisCred) {
        this.analisis = analisisCred;
        cliente = analisisCred.getCliente();
        if (analisisCred.getTrabajos() != null) {
            cargarTrabajosATabla(analisisCred.getTrabajos());
        }
    }

    public void save() {
        try {
            analisis.setFechaCreacion(new Date());
            analisis.setCliente(cliente);
            analisis.setTrabajos(convertirFilas(trabajosFila));
            analisisCrediticioSessionBean.save(analisis);
            JsfUtil.addSuccessMessage("Análisis Crediticio guardado");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Ocurrio un error al guardar Análisis Crediticio");
            System.out.println("Error al persistir Analisis Crediticio: " + e.toString());
        }
    }

    public List<TrabajoFila> getTrabajosFila() {
        if (trabajosFila == null) {
            trabajosFila = new ArrayList<TrabajoFila>();
        }
        return trabajosFila;
    }

    public void setTrabajosFila(List<TrabajoFila> trabajosFila) {
        this.trabajosFila = trabajosFila;
    }

    public void rowSelectionListener(RowSelectorEvent event) {
        System.out.println("entro al row selector");
        System.out.println("Empleador de la fila seleccionada: " + trabajosFila.get(event.getRow()).getTrabajo().getEmpleador());
    }


    //--------------  borrar, es para prueba de seleccion de filas -----
    public void cargarTabla() {
        TrabajoManagedBean trabajoMB = (TrabajoManagedBean) JsfUtil.obtenerManejador("trabajoManagedBean");
        List<Trabajo> trabajoss = trabajoMB.obtenerTrabajosEjemplo();
        TrabajoFila traFi;
        for (Trabajo trabajo : trabajoss) {
            traFi = new TrabajoFila(trabajo);
            traFi.setSelected(false);

            getTrabajosFila().add(traFi);
        }
    }

    public Trabajo getTrabajoSeleccionado() {
        Trabajo tra = null;
        for (TrabajoFila trabajoFila : trabajosFila) {
            if (trabajoFila.isSelected()) {
                tra = trabajoFila.getTrabajo();
            }
        }
        return tra;
    }

    public String botonEditarTrabajo() {
        TrabajoManagedBean trabajoMB = (TrabajoManagedBean) JsfUtil.obtenerManejador("trabajoManagedBean");
        trabajoMB.setTrabajo(getTrabajoSeleccionado());
        deseleccionarFilas();
        return "trabajo";
    }

    public String botonAgregarTrabajo() {
        TrabajoManagedBean trabajoMB = (TrabajoManagedBean) JsfUtil.obtenerManejador("trabajoManagedBean");
        trabajoMB.setTrabajo(new Trabajo());
        deseleccionarFilas();
        return "trabajo";
    }

    private void deseleccionarFilas() {
        for (TrabajoFila trabajoFila : trabajosFila) {
            trabajoFila.setSelected(false);
        }
    }

    public boolean isMostrarPopupCliente() {
        return mostrarPopupCliente;
    }

    public void setMostrarPopupCliente(boolean mostrarPopupCliente) {
        this.mostrarPopupCliente = mostrarPopupCliente;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public ClienteManagedBean getClienteManagedBean() {
        if (clienteManagedBean == null) {
            clienteManagedBean = (ClienteManagedBean) JsfUtil.obtenerManejador("cliente");
        }
        return clienteManagedBean;
    }

    public void setClienteManagedBean(ClienteManagedBean clienteManagedBean) {
        this.clienteManagedBean = clienteManagedBean;
    }

    public Cliente getCliente() {
        if (cliente == null) {
            cliente = new Cliente();
        }
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @FacesConverter(forClass = EstadoAnalisisCrediticio.class)
    public static class InmuebleControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EstadoAnalisisCrediticioManagedBean controller = (EstadoAnalisisCrediticioManagedBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "estadoAnalisisCrediticioManagedBean");
            return controller.getEstadoAnalisisCrediticioSessionBean().find(value);
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof EstadoAnalisisCrediticio) {
                EstadoAnalisisCrediticio o = (EstadoAnalisisCrediticio) object;
                return o.getIdEstadoAnalisisCrediticio();
            } else {
                throw new IllegalArgumentException("El objeto " + object + " es de tipo "
                        + object.getClass().getName() + "; tipo esperado: " + EstadoAnalisisCrediticioManagedBean.class.getName());
            }
        }
    }

    public void implementar1() {
    }

    public void implementar2() {
    }

    private void cargarTrabajosATabla(List<Trabajo> trabajos) {
        trabajosFila = new ArrayList<TrabajoFila>();
        TrabajoFila fila;
        for (Trabajo trabajo : trabajos) {
            fila = new TrabajoFila(trabajo);
            trabajosFila.add(fila);
        }
    }

    @PermitAll
    public AnalisisCrediticio obtenerUltimoAnalisisCrediticio(Cliente cliente) {
        return analisisCrediticioSessionBean.obtenerUltimoAnalisisCrediticio(cliente);
    }


    private BuscarClienteManagedBean getBuscarClienteManagedBean() {
        return (BuscarClienteManagedBean) JsfUtil.obtenerManejador("buscarClienteManagedBean");
    }

    public String botonBuscarCliente() {
        getBuscarClienteManagedBean().limpiarPantalla();
        getBuscarClienteManagedBean().setPagOrigen("/analisisCrediticio/Create");
        return "/cliente/BuscarCliente";
    }

    public void closePopupCliente() {
        mostrarPopupCliente = false;
        getAnalisis().setCliente(null);
        dniCliente = null;
    }

    public void openPopupCliente() {
        mostrarPopupCliente = true;
    }

    public void botonAceptarPopupCliente() {
        Cliente cliente = getClienteManagedBean().obtenerCliente(dniCliente);
        if (cliente == null) {
            dniCliente = null;
            dniValido = false;
        } else {
            analisis.setCliente(cliente);
            this.cliente = cliente;
            closePopupCliente();
            dniValido = true;
        }
    }

    private List<Trabajo> convertirFilas(List<TrabajoFila> filas) {
        List<Trabajo> resultado = new ArrayList<Trabajo>();
        Trabajo trabajo;
        for (TrabajoFila trabajoFila : filas) {
            trabajo = trabajoFila.getTrabajo();
            resultado.add(trabajo);
        }
        return resultado;
    }

    public String validarDNIExistente() {
        String resultado;
        if (!dniValido) {
            resultado = "  El DNI ingresado inválido";
        } else {
            resultado = "";
        }
        return resultado;
    }

    public void agregarTrabajoATabla(Trabajo trabajo) {
        trabajosFila.add(new TrabajoFila(trabajo));
    }

    public void limpiarPantalla() {
        analisis = null;
        trabajosFila = null;
        cliente = null; 
//        EstadoAnalisisCrediticio estado = ((EstadoAnalisisCrediticioManagedBean) JsfUtil.obtenerManejador("estadoAnalisisCrediticioManagedBean")).obetenerEstadoAnalisisCrediticio("-1");
//        getAnalisis().setEstadoAnalisisCrediticio(estado);

    }
}
