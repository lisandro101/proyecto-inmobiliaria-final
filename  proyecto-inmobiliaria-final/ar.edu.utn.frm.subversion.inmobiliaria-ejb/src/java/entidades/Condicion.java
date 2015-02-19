/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author MARIANA
 */
@Entity
@Table(name="condicion")
@NamedQueries({
    @NamedQuery(name = "Condicion.findAll", query = "SELECT c FROM Condicion c")
    //,@NamedQuery(name = "Condicion.findByIdCondicion", query = "SELECT c FROM Condicion c WHERE c.idCondicion = :idCondicion"),
    //@NamedQuery(name = "Condicion.findByNombre", query = "SELECT c FROM Condicion c WHERE c.nombre = :nombre"),
    /*@NamedQuery(name = "Condicion.findByDescripcion", query = "SELECT c FROM Condicion c WHERE c.descripcion = :descripcion")*/
})
public class Condicion implements Serializable {
    
    @Id
    private String idCondicion;
    private String nombre;
    private String descripcion;

    public String getIdCondicion() {
        return idCondicion;
    }

    public void setIdCondicion(String id) {
        this.idCondicion = id;
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
        hash += (idCondicion != null ? idCondicion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Condicion)) {
            return false;
        }
        Condicion other = (Condicion) object;
        if ((this.idCondicion == null && other.idCondicion != null) || (this.idCondicion != null && !this.idCondicion.equals(other.idCondicion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
