package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Mariana, Lisandro
 */
@Entity
public class ContratoCompraVenta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String idContrato;
    private int nroContrato;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFirma;
    private float porcentajePropietario;
    private float porcentajeOtraParte;
    private double importe;
    private double importeCompraVenta;
    
    @OneToOne(cascade = {CascadeType.ALL})
    private Direccion lugarDePago; 
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Inmueble inmueble;
    @ManyToMany
    private List<Cliente> clientesPropietarios;
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Cliente clienteComprador;

    /**
     * @return the importe
     */
    public double getImporte() {
        return importe;
    }

    /**
     * @param importe the importe to set
     */
    public void setImporte(double importe) {
        this.importe = importe;
    }

    /**
     * @return the importeCompraVenta
     */
    public double getImporteCompraVenta() {
        return importeCompraVenta;
    }

    /**
     * @param importeCompraVenta the importeCompraVenta to set
     */
    public void setImporteCompraVenta(double importeCompraVenta) {
        this.importeCompraVenta = importeCompraVenta;
    }

    /**
     * @return the idContrato
     */
    public String getIdContrato() {
        return idContrato;
    }

    /**
     * @param idContrato the idContrato to set
     */
    public void setIdContrato(String idContrato) {
        this.idContrato = idContrato;
    }

    /**
     * @return the nroContrato
     */
    public int getNroContrato() {
        return nroContrato;
    }

    /**
     * @param nroContrato the nroContrato to set
     */
    public void setNroContrato(int nroContrato) {
        this.nroContrato = nroContrato;
    }

    /**
     * @return the fechaFirma
     */
    public Date getFechaFirma() {
        return fechaFirma;
    }

    /**
     * @param fechaFirma the fechaFirma to set
     */
    public void setFechaFirma(Date fechaFirma) {
        this.fechaFirma = fechaFirma;
    }

    /**
     * @return the porcentajePropietario
     */
    public float getPorcentajePropietario() {
        return porcentajePropietario;
    }

    /**
     * @param porcentajePropietario the porcentajePropietario to set
     */
    public void setPorcentajePropietario(float porcentajePropietario) {
        this.porcentajePropietario = porcentajePropietario;
    }

    /**
     * @return the porcentajeOtraParte
     */
    public float getPorcentajeOtraParte() {
        return porcentajeOtraParte;
    }

    /**
     * @param porcentajeOtraParte the porcentajeOtraParte to set
     */
    public void setPorcentajeOtraParte(float porcentajeOtraParte) {
        this.porcentajeOtraParte = porcentajeOtraParte;
    }

    /**
     * @return the inmueble
     */
    public Inmueble getInmueble() {
        return inmueble;
    }

    /**
     * @param inmueble the inmueble to set
     */
    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    /**
     * @return the clienteComprador
     */
    public Cliente getClienteComprador() {
        return clienteComprador;
    }

    /**
     * @param clienteComprador the clienteComprador to set
     */
    public void setClienteComprador(Cliente clienteComprador) {
        this.clienteComprador = clienteComprador;
    }

    /**
     * @return the lugarDePago
     */
    public Direccion getLugarDePago() {
        return lugarDePago;
    }

    /**
     * @param lugarDePago the lugarDePago to set
     */
    public void setLugarDePago(Direccion lugarDePago) {
        this.lugarDePago = lugarDePago;
    }

    /**
     * @return the clientesPropietarios
     */
    public List<Cliente> getClientesPropietarios() {
        return clientesPropietarios;
    }

    /**
     * @param clientesPropietarios the clientesPropietarios to set
     */
    public void setClientesPropietarios(List<Cliente> clientesPropietarios) {
        this.clientesPropietarios = clientesPropietarios;
    }
}
