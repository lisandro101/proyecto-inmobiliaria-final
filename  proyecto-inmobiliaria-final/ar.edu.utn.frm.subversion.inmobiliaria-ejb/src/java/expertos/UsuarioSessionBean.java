/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import entidades.Usuario;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Dario
 */
@Stateless
@LocalBean
public class UsuarioSessionBean extends AbstractSession<Usuario> {
    
    @PersistenceContext(unitName="ar.edu.utn.frm.subversion.inmobiliaria-ejbPU")
    EntityManager em;
        
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public UsuarioSessionBean(){
        super(Usuario.class);
    }

    public boolean validarUsuario(String nombreUsuario, String contraseña) {
        boolean resultado = false;
        Usuario usuario;
        Query consulta = em.createNamedQuery("Usuario.login");
        consulta.setParameter("nombreUsuario", nombreUsuario);
        consulta.setParameter("contrasenia", contraseña);
        try{
            usuario = (Usuario) consulta.getSingleResult();
        }
        catch (NoResultException e){
            return false;
        }
        if (usuario != null)
            resultado = true;
        
        return resultado;
    }

    public boolean validarNombreUsuario(String nombreUsuario, String nombre) {
        boolean resultado = false;
        Usuario usuario;
        Query consulta = em.createNamedQuery("Usuario.validar");
        consulta.setParameter("nombreUsuario", nombreUsuario);
        consulta.setParameter("nombre", nombre);
        try{
            usuario = (Usuario) consulta.getSingleResult();
        }
        catch (NoResultException e){
            return false;
        }
        if (usuario != null)
            resultado = true;
        
        return resultado;
    }

    public Usuario buscarPorNombreUsuario(String nombreUsuario) {
        Usuario usuario;
        Query consulta = em.createNamedQuery("Usuario.buscarPorNombreUsuario");
        consulta.setParameter("nombreUsuario", nombreUsuario);
        try{
            usuario = (Usuario) consulta.getSingleResult();
        }
        catch (Exception e){
            return null;
        }
            return usuario;
    }
    

        
}
