package controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Sir Lekxas
 */
@Controller 
public class Administrador2Controller 
{
    @RequestMapping(value = "admHome.htm") 
    public ModelAndView admHome()
    {
        ModelAndView mav = new ModelAndView();
         mav.setViewName("Administracion2/admHome");
        return mav;
    
    }
    
}