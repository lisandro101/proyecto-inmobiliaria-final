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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author MARIANA
 */
@Entity
@Table
//@NamedQueries({
// @NamedQuery(name = "Localidad.findAll", query = "SELECT l FROM Localidad l"),
// @NamedQuery(name = "Localidad.findNombreLocalidad", query = "SELECT l FROM Localidad l WHERE l.nombreLocalidad = :nombreLocalidad"),
// @NamedQuery(name = "Localidad.findCodigoPostal", query = "SELECT l FROM Localidad l WHERE l.codigoPostal = :codigoPostal")})
public class Localidad implements Serializable {
   
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String idLocalidad;
    private String nombreLocalidad;
    private int codigoPostal;

    public String getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(String id) {
        this.idLocalidad = id;
    }

    /**
     * @return the nombreLocalidad
     */
    public String getNombreLocalidad() {
        return nombreLocalidad;
    }

    /**
     * @param nombreLocalidad the nombreLocalidad to set
     */
    public void setNombreLocalidad(String nombreLocalidad) {
        this.nombreLocalidad = nombreLocalidad;
    }

    /**
     * @return the codigoPostal
     */
    public int getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * @param codigoPostal the codigoPostal to set
     */
    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLocalidad != null ? idLocalidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Localidad)) {
            return false;
        }
        Localidad other = (Localidad) object;
        if ((this.idLocalidad == null && other.idLocalidad != null) || (this.idLocalidad != null && !this.idLocalidad.equals(other.idLocalidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreLocalidad;
    }

}
