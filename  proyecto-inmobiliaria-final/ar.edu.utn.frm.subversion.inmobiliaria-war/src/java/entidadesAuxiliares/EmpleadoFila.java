package entidadesAuxiliares;

import entidades.Empleado;

/**
 *
 * @author Lisandro
 */
public class EmpleadoFila {

    private boolean selected;
    private Empleado empleado;

    public EmpleadoFila() {
    }

    public EmpleadoFila(Empleado empleado) {
        this.empleado = empleado;
        this.selected = false;
    }

    /**
     * @return the selected
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * @return the empleado
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
