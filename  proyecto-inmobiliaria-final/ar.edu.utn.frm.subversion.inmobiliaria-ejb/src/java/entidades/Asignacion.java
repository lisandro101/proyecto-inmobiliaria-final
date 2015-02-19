/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

/**
 *
 * @author Dario
 */
@Entity
@NamedQueries({
   @NamedQuery(name = "Asignacion.buscarPorId", query = "SELECT a FROM "
      + "Asignacion a WHERE a.id like :idAsignacion AND a.activo = true"),
   @NamedQuery(name = "Asignacion.buscarPorUsuario", query = "SELECT a FROM "
      + "Asignacion a WHERE a.usuario.nombreUsuario like :nombre AND a.activo = true" ),
   @NamedQuery(name = "Asignacion.buscarPorPerfil", query = "SELECT a FROM "
      + "Asignacion a WHERE a.perfil.idPerfil like :idPerfil AND a.activo = true")})
public class Asignacion implements Serializable {
    
    @Id
    private String id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDesde;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaHasta;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Perfil perfil;
    private boolean activo;
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignacion)) {
            return false;
        }
        Asignacion other = (Asignacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Asignacion[ id=" + id + " ]";
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechadesde) {
        this.fechaDesde = fechadesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
   
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
}
