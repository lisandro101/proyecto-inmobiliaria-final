/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import entidades.Asignacion;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

/**
 *
 * @author Dario
 */
@Stateless
@LocalBean
public class AsignacionSessionBean extends AbstractSession<Asignacion> {

    @PersistenceContext(unitName = "ar.edu.utn.frm.subversion.inmobiliaria-ejbPU")
    EntityManager em;
    @Resource(name="jdbc/inmobiliaria")
    private DataSource dataSource;
    
    public AsignacionSessionBean(){
        super(Asignacion.class);
    } 

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Asignacion buscarPorNombreUsuario(String nombre){
        Asignacion asignacion= null;
        Query consulta = em.createNamedQuery("Asignacion.buscarPorUsuario");
        consulta.setParameter("nombre",nombre);
        try{
            asignacion = (Asignacion) consulta.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
        return asignacion;
    }
    
    public List<Asignacion> buscarPorCoincidencia(String nombre){
        List<Asignacion> asignacion= null;
        Query consulta = em.createNamedQuery("Asignacion.buscarPorUsuario");
        consulta.setParameter("nombre","%"+nombre+"%");
        try{
            asignacion = consulta.getResultList();
        }catch(NoResultException e){
            return null;
        }
        return asignacion;
    }

    public Asignacion buscarPorId(String id) {
        Asignacion asignacion = null;
        Query consulta = em.createNamedQuery("Asignacion.buscarPorId");
        consulta.setParameter("idAsignacion",id);
        try{
            asignacion = (Asignacion) consulta.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
        return asignacion;
    }

    public boolean buscarUsoDePerfil(String idPerfil) {
        List<Asignacion> asignacion = new ArrayList<Asignacion>();
        boolean resultado = false;
        Query consulta = em.createNamedQuery("Asignacion.buscarPorPerfil");
        consulta.setParameter("idPerfil",idPerfil);
        try{
            asignacion = consulta.getResultList();
        }catch(NoResultException e){
            resultado = false;
        }
        if(asignacion.isEmpty()){
            resultado = false;
        }else{
            resultado = true;
        }
        return resultado;
    }
    
}