/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mobile.entidades;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Dario
 */
public class DtoVisitas implements Serializable{
    private String domicilio;
    private String contacto;
    private String empleado;
    private String fecha;
    private String hora;
    private String nroVisita;
    private String estado;

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        SimpleDateFormat fechaString = new SimpleDateFormat("dd-MM-yyyy");
        this.fecha = fechaString.format(fecha);
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNroVisita() {
        return nroVisita;
    }

    public void setNroVisita(String nroVisita) {
        this.nroVisita = nroVisita;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public void serializar (String archivo) throws IOException {
        ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivo));
        salida.writeObject(this);
    }

    @Override   
    public String toString() {
        return getNroVisita() + "|" + getFecha() + "|" + getHora() + "|" + getDomicilio() + "|" + 
                getContacto() + "|"+ getEmpleado() + "|" + getEstado() + "|" + "*";
    }
    
}
