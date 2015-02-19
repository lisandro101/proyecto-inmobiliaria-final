 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author MARIANA
 */
@Entity
@Table(name="permiso")
@NamedQueries({
    //@NamedQuery(name = "Permiso.findIdPermiso", query = "SELECT p FROM Permiso p WHERE p.idPermiso = :idPermiso"),
    //@NamedQuery(name = "Permiso.findVer", query = "SELECT p FROM Permiso p WHERE p.Ver = :ver"),
    //@NamedQuery(name = "Permiso.findCrear", query = "SELECT p FROM Permiso p WHERE p.crear = :crear"),
    //@NamedQuery(name = "Permiso.findModificar", query = "SELECT p FROM Permiso p WHERE p.modificar = :modificar"),
    //@NamedQuery(name = "Permiso.findEliminar", query = "SELECT p FROM Permiso p WHERE p.eliminar = :eliminar"),
    //@NamedQuery(name = "Permiso.findPerfil", query = "SELECT p FROM Permiso p WHERE p.perfil = :perfil"),
    @NamedQuery(name = "Permiso.buscarPorPerfil", query = "SELECT p FROM Permiso p WHERE p.perfil.idPerfil = :idPerfil")})
public class Permiso implements Serializable {
    @ManyToOne
    private Perfil perfil;
    
    @Id
    private String idPermiso;
 
    /*private boolean crear;
    private boolean modificar;
    private boolean eliminar;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDesde;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaHasta;*/
    @ManyToOne
    private Menu menu;
    @ManyToOne
    private Accion accion;
    
    public String getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(String id) {
        this.idPermiso = id;
    }

//    /**
//     * @return the ver
//     */
//    public boolean isVer() {
//        return ver;
//    }
//
//    /**
//     * @param ver the ver to set
//     */
//    public void setVer(boolean ver) {
//        this.ver = ver;
//    }
//
//    /**
//     * @return the crear
//     */
//    public boolean isCrear() {
//        return crear;
//    }
//
//    /**
//     * @param crear the crear to set
//     */
//    public void setCrear(boolean crear) {
//        this.crear = crear;
//    }
//
//    /**
//     * @return the modificar
//     */
//    public boolean isModificar() {
//        return modificar;
//    }
//
//    /**
//     * @param modificar the modificar to set
//     */
//    public void setModificar(boolean modificar) {
//        this.modificar = modificar;
//    }
//
//    /**
//     * @return the eliminar
//     */
//    public boolean isEliminar() {
//        return eliminar;
//    }
//
//    /**
//     * @param eliminar the eliminar to set
//     */
//    public void setEliminar(boolean eliminar) {
//        this.eliminar = eliminar;
//    }
//    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPermiso != null ? idPermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permiso)) {
            return false;
        }
        Permiso other = (Permiso) object;
        if ((this.idPermiso == null && other.idPermiso != null) || (this.idPermiso != null && !this.idPermiso.equals(other.idPermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.edu.utn.frm.subversion.inmobiliaria.entidades.Permiso[ idPermiso=" + idPermiso + " ]";
    }

//    public Date getFechaDesde() {
//        return fechaDesde;
//    }
//
//    public void setFechaDesde(Date fechaDesde) {
//        this.fechaDesde = fechaDesde;
//    }
//
//    public Date getFechaHasta() {
//        return fechaHasta;
//    }
//
//    public void setFechaHasta(Date fechaHasta) {
//        this.fechaHasta = fechaHasta;
//    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Accion getAccion() {
        return accion;
    }

    public void setAccion(Accion accion) {
        this.accion = accion;
    }
     public boolean tieneMenu(){
        boolean resultado = false;
        if(this.getMenu() != null)
            resultado = true;
        
        return resultado;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

}
