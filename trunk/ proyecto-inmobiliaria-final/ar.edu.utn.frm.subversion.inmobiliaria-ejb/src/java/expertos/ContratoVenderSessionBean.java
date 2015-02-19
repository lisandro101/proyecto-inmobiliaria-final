package expertos;

import entidades.ContratoCompraVenta;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lisandro Nieto
 */
@Stateless
public class ContratoVenderSessionBean extends AbstractSession<ContratoCompraVenta> {
    @PersistenceContext(unitName = "ar.edu.utn.frm.subversion.inmobiliaria-ejbPU")
    EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContratoVenderSessionBean() {
        super(ContratoCompraVenta.class);
    }

       
     public  List<ContratoCompraVenta> validarExistenciaContratoVenta(int nroContrato) {
    
         List<ContratoCompraVenta> todos = this.findAll();
        
        List<ContratoCompraVenta> resultado = null;
        
         if (todos != null && !todos.isEmpty()) 
         {
             if(nroContrato>0 )
             {
                 for (int i = 0; i < todos.size(); i++) {
                    
                     ContratoCompraVenta cont = todos.get(i);
                                        
                     if(cont.getNroContrato()== nroContrato)
                     {
                         if(resultado==null)
                         {
                             resultado=new ArrayList<ContratoCompraVenta>();
                         }
                         
                         resultado.add(cont);
                     }
                 }
             }
         }
         
        return resultado;
        
        
        
    }

    
}
