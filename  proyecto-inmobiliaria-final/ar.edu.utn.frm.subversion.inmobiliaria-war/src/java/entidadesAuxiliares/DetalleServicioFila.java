/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesAuxiliares;

import entidades.DetalleServicio;

/**
 *
 * @author Sebastian
 */
public class DetalleServicioFila {
    private boolean selected;
    private DetalleServicio detalleServicio;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public DetalleServicio getDetalleServicio() {
        return detalleServicio;
    }

    public void setDetalleServicio(DetalleServicio detalleServicio) {
        this.detalleServicio = detalleServicio;
    }
}
