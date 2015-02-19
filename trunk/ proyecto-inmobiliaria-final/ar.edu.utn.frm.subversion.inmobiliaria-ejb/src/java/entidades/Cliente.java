package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table
@NamedQueries({
    //    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    //    @NamedQuery(name = "Cliente.findNroCliente", query = "SELECT c FROM Cliente c WHERE c.nroCliente = :nroCliente"),
    //    @NamedQuery(name = "Cliente.findTipo", query = "SELECT c FROM Cliente c WHERE c.tipo = :tipo"),
    //    @NamedQuery(name = "Cliente.findApellido", query = "SELECT c FROM Cliente c WHERE c.apellido = :apellido"),
    //    @NamedQuery(name = "Cliente.findNombre", query = "SELECT c FROM Cliente c WHERE c.nombre = :nombre"),
    //    @NamedQuery(name = "Cliente.findDNI", query = "SELECT c FROM Cliente c WHERE c.dni = :dni"),
    //    @NamedQuery(name = "Cliente.findCUIL", query = "SELECT c FROM Cliente c WHERE c.cuil = :cuil"),
    //    @NamedQuery(name = "Cliente.findEmail", query = "SELECT c FROM Cliente c WHERE c.email = :email"),
    //    @NamedQuery(name = "Cliente.findTelefono", query = "SELECT c FROM Cliente c WHERE c.telefono = :telefono"),
    //    @NamedQuery(name = "Cliente.findDireccion", query = "SELECT c FROM Cliente c WHERE c.direccion = :direccion"),
    //    @NamedQuery(name = "Cliente.findFechaUltimaModificacion", query = "SELECT c FROM Cliente c WHERE c.fechaUltimaModificacion = :fechaUltimaModificacion"),
    //    @NamedQuery(name = "Cliente.findFechaAlta", query = "SELECT c FROM Cliente c WHERE c.fechaAlta = :fechaAlta")

    @NamedQuery(name = "Cliente.buscarUltimoPorNumero", query = "SELECT c FROM Cliente c ORDER BY c.nroCliente DESC"),
    @NamedQuery(name = "Cliente.validar", query = "SELECT c FROM Cliente c WHERE c.dni = :dni")
   // ,@NamedQuery(name = "Cliente.buscarCliente", query = "SELECT * FROM Cliente c WHERE c.apellido LIKE :valor OR c.nombre LIKE :valor OR c.dni LIKE :valor OR c.nroCliente LIKE :valor ORDER BY c.nroCliente DESC")
})
public class Cliente implements Serializable {
    @ManyToMany(mappedBy = "garantes")
    private List<ContratoAlquiler> garantesContratoAlquiler;

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String idCliente;
    private String nroCliente;
    private String tipo;
    private String apellido;
    private String nombre;
    private String dni;
    private String cuil;
    private String email;
    private String telefono;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaAlta;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaUltimaModificacion;
    
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Direccion direccion;
    @ManyToMany(mappedBy = "clientesPropietarios")
    private List<ContratoCompraVenta> contratosCompraVenta;
   
    @ManyToMany(mappedBy = "propietarios")
    private List<ContratoAlquiler> propietariosContratoAlquiler;
    
    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String id) {
        this.idCliente = id;
    }

    /**
     * @return the nroCliente
     */
    public String getNroCliente() {
        return nroCliente;
    }

    /**
     * @param nroCliente the nroCliente to set
     */
    public void setNroCliente(String nroCliente) {
        this.nroCliente = nroCliente;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
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
     * @return the fechaUltimaModificacion
     */
    public Date getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    /**
     * @param fechaUltimaModificacion the fechaUltimaModificacion to set
     */
    public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.edu.utn.frm.subversion.inmobiliaria.entidades.Cliente[ idCliente=" + idCliente + " ]";
    }

    public String getApellidoNombre() {
        return this.getApellido() != null && this.getNombre() != null ? this.getApellido() + ", " + this.getNombre() : "";
    }

    /**
     * @return the contratosCompraVenta
     */
    public List<ContratoCompraVenta> getContratosCompraVenta() {
        return contratosCompraVenta;
    }

    /**
     * @param contratosCompraVenta the contratosCompraVenta to set
     */
    public void setContratosCompraVenta(List<ContratoCompraVenta> contratosCompraVenta) {
        this.contratosCompraVenta = contratosCompraVenta;
    }

    public List<ContratoAlquiler> getPropietariosContratoAlquiler() {
        return propietariosContratoAlquiler;
    }

    public void setPropietariosContratoAlquiler(List<ContratoAlquiler> propietariosContratoAlquiler) {
        this.propietariosContratoAlquiler = propietariosContratoAlquiler;
    }

    public List<ContratoAlquiler> getGarantesContratoAlquiler() {
        return garantesContratoAlquiler;
    }

    public void setGarantesContratoAlquiler(List<ContratoAlquiler> garantesContratoAlquiler) {
        this.garantesContratoAlquiler = garantesContratoAlquiler;
    }

}
