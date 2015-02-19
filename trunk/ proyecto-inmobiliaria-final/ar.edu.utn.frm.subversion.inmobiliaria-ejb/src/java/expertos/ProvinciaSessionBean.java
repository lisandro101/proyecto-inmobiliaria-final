/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import entidades.Departamento;
import entidades.Provincia;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mariodante
 */
@Stateless
@LocalBean
public class ProvinciaSessionBean extends AbstractSession<Provincia> {
    @PersistenceContext(unitName = "ar.edu.utn.frm.subversion.inmobiliaria-ejbPU")
    private EntityManager em;
    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProvinciaSessionBean(){
        super(Provincia.class);
    }

    
    
    public List<Provincia> buscarProvinciasHard()
    {
        return hardProvincias();
    }

   
    private List<Provincia> hardProvincias() {
        
        Provincia uno;
        Provincia dos;
        Provincia tres;
        
        uno= new Provincia();
        dos= new Provincia();
        tres= new Provincia();
        
        uno.setIdProvincia("1");
        uno.setNombreProvincia("Mendoza2");
        uno.setAbreviatura("MZA");
        uno.setDepartamentos(null);
        
        dos.setIdProvincia("2");
        dos.setNombreProvincia("San Juan2");
        dos.setAbreviatura("SJA");
        dos.setDepartamentos(null);
        
        tres.setIdProvincia("3");
        tres.setNombreProvincia("Cordoba2");
        tres.setAbreviatura("CBA");
        tres.setDepartamentos(null);
        
        List<Provincia> lista = new ArrayList<Provincia>();
        
        lista.add(uno);
//        dos.setMenu(lista);
        lista.add(dos);
        lista.add(tres);
  //      uno.setMenu(lista);
       
        em.merge(uno);
        em.merge(dos);
        em.merge(tres);
        
        return lista;
    }
    
    public List<Provincia> buscarProvincias()
    {
        List<Provincia> lista = new ArrayList<Provincia>(); 
        
        Query consulta = em.createNamedQuery("Provincia.buscar");
        
        try
        {
            lista = consulta.getResultList();
        }
        catch (NoResultException e)
        {
            System.out.println("    " + lista);
        }
        
        return  lista;

    }
    
    public List<Departamento> buscarDepartamentosProvincia(int indiceProvincia)
    {
        
        List<Provincia> lista = new ArrayList<Provincia>(); 
        
        Query consulta = em.createNamedQuery("Provincia.buscar");
        
        try
        {
            lista = consulta.getResultList();
            
        }
        catch (NoResultException e)
        {
            System.out.println("    " + lista);
        }

        
        List<Departamento> listaDeptos = new ArrayList<Departamento>(); 
        
        for (Provincia pro : lista) {
            
            int idProvincia = Integer.parseInt(pro.getIdProvincia());
            
            
            if(indiceProvincia==idProvincia )
            {
                listaDeptos=pro.getDepartamentos();
            }
        }
        
        
        return  listaDeptos;
        
    }
    
}
