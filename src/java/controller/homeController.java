package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller 
public class homeController 
{
  @RequestMapping("home.htm")  
  public ModelAndView home()
  {
      
     ModelAndView mav = new ModelAndView();
     mav.setViewName("home");
     return mav;
  }
  
  @RequestMapping("compañia.htm")
    public ModelAndView compañia()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Login/compañia");
        return mav;
    }
    
    @RequestMapping("socios.htm")
    public ModelAndView socios()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Login/socios");
        return mav;
    }
    
    @RequestMapping("soluciones.htm")
    public ModelAndView soluciones()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Login/soluciones");
        return mav;
    }
    
}
