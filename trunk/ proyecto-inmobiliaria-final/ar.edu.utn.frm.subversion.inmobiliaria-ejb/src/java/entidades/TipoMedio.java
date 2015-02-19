/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author MARIANA
 */
@Entity
@Table(name="tipoMedio")
@NamedQueries({
    //@NamedQuery(name = "TipoMedio.findAll", query = "SELECT t FROM TipoMedio t"),
    //@NamedQuery(name = "TipoMedio.findIdTasacion", query = "SELECT t FROM TipoMedio t WHERE t.idTipoMedio = :idTipoMedio")
    //@NamedQuery(name = "TipoMedio.findNombre", query = "SELECT t FROM TipoMedio t WHERE t.nombre = :nombre")
    /*@NamedQuery(name = "TipoMedio.findPrecioPromedio", query = "SELECT t FROM TipoMedio t WHERE t.precioPromedio = :precioPromedio")*/})
public class TipoMedio implements Serializable {
    
    @Id
    private String idTipoMedio;
    private String nombre;
    private double precioPromedio;
    
    public String getIdTipoMedio() {
        return idTipoMedio;
    }

    public void setIdTipoMedio(String id) {
        this.idTipoMedio = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the precioPromedio
     */
    public double getPrecioPromedio() {
        return precioPromedio;
    }

    /**
     * @param precioPromedio the precioPromedio to set
     */
    public void setPrecioPromedio(double precioPromedio) {
        this.precioPromedio = precioPromedio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoMedio != null ? idTipoMedio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMedio)) {
            return false;
        }
        TipoMedio other = (TipoMedio) object;
        if ((this.idTipoMedio == null && other.idTipoMedio != null) || (this.idTipoMedio != null && !this.idTipoMedio.equals(other.idTipoMedio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.edu.utn.frm.subversion.inmobiliaria.entidades.TipoMedio[ idTipoMedio=" + idTipoMedio + " ]";
    }
    
}
