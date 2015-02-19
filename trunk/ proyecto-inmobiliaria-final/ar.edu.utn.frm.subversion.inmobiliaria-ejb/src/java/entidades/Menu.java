/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author MARIANA
 */
@Entity
@Table(name="menu")
@NamedQueries({
     @NamedQuery(name = "Menu.buscarPorPerfil", query = "SELECT m FROM Menu m "
        + "WHERE m.idMenu NOT IN (SELECT p.menu.idMenu FROM Permiso p WHERE p.perfil.idPerfil LIKE :idPerfil) Order By m.nroMenu asc"),
    @NamedQuery(name = "Menu.buscarPorPerfilAsignado", query = "SELECT m FROM Menu m "
        + "WHERE m.idMenu IN (SELECT p.menu.idMenu FROM Permiso p WHERE p.perfil.idPerfil LIKE :idPerfil) Order By m.nroMenu asc"),
    //@NamedQuery(name = "Menu.findNombre", query = "SELECT m FROM Menu m WHERE m.nombre = :nombre"),
    //@NamedQuery(name = "Menu.findNroMenu", query = "SELECT m FROM Menu m WHERE m.nroMenu = :nroMenu"),
    //@NamedQuery(name = "Menu.buscarPorPerfil", query = "SELECT m FROM Menu m WHERE m.padre = :padre"),
     @NamedQuery(name = "Menu.buscar", query = "SELECT m FROM Menu m Order By m.nroMenu asc")
    /*@NamedQuery(name = "Menu.findProceso", query = "SELECT m FROM Menu m WHERE m.proceso = :proceso")*/})
public class Menu implements Serializable {
    
    
    
    @ManyToOne
    private Menu menu_padre;

    @Id
    private String idMenu;
    private String nombre;
    private int nroMenu;
    private String vinculo;
    @ManyToOne
    private Accion accion;
           
    //@OneToMany()
    //@JoinTable(name="menu_menu",joinColumns=@JoinColumn(name="id_menu"))
    
    
    
    @OneToMany(mappedBy = "menu_padre")
    private List<Menu> menu_hijos;
  
    
    public String getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(String id) {
        this.idMenu = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the nroMenu
     */
    public int getNroMenu() {
        return nroMenu;
    }

    /**
     * @param nroMenu the nroMenu to set
     */
    public void setNroMenu(int nroMenu) {
        this.nroMenu = nroMenu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMenu != null ? idMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.idMenu == null && other.idMenu != null) || (this.idMenu != null && !this.idMenu.equals(other.idMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.edu.utn.frm.subversion.inmobiliaria.entidades.Menu[ idMenu=" + idMenu + " ]";
    }

    public String getVinculo() {
        return vinculo;
    }

    public void setVinculo(String vinculo) {
        this.vinculo = vinculo;
    }

    public Accion getAccion() {
        return accion;
    }

    public void setAccion(Accion accion) {
        this.accion = accion;
    }

    /**
     * @return the menu_padre
     */
    public Menu getMenu_padre() {
        return menu_padre;
    }

    /**
     * @param menu_padre the menu_padre to set
     */
    public void setMenu_padre(Menu menu_padre) {
        this.menu_padre = menu_padre;
    }

    /**
     * @return the menu_hijos
     */
    public List<Menu> getMenu_hijos() {
        return menu_hijos;
    }

    /**
     * @param menu_hijos the menu_hijos to set
     */
    public void setMenu_hijos(List<Menu> menu_hijos) {
        this.menu_hijos = menu_hijos;
    }
/*
    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }
    public void setMenu(Menu menu){
        this.menu.add(menu);
    }
*/
    /**
     * @return the menu_hijo
     */
    /*
    public Menu getMenu_hijo() {
        return menu_hijo;
    }
     
    public void setMenu_hijo(Menu menu_hijo) {
        this.menu_hijo = menu_hijo;
    }*/
}
