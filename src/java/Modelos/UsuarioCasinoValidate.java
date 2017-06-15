
package Modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;



public class UsuarioCasinoValidate implements Validator{
    
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
         List<String> lista = new ArrayList<>();
         int cod;
         lista =usuario.getCombobox();
         if(usuario.getCombobox().isEmpty()){
             errors.rejectValue("combobox", "combobox.incorrect",
                  "Tiene que seleccionar un casino");
         }
         
          /*   for (int i = 0; i < lista.size(); i++) {
              cod=Integer.parseInt(lista.get(i));   
            if(cod==0 || cod==null){
                errors.rejectValue("combobox", "combobox.incorrect",
                  "Tiene que seleccionar un casino");
            }
        }*/
        
        
    }
    
}