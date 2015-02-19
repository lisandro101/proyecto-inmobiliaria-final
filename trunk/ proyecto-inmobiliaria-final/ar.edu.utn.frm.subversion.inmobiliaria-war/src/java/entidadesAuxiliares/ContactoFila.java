package entidadesAuxiliares;

import entidades.Contacto;

/**
 *
 * @author Lisandro
 */
public class ContactoFila{

    private boolean selected;
    private Contacto contacto;
    
    public ContactoFila(){   
    }
    
    public ContactoFila(Contacto contacto){   
        this.contacto = contacto;
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
    public Contacto getContacto() {
        return contacto;
    }

    /**
     * @param contacto the contacto to set
     */
    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }
    
}
