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
@Table(name="publicacion")
//@NamedQueries({
//    @NamedQuery(name = "Publicacion.findAll", query = "SELECT p FROM Publicacion p"),
//    @NamedQuery(name = "Publicacion.findNroPublicacion", query = "SELECT p FROM Publicacion p WHERE p.nroPublicacion = :nroPublicacion"),
//    @NamedQuery(name = "Publicacion.findFechaPublicacion", query = "SELECT p FROM Publicacion p WHERE p.fechaPublicacion = :fechaPublicacion"),
//    @NamedQuery(name = "Publicacion.findDuracion", query = "SELECT p FROM Publicacion p WHERE p.duracion = :duracion"),
//    @NamedQuery(name = "Publicacion.findPrecioTotal", query = "SELECT p FROM Publicacion p WHERE p.precioTotal = :precioTotal"),
//    @NamedQuery(name = "Publicacion.findMedio", query = "SELECT p FROM Publicacion p WHERE p.medio = :medio"),
//    @NamedQuery(name = "Publicacion.findInmueble", query = "SELECT p FROM Publicacion p WHERE p.inmueble = :inmueble")})
public class Publicacion implements Serializable {
    
    @Id
    private String idPublicacion;
    private String nroPublicacion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaPublicacion;
    private String duracion;
    private double precioTotal;
    @OneToOne
    private Medio medio;
    @OneToOne
    private Inmueble inmueble;
    
    public String getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(String id) {
        this.idPublicacion = id;
    }

    /**
     * @return the nroPublicacion
     */
    public String getNroPublicacion() {
        return nroPublicacion;
    }

    /**
     * @param nroPublicacion the nroPublicacion to set
     */
    public void setNroPublicacion(String nroPublicacion) {
        this.nroPublicacion = nroPublicacion;
    }

    /**
     * @return the fechaPublicacion
     */
    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    /**
     * @param fechaPublicacion the fechaPublicacion to set
     */
    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    /**
     * @return the duracion
     */
    public String getDuracion() {
        return duracion;
    }

    /**
     * @param duracion the duracion to set
     */
    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    /**
     * @return the precioTotal
     */
    public double getPrecioTotal() {
        return precioTotal;
    }

    /**
     * @param precioTotal the precioTotal to set
     */
    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    /**
     * @return the Medio
     */
    public Medio getMedio() {
        return medio;
    }

    /**
     * @param Medio the Medio to set
     */
    public void setMedio(Medio medio) {
        this.medio = medio;
    }

    /**
     * @return the Inmueble
     */
    public Inmueble getInmueble() {
        return inmueble;
    }

    /**
     * @param Inmueble the Inmueble to set
     */
    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPublicacion != null ? idPublicacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Publicacion)) {
            return false;
        }
        Publicacion other = (Publicacion) object;
        if ((this.idPublicacion == null && other.idPublicacion != null) || (this.idPublicacion != null && !this.idPublicacion.equals(other.idPublicacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.edu.utn.frm.subversion.inmobiliaria.entidades.Publicacion[ idPublicacion=" + idPublicacion + " ]";
    }
    
}
