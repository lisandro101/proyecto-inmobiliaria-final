
package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table
@NamedQueries({
    @NamedQuery(name = "Inmueble.findAll", query = "SELECT i FROM Inmueble i")
//    @NamedQuery(name = "Inmueble.findIdInmueble", query = "SELECT i FROM Inmueble i WHERE i.idInmueble = :idInmueble"),
//    @NamedQuery(name = "Inmueble.findCodInmueble", query = "SELECT i FROM Inmueble i WHERE i.codInmueble = :codInmueble"),
//    @NamedQuery(name = "Inmueble.findSuperficieTotal", query = "SELECT i FROM Inmueble i WHERE i.superficieTotal = :superficieTotal"),
//    @NamedQuery(name = "Inmueble.findSuperficieCubierta", query = "SELECT i FROM Inmueble i WHERE i.superficieCubierta = :superficieCubierta"),
//    @NamedQuery(name = "Inmueble.findPrecioPesos", query = "SELECT i FROM Inmueble i WHERE i.precioPesos = :precioPesos"),
//    @NamedQuery(name = "Inmueble.findAnioConstruccion", query = "SELECT i FROM Inmueble i WHERE i.anioConstruccion = :anioConstruccion"),
//    @NamedQuery(name = "Inmueble.findFotos", query = "SELECT i FROM Inmueble i WHERE i.fotos = :fotos"),
//    @NamedQuery(name = "Inmueble.findNroPlantas", query = "SELECT i FROM Inmueble i WHERE i.nroPlantas = :nroPlantas"),
//    @NamedQuery(name = "Inmueble.findImporteMensualidad", query = "SELECT i FROM Inmueble i WHERE i.importeMensualidad = :importeMensualidad"),
//    @NamedQuery(name = "Inmueble.findDireccion", query = "SELECT i FROM Inmueble i WHERE i.direccion = :direccion"),
//    @NamedQuery(name = "Inmueble.findCondicion", query = "SELECT i FROM Inmueble i WHERE i.condicion = :condicion"),
//    @NamedQuery(name = "Inmueble.findDetallesServicios", query = "SELECT i FROM Inmueble i WHERE i.detallesServicios = :detallesServicios"),
//    @NamedQuery(name = "Inmueble.findTipoInmueble", query = "SELECT i FROM Inmueble i WHERE i.tipoInmueble = :tipoInmueble"),
//    @NamedQuery(name = "Inmueble.findClientes", query = "SELECT i FROM Inmueble i WHERE i.clientes = :clientes"),
//    @NamedQuery(name = "Inmueble.findDetallesCaracteristicas", query = "SELECT i FROM Inmueble i WHERE i.detallesCaracteristicas = :detallesCaracteristicas"),
//    ,@NamedQuery(name = "Inmueble.findEstadoInmueble", query = "SELECT i FROM Inmueble i WHERE i.historicosEstadosInmuebles.estadoInmueble = :estadoInmueble")
})
public class Inmueble implements Serializable {
    
   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   private String idInmueble;
   private String codInmueble;
   @Temporal(javax.persistence.TemporalType.DATE)
   private Date fechaAlta;
   private String superficieTotal;
   private String superficieCubierta;
   private String precioPesos;
   private int anioConstruccion;
   private String fotos;
   private int nroPlantas;
   private double importeMensualidad;
   @OneToOne(cascade = {CascadeType.ALL})
   private Direccion direccion;
   @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval=true)
   private List<DetalleServicio> detallesServicios;
   @ManyToOne(cascade={CascadeType.REFRESH})                                                                     
   private TipoInmueble tipoInmueble;                                            
   @OneToMany(cascade = {CascadeType.ALL})
   private List<Cliente> propietarios;
   @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval=true)
   private List<DetalleCaracteristica> detallesCaracteristicas;
   @OneToMany(cascade = {CascadeType.ALL})                                                                   
   private List<HistoricoEstadoInmueble> historicosEstadosInmuebles;  


    public Inmueble() {
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdInmueble() != null ? getIdInmueble().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inmueble)) {
            return false;
        }
        Inmueble other = (Inmueble) object;
        if ((this.getIdInmueble() == null && other.getIdInmueble() != null) || (this.getIdInmueble() != null && !this.idInmueble.equals(other.idInmueble))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return (this.tipoInmueble != null ? this.tipoInmueble.getNombreTipo() : "") + " # " + (this.direccion != null ? this.direccion.toString() : "");
    }

    /**
     * @return the idInmueble
     */
    public String getIdInmueble() {
        return idInmueble;
    }

    /**
     * @param idInmueble the idInmueble to set
     */
    public void setIdInmueble(String idInmueble) {
        this.idInmueble = idInmueble;
    }

    /**
     * @return the codInmueble
     */
    public String getCodInmueble() {
        return codInmueble;
    }

    /**
     * @param codInmueble the codInmueble to set
     */
    public void setCodInmueble(String codInmueble) {
        this.codInmueble = codInmueble;
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

    /**
     * @return the superficieTotal
     */
    public String getSuperficieTotal() {
        return superficieTotal;
    }

    /**
     * @param superficieTotal the superficieTotal to set
     */
    public void setSuperficieTotal(String superficieTotal) {
        this.superficieTotal = superficieTotal;
    }

    /**
     * @return the superficieCubierta
     */
    public String getSuperficieCubierta() {
        return superficieCubierta;
    }

    /**
     * @param superficieCubierta the superficieCubierta to set
     */
    public void setSuperficieCubierta(String superficieCubierta) {
        this.superficieCubierta = superficieCubierta;
    }

    /**
     * @return the precioPesos
     */
    public String getPrecioPesos() {
        return precioPesos;
    }

    /**
     * @param precioPesos the precioPesos to set
     */
    public void setPrecioPesos(String precioPesos) {
        this.precioPesos = precioPesos;
    }

    /**
     * @return the anioConstruccion
     */
    public int getAnioConstruccion() {
        return anioConstruccion;
    }

    /**
     * @param anioConstruccion the anioConstruccion to set
     */
    public void setAnioConstruccion(int anioConstruccion) {
        this.anioConstruccion = anioConstruccion;
    }

    /**
     * @return the fotos
     */
    public String getFotos() {
        return fotos;
    }

    /**
     * @param fotos the fotos to set
     */
    public void setFotos(String fotos) {
        this.fotos = fotos;
    }

    /**
     * @return the nroPlantas
     */
    public int getNroPlantas() {
        return nroPlantas;
    }

    /**
     * @param nroPlantas the nroPlantas to set
     */
    public void setNroPlantas(int nroPlantas) {
        this.nroPlantas = nroPlantas;
    }

    /**
     * @return the importeMensualidad
     */
    public double getImporteMensualidad() {
        return importeMensualidad;
    }

    /**
     * @param importeMensualidad the importeMensualidad to set
     */
    public void setImporteMensualidad(double importeMensualidad) {
        this.importeMensualidad = importeMensualidad;
    }

    /**
     * @return the direccion
     */
    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the detalleServicios
     */
    public List<DetalleServicio> getDetallesServicios() {
        return detallesServicios;
    }

    /**
     * @param detalleServicios the detalleServicios to set
     */
    public void setDetallesServicios(List<DetalleServicio> detallesServicios) {
        this.detallesServicios = detallesServicios;
    }

    /**
     * @return the tipoInmueble
     */
    public TipoInmueble getTipoInmueble() {
        return tipoInmueble;
    }

    /**
     * @param tipoInmueble the tipoInmueble to set
     */
    public void setTipoInmueble(TipoInmueble tipoInmueble) {
        this.tipoInmueble = tipoInmueble;
    }

    /**
     * @return the detalleCaracteristicas
     */
    public List<DetalleCaracteristica> getDetallesCaracteristicas() {
        return detallesCaracteristicas;
    }

    /**
     * @param detalleCaracteristicas the detalleCaracteristicas to set
     */
    public void setDetallesCaracteristicas(List<DetalleCaracteristica> detallesCaracteristicas) {
        this.detallesCaracteristicas = detallesCaracteristicas;
    }

    /**
     * @return the historicosEstadoInmueble
     */
    public List<HistoricoEstadoInmueble> getHistoricosEstadosInmuebles() {
        return historicosEstadosInmuebles;
    }

    /**
     * @param historicosEstadoInmueble the historicosEstadoInmueble to set
     */
    public void setHistoricosEstadosInmuebles(List<HistoricoEstadoInmueble> historicosEstadosInmuebles) {
        this.historicosEstadosInmuebles = historicosEstadosInmuebles;
    }

    public void addDetallesServicio(List<Servicio> servicios) {
        DetalleServicio det;
        for (Servicio servicio : servicios) {
            det = new DetalleServicio();
            det.setServicio(servicio);
            detallesServicios.add(det);
        }
    }

    public void setDetallesServicio(List<Servicio> servicios) {
        List<DetalleServicio> detas = new ArrayList<DetalleServicio>();
        DetalleServicio det;
        for (Servicio servicio : servicios) {
            det = new DetalleServicio();
            det.setServicio(servicio);
            det.setPublicar(true);
            detas.add(det);
        }
        detallesServicios = detas;
    }

    public List<Servicio> getServicios() {
        List<Servicio> sers = new ArrayList<Servicio>();
        for (DetalleServicio deta : this.getDetallesServicios()) {
            sers.add(deta.getServicio());
        }
        return sers;
    }

    public void setDetallesCaracteristica(List<Caracteristica> caracteristicas) {
        List<DetalleCaracteristica> detas = new ArrayList<DetalleCaracteristica>();
        DetalleCaracteristica det;
        for (Caracteristica ca : caracteristicas) {
            det = new DetalleCaracteristica();
            det.setCaracteristica(ca);
            det.setPublicar(true);
            det.setCantidad(1);
            det.setPuntuacion(3);

            detas.add(det);
        }
        detallesCaracteristicas = detas;
    }

//    public List<Caracteristica> getCaracteristicas() {
//        List<Caracteristica> cars = new ArrayList<Caracteristica>();
//        for (DetalleCaracteristica deta : this.getDetallesCaracteristicas()) {
//            cars.add(deta.getCaracteristica());
//        }
//        return cars;
//    }

    /**
     * @return the propietarios
     */
    public List<Cliente> getPropietarios() {
        return propietarios;
    }

    /**
     * @param propietarios the propietarios to set
     */
    public void setPropietarios(List<Cliente> propietarios) {
        this.propietarios = propietarios;
    }
    
    public EstadoInmueble getUltimoEstadoInmueble(){
        return historicosEstadosInmuebles.get(historicosEstadosInmuebles.size()-1).getEstadoInmueble();
    }
    
}
