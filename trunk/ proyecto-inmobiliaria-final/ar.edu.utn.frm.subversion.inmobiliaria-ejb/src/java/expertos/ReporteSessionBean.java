/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import java.sql.Connection;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mariodante
 */
@Stateless
@LocalBean
public class ReporteSessionBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "ar.edu.utn.frm.subversion.inmobiliaria-ejbPU")
    EntityManager em;

    
    public Connection obtenerConexion()
    {
        java.sql.Connection conexion = null;
        
       return conexion = em.unwrap(java.sql.Connection.class);

    
    }
    
}
