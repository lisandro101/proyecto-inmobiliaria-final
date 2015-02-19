/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import entidades.EstadoInmueble;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author MARIANA
 */
@Entity
@Table(name="historicoEstadoInmueble")
@NamedQueries({
//    @NamedQuery(name = "HistoricoEstadoInmueble.findAll", query = "SELECT hei FROM HistoricoEstadoInmueble hei")
    //,@NamedQuery(name = "HistoricoEstadoInmueble.findIdHistoricoEstadoInmueble", query = "SELECT hei FROM HistoricoEstadoInmueble hei WHERE hei.idHistoricoEstadoInmueble = :idHistoricoEstadoInmueble"),
    //@NamedQuery(name = "HistoricoEstadoInmueble.findFechaInicio", query = "SELECT hei FROM HistoricoEstadoInmueble hei WHERE hei.fechaInicio = :fechaInicio")
    //@NamedQuery(name = "HistoricoEstadoInmueble.findFechaFin", query = "SELECT hei FROM HistoricoEstadoInmueble hei WHERE hei.fechaFin = :fechaFin"),
    /*@NamedQuery(name = "HistoricoEstadoInmueble.findEstadoInmueble", query = "SELECT hei FROM HistoricoEstadoInmueble hei WHERE hei.estadoInmueble = :estadoInmueble")*/
})
public class HistoricoEstadoInmueble implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String idHistoricoEstadoInmueble;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaIncio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFin;
    
    @ManyToOne(cascade={CascadeType.REFRESH})
    private EstadoInmueble estadoInmueble;

    public String getIdHistoricoEstadoInmueble() {
        return idHistoricoEstadoInmueble;
    }

    public void setIdHistoricoEstadoInmueble(String id) {
        this.idHistoricoEstadoInmueble = id;
    }

    /**
     * @return the fechaIncio
     */
    public Date getFechaIncio() {
        return fechaIncio;
    }

    /**
     * @param fechaIncio the fechaIncio to set
     */
    public void setFechaIncio(Date fechaIncio) {
        this.fechaIncio = fechaIncio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the EstadoInmueble
     */
    public EstadoInmueble getEstadoInmueble() {
        return estadoInmueble;
    }

    /**
     * @param EstadoInmueble the EstadoInmueble to set
     */
    public void setEstadoInmueble(EstadoInmueble estadoInmueble) {
        this.estadoInmueble = estadoInmueble;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistoricoEstadoInmueble != null ? idHistoricoEstadoInmueble.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoricoEstadoInmueble)) {
            return false;
        }
        HistoricoEstadoInmueble other = (HistoricoEstadoInmueble) object;
        if ((this.idHistoricoEstadoInmueble == null && other.idHistoricoEstadoInmueble != null) || (this.idHistoricoEstadoInmueble != null && !this.idHistoricoEstadoInmueble.equals(other.idHistoricoEstadoInmueble))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.edu.utn.frm.subversion.inmobiliaria.entidades.HistoricoEstadoInmueble[ idHistoricoEstadoInmueble=" + idHistoricoEstadoInmueble + " ]";
    }
    
}
