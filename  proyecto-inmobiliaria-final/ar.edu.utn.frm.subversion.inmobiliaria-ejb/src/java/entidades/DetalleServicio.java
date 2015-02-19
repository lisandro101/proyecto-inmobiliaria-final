/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author MARIANA
 */
@Entity
@Table(name="detalleServicio")
@NamedQueries({
    //@NamedQuery(name = "DetalleServicio.findAll", query = "SELECT ds FROM DetalleServicio ds"),
    //@NamedQuery(name = "DetalleServicio.findIdDetalleServicio", query = "SELECT ds FROM DetalleServicio ds WHERE ds.idDetalleServicio = :idDetalleServicio"),
    //@NamedQuery(name = "DetalleServicio.findObservacion", query = "SELECT ds FROM DetalleServicio ds WHERE ds.observacion = :observacion"),
    //@NamedQuery(name = "DetalleServicio.findPublicar", query = "SELECT ds FROM DetalleServicio ds WHERE ds.publicar = :publicar"),
    /*@NamedQuery(name = "DetalleServicio.findServicio", query = "SELECT ds FROM DetalleServicio ds WHERE ds.servicio = :servicio")*/})
public class DetalleServicio implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String idDetalleServicio;
    private String observacion;
    private boolean publicar;
    @ManyToOne(cascade={CascadeType.REFRESH}) 
    private Servicio servicio;
    
    public String getIdDetalleServicio() {
        return idDetalleServicio;
    }

    public void setIdDetalleServicio(String id) {
        this.idDetalleServicio = id;
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * @return the publicar
     */
    public boolean isPublicar() {
        return publicar;
    }

    /**
     * @param publicar the publicar to set
     */
    public void setPublicar(boolean publicar) {
        this.publicar = publicar;
    }

    /**
     * @return the servicio
     */
    public Servicio getServicio() {
        return servicio;
    }

    /**
     * @param servicio the servicio to set
     */
    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleServicio != null ? idDetalleServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleServicio)) {
            return false;
        }
        DetalleServicio other = (DetalleServicio) object;
        if ((this.idDetalleServicio == null && other.idDetalleServicio != null) || (this.idDetalleServicio != null && !this.idDetalleServicio.equals(other.idDetalleServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.edu.utn.frm.subversion.inmobiliaria.entidades.DetalleServicio[ idDetalleServicio=" + idDetalleServicio + " ]";
    }
    
}
