package controladores;

import com.icesoft.faces.component.menubar.MenuItem;
import entidades.Menu;
import entidades.Usuario;
import expertos.MenuSessionBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author mariodante
 */
@ManagedBean(name = "menu")
@RequestScoped
public class MenuManagedBean {

    private List elementosMios;
    List<Menu> lstMenu;
    @EJB
    private MenuSessionBean ejbSessionMenu;
    @ManagedProperty("#{usuario}")
    private UsuarioManagedBean usuarioMB;
    
    @ManagedProperty("#{asignacion}")
    private AsignacionManagedBean asignacionMB;
    
    public List buscarElementos() {
        String nombreUsuario = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogueado").toString();
        String idPerfil = getAsignacionMB().buscarPorUsuario(nombreUsuario);
        elementosMios = new ArrayList();
        //lstMenu = getEjbSessionMenu().buscarMenu2();
        lstMenu = getEjbSessionMenu().buscarPorPerfilAsignado(idPerfil);
        if (lstMenu.size() > 0) {
            //entra si al menos tiene un lstMenu asociado
            for (int i = 0; i < lstMenu.size(); i++) {
                MenuItem nivelBasico = new MenuItem();
                if (lstMenu.get(i).getMenu_padre() == null) {
                    //entro aca porque es padre
                    nivelBasico = agregarSubItems(lstMenu.get(i));
                    elementosMios.add(nivelBasico);
                }
            }
        }
        return elementosMios;
    }

    private MenuItem agregarSubItems(Menu elem) {
        MenuItem subNivel = null;

        boolean hijos = tieneHijos(elem);

        if (hijos) {
            //si tiene hijos el menu
            subNivel = agregarItem(elem);
            for (Menu elemHijo : elem.getMenu_hijos()) {
                MenuItem sub = agregarSubItems(elemHijo);
                subNivel.getChildren().add(sub);
            }
        } //si es Hijo y tiene Link
        else if (esHoja(elem)) {
            if (elem.getVinculo() != null && elem.getVinculo().isEmpty()) {
                subNivel = agregarItem(elem);
            } else {
                MenuItem node = new MenuItem();
              //  node.setTitle(elem.getNombre());  // FIXME: reemplazo por linea de abajo 2014
                node.setTarget(elem.getNombre());
                node.setValue(elem.getNombre());
                node.setLink((elem.getVinculo() == null ? "" : elem.getVinculo()));
                subNivel = node;
            }
        } else {
            //si no tiene hijos ni es hijo.asumo que es de una cabecera sin link.
            //creo que serÃ­a un pato
            String msg = "";
            if (elem.getIdMenu() == null) {
                msg = "null";
            } else {
                msg = elem.getIdMenu();
            }
        }
        return subNivel;
    }

    private boolean tieneHijos(Menu elem) {
        boolean rta = false;

        if (!elem.getMenu_hijos().isEmpty()) {
            rta = true;
        }
        return rta;
    }

    private boolean esHoja(Menu elem) {
        return !tieneHijos(elem);
    }

    private MenuItem agregarItem(Menu elem) {
        MenuItem node = new MenuItem();
      //  node.setTitle(elem.getNombre());  // FIXME: reemplazo por linea de abajo 2014
        node.setTarget(elem.getNombre());
        node.setValue(elem.getNombre());
        return node;
    }

    public MenuSessionBean getEjbSessionMenu() {
        return ejbSessionMenu;
    }

    public void setEjbSessionMenu(MenuSessionBean ejbSessionMenu) {
        this.ejbSessionMenu = ejbSessionMenu;
    }
    
    public void cerrarSesion(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogueado",null);
            FacesContext.getCurrentInstance().getExternalContext().redirect("../principal/login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(MenuManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public void cambiarContrasenia(){
     try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("activo", true);
            FacesContext.getCurrentInstance().getExternalContext().redirect("../recursos/componentes/popUpPropio.xhtml");
            //this.getUsuarioMB().setVistaPopUp(true);
           // this.getUsuarioMB().cambiarContraseñaAnterior();
        } catch (IOException ex) {
            Logger.getLogger(MenuManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public UsuarioManagedBean getUsuarioMB() {
        return usuarioMB;
    }

    public void setUsuarioMB(UsuarioManagedBean usuarioMB) {
        this.usuarioMB = usuarioMB;
    }

    public AsignacionManagedBean getAsignacionMB() {
        return asignacionMB;
    }

    public void setAsignacionMB(AsignacionManagedBean asignacionMB) {
        this.asignacionMB = asignacionMB;
    }

}
