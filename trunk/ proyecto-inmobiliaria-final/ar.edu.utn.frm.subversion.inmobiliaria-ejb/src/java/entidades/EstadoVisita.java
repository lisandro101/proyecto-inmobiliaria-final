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
 * @author Lisandro Nieto
 */
@Entity
@Table(name = "estadoVisita")
@NamedQueries({
    @NamedQuery(name = "EstadoVisita.findByNombreEstado", query = "SELECT c FROM EstadoVisita c WHERE c.estado = :estado"),
    @NamedQuery(name = "EstadoVisita.findByEstadoPendiente", query = "SELECT c FROM EstadoVisita c WHERE c.estado = 'Pendiente'")
})
public class EstadoVisita implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String idEstadoVisita;
    private String estado;

    /**
     * @return the idEstadoVisita
     */
    public String getIdEstadoVisita() {
        return idEstadoVisita;
    }

    /**
     * @param idEstadoVisita the idEstadoVisita to set
     */
    public void setIdEstadoVisita(String idEstadoVisita) {
        this.idEstadoVisita = idEstadoVisita;
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
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EstadoVisita other = (EstadoVisita) obj;
        if ((this.idEstadoVisita == null) ? (other.idEstadoVisita != null) : !this.idEstadoVisita.equals(other.idEstadoVisita)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.idEstadoVisita != null ? this.idEstadoVisita.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return this.getEstado() != null ? this.getEstado() : "";
    }
}
