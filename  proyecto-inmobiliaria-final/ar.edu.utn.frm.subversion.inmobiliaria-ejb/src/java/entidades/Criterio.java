/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
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
@Table(name="criterio")
@NamedQueries({
    //@NamedQuery(name = "Criterio.findAll", query = "SELECT c FROM Criterio c"),
    //@NamedQuery(name = "Criterio.findIdCriterio", query = "SELECT c FROM Criterio c WHERE c.idCriterio = :idCriterio"),
    //@NamedQuery(name = "Criterio.findNombreAtributo", query = "SELECT c FROM Criterio c WHERE c.nombreAtributo = :nombreAtributo"),
    //@NamedQuery(name = "Criterio.findOperador", query = "SELECT c FROM Criterio c WHERE c.operador = :operador"),
    /*@NamedQuery(name = "Criterio.findValor", query = "SELECT c FROM Criterio c WHERE c.valor = :valor")*/})
public class Criterio implements Serializable {
    
    @Id
    private String idCriterio;
    private String nombreAtributo;
    private String operador;
    private String valor;
    
    public String getIdCriterio() {
        return idCriterio;
    }

    public void setIdCriterio(String id) {
        this.idCriterio = id;
    }

    /**
     * @return the nombreAtributo
     */
    public String getNombreAtributo() {
        return nombreAtributo;
    }

    /**
     * @param nombreAtributo the nombreAtributo to set
     */
    public void setNombreAtributo(String nombreAtributo) {
        this.nombreAtributo = nombreAtributo;
    }

    /**
     * @return the operador
     */
    public String getOperador() {
        return operador;
    }

    /**
     * @param operador the operador to set
     */
    public void setOperador(String operador) {
        this.operador = operador;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCriterio != null ? idCriterio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Criterio)) {
            return false;
        }
        Criterio other = (Criterio) object;
        if ((this.idCriterio == null && other.idCriterio != null) || (this.idCriterio != null && !this.idCriterio.equals(other.idCriterio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.edu.utn.frm.subversion.inmobiliaria.entidades.Criterio[ idCriterio=" + idCriterio + " ]";
    }
    
}
