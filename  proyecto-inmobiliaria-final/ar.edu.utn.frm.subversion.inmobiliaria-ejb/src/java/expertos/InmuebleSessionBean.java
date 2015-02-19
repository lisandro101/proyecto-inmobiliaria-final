/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package expertos;

import entidades.HistoricoEstadoInmueble;
import entidades.Inmueble;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.PermitAll;
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
public class InmuebleSessionBean extends AbstractSession<Inmueble> {

    @PersistenceContext(unitName = "ar.edu.utn.frm.subversion.inmobiliaria-ejbPU")
    EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InmuebleSessionBean() {
        super(Inmueble.class);
    }

    @PermitAll
    public List<Inmueble> obtenerInmuebles(String nombreEstado) {
        List<Inmueble> todos = this.findAll();
        List<Inmueble> resultado = null;
        HistoricoEstadoInmueble historicoEstadoInmueble;

        if (todos != null && !todos.isEmpty()) {
            resultado = new ArrayList<Inmueble>();
            for (Inmueble inmueble : todos) {
                if (inmueble != null && inmueble.getHistoricosEstadosInmuebles() != null && !inmueble.getHistoricosEstadosInmuebles().isEmpty()) {
                    historicoEstadoInmueble = obtenerUltimoHistoricoEstadoInmueble(inmueble.getHistoricosEstadosInmuebles());
                    if (historicoEstadoInmueble.getEstadoInmueble() != null && historicoEstadoInmueble.getEstadoInmueble().getEstado().trim().equals(nombreEstado.trim())) {
                        resultado.add(inmueble);
                    }
                }
            }
        }
        return resultado;
    }

    public static HistoricoEstadoInmueble obtenerUltimoHistoricoEstadoInmueble(List<HistoricoEstadoInmueble> historicos) {
        HistoricoEstadoInmueble resultado = null;
        if (historicos != null && !historicos.isEmpty()) {
            resultado = historicos.get(0);
            for (HistoricoEstadoInmueble historicoEstadoInmueble : historicos) {
                if (historicoEstadoInmueble.getFechaIncio().after(resultado.getFechaIncio())) {
                    resultado = historicoEstadoInmueble;
                }
            }
        }
        return resultado;
    }
    
    /**
     * Veo si el inmueble ingresado posee visitas
     * @param filtro es el codigo del inmueble
     * @return el inmueble correspondiente al ingresado 
     */
    public List<Inmueble> validarInmuebleConVisitas(String codInmueble) {
        
        List<Inmueble> todos = this.findAll();
        
        
        
        return todos;
        
//        List<Inmueble> resultado = null;
//        HistoricoEstadoInmueble historicoEstadoInmueble;
//
//        if (todos != null && !todos.isEmpty()) {
//            resultado = new ArrayList<Inmueble>();
//            for (Inmueble inmueble : todos) {
//                if (inmueble != null && inmueble.getHistoricosEstadosInmuebles() != null && !inmueble.getHistoricosEstadosInmuebles().isEmpty()) {
//                    historicoEstadoInmueble = obtenerUltimoHistoricoEstadoInmueble(inmueble.getHistoricosEstadosInmuebles());
//                    if (historicoEstadoInmueble.getEstadoInmueble() != null && historicoEstadoInmueble.getEstadoInmueble().getEstado().trim().equals(nombreEstado.trim())) {
//                        resultado.add(inmueble);
//                    }
//                }
//            }
//        }
//        return resultado;
//        
//        
//        
//        
//        
//        
//        
//        
//        List<Inmueble> inmuebles;
//        Query consulta;
//        
//        if (filtro != null) {
//            String valor = "'" + filtro + "'";
//            consulta = em.createQuery("select c FROM Inmueble c where Inmueble.CODINMUEBLE="+ valor);
//
//            try {
//                inmuebles = (List<Inmueble>) consulta.getResultList();
//            } catch (NoResultException e) {
//                inmuebles = null;
//            }
//        } else {
//            inmuebles = null;
//        }
//        return inmuebles;
        
    }
}
