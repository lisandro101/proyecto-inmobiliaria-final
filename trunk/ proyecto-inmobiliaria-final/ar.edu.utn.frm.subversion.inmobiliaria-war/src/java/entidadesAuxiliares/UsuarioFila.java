package entidadesAuxiliares;

import entidades.Usuario;

/**
 *
 * @author Lisandro
 */
public class UsuarioFila{

    private boolean selected;
    private Usuario usuario;
    
    public UsuarioFila(){   
    }
    
    public UsuarioFila(Usuario usuario){   
        this.usuario = usuario;
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
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }   
}
