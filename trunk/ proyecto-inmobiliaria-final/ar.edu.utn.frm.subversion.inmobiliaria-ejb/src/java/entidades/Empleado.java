package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author MARIANA
 */
@Entity
@Table(name = "empleado")
@NamedQueries({
//@NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e"),
//@NamedQuery(name = "Empleado.findIdEmpleado", query = "SELECT e FROM Empleado e WHERE e.idEmpleado = :idEmpleado"),
//@NamedQuery(name = "Empleado.findNombre", query = "SELECT e FROM Empleado e WHERE e.nombre = :nombre"),
//@NamedQuery(name = "Empleado.findApellido", query = "SELECT e FROM Empleado e WHERE e.apellido = :apellido"),
//@NamedQuery(name = "Empleado.findDNI", query = "SELECT e FROM Empleado e WHERE e.dni = :dni"),
//@NamedQuery(name = "Empleado.findCUIL", query = "SELECT e FROM Empleado e WHERE e.cuil = :cuil"),
//@NamedQuery(name = "Empleado.findEmail", query = "SELECT e FROM Empleado e WHERE e.email = :email"),
//@NamedQuery(name = "Empleado.findTelefono", query = "SELECT e FROM Empleado e WHERE e.telefono = :telefono"),
//@NamedQuery(name = "Empleado.findDireccion", query = "SELECT e FROM Empleado e WHERE e.direccion = :direccion"),
    /*@NamedQuery(name = "Empleado.findFechaAlta", query = "SELECT e FROM Empleado e WHERE e.fechaAlta = :fechaAlta")*/
})
public class Empleado implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String idEmpleado;
    private String nombre;
    private String apellido;
    private String dni;
    private String cuil;
    private String email;
    private String telefono;
    @OneToOne(cascade = {CascadeType.ALL})
    private Direccion direccion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaAlta;
    @OneToOne
    private Usuario usuario;

    public Empleado() {
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String id) {
        this.idEmpleado = id;
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
    
    
    public String getApellidoNombre() {
        return this.getApellido() != null && this.getNombre() != null ? this.getApellido() + ", " + this.getNombre() : "";
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpleado != null ? idEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.idEmpleado == null && other.idEmpleado != null) || (this.idEmpleado != null && !this.idEmpleado.equals(other.idEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idEmpleado != null ? idEmpleado : "";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
