/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package controladores;

import com.icesoft.faces.component.ext.RowSelectorEvent;
import entidades.Caracteristica;
import entidades.Cliente;
import entidades.DetalleCaracteristica;
import entidades.DetalleServicio;
import entidades.Direccion;
import entidades.EstadoInmueble;
import entidades.HistoricoEstadoInmueble;
import entidades.Inmueble;
import entidades.Servicio;
import entidades.TipoInmueble;
import entidadesAuxiliares.ClienteFila;
import entidadesAuxiliares.DetalleCaracteristicaFila;
import entidadesAuxiliares.DetalleServicioFila;
import entidadesAuxiliares.InmuebleFila;
import expertos.ClienteSessionBean;
import expertos.DireccionSessionBean;
import expertos.InmuebleSessionBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import util.JsfUtil;

/**
 *
 * @author Sebastian Torres, Lisandro Nieto
 */
@ManagedBean(name = "inmuebleMB")
@SessionScoped
public class InmuebleManagedBean implements Serializable {

    private Inmueble inmueble;
    private List<InmuebleFila> inmueblesFilas;
    @EJB
    private InmuebleSessionBean inmuebleSessionBean;
    @EJB
    private ClienteSessionBean clienteSessionBean;
    @EJB
    private DireccionSessionBean direccionSessionBean;
    @ManagedProperty("#{servicioMB}")
    private ServicioManagedBean servicioManagedBean;
    @ManagedProperty("#{caracteristicaMB}")
    private CaracteristicaManagedBean caracteristicaManagedBean;
    private List<DetalleCaracteristicaFila> detallesCaracteristicasFila;
    private List<DetalleServicioFila> detallesServiciosFila;
    @ManagedProperty("#{historicoEstadoInmuebleMB}")
    private HistoricoEstadoInmuebleManagedBean historicoEstadoInmuebleManagedBean;
    @ManagedProperty("#{direccionMB}")
    private DireccionManagedBean direccionMB;
    private Cliente propietarioSelected;
    private List<ClienteFila> propietarios;
//    private EstadoInmueble estadoInmueble;

//    @ManagedProperty("#{tipoInmuebleManagedBean}")
//    private TipoInmuebleManagedBean tipoInmuebleManagedBean;
//    @ManagedProperty("#{estadoInmuebleMB}")
//    private EstadoInmuebleManagedBean estadoInmuebleManagedBean;
    public InmuebleManagedBean() {
        TipoInmueble tipo;
        if (inmueble == null) {
            inmueble = new Inmueble();
            tipo = new TipoInmueble();
            tipo.setIdTipoInmueble("-1");
//            tipo.setIdTipoInmueble("");
            inmueble.setTipoInmueble(tipo);
//            estadoInmueble = new EstadoInmueble();
        }
    }

    public void setDetallesCaracteristicas(List<DetalleCaracteristica> cars) {
        inmueble.setDetallesCaracteristicas(cars);
    }

    public void setTipoInmueble(TipoInmueble tipo) {
        inmueble.setTipoInmueble(tipo);
    }

    public TipoInmueble getTipoInmueble() {
        return inmueble.getTipoInmueble();
    }

    public void actualizarEstadoInmueble(EstadoInmueble estadoNuevo) {
        HistoricoEstadoInmueble hist;
        List<HistoricoEstadoInmueble> historicos;
        if (inmueble.getHistoricosEstadosInmuebles() != null) {
            if (!inmueble.getHistoricosEstadosInmuebles().isEmpty() && !estadoNuevo.getEstado().
                    equals(inmueble.getHistoricosEstadosInmuebles().get(inmueble.getHistoricosEstadosInmuebles().size() - 1).getEstadoInmueble().getEstado())) {

                hist = new HistoricoEstadoInmueble();
                hist.setFechaIncio(new Date());
                hist.setEstadoInmueble(estadoNuevo);
                inmueble.getHistoricosEstadosInmuebles().
                        get(inmueble.getHistoricosEstadosInmuebles().size() - 1).setFechaFin(new Date());

                inmueble.getHistoricosEstadosInmuebles().add(hist);
            }
        } else {
            hist = new HistoricoEstadoInmueble();
            hist.setEstadoInmueble(estadoNuevo);
            hist.setFechaIncio(new Date());
            historicos = new ArrayList<HistoricoEstadoInmueble>();
            historicos.add(hist);
            inmueble.setHistoricosEstadosInmuebles(historicos);
        }

    }

    public Inmueble getSelectedInmueble() {
        if (inmueble == null) {
            inmueble = new Inmueble();
        }
        if (inmueble.getFechaAlta() == null) {
            inmueble.setFechaAlta(new Date());
        }
        return inmueble;
    }

    public void setSelectedInmueble(Inmueble inmuebleN) {
        this.inmueble = inmuebleN;
        if (inmuebleN != null) {
            propietarios = convertirClientesAFilas(inmuebleN.getPropietarios());
        }
    }

    private InmuebleSessionBean getInmuebleSessionBean() {
        return inmuebleSessionBean;
    }

    public void create() {
        EstadoInmuebleManagedBean estadoMB = (EstadoInmuebleManagedBean) JsfUtil.obtenerManejador("estadoInmuebleMB");
//        TipoInmuebleManagedBean tipoinmuebleMB = (TipoInmuebleManagedBean) JsfUtil.obtenerManejador("tipoInmuebleManagedBean");
        try {

            setDireccionMB((DireccionManagedBean) JsfUtil.obtenerManejador("direccionMB"));
            inmueble.setDireccion(getDireccionMB().getDireccion());

            inmueble.setFechaAlta(new Date());
            inmueble.setDetallesCaracteristicas(obtenerDetallesCaracteristicas());
            inmueble.setDetallesServicios(obtenerDetallesServicios());
//            actualizarEstadoInmueble(estadoMB.getEstadoInmueble());
//            inmueble.setTipoInmueble(tipoinmuebleMB.getTipoInmueble());
            inmueble.setHistoricosEstadosInmuebles(crearHistoricoEstadoInmueble(estadoMB.getEstadoInmueble()));

            inmueble.setPropietarios(convertirFilasAPropietarios(propietarios));

            inmuebleSessionBean.save(inmueble);
            limpiarInmueble();

            JsfUtil.addSuccessMessage("Inmueble creado");

        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Ocurrio un error en la persistencia al guardar Inmueble");
            System.out.println("--> error: " + e.toString());

        }
    }

    public void limpiarInmueble() {
        inmueble = null;
        TipoInmueble tipo;
        getDireccionMB().limpiarDireccion();
        ((EstadoInmuebleManagedBean) JsfUtil.obtenerManejador("estadoInmuebleMB")).limpiarPantalla();

        setDetallesCaracteristicasFila(null);
        setDetallesServiciosFila(null);
        setSelectedInmueble(null);

        if (inmueble == null) {
            inmueble = new Inmueble();
            tipo = new TipoInmueble();
            tipo.setIdTipoInmueble("-1");
//            tipo.setIdTipoInmueble("");
            inmueble.setTipoInmueble(tipo);
//            estadoInmueble = new EstadoInmueble();
        }
        propietarioSelected = null;
        propietarios = null;
        inmueblesFilas = null;
    }

    public String update() {
        try {
            getInmuebleSessionBean().save(inmueble);
            JsfUtil.addSuccessMessage("Inmueble Actualizado");
            return "Ver";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Ocurrio un error en la persistencia");
            return null;
        }
    }

    public String update(Inmueble inmu) {
        try {
            getInmuebleSessionBean().save(inmu);
            JsfUtil.addSuccessMessage("Inmueble Actualizado");
            return "Ver";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Ocurrio un error en la persistencia");
            return null;
        }
    }

    public void destroy() {
        try {

            inmuebleSessionBean.remove(inmueble);

            JsfUtil.addSuccessMessage("Inmueble eliminado");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Ocurrio un error en la persistencia");
        }
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(inmuebleSessionBean.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(inmuebleSessionBean.findAll(), true);
    }

    public ServicioManagedBean getServicioManagedBean() {
        if (servicioManagedBean == null) {
            servicioManagedBean = new ServicioManagedBean();
        }
        return servicioManagedBean;
    }

    public void setServicioManagedBean(ServicioManagedBean servicioManagedBean) {
        this.servicioManagedBean = servicioManagedBean;
    }

    public String servicioAInmueble() {
        ServicioManagedBean servicioMB = (ServicioManagedBean) JsfUtil.obtenerManejador("servicioMB");
        actualizarDetallesServicios(servicioMB.getServiciosSeleccionados());
        return "inmueble";
    }

    public String caracteristicaAInmueble() {
        CaracteristicaManagedBean caracteristicaMB = (CaracteristicaManagedBean) JsfUtil.obtenerManejador("caracteristicaMB");
        actualizarDetallesCaracteristicas(caracteristicaMB.getCaracteristicasSeleccionadas());
        return "inmueble";
    }

    // ----------nuevo detalles caracteristicas
    private void actualizarDetallesCaracteristicas(List<Caracteristica> caracteristicasNuevas) {
        for (Caracteristica caracteristica : caracteristicasNuevas) {
            if (!obtenerCaracteristicas(detallesCaracteristicasFila).contains(caracteristica)) {
                getDetallesCaracteristicasFila().add(crearFilaDetalleCaracteristica(caracteristica));
            }
        }
    }

    private DetalleCaracteristicaFila crearFilaDetalleCaracteristica(Caracteristica caracteristica) {
        DetalleCaracteristicaFila fila = new DetalleCaracteristicaFila();
        DetalleCaracteristica detalle = new DetalleCaracteristica();
        detalle.setCaracteristica(caracteristica);
        detalle.setCantidad(1);
        detalle.setPublicar(true);
        detalle.setPuntuacion(3);
        fila.setDetalleCaracteristica(detalle);
        fila.setSelected(false);

        return fila;
    }

    private List<Caracteristica> obtenerCaracteristicas(List<DetalleCaracteristicaFila> detallesFila) {
        List<Caracteristica> caracteristicas = new ArrayList<Caracteristica>();
        for (DetalleCaracteristicaFila fila : detallesFila) {
            caracteristicas.add(fila.getDetalleCaracteristica().getCaracteristica());
        }
        return caracteristicas;
    }

    public void botonEliminarCaracteristica() {
        int cantFilasEliminadas = 0;
        List<DetalleCaracteristicaFila> filasEliminadas = new ArrayList<DetalleCaracteristicaFila>();

        for (DetalleCaracteristicaFila detalle : getDetallesCaracteristicasFila()) {
            if (detalle.isSelected()) {
                ++cantFilasEliminadas;
                filasEliminadas.add(detalle);
            }
        }
        for (int i = 0; i < cantFilasEliminadas; i++) {
            getDetallesCaracteristicasFila().remove(filasEliminadas.get(i));
        }
    }
    // ----------fin detalle caracteristica

    // ----------nuevo detalles servicios
    private void actualizarDetallesServicios(List<Servicio> serviciosNuevos) {
        for (Servicio servicio : serviciosNuevos) {
            if (!obtenerServicios(detallesServiciosFila).contains(servicio)) {
                getDetallesServiciosFila().add(crearFilaDetalleServicio(servicio));
            }
        }
    }

    private DetalleServicioFila crearFilaDetalleServicio(Servicio servicio) {
        DetalleServicioFila fila = new DetalleServicioFila();
        DetalleServicio detalle = new DetalleServicio();
        detalle.setServicio(servicio);
        detalle.setPublicar(true);
        fila.setDetalleServicio(detalle);
        fila.setSelected(false);
        return fila;
    }

    private List<Servicio> obtenerServicios(List<DetalleServicioFila> detallesFila) {
        List<Servicio> servicios = new ArrayList<Servicio>();
        for (DetalleServicioFila fila : detallesFila) {
            servicios.add(fila.getDetalleServicio().getServicio());
        }
        return servicios;
    }

    public void botonEliminarServicio() {
        int cantFilasEliminadas = 0;
        List<DetalleServicioFila> filasEliminadas = new ArrayList<DetalleServicioFila>();

        for (DetalleServicioFila detalle : getDetallesServiciosFila()) {
            if (detalle.isSelected()) {
                ++cantFilasEliminadas;
                filasEliminadas.add(detalle);
            }
        }
        for (int i = 0; i < cantFilasEliminadas; i++) {
            getDetallesServiciosFila().remove(filasEliminadas.get(i));
        }
    }
    // ----------fin detalle servicio

    public CaracteristicaManagedBean getCaracteristicaManagedBean() {
        return caracteristicaManagedBean;
    }

    public void setCaracteristicaManagedBean(CaracteristicaManagedBean caracteristicaManagedBean) {
        this.caracteristicaManagedBean = caracteristicaManagedBean;
    }

//    public EstadoInmuebleManagedBean getEstadoInmuebleManagedBean() {
//        if (estadoInmuebleManagedBean == null) {
//            estadoInmuebleManagedBean = (EstadoInmuebleManagedBean) JsfUtil.obtenerManejador("estadoInmuebleMB");
//        }
//        return estadoInmuebleManagedBean;
//    }
//
//    public void setEstadoInmuebleManagedBean(EstadoInmuebleManagedBean estadoInmuebleManagedBean) {
//        this.estadoInmuebleManagedBean = estadoInmuebleManagedBean;
//    }
    public HistoricoEstadoInmuebleManagedBean getHistoricoEstadoInmuebleManagedBean() {
        return historicoEstadoInmuebleManagedBean;
    }

    public void setHistoricoEstadoInmuebleManagedBean(HistoricoEstadoInmuebleManagedBean historicoEstadoInmuebleManagedBean) {
        this.historicoEstadoInmuebleManagedBean = historicoEstadoInmuebleManagedBean;
    }

    public List<Inmueble> getInmuebles() {
        return inmuebleSessionBean.findAll();
    }

    public List<InmuebleFila> getInmueblesFilas() {
//        recargarTabla();
        if (inmueblesFilas == null) {
            recargarTabla();
        }
        return inmueblesFilas;
    }

    public void setInmueblesFilas(List<InmuebleFila> inmueblesFilas) {
        this.inmueblesFilas = inmueblesFilas;
    }

    public void rowSelectionListener(RowSelectorEvent event) {
        try {
            if (event.isSelected()) {
                this.setSelectedInmueble(this.inmueblesFilas.get(event.getRow()).getInmueble());
//                inmueble = inmueblesFilas.get(event.getRow()).getInmueble();
            } else {
//                inmueblesFilas.get(event.getRow()).setSelected(false);
//                inmueble = null;
                this.inmueblesFilas.get(event.getRow()).setSelected(false);
                this.setSelectedInmueble(null);
            }
        } catch (Exception e) {
            System.out.println("--> Error rowSelectionListener: " + e.toString());
        }
    }

    public List<DetalleCaracteristicaFila> getDetallesCaracteristicasFila() {
        if (detallesCaracteristicasFila == null) {
            detallesCaracteristicasFila = new ArrayList<DetalleCaracteristicaFila>();
        }
        return detallesCaracteristicasFila;
    }

    public void setDetallesCaracteristicasFila(List<DetalleCaracteristicaFila> detalles) {
        this.detallesCaracteristicasFila = detalles;
    }

    public List<DetalleServicioFila> getDetallesServiciosFila() {
        if (detallesServiciosFila == null) {
            detallesServiciosFila = new ArrayList<DetalleServicioFila>();
        }
        return detallesServiciosFila;
    }

    public void setDetallesServiciosFila(List<DetalleServicioFila> detalles) {
        this.detallesServiciosFila = detalles;
    }

    public DireccionManagedBean getDireccionMB() {
        if (direccionMB == null) {
            direccionMB = (DireccionManagedBean) JsfUtil.obtenerManejador("direccionMB");
        }
        return direccionMB;
    }

    public void setDireccionMB(DireccionManagedBean direccionMB) {
        this.direccionMB = direccionMB;
    }

    public Cliente getPropietarioSelected() {
        return propietarioSelected;
    }

    public void setPropietarioSelected(Cliente propietarioSelected) {
        this.propietarioSelected = propietarioSelected;
    }

    public List<ClienteFila> getPropietarios() {
        if (propietarios == null) {
            propietarios = new ArrayList<ClienteFila>();
        }
        return propietarios;
    }

    public void setPropietarios(List<ClienteFila> propietarios) {
        this.propietarios = propietarios;
    }

//    public TipoInmuebleManagedBean getTipoInmuebleManagedBean() {
////        if(tipoInmuebleManagedBean == null){
////            tipoInmuebleManagedBean = (TipoInmuebleManagedBean) JsfUtil.obtenerManejador("tipoInmuebleManagedBean");
////        }
//        return tipoInmuebleManagedBean;
//    }
//
//    public void setTipoInmuebleManagedBean(TipoInmuebleManagedBean tipoInmuebleManagedBean) {
//        this.tipoInmuebleManagedBean = tipoInmuebleManagedBean;
//    }
    @FacesConverter(forClass = Inmueble.class)
    public static class InmuebleControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            InmuebleManagedBean controller = (InmuebleManagedBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "inmuebleMB");
            return controller.inmuebleSessionBean.find(value);
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Inmueble) {
                Inmueble o = (Inmueble) object;
                return o.getIdInmueble(); //return getStringKey(o.getIdInmueble());          //TODO: metodo original
            } else {
                throw new IllegalArgumentException("El objeto " + object + " es de tipo " + object.getClass().getName() + "; tipo esperado: " + InmuebleManagedBean.class.getName());
            }
        }
    }

    private List<DetalleCaracteristica> obtenerDetallesCaracteristicas() {
        List<DetalleCaracteristica> resultado = new ArrayList<DetalleCaracteristica>();
        for (DetalleCaracteristicaFila detalleCaracteristicaFila : getDetallesCaracteristicasFila()) {
            resultado.add(detalleCaracteristicaFila.getDetalleCaracteristica());
        }
        return resultado;
    }

    private List<DetalleServicio> obtenerDetallesServicios() {
        List<DetalleServicio> resultado = new ArrayList<DetalleServicio>();
        for (DetalleServicioFila detalleServicioFila : getDetallesServiciosFila()) {
            resultado.add(detalleServicioFila.getDetalleServicio());
        }
        return resultado;
    }

    public String botonCrearInmueble() {
        limpiarInmueble();
        return "editarInmueble";
    }

    public String botonEditar() {
        if (isInmuebleSeleccionado()) {
            cargarPantallaInmueble(obtenerInmuebleSeleccionado());
            return "editarInmueble";
        } else {
            return "recursiva";
        }
//        if (inmueble != null) {
//            System.out.println("existe un inmueble seleccionado");
//            cargarPantallaInmueble(getSelectedInmueble());
//            return "editarInmueble";
//        } else {
//            return "recursiva";
//        }

    }

    public String botonEliminar() {
        if (isInmuebleSeleccionado()) {
            inmueble = obtenerInmuebleSeleccionado();
            destroy();
            recargarTabla();
        }
        return "recursiva";
    }

    private Inmueble obtenerInmuebleSeleccionado() {
        Inmueble resultado = null;
        for (InmuebleFila inmuebleFila : inmueblesFilas) {
            if (inmuebleFila.isSelected()) {
                resultado = inmuebleFila.getInmueble();
            }
        }
        return resultado;
    }

    public void cargarPantallaInmueble(Inmueble inmuebleNuevo) {
        if (getDireccionMB() == null) {
            setDireccionMB((DireccionManagedBean) JsfUtil.obtenerManejador("direccionMB"));
        }
        this.inmueble = inmuebleNuevo;
        this.setDetallesCaracteristicasFila(convertirDetalleCaracteristicaFila(inmuebleNuevo.getDetallesCaracteristicas()));
        this.setDetallesServiciosFila(convertirDetalleServicioFila(inmuebleNuevo.getDetallesServicios()));
        getDireccionMB().cargarPantallaDireccion(inmuebleNuevo.getDireccion());
        EstadoInmuebleManagedBean estadoMB = (EstadoInmuebleManagedBean) JsfUtil.obtenerManejador("estadoInmuebleMB");
        estadoMB.setEstadoInmueble(inmuebleNuevo.getUltimoEstadoInmueble());
        propietarios = convertirClientesAFilas(inmuebleNuevo.getPropietarios());
    }

    private List<DetalleCaracteristicaFila> convertirDetalleCaracteristicaFila(List<DetalleCaracteristica> detalle) {
        List<DetalleCaracteristicaFila> filas = new ArrayList<DetalleCaracteristicaFila>();
        DetalleCaracteristicaFila detalleFila;
        for (DetalleCaracteristica detalleCaracteristica : detalle) {
            detalleFila = new DetalleCaracteristicaFila();
            detalleFila.setDetalleCaracteristica(detalleCaracteristica);
            detalleFila.setSelected(false);
            filas.add(detalleFila);
        }
        return filas;
    }

    private List<DetalleServicioFila> convertirDetalleServicioFila(List<DetalleServicio> detalle) {
        List<DetalleServicioFila> filas = new ArrayList<DetalleServicioFila>();
        DetalleServicioFila detalleFila;
        for (DetalleServicio detalleServicio : detalle) {
            detalleFila = new DetalleServicioFila();
            detalleFila.setDetalleServicio(detalleServicio);
            detalleFila.setSelected(false);
            filas.add(detalleFila);
        }
        return filas;
    }

    public String botonLimpiar() {
        limpiarInmueble();
        return "/inmueble/inmuebleCrearEditar.xhtml";
    }

    private void recargarTabla() {
        inmueblesFilas = new ArrayList<InmuebleFila>();
        for (Inmueble i : inmuebleSessionBean.findAll()) {
            inmueblesFilas.add(new InmuebleFila(i));
        }
    }

    private boolean isInmuebleSeleccionado() {
        boolean resultado = false;
        if (inmueblesFilas != null) {
            if (!inmueblesFilas.isEmpty()) {
                for (InmuebleFila inmuebleFila : inmueblesFilas) {
                    if (inmuebleFila.isSelected()) {
                        resultado = true;
                    }
                }
            }
        }
        return resultado;
    }

    public List<Inmueble> obtenerInmuebles(String nombreEstado) {
        return inmuebleSessionBean.obtenerInmuebles(nombreEstado);
    }

    private List<HistoricoEstadoInmueble> crearHistoricoEstadoInmueble(EstadoInmueble estadoInmueble) {
        List<HistoricoEstadoInmueble> historicos = new ArrayList<HistoricoEstadoInmueble>();
        HistoricoEstadoInmueble hist = new HistoricoEstadoInmueble();
        hist.setEstadoInmueble(estadoInmueble);
        hist.setFechaIncio(new Date());

        historicos.add(hist);
        return historicos;
    }

    private BuscarClienteManagedBean getBuscarClienteManagedBean() {
        return (BuscarClienteManagedBean) JsfUtil.obtenerManejador("buscarClienteManagedBean");
    }

    public String botonAgregarPropietario() {
        getBuscarClienteManagedBean().limpiarPantalla();
        getBuscarClienteManagedBean().setPagOrigen("/inmueble/inmuebleCrearEditar");
        return "/cliente/BuscarCliente";
    }

    public void botonQuitarPropietario() {
        ClienteFila cli = null;
        for (ClienteFila clienteFila : propietarios) {
            if (clienteFila.getCliente().equals(propietarioSelected)) {
                cli = clienteFila;
            }
        }
        propietarios.remove(cli);
    }

    private List<ClienteFila> convertirClientesAFilas(List<Cliente> clis) {
        List<ClienteFila> filas = new ArrayList<ClienteFila>();
        ClienteFila fi;
        for (Cliente cliente : clis) {
            fi = new ClienteFila(cliente);
            filas.add(fi);
        }
        return filas;
    }

    private List<Cliente> convertirFilasAPropietarios(List<ClienteFila> filas) {
        List<Cliente> clients = null;
        if (filas != null) {
            clients = new ArrayList<Cliente>();
            for (ClienteFila clienteFila : filas) {
                clients.add(clienteFila.getCliente());
            }
        }
        return clients;
    }

    public void agregarPropietario(Cliente prop) {
        if (!convertirFilasAPropietarios(propietarios).contains(prop)) {
            propietarios.add(new ClienteFila(prop));
        }
    }

    public void rowSelectionListenerPropietarios(RowSelectorEvent event) {
        try {
            if (event.isSelected()) {
                propietarioSelected = propietarios.get(event.getRow()).getCliente();
            } else {
                propietarios.get(event.getRow()).setSelected(false);
                propietarioSelected = null;
            }
        } catch (Exception e) {
            System.out.println("--> Error rowSelectionListener: " + e.toString());
        }
    }
}
