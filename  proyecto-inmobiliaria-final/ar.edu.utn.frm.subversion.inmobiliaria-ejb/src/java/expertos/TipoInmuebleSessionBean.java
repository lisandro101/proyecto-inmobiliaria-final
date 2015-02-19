/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import entidades.TipoInmueble;
import entidades.TipoMedio;
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
public class TipoInmuebleSessionBean extends AbstractSession<TipoInmueble> {
    @PersistenceContext(unitName = "ar.edu.utn.frm.subversion.inmobiliaria-ejbPU")
    EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoInmuebleSessionBean() {
        super(TipoInmueble.class);
    }
}
