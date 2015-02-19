/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.icesoft.faces.component.ext.RowSelectorEvent;
import entidades.Cliente;
import entidadesAuxiliares.ClienteFila;
import expertos.ClienteSessionBean;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import util.JsfUtil;

/**
 *
 * @author Sebastian
 */
@ManagedBean
@SessionScoped
public class BuscarClienteManagedBean {

    @EJB
    private ClienteSessionBean clienteSessionBean;
    private String pagOrigen;
    private Cliente clienteSelected;
    private List<ClienteFila> clientesFilas;
    private String criterio;

    public String getPagOrigen() {
        return pagOrigen;
    }

    public void setPagOrigen(String pagOrigen) {
        this.pagOrigen = pagOrigen;
    }

    public Cliente getClienteSelected() {
        if(clienteSelected == null){
            clienteSelected = new Cliente();
        }
        return clienteSelected;
    }

    public void setClienteSelected(Cliente clienteSelected) {
        this.clienteSelected = clienteSelected;
    }

    public List<ClienteFila> getClientesFilas() {
        if(clientesFilas == null){
            clientesFilas = new ArrayList<ClienteFila>();
        }
        return clientesFilas;
    }

    public void setClientesFilas(List<ClienteFila> clientesFilas) {
        this.clientesFilas = clientesFilas;
    }

    public void rowSelectionListener(RowSelectorEvent event) {
        try {
            if (event.isSelected()) {
                clienteSelected = clientesFilas.get(event.getRow()).getCliente();
            } else {
                clientesFilas.get(event.getRow()).setSelected(false);
                clienteSelected = null;
            }
        } catch (Exception e) {
            System.out.println("--> Error rowSelectionListener: " + e.toString());
        }
    }

    public void actualizarTabla() {
        clientesFilas = convertirAFilas(clienteSessionBean.buscarClientes(criterio));
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    private List<ClienteFila> convertirAFilas(List<Cliente> clis) {
        List<ClienteFila> filas = new ArrayList<ClienteFila>();
        ClienteFila fi;
        for (Cliente cliente : clis) {
            fi = new ClienteFila(cliente);
            filas.add(fi);
        }
        return filas;
    }

    public String botonAceptar() {
        if (pagOrigen != null && clienteSelected != null) {
            if(pagOrigen.equals("/inmueble/inmuebleCrearEditar")){
                ((InmuebleManagedBean)JsfUtil.obtenerManejador("inmuebleMB")).agregarPropietario(clienteSelected);
            }else if(pagOrigen.equals("/analisisCrediticio/Create")){
                ((AnalisisCrediticioManagedBean)JsfUtil.obtenerManejador("analisisCrediticioManagedBean")).setCliente(clienteSelected) ;
                
            }
            return pagOrigen;
        } else {
            return "";
        }
    }

    public String botonCancelar() {
        return pagOrigen;
    }
    
    @PostConstruct
    public void limpiarPantalla(){
         clienteSelected = null;
         clientesFilas = null;
         criterio = "";
    }
}
