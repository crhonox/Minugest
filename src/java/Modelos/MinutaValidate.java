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
 * @author Sir Lekxas
 */
public class MinutaValidate implements Validator {
    @Override
    public boolean supports(Class<?> type) {
        return Ingrediente.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Minuta minuta = (Minuta)o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Nombre_Min", "required.Nombre_Min","El campo Nombre es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Codigo_Casi", "required.Codigo_Casi","El campo Casino es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Fecha_Min", "required.Fecha_Min","El campo Fecha es obligatorio");
        
    }
}
