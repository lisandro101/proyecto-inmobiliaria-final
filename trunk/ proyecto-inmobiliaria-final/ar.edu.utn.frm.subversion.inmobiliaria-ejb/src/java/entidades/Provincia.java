/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author MARIANA
 */
@Entity
@Table(name = "provincia")
@NamedQueries({
//    @NamedQuery(name = "Provincia.findAll", query = "SELECT p FROM Provincia p"),
    @NamedQuery(name = "Provincia.buscar", query = "SELECT p FROM Provincia p")
//    @NamedQuery(name = "Provincia.findIdProvincia", query = "SELECT p FROM Provincia p WHERE p.idProvincia = :idProvincia"),
//    @NamedQuery(name = "Provincia.findNombreProvincia", query = "SELECT p FROM Provincia p WHERE p.nombreProvincia = :nombreProvincia"),
//    @NamedQuery(name = "Provincia.findAbreviatura", query = "SELECT p FROM Provincia p WHERE p.abreviatura = :abreviatura")
})
public class Provincia implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String idProvincia;
    private String nombreProvincia;
    private String abreviatura;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Departamento> departamento;

    /**
     * @return the departamento
     */
    public List<Departamento> getDepartamentos() {
        return departamento;
    }

    /**
     * @param departamento the departamento to set
     */
    public void setDepartamentos(List<Departamento> departamento) {
        this.departamento = departamento;
    }

    public String getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(String id) {
        this.idProvincia = id;
    }

    /**
     * @return the nombreProvincia
     */
    public String getNombreProvincia() {
        return nombreProvincia;
    }

    /**
     * @param nombreProvincia the nombreProvincia to set
     */
    public void setNombreProvincia(String nombreProvincia) {
        this.nombreProvincia = nombreProvincia;
    }

    /**
     * @return the abreviatura
     */
    public String getAbreviatura() {
        return abreviatura;
    }

    /**
     * @param abreviatura the abreviatura to set
     */
    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProvincia != null ? idProvincia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Provincia)) {
            return false;
        }
        Provincia other = (Provincia) object;
        if ((this.idProvincia == null && other.idProvincia != null) || (this.idProvincia != null && !this.idProvincia.equals(other.idProvincia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreProvincia;
    }
}
