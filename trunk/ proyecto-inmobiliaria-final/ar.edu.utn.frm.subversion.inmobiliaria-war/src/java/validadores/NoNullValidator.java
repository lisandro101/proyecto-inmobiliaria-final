/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validadores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Sebastian
 */

@FacesValidator("noNullValidator") 
public class NoNullValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        System.out.println("NoNullValidator -> valor del componente no vacio: " + value);
        
        if (value.toString().equals("- seleccionar -")) {
            throw new ValidatorException(new FacesMessage("*"));
        }else if (value.toString().isEmpty() || value == null || value.equals(" ") || value.equals("")) {
//            ((UIInput)validate).setValid(false);
//            FacesMessage msg = new FacesMessage("*");
//            context.addMessage(validate.getClientId(context), msg);
            throw new ValidatorException(new FacesMessage("*"));

        }else if (value.toString().equals("")) {
            throw new ValidatorException(new FacesMessage("*"));

        }else if (value.toString().equals("0") || value.toString().equals("0.0")) {
            throw new ValidatorException(new FacesMessage("*"));
        }
    }
}
