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
@Table(name="trabajo")
@NamedQueries({
    //@NamedQuery(name = "Trabajo.findAll", query = "SELECT t FROM Trabajo t"),
    //@NamedQuery(name = "Trabajo.findIdTrabajo", query = "SELECT t FROM Trabajo t WHERE t.idTrabajo = :idTrabajo"),
    //@NamedQuery(name = "Trabajo.findEmpresaLaboral", query = "SELECT t FROM Trabajo t WHERE t.empresaLaboral = :empresaLaboral"),
    //@NamedQuery(name = "Trabajo.findPuestoLaboral", query = "SELECT t FROM Trabajo t WHERE t.puestoLaboral = :puestoLaboral"),
    //@NamedQuery(name = "Trabajo.findAntiguedad", query = "SELECT t FROM Trabajo t WHERE t.antiguedad = :antiguedad")
    //@NamedQuery(name = "Trabajo.findSueldo", query = "SELECT t FROM Trabajo t WHERE t.sueldo = :sueldo")
    /*@NamedQuery(name = "Trabajo.findCUIT", query = "SELECT t FROM Trabajo t WHERE t.cuit = :cuit")*/})
public class Trabajo implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String idTrabajo;
    private String empleador;
    private String puestoLaboral;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaIngreso;
    private double sueldo;
    private String cuit;
    

    public String getIdTrabajo() {
        return idTrabajo;
    }

    public void setIdTrabajo(String idTrabajo) {
        this.idTrabajo = idTrabajo;
    }

    public String getEmpleador() {
        return empleador;
    }

    public void setEmpleador(String empleador) {
        this.empleador = empleador;
    }

    public String getPuestoLaboral() {
        return puestoLaboral;
    }

    public void setPuestoLaboral(String puestoLaboral) {
        this.puestoLaboral = puestoLaboral;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdTrabajo() != null ? getIdTrabajo().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trabajo)) {
            return false;
        }
        Trabajo other = (Trabajo) object;
        if ((this.getIdTrabajo() == null && other.getIdTrabajo() != null) || (this.getIdTrabajo() != null && !this.idTrabajo.equals(other.idTrabajo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.edu.utn.frm.subversion.inmobiliaria.entidades.Trabajo[ idTrabajo=" + getIdTrabajo() + " ]";
    }
}
