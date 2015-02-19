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
@Table(name="usuario")
@NamedQueries({
    //@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
   // @NamedQuery(name = "Usuario.findIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario LIKE :idUsuario"),
    //@NamedQuery(name = "Usuario.findNombre", query = "SELECT u FROM Usuario u WHERE u.nombre LIKE :nombre"),
    //@NamedQuery(name = "Usuario.findApellido", query = "SELECT u FROM Usuario u WHERE u.apellido LIKE :apellido"),
    //@NamedQuery(name = "Usuario.findNombreUsuario", query = "SELECT u FROM Usuario u WHERE u.nombreUsuario LIKE :nombreUsuario"),
    //@NamedQuery(name = "Usuario.findContrasenia", query = "SELECT u FROM Usuario u WHERE u.contrasenia LIKE :contrasenia"),
    //@NamedQuery(name = "Usuario.findEmail", query = "SELECT u FROM Usuario u WHERE u.email LIKE :email"),
    @NamedQuery(name = "Usuario.validar", query = "SELECT u FROM Usuario u WHERE u.nombreUsuario LIKE :nombreUsuario AND u.nombre LIKE :nombre"),
    @NamedQuery(name = "Usuario.buscarPorNombreUsuario", query = "SELECT u FROM Usuario u WHERE u.nombreUsuario LIKE :nombreUsuario"),
    @NamedQuery(name = "Usuario.login", query = "SELECT u FROM Usuario u WHERE u.nombreUsuario LIKE :nombreUsuario And u.contrasenia LIKE :contrasenia")
    /*@NamedQuery(name = "Usuario.findPerfil", query = "SELECT u FROM Usuario u WHERE u.perfil = :perfil")*/})
public class Usuario implements Serializable {
   
    @Id
    private String idUsuario;
    private String nombre;
    private String apellido;
    private String nombreUsuario;
    private String contrasenia;
    private String email;
    private boolean claveReseteada = false;
    
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String id) {
        this.idUsuario = id;
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
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @return the contrasenia
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * @param contrasenia the contrasenia to set
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
        
    public String getApellidoNombre()
    {
        return this.getApellido() != null && this.getNombre() != null ? this.getApellido() + ", " + this.getNombre() : "";
    }
  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.edu.utn.frm.subversion.inmobiliaria.entidades.Usuario[ idUsuario=" + idUsuario + " ]";
    }

    public boolean isClaveReseteada() {
        return claveReseteada;
    }

    public void setClaveReseteada(boolean claveReseteada) {
        this.claveReseteada = claveReseteada;
    }
    
}
