/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author MARIANA
 */
@Entity
@Table(name = "analisisCrediticio")
@NamedQueries({
    //@NamedQuery(name = "AnalisisCrediticio.findAll", query = "SELECT m FROM AnalisisCrediticio m"),
    //@NamedQuery(name = "AnalisisCrediticio.findByIdAnalisisCrediticio", query = "SELECT m FROM AnalisisCrediticio m WHERE m.idAnalisisCrediticio = :idAnalisisCrediticio"),
    //@NamedQuery(name = "AnalisisCrediticio.findByDeudaVeraz", query = "SELECT m FROM AnalisisCrediticio m WHERE m.deudaVeraz = :deudaVeraz"),
    //@NamedQuery(name = "AnalisisCrediticio.findByFechaActualizacionVeraz", query = "SELECT m FROM AnalisisCrediticio m WHERE m.fechaActualizacionVeraz = :fechaActualizacionVeraz"),
    //@NamedQuery(name = "AnalisisCrediticio.findByDeudaCodeme", query = "SELECT m FROM AnalisisCrediticio m WHERE m.deudaCodeme = :deudaCodeme"),
    //@NamedQuery(name = "AnalisisCrediticio.findByFechaActualizacionCodeme", query = "SELECT m FROM AnalisisCrediticio m WHERE m.fechaActualizacionCodeme = :fechaActualizacionCodeme"),
    //@NamedQuery(name = "AnalisisCrediticio.findByEstadoAnalisisCrediticio", query = "SELECT m FROM AnalisisCrediticio m WHERE m.estadoAnalisisCrediticio = :estadoAnalisisCrediticio"),
    //@NamedQuery(name = "AnalisisCrediticio.findByTrabajo", query = "SELECT m FROM AnalisisCrediticio m WHERE m.trabajo = :trabajo"),
    @NamedQuery(name = "AnalisisCrediticio.findByCliente", query = "SELECT m FROM AnalisisCrediticio m WHERE m.cliente = :cliente ORDER BY m.fechaCreacion DESC")})
public class AnalisisCrediticio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String idAnalisisCrediticio;
    private double deudaVeraz;
    private double deudaCodeme;
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private EstadoAnalisisCrediticio estadoAnalisisCrediticio;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Trabajo> trabajos;
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Cliente cliente;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCreacion;

    public String getIdAnalisisCrediticio() {
        return idAnalisisCrediticio;
    }

    public void setIdAnalisisCrediticio(String id) {
        this.idAnalisisCrediticio = id;
    }

    /**
     * @return the deudaVeraz
     */
    public double getDeudaVeraz() {
        return deudaVeraz;
    }

    /**
     * @param deudaVeraz the deudaVeraz to set
     */
    public void setDeudaVeraz(double deudaVeraz) {
        this.deudaVeraz = deudaVeraz;
    }

    /**
     * @return the deudaCodeme
     */
    public double getDeudaCodeme() {
        return deudaCodeme;
    }

    /**
     * @param deudaCodeme the deudaCodeme to set
     */
    public void setDeudaCodeme(double deudaCodeme) {
        this.deudaCodeme = deudaCodeme;
    }

    /**
     * @return the EstadoAnalisisCrediticio
     */
    public EstadoAnalisisCrediticio getEstadoAnalisisCrediticio() {
        return estadoAnalisisCrediticio;
    }

    /**
     * @param estadoAnalisisCrediticio the EstadoAnalisisCrediticio to set
     */
    public void setEstadoAnalisisCrediticio(EstadoAnalisisCrediticio estadoAnalisisCrediticio) {
        this.estadoAnalisisCrediticio = estadoAnalisisCrediticio;
    }

    public List<Trabajo> getTrabajos() {
        return trabajos;
    }

    public void setTrabajos(List<Trabajo> trabajos) {
        this.trabajos = trabajos;
    }

    /**
     * @return the Cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the Cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnalisisCrediticio != null ? idAnalisisCrediticio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnalisisCrediticio)) {
            return false;
        }
        AnalisisCrediticio other = (AnalisisCrediticio) object;
        if ((this.idAnalisisCrediticio == null && other.idAnalisisCrediticio != null) || (this.idAnalisisCrediticio != null && !this.idAnalisisCrediticio.equals(other.idAnalisisCrediticio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return estadoAnalisisCrediticio.getNombre();
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
