
package Modelos;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;



public class CasinoValidate implements Validator{
    

    @Override
    public boolean supports(Class<?> type) {
        return Casino.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Casino casino = (Casino) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "RutEmpresa", "required.RutEmpresa","El campo Rut es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "NombreCasino", "required.NombreCasino","El campo Nombre es obligatorio"); 
    }
    
}
