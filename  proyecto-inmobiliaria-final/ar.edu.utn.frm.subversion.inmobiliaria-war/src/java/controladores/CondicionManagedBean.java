/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package controladores;

import entidades.Condicion;
import expertos.CondicionSessionBean;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import util.JsfUtil;
import util.PaginationHelper;

/**
 *
 * @author Sebastian
 */

@ManagedBean(name = "condicionManagedBean")
@RequestScoped
public class CondicionManagedBean {
    private Condicion condicion;    
    private List<Condicion> condiciones;
    private DataModel items = null;
    @EJB
    private CondicionSessionBean condicionSessionBean;
    private PaginationHelper pagination;
    private int selectedItemCondicion;
    
    
    public CondicionManagedBean() {
    }
         
    public Condicion getSelected() {
        if (condicion == null) {
            condicion = new Condicion();
            selectedItemCondicion = -1;
        }
        return condicion;
    }

    private CondicionSessionBean getFacade() {
        return condicionSessionBean;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "Listar";
    }

    public String prepareView() {
        condicion = (Condicion) getItems().getRowData();
        selectedItemCondicion = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Ver";
    }

    public String prepareCreate() {
        condicion = new Condicion();
        selectedItemCondicion = -1;
        return "Crear";
    }

    public String create() {
        try {
            getFacade().create(condicion);
            JsfUtil.addSuccessMessage("Condicion creada"); 
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Ocurrio un error en la persistencia");
            return null;
        }
    }

    public String prepareEdit() {
        condicion = (Condicion) getItems().getRowData();
        selectedItemCondicion = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Editar";
    }

    public String update() {
        try {
            getFacade().edit(condicion);
            JsfUtil.addSuccessMessage("Condicion Actualizada"); //JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProductUpdated"));
            return "Ver";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Ocurrio un error en la persistencia"); //JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        condicion = (Condicion) getItems().getRowData();
        selectedItemCondicion = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreateModel();
        return "Listar";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemCondicion >= 0) {
            return "Ver";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "Listar";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(condicion);
            JsfUtil.addSuccessMessage("Condicion eliminada"); 
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Ocurrio un error en la persistencia"); 
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemCondicion >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemCondicion = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemCondicion >= 0) {
            condicion = getFacade().findRange(new int[]{selectedItemCondicion, selectedItemCondicion + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "Listar";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "Listar";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(condicionSessionBean.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(condicionSessionBean.findAll(), true);
    }

    @FacesConverter(forClass = Condicion.class)
    public static class CondicionControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CondicionManagedBean controller = (CondicionManagedBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "condicionManagedBean");
            return controller.condicionSessionBean.find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Condicion) {
                Condicion o = (Condicion) object;
                return o.getIdCondicion(); 
            } else {
                throw new IllegalArgumentException("El objeto " + object + " es de tipo " + 
                        object.getClass().getName() + "; tipo esperado: " + CondicionManagedBean.class.getName());
            }
        }
    }
    
        public List<Condicion> getCondiciones(){
        condiciones = condicionSessionBean.findAll();
        if(condiciones== null || condiciones.isEmpty()){
//            cargarEjemplos();
        }
        
        return condicionSessionBean.findAll();
    }
    
}
