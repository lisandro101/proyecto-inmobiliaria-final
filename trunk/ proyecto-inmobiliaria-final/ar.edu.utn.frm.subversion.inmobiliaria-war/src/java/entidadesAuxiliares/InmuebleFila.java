package entidadesAuxiliares;

import entidades.Inmueble;
import java.io.Serializable;

/**
 *
 * @author Lisandro
 */
public class InmuebleFila implements Serializable{

    private boolean selected;
    private Inmueble inmueble;
    private boolean visitado;
    
    public InmuebleFila(){           
    }
    
    public InmuebleFila(Inmueble inmueble){   
        this.inmueble = inmueble;
        this.selected = false; 
        this.visitado = false;
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
     * @return the inmueble
     */
    public Inmueble getInmueble() {
        return inmueble;
    }

    /**
     * @param inmueble the inmueble to set
     */
    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }
    
}
