
package controller;

import Modelos.Cliente;
import Modelos.Conexion;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdministracionController {
    
    private JdbcTemplate jdbcTemplate;
    
    public AdministracionController(){
        Conexion con= new Conexion();
        this.jdbcTemplate=new JdbcTemplate(con.conectar());
    }
    
    @RequestMapping("login.htm")
    public ModelAndView login()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Login/login");
        return mav;
    }
    
    
    @RequestMapping("cliente.htm")
    public ModelAndView cliente()
    {
        ModelAndView mav= new ModelAndView();
        String sql ="SELECT\n" +
"   COMUNA_NOMBRE,\n" +
"    REGION_NOMBRE,\n" +
"    RUT_EMPRESA,\n" +
"    NOMBRE_EMPRESA,\n" +
"    TELEFONO_EMPRESA,\n" +
"    CORREO_EMPRESA,\n" +
"    DIRECCION_EMPRESA\n" +
"FROM\n" +
"    region  inner join\n" +
"    provincia on REGION_ID = PROVINCIA_REGION_ID inner join\n" +
"    comuna on PROVINCIA_ID = COMUNA_PROVINCIA_ID inner join\n" +
"    EMPRESA on COMUNA_EMPRESA = COMUNA_ID";
        List datos=this.jdbcTemplate.queryForList(sql);
        mav.addObject("datos",datos);

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
