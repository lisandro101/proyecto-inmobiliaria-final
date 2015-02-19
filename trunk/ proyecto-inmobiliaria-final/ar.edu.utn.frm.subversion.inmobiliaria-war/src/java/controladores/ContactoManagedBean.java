package controladores;

import com.icesoft.faces.component.ext.RowSelectorEvent;
import entidades.Contacto;
import entidadesAuxiliares.ContactoFila;
import expertos.ContactoSessionBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
  
@ManagedBean(name = "contactoMB")
@SessionScoped
public class ContactoManagedBean implements Serializable {

    @EJB
    private ContactoSessionBean contactoSessionBean;
    private List<ContactoFila> contactoFilas;

public ContactoManagedBean() {
    }

    /**
     * @return the contactoFilas
     */
    public List<ContactoFila> getContactoFilas() {
        if (contactoFilas == null || contactoFilas.isEmpty()) {
            contactoFilas = new ArrayList<ContactoFila>();
            for (Contacto c : contactoSessionBean.findAll()) {
                contactoFilas.add(new ContactoFila(c));
            }
        }
        return contactoFilas;
    }

    public void setContactosFilas(List<ContactoFila> contactoFilas) {
        this.contactoFilas = contactoFilas;
    }

    public void rowSelectionListener(RowSelectorEvent event) {
        try {
            int row = event.getRow();
        } catch (Exception e) {
            System.out.println("--> Error rowSelectionListener: " + e.toString());
        }
    }

    @FacesConverter(forClass = Contacto.class)
    public static class VisitaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ContactoManagedBean controller = (ContactoManagedBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "contactoMB");
            return controller.contactoSessionBean.find(value);
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Contacto) {
                Contacto o = (Contacto) object;
                return getStringKey(o.getIdContacto());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + VisitaManagedBean.class.getName());
            }
        }
    }
}
