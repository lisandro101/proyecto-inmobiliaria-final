/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package expertos;

import entidades.Trabajo;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sebastian Torres
 */
@Stateless
@LocalBean
public class TrabajoSessionBean extends AbstractSession<Trabajo> {
    @PersistenceContext(unitName = "ar.edu.utn.frm.subversion.inmobiliaria-ejbPU")
    EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TrabajoSessionBean() {
        super(Trabajo.class);
    }
    
}
