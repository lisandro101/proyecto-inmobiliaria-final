/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package entidadesAuxiliares;

import entidades.Servicio;
import java.io.Serializable;

/**
 *
 * @author Sebastian
 */
public class ServicioFila implements Serializable{

    private boolean selected;
    private Servicio servicio;

    public ServicioFila(Servicio servicio) {
        this.servicio = servicio;
        this.selected = false;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
}
