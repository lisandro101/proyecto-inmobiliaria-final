/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import entidades.Menu;
import entidades.Permiso;
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
public class MenuSessionBean extends AbstractSession<Menu> {
    @PersistenceContext(unitName = "ar.edu.utn.frm.subversion.inmobiliaria-ejbPU")
    private EntityManager em;
    
        
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuSessionBean(){
        super(Menu.class);
    }
    
    
    
    public List<Menu> buscarMenu()
    {
        return hardMenu();
    }

   
    
//    
//     Add business logic below. (Right-click in editor and choose
//     "Insert Code > Add Business Method")

    private List<Menu> hardMenu() {
        
        Menu uno;
        Menu dos;
        Menu tres;
        Menu cuatro;

                
        uno = new Menu();
        dos =new  Menu();
        tres= new Menu();
        cuatro= new Menu();
        
        uno.setIdMenu("Uno");
        uno.setNombre("Usuarios");
        uno.setNroMenu(1);
        uno.setVinculo("/visita/Create.xhtml");
        
        
        
    //insert into menu values ('Uno','Usuarios',1,0,null);
        //insert into menu values ('Dos','Inmuebles',2,1,null);
    //insert into menu values ('tres','Clientes',3,0,null);
    //insert into menu values ('cuatro','visitas',5,,null);
       
        dos.setIdMenu("Dos");
        dos.setNombre("Inmuebles");
        dos.setNroMenu(2);
        
        tres.setIdMenu("tres");
        tres.setNombre("Cientes");
        tres.setNroMenu(3);
        
        cuatro.setIdMenu("cuatro");
        cuatro.setNombre("visitas");
        cuatro.setNroMenu(5);
        
        List<Menu> lista = new ArrayList<Menu>();
        
        lista.add(cuatro);
//        dos.setMenu(lista);
        lista.add(tres);
  //      uno.setMenu(lista);
       
        em.merge(tres);
        em.merge(cuatro);
        em.merge(uno);
        em.merge(dos);
        
        
        /*lista = new ArrayList<Menu>();
        
        lista.add(uno);
        lista.add(dos);
        lista.add(tres);
        lista.add(cuatro);*/
        
        return lista;
    }
    
    public List<Menu> buscarMenu2()
    {
        List<Menu> lista = new ArrayList<Menu>(); 
        
        Query consulta = em.createNamedQuery("Menu.buscar");
        
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
    
    public List<Menu> buscarPorPerfil(String idPerfil){
        List<Menu> menues = null;
        Query consulta = em.createNamedQuery("Menu.buscarPorPerfil");
        consulta.setParameter("idPerfil", idPerfil);
        menues = consulta.getResultList();
        
        return menues;
    }
    
    public List<Menu> buscarPorPerfilAsignado(String idPerfil){
        List<Menu> menues = null;
        Query consulta = em.createNamedQuery("Menu.buscarPorPerfilAsignado");
        consulta.setParameter("idPerfil", idPerfil);
        menues = consulta.getResultList();
        
        return menues;
    }
}
