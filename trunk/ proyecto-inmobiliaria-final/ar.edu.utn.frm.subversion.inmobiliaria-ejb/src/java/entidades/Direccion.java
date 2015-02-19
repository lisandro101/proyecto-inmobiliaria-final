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
//    @NamedQuery(name = "Direccion.findAll", query = "SELECT d FROM Direccion d"),
//    @NamedQuery(name = "Direccion.findIdDireccion", query = "SELECT d FROM Direccion d WHERE d.idDireccion = :idDireccion"),
//    @NamedQuery(name = "Direccion.findCalle", query = "SELECT d FROM Direccion d WHERE d.calle = :calle"),
//    @NamedQuery(name = "Direccion.findNumero", query = "SELECT d FROM Direccion d WHERE d.numero = :numero"),
//    @NamedQuery(name = "Direccion.findCasa", query = "SELECT d FROM Direccion d WHERE d.casa = :casa"),
//    @NamedQuery(name = "Direccion.findManzana", query = "SELECT d FROM Direccion d WHERE d.manzana = :manzana"),
//    @NamedQuery(name = "Direccion.findDepartamento", query = "SELECT d FROM Direccion d WHERE d.departamento = :departamento"),
//    @NamedQuery(name = "Direccion.findLocalidad", query = "SELECT d FROM Direccion d WHERE d.localidad = :localidad")})
public class Direccion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String idDireccion;
    private String nombreCalle;
    private String numero;
    private String nroPiso;
    private String nroDepartamento;
    private String observacion;
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Provincia provincia;
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Departamento departamento;
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Localidad localidad;

    public String getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(String id) {
        this.idDireccion = id;
    }

    /**
     * @return the calle
     */
    public String getNombreCalle() {
        return nombreCalle;
    }

    /**
     * @param calle the calle to set
     */
    public void setNombreCalle(String calle) {
        this.nombreCalle = calle;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the Provincia
     */
    public Provincia getProvincia() {
        return provincia;
    }

    /**
     * @param Provincia the Provincia to set
     */
    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    /**
     * @return the Departamento
     */
    public Departamento getDepartamento() {
        return departamento;
    }

    /**
     * @param Departamento the Departamento to set
     */
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    /**
     * @return the Localidad
     */
    public Localidad getLocalidad() {
        return localidad;
    }

    /**
     * @param Localidad the Localidad to set
     */
    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDireccion != null ? idDireccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Direccion)) {
            return false;
        }
        Direccion other = (Direccion) object;
        if ((this.idDireccion == null && other.idDireccion != null) || (this.idDireccion != null && !this.idDireccion.equals(other.idDireccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String result = getNombreCalle()
                + " " + getNumero()
                + (getNroPiso() != null && !getNroPiso().equals("")? (" Piso " + getNroPiso()) : "")
                + (getNroDepartamento() != null && !getNroDepartamento().equals("") ? (" Depto " + getNroDepartamento()) : "")
                + " - " + localidad.getNombreLocalidad()
                + ", " + departamento.getNombreDepartamento()
                + ", " + provincia.getNombreProvincia();

        return result;
    }
    
    public String getDireccionCorta() {
        String result = getNombreCalle()
                + " " + getNumero()
                + " " + departamento.getNombreDepartamento()
                + ", " + provincia.getNombreProvincia();

        return result;
    }

    public String getNroPiso() {
        return nroPiso;
    }

    public void setNroPiso(String nroPiso) {
        this.nroPiso = nroPiso;
    }

    public String getNroDepartamento() {
        return nroDepartamento;
    }

    public void setNroDepartamento(String nroDepartamente) {
        this.nroDepartamento = nroDepartamente;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
        }
