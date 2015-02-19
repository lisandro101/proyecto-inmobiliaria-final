package expertos;

import entidades.EstadoVisita;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Lisandro Nieto
 */
@Stateless
@LocalBean
public class EstadoVisitaSessionBean extends AbstractSession<EstadoVisita> {

    @PersistenceContext(unitName = "ar.edu.utn.frm.subversion.inmobiliaria-ejbPU")
    EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoVisitaSessionBean() {
        super(EstadoVisita.class);
    }

    public EstadoVisita obtenerEstadoVisita(String nombreEstado) {
        EstadoVisita estadoVisita;
        Query consulta = em.createQuery("SELECT c FROM EstadoVisita c WHERE c.estado = " + "'"+nombreEstado+"'");
//        consulta.setParameter("estado", nombreEstado);

        try {
            estadoVisita = (EstadoVisita) consulta.getSingleResult();
        } catch (NoResultException e) {
            estadoVisita = null;
        }
        return estadoVisita;
    }
}
