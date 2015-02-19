/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import entidades.Accion;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Dario
 */
@Stateless
@LocalBean
public class AccionSessionBean extends AbstractSession<Accion> {

    @PersistenceContext(unitName = "ar.edu.utn.frm.subversion.inmobiliaria-ejbPU")
    EntityManager em;
    
    public AccionSessionBean(){
        super(Accion.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    

    
}
