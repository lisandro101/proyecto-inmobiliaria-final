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
@Table(name="calendario")
@NamedQueries({
    //@NamedQuery(name = "Calendario.findAll", query = "SELECT c FROM Calendario c"),
    //@NamedQuery(name = "Calendario.findByIdCalendario", query = "SELECT c FROM Calendario c WHERE c.idCalendario = :idCalendario"),
    //@NamedQuery(name = "Calendario.findByNroAnio", query = "SELECT c FROM Calendario c WHERE c.nroAnio = :nroAnio")
    /*@NamedQuery(name = "Calendario.findByMes", query = "SELECT c FROM Calendario c WHERE c.mes = :mes")*/})
public class Calendario implements Serializable {
    
    @Id
    private String idCalendario;
    private int nroAnio;
    @ManyToOne
    private Mes mes;
    
    public String getIdCalendario() {
        return idCalendario;
    }

    public void setIdCalendario(String id) {
        this.idCalendario = id;
    }

    /**
     * @return the nroAnio
     */
    public int getNroAnio() {
        return nroAnio;
    }

    /**
     * @param nroAnio the nroAnio to set
     */
    public void setNroAnio(int nroAnio) {
        this.nroAnio = nroAnio;
    }

    /**
     * @return the Mes
     */
    public Mes getMes() {
        return mes;
    }

    /**
     * @param Mes the Mes to set
     */
    public void setMes(Mes mes) {
        this.mes = mes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCalendario != null ? idCalendario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calendario)) {
            return false;
        }
        Calendario other = (Calendario) object;
        if ((this.idCalendario == null && other.idCalendario != null) || (this.idCalendario != null && !this.idCalendario.equals(other.idCalendario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.edu.utn.frm.subversion.inmobiliaria.entidades.Calendario[ idCalendario=" + idCalendario + " ]";
    }
    
}
