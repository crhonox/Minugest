package controller;

import Modelos.Conexion;
import Modelos.Ingrediente;
import Modelos.IngredienteValidate;
import Modelos.JSONValid;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IngredienteController 
{
    private IngredienteValidate ingredienteValidate;
    private JdbcTemplate jdbcTemplate;
    
    
    public IngredienteController()
    {
        this.ingredienteValidate = new IngredienteValidate();
        Conexion con= new Conexion();
        this.jdbcTemplate=new JdbcTemplate(con.conectar());
    }
    
    //ADMINSTRACIÓN ============================================================
    @RequestMapping(value = "Administracion/ingrediente.htm")
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
        mav.addObject("ingrediente",new Ingrediente());
        mav.setViewName("Administracion/ingrediente");
        return mav;
    }
    
    // b u s c a r 
      @RequestMapping(value="Administracion/ingrediente.htm", method = RequestMethod.POST)
    public ModelAndView formBuscarAdm (@ModelAttribute("ingrediente") Ingrediente ing, BindingResult result, SessionStatus status)  
    {
        this.ingredienteValidate.validate(ing, result);
        if(result.hasErrors())
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
             mav.addObject("ingrediente",new Ingrediente());
             mav.setViewName("Administracion/ingrediente");
             return mav;
        }
        else
        {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("Administracion/ingrediente");
            List ingrediente = this.jdbcTemplate.queryForList("SELECT * FROM INGREDIENTE inner join UNIDAD_DE_MEDIDA on UNIDAD_DE_MEDIDA.CODIGO_UNIDAD = INGREDIENTE.CODIGO_UNIDAD where NOMBRE_INGREDIENTE regexp '"+ing.getNombreIngrediente()+"'");
            mav.addObject("datos", ingrediente);
            mav.addObject("ingrediente",new Ingrediente());
            return mav;
        }
        
    }
    
    @RequestMapping(value = "Administracion/AñadirIngrediente.htm",method = RequestMethod.GET)
    public ModelAndView añadirIngrediente()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Administracion/AñadirIngrediente");
        List unidadMedida= this.jdbcTemplate.queryForList("select CODIGO_UNIDAD, NOMBRE_UNIDAD from UNIDAD_DE_MEDIDA");
      
        mav.addObject("unidadMedida",unidadMedida);
        mav.addObject("ingrediente",new Ingrediente());
        return mav;
    }
     
    @RequestMapping(value = "/Administracion/AñadirIngrediente.htm",method = RequestMethod.POST)
    public ModelAndView form (@ModelAttribute("ingrediente") Ingrediente ing, BindingResult result, SessionStatus status)  
    {
        this.ingredienteValidate.validate(ing, result);
        if(result.hasErrors())
        {
                ModelAndView mav = new ModelAndView();
                mav.setViewName("Administracion/AñadirIngrediente");
                mav.addObject("ingrediente",new Ingrediente());
                return mav;
        }else
        {
           
            this.jdbcTemplate.update("insert into INGREDIENTE (CODIGO_UNIDAD, NOMBRE_INGREDIENTE) values (?,?)" , ing.getUnidadMedida() ,ing.getNombreIngrediente());
            return new ModelAndView("redirect:/ingrediente.htm");
        }
        
    } 
    //EDITAR INGREDIENTE ADMISTRADOR
    @RequestMapping(value="editarIngrediente.htm" ,method=RequestMethod.GET)
    public ModelAndView editarIngrediente (HttpServletRequest request)
    {
        ModelAndView mav = new ModelAndView();
        String idIngrediente = request.getParameter("idIngrediente");
        //int idIngrediente = Integer.parseInt(request.getParameter("idIngrediente"));
        Ingrediente datos = this.selectIngrediente(idIngrediente);
        List unidadMedida= this.jdbcTemplate.queryForList("select CODIGO_UNIDAD, NOMBRE_UNIDAD from UNIDAD_DE_MEDIDA");
        mav.addObject("unidadMedida",unidadMedida);
        mav.setViewName("Administracion/editarIngrediente");
        mav.addObject("ingrediente",new Ingrediente(idIngrediente,datos.getNombreIngrediente(), datos.getUnidadMedida()));
        return mav;
    }
    
     @RequestMapping(value="editarIngrediente.htm" , method=RequestMethod.POST)
    public ModelAndView form
        (
                @ModelAttribute("ingrediente") Ingrediente ing,
                BindingResult result,
                SessionStatus status,
                HttpServletRequest request
        )
    {
        this.ingredienteValidate.validate(ing, result);
        if(result.hasErrors())
        {
             ModelAndView mav=new ModelAndView();
              String idIngrediente = request.getParameter("idIngrediente");
            //int idIngrediente = Integer.parseInt(request.getParameter("idIngrediente"));
            Ingrediente datos=this.selectIngrediente(idIngrediente);
            mav.setViewName("Administracion/editarIngrediente");
            
            mav.addObject("ingrediente",new Ingrediente(idIngrediente,datos.getNombreIngrediente(), datos.getUnidadMedida()));
            return mav;
        }
        else
        {
             String idIngrediente = request.getParameter("idIngrediente");
             //int idIngrediente = Integer.parseInt(request.getParameter("idIngrediente"));
             this.jdbcTemplate.update( //REVISAR ORDEN UPDATE 
                    "update INGREDIENTE set NOMBRE_INGREDIENTE=?,CODIGO_UNIDAD=? where CODIGO_INGREDIENTE=? ",
         ing.getNombreIngrediente(),ing.getUnidadMedida(),idIngrediente);
         return new ModelAndView("redirect:/ingrediente.htm");
        }
       
    }
    
    
    
      public Ingrediente selectIngrediente(String idIngrediente) 
    {
        final Ingrediente ingrediente = new Ingrediente();
        String quer="SELECT\n"
                + "     CODIGO_INGREDIENTE,\n"
                + "     NOMBRE_UNIDAD,\n"
                + "     NOMBRE_INGREDIENTE\n"
                + "FROM\n"
                + "INGREDIENTE inner join\n"
                + " UNIDAD_DE_MEDIDA on UNIDAD_DE_MEDIDA.CODIGO_UNIDAD = INGREDIENTE.CODIGO_UNIDAD"
                + "  WHERE CODIGO_INGREDIENTE='" + idIngrediente + "'";
        return (Ingrediente) jdbcTemplate.query
        (
                quer, new ResultSetExtractor<Ingrediente>() 
            {
                public Ingrediente extractData(ResultSet rs) throws SQLException, DataAccessException {
                    if (rs.next()) {
                        ingrediente.setNombreIngrediente(rs.getString("NOMBRE_INGREDIENTE"));
                        ingrediente.setUnidadMedida(rs.getString("NOMBRE_UNIDAD"));
                        
                        
                        
                    }
                    return ingrediente;
                }


            }
        );
    }
    
        //ENCARGADO ===========================================================
      //LISTAR
    @RequestMapping(value = "Encargado/ingrediente.htm", method = RequestMethod.GET)
    public ModelAndView ingredienteEnc()
    {
          ModelAndView mav= new ModelAndView();
        String sql ="SELECT\n"
                + "     CODIGO_INGREDIENTE,\n"
                + "     NOMBRE_UNIDAD,\n"
                + "     NOMBRE_INGREDIENTE\n"
                + "FROM\n"
                + " INGREDIENTE "
                + " inner join\n"
                + " UNIDAD_DE_MEDIDA on UNIDAD_DE_MEDIDA.CODIGO_UNIDAD = INGREDIENTE.CODIGO_UNIDAD ORDER BY NOMBRE_INGREDIENTE";
        
        List datos=this.jdbcTemplate.queryForList(sql);
        mav.addObject("datos",datos);
        mav.addObject("ingrediente", new Ingrediente());
        mav.setViewName("Encargado/ingrediente");
        return mav;
    }
    //B U S C A R
    @RequestMapping(value="Encargado/ingrediente.htm", method = RequestMethod.POST)
    public ModelAndView formBuscarEnc (@ModelAttribute("ingrediente") Ingrediente ing, BindingResult result, SessionStatus status)  
    {
        this.ingredienteValidate.validate(ing, result);
        if(result.hasErrors())
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
             mav.addObject("ingrediente",new Ingrediente());
             mav.setViewName("Encargado/ingrediente");
             return mav;
        }
        else
        {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("Encargado/ingrediente");
            List ingrediente = this.jdbcTemplate.queryForList("SELECT * FROM INGREDIENTE inner join UNIDAD_DE_MEDIDA on UNIDAD_DE_MEDIDA.CODIGO_UNIDAD = INGREDIENTE.CODIGO_UNIDAD where NOMBRE_INGREDIENTE regexp '"+ing.getNombreIngrediente()+"'");
            mav.addObject("datos", ingrediente);
            mav.addObject("ingrediente",new Ingrediente());
            return mav;
        }
        
    }
    
    //AÑADIR INGREDIENTE ENCARGADO
    @RequestMapping(value="Encargado/AñadirIngrediente.htm" ,method = RequestMethod.GET)
    public ModelAndView añadirIngredienteEnc()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Encargado/AñadirIngrediente");
        List unidadMedida= this.jdbcTemplate.queryForList("select CODIGO_UNIDAD, NOMBRE_UNIDAD from UNIDAD_DE_MEDIDA");
      
        mav.addObject("unidadMedida",unidadMedida);
        mav.addObject("ingrediente",new Ingrediente());
        return mav;
    }
     
    @RequestMapping(value="Encargado/AñadirIngrediente.htm" ,method = RequestMethod.POST)
    public ModelAndView formEnc (@ModelAttribute("ingrediente") Ingrediente ing, BindingResult result, SessionStatus status)  
    {
        
           
            this.jdbcTemplate.update("insert into INGREDIENTE (CODIGO_UNIDAD, NOMBRE_INGREDIENTE) values (?,?)" , ing.getUnidadMedida() ,ing.getNombreIngrediente());
            return new ModelAndView("redirect:/Encargado/ingrediente.htm");
        
        
    }
    
    //EDITAR INGREDIENTE ENCARGADO
    @RequestMapping(value="Encargado/editarIngrediente.htm" ,method=RequestMethod.GET)
    public ModelAndView editarIngredienteEnc (HttpServletRequest request)
    {
        ModelAndView mav = new ModelAndView();
        String idIngrediente = request.getParameter("idIngrediente");
        //int idIngrediente = Integer.parseInt(request.getParameter("idIngrediente"));
        Ingrediente datos = this.selectIngrediente(idIngrediente);
        List unidadMedida= this.jdbcTemplate.queryForList("select CODIGO_UNIDAD, NOMBRE_UNIDAD from UNIDAD_DE_MEDIDA");
        mav.addObject("unidadMedida",unidadMedida);
        mav.setViewName("Encargado/editarIngrediente");
        mav.addObject("ingrediente",new Ingrediente(idIngrediente,datos.getNombreIngrediente(), datos.getUnidadMedida()));
        return mav;
    }
    
     @RequestMapping(value="Encargado/editarIngrediente.htm" , method=RequestMethod.POST)
    public ModelAndView formEnc
        (
                @ModelAttribute("ingrediente") Ingrediente ing,
                BindingResult result,
                SessionStatus status,
                HttpServletRequest request
        )
    {
        
             String idIngrediente = request.getParameter("idIngrediente");
             //int idIngrediente = Integer.parseInt(request.getParameter("idIngrediente"));
             this.jdbcTemplate.update( //REVISAR ORDEN UPDATE 
                    "update INGREDIENTE set NOMBRE_INGREDIENTE=?,CODIGO_UNIDAD=? where CODIGO_INGREDIENTE=? ",
         ing.getNombreIngrediente(),ing.getUnidadMedida(),idIngrediente);
         return new ModelAndView("redirect:/Encargado/ingrediente.htm");
        
       
    }
        
    @RequestMapping(value = "Encargado/ValidarIngrediente.do", method = RequestMethod.GET)
    @ResponseBody
    public void ValidarIngrediente(@RequestParam(value = "nombreIngrediente") String Ingrediente, HttpServletRequest request,
            HttpServletResponse response) {
        String Nombre= request.getParameter("nombreIngrediente");
        JSONValid valida= new JSONValid();
        int ingrediente = this.jdbcTemplate.queryForObject("SELECT COUNT(NOMBRE_INGREDIENTE) from INGREDIENTE where NOMBRE_INGREDIENTE='"+Nombre+"'", Integer.class);
        Boolean valid = true;
        if (ingrediente != 0) {
            valid=false;
        }
        valida.setValid(valid);
        String json = null;
        json = new Gson().toJson(valida);
        response.setContentType("Encargado/AñadirIngrediente");
        try {
            response.getWriter().write(json);
            System.out.println("Ingrediente="+ingrediente);
        } catch (IOException ex) {
            Logger.getLogger(AdministracionController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
      public Ingrediente selectIngredienteEnc(String idIngrediente) 
    {
        final Ingrediente ingrediente = new Ingrediente();
        String quer="SELECT\n"
                + "     CODIGO_INGREDIENTE,\n"
                + "     NOMBRE_UNIDAD,\n"
                + "     NOMBRE_INGREDIENTE\n"
                + "FROM\n"
                + "INGREDIENTE inner join\n"
                + " UNIDAD_DE_MEDIDA on UNIDAD_DE_MEDIDA.CODIGO_UNIDAD = INGREDIENTE.CODIGO_UNIDAD"
                + "  WHERE CODIGO_INGREDIENTE='" + idIngrediente + "'";
        return (Ingrediente) jdbcTemplate.query
        (
                quer, new ResultSetExtractor<Ingrediente>() 
            {
                public Ingrediente extractData(ResultSet rs) throws SQLException, DataAccessException {
                    if (rs.next()) {
                        ingrediente.setNombreIngrediente(rs.getString("NOMBRE_INGREDIENTE"));
                        ingrediente.setUnidadMedida(rs.getString("NOMBRE_UNIDAD"));
                        
                        
                        
                    }
                    return ingrediente;
                }


            }
        );
    }
    
}