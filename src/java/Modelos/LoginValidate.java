package Modelos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
/**
 *
 * @author Sir Lekxas
 */
public class LoginValidate implements Validator {
    
    @Override
    public boolean supports(Class<?> type) {
        return Login.class.isAssignableFrom(type);
        
    }
    
    @Override
    public void validate(Object o, Errors errors){
    Login login = (Login) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Rut", "required.Rut","El campo Rut es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Pass", "required.Pass","La contraseña es obligatorio");
    
    }

    
}
