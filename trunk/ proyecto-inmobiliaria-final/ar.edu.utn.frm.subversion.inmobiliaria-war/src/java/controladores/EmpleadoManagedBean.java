package controladores;

import com.icesoft.faces.component.ext.RowSelectorEvent;
import entidades.Direccion;
import entidades.Empleado;
import entidadesAuxiliares.EmpleadoFila;
import expertos.DireccionSessionBean;
import expertos.EmpleadoSessionBean;
import java.util.List;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import util.JsfUtil;

@ManagedBean(name = "empleadoMB")
@SessionScoped
public class EmpleadoManagedBean implements Serializable {

    private List<EmpleadoFila> empleadoFilas;
    @EJB
    private EmpleadoSessionBean empleadoSessionBean;
    @EJB
    private DireccionSessionBean direccionSessionBean;

    public EmpleadoManagedBean() {
    }

    /**
     * @return the empleadoFilas
     */
    public List<EmpleadoFila> getEmpleadoFilas() {
        if (empleadoFilas == null || empleadoFilas.isEmpty()) {
            empleadoFilas = new ArrayList<EmpleadoFila>();
            for (Empleado e : empleadoSessionBean.findAll()) {
                empleadoFilas.add(new EmpleadoFila(e));
            }
        }
        return empleadoFilas;
    }

    public void setInmueblesFilas(List<EmpleadoFila> empleadoFilas) {
        this.empleadoFilas = empleadoFilas;
    }

    public void rowSelectionListener(RowSelectorEvent event) {
        try {
            int row = event.getRow();
        } catch (Exception e) {
            System.out.println("--> Error rowSelectionListener: " + e.toString());
        }
    }
}
