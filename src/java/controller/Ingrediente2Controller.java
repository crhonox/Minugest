package controller;


import Modelos.Conexion;
import Modelos.Ingrediente;
import Modelos.IngredienteValidate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.jboss.weld.context.http.Http;
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

@Controller
@RequestMapping("editarIngrediente.htm")
public class Ingrediente2Controller 
{
    private IngredienteValidate ingredienteValidate;
    private JdbcTemplate jdbcTemplate;

    public Ingrediente2Controller() 
    {
        this.ingredienteValidate = new IngredienteValidate();
        Conexion con = new Conexion();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
    }
    @RequestMapping(method=RequestMethod.GET)
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
    
      @RequestMapping(method=RequestMethod.POST)
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
    
    //public Ingrediente selectIngrediente(int idIngrediente) 
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
}
