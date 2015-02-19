/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author MARIANA
 */
@Entity
@Table
@NamedQueries({
   //@NamedQuery(name = "Perfil.findAll", query = "SELECT p FROM Perfil p")
    @NamedQuery(name = "Perfil.findIdPerfil", query = "SELECT p FROM Perfil p WHERE p.idPerfil = :idPerfil")
//    @NamedQuery(name = "Perfil.findDescripcion", query = "SELECT p FROM Perfil p WHERE p.descripcion = :descripcion"),
      /*@NamedQuery(name = "Perfil.findEstado", query = "SELECT p FROM Perfil p WHERE p.estado = :estado")*/})
public class Perfil implements Serializable {
    
    @Id
    private String idPerfil;
    private String nombre;
    private String descripcion;
    private boolean estado;
   
    
    
    @OneToMany(mappedBy = "perfil")
    private List<Permiso> permiso;
    
    public String getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(String id) {
        this.idPerfil = id;
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
     * @return the estado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerfil != null ? idPerfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perfil)) {
            return false;
        }
        Perfil other = (Perfil) object;
        if ((this.idPerfil == null && other.idPerfil != null) || (this.idPerfil != null && !this.idPerfil.equals(other.idPerfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.edu.utn.frm.subversion.inmobiliaria.entidades.Perfil[ idPerfil=" + idPerfil + " ]";
    }

    public List<Permiso> getPermiso() {
        return permiso;
    }

    public void setPermiso(List<Permiso> permiso) {
        this.permiso = permiso;
    }
    
}
