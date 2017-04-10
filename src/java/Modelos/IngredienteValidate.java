package Modelos;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class IngredienteValidate implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return Ingrediente.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Ingrediente ingrediente = (Ingrediente)o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombreIngrediente", "required.nombre", "El campo nombre es obligatorio.");
        
    }
    
}
