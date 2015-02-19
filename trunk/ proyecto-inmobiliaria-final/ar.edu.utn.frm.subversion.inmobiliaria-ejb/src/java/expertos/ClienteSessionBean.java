/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import entidades.Cliente;
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
public class ClienteSessionBean extends AbstractSession<Cliente> {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "ar.edu.utn.frm.subversion.inmobiliaria-ejbPU")
    EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteSessionBean() {
        super(Cliente.class);
    }

    public List<Cliente> buscarUltimoNumeroCliente() {

        List<Cliente> listaCliente = new ArrayList<Cliente>();

        Query consulta = em.createNamedQuery("Cliente.buscarUltimoPorNumero");

        //  List<Menu> lista = new ArrayList<Menu>(); 

        // Query consulta = em.createNamedQuery("Menu.buscar");

        try {
            listaCliente = consulta.getResultList();
        } catch (NoResultException e) {
            System.out.println("    " + listaCliente);
        }

        return listaCliente;

    }

    public boolean validarExistenciaCliente(String dni) {
        boolean resultado = false;

        Cliente cliente;

        Query consulta = em.createNamedQuery("Cliente.validar");

        consulta.setParameter("dni", dni);

        try {
            cliente = (Cliente) consulta.getSingleResult();
        } catch (NoResultException e) {
            return false;
        }
        if (cliente != null) {
            resultado = true;
        }


        return resultado;
    }

    public Cliente obtenerCliente(String dni) {
        Cliente cliente;
        Query consulta = em.createNamedQuery("Cliente.validar");
        consulta.setParameter("dni", dni);

        try {
            cliente = (Cliente) consulta.getSingleResult();
        } catch (NoResultException e) {
            cliente = null;
        }
        return cliente;
    }

    public List<Cliente> buscarClientes(String nombreDniNumero) {
        List<Cliente> clientes;
        Query consulta;
        if (nombreDniNumero != null) {
            String valor = "'%" + nombreDniNumero.toUpperCase() + "%'";
            consulta = em.createQuery("SELECT c FROM Cliente c WHERE UPPER(c.apellido) LIKE " + valor + " OR UPPER(c.nombre) LIKE " 
                                                    + valor + " OR c.dni LIKE " + valor 
                                                    + " OR c.nroCliente LIKE " 
                                                    + valor + " ORDER BY c.nroCliente ASC");

            try {
                clientes = (List<Cliente>) consulta.getResultList();
            } catch (NoResultException e) {
                clientes = null;
            }
        } else {
            clientes = this.findAll();
        }
        return clientes;
    }

    public boolean validarExistenciaClientePorNroCliente(String nroCliente) 
    {
        boolean existe = false;
           
        List<Cliente> clientes;
        
        Query consulta;
        if (nroCliente != null || !nroCliente.equals("")) {
            
            String valor = "'" + nroCliente.toUpperCase() + "'";
            consulta = em.createQuery("SELECT c FROM Cliente c WHERE c.nroCliente = " + valor );

            try 
            {
                clientes = (List<Cliente>) consulta.getResultList();
            } catch (NoResultException e) {
                clientes = null;
            }
            
        } 
        else 
        {
            clientes = null;
        }
        //si tiene algún elemento, devuelvo true
        if(clientes.size()>0)
        {
            existe=true;
        }
        
        return existe;
        
    }
}
