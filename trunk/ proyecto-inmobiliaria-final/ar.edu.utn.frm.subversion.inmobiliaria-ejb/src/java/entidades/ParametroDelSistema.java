/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author MARIANA
 */
@Entity
@Table(name="parametroDelSistema")
@NamedQueries({
    //@NamedQuery(name = "ParametroDelSistema.findAll", query = "SELECT pds FROM ParametroDelSistema pds"),
    //@NamedQuery(name = "ParametroDelSistema.findIdParametroDelSistema", query = "SELECT pds FROM ParametroDelSistema pds WHERE pds.idParametroDelSistema = :idParametroDelSistema"),
    //@NamedQuery(name = "ParametroDelSistema.findFechaActualizacion", query = "SELECT pds FROM ParametroDelSistema pds WHERE pds.fechaActualizacion = :fechaActualizacion"),
    /*@NamedQuery(name = "ParametroDelSistema.findCondicionGarante", query = "SELECT pds FROM ParametroDelSistema pds WHERE pds.condicionGarante = :condicionGarante")*/})
public class ParametroDelSistema implements Serializable {
    
    @Id
    private String idParametroDelSistema;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaActualizacion;
    @ManyToOne
    private CondicionGarante condicionGarante;

    public String getIdParametroDelSistema() {
        return idParametroDelSistema;
    }

    public void setIdParametroDelSistema(String id) {
        this.idParametroDelSistema = id;
    }

    /**
     * @return the fechaActualizacion
     */
    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    /**
     * @param fechaActualizacion the fechaActualizacion to set
     */
    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    /**
     * @return the CondicionGarante
     */
    public CondicionGarante getCondicionGarante() {
        return condicionGarante;
    }

    /**
     * @param CondicionGarante the CondicionGarante to set
     */
    public void setCondicionGarante(CondicionGarante condicionGarante) {
        this.condicionGarante = condicionGarante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParametroDelSistema != null ? idParametroDelSistema.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametroDelSistema)) {
            return false;
        }
        ParametroDelSistema other = (ParametroDelSistema) object;
        if ((this.idParametroDelSistema == null && other.idParametroDelSistema != null) || (this.idParametroDelSistema != null && !this.idParametroDelSistema.equals(other.idParametroDelSistema))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.edu.utn.frm.subversion.inmobiliaria.entidades.ParametroDelSistema[ idParametroDelSistema=" + idParametroDelSistema + " ]";
    }
    
}
