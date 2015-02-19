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
@Table
//@NamedQueries({
//    @NamedQuery(name = "Medio.findAll", query = "SELECT m FROM Medio m"),
//    @NamedQuery(name = "Medio.findIdMedio", query = "SELECT m FROM Medio m WHERE m.idMedio = :idMedio"),
//    @NamedQuery(name = "Medio.findNombre", query = "SELECT m FROM Medio m WHERE m.nombre = :nombre"),
//    @NamedQuery(name = "Medio.findTelefono", query = "SELECT m FROM Medio m WHERE m.telefono = :telefono"),
//    @NamedQuery(name = "Medio.findFechaAlta", query = "SELECT m FROM Medio m WHERE m.fechaAlta = :fechaAlta"),
//    @NamedQuery(name = "Medio.findActivo", query = "SELECT m FROM Medio m WHERE m.activo = :activo"),
//    @NamedQuery(name = "Medio.findTipoMedio", query = "SELECT m FROM Medio m WHERE m.tipoMedio = :tipoMedio")})
public class Medio implements Serializable {
   
    @Id
    private String idMedio;
    private String nombre;
    private String telefono;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaAlta;
    private boolean activo;
    @OneToOne
    private TipoMedio tipoMedio;
    @OneToOne
    private Direccion direccion;

    public String getIdMedio() {
        return idMedio;
    }

    public void setIdMedio(String id) {
        this.idMedio = id;
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
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the fechaAlta
     */
    public Date getFechaAlta() {
        return fechaAlta;
    }

    /**
     * @param fechaAlta the fechaAlta to set
     */
    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /**
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * @return the TipoMedio
     */
    public TipoMedio getTipoMedio() {
        return tipoMedio;
    }

    /**
     * @param TipoMedio the TipoMedio to set
     */
    public void setTipoMedio(TipoMedio tipoMedio) {
        this.tipoMedio = tipoMedio;
    }

    /**
     * @return the Direccion
     */
    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * @param Direccion the Direccion to set
     */
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMedio != null ? idMedio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medio)) {
            return false;
        }
        Medio other = (Medio) object;
        if ((this.idMedio == null && other.idMedio != null) || (this.idMedio != null && !this.idMedio.equals(other.idMedio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.edu.utn.frm.subversion.inmobiliaria.entidades.Medio[ idMedio=" + idMedio + " ]";
    }
    
}
