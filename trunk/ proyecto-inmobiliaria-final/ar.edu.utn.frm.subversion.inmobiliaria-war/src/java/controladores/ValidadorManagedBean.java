/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Dario
 */
@ManagedBean(name = "validador")
@RequestScoped
public class ValidadorManagedBean implements Validator {

    /** Creates a new instance of ValidadorManagedBean */
    public ValidadorManagedBean() {
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        System.out.println("valor del componente: " + value);
        if (value.equals(" ")) {
            throw new ValidatorException(new FacesMessage("El campo no admite espacios en blanco"));
        } else if (value.toString().equals("0")) {
            throw new ValidatorException(new FacesMessage("(*) Debe Seleccionar un Perfil"));
        } else if (value.toString().equals("- seleccionar -")) {
            throw new ValidatorException(new FacesMessage("*"));
        }else if (value.toString().isEmpty()) {
            throw new ValidatorException(new FacesMessage("*"));
        }else if (value == null) {
            throw new ValidatorException(new FacesMessage("*"));
        }
    }
    
    /** method to validate the email field
     *@param FacesContext context, UIComponent validate, Object value
     */
    public void validateEmail(FacesContext context, UIComponent validate, Object value){
        String email = (String)value;

        if(email.indexOf('@')==-1){
            ((UIInput)validate).setValid(false);
            FacesMessage msg = new FacesMessage("Email invalido");
            context.addMessage(validate.getClientId(context), msg);
        }
    }

    public void validateNoVacio(FacesContext context, UIComponent validate, Object value){
        System.out.println("valor del componente no vacio: " + value);
        if(value.toString().isEmpty() || value == null || value.equals(" ") || value.equals("")){
            ((UIInput)validate).setValid(false);
            FacesMessage msg = new FacesMessage("*");
            context.addMessage(validate.getClientId(context), msg);
        }
    }    
    
    
    
}
