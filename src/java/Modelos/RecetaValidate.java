package Modelos;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class RecetaValidate implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return Receta.class.isAssignableFrom(type);
    }
    
    /*
    private String idReceta;
    private String idCategoria;
    private String nombreReceta;
    private String descripcionReceta;
    private String porcionReceta;
    private Integer remove; // boolean flag
    private Minuta  minuta;
    */

    @Override
    public void validate(Object o, Errors errors) {
        Receta receta = (Receta)o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombreReceta", "required.nombreReceta", "El campo nombre es obligatorio.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "porcionReceta", "required.porcionReceta", "El campo nombre es obligatorio.");
        
        int cod = Integer.parseInt(receta.getIdCategoria());
        if(cod == 0){
         errors.rejectValue("idCategoria", "idCategoria.incorrect",
                  "La categoria no puede ser 0");    
        
        }
        
    }
    
}
