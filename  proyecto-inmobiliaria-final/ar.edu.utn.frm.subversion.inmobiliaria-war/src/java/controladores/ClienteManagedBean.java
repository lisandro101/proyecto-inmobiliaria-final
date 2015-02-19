package controladores;

import com.icesoft.faces.component.ext.RowSelectorEvent;
import entidades.Cliente;
import entidades.Direccion;
import entidadesAuxiliares.ClienteFila;
import expertos.ClienteSessionBean;
import expertos.DireccionSessionBean;
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
 * @author mariodante
 */
@ManagedBean(name = "cliente")
@SessionScoped
public class ClienteManagedBean implements Serializable {

    @EJB
    private ClienteSessionBean ejbSessionCliente;
    @EJB
    private DireccionSessionBean ejbSessionDireccion;
    private Cliente cliente;
    private Direccion direccion;
    private String tipoCliente;
    private List<ClienteFila> clientesFilas;
    @ManagedProperty("#{provinciaMB}")
    private ProvinciaManagedBean provinciaManagedBean;
    @ManagedProperty("#{direccionMB}")
    private DireccionManagedBean direccionManagedBean;
   // @ManagedProperty("#{reporteMB}")
   // private ReporteManagedBean reportesManagedBean;

    /** Creates a new instance of ClienteManagedBean */
    public ClienteManagedBean() {
    }

    /**
     * @return the ejbSessionCliente
     */
    public ClienteSessionBean getEjbSessionCliente() {
        return ejbSessionCliente;
    }

    /**
     * @param ejbSessionCliente the ejbSessionCliente to set
     */
    public void setEjbSessionCliente(ClienteSessionBean ejbSessionCliente) {
        this.ejbSessionCliente = ejbSessionCliente;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        if (cliente == null) {
            cliente = new Cliente();

            Direccion dir = new Direccion();

            cliente.setDireccion(dir);

        }
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    //direccion
    public void setEjbSessionDireccion(DireccionSessionBean ejbSessionDireccion) {
        this.ejbSessionDireccion = ejbSessionDireccion;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * @return the ejbSessionCliente
     */
    public DireccionSessionBean getEjbSessionDireccion() {
        return ejbSessionDireccion;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public ProvinciaManagedBean getProvinciaManagedBean() {
        return provinciaManagedBean;
    }

    public void setProvinciaManagedBean(ProvinciaManagedBean provinciaManagedBean) {
        this.provinciaManagedBean = provinciaManagedBean;
    }

    public DireccionManagedBean getDireccionManagedBean() {
        return direccionManagedBean;
    }

    public void setDireccionManagedBean(DireccionManagedBean direccionManagedBean) {
        this.direccionManagedBean = direccionManagedBean;
    }

//    public ReporteManagedBean getReportesManagedBean() {
//        return reportesManagedBean;
//    }
//
//    public void setReportesManagedBean(ReporteManagedBean reportesManagedBean) {
//        this.reportesManagedBean = reportesManagedBean;
//    }

    public void create() {
        final Cliente clienteLocal = this.getCliente();

        //comprueba que no exista el cliente, lo intenta grabar
        if (getEjbSessionCliente().validarExistenciaCliente(clienteLocal.getDni())) {
            JsfUtil.addErrorMessage("El Cliente ya existe");
        } else {
            try {
                clienteLocal.setNroCliente(String.valueOf(buscarUltimoNroCliente()));
                clienteLocal.setDireccion(this.asignarDireccion());
                
                clienteLocal.setFechaAlta(new Date());
                clienteLocal.setTipo(this.getTipoClienteSelected());
                
                this.getEjbSessionCliente().save(clienteLocal);

                JsfUtil.addSuccessMessage("El Cliente " + clienteLocal.getApellidoNombre() + " se creo correctamente.");

            } catch (Exception ex) {
                JsfUtil.addErrorMessage(ex, "Ocurrio un error en la persistencia al guardar cliente");
                System.out.println("--> error: " + ex.toString() + "\n");
            }
        }


    }

    public void save(Cliente cli) {
        try {
            ejbSessionCliente.save(cli);
        } catch (Exception e) {
            System.out.println("Error al persistir Trabajo: " + e.toString());
        }
    }

    public void save() {
        try {
            ejbSessionCliente.save(cliente);
        } catch (Exception e) {
            System.out.println("Error al persistir Trabajo: " + e.toString());
        }
    }

    private int buscarUltimoNroCliente() {

        List<Cliente> cli = this.getEjbSessionCliente().buscarUltimoNumeroCliente();

        int nroCliente = 0;

        if (cli.isEmpty()) {
            nroCliente = 1;
        } else {
            //como sé q esa lista es uno solo, busco el nro y le +1
            nroCliente = Integer.parseInt(cli.get(0).getNroCliente()) + 1;
        }

        return nroCliente;

    }

    public String getTipoClienteSelected() {
        return tipoCliente;
    }

    public void setTipoClienteSelected(String tipoClienteSelected) {
        this.tipoCliente = tipoClienteSelected;
    }

    public void seleccionoTipoCliente(ValueChangeEvent event) {
        tipoCliente = (String) event.getNewValue();

    }

    private Direccion asignarDireccion() {
        Direccion dirNu = new Direccion();

        dirNu.setProvincia(this.direccionManagedBean.getProvinciaSelected());
        dirNu.setDepartamento(this.direccionManagedBean.getDepartamentoSelected());
        dirNu.setLocalidad(this.direccionManagedBean.getLocalidadSelected());
        dirNu.setNombreCalle(this.direccionManagedBean.getDireccion().getNombreCalle().toString());
        dirNu.setNumero(this.direccionManagedBean.getDireccion().getNumero().toString());
        dirNu.setNroPiso(this.direccionManagedBean.getDireccion().getNroPiso().toString());
        dirNu.setNroDepartamento(this.direccionManagedBean.getDireccion().getNroDepartamento().toString());
        dirNu.setObservacion(this.direccionManagedBean.getDireccion().getObservacion().toString());

        return dirNu;

    }

    public Cliente obtenerCliente(String dni) {
        return getEjbSessionCliente().obtenerCliente(dni);
    }

    public List<ClienteFila> getClientesFilas() {
        if (clientesFilas == null || clientesFilas.isEmpty()) {
            clientesFilas = new ArrayList<ClienteFila>();
            for (Cliente u : ejbSessionCliente.findAll()) {
                clientesFilas.add(new ClienteFila(u));
            }
        }
        return clientesFilas;
    }

    public void rowSelectionListener(RowSelectorEvent event) {
        try {
            int row = event.getRow();
        } catch (Exception e) {
            System.out.println("--> Error rowSelectionListener: " + e.toString());
        }
    }
    
    public String botonCancelar() {
        limpiarPantalla();
       // return "/cliente/create";
        return "";
    }
    
    private void limpiarPantalla() {
        cliente = null;
        direccion = null;
        getDireccionManagedBean().limpiarDireccion();
        
         if (cliente == null) {
            cliente = new Cliente();
            cliente.setApellido("");
            
//            tipo = new TipoInmueble();
//            tipo.setIdTipoInmueble("-1");
////            tipo.setIdTipoInmueble("");
//            inmueble.setTipoInmueble(tipo);
//            estadoInmueble = new EstadoInmueble();
        }
        
    }
    
    public String irAImprimirClientes()
    {
        return "/reportes/reporteClientesConFiltro";
    }
    
}