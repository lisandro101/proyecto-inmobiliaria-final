/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package controladores;

import com.icesoft.faces.component.ext.RowSelectorEvent;
import entidades.Servicio;
import entidadesAuxiliares.DetalleServicioFila;
import entidadesAuxiliares.ServicioFila;
import expertos.ServicioSessionBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import util.JsfUtil;

/**
 *
 * @author Sebastian
 */
@ManagedBean(name = "servicioMB")
@SessionScoped
public class ServicioManagedBean implements Serializable{

    @EJB
    private ServicioSessionBean servicioSessionBean;
    private List<ServicioFila> serviciosFila;
    private Servicio servicio;
    @ManagedProperty("#{inmuebleMB}")
    private InmuebleManagedBean inmuebleManagedBean;


    public List<Servicio> getServiciosSeleccionados() {
        List<Servicio> seleccionados = null;

        if (serviciosFila != null && !serviciosFila.isEmpty()) {
            seleccionados = new ArrayList<Servicio>();
            for (ServicioFila serviFila : serviciosFila) {
                if (serviFila.isSelected()) {
                    seleccionados.add(serviFila.getServicio());
                }
            }
        }
        return seleccionados;
    }

    private ServicioSessionBean getServicioSessionBean() {
        return servicioSessionBean;
    }

    public void create() {
        try {
            servicioSessionBean.save(getServicio());
            serviciosFila.add(new ServicioFila(getServicio()));
            setServicio(null);

        } catch (Exception e) {
            System.out.println("Error al persistir Servicio: " + e.toString());
        }
    }

    public void update() {
        try {
            servicioSessionBean.save(getServicio());
        } catch (Exception e) {
            System.out.println("Error al actualizar Servicio: " + e.toString());
        }
    }

    private void delete() {
        try {
            servicioSessionBean.remove(getServicio());

        } catch (Exception e) {
            System.out.println("Error al eliminar Servicio: " + e.toString());
        }
    }

    public List<ServicioFila> getServiciosFila() {
        if (serviciosFila == null) {
            serviciosFila = new ArrayList<ServicioFila>();
        }
        if (serviciosFila.isEmpty()) {
            cargarFilas();
        }
        return serviciosFila;
    }

    private void cargarFilas() {
        List<Servicio> serviciosPosibles;
        ServicioFila sf;
        if (serviciosFila == null) {
            serviciosFila = new ArrayList<ServicioFila>();
        }
        serviciosPosibles = getServicioSessionBean().findAll();

        for (Servicio ca : serviciosPosibles) {
            serviciosFila.add(new ServicioFila(ca));
        }
    }

    public void rowSelectionListener(RowSelectorEvent event) {
        
    }

    public String botonVolverAInmueble() {
        return "inmueble";
    }
    
    public String botonVolverAConfiguracion(){
        return "configuracion";
    }


    public InmuebleManagedBean getInmuebleManagedBean() {
        return inmuebleManagedBean;
    }

    public void setInmuebleManagedBean(InmuebleManagedBean inmuebleManagedBean) {
        this.inmuebleManagedBean = inmuebleManagedBean;
    }

    public String inmuebleAServicio() {
        List<DetalleServicioFila> detallesServiFila;
        if (inmuebleManagedBean == null) {
            System.out.println("el manejador inmueble es nulo");
            inmuebleManagedBean = (InmuebleManagedBean) JsfUtil.obtenerManejador("inmuebleMB");
        }

        for (ServicioFila sesrviFila : getServiciosFila()) {
            sesrviFila.setSelected(false);
        }
        detallesServiFila = inmuebleManagedBean.getDetallesServiciosFila();
        if (detallesServiFila == null) {
            inmuebleManagedBean.setDetallesServiciosFila(new ArrayList<DetalleServicioFila>());
        } else {
            for (DetalleServicioFila filaDetalle : detallesServiFila) {
                for (ServicioFila serviFila : serviciosFila) {
                    if (serviFila.getServicio().equals(filaDetalle.getDetalleServicio().getServicio())) {
                        serviFila.setSelected(true);
                    }
                }
            }
        }
        return "servicios";
    }
    
    public List<Servicio> getServiciosSeleccionadas() {
        List<Servicio> seleccionados = null;

        if (serviciosFila != null && !serviciosFila.isEmpty()) {
            seleccionados = new ArrayList<Servicio>();
            for (ServicioFila serFila : serviciosFila) {
                if (serFila.isSelected()) {
                    seleccionados.add(serFila.getServicio());
                }
            }
        }
        return seleccionados;
    }

    public Servicio getServicio() {
        if(servicio == null){
            servicio = new Servicio();
        }
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
    
}
