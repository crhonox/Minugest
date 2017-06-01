
package controller;
import Modelos.Conexion;
import Modelos.Receta;
import Modelos.RecetaValidate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    @RequestMapping(value = "Administracion/receta.htm")
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
    
 @RequestMapping(value = "Administracion/AñadirReceta.htm",method = RequestMethod.GET)
 public ModelAndView añadirReceta()
 {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Administracion/AñadirReceta");
        List idCategoria = this.jdbcTemplate.queryForList("select CODIGO_CATEGORIA, NOMBRE_CATEGORIA from CATEGORIA");
        
        mav.addObject("idCategoria",idCategoria);
        mav.addObject("receta",new Receta());
        return mav;
 }

 @RequestMapping(value = "Administracion/AñadirReceta.htm",method = RequestMethod.POST)
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
    
      @RequestMapping(value="Administracion/editarReceta.htm",method=RequestMethod.GET)
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
    
      @RequestMapping(value="Administracion/editarReceta.htm" ,method=RequestMethod.POST)
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
        @RequestMapping(value = "Encargado/receta.htm")
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
        
        @RequestMapping(value="Encargado/IngredienteReceta.htm",method=RequestMethod.GET)
    public ModelAndView RecetaIngrediente (HttpServletRequest request)
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Encargado/IngredienteReceta");
        String idReceta  = request.getParameter("idReceta");
        Receta datos = this.selectReceta(idReceta);
        List idCategoria = this.jdbcTemplate.queryForList("select CODIGO_CATEGORIA, NOMBRE_CATEGORIA from CATEGORIA");
        List ingrediente= this.jdbcTemplate.queryForList("SELECT CODIGO_INGREDIENTE,NOMBRE_INGREDIENTE,UNIDAD_DE_MEDIDA.NOMBRE_UNIDAD FROM INGREDIENTE inner join UNIDAD_DE_MEDIDA on UNIDAD_DE_MEDIDA.CODIGO_UNIDAD=INGREDIENTE.CODIGO_UNIDAD;");
        mav.addObject("idReceta",idReceta);
        mav.addObject("ingrediente",ingrediente);
        mav.addObject("nombre",datos.getNombreReceta());
        mav.addObject("porcion",datos.getPorcionReceta());
        mav.addObject("receta",new Receta(idReceta, datos.getNombreReceta(), datos.getIdCategoria(), datos.getDescripcionReceta(), datos.getPorcionReceta()));
        return mav;
    }
    
    @RequestMapping(value = "Encargado/AñadirIngredienteReceta.htm",method = RequestMethod.POST)
    public ModelAndView IngredienteInsertReceta (@ModelAttribute("Receta") Receta receta, BindingResult result, SessionStatus status,HttpServletRequest request)  
    {
             String Codigo = request.getParameter("idReceta");
             List<String> lista = new ArrayList<>();
             List<String> lista2 = new ArrayList<>();
             lista = receta.getCombobox();
             lista2 = receta.getCantidad();
             for (int i = 0; i < lista.size(); i++) {
            this.jdbcTemplate.update("insert into RECETAINGREDIENTE (CODIGO_RECETA, "
                    + "CODIGO_INGREDIENTE,CANTIDAD_INGREDIENTE)"
                    + "values (?,?,?)" , Codigo,lista.get(i),lista2.get(i));
        }
             
              
            return new ModelAndView("redirect:/Encargado/receta.htm");
    
    }
    
    @RequestMapping(value = "Encargado/DetalleReceta.htm",method = RequestMethod.GET)
    public ModelAndView DetalleReceta(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Encargado/DetalleReceta");
        String Codigo = request.getParameter("COD");
        Receta datos = this.selectReceta(Codigo);
        List ingrediente= this.jdbcTemplate.queryForList("SELECT INGREDIENTE.CODIGO_INGREDIENTE,NOMBRE_INGREDIENTE,UNIDAD_DE_MEDIDA.NOMBRE_UNIDAD,RECETAINGREDIENTE.CANTIDAD_INGREDIENTE FROM INGREDIENTE inner join UNIDAD_DE_MEDIDA on UNIDAD_DE_MEDIDA.CODIGO_UNIDAD=INGREDIENTE.CODIGO_UNIDAD inner join RECETAINGREDIENTE on RECETAINGREDIENTE.CODIGO_INGREDIENTE=INGREDIENTE.CODIGO_INGREDIENTE where RECETAINGREDIENTE.CODIGO_RECETA='"+Codigo+"';");
        String categoria = this.jdbcTemplate.queryForObject("select NOMBRE_CATEGORIA from CATEGORIA inner join RECETA on CATEGORIA.CODIGO_CATEGORIA=RECETA.CODIGO_CATEGORIA where CODIGO_RECETA='"+Codigo+"'",String.class);
        mav.addObject("ingredientes",ingrediente);
        mav.addObject("nombre",datos.getNombreReceta());
        mav.addObject("categoria",categoria);
        mav.addObject("codigo",Codigo);
        mav.addObject("porciones",datos.getPorcionReceta());
        mav.addObject("receta",new Receta(Codigo, datos.getNombreReceta(), datos.getIdCategoria(), datos.getDescripcionReceta(), datos.getPorcionReceta()));
        return mav;
    }
    
     @RequestMapping(value = "Encargado/EliminarIngrediente.htm",method = RequestMethod.GET)
    public ModelAndView EliminarReceta(HttpServletRequest request){
        String CodigoR = request.getParameter("CODR");
        String CodigoI = request.getParameter("CODI");
        String Query = "DELETE FROM `Minugest`.`RECETAINGREDIENTE` WHERE CODIGO_RECETA='"+CodigoR+"' AND CODIGO_INGREDIENTE='"+CodigoI+"'";
        this.jdbcTemplate.execute(Query);
        return new ModelAndView("redirect:/Encargado/DetalleReceta.htm?COD="+CodigoR);
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
                + "     CATEGORIA.CODIGO_CATEGORIA,\n"
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
                        receta.setIdCategoria(rs.getString("CATEGORIA.CODIGO_CATEGORIA"));
                        receta.setDescripcionReceta(rs.getString("DESCRIPCION_RECETA"));
                        receta.setPorcionReceta(rs.getString("CANTIDAD_PORCION"));
                     }
                    return receta;
                }


            }
        );
    
    
       }
        
        
}
