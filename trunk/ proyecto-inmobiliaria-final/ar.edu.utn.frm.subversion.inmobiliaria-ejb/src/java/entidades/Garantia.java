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
@Table(name="garantia")
@NamedQueries({
    //@NamedQuery(name = "Garantia.findAll", query = "SELECT g FROM Garantia g"),
    //@NamedQuery(name = "Garantia.findIdGarantia", query = "SELECT g FROM Garantia g WHERE g.idGarantia = :idGarantia"),
    /*@NamedQuery(name = "Garantia.findNombre", query = "SELECT g FROM Garantia g WHERE g.nombre = :nombre")*/})
public class Garantia implements Serializable {
     
    @Id
    private String idGarantia;
    private String nombre;

    public String getIdGarantia() {
        return idGarantia;
    }

    public void setIdGarantia(String id) {
        this.idGarantia = id;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGarantia != null ? idGarantia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Garantia)) {
            return false;
        }
        Garantia other = (Garantia) object;
        if ((this.idGarantia == null && other.idGarantia != null) || (this.idGarantia != null && !this.idGarantia.equals(other.idGarantia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.edu.utn.frm.subversion.inmobiliaria.entidades.Garantia[ idGarantia=" + idGarantia + " ]";
    }
    
}
