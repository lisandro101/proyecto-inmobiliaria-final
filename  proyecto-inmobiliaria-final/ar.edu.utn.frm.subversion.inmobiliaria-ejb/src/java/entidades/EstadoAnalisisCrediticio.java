/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author MARIANA
 */
@Entity
@Table(name="estadoAnalisisCrediticio")
@NamedQueries({
    //@NamedQuery(name = "EstadoAnalisisCrediticio.findAll", query = "SELECT eac FROM EstadoAnalisisCrediticio eac"),
    //@NamedQuery(name = "EstadoAnalisisCrediticio.findIdEstadoAnalisisCrediticio", query = "SELECT eac FROM EstadoAnalisisCrediticio eac WHERE eac.idEstadoAnalisisCrediticio = :idEstadoAnalisisCrediticio"),
    /*@NamedQuery(name = "EstadoAnalisisCrediticio.findNombre", query = "SELECT eac FROM EstadoAnalisisCrediticio eac WHERE eac.nombre = :nombre")*/})
public class EstadoAnalisisCrediticio implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String idEstadoAnalisisCrediticio;
    private String nombre;

    public String getIdEstadoAnalisisCrediticio() {
        return idEstadoAnalisisCrediticio;
    }

    public void setIdEstadoAnalisisCrediticio(String id) {
        this.idEstadoAnalisisCrediticio = id;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoAnalisisCrediticio != null ? idEstadoAnalisisCrediticio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoAnalisisCrediticio)) {
            return false;
        }
        EstadoAnalisisCrediticio other = (EstadoAnalisisCrediticio) object;
        if ((this.idEstadoAnalisisCrediticio == null && other.idEstadoAnalisisCrediticio != null) || (this.idEstadoAnalisisCrediticio != null && !this.idEstadoAnalisisCrediticio.equals(other.idEstadoAnalisisCrediticio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
