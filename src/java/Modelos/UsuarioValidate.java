
package Modelos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;



public class UsuarioValidate implements Validator{
    
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
   + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
   
     private Pattern pattern;
     private Matcher matcher;

    @Override
    public boolean supports(Class<?> type) {
        return Usuario.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Usuario usuario = (Usuario) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Nombre", "required.Nombre","El campo Nombre es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellido", "required.apellido","El campo Apellido es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Rut", "required.Rut","El campo Rut es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cod_perfil", "required.cod_perfil","El campo Perfil es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "correo", "required.correo","El campo E-mail es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pass", "required.pass","El campo Contraseña es obligatorio");    
         if (!(usuario.getCorreo()!= null && usuario.getCorreo().isEmpty()))
        {
            this.pattern = Pattern.compile(EMAIL_PATTERN);
            this.matcher = pattern.matcher(usuario.getCorreo());
             if (!matcher.matches()) {
                errors.rejectValue("email", "email.incorrect",
                  "El Correo electrónico "+usuario.getCorreo()+" no es válido");
               }
        }
         
        
    }
    
}
