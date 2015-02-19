/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import entidades.Direccion;
import entidades.Provincia;
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
 * @author mariodante
 */
@Stateless
@LocalBean
public class DireccionSessionBean extends AbstractSession<Direccion>   {

    @PersistenceContext(unitName = "ar.edu.utn.frm.subversion.inmobiliaria-ejbPU")
    private EntityManager em;
    
        
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DireccionSessionBean(){
        super(Direccion.class);
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<Provincia> buscarTodasLasProvincias()
    {
        List<Provincia> listaprovincias = new ArrayList<Provincia>(); 
        
        Query consulta = em.createNamedQuery("Provincia.buscar");
        
        try
        {
            listaprovincias = consulta.getResultList();
        }
        catch (NoResultException e)
        {
            System.out.println(" lstapro " + listaprovincias);
        }
        
        return  listaprovincias;

    }
      
}
