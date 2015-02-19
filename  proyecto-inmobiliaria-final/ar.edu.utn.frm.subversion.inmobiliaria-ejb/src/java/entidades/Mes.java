/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Entity;
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
@Table(name="mes")
@NamedQueries({
    //@NamedQuery(name = "Mes.findAll", query = "SELECT m FROM Mes m"),
    //@NamedQuery(name = "Mes.findIdMes", query = "SELECT m FROM Mes m WHERE m.idMes = :idMes"),
    //@NamedQuery(name = "Mes.findNroMes", query = "SELECT m FROM Mes m WHERE m.nroMes = :nroMes"),
    /*@NamedQuery(name = "Mes.findDia", query = "SELECT m FROM Mes m WHERE m.dia = :dia")*/})
public class Mes implements Serializable {
    
    @Id
    private String idMes;
    private int nroMes;
    @ManyToOne
    private Dia dia;
    
    public String getIdMes() {
        return idMes;
    }

    public void setIdMes(String id) {
        this.idMes = id;
    }

    /**
     * @return the nroMes
     */
    public int getNroMes() {
        return nroMes;
    }

    /**
     * @param nroMes the nroMes to set
     */
    public void setNroMes(int nroMes) {
        this.nroMes = nroMes;
    }

    /**
     * @return the Dia
     */
    public Dia getDia() {
        return dia;
    }

    /**
     * @param Dia the Dia to set
     */
    public void setDia(Dia dia) {
        this.dia = dia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMes != null ? idMes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mes)) {
            return false;
        }
        Mes other = (Mes) object;
        if ((this.idMes == null && other.idMes != null) || (this.idMes != null && !this.idMes.equals(other.idMes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.edu.utn.frm.subversion.inmobiliaria.entidades.Mes[ idMes=" + idMes + " ]";
    }
    
}
