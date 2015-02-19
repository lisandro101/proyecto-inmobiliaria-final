/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import entidades.ContratoAlquiler;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sebastian Torres
 */
@Stateless
public class ContratoAlquilerSessionBean extends AbstractSession<ContratoAlquiler> {
    @PersistenceContext(unitName = "ar.edu.utn.frm.subversion.inmobiliaria-ejbPU")
    EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContratoAlquilerSessionBean() {
        super(ContratoAlquiler.class);
    }

    public  List<ContratoAlquiler> validarExistenciaContratoAlquiler(int nroContrato) {
    
         List<ContratoAlquiler> todos = this.findAll();
        
        List<ContratoAlquiler> resultado = null;
        
         if (todos != null && !todos.isEmpty()) 
         {
             if(nroContrato>0 )
             {
                 for (int i = 0; i < todos.size(); i++) {
                    
                     ContratoAlquiler cont = todos.get(i);
                                        
                     if(cont.getNroContrato()== nroContrato)
                     {
                         if(resultado==null)
                         {
                             resultado=new ArrayList<ContratoAlquiler>();
                         }
                         
                         resultado.add(cont);
                     }
                 }
             }
         }
         
        return resultado;
        
   
        
    }

    
    
}
