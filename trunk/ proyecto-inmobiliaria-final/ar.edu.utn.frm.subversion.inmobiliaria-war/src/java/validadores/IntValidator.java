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
@FacesValidator("intValidator")
public class IntValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        if (value instanceof Integer) {
            if (-65535 > ((Integer) value) && ((Integer) value) > 65535) {
                throw new ValidatorException(new FacesMessage("*"));
            }
        } else if (value instanceof String) {
            if(!isNumeric((String)value)){
                throw new ValidatorException(new FacesMessage("*"));
            }
        
        }
    }

    private static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
