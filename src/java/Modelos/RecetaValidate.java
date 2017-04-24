package Modelos;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class RecetaValidate implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return Receta.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Receta receta = (Receta)o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombreReceta", "required.nombre", "El campo nombre es obligatorio.");
        
    }
    
}
