package expertos;

import entidades.Contacto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lisandro
 */
@Stateless
public class ContactoSessionBean extends AbstractSession<Contacto> {

    @PersistenceContext(unitName = "ar.edu.utn.frm.subversion.inmobiliaria-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContactoSessionBean() {
        super(Contacto.class);
    }
}
