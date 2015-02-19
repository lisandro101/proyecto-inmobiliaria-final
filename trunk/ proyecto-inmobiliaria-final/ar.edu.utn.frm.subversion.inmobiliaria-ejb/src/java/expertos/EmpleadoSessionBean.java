package expertos;

import entidades.Empleado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lisandro
 */
@Stateless
public class EmpleadoSessionBean extends AbstractSession<Empleado> {

    @PersistenceContext(unitName = "ar.edu.utn.frm.subversion.inmobiliaria-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadoSessionBean() {
        super(Empleado.class);
    }
}
