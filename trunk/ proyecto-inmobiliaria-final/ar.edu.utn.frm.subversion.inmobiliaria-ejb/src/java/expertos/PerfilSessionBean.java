/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import entidades.Perfil;
import entidades.Permiso;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Dario
 */
@Stateless
@LocalBean
public class PerfilSessionBean extends AbstractSession<Perfil> {
     
     @PersistenceContext(unitName = "ar.edu.utn.frm.subversion.inmobiliaria-ejbPU")
     private EntityManager em;
    
    public PerfilSessionBean(){
        super(Perfil.class);
    }
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Perfil buscarPorId(String idPerfil) {
        Perfil perfil = null;
        Query consulta = em.createNamedQuery("Perfil.findIdPerfil");
        consulta.setParameter("idPerfil", idPerfil);
        try{
            perfil = (Perfil) consulta.getSingleResult();
        } catch (NoResultException e){
             return null;
        }
        return perfil;
    }
    
}
