package controller;

import Modelos.Conexion;
import Modelos.Ingrediente;
import Modelos.Minuta;
import Modelos.MinutaValidate;
import Modelos.Receta;
import Modelos.Tag;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
        
    }
//----------------------------INICIO-CODIGO DE PRUEBA-------------------//    
@RequestMapping(value = "Encargado/getTags", method = RequestMethod.GET)
	public @ResponseBody
	List<Tag> getTags(@RequestParam String tagName) {

		return simulateSearchResult(tagName);

	}
    
 private List<Tag> simulateSearchResult(String tagName) {

		List<Tag> result = new ArrayList<Tag>();

		// iterate a list and filter by tagName
                String quer="SELECT CODIGO_RECETA,NOMBRE_RECETA FROM RECETA";


	List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(quer);
	for (Map row : rows) {
		Tag tag = new Tag();
		tag.setId((int) (row.get("CODIGO_RECETA")));
		tag.setTagName((String) (row.get("NOMBRE_RECETA")));
		data.add(new Tag(tag.getId(),tag.getTagName()));
		;
	}

                
		for (Tag tag : data) {
			if (tag.getTagName().contains(tagName)) {
				result.add(tag);
			}
		}

		return result;
	}       
  //---------------------------FIN-CODIGO DE PUREBA--------------------------//  
    @RequestMapping(value = "/Encargado/Minutas.htm")
    public ModelAndView Minuta(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Encargado/Minutas");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        String user=userDetail.getUsername();
        String rut_emp= this.jdbcTemplate.queryForObject("SELECT RUT_EMPRESA_USUARIO from USUARIO where CODIGO_USUARIO='"+user+"'", String.class);
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
        String rut_emp= this.jdbcTemplate.queryForObject("SELECT RUT_EMPRESA_USUARIO from USUARIO where CODIGO_USUARIO='"+user+"'", String.class);
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
        List Recetas= this.jdbcTemplate.queryForList("SELECT CODIGO_RECETA,NOMBRE_RECETA FROM RECETA");
        mav.addObject("Receta",Recetas);
        mav.addObject("Minuta",new Minuta(datos.getNombre_Min(),Codigo,datos.getCodigo_Casi(),datos.getFecha_Min()));
        return mav;
    }
    
    
    
     @RequestMapping(value = "/Encargado/RecetaMinuta.htm",method = RequestMethod.POST)
    public ModelAndView RecetaInsertMinuta (@ModelAttribute("Minuta") Minuta minuta, BindingResult result, SessionStatus status,HttpServletRequest request)  
    {
             String Codigo = request.getParameter("COD");
             List<String> lista = new ArrayList<>();
             List<String> lista2 = new ArrayList<>();
             lista = minuta.getCombobox();
             lista2 = minuta.getCantidad();
             for (int i = 0; i < lista.size(); i++) {
            this.jdbcTemplate.update("insert into RECETAMINUTA (CODIGO_RECETA, "
                    + "CODIGO_MINUTA,CANTIDAD_PORCION)"
                    + "values (?,?,?)" , lista.get(i),Codigo,lista2.get(i));
        }
             
              
            return new ModelAndView("redirect:/Encargado/DetalleMinuta.htm?COD="+Codigo+"");
          
 
    
    }
    
    @RequestMapping(value = "/Encargado/DetalleMinuta.htm",method = RequestMethod.GET)
    public ModelAndView DetalleMinuta(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Encargado/DetalleMinuta");
        String Codigo = request.getParameter("COD");
        Minuta datos = selectMinuta(Codigo);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        String user=userDetail.getUsername();
        String Nombre=datos.getNombre_Min();
        String fecha=datos.getFecha_Min();
        String codcasino=datos.getCodigo_Casi();
        String Casino=this.jdbcTemplate.queryForObject("SELECT NOMBRE_CASINO from CASINO where CODIGO_CASINO='"+codcasino+"'", String.class);
        String Usuario= this.jdbcTemplate.queryForObject("SELECT NOMBRE_USUARIO from USUARIO where CODIGO_USUARIO='"+user+"'", String.class);
        List Recetas = this.jdbcTemplate.queryForList("SELECT RECETAMINUTA.CODIGO_RECETA,NOMBRE_RECETA,RECETAMINUTA.CANTIDAD_PORCION from RECETA inner join RECETAMINUTA on RECETA.CODIGO_RECETA=RECETAMINUTA.CODIGO_RECETA where CODIGO_MINUTA='"+Codigo+"'");
        mav.addObject("casino",Casino);
        mav.addObject("nombre",Nombre);
        mav.addObject("fecha",fecha);
        mav.addObject("usuario",Usuario);
        mav.addObject("recetas",Recetas);
        mav.addObject("CODM",Codigo);
        mav.addObject("Minuta",new Minuta(datos.getNombre_Min(),Codigo,datos.getCodigo_Casi(),datos.getFecha_Min()));
        return mav;
    }
   
    @RequestMapping(value = "/Encargado/test.htm",method = RequestMethod.GET)
    public ModelAndView CalculoReceta(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Encargado/test");
        String Codigo = request.getParameter("COD");
        Minuta datos = selectMinuta(Codigo);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        String user=userDetail.getUsername();
        String Nombre=datos.getNombre_Min();
        String fecha=datos.getFecha_Min();
        String codcasino=datos.getCodigo_Casi();
        String Casino=this.jdbcTemplate.queryForObject("SELECT NOMBRE_CASINO from CASINO where CODIGO_CASINO='"+codcasino+"'", String.class);
        String Usuario= this.jdbcTemplate.queryForObject("SELECT NOMBRE_USUARIO from USUARIO where CODIGO_USUARIO='"+user+"'", String.class);
        List Recetas = this.jdbcTemplate.queryForList("SELECT RECETAMINUTA.CODIGO_RECETA,NOMBRE_RECETA,RECETAMINUTA.CANTIDAD_PORCION from RECETA inner join RECETAMINUTA on RECETA.CODIGO_RECETA=RECETAMINUTA.CODIGO_RECETA where CODIGO_MINUTA='"+Codigo+"'");
        List<Receta> rec = getReceta(Codigo);
        
        
        
        mav.addObject("casino",Casino);
        mav.addObject("nombre",Nombre);
        mav.addObject("fecha",fecha);
        mav.addObject("usuario",Usuario);
        mav.addObject("recetas",Recetas);
        mav.addObject("CODM",Codigo);
        mav.addObject("Minuta",new Minuta(datos.getNombre_Min(),Codigo,datos.getCodigo_Casi(),datos.getFecha_Min()));
        return mav;
    }
    
    @RequestMapping(value = "/Encargado/EliminarReceta.htm",method = RequestMethod.GET)
    public ModelAndView EliminarReceta(HttpServletRequest request){
        String CodigoM = request.getParameter("CODM");
        String CodigoR = request.getParameter("CODR");
        String Query = "DELETE FROM `Minugest`.`RECETAMINUTA` WHERE CODIGO_RECETA='"+CodigoR+"' AND CODIGO_MINUTA='"+CodigoM+"'";
        this.jdbcTemplate.execute(Query);
        return new ModelAndView("redirect:/Encargado/DetalleMinuta.htm?COD="+CodigoM);
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
     
     public List<Receta> getReceta(String COD) {
	final String sql = "select RECETAMINUTA.CODIGO_RECETA,NOMBRE_RECETA,RECETAMINUTA.CANTIDAD_PORCION,CODIGO_MINUTA from RECETAMINUTA inner join RECETA on RECETAMINUTA.CODIGO_RECETA = RECETA.CODIGO_RECETA WHERE CODIGO_MINUTA='"+COD+"'";
	final List<Receta> recetas = new ArrayList<Receta>();
        final List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
	final List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        
	for (Map<String, Object> row : rows) {
		Receta receta = new Receta();
		receta.setIdReceta((String) row.get("CODIGO_RECETA"));
		receta.setNombreReceta((String) row.get("NOMBRE_RECETA"));
		receta.setPorcionReceta((String) row.get("CANTIDAD_PORCION"));
		recetas.add(receta);
                final String query="select ";
        final List<Map<String, Object>> filas = jdbcTemplate.queryForList(query);
	}
	return recetas;
    }
     
     
}
