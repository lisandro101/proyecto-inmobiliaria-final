package controladores;

import com.icesoft.faces.component.ext.RowSelectorEvent;
import entidades.AnalisisCrediticio;
import entidades.Cliente;
import entidades.ContratoCompraVenta;
import entidades.Inmueble;
import entidades.Visita;
import entidadesAuxiliares.InmuebleFila;
import expertos.AnalisisCrediticioSessionBean;
import expertos.ContratoVenderSessionBean;
import expertos.InmuebleSessionBean;
import expertos.VisitaSessionBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import util.JsfUtil;

/**
 *
 * @author Sebastian, Lisandro
 */
@ManagedBean
@SessionScoped
public class VenderInmuebleManagedBean implements Serializable {

    @EJB
    private ContratoVenderSessionBean contratoVenderSessionBean;
    @EJB
    private InmuebleSessionBean inmuebleSessionBean;
    @EJB
    private VisitaSessionBean visitaSessionBean;
    @EJB
    private AnalisisCrediticioSessionBean analisisCrediticioSessionBean;
    private ContratoCompraVenta contratoVender;
    private boolean mostrarPopupCliente = false;
    private boolean mostrarPopupAnalisisCrediticioInvalido = false;
    private boolean mostrarPopupAnalisisCrediticioDesaprobado = false;
    private String dniCliente;
    private AnalisisCrediticio analisisCrediticioInquilino;
    private Inmueble inmueble;
    private List<InmuebleFila> inmueblesFilas;
    @ManagedProperty("#{analisisCrediticioManagedBean}")
    private AnalisisCrediticioManagedBean analisisCrediticioManagedBean;
    @ManagedProperty("#{cliente}")
    private ClienteManagedBean clienteManagedBean;
    @ManagedProperty("#{inmuebleMB}")
    private InmuebleManagedBean inmuebleManagedBean;
    @ManagedProperty("#{direccionMB}")
    private DireccionManagedBean direccionManagedBean;

    public ContratoCompraVenta getContratoVender() {
        if (contratoVender == null) {
            contratoVender = new ContratoCompraVenta();
            contratoVender.setFechaFirma(new Date());
        }
        return contratoVender;
    }

    public void setContratoVender(ContratoCompraVenta contratoVender) {
        this.contratoVender = contratoVender;
    }

    public boolean isMostrarPopupCliente() {
        return mostrarPopupCliente;
    }

    public void setMostrarPopupCliente(boolean mostrarPopup) {
        this.mostrarPopupCliente = mostrarPopup;
    }

    public void closePopupCliente() {
        mostrarPopupCliente = false;
        dniCliente = null;
    }

    public void openPopupCliente() {
        mostrarPopupCliente = true;
    }

    public double getImporte() {
        double result = 0;
        double importe = this.contratoVender.getImporte();
        if (importe != 0) {
            result = importe + this.getComisionComprador();
        }
        return result;
    }

    public double getComisionVendedor() {
        return this.contratoVender.getImporte() * this.getPorcentajeComisionVendedor() / 100;
    }

    public double getComisionComprador() {
        return this.contratoVender.getImporte() * this.getPorcentajeComisionVendedor() / 100;
    }

    public double getMultaDiaria() {
        return this.contratoVender.getImporte() * 2 / 1000;
    }

    public double getImporteEscrituracion() {
        return this.getImporte() - this.contratoVender.getImporteCompraVenta();
    }

    public float getPorcentajeComisionVendedor() {
        return 3;
    }

    public float getPorcentajeComisionComprador() {
        return 3;
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
        Cliente clienteLocal = getClienteManagedBean().obtenerCliente(dniCliente);
        if (clienteLocal == null) {
            openPopupCliente();
            return null;
        } else {
            getContratoVender().setClienteComprador(clienteLocal);
        }
        return "VerDatosCliente";
    }

    public String botonCancelar() {
        contratoVender = null;
        return "IngresarCliente";
    }

    public String botonSiguienteVerDatosCliente() {
        analisisCrediticioInquilino = getAnalisisCrediticioManagedBean().obtenerUltimoAnalisisCrediticio(contratoVender.getClienteComprador());
        if (analisisCrediticioInquilino == null || !isVigente(analisisCrediticioInquilino)) {
            openPopupAnalisisCredInvalido();
            return "VerDatosCliente";
        } else if (analisisCrediticioInquilino.getEstadoAnalisisCrediticio().getNombre().trim().equals("Desaprobado")) {
            openPopupAnalisisCredDesaprobado();
            return "VerDatosCliente";
        } else {
            analisisCrediticioManagedBean.setAnalisis(analisisCrediticioInquilino);
            return "VerAnalisisCrediticio";
        }
    }

    public String botonConfirmarVenta() {
        contratoVenderSessionBean.save(contratoVender);
        List<Cliente> nuevosPropietarios = new ArrayList<Cliente>();
        nuevosPropietarios.add(contratoVender.getClienteComprador());
        contratoVender.getInmueble().setPropietarios(nuevosPropietarios);
        inmuebleSessionBean.save(contratoVender.getInmueble());
        JsfUtil.addSuccessMessage("Venta realizada exitosamente");
        return "IngresarCliente";
    }

    public String botonAtrasVerDatosCliente() {
        return "IngresarCliente";
    }

    public String botonAtrasVerDatosVenta() {
        return "IngresarVenta";
    }

    private boolean isVigente(AnalisisCrediticio analisisCrediticio) {
        boolean resultado = false;
        if (analisisCrediticio != null) {
            resultado = JsfUtil.esFechaValida(analisisCrediticio.getFechaCreacion(), 30);
        }
        return resultado;
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
            contratoVender.setInmueble(inmueble);
            return "VerDatosInmueble";
        } else {
            return "";
        }
    }

    public String botonAtrasListarInmueble() {
        return "VerAnalisisCrediticio";
    }

    public String botonSiguienteIngresarVenta() {
        if (contratoVender != null) {
            this.contratoVender.setLugarDePago(this.direccionManagedBean.getDireccion());
            this.contratoVender.setPorcentajePropietario(getPorcentajeComisionVendedor());
            this.contratoVender.setPorcentajeOtraParte(getPorcentajeComisionComprador());
            this.contratoVender.setClientesPropietarios(this.contratoVender.getInmueble().getPropietarios());
            return "VerDatosVenta";
        } else {
            return "";
        }
    }

    public String botonAtrasIngresarVenta() {
        return "VerDatosInmueble";
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

    public void onImporteChange(ValueChangeEvent event) {
        this.contratoVender.setImporte(Double.parseDouble(event.getNewValue().toString()));
    }

    public void onImporteCompraVentaChange(ValueChangeEvent event) {
        this.contratoVender.setImporteCompraVenta(Double.parseDouble(event.getNewValue().toString()));
    }

    public String botonSiguienteVerAnalisisCrediticio() {
        List<Visita> visitas = visitaSessionBean.obtenerVisitas(contratoVender.getClienteComprador(), "En venta");
        List<Inmueble> inmueblesNoVisitados = getInmuebleManagedBean().obtenerInmuebles("En venta");
        List<InmuebleFila> resultado = new ArrayList<InmuebleFila>();
        InmuebleFila fila;

        if (visitas != null) {
            for (Visita visita : visitas) {
                fila = new InmuebleFila(visita.getInmueble());
                fila.setVisitado(true);
                resultado.add(fila);
            }
        }
        if (inmueblesNoVisitados != null) {
            for (InmuebleFila inmuebleFila : resultado) {
                if (inmueblesNoVisitados.contains(inmuebleFila.getInmueble())) {
                    inmueblesNoVisitados.remove(inmuebleFila.getInmueble());
                }
            }
            for (Inmueble i : inmueblesNoVisitados) {
                fila = new InmuebleFila(i);
                fila.setVisitado(false);
                resultado.add(fila);
            }
        }

        inmueblesFilas = resultado;
        return "ListarInmuebles";
    }

    public String botonAtrasVerAnalisisCrediticio() {
        return "VerDatosCliente";
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
        if (this.contratoVender.getInmueble() != null) {
            return "IngresarVenta";
        } else {
            return "";
        }
    }

    public String botonAtrasVerDatosInmueble() {
        this.inmueble = null;
        return "ListarInmuebles";
    }

    public String botonEditarInmueble() {
        if (inmueble != null) {
            getInmuebleManagedBean().cargarPantallaInmueble(inmueble);
            return "/inmueble/inmuebleCrearEditar";
        } else {
            return "";
        }
    }

    /**
     * @return the direccionManagedBean
     */
    public DireccionManagedBean getDireccionManagedBean() {
        return direccionManagedBean;
    }

    /**
     * @param direccionManagedBean the direccionManagedBean to set
     */
    public void setDireccionManagedBean(DireccionManagedBean direccionManagedBean) {
        this.direccionManagedBean = direccionManagedBean;
    }
}
