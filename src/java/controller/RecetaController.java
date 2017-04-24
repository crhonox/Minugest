
package controller;
import Modelos.Conexion;
import Modelos.Receta;
import Modelos.RecetaValidate;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
/**
 *
 * @author Bayron Cruz C
 */
@Controller
public class RecetaController {
    private RecetaValidate recetaValidate;
    private JdbcTemplate jdbcTemplate;

    public RecetaController() 
    {
        this.recetaValidate = new RecetaValidate();
        Conexion con= new Conexion();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
    }
    @RequestMapping("receta.htm")
    public ModelAndView receta()
    {
          ModelAndView mav= new ModelAndView();
        String sql ="SELECT\n"
                + "     CODIGO_RECETA,\n"
                + "     NOMBRE_CATEGORIA,\n"
                + "     NOMBRE_RECETA,\n"
                + "     DESCRIPCION_RECETA\n"
                + "FROM\n"
                + "RECETA inner join\n"
                + " CATEGORIA on CATEGORIA.CODIGO_CATEGORIA = RECETA.CODIGO_CATEGORIA";
        
        List datos=this.jdbcTemplate.queryForList(sql);
        mav.addObject("datos",datos);

        mav.setViewName("Administracion/receta");
        return mav;
    }
    
 @RequestMapping(method = RequestMethod.GET)
 public ModelAndView añadirReceta()
 {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Administracion/AñadirReceta");
        List idCategoria = this.jdbcTemplate.queryForList("select CODIGO_CATEGORIA, NOMBRE_CATEGORIA from CATEGORIA");
        
        mav.addObject("idCategoria",idCategoria);
        mav.addObject("receta",new Receta());
        return mav;
 }
 
 @RequestMapping(method = RequestMethod.POST)
    public ModelAndView form (@ModelAttribute("receta") Receta rec, BindingResult result, SessionStatus status)  
    {
    this.recetaValidate.validate(rec, result);
        if(result.hasErrors())
        {
                ModelAndView mav = new ModelAndView();
                mav.setViewName("Administracion/AñadirReceta");
                mav.addObject("receta",new Receta());
                return mav;
        }else
        {
           
            this.jdbcTemplate.update("insert into RECETA (CODIGO_CATEGORIA, "
                    + "NOMBRE_RECETA, DESCRIPCION_RECETA) "
                    + "values (?,?,?)" , rec.getIdCategoria(), rec.getNombreReceta(), rec.getDescripcionReceta());
            return new ModelAndView("redirect:/receta.htm");
         } 
 
    
}
    
     @RequestMapping("receta.htm")
    public ModelAndView receta1()
    {
          ModelAndView mav= new ModelAndView();
        String sql ="SELECT\n"
                + "     CODIGO_RECETA,\n"
                + "     NOMBRE_CATEGORIA,\n"
                + "     NOMBRE_RECETA,\n"
                + "     DESCRIPCION_RECETA\n"
                + "FROM\n"
                + "RECETA inner join\n"
                + " CATEGORIA on CATEGORIA.CODIGO_CATEGORIA = RECETA.CODIGO_CATEGORIA";
        
        List datos=this.jdbcTemplate.queryForList(sql);
        mav.addObject("datos",datos);

        mav.setViewName("Administracion/receta");
        return mav;
    }
}

