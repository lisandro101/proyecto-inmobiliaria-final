/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author MARIANA
 */
@Entity
@Table
//@NamedQueries({
//    @NamedQuery(name = "Cuota.findAll", query = "SELECT c FROM Cuota c"),
//    @NamedQuery(name = "Cuota.findIdCuota", query = "SELECT c FROM Cuota c WHERE c.idCuota = :idCuota"),
//    @NamedQuery(name = "Cuota.findFechaPago", query = "SELECT c FROM Cuota c WHERE c.fechaPago = :fechaPago"),
//    @NamedQuery(name = "Cuota.findImporte", query = "SELECT c FROM Cuota c WHERE c.importe = :importe")})
public class Cuota implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String idCuota;
    private int nroCuota;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaPago;
    private double importe;
    private float interes;
    
    public String getIdCuota() {
        return idCuota;
    }

    public void setIdCuota(String id) {
        this.idCuota = id;
    }

    /**
     * @return the nroCuota
     */
    public int getNroCuota() {
        return nroCuota;
    }

    /**
     * @param nroCuota the nroCuota to set
     */
    public void setNroCuota(int nroCuota) {
        this.nroCuota = nroCuota;
    }

    /**
     * @return the fechaPago
     */
    public Date getFechaPago() {
        return fechaPago;
    }

    /**
     * @param fechaPago the fechaPago to set
     */
    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
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
     * @return the interes
     */
    public float getInteres() {
        return interes;
    }

    /**
     * @param interes the interes to set
     */
    public void setInteres(float interes) {
        this.interes = interes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCuota != null ? idCuota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuota)) {
            return false;
        }
        Cuota other = (Cuota) object;
        if ((this.idCuota == null && other.idCuota != null) || (this.idCuota != null && !this.idCuota.equals(other.idCuota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.edu.utn.frm.subversion.inmobiliaria.entidades.Cuota[ idCuota=" + idCuota + " ]";
    }
    
}
