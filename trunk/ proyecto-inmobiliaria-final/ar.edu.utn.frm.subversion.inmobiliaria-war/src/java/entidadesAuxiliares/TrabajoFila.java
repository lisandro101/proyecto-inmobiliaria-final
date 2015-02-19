/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesAuxiliares;

import entidades.Trabajo;
import java.io.Serializable;

/**
 *
 * @author Sebastian
 */
public class TrabajoFila implements Serializable{
    private boolean selected;
    private Trabajo trabajo;

    public TrabajoFila(Trabajo trabajo){
        this.trabajo = trabajo;
    }
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Trabajo getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(Trabajo trabajo) {
        this.trabajo = trabajo;
    }
       
}
