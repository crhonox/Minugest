package controller;


import Modelos.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
/**
 *
 * @author Sir Lekxas
 */
@Controller
public class SupervisorController {
    
    @RequestMapping("minutaDia.htm")
    public ModelAndView minutaDia()
    {
        ModelAndView mav= new ModelAndView();
        mav.setViewName("SupervisorC/minutaDia");
        return mav;
    }
    
    @RequestMapping("consultaMinuta.htm")
    public ModelAndView consultaMinuta()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("SupervisorC/consultaMinuta");
        return mav;
    }
}
