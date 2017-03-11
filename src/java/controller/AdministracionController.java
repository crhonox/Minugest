
package controller;

import Modelos.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    
    @RequestMapping("login.htm")
    public ModelAndView login()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Login/login");
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
    
    @RequestMapping(value = "AñadirCliente.htm", method = RequestMethod.GET)
    public ModelAndView añadirCliente()
    {
        return new ModelAndView("Administracion/AñadirCliente","command", new Cliente());
    }
    
    @RequestMapping(value="AñadirCliente.htm",method = RequestMethod.POST)
    public String form(Cliente cli,ModelMap model)
    {
        model.addAttribute("Nombre",cli.getNombre());
        model.addAttribute("rut", cli.getRut());
        model.addAttribute("Direccion",cli.getDireccion());
        model.addAttribute("Telefono",cli.getTelefono());
        model.addAttribute("Comuna", cli.getComuna());
        model.addAttribute("Region",cli.getRegion());
        model.addAttribute("email", cli.getEmail());
        return "exito";
    }
}
