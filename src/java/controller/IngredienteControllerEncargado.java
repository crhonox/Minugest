package controller;

import Modelos.Conexion;
import Modelos.Ingrediente;
import Modelos.IngredienteValidate;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IngredienteControllerEncargado 
{
    private IngredienteValidate ingredienteValidate;
    private JdbcTemplate jdbcTemplate;
    
    
    public IngredienteControllerEncargado()
    {
        this.ingredienteValidate = new IngredienteValidate();
        Conexion con= new Conexion();
        this.jdbcTemplate=new JdbcTemplate(con.conectar());
    }
    
    @RequestMapping("Encargado/ingrediente.htm")
    public ModelAndView ingrediente()
    {
          ModelAndView mav= new ModelAndView();
        String sql ="SELECT\n"
                + "     CODIGO_INGREDIENTE,\n"
                + "     NOMBRE_UNIDAD,\n"
                + "     NOMBRE_INGREDIENTE\n"
                + "FROM\n"
                + "INGREDIENTE inner join\n"
                + " UNIDAD_DE_MEDIDA on UNIDAD_DE_MEDIDA.CODIGO_UNIDAD = INGREDIENTE.CODIGO_UNIDAD";
        
        List datos=this.jdbcTemplate.queryForList(sql);
        mav.addObject("datos",datos);

        mav.setViewName("Encargado/ingrediente");
        return mav;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView añadirIngrediente()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Encargado/AñadirIngrediente");
        List unidadMedida= this.jdbcTemplate.queryForList("select CODIGO_UNIDAD, NOMBRE_UNIDAD from UNIDAD_DE_MEDIDA");
      
        mav.addObject("unidadMedida",unidadMedida);
        mav.addObject("ingrediente",new Ingrediente());
        return mav;
    }
     
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView form (@ModelAttribute("ingrediente") Ingrediente ing, BindingResult result, SessionStatus status)  
    {
        this.ingredienteValidate.validate(ing, result);
        if(result.hasErrors())
        {
                ModelAndView mav = new ModelAndView();
                mav.setViewName("Encargado/AñadirIngrediente");
                mav.addObject("ingrediente",new Ingrediente());
                return mav;
        }else
        {
           
            this.jdbcTemplate.update("insert into INGREDIENTE (CODIGO_UNIDAD, NOMBRE_INGREDIENTE) values (?,?)" , ing.getUnidadMedida() ,ing.getNombreIngrediente());
            return new ModelAndView("redirect:/Encargado/ingrediente.htm");
        }
        
    } 
}