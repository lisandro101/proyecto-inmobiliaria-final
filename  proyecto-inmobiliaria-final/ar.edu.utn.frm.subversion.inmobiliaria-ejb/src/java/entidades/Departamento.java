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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author MARIANA
 */
@Entity
@Table(name="departamento")
//@NamedQueries({
//    @NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d"),
//    @NamedQuery(name = "Departamento.findNombreDepartamento", query = "SELECT d FROM Departamento d WHERE d.nombreDepartamento = :nombreDepartamento"),
//    @NamedQuery(name = "Departamento.findLocalidad", query = "SELECT d FROM Departamento d WHERE d.localidad = :localidad")})
public class Departamento implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String idDepartamento;
    private String nombreDepartamento;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Localidad> localidades;

    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String id) {
        this.idDepartamento = id;
    }

    /**
     * @return the nombreDepartamento
     */
    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    /**
     * @param nombreDepartamento the nombreDepartamento to set
     */
    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    
    /**
     * @return the localidad
     */
    public List<Localidad> getLocalidades() {
        return localidades;
    }

    /**
     * @param localidad the localidad to set
     */
    public void setLocalidades(List<Localidad> localidad) {
        this.localidades = localidad;
    }
 
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDepartamento != null ? idDepartamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamento)) {
            return false;
        }
        Departamento other = (Departamento) object;
        if ((this.idDepartamento == null && other.idDepartamento != null) || (this.idDepartamento != null && !this.idDepartamento.equals(other.idDepartamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreDepartamento;
    }
   
}
