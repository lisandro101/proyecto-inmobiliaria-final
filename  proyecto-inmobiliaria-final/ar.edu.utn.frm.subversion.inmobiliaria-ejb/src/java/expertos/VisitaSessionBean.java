package expertos;

import entidades.Cliente;
import entidades.EstadoVisita;
import entidades.Inmueble;
import entidades.Visita;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author Lisandro
 */
@Stateless
public class VisitaSessionBean extends AbstractSession<Visita> {

    @PersistenceContext(unitName = "ar.edu.utn.frm.subversion.inmobiliaria-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VisitaSessionBean() {
        super(Visita.class);
    }

    /**
     * 
     * @param cliente
     * @param estadoInmueble Estados posibles: "En venta", "Vendido, "En alquiler", "Alquilado"
     * @return 
     */
    public List<Visita> obtenerVisitas(Cliente cliente, String estadoInmueble) {
        List<Visita> visitasRealizadas;
        List<Visita> resultado = null;
        Query consulta = em.createNamedQuery("Visita.findCliente");
        
        consulta.setParameter("cliente", cliente);
        resultado = new ArrayList<Visita>();
        try {
            visitasRealizadas = (List<Visita>) consulta.getResultList();
            if (visitasRealizadas != null && !visitasRealizadas.isEmpty()) {              
                for (Visita visita : visitasRealizadas) {
                    if (InmuebleSessionBean.obtenerUltimoHistoricoEstadoInmueble(visita.getInmueble().getHistoricosEstadosInmuebles()).getEstadoInmueble().getEstado().trim().equals(estadoInmueble.trim())) {
                        resultado.add(visita);
                    }
                }
            }
        } catch (NoResultException e) {
            resultado = null;
        }
        return resultado;
    }

    public List<Visita> obtenerVisitasPendientes(String empleado,String estadoVisita) {
        List<Visita> resultado = null;
        Query consulta = em.createNamedQuery("Visita.findEmpleadoEstado");
        consulta.setParameter("empleado", empleado);
        consulta.setParameter("estadoVisita", estadoVisita);
        try {
            resultado = (List<Visita>) consulta.getResultList();
        } catch (NoResultException e) {
            resultado = null;
        }
        return resultado;
    }

    public List<Visita> obtenerVisitasPorContacto(String dniContacto, String estado) {
        List<Visita> resultado = null;
        Query consulta = em.createNamedQuery("Visita.findContactoEstado");
        consulta.setParameter("contacto", dniContacto);
        consulta.setParameter("estadoVisita", estado);
        try {
            resultado = (List<Visita>) consulta.getResultList();
        } catch (NoResultException e) {
            resultado = null;
        }
        return resultado;
    }

    public boolean cambiarEstadoVisita(String nroVisitaString, EstadoVisita estado) {
        boolean resultado = false;
        Visita visita = null;
        if(estado != null){
            Query consulta = em.createNamedQuery("Visita.findNroVisita");
            consulta.setParameter("nroVisita", nroVisitaString);
            visita = (Visita) consulta.getSingleResult();
        }
        if(visita != null){
            visita.setEstadoVisita(estado);
            em.merge(visita);
            resultado = true;
        }
        
        return resultado;
    }

    public Inmueble validarInmuebleConVisitas(String codInmueble) {
        
        List<Visita> todos = this.findAll();
        
        Inmueble resultado = null;
        
         if (todos != null && !todos.isEmpty()) 
         {
             if(codInmueble !=null && !codInmueble.equals("") )
             {
                 for (int i = 0; i < todos.size(); i++) {
                    
                     Visita vis = todos.get(i);
                     
                     if(vis.getInmueble().getCodInmueble().endsWith(codInmueble))
                     {
                        resultado=vis.getInmueble();
                     }
                 }
             }
         }
         
        return resultado;
        
    }

    public Cliente validarClienteConVisitas(Cliente cliente) {

        List<Visita> todos = this.findAll();
        
        Cliente resultado = null;
        
         if (todos != null && !todos.isEmpty()) 
         {
             if(cliente!=null)
             {
                 for (int i = 0; i < todos.size(); i++) {
                    
                     Visita vis = todos.get(i);
                     
                     if(vis.getCliente().getIdCliente().equals(cliente.getIdCliente()))
                     {
                        resultado=vis.getCliente();
                     }
                 }
             }
         }
         
        return resultado;
    }

    public List<Visita> validarVisitasEntreFechas(Date fecInf, Date fecSup) {
        
        List<Visita> todos = this.findAll();
        
        List<Visita> resultado = null;
        
         if (todos != null && !todos.isEmpty()) 
         {
             if(fecInf!=null && fecSup!=null)
             {
                for (int i = 0; i < todos.size(); i++) {
                    
                     Visita vis = todos.get(i);
                     
                     long  from= fecInf.getTime();
                     
                     long to =fecSup.getTime();
                     
                     long check=vis.getFecha().getTime();

                      if((check-from)>0 && (to-check)>0)
                      {
                            if(resultado==null)
                            {
                            resultado= new ArrayList<Visita>();
                            }
                            resultado.add(vis);
                      }

                 }
             }
         }
         
          return resultado;
    }
}