package entidadesAuxiliares;

import entidades.Visita;

/**
 *
 * @author Lisandro
 */
public class VisitaFila{

    private boolean selected;
    private Visita visita;
    
    public VisitaFila(){   
        super();
    }
    
    public VisitaFila(Visita visita){   
        this.visita = visita;
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
     * @return the contacto
     */
    public Visita getVisita() {
        return visita;
    }

    /**
     * @param visita the visita to set
     */
    public void setVisita(Visita visita) {
        this.visita = visita;
    }
    
}
