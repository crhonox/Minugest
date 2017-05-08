
package controller;
import Modelos.Conexion;
import Modelos.Receta;
import Modelos.RecetaValidate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
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
    //ADMINITRACIÓN =============================================================================
    @RequestMapping("receta.htm")
    public ModelAndView receta()
    {
          ModelAndView mav= new ModelAndView();
        String sql ="SELECT\n"
                + "     CODIGO_RECETA,\n"
                + "     NOMBRE_CATEGORIA,\n"
                + "     NOMBRE_RECETA,\n"
                + "     DESCRIPCION_RECETA,\n"
                + "     CANTIDAD_PORCION\n"
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
                    + "NOMBRE_RECETA, DESCRIPCION_RECETA, CANTIDAD_PORCION) "
                    + "values (?,?,?,?)" , rec.getIdCategoria(), rec.getNombreReceta(), rec.getDescripcionReceta(), rec.getPorcionReceta());
            return new ModelAndView("redirect:/receta.htm");
         } 
 
    
    }
    
      @RequestMapping(value="editarReceta.htm",method=RequestMethod.GET)
    public ModelAndView editarReceta (HttpServletRequest request)
    {
        ModelAndView mav = new ModelAndView();
        String idReceta  = request.getParameter("idReceta");
        Receta datos = this.selectReceta(idReceta);
        List idCategoria = this.jdbcTemplate.queryForList("select CODIGO_CATEGORIA, NOMBRE_CATEGORIA from CATEGORIA");
        mav.addObject("idCategoria",idCategoria);
        
        mav.setViewName("Administracion/editarReceta");
        mav.addObject("receta",new Receta(idReceta, datos.getNombreReceta(), datos.getIdCategoria(), datos.getDescripcionReceta(), datos.getPorcionReceta()));
        return mav;
    }
    
      @RequestMapping(value="editarReceta.htm" ,method=RequestMethod.POST)
    public ModelAndView form
        (
                @ModelAttribute("receta") Receta rec,
                BindingResult result,
                SessionStatus status,
                HttpServletRequest request
        )
    {
        this.recetaValidate.validate(rec, result);
        if(result.hasErrors())
        {
             ModelAndView mav=new ModelAndView();
            String idReceta  = request.getParameter("idReceta");
            Receta datos = this.selectReceta(idReceta);
            mav.setViewName("Administracion/editarReceta");
            mav.addObject("receta",new Receta(idReceta, datos.getNombreReceta(), datos.getIdCategoria(), datos.getDescripcionReceta(), datos.getPorcionReceta()));
            return mav;
        }
        else
        {
            String idReceta  = request.getParameter("idReceta");
            this.jdbcTemplate.update( //REVISAR ORDEN UPDATE 
                        "update RECETA set  CODIGO_CATEGORIA=?, NOMBRE_RECETA=?, DESCRIPCION_RECETA=?, CANTIDAD_PORCION=? where CODIGO_RECETA=? ",
        rec.getIdCategoria(),  rec.getNombreReceta(), rec.getDescripcionReceta(), rec.getPorcionReceta(), idReceta);
         return new ModelAndView("redirect:/receta.htm");
        }
       
    }
    
        public Receta selectReceta(String idReceta) 
    {
        final Receta receta = new Receta();
        /*String quer="SELECT\n"
                + "     CODIGO_RECETA,\n"
                + "     NOMBRE_CATEGORIA,\n"
                + "     NOMBRE_RECETA,\n"
                + "     DESCRIPCION_RECETA,\n"
                + "     CANTIDAD_PORCION\n"
                + "FROM\n"
                + "RECETA inner join\n"
                 + " CATEGORIA on CATEGORIA.CODIGO_CATEGORIA = RECETA.CODIGO_CATEGORIA"
                + "  WHERE CODIGO_RECETA='" + idReceta + "'"; */
        String quer="SELECT\n"
                + "     CODIGO_RECETA,\n"
                + "     NOMBRE_CATEGORIA,\n"
                + "     NOMBRE_RECETA,\n"
                + "     DESCRIPCION_RECETA,\n"
                + "     CANTIDAD_PORCION\n"
                + "FROM\n"
                + "RECETA inner join\n"
                 + " CATEGORIA on CATEGORIA.CODIGO_CATEGORIA = RECETA.CODIGO_CATEGORIA"
                + "  WHERE CODIGO_RECETA='" + idReceta + "'"; 
        return (Receta) jdbcTemplate.query
        (
                quer, new ResultSetExtractor<Receta>() 
            {
                public Receta extractData(ResultSet rs) throws SQLException, DataAccessException {
                    if (rs.next()) 
                    {
                        receta.setNombreReceta(rs.getString("NOMBRE_RECETA"));
                        receta.setIdCategoria(rs.getString("NOMBRE_CATEGORIA"));
                        receta.setDescripcionReceta(rs.getString("DESCRIPCION_RECETA"));
                        receta.setPorcionReceta(rs.getString("CANTIDAD_PORCION"));
                     }
                    return receta;
                }


            }
        );
    
    
       }
        //ENCARGADO =========================================================================
        @RequestMapping("Encargado/receta.htm")
             public ModelAndView recetaEnc()
    {
          ModelAndView mav= new ModelAndView();
        String sql ="SELECT\n"
                + "     CODIGO_RECETA,\n"
                + "     NOMBRE_CATEGORIA,\n"
                + "     NOMBRE_RECETA,\n"
                + "     DESCRIPCION_RECETA,\n"
                + "     CANTIDAD_PORCION\n"
                + "FROM\n"
                + "RECETA inner join\n"
                + " CATEGORIA on CATEGORIA.CODIGO_CATEGORIA = RECETA.CODIGO_CATEGORIA";
        
        List datos=this.jdbcTemplate.queryForList(sql);
        mav.addObject("datos",datos);

        mav.setViewName("Encargado/receta");
        return mav;
    }
     
 @RequestMapping(value="Encargado/AñadirReceta.htm" ,method = RequestMethod.GET)
 public ModelAndView añadirRecetaEnc()
 {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Encargado/AñadirReceta");
        List idCategoria = this.jdbcTemplate.queryForList("select CODIGO_CATEGORIA, NOMBRE_CATEGORIA from CATEGORIA");
        
        mav.addObject("idCategoria",idCategoria);
        mav.addObject("receta",new Receta());
        return mav;
 }
 
 @RequestMapping(value="Encargado/AñadirReceta.htm" ,method = RequestMethod.POST)
    public ModelAndView formEnc (@ModelAttribute("receta") Receta rec, BindingResult result, SessionStatus status)  
    {
    this.recetaValidate.validate(rec, result);
        if(result.hasErrors())
        {
                ModelAndView mav = new ModelAndView();
                mav.setViewName("Encargado/AñadirReceta");
                mav.addObject("receta",new Receta());
                return mav;
        }else
        {
           
            this.jdbcTemplate.update("insert into RECETA (CODIGO_CATEGORIA, "
                    + "NOMBRE_RECETA, DESCRIPCION_RECETA, CANTIDAD_PORCION) "
                    + "values (?,?,?,?)" , rec.getIdCategoria(), rec.getNombreReceta(), rec.getDescripcionReceta(), rec.getPorcionReceta());
            return new ModelAndView("redirect:/Encargado/receta.htm");
         } 
 
    
    }
    
      @RequestMapping(value="Encargado/editarReceta.htm",method=RequestMethod.GET)
    public ModelAndView editarRecetaEnc (HttpServletRequest request)
    {
        ModelAndView mav = new ModelAndView();
        String idReceta  = request.getParameter("idReceta");
        Receta datos = this.selectReceta(idReceta);
        List idCategoria = this.jdbcTemplate.queryForList("select CODIGO_CATEGORIA, NOMBRE_CATEGORIA from CATEGORIA");
        mav.addObject("idCategoria",idCategoria);
        
        mav.setViewName("Encargado/editarReceta");
        mav.addObject("receta",new Receta(idReceta, datos.getNombreReceta(), datos.getIdCategoria(), datos.getDescripcionReceta(), datos.getPorcionReceta()));
        return mav;
    }
    
      @RequestMapping(value="Encargado/editarReceta.htm" ,method=RequestMethod.POST)
    public ModelAndView formEnc
        (
                @ModelAttribute("receta") Receta rec,
                BindingResult result,
                SessionStatus status,
                HttpServletRequest request
        )
    {
        this.recetaValidate.validate(rec, result);
        if(result.hasErrors())
        {
             ModelAndView mav=new ModelAndView();
            String idReceta  = request.getParameter("idReceta");
            Receta datos = this.selectReceta(idReceta);
            mav.setViewName("Encargado/editarReceta");
            mav.addObject("receta",new Receta(idReceta, datos.getNombreReceta(), datos.getIdCategoria(), datos.getDescripcionReceta(), datos.getPorcionReceta()));
            return mav;
        }
        else
        {
            String idReceta  = request.getParameter("idReceta");
            this.jdbcTemplate.update( //REVISAR ORDEN UPDATE 
                        "update RECETA set  CODIGO_CATEGORIA=?, NOMBRE_RECETA=?, DESCRIPCION_RECETA=?, CANTIDAD_PORCION=? where CODIGO_RECETA=? ",
        rec.getIdCategoria(),  rec.getNombreReceta(), rec.getDescripcionReceta(), rec.getPorcionReceta(), idReceta);
         return new ModelAndView("redirect:/Encargado/receta.htm");
        }
       
    }
    
        public Receta selectRecetaEnc(String idReceta) 
    {
        final Receta receta = new Receta();
        /*String quer="SELECT\n"
                + "     CODIGO_RECETA,\n"
                + "     NOMBRE_CATEGORIA,\n"
                + "     NOMBRE_RECETA,\n"
                + "     DESCRIPCION_RECETA,\n"
                + "     CANTIDAD_PORCION\n"
                + "FROM\n"
                + "RECETA inner join\n"
                 + " CATEGORIA on CATEGORIA.CODIGO_CATEGORIA = RECETA.CODIGO_CATEGORIA"
                + "  WHERE CODIGO_RECETA='" + idReceta + "'"; */
        String quer="SELECT\n"
                + "     CODIGO_RECETA,\n"
                + "     NOMBRE_CATEGORIA,\n"
                + "     NOMBRE_RECETA,\n"
                + "     DESCRIPCION_RECETA,\n"
                + "     CANTIDAD_PORCION\n"
                + "FROM\n"
                + "RECETA inner join\n"
                 + " CATEGORIA on CATEGORIA.CODIGO_CATEGORIA = RECETA.CODIGO_CATEGORIA"
                + "  WHERE CODIGO_RECETA='" + idReceta + "'"; 
        return (Receta) jdbcTemplate.query
        (
                quer, new ResultSetExtractor<Receta>() 
            {
                public Receta extractData(ResultSet rs) throws SQLException, DataAccessException {
                    if (rs.next()) 
                    {
                        receta.setNombreReceta(rs.getString("NOMBRE_RECETA"));
                        receta.setIdCategoria(rs.getString("NOMBRE_CATEGORIA"));
                        receta.setDescripcionReceta(rs.getString("DESCRIPCION_RECETA"));
                        receta.setPorcionReceta(rs.getString("CANTIDAD_PORCION"));
                     }
                    return receta;
                }


            }
        );
    
    
       }
        
        
}
