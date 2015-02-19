/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import entidades.Menu;
import entidades.Permiso;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Dario
 */
@Stateless
public class PermisoSessionBean extends AbstractSession<Permiso>{

    @PersistenceContext(unitName = "ar.edu.utn.frm.subversion.inmobiliaria-ejbPU")
    EntityManager em;
    
    public PermisoSessionBean(){
        super(Permiso.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Permiso> buscarPorPerfil(String idPerfil) {
        List<Permiso> permisos = null;
        Query consulta = em.createNamedQuery("Permiso.buscarPorPerfil");
        consulta.setParameter("idPerfil", idPerfil);
        permisos = consulta.getResultList();
        
        return permisos;
    }
    
    
    
}
