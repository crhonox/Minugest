
package controller;

import Modelos.Cliente;
import Modelos.ClienteValidate;
import Modelos.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdministracionController {
    
    private ClienteValidate clienteValidate;
    
    private JdbcTemplate jdbcTemplate;
    
    public AdministracionController(){
        this.clienteValidate=new ClienteValidate(); //Implementar Validator
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
    
  
    
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView añadirCliente()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Administracion/AñadirCliente");
        List regiones= this.jdbcTemplate.queryForList("select REGION_ID, REGION_NOMBRE from region");
        List comunas= this.jdbcTemplate.queryForList("select COMUNA_ID, COMUNA_NOMBRE from comuna");
        mav.addObject("regiones",regiones);
        mav.addObject("comunas",comunas);
        mav.addObject("cliente",new Cliente());
        return mav;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView form(@ModelAttribute ("cliente") Cliente cli,BindingResult result, SessionStatus status  )
    {
        this.clienteValidate.validate(cli, result);
        if(result.hasErrors())
        {
            //Volver al formulario por que los datos ingresados estan erroneos
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Administracion/AñadirCliente");
        mav.addObject("cliente",new Cliente());
        return mav;
        }else{
            this.jdbcTemplate.update("insert into EMPRESA (RUT_EMPRESA,NOMBRE_EMPRESA,TELEFONO_EMPRESA,CORREO_EMPRESA,Region_Empresa,Comuna_Empresa,Direccion_Empresa) values (?,?,?,?,?,?,?)"
                                    ,cli.getRut(),cli.getNombre(),cli.getTelefono(),cli.getEmail(),cli.getRegion(),cli.getComuna(),cli.getDireccion());
            //Mostrar los datos ingresados
       //     ModelAndView mav = new ModelAndView();
      //  mav.setViewName("exito");
       // mav.addObject("Nombre",cli.getNombre());
       // mav.addObject("Rut",cli.getRut());
      //  mav.addObject("Telefono",cli.getTelefono());
       // mav.addObject("Comuna",cli.getComuna());
       // mav.addObject("Region",cli.getRegion());
       // mav.addObject("Direccion",cli.getDireccion());
       // mav.addObject("email",cli.getEmail());
        return new ModelAndView("redirect:/cliente.htm");
        }
        
    }
}
