package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Mariana Penesi, Lisandro Nieto
 */
@Entity
@Table(name="visita")
@NamedQueries({

    //@NamedQuery(name = "Visita.findClienteEstado", query = "SELECT t FROM Visita t WHERE t.cliente = :cliente AND t.inmueble.estadoVisita = :estadoVisita" + "select id from NOMBRE_TABLA order by id DESC limit 1"),
    @NamedQuery(name = "Visita.findEmpleado", query = "SELECT t FROM Visita t WHERE t.empleado = :empleado"),
    @NamedQuery(name = "Visita.findNroVisita", query = "SELECT t FROM Visita t WHERE t.nroVisita = :nroVisita"),
    @NamedQuery(name = "Visita.findEmpleadoEstado", query = "SELECT t FROM Visita t WHERE t.empleado.usuario.nombreUsuario = :empleado AND t.estadoVisita.estado =:estadoVisita"), 
    @NamedQuery(name = "Visita.findContactoEstado", query = "SELECT t FROM Visita t WHERE t.cliente.dni = :contacto AND t.estadoVisita.estado =:estadoVisita"),
    //@NamedQuery(name = "Visita.cambiarEstado", query = "UPDATE Visita t SET t.estadoVisita.estado = :estadoVisita WHERE t.nroVisita = :nroVisita"),
    @NamedQuery(name = "Visita.findCliente", query = "SELECT t FROM Visita t WHERE t.cliente = :cliente")
   //,@NamedQuery(name = "Visita.findVisitaEntreFechas", query = "SELECT t FROM Visita t WHERE t.FECHA BETWEEN :fechaInferior AND :fechaSuperior")
     //,@NamedQuery(name = "Trabajo.findAntiguedad", query = "SELECT t FROM Trabajo t WHERE t.antiguedad = :antiguedad")
    //@NamedQuery(name = "Trabajo.findSueldo", query = "SELECT t FROM Trabajo t WHERE t.sueldo = :sueldo")
    /*@NamedQuery(name = "Trabajo.findCUIT", query = "SELECT t FROM Trabajo t WHERE t.cuit = :cuit")*/})
public class Visita implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String idVisita;
    private String nroVisita;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private String hora;
    private int duracion;
    @ManyToOne(cascade={CascadeType.REFRESH})
    private Empleado empleado;
    @ManyToOne(cascade={CascadeType.REFRESH})
    private Inmueble inmueble;
    @ManyToOne(cascade={CascadeType.REFRESH})
    private Cliente cliente;
    @ManyToOne(cascade={CascadeType.REFRESH})
    private EstadoVisita estadoVisita;

    public Visita() {
        this.nroVisita = "";
        this.fecha = new Date();
        this.hora = "";
        this.duracion = 0;
        this.empleado = new Empleado();
        this.inmueble = new Inmueble();
        this.cliente = new Cliente();
    }

    public String getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(String id) {
        this.idVisita = id;
    }

    /**
     * @return the nroVisita
     */
    public String getNroVisita() {
        return nroVisita;
    }

    /**
     * @param nroVisita the nroVisita to set
     */
    public void setNroVisita(String nroVisita) {
        this.nroVisita = nroVisita;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * @return the duracion
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * @param duracion the duracion to set
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    /**
     * @return the Empleado
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * @param Empleado the Empleado to set
     */
    public void setEmpleado(Empleado Empleado) {
        this.empleado = Empleado;
    }

    /**
     * @return the Inmueble
     */
    public Inmueble getInmueble() {
        return inmueble == null ? new Inmueble() : this.inmueble;
    }

    /**
     * @param Inmueble the Inmueble to set
     */
    public void setInmueble(Inmueble Inmueble) {
        this.inmueble = Inmueble;
    }

    /**
     * @return the EstadoVisita
     */
    public EstadoVisita getEstadoVisita() {
        return estadoVisita;
    }

    /**
     * @param estadoVisita the EstadoVisita to set
     */
    public void setEstadoVisita(EstadoVisita estadoVisita) {
        this.estadoVisita = estadoVisita;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Visita other = (Visita) obj;
        if ((this.idVisita == null) ? (other.idVisita != null) : !this.idVisita.equals(other.idVisita)) {
            return false;
        }
        if ((this.nroVisita == null) ? (other.nroVisita != null) : !this.nroVisita.equals(other.nroVisita)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.idVisita != null ? this.idVisita.hashCode() : 0);
        hash = 29 * hash + (this.nroVisita != null ? this.nroVisita.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Visita{" + "nroVisita=" + nroVisita + ", fecha=" + fecha + ", hora=" + hora + ", duracion=" + duracion + ", Empleado=" + empleado + ", Inmueble=" + inmueble + ", Cliente=" + getCliente() + '}';
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


}
