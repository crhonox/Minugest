package controller;


import Modelos.Minuta;
import Modelos.MinutaValidate;
import Modelos.Conexion;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
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
    private MinutaValidate minutaValidate;
    
    private JdbcTemplate jdbcTemplate;
    
    public SupervisorController(){
        this.minutaValidate=new MinutaValidate(); //Implementar Validator
        Conexion con= new Conexion();
        this.jdbcTemplate=new JdbcTemplate(con.conectar());
    }
    
    
    @RequestMapping("minutaDia.htm")
    public ModelAndView minutaDia()
    {
        ModelAndView mav= new ModelAndView();
        String sql ="SELECT\n" +
                "NOMBRE_MINUTA,\n" +
                "CODIGO_MINUTA,\n" +
                "CODIGO_CASINO,\n" +
                "CODIGO_USUARIO,\n" +
                "FECHA_MINUTA\n" +
                "FROM\n " +
                "MINUTA";
        
        List datos=this.jdbcTemplate.queryForList(sql);
        mav.addObject("datos",datos);
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
    
    @RequestMapping("consultaExito.htm")
    public ModelAndView consultaExito()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("SupervisorC/consultaExito");
        return mav;
    }
    
    @RequestMapping("consultaError.htm")
    public ModelAndView consultaError()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("SupervisorC/consultaError");
        return mav;
    }
    
    
}
