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
@Table(name="tipoInmueble")
@NamedQueries({
    @NamedQuery(name = "TipoInmueble.findAll", query = "SELECT t FROM TipoInmueble t")
    //,@NamedQuery(name = "TipoInmueble.findIdTipoInmueble", query = "SELECT t FROM TipoInmueble t WHERE t.idTipoInmueble = :idTipoInmueble"),
    //@NamedQuery(name = "TipoInmueble.findNombreTipo", query = "SELECT t FROM TipoInmueble t WHERE t.nombreTipo = :nombreTipo")
})
public class TipoInmueble implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String idTipoInmueble;
    private String nombreTipo;

    public String getIdTipoInmueble() {
        return idTipoInmueble;
    }

    public void setIdTipoInmueble(String id) {
        this.idTipoInmueble = id;
    }

    /**
     * @return the nombreTipo
     */
    public String getNombreTipo() {
        return nombreTipo;
    }

    /**
     * @param nombreTipo the nombreTipo to set
     */
    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }
  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoInmueble != null ? idTipoInmueble.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoInmueble)) {
            return false;
        }
        TipoInmueble other = (TipoInmueble) object;
        if ((this.idTipoInmueble == null && other.idTipoInmueble != null) || (this.idTipoInmueble != null && !this.idTipoInmueble.equals(other.idTipoInmueble))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreTipo;
    }
  
}
