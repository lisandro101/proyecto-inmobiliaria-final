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
@Table(name="reserva")
@NamedQueries({
    //@NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r"),
    //@NamedQuery(name = "Reserva.findIdReserva", query = "SELECT r FROM Reserva r WHERE r.idReserva = :idReserva"),
    //@NamedQuery(name = "Reserva.findImporteSenia", query = "SELECT r FROM Reserva r WHERE r.importeSenia = :importeSenia"),
    //@NamedQuery(name = "Reserva.findFechaReserva", query = "SELECT r FROM Reserva r WHERE r.fechaReserva = :fechaReserva"),
    //@NamedQuery(name = "Reserva.findFechaFinPlazo", query = "SELECT r FROM Reserva r WHERE r.fechaFinPlazo = :fechaFinPlazo"),
    //@NamedQuery(name = "Reserva.findVigente", query = "SELECT r FROM Reserva r WHERE r.vigente = :vigente"),
    //@NamedQuery(name = "Reserva.findDescripcion", query = "SELECT r FROM Reserva r WHERE r.descripcion = :descripcion"),
    //@NamedQuery(name = "Reserva.findInmueble", query = "SELECT r FROM Reserva r WHERE r.inmueble = :inmueble"),
    /*@NamedQuery(name = "Reserva.findCliente", query = "SELECT r FROM Reserva r WHERE r.cliente = :cliente")*/})
public class Reserva implements Serializable {
   
    @Id
    private String idReserva;
    private double importeSenia;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaReserva;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFinPlazo;
    private boolean vigente;
    private String descripcion;
    @OneToOne
    private Inmueble inmueble;
    @OneToOne
    private Cliente cliente;
    
    public String getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(String id) {
        this.idReserva = id;
    }

    /**
     * @return the importeSenia
     */
    public double getImporteSenia() {
        return importeSenia;
    }

    /**
     * @param importeSenia the importeSenia to set
     */
    public void setImporteSenia(double importeSenia) {
        this.importeSenia = importeSenia;
    }

    /**
     * @return the fechaReserva
     */
    public Date getFechaReserva() {
        return fechaReserva;
    }

    /**
     * @param fechaReserva the fechaReserva to set
     */
    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    /**
     * @return the fechaFinPlazo
     */
    public Date getFechaFinPlazo() {
        return fechaFinPlazo;
    }

    /**
     * @param fechaFinPlazo the fechaFinPlazo to set
     */
    public void setFechaFinPlazo(Date fechaFinPlazo) {
        this.fechaFinPlazo = fechaFinPlazo;
    }

    /**
     * @return the vigente
     */
    public boolean isVigente() {
        return vigente;
    }

    /**
     * @param vigente the vigente to set
     */
    public void setVigente(boolean vigente) {
        this.vigente = vigente;
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

    /**
     * @return the Cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param Cliente the Cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReserva != null ? idReserva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.idReserva == null && other.idReserva != null) || (this.idReserva != null && !this.idReserva.equals(other.idReserva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.edu.utn.frm.subversion.inmobiliaria.entidades.Reserva[ idReserva=" + idReserva + " ]";
    }
    
}
