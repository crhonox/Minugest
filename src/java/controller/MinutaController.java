package controller;

import Modelos.Conexion;
import Modelos.Minuta;
import Modelos.MinutaValidate;
import Modelos.Receta;
import Modelos.Tag;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MinutaController {
    
     private JdbcTemplate jdbcTemplate;
     private MinutaValidate minutaValidate;
     List<Tag> data = new ArrayList<Tag>();//PRUEBA
     
    public MinutaController(){
        
        Conexion con= new Conexion();
        this.minutaValidate = new MinutaValidate();
        this.jdbcTemplate=new JdbcTemplate(con.conectar());
        data.add(new Tag(1, "ruby"));//PRUEBA
		data.add(new Tag(2, "rails"));
		data.add(new Tag(3, "c / c++"));
		data.add(new Tag(4, ".net"));
		data.add(new Tag(5, "python"));
		data.add(new Tag(6, "java"));
		data.add(new Tag(7, "javascript"));
		data.add(new Tag(8, "jscript"));
    }
//-----------------------------CODIGO DE PRUEBA-------------------//    
@RequestMapping(value = "Encargado/getTags", method = RequestMethod.GET)
	public @ResponseBody
	List<Tag> getTags(@RequestParam String tagName) {

		return simulateSearchResult(tagName);

	}
    
 private List<Tag> simulateSearchResult(String tagName) {

		List<Tag> result = new ArrayList<Tag>();

		// iterate a list and filter by tagName
		for (Tag tag : data) {
			if (tag.getTagName().contains(tagName)) {
				result.add(tag);
			}
		}

		return result;
	}       
  //---------------------------CODIGO DE PUREBA--------------------------//  
    @RequestMapping(value = "/Encargado/Minuta.htm")
    public ModelAndView Minuta(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Encargado/Minutas");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        String user=userDetail.getUsername();
        String rut_emp= this.jdbcTemplate.queryForObject("SELECT RUT_EMPRESA from USUARIO inner join CASINO on USUARIO.CODIGO_CASINO = CASINO.CODIGO_CASINO where CODIGO_USUARIO='"+user+"'", String.class);
        List Minutas = this.jdbcTemplate.queryForList("SELECT MINUTA.*,CASINO.* FROM Minugest.MINUTA inner join CASINO on MINUTA.CODIGO_CASINO=CASINO.CODIGO_CASINO where RUT_EMPRESA='"+rut_emp+"'");
        mav.addObject("minutas",Minutas);
        return mav;
    }
    
    @RequestMapping(value = "/Encargado/AñadirMinuta.htm",method = RequestMethod.GET)
    public ModelAndView AñadirMinuta(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Encargado/AñadirMinuta");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        String user=userDetail.getUsername();
        String rut_emp= this.jdbcTemplate.queryForObject("SELECT RUT_EMPRESA from USUARIO inner join CASINO on USUARIO.CODIGO_CASINO = CASINO.CODIGO_CASINO where CODIGO_USUARIO='"+user+"'", String.class);
        List casino = this.jdbcTemplate.queryForList("select CODIGO_CASINO, NOMBRE_CASINO from CASINO where RUT_EMPRESA='"+rut_emp+"'");
        mav.addObject("casino",casino);
        mav.addObject("Minuta",new Minuta());
        return mav;
    }
    
    @RequestMapping(value = "/Encargado/AñadirMinuta.htm",method = RequestMethod.POST)
    public ModelAndView formMinuta (@ModelAttribute("Minuta") Minuta min, BindingResult result, SessionStatus status)  
    {
    this.minutaValidate.validate(min, result);
        if(result.hasErrors())
        {
                ModelAndView mav = new ModelAndView();
                mav.setViewName("Encargado/AñadirMinuta");
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                UserDetails userDetail = (UserDetails) auth.getPrincipal();
                String user=userDetail.getUsername();
                String rut_emp= this.jdbcTemplate.queryForObject("SELECT RUT_EMPRESA from USUARIO inner join CASINO on USUARIO.CODIGO_CASINO = CASINO.CODIGO_CASINO where CODIGO_USUARIO='"+user+"'", String.class);
                List casino = this.jdbcTemplate.queryForList("select CODIGO_CASINO, NOMBRE_CASINO from CASINO where RUT_EMPRESA='"+rut_emp+"'");
                mav.addObject("casino",casino);
                mav.addObject("Minuta",new Minuta());
                return mav;
        }else
        {
          

            this.jdbcTemplate.update("insert into MINUTA (NOMBRE_MINUTA, "
                    + "CODIGO_CASINO, CODIGO_USUARIO, FECHA_MINUTA) "
                    + "values (?,?,?,?)" , min.getNombre_Min(),min.getCodigo_Casi(),min.getCodigo_User(),min.getFecha_Min());
            return new ModelAndView("redirect:/Encargado/Minuta.htm");
         } 
 
    
    }
    
    

    @RequestMapping(value = "/Encargado/ModificarMinuta.htm",method = RequestMethod.GET)
    public ModelAndView ModificarMinuta(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Encargado/ModificarMinuta");
        String Codigo = request.getParameter("COD");
        Minuta datos = selectMinuta(Codigo);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        String user=userDetail.getUsername();
        String rut_emp= this.jdbcTemplate.queryForObject("SELECT RUT_EMPRESA from USUARIO inner join CASINO on USUARIO.CODIGO_CASINO = CASINO.CODIGO_CASINO where CODIGO_USUARIO='"+user+"'", String.class);
        List casino = this.jdbcTemplate.queryForList("select CODIGO_CASINO, NOMBRE_CASINO from CASINO where RUT_EMPRESA='"+rut_emp+"'");
        mav.addObject("casino",casino);
        mav.addObject("Minuta",new Minuta(datos.getNombre_Min(),Codigo,datos.getCodigo_Casi(),datos.getFecha_Min()));
        return mav;
    }
    
    @RequestMapping(value = "/Encargado/ModificarMinuta.htm",method = RequestMethod.POST)
    public ModelAndView UpdateMinuta (@ModelAttribute("Minuta") Minuta min, BindingResult result, SessionStatus status,HttpServletRequest request)  
    {
    this.minutaValidate.validate(min, result);
        if(result.hasErrors())
        {
               ModelAndView mav = new ModelAndView();
        mav.setViewName("Encargado/ModificarMinuta");
        String Codigo = request.getParameter("COD");
        Minuta datos = selectMinuta(Codigo);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        String user=userDetail.getUsername();
        String rut_emp= this.jdbcTemplate.queryForObject("SELECT RUT_EMPRESA from USUARIO inner join CASINO on USUARIO.CODIGO_CASINO = CASINO.CODIGO_CASINO where CODIGO_USUARIO='"+user+"'", String.class);
        List casino = this.jdbcTemplate.queryForList("select CODIGO_CASINO, NOMBRE_CASINO from CASINO where RUT_EMPRESA='"+rut_emp+"'");
        mav.addObject("casino",casino);
        mav.addObject("Minuta",new Minuta(datos.getNombre_Min(),Codigo,datos.getCodigo_Casi(),datos.getFecha_Min()));
        return mav;
        }else
        {
             String Codigo = request.getParameter("COD");
              this.jdbcTemplate.update("UPDATE MINUTA SET NOMBRE_MINUTA=?,CODIGO_CASINO=?, FECHA_MINUTA=? where CODIGO_MINUTA=?"
                    ,min.getNombre_Min(),min.getCodigo_Casi(),min.getFecha_Min(),Codigo);
            return new ModelAndView("redirect:/Encargado/Minuta.htm");
         } 
 
    
    }
    
    @RequestMapping(value = "/Encargado/RecetaMinuta.htm",method = RequestMethod.GET)
    public ModelAndView RecetaMinuta(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Encargado/RecetaMinuta");
        String Codigo = request.getParameter("COD");
        Minuta datos = selectMinuta(Codigo);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        String user=userDetail.getUsername();
        String rut_emp= this.jdbcTemplate.queryForObject("SELECT RUT_EMPRESA from USUARIO inner join CASINO on USUARIO.CODIGO_CASINO = CASINO.CODIGO_CASINO where CODIGO_USUARIO='"+user+"'", String.class);
        List casino = this.jdbcTemplate.queryForList("select CODIGO_CASINO, NOMBRE_CASINO from CASINO where RUT_EMPRESA='"+rut_emp+"'");
        mav.addObject("casino",casino);
        List Recetas= this.jdbcTemplate.queryForList("SELECT NOMBRE_RECETA FROM RECETA");
        mav.addObject("Receta",Recetas);
        mav.addObject("ReMinuta",new Minuta(datos.getNombre_Min(),Codigo,datos.getCodigo_Casi(),datos.getFecha_Min()));
        return mav;
    }
    
     public Minuta selectMinuta(String COD) 
    {
        final Minuta minuta = new Minuta();
        String quer="SELECT\n"
                + "     NOMBRE_MINUTA,\n"
                + "     CODIGO_CASINO,\n"
                + "     FECHA_MINUTA\n"
                + "FROM\n"
                + "MINUTA"
                + "  WHERE CODIGO_MINUTA='" + COD + "'";
        return (Minuta) jdbcTemplate.query
        (
                quer, new ResultSetExtractor<Minuta>() 
            {
                public Minuta extractData(ResultSet rs) throws SQLException, DataAccessException {
                    if (rs.next()) {
                        minuta.setCodigo_Casi(rs.getString("CODIGO_CASINO"));
                        minuta.setFecha_Min(rs.getString("FECHA_MINUTA"));
                        minuta.setNombre_Min(rs.getString("NOMBRE_MINUTA"));
                        
                        
                        
                    }
                    return minuta;
                }


            }
        );
    }
}
