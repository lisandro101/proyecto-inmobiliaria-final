/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package expertos;

import entidades.AnalisisCrediticio;
import entidades.Cliente;
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
public class AnalisisCrediticioSessionBean extends AbstractSession<AnalisisCrediticio> {

    @PersistenceContext(unitName = "ar.edu.utn.frm.subversion.inmobiliaria-ejbPU")
    EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnalisisCrediticioSessionBean() {
        super(AnalisisCrediticio.class);
    }

    /**
     * Devuelve "null" en caso de que no exista un analisis crediticio asociado al cliente o que el 
     * último analisis crediticio este "desaprobado".
     * Para saber cual es el último analisis crediticio se utiliza la fecha de actualización de Veraz
     * @param cliente
     * @return 
     */
    public AnalisisCrediticio obtenerUltimoAnalisisCrediticio(Cliente cliente) {
        AnalisisCrediticio analisisCred;
        Query consulta = em.createNamedQuery("AnalisisCrediticio.findByCliente");
        consulta.setParameter("cliente", cliente);
        try {
            analisisCred = (AnalisisCrediticio) consulta.getResultList().get(0);
        } catch (Exception e) {
            System.out.println("--> Error al obtener analisis crediticio válido:" + e.toString());
            analisisCred = null;
        }
        return analisisCred;
    }
}
