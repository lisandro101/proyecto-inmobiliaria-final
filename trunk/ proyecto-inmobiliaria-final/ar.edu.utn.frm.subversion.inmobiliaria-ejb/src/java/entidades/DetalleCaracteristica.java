/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author MARIANA
 */
@Entity
@Table
//@NamedQueries({
//    @NamedQuery(name = "DetalleCaracteristica.findAll", query = "SELECT dc FROM DetalleCaracteristica dc"),
//    @NamedQuery(name = "DetalleCaracteristica.findIdDetalleCaracteristica", query = "SELECT dc FROM DetalleCaracteristica dc WHERE dc.idDetalleCaracteristica = :idDetalleCaracteristica"),
//    @NamedQuery(name = "DetalleCaracteristica.findCantidad", query = "SELECT dc FROM DetalleCaracteristica dc WHERE dc.cantidad = :cantidad"),
//    @NamedQuery(name = "DetalleCaracteristica.findPuntuacion", query = "SELECT dc FROM DetalleCaracteristica dc WHERE dc.puntuacion = :puntuacion")})
public class DetalleCaracteristica implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String idDetalleCaracteristica;
    private int cantidad;
    private boolean publicar;
    @ManyToOne(cascade={CascadeType.REFRESH}) 
    private Caracteristica caracteristica;

    private int puntuacion;
    
    public String getIdDetalleCaracteristica() {
        return idDetalleCaracteristica;
    }

    public void setIdDetalleCaracteristica(String id) {
        this.idDetalleCaracteristica = id;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the publicar
     */
    public boolean isPublicar() {
        return publicar;
    }

    /**
     * @param publicar the publicar to set
     */
    public void setPublicar(boolean publicar) {
        this.publicar = publicar;
    }

    /**
     * @return the Caracteristica
     */
    public Caracteristica getCaracteristica() {
        return caracteristica;
    }

    /**
     * @param Caracteristica the Caracteristica to set
     */
    public void setCaracteristica(Caracteristica caracteristica) {
        this.caracteristica = caracteristica;
    }
    
    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleCaracteristica != null ? idDetalleCaracteristica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleCaracteristica)) {
            return false;
        }
        DetalleCaracteristica other = (DetalleCaracteristica) object;
        if ((this.idDetalleCaracteristica == null && other.idDetalleCaracteristica != null) || (this.idDetalleCaracteristica != null && !this.idDetalleCaracteristica.equals(other.idDetalleCaracteristica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.edu.utn.frm.subversion.inmobiliaria.entidades.DetalleCaracteristica[ idDetalleCaracteristica=" + idDetalleCaracteristica + " ]";
    }


    
}
