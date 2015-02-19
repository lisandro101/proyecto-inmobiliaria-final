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
@Table(name="servicio")
@NamedQueries({
    @NamedQuery(name = "Servicio.findAll", query = "SELECT s FROM Servicio s ORDER BY s.nombre ASC")
    //,@NamedQuery(name = "Servicio.findIdServicio", query = "SELECT s FROM Servicio s WHERE s.idServicio = :idServicio")
    //@NamedQuery(name = "Servicio.findNombre", query = "SELECT s FROM Servicio s WHERE s.nombre = :nombre"),
    /*@NamedQuery(name = "Servicio.findDescripcion", query = "SELECT s FROM Servicio s WHERE s.descripcion = :descripcion")*/
})
public class Servicio implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String idServicio;
    private String nombre;
    private String descripcion;
        
    public String getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(String id) {
        this.idServicio = id;
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
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idServicio != null ? idServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicio)) {
            return false;
        }
        Servicio other = (Servicio) object;
        if ((this.idServicio == null && other.idServicio != null) || (this.idServicio != null && !this.idServicio.equals(other.idServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.edu.utn.frm.subversion.inmobiliaria.entidades.Servicio[ idServicio=" + idServicio + " ]";
    }
    
}
