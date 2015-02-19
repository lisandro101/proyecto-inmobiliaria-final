package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author MARIANA
 */
@Entity
@Table(name = "contacto")
@NamedQueries({ 
//@NamedQuery(name = "Contacto.findAll", query = "SELECT c FROM Contacto c"),
//@NamedQuery(name = "Contacto.findByIdContacto", query = "SELECT c FROM Contacto c WHERE c.idContacto = :idContacto"),
//@NamedQuery(name = "Contacto.findByApellido", query = "SELECT c FROM Contacto c WHERE c.apellido = :apellido"),
//@NamedQuery(name = "Contacto.findByNombre", query = "SELECT c FROM Contacto c WHERE c.nombre = :nombre"),
//@NamedQuery(name = "Contacto.findByDNI", query = "SELECT c FROM Contacto c WHERE c.dni = :dni"),
//@NamedQuery(name = "Contacto.findByCuil", query = "SELECT c FROM Contacto c WHERE c.cuil = :cuil"),
//@NamedQuery(name = "Contacto.findByTelefono", query = "SELECT c FROM Contacto c WHERE c.telefono = :telefono"),
//@NamedQuery(name = "Contacto.findByEmail", query = "SELECT c FROM Contacto c WHERE c.email = :email"),
/*@NamedQuery(name = "Contacto.findByFechaAlta", query = "SELECT c FROM Contacto c WHERE c.fechaAlta = :fechaAlta")*/})
public class Contacto implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String idContacto;
    private String apellido;
    private String nombre;
    private String dni;
    private String cuil;
    private String telefono;
    private String email;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaAlta;

    public Contacto() {
    }

    public String getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(String id) {
        this.idContacto = id;
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
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @return the cuil
     */
    public String getCuil() {
        return cuil;
    }

    /**
     * @param cuil the cuil to set
     */
    public void setCuil(String cuil) {
        this.cuil = cuil;
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
    
    public String getApellidoNombre()
    {
        return this.getApellido() != null && this.getNombre() != null ? this.getApellido() + ", " + this.getNombre() : "";
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContacto != null ? idContacto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contacto)) {
            return false;
        }
        Contacto other = (Contacto) object;
        if ((this.idContacto == null && other.idContacto != null) || (this.idContacto != null && !this.idContacto.equals(other.idContacto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idContacto != null ? idContacto : "";
    }
}
