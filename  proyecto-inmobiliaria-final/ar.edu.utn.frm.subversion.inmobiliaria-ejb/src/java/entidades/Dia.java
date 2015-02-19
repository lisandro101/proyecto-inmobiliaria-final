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
@Table(name="dia")
@NamedQueries({
    //@NamedQuery(name = "Dia.findAll", query = "SELECT d FROM Dia d"),
    //@NamedQuery(name = "Dia.findIdDia", query = "SELECT d FROM Dia d WHERE d.idDia = :idDia"),
    //@NamedQuery(name = "Dia.findNroDia", query = "SELECT d FROM Dia d WHERE d.nroDia = :nroDia")
    /*@NamedQuery(name = "Dia.findEvento", query = "SELECT d FROM Dia d WHERE d.evento = :evento")*/})
public class Dia implements Serializable {
    
    @Id
    private String idDia;
    private int nroDia;
    @ManyToOne
    private Evento evento;

    public String getIdDia() {
        return idDia;
    }

    public void setIdDia(String id) {
        this.idDia = id;
    }

    /**
     * @return the nroDia
     */
    public int getNroDia() {
        return nroDia;
    }

    /**
     * @param nroDia the nroDia to set
     */
    public void setNroDia(int nroDia) {
        this.nroDia = nroDia;
    }

    /**
     * @return the Evento
     */
    public Evento getEvento() {
        return evento;
    }

    /**
     * @param Evento the Evento to set
     */
    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDia != null ? idDia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dia)) {
            return false;
        }
        Dia other = (Dia) object;
        if ((this.idDia == null && other.idDia != null) || (this.idDia != null && !this.idDia.equals(other.idDia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.edu.utn.frm.subversion.inmobiliaria.entidades.Dia[ idDia=" + idDia + " ]";
    }
 
}
