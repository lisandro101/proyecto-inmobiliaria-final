package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author MARIANA
 */
@Entity
public class ContratoAlquiler implements Serializable {
        
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String idContrato;
    private int nroContrato;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFirma;
    private float porcentajeComision;
    @ManyToOne(cascade={CascadeType.REFRESH}) 
    private Inmueble inmueble;    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFinalizacion;
    private int cantPersonas;
    private float porcentajeIncremento;
    private int periodoEntreIncremento;
    private int nroCuotas;
    private double importeTotal;
    private int diaDeCobro;
    
    @OneToOne(cascade = {CascadeType.ALL})
    private Direccion lugarDeCobro; 
    @ManyToOne(cascade={CascadeType.REFRESH}) 
    private Cliente clienteInquilino;
    
    @ManyToMany
    @JoinTable(name = "ContratoAlquiler_Garantes")
//    ,joinColumns = {@JoinColumn(name = "contratoAlquiler_idContratoAlquiler", referencedColumnName = "idContrato") },
//    inverseJoinColumns = { @JoinColumn(name = "cliente_idCliente", referencedColumnName = "idCliente") })     
    private List<Cliente> garantes;
    
    @ManyToMany
    @JoinTable(name = "ContratoAlquiler_Propietarios")
//    ,joinColumns = {@JoinColumn(name = "contratoAlquiler_idContratoAlquiler", referencedColumnName = "idContrato") },
//    inverseJoinColumns = { @JoinColumn(name = "cliente_idCliente", referencedColumnName = "idCliente") })    
    private List<Cliente> propietarios;
    
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Cuota> cuotas;
    @ManyToOne(cascade={CascadeType.REFRESH}) 
    private TipoPago tipoPago;

 
    
    
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public int getCantPersonas() {
        return cantPersonas;
    }

    public void setCantPersonas(int cantPersonas) {
        this.cantPersonas = cantPersonas;
    }

    public float getPorcentajeIncremento() {
        return porcentajeIncremento;
    }

    public void setPorcentajeIncremento(float porcentajeIncremento) {
        this.porcentajeIncremento = porcentajeIncremento;
    }

    public int getPeriodoEntreIncremento() {
        return periodoEntreIncremento;
    }

    public void setPeriodoEntreIncremento(int periodoEntreIncremento) {
        this.periodoEntreIncremento = periodoEntreIncremento;
    }

    public int getNroCuotas() {
        return nroCuotas;
    }

    public void setNroCuotas(int nroCuotas) {
        this.nroCuotas = nroCuotas;
    }

    public double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(double importeTotal) {
        this.importeTotal = importeTotal;
    }

    public int getDiaDeCobro() {
        return diaDeCobro;
    }

    public void setDiaDeCobro(int diaDeCobro) {
        this.diaDeCobro = diaDeCobro;
    }

    public Cliente getClienteInquilino() {
        return clienteInquilino;
    }

    public void setClienteInquilino(Cliente clienteInquilino) {
        this.clienteInquilino = clienteInquilino;
    }

    public List<Cuota> getCuotas() {
        return cuotas;
    }

    public void setCuotas(List<Cuota> cuotas) {
        this.cuotas= cuotas;
    }

    public TipoPago getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(TipoPago tipoPago) {
        this.tipoPago = tipoPago;
    }

    public Direccion getLugarDeCobro() {
        return lugarDeCobro;
    }

    public void setLugarDeCobro(Direccion lugarDeCobro) {
        this.lugarDeCobro = lugarDeCobro;
    }

    public String getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(String idContrato) {
        this.idContrato = idContrato;
    }

    public int getNroContrato() {
        return nroContrato;
    }

    public void setNroContrato(int nroContrato) {
        this.nroContrato = nroContrato;
    }

    public Date getFechaFirma() {
        return fechaFirma;
    }

    public void setFechaFirma(Date fechaFirma) {
        this.fechaFirma = fechaFirma;
    }

    public float getPorcentajeComision() {
        return porcentajeComision;
    }

    public void setPorcentajeComision(float porcentajeComision) {
        this.porcentajeComision = porcentajeComision;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    /**
     * @return the garantes
     */
    public List<Cliente> getGarantes() {
        return garantes;
    }

    /**
     * @param garantes the garantes to set
     */
    public void setGarantes(List<Cliente> garantes) {
        this.garantes = garantes;
    }

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
}
