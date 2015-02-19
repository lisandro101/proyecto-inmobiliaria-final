/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package entidadesAuxiliares;

import entidades.Caracteristica;
import java.io.Serializable;

/**
 *
 * @author Sebastian
 */
public class CaracteristicaFila implements Serializable{

    private Caracteristica caracteristica;
    private boolean selected;
    private boolean edit;
    
    public CaracteristicaFila(Caracteristica caracteristica){    
        this.caracteristica = caracteristica;
        this.selected = false;
    }

    public Caracteristica getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(Caracteristica caracteristica) {
        this.caracteristica = caracteristica;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
    
}
