/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author MARIANA
 */
@Entity
@Table(name="condicionGarante")
@NamedQueries({
    //@NamedQuery(name = "CondicionGarante.findAll", query = "SELECT c FROM CondicionGarante c"),
    //@NamedQuery(name = "CondicionGarante.findByIdCondicionGarante", query = "SELECT c FROM CondicionGarante c WHERE c.idCondicionGarante = :idCondicionGarante"),
    //@NamedQuery(name = "CondicionGarante.findByCantGarantes", query = "SELECT c FROM CondicionGarante c WHERE c.cantGarantes = :cantGarantes"),
    //@NamedQuery(name = "CondicionGarante.findByFechaInicio", query = "SELECT c FROM CondicionGarante c WHERE c.fechaInicio = :fechaInicio"),
    //@NamedQuery(name = "CondicionGarante.findByFechaFin", query = "SELECT c FROM CondicionGarante c WHERE c.fechaFin = :fechaFin"),
    /*@NamedQuery(name = "CondicionGarante.findByTipoInmueble", query = "SELECT c FROM CondicionGarante c WHERE c.tipoInmueble = :tipoInmueble")*/})
public class CondicionGarante implements Serializable {
    
    @Id
    private String idCondicionGarante;
    private int cantGarantes;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFin;
    @OneToOne
    private TipoInmueble tipoInmueble;

    public String getIdCondicionGarante() {
        return idCondicionGarante;
    }

    public void setIdCondicionGarante(String id) {
        this.idCondicionGarante = id;
    }

    /**
     * @return the cantGarantes
     */
    public int getCantGarantes() {
        return cantGarantes;
    }

    /**
     * @param cantGarantes the cantGarantes to set
     */
    public void setCantGarantes(int cantGarantes) {
        this.cantGarantes = cantGarantes;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
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
     * @return the TipoInmueble
     */
    public TipoInmueble getTipoInmueble() {
        return tipoInmueble;
    }

    /**
     * @param TipoInmueble the TipoInmueble to set
     */
    public void setTipoInmueble(TipoInmueble tipoInmueble) {
        this.tipoInmueble = tipoInmueble;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCondicionGarante != null ? idCondicionGarante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CondicionGarante)) {
            return false;
        }
        CondicionGarante other = (CondicionGarante) object;
        if ((this.idCondicionGarante == null && other.idCondicionGarante != null) || (this.idCondicionGarante != null && !this.idCondicionGarante.equals(other.idCondicionGarante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.edu.utn.frm.subversion.inmobiliaria.entidades.CondicionGarante[ idCondicionGarante=" + idCondicionGarante + " ]";
    }
    
}
