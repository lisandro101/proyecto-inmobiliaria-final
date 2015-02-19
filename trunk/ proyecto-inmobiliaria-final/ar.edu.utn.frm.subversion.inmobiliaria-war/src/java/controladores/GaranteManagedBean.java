/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.icesoft.faces.component.ext.RowSelectorEvent;
import entidades.AnalisisCrediticio;
import entidades.Cliente;
import entidades.EstadoAnalisisCrediticio;
import entidadesAuxiliares.ClienteFila;
import entidadesAuxiliares.GaranteFila;
import expertos.AnalisisCrediticioSessionBean;
import expertos.ClienteSessionBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import util.JsfUtil;

/**
 *
 * @author Sebastian
 */
@ManagedBean
@SessionScoped
public class GaranteManagedBean implements Serializable {

    List<GaranteFila> garantesFila;
    @EJB
    private ClienteSessionBean clienteSessionBean;
    @EJB
    private AnalisisCrediticioSessionBean analisisCrediticioSessionBean;
    
    private boolean mostrarPopupErrorGarante = false;
    //hardcode
    private int cantMinimaGarantes = 2;
    @ManagedProperty("#{alquilarInmuebleManagedBean}")
    private AlquilarInmuebleManagedBean alquilarInmuebleManagedBean;

    private String verificarEstadoAnalisisCrediticio(Cliente garante) {
        String resultado = null;
        AnalisisCrediticio analisisCred = analisisCrediticioSessionBean.obtenerUltimoAnalisisCrediticio(garante);
        EstadoAnalisisCrediticio estado;

        if (analisisCred == null) {
            resultado = "No realizado";
        } else {
            estado = analisisCred.getEstadoAnalisisCrediticio();
            if (estado.getNombre().equals("Aprobado")) {
                if (JsfUtil.esFechaValida(analisisCred.getFechaCreacion(), 30)) {
                    resultado = "Aprobado";
                } else {
                    resultado = "Desactualizado";
                }
            } else {
                resultado = "Desaprobado";
            }
        }
        return resultado;
    }

    public List<GaranteFila> obtenerGarantes() {
        List<Cliente> clientes;
        GaranteFila garaFila;

        if (garantesFila == null || garantesFila.isEmpty()) {
            clientes = clienteSessionBean.findAll();
            if (garantesFila == null) {
                garantesFila = new ArrayList<GaranteFila>();
            }

            for (Cliente cliente : clientes) {
                garaFila = new GaranteFila(cliente, verificarEstadoAnalisisCrediticio(cliente));
                garantesFila.add(garaFila);
            }
        }
        return garantesFila;
    }

    public void rowSelectionListener(RowSelectorEvent event) {
        try {
            if (!event.isSelected()) {
                garantesFila.get(event.getRow()).setSelected(false);
            }
        } catch (Exception e) {
            System.out.println("--> Error rowSelectionListener: " + e.toString());
        }
    }

    public String botonSiguienteListarGarantes() {
        if (validaCondicionesGarantes()) {
            getAlquilarInmuebleManagedBean().setGarantes(obtenerGarantesSeleccionados());
            return "IngresarDatosContrato";
        } else {
            openPopupErrorGarante();
            return "";
        }
    }

    public String botonAtrasListarGarantes() {
        return "VerDatosInmueble";
    }

    public boolean isMostrarPopupErrorGarante() {
        return mostrarPopupErrorGarante;
    }

    public void setMostrarPopupErrorGarante(boolean mostrarPopupErrorGarante) {
        this.mostrarPopupErrorGarante = mostrarPopupErrorGarante;
    }

    public void closePopupErrorGarante() {
        mostrarPopupErrorGarante = false;
    }

    public void openPopupErrorGarante() {
        mostrarPopupErrorGarante = true;
    }

    private boolean validaCondicionesGarantes() {
        boolean resultado = false;
        int contador = 0;
        if (garantesFila != null) {
            for (GaranteFila garanteFila : garantesFila) {
                if (garanteFila.isSelected() && garanteFila.getResultadoAnalisisCrediticio().equals("Aprobado")) {
                    ++contador;
                }
            }
        }
        if (contador >= cantMinimaGarantes) {
            resultado = true;
        }
        return resultado;

    }

    private List<Cliente> obtenerGarantesSeleccionados() {
        List<Cliente> resultado = null;
        if (garantesFila != null && !garantesFila.isEmpty()) {
            resultado = new ArrayList<Cliente>();
            for (GaranteFila garanteFila : garantesFila) {
                if (garanteFila.isSelected()) {
                    resultado.add(garanteFila.getGarante());
                }
            }
        }
        return resultado;
    }

    public AlquilarInmuebleManagedBean getAlquilarInmuebleManagedBean() {
        if (alquilarInmuebleManagedBean == null) {
            alquilarInmuebleManagedBean = (AlquilarInmuebleManagedBean) JsfUtil.obtenerManejador("alquilarInmuebleManagedBean");
        }
        return alquilarInmuebleManagedBean;
    }

    public void setAlquilarInmuebleManagedBean(AlquilarInmuebleManagedBean alquilarInmuebleManagedBean) {
        this.alquilarInmuebleManagedBean = alquilarInmuebleManagedBean;
    }
}
