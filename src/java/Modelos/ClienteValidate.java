
package Modelos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class ClienteValidate implements Validator{
    
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
   + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
   
     private Pattern pattern;
     private Matcher matcher;

    @Override
    public boolean supports(Class<?> type) {
        return Cliente.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Cliente cliente = (Cliente) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Nombre", "required.Nombre","El campo Nombre es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Rut", "required.Rut","El campo Rut es obligatorio");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Direccion", "required.Direccion","El campo Direccion es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required.email","El campo E-mail es obligatorio");
         if (!(cliente.getEmail()!= null && cliente.getEmail().isEmpty()))
        {
            this.pattern = Pattern.compile(EMAIL_PATTERN);
            this.matcher = pattern.matcher(cliente.getEmail());
             if (!matcher.matches()) {
                errors.rejectValue("email", "email.incorrect",
                  "El Correo electrónico "+cliente.getEmail()+" no es válido");
               }
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Telefono", "required.Telefono","El campo Teléfono es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Region", "required.Region","El campo Región es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Comuna", "required.Comuna","El campo Comuna es obligatorio");
        
    }
    
}
