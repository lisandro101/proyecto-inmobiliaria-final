/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author MARIANA
 */
@Entity
@Table(name = "tipoPago")
@NamedQueries({ //@NamedQuery(name = "TipoPago.findAll", query = "SELECT t FROM TipoPago t"),
//@NamedQuery(name = "TipoPago.findIdTipoPago", query = "SELECT t FROM TipoPago t WHERE t.idTipoPago = :idTipoPago"),
//@NamedQuery(name = "TipoPago.findNroTipo", query = "SELECT t FROM TipoPago t WHERE t.nroTipo = :nroTipo"),
//@NamedQuery(name = "TipoPago.findNombreTipoPago", query = "SELECT t FROM TipoPago t WHERE t.nombreTipoPago = :nombreTipoPago"),
/*@NamedQuery(name = "TipoPago.findVigente", query = "SELECT t FROM TipoPago t WHERE t.vigente = :vigente")*/})
public class TipoPago implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String idTipoPago;
    private int nroTipo;
    private String nombreTipoPago;
    private boolean vigente;

    public String getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(String id) {
        this.idTipoPago = id;
    }

    /**
     * @return the nroTipo
     */
    public int getNroTipo() {
        return nroTipo;
    }

    /**
     * @param nroTipo the nroTipo to set
     */
    public void setNroTipo(int nroTipo) {
        this.nroTipo = nroTipo;
    }

    /**
     * @return the nombreTipoPago
     */
    public String getNombreTipoPago() {
        return nombreTipoPago;
    }

    /**
     * @param nombreTipoPago the nombreTipoPago to set
     */
    public void setNombreTipoPago(String nombreTipoPago) {
        this.nombreTipoPago = nombreTipoPago;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoPago != null ? idTipoPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPago)) {
            return false;
        }
        TipoPago other = (TipoPago) object;
        if ((this.idTipoPago == null && other.idTipoPago != null) || (this.idTipoPago != null && !this.idTipoPago.equals(other.idTipoPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.edu.utn.frm.subversion.inmobiliaria.entidades.TipoPago[ idTipoPago=" + idTipoPago + " ]";
    }
}
