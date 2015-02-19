/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.icesoft.faces.component.ext.RowSelectorEvent;
import entidades.AnalisisCrediticio;
import entidades.Cliente;
import entidades.ContratoAlquiler;
import entidades.ContratoCompraVenta;
import entidades.Cuota;
import entidades.EstadoInmueble;
import entidades.Inmueble;
import entidades.TipoPago;
import entidades.Visita;
import entidadesAuxiliares.InmuebleFila;
import expertos.AnalisisCrediticioSessionBean;
import expertos.ClienteSessionBean;
import expertos.ContratoAlquilerSessionBean;
import expertos.InmuebleSessionBean;
import expertos.VisitaSessionBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import util.JsfUtil;

/**
 *
 * @author Sebastian
 */
@ManagedBean
@SessionScoped
public class AlquilarInmuebleManagedBean implements Serializable {

    @EJB
    private ContratoAlquilerSessionBean contratoAlquilerSessionBean;
    @EJB
    private InmuebleSessionBean inmuebleSessionBean;
    @EJB
    private VisitaSessionBean visitaSessionBean;
    @EJB
    private AnalisisCrediticioSessionBean analisisCrediticioSessionBean;
    @EJB
    private ClienteSessionBean clienteSessionBean;
    private ContratoAlquiler contratoAlquiler;
    private boolean mostrarPopupCliente = false;
    private boolean mostrarPopupAnalisisCrediticioInvalido = false;
    private boolean mostrarPopupAnalisisCrediticioDesaprobado = false;
    private String dniCliente;
    private Cliente inquilino;
    private AnalisisCrediticio analisisCrediticioInquilino;
    @ManagedProperty("#{analisisCrediticioManagedBean}")
    private AnalisisCrediticioManagedBean analisisCrediticioManagedBean;
    @ManagedProperty("#{cliente}")
    private ClienteManagedBean clienteManagedBean;
    private List<InmuebleFila> inmueblesFilas;
    @ManagedProperty("#{inmuebleMB}")
    private InmuebleManagedBean inmuebleManagedBean;
    private Inmueble inmueble;
    private Cliente garanteSelected;
    private int duracionContrato;
    private int importeMensualidad;
    //hardcode
    private int maxCantidadMesesContratoAlquiler = 12;
    private double comisionMensualidad;

    public ContratoAlquiler getContratoAlquiler() {
        if (contratoAlquiler == null) {

            contratoAlquiler = new ContratoAlquiler();
            contratoAlquiler.setIdContrato(UUID.randomUUID().toString());
            contratoAlquiler.setFechaFirma(new Date());
            importeMensualidad = 0;
            comisionMensualidad = 0;
        }
        return contratoAlquiler;
    }

    public void setContratoAlquiler(ContratoAlquiler contratoAlquiler) {
        this.contratoAlquiler = contratoAlquiler;
    }

    public boolean isMostrarPopupCliente() {
        return mostrarPopupCliente;
    }

    public void setMostrarPopupCliente(boolean mostrarPopup) {
        this.mostrarPopupCliente = mostrarPopup;
    }

    public void closePopupCliente() {
        mostrarPopupCliente = false;
        inquilino = null;
        dniCliente = null;
    }

    public void openPopupCliente() {
        mostrarPopupCliente = true;
    }

    public AnalisisCrediticioManagedBean getAnalisisCrediticioManagedBean() {
        if (analisisCrediticioManagedBean == null) {
            analisisCrediticioManagedBean = (AnalisisCrediticioManagedBean) JsfUtil.obtenerManejador("analisisCrediticioManagedBean");
        }
        return analisisCrediticioManagedBean;
    }

    public void setAnalisisCrediticioManagedBean(AnalisisCrediticioManagedBean analisisCrediticioManagedBean) {
        this.analisisCrediticioManagedBean = analisisCrediticioManagedBean;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public String botonSiguienteIngresarCliente() {
        Cliente cli = getClienteManagedBean().obtenerCliente(dniCliente);
        if (cli == null) {
            openPopupCliente();
            return "recursion";
        } else {
            inquilino = cli;
            return "verDatosCliente";
        }
    }

    public String botonCancelar() {
        limpiarPantalla();
        return "/comercializacion/IngresarCliente";
    }

    @PermitAll
    public String botonSiguienteVerDatosCliente() {
        analisisCrediticioInquilino = getAnalisisCrediticioManagedBean().obtenerUltimoAnalisisCrediticio(inquilino);
        if (analisisCrediticioInquilino == null) {
            openPopupAnalisisCredInvalido();
            return "recursion";
        } else {

            if (!isVigente(analisisCrediticioInquilino)) {
                openPopupAnalisisCredInvalido();
                return "recursion";
            } else if (analisisCrediticioInquilino.getEstadoAnalisisCrediticio().getNombre().trim().equals("Desaprobado")) {
                openPopupAnalisisCredDesaprobado();
                return "recursion";
            } else {
                getContratoAlquiler().setClienteInquilino(inquilino);
                analisisCrediticioManagedBean.setAnalisis(analisisCrediticioInquilino);
                return "VerAnalisisCrediticio";
            }
        }
    }

    private boolean isVigente(AnalisisCrediticio analisisCrediticio) {
        boolean resultado = false;
        if (analisisCrediticio != null) {
            if (JsfUtil.esFechaValida(analisisCrediticio.getFechaCreacion(), 30)) {
//            if (true) {
                //fecha de creacios del analisis crediticio no supera los 30 dias de la fecha actual
                resultado = true;
            }
        }
        return resultado;
    }

    public Cliente getInquilino() {
        if (inquilino == null) {
            inquilino = new Cliente();
        }
        return inquilino;
    }

    public void setInquilino(Cliente inquilino) {
        this.inquilino = inquilino;
    }

    public AnalisisCrediticio getAnalisisCrediticioInquilino() {
        return analisisCrediticioInquilino;
    }

    public void setAnalisisCrediticioInquilino(AnalisisCrediticio analisisCrediticioInquilino) {
        this.analisisCrediticioInquilino = analisisCrediticioInquilino;
    }

    public boolean isMostrarPopupAnalisisCrediticioInvalido() {
        return mostrarPopupAnalisisCrediticioInvalido;
    }

    public void setMostrarPopupAnalisisCrediticioInvalido(boolean mostrarPopupAnalisisCrediticio) {
        this.mostrarPopupAnalisisCrediticioInvalido = mostrarPopupAnalisisCrediticio;
    }

    public void closePopupAnalisisCredInvalido() {
        mostrarPopupAnalisisCrediticioInvalido = false;
        analisisCrediticioInquilino = null;
    }

    public void openPopupAnalisisCredInvalido() {
        mostrarPopupAnalisisCrediticioInvalido = true;
    }

    public ClienteManagedBean getClienteManagedBean() {
        if (clienteManagedBean == null) {
            setClienteManagedBean((ClienteManagedBean) JsfUtil.obtenerManejador("cliente"));
        }
        return clienteManagedBean;
    }

    public void setClienteManagedBean(ClienteManagedBean clienteManagedBean) {
        this.clienteManagedBean = clienteManagedBean;
    }

    private void limpiarPantalla() {
        analisisCrediticioInquilino = null;
        contratoAlquiler = null;
        dniCliente = "";
        inquilino = null;
    }

    public boolean isMostrarPopupAnalisisCrediticioDesaprobado() {
        return mostrarPopupAnalisisCrediticioDesaprobado;
    }

    public void setMostrarPopupAnalisisCrediticioDesaprobado(boolean mostrarPopupAnalisisCrediticioDesaprobado) {
        this.mostrarPopupAnalisisCrediticioDesaprobado = mostrarPopupAnalisisCrediticioDesaprobado;
    }

    public void closePopupAnalisisCredDesaprobado() {
        mostrarPopupAnalisisCrediticioDesaprobado = false;
        analisisCrediticioInquilino = null;
    }

    public void openPopupAnalisisCredDesaprobado() {
        mostrarPopupAnalisisCrediticioDesaprobado = true;
    }

    public String botonSiguienteListarInmueble() {
        if (inmueble != null) {
            getInmuebleManagedBean().setSelectedInmueble(inmueble);
            return "VerDatosInmueble";
        } else {
            return "";
        }
    }

    public List<InmuebleFila> getInmueblesFilas() {
        if (inmueblesFilas == null) {
            inmueblesFilas = new ArrayList<InmuebleFila>();
        }
        return inmueblesFilas;
    }

    public void setInmueblesFilas(List<InmuebleFila> inmueblesFilas) {
        this.inmueblesFilas = inmueblesFilas;
    }

    public void rowSelectionListener(RowSelectorEvent event) {
        try {
            if (event.isSelected()) {
                inmueble = inmueblesFilas.get(event.getRow()).getInmueble();
            } else {
                inmueblesFilas.get(event.getRow()).setSelected(false);
                inmueble = null;
            }
        } catch (Exception e) {
            System.out.println("--> Error rowSelectionListener: " + e.toString());
        }
    }

    @PermitAll
    public String botonSiguienteVerAnalisisCrediticio() {
        List<Visita> visitas = visitaSessionBean.obtenerVisitas(inquilino, "En alquiler");
        List<Inmueble> inmueblesNoVisitados = getInmuebleManagedBean().obtenerInmuebles("En alquiler");
        List<InmuebleFila> resultado = null;
        InmuebleFila fila;

        if (visitas != null) {
            resultado = new ArrayList<InmuebleFila>();
            for (Visita visita : visitas) {
                fila = new InmuebleFila(visita.getInmueble());
                fila.setVisitado(true);
                resultado.add(fila);
            }
        }

        if (inmueblesNoVisitados != null) {
            if (resultado == null) {
                resultado = new ArrayList<InmuebleFila>();
            }
            for (InmuebleFila inmuebleFila : resultado) {
                if (inmueblesNoVisitados.contains(inmuebleFila.getInmueble())) {
                    inmueblesNoVisitados.remove(inmuebleFila.getInmueble());
                }
            }

            for (Inmueble inmu : inmueblesNoVisitados) {
                fila = new InmuebleFila(inmu);
                fila.setVisitado(false);
                resultado.add(fila);
            }
        }


        inmueblesFilas = resultado;
        return "ListarInmuebles";
    }

    public InmuebleManagedBean getInmuebleManagedBean() {
        if (inmuebleManagedBean == null) {
            inmuebleManagedBean = (InmuebleManagedBean) JsfUtil.obtenerManejador("inmuebleMB");
        }
        return inmuebleManagedBean;
    }

    public void setInmuebleManagedBean(InmuebleManagedBean inmuebleManagedBean) {
        this.inmuebleManagedBean = inmuebleManagedBean;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public String botonSiguienteVerDatosInmueble() {
        getContratoAlquiler().setInmueble(getInmuebleManagedBean().getSelectedInmueble());
        getContratoAlquiler().setPropietarios(inmueble.getPropietarios());
        return "ListarGarantes";
    }

    public String botonEditarInmueble() {
        if (inmueble != null) {
            getInmuebleManagedBean().cargarPantallaInmueble(inmueble);
            return "/inmueble/inmuebleCrearEditar";
        } else {
            return "";
        }
    }

    public Cliente getGaranteSelected() {
        if (garanteSelected == null) {
            garanteSelected = new Cliente();
        }
        return garanteSelected;
    }

    public void setGaranteSelected(Cliente garanteSelected) {
        this.garanteSelected = garanteSelected;
    }

    public void setGarantes(List<Cliente> garantes) {
        contratoAlquiler.setGarantes(garantes);
        System.out.println("cantidad de garanes seleccionados: " + contratoAlquiler.getGarantes().size());
    }

//    public void agregarGarante() {
//        // if() guardar al garante si tiene creado el analisis crediticio
//        garanteSelected.setDireccion(getDireccionMB().getDireccion());
//        getClienteManagedBean().save(garanteSelected);
//        contratoAlquiler.getGarantes().add(garanteSelected);
//        garanteSelected = null;
//        analisisCrediticioManagedBean.limpiarPantalla();
//        tieneAnalisisCredicioGarante = false;
//        getDireccionMB().limpiarDireccion();
//    }
    private DireccionManagedBean getDireccionMB() {
        return (DireccionManagedBean) JsfUtil.obtenerManejador("direccionMB");
    }

//    public String botonCrearAnalisisCrediticio() {
//        if (!tieneAnalisisCredicioGarante) {
//            analisisCrediticioManagedBean.limpiarPantalla();
////            analisisCrediticioManagedBean.setAnalisis(new AnalisisCrediticio());
//            analisisCrediticioManagedBean.setCliente(garanteSelected);
//        }
//
//        return "CrearAnalisisCrediticio";
//    }
    public int getDuracionContrato() {
        return duracionContrato;
    }

    public void setDuracionContrato(int duracionContrato) {
        this.duracionContrato = duracionContrato;
    }

    public int getImporteMensualidad() {
        return importeMensualidad;
    }

    public void setImporteMensualidad(int importeMensualidad) {
        this.importeMensualidad = importeMensualidad;
    }

    public String botonAtrasVerDatosCliente() {
        dniCliente = "";
        return "IngresarCliente";
    }

    public String botonAtrasVerAnalisisCrediticio() {
        return "VerDatosCliente";
    }

    public String botonAtrasListarInmuebles() {
        return "VerAnalisisCrediticio";
    }

    public String botonAtrasVerDatosInmueble() {
        return "ListarInmuebles";
    }

    public String botonAtrasIngresarDatosContrato() {
        return "ListarGarantes";
    }

    public String botonAtrasVerDatosContrato() {
        return "IngresarDatosContrato";
    }

    public String botonSiguienteIngresarDatosContrato() {
        getContratoAlquiler().setLugarDeCobro(getDireccionMB().getDireccion());
        actualizarFechaFinContrato();
        if (validosDatosContratoAlquiler()) {
            return "VerDatosContratoAlquiler";
        } else {
            return " ";
        }
    }

    public String botonConfirmarContrato() {
        save();
        return "";
    }

    public void actualizarFechaFinContrato() {
        if (contratoAlquiler.getFechaInicio() != null && duracionContrato > 0) {
            contratoAlquiler.setFechaFinalizacion(JsfUtil.sumarMesesAFecha(contratoAlquiler.getFechaInicio(), duracionContrato));
        }
    }

    private boolean validosDatosContratoAlquiler() {
        //verificar que los datos esten cargados y que la cantidad de 
        return true;
    }

    public void save() {
        try {

            for (Cliente cliente : contratoAlquiler.getGarantes()) {
                if (cliente.getGarantesContratoAlquiler() == null) {
                    cliente.setGarantesContratoAlquiler(new ArrayList<ContratoAlquiler>());
                }
                cliente.getGarantesContratoAlquiler().add(contratoAlquiler);

            }

            for (Cliente cliente : contratoAlquiler.getPropietarios()) {
                if (cliente.getPropietariosContratoAlquiler() == null) {
                    cliente.setPropietariosContratoAlquiler(new ArrayList<ContratoAlquiler>());
                }
                cliente.getPropietariosContratoAlquiler().add(contratoAlquiler);

            }


            TipoPago tipoPago = new TipoPago();
            tipoPago.setIdTipoPago("1");
            tipoPago.setNombreTipoPago("Cuota Fija");
            tipoPago.setVigente(true);
            tipoPago.setNroTipo(1);
            contratoAlquiler.setTipoPago(tipoPago);
            contratoAlquiler.setNroCuotas(contratoAlquiler.getCuotas().size());

            contratoAlquilerSessionBean.save(contratoAlquiler);
            cambiarInmuebleAAlquilado();
            JsfUtil.addSuccessMessage("Contrato de Alquiler guardado");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Ocurrio un error al guardar el Contrato de Alquiler");
            System.out.println("--> error: " + e.toString());
        }
    }

    private void cambiarInmuebleAAlquilado() {
        Inmueble inmu;
        EstadoInmueble estadoNuevo;
        inmu = contratoAlquiler.getInmueble();
        estadoNuevo = ((EstadoInmuebleManagedBean) JsfUtil.obtenerManejador("estadoInmuebleMB")).obtenerEstadoInmueble("4");
        getInmuebleManagedBean().setSelectedInmueble(inmu);
        getInmuebleManagedBean().actualizarEstadoInmueble(estadoNuevo);
        getInmuebleManagedBean().update();
        getInmuebleManagedBean().setSelectedInmueble(null);
    }

    public void limpiarAlquilarInmueble() {
        contratoAlquiler = null;
        inmueble = null;
        inquilino = null;
        inmueblesFilas = null;
        garanteSelected = null;
        dniCliente = null;
        importeMensualidad = 0;
        comisionMensualidad = 0;
    }

    public List<Cuota> generarCuotas() {
        List<Cuota> cuotas;
        Cuota cuota;
        Date fecha = JsfUtil.setearDiaADate(contratoAlquiler.getDiaDeCobro(), contratoAlquiler.getFechaInicio());

//        if (contratoAlquiler.getCuotas() == null) {
        cuotas = new ArrayList<Cuota>();
        System.out.println("duracion del contrato: " + duracionContrato);
        for (int i = 0; i < duracionContrato; i++) {
            cuota = new Cuota();
            cuota.setNroCuota(i + 1);
            cuota.setFechaPago(JsfUtil.sumarMesesAFecha(fecha, i));
            cuota.setImporte(importeMensualidad);

            cuotas.add(cuota);
        }
        contratoAlquiler.setCuotas(cuotas);
//        }


        return contratoAlquiler.getCuotas();
    }

//    public void onImporteChange(ValueChangeEvent event) {
//        if (!event.getNewValue().toString().isEmpty() && event.getNewValue() == null) {
//            comisionMensualidad = Double.parseDouble(event.getNewValue().toString()) * contratoAlquiler.getPorcentajeComision();
//            comisionMensualidad = comisionMensualidad/100;
//        }
//    }
//
//    public void onPorcentajeChange(ValueChangeEvent event) {
//        if (!event.getNewValue().toString().isEmpty() && event.getNewValue() != null) {
//            comisionMensualidad = Double.parseDouble(event.getNewValue().toString()) * Double.parseDouble(importeMensualidad);
//            comisionMensualidad = comisionMensualidad/100;
//        }
//    }
    public double getComisionMensualidad() {
        if (contratoAlquiler.getPorcentajeComision() != 0 && importeMensualidad != 0) {
            comisionMensualidad = importeMensualidad * contratoAlquiler.getPorcentajeComision();
            comisionMensualidad = comisionMensualidad / 100;
        }
        return comisionMensualidad;
    }

    public void setComisionMensualidad(double comisionMensualidad) {
        this.comisionMensualidad = comisionMensualidad;
    }

    /**
    private void realizarReporteContratoAlquiler() {
        String dirBaseReporte = "c://Repositorios//";
        HashMap map = new HashMap();

        map.put("nroContrato", contratoAlquiler.getNroContrato());
        new ReporteManagedBean().crearReporte(map, "ReporteContrato.jasper", dirBaseReporte + "ReporteContrato.pdf");

    }
    **/
}
