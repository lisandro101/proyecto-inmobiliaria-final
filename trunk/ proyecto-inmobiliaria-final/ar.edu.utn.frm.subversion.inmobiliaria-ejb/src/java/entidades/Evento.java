/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.sql.Time;
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
@Table
//@NamedQueries({
//    @NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e"),
//    @NamedQuery(name = "Evento.findNombreEvento", query = "SELECT e FROM Evento e WHERE e.nombreEvento = :nombreEvento"),
//    @NamedQuery(name = "Evento.findHora", query = "SELECT e FROM Evento e WHERE e.hora = :hora"),
//    @NamedQuery(name = "Evento.findFrecuencia", query = "SELECT e FROM Evento e WHERE e.frecuencia = :frecuencia"),
//    @NamedQuery(name = "Evento.findNroRepeticiones", query = "SELECT e FROM Evento e WHERE e.nroRepeticiones = :nroRepeticiones")})
public class Evento implements Serializable {
   
    @Id
    private String idEvento;
    private String nombreEvento;
    private Time hora;
    private int frecuencia;
    private int nroRepeticiones;

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String id) {
        this.idEvento = id;
    }

    /**
     * @return the nombreEvento
     */
    public String getNombreEvento() {
        return nombreEvento;
    }

    /**
     * @param nombreEvento the nombreEvento to set
     */
    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    /**
     * @return the hora
     */
    public Time getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(Time hora) {
        this.hora = hora;
    }

    /**
     * @return the frecuencia
     */
    public int getFrecuencia() {
        return frecuencia;
    }

    /**
     * @param frecuencia the frecuencia to set
     */
    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    /**
     * @return the nroRepeticiones
     */
    public int getNroRepeticiones() {
        return nroRepeticiones;
    }

    /**
     * @param nroRepeticiones the nroRepeticiones to set
     */
    public void setNroRepeticiones(int nroRepeticiones) {
        this.nroRepeticiones = nroRepeticiones;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEvento != null ? idEvento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.idEvento == null && other.idEvento != null) || (this.idEvento != null && !this.idEvento.equals(other.idEvento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.edu.utn.frm.subversion.inmobiliaria.entidades.Evento[ idEvento=" + idEvento + " ]";
    }
    
}
