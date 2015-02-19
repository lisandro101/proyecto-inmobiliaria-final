/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package controladores;

import com.icesoft.faces.component.ext.RowSelectorEvent;
import entidades.Caracteristica;
import entidadesAuxiliares.CaracteristicaFila;
import entidadesAuxiliares.DetalleCaracteristicaFila;
import expertos.CaracteristicaSessionBean;
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
@ManagedBean(name = "caracteristicaMB")
@SessionScoped
public class CaracteristicaManagedBean implements Serializable {

    @EJB
    private CaracteristicaSessionBean caracteristicaSessionBean;
    private List<CaracteristicaFila> caracteristicasFila;
    private Caracteristica caracteristica;
    @ManagedProperty("#{inmuebleMB}")
    private InmuebleManagedBean inmuebleManagedBean;

    public void create() {
        try {
            caracteristicaSessionBean.save(caracteristica);
            caracteristicasFila.add(new CaracteristicaFila(caracteristica));
            caracteristica = null;
        } catch (Exception e) {
            System.out.println("Error al persistir Caracteristica: " + e.toString());
        }
    }

    public void update() {
        try {
            getCaracteristicaSessionBean().save(caracteristica);

        } catch (Exception e) {
            System.out.println("Error al actualizar Caracteristica: " + e.toString());
        }
    }

    private void delete() {
        try {
            getCaracteristicaSessionBean().remove(caracteristica);

        } catch (Exception e) {
            System.out.println("Error al eliminar Caracteristica: " + e.toString());
        }
    }


    private CaracteristicaSessionBean getCaracteristicaSessionBean() {
        return caracteristicaSessionBean;
    }

    public List<CaracteristicaFila> getCaracteristicasFila() {
        if (caracteristicasFila == null) {
            caracteristicasFila = new ArrayList<CaracteristicaFila>();
        }
        if (caracteristicasFila.isEmpty()) {
            cargarFilas();
        }
        return caracteristicasFila;
    }

    private void cargarFilas() {
        List<Caracteristica> caracteristilasPosibles;
        CaracteristicaFila sf;
        if (caracteristicasFila == null) {
            caracteristicasFila = new ArrayList<CaracteristicaFila>();
        }
        caracteristilasPosibles = getCaracteristicaSessionBean().findAll();

        for (Caracteristica ca : caracteristilasPosibles) {
            caracteristicasFila.add(new CaracteristicaFila(ca));
        }
    }

    public void rowSelectionListener(RowSelectorEvent event) {
    }

    public List<Caracteristica> getCaracteristicasSeleccionadas() {
        List<Caracteristica> seleccionadas = null;

        if (caracteristicasFila != null && !caracteristicasFila.isEmpty()) {
            seleccionadas = new ArrayList<Caracteristica>();
            for (CaracteristicaFila caracteristicaFila1 : caracteristicasFila) {
                if (caracteristicaFila1.isSelected()) {
                    seleccionadas.add(caracteristicaFila1.getCaracteristica());
                }
            }
        }
        return seleccionadas;
    }

    public String botonVolverAInmueble() {
        return "inmueble";
    }

    public String botonVolverAConfiguracion() {
        return "configuracion";
    }

    public InmuebleManagedBean getInmuebleManagedBean() {
        return inmuebleManagedBean;
    }

    public void setInmuebleManagedBean(InmuebleManagedBean inmuebleManagedBean) {
        this.inmuebleManagedBean = inmuebleManagedBean;
    }

    public String inmuebleACaracteristica() {
        List<DetalleCaracteristicaFila> detallesCaractFila;
        if (inmuebleManagedBean == null) {
            System.out.println("el manejador inmueble es nulo");
            inmuebleManagedBean = (InmuebleManagedBean) JsfUtil.obtenerManejador("inmuebleMB");
        }

        for (CaracteristicaFila caracteristicaFila : getCaracteristicasFila()) {
            caracteristicaFila.setSelected(false);
        }
        detallesCaractFila = inmuebleManagedBean.getDetallesCaracteristicasFila();
        if (detallesCaractFila == null) {
            inmuebleManagedBean.setDetallesCaracteristicasFila(new ArrayList<DetalleCaracteristicaFila>());
        } else {
            for (DetalleCaracteristicaFila filaDetalle : detallesCaractFila) {
                for (CaracteristicaFila caractFila : caracteristicasFila) {
                    if (caractFila.getCaracteristica().equals(filaDetalle.getDetalleCaracteristica().getCaracteristica())) {
                        caractFila.setSelected(true);
                    }
                }
            }
        }
        return "caracteristicas";
    }

    public Caracteristica getCaracteristica() {
        if(caracteristica == null){
            caracteristica = new Caracteristica();                   
        }
        return caracteristica;
    }

    public void setCaracteristica(Caracteristica caracteristica) {
        this.caracteristica = caracteristica;
    }
    
}
