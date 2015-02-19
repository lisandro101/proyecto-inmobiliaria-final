/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import entidades.EstadoInmueble;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Sebastian Torres
 */
@Stateless
@LocalBean
public class EstadoInmuebleSessionBean extends AbstractSession<EstadoInmueble> {

    @PersistenceContext(unitName = "ar.edu.utn.frm.subversion.inmobiliaria-ejbPU")
    EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoInmuebleSessionBean() {
        super(EstadoInmueble.class);
    }

    public EstadoInmueble obtenerEstadoInmueble(String nombreEstado) {
        EstadoInmueble estadoInmueble;
        Query consulta = em.createNamedQuery("EstadoInmueble.findNombreEstado");
        consulta.setParameter("estado", nombreEstado);

        try {
            estadoInmueble = (EstadoInmueble) consulta.getSingleResult();
        } catch (NoResultException e) {
            estadoInmueble = null;
        }
        return estadoInmueble;
    }
}
