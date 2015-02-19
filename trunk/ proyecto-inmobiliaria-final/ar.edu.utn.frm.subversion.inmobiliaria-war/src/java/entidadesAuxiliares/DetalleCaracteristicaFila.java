/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesAuxiliares;

import entidades.DetalleCaracteristica;

/**
 *
 * @author Sebastian
 */
public class DetalleCaracteristicaFila {
    private boolean selected;
    private DetalleCaracteristica detalleCaracteristica;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public DetalleCaracteristica getDetalleCaracteristica() {
        return detalleCaracteristica;
    }

    public void setDetalleCaracteristica(DetalleCaracteristica detalleCaracteristica) {
        this.detalleCaracteristica = detalleCaracteristica;
    }
        
}
