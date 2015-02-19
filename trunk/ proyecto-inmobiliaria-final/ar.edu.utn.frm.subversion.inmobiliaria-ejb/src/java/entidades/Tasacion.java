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
@Table(name="tasacion")
@NamedQueries({
    //@NamedQuery(name = "Tasacion.findAll", query = "SELECT t FROM Tasacion t"),
    //@NamedQuery(name = "Tasacion.findIdTasacion", query = "SELECT t FROM Tasacion t WHERE t.idTasacion = :idTasacion"),
    //@NamedQuery(name = "Tasacion.findNroTasacion", query = "SELECT t FROM Tasacion t WHERE t.nroTasacion = :nroTasacion"),
    //@NamedQuery(name = "Tasacion.findFecha", query = "SELECT t FROM Tasacion t WHERE t.fecha = :fecha"),
    //@NamedQuery(name = "Tasacion.findImporte", query = "SELECT t FROM Tasacion t WHERE t.importe = :importe"),
    //@NamedQuery(name = "Tasacion.findDetalle", query = "SELECT t FROM Tasacion t WHERE t.detalle = :detalle"),
    //@NamedQuery(name = "Tasacion.findEmpleado", query = "SELECT t FROM Tasacion t WHERE t.empleado = :empleado"),
    /*@NamedQuery(name = "Tasacion.findInmueble", query = "SELECT t FROM Tasacion t WHERE t.inmueble = :inmueble")*/})
public class Tasacion implements Serializable {
   
    @Id
    private String idTasacion;
    private int nroTasacion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    private double importe;
    private String detalle;
    @OneToOne
    private Empleado empleado;
    @OneToOne
    private Inmueble inmueble;
    
    public String getIdTasacion() {
        return idTasacion;
    }

    public void setIdTasacion(String id) {
        this.idTasacion = id;
    }

    /**
     * @return the nroTasacion
     */
    public int getNroTasacion() {
        return nroTasacion;
    }

    /**
     * @param nroTasacion the nroTasacion to set
     */
    public void setNroTasacion(int nroTasacion) {
        this.nroTasacion = nroTasacion;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the importe
     */
    public double getImporte() {
        return importe;
    }

    /**
     * @param importe the importe to set
     */
    public void setImporte(double importe) {
        this.importe = importe;
    }

    /**
     * @return the detalle
     */
    public String getDetalle() {
        return detalle;
    }

    /**
     * @param detalle the detalle to set
     */
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    /**
     * @return the Empleado
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * @param Empleado the Empleado to set
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTasacion != null ? idTasacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tasacion)) {
            return false;
        }
        Tasacion other = (Tasacion) object;
        if ((this.idTasacion == null && other.idTasacion != null) || (this.idTasacion != null && !this.idTasacion.equals(other.idTasacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.edu.utn.frm.subversion.inmobiliaria.entidades.Tasacion[ idTasacion=" + idTasacion + " ]";
    }
    
}
