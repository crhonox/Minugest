
package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdministracionController {
    
    @RequestMapping("cliente.htm")
    public ModelAndView cliente()
    {
        ModelAndView mav= new ModelAndView();
        mav.setViewName("Administracion/cliente");
        return mav;
    }
    
    @RequestMapping("receta.htm")
    public ModelAndView receta()
    {
        ModelAndView mav= new ModelAndView();
        mav.setViewName("Administracion/receta");
        return mav;
    }
    
    @RequestMapping("ingrediente.htm")
    public ModelAndView ingrediente()
    {
        ModelAndView mav= new ModelAndView();
        mav.setViewName("Administracion/ingrediente");
        return mav;
    }
}
