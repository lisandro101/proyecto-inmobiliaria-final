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
@Table(name="caracteristica")
@NamedQueries({
    @NamedQuery(name = "Caracteristica.findAll", query = "SELECT c FROM Caracteristica c ORDER BY c.nombre ASC")
    //,@NamedQuery(name = "Caracteristica.findByIdCaracteristica", query = "SELECT c FROM Caracteristica c WHERE c.idCaracteristica = :idCaracteristica"),
    //@NamedQuery(name = "Caracteristica.findByNombre", query = "SELECT c FROM Caracteristica c WHERE c.nombre = :nombre"),
    //@NamedQuery(name = "Caracteristica.findByDescripcion", query = "SELECT c FROM Caracteristica c WHERE c.descripcion = :descripcion")
      })
public class Caracteristica implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String idCaracteristica;
    private String nombre;
    private String descripcion;

    public String getIdCaracteristica() {
        return idCaracteristica;
    }

    public void setIdCaracteristica(String id) {
        this.idCaracteristica = id;
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
        hash += (idCaracteristica != null ? idCaracteristica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caracteristica)) {
            return false;
        }
        Caracteristica other = (Caracteristica) object;
        if ((this.idCaracteristica == null && other.idCaracteristica != null) || (this.idCaracteristica != null && !this.idCaracteristica.equals(other.idCaracteristica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    } 

}
