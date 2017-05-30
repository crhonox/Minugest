/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author crhonox
 */
public class SolicitudValidate implements Validator{
    
    @Override
    public boolean supports(Class<?> type) {
        return Solicitud.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Solicitud solicitud = (Solicitud)o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "asunto", "required.asunto", "El campo asunto es obligatorio.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contenido", "required.contenido", "El campo contenido es obligatorio.");
        
    }
    
}
