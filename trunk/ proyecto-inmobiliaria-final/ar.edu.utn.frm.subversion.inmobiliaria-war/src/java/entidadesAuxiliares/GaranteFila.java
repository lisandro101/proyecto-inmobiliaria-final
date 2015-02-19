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
public class GaranteFila {
    private boolean selected;
    private Cliente garante;
    private String resultadoAnalisisCrediticio;

    public GaranteFila(Cliente garante, String resultadoAnalisisCrediticio) {
        this.garante = garante;
        this.resultadoAnalisisCrediticio = resultadoAnalisisCrediticio;
        this.selected = false;
    }

    
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Cliente getGarante() {
        return garante;
    }

    public void setGarante(Cliente garante) {
        this.garante = garante;
    }

    public String getResultadoAnalisisCrediticio() {
        return resultadoAnalisisCrediticio;
    }

    public void setResultadoAnalisisCrediticio(String resultadoAnalisisCrediticio) {
        this.resultadoAnalisisCrediticio = resultadoAnalisisCrediticio;
    }
    
    
}
