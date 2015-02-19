/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesAuxiliares;

import entidades.Cliente;

/**
 *
 * @author Sebastian
 */
public class ClienteFila {
    private boolean selected;
    private Cliente cliente;

    public ClienteFila(Cliente cliente) {
        this.selected = false;
        this.cliente = cliente;
    }

    
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
       
}
