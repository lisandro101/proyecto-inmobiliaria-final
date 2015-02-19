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
@Table(name="estadoInmueble")
@NamedQueries({
    @NamedQuery(name = "EstadoInmueble.findAll", query = "SELECT ei FROM EstadoInmueble ei"),
    //@NamedQuery(name = "EstadoInmueble.findIdEstadoInmueble", query = "SELECT ei FROM EstadoInmueble ei WHERE ei.idEstadoInmueble = :idEstadoInmueble"),
    @NamedQuery(name = "EstadoInmueble.findNombreEstado", query = "SELECT ei FROM EstadoInmueble ei WHERE ei.estado = :estado")
})
public class EstadoInmueble implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String idEstadoInmueble;
    private String estado;
	
    public String getIdEstadoInmueble() {
        return idEstadoInmueble;
    }

    public void setIdEstadoInmueble(String id) {
        this.idEstadoInmueble = id;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoInmueble != null ? idEstadoInmueble.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoInmueble)) {
            return false;
        }
        EstadoInmueble other = (EstadoInmueble) object;
        if ((this.idEstadoInmueble == null && other.idEstadoInmueble != null) || (this.idEstadoInmueble != null && !this.idEstadoInmueble.equals(other.idEstadoInmueble))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return estado;
    }
    
}
