package controller;

import Modelos.Conexion;
import Modelos.EstadisticaSolicitud;
import Modelos.Ingrediente;
import Modelos.Listado;
import Modelos.Minuta;
import Modelos.MinutaValidate;
import Modelos.PDF;
import Modelos.Receta;
import Modelos.Tag;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
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
        List Minutas = this.jdbcTemplate.queryForList("SELECT MINUTA.*,CASINO.* FROM Minugest.MINUTA inner join CASINO on MINUTA.CODIGO_CASINO=CASINO.CODIGO_CASINO where RUT_EMPRESA='"+rut_emp+"' and CODIGO_SOLICITUD = 0 order by FECHA_MINUTA");
        mav.addObject("minutas",Minutas);
        return mav;
    }
    
    @RequestMapping(value = "/Encargado/AñadirMinuta.htm",method = RequestMethod.GET)
    public ModelAndView AñadirMinuta(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        String idSolicitud = request.getParameter("idSolicitud");
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
    
    @RequestMapping(value = "/Encargado/AñadirMinutas.htm",method = RequestMethod.GET)
    public ModelAndView AñadirMinuta2(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        String idSolicitud = request.getParameter("idSolicitud");
        String fechaSolicitada = request.getParameter("fecha");
        mav.setViewName("Encargado/AñadirMinuta2");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        String user=userDetail.getUsername();
        String rut_emp= this.jdbcTemplate.queryForObject("SELECT RUT_EMPRESA_USUARIO from USUARIO where CODIGO_USUARIO='"+user+"'", String.class);
        List casino = this.jdbcTemplate.queryForList("select CODIGO_CASINO, NOMBRE_CASINO from CASINO where RUT_EMPRESA='"+rut_emp+"'");
        mav.addObject("idSolicitud",idSolicitud);
        mav.addObject("fechaSolicitada",fechaSolicitada);
        mav.addObject("casino",casino);
        mav.addObject("Minuta",new Minuta());
        return mav;
    }
    @RequestMapping(value = "/Encargado/AñadirMinutas.htm",method = RequestMethod.POST)
    public ModelAndView formMinuta2 (@ModelAttribute("Minuta") Minuta min, BindingResult result, SessionStatus status)  
    {
    
          

            this.jdbcTemplate.update("insert into MINUTA (NOMBRE_MINUTA, "
                    + "CODIGO_USUARIO, FECHA_MINUTA,CODIGO_SOLICITUD) "
                    + "values (?,?,?,?)" , min.getNombre_Min(),min.getCodigo_User(),min.getFecha_Min(),min.getIdSolicitud());
            return new ModelAndView("redirect:/Encargado/DetalleSolicitud.htm?idSolicitud="+min.getIdSolicitud());
          
 
    
    }
    
    
    
    @RequestMapping(value = "/Encargado/AñadirMinuta.htm",method = RequestMethod.POST)
    public ModelAndView formMinuta (@ModelAttribute("Minuta") Minuta min, BindingResult result, SessionStatus status)  
    {
    
          

            this.jdbcTemplate.update("insert into MINUTA (NOMBRE_MINUTA, "
                    + "CODIGO_CASINO, CODIGO_USUARIO, FECHA_MINUTA) "
                    + "values (?,?,?,?)" , min.getNombre_Min(),min.getCodigo_Casi(),min.getCodigo_User(),min.getFecha_Min());
            return new ModelAndView("redirect:/Encargado/Minutas.htm");
          
 
    
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
        String rut_emp= this.jdbcTemplate.queryForObject("SELECT RUT_EMPRESA_USUARIO from USUARIO  where CODIGO_USUARIO='"+user+"'", String.class);
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
            return new ModelAndView("redirect:/Encargado/Minutas.htm");
         } 
 
    
    }
    
    @RequestMapping(value = "/Encargado/ModificarMinutaSolicitud.htm",method = RequestMethod.GET)
    public ModelAndView ModificarMinutaSolicitud(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Encargado/ModificarMinutaSolicitud");
        String Codigo = request.getParameter("COD");
        Minuta datos = selectMinutaSolicitud(Codigo);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        String user=userDetail.getUsername();
        String rut_emp= this.jdbcTemplate.queryForObject("SELECT RUT_EMPRESA_USUARIO from USUARIO  where CODIGO_USUARIO='"+user+"'", String.class);
        List casino = this.jdbcTemplate.queryForList("select CODIGO_CASINO, NOMBRE_CASINO from CASINO where RUT_EMPRESA='"+rut_emp+"'");
        mav.addObject("casino",casino);
        mav.addObject("Minuta",new Minuta(datos.getNombre_Min(),Codigo,datos.getCodigo_Casi(),datos.getFecha_Min()));
        return mav;
    }
    
    @RequestMapping(value = "/Encargado/ModificarMinutaSolicitud.htm",method = RequestMethod.POST)
    public ModelAndView UpdateMinutaSolicitud (@ModelAttribute("Minuta") Minuta min, BindingResult result, SessionStatus status,HttpServletRequest request)  
    {
    
             String Codigo = request.getParameter("COD");
             String idSolicitud = request.getParameter("idSolicitud");
              this.jdbcTemplate.update("UPDATE MINUTA SET NOMBRE_MINUTA=?, FECHA_MINUTA=? where CODIGO_MINUTA=?"
                    ,min.getNombre_Min(),min.getFecha_Min(),Codigo);
            return new ModelAndView("redirect:/Encargado/DetalleSolicitud.htm?idSolicitud="+idSolicitud+"");
         
 
    
    }
    
    @RequestMapping(value = "/Encargado/RecetaMinutaSolicitud.htm",method = RequestMethod.GET)
    public ModelAndView RecetaMinutaSolicitud(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Encargado/RecetaMinutaSolicitud");
        String Codigo = request.getParameter("COD");
        Minuta datos = selectMinuta(Codigo);
        List RecetasM = this.jdbcTemplate.queryForList("SELECT RECETAMINUTA.CODIGO_RECETA,NOMBRE_RECETA,RECETAMINUTA.CANTIDAD_PORCION from RECETA inner join RECETAMINUTA on RECETA.CODIGO_RECETA=RECETAMINUTA.CODIGO_RECETA where CODIGO_MINUTA='"+Codigo+"'");
        List Recetas= this.jdbcTemplate.queryForList("SELECT CODIGO_RECETA,NOMBRE_RECETA FROM RECETA");
        mav.addObject("Receta",Recetas);
        mav.addObject("Recetas",RecetasM);
        mav.addObject("COD", Codigo);
        mav.addObject("Minuta",new Minuta(datos.getNombre_Min(),Codigo,datos.getCodigo_Casi(),datos.getFecha_Min()));
        return mav;
    }
    
    
    
     @RequestMapping(value = "/Encargado/RecetaMinutaSolicitud.htm",method = RequestMethod.POST)
    public ModelAndView RecetaInsertMinutaSOlicitud (@ModelAttribute("Minuta") Minuta minuta, BindingResult result, SessionStatus status,HttpServletRequest request)  
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
             
              
            return new ModelAndView("redirect:/Encargado/DetalleMinutaSolicitud.htm?COD="+Codigo+"");
          
 
    
    }
    
    
    @RequestMapping(value = "/Encargado/RecetaMinuta.htm",method = RequestMethod.GET)
    public ModelAndView RecetaMinuta(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Encargado/RecetaMinuta");
        String Codigo = request.getParameter("COD");
        Minuta datos = selectMinuta(Codigo);
        List RecetasM = this.jdbcTemplate.queryForList("SELECT RECETAMINUTA.CODIGO_RECETA,NOMBRE_RECETA,RECETAMINUTA.CANTIDAD_PORCION from RECETA inner join RECETAMINUTA on RECETA.CODIGO_RECETA=RECETAMINUTA.CODIGO_RECETA where CODIGO_MINUTA='"+Codigo+"'");
        List Recetas= this.jdbcTemplate.queryForList("SELECT CODIGO_RECETA,NOMBRE_RECETA FROM RECETA");
        mav.addObject("Receta",Recetas);
        mav.addObject("Recetas",RecetasM);
        mav.addObject("COD", Codigo);
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
    
    @RequestMapping(value = "/Encargado/DetalleMinutaSolicitud.htm",method = RequestMethod.GET)
    public ModelAndView DetalleMinutaSolicitud(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Encargado/DetalleMinutaSolicitud");
        String Codigo = request.getParameter("COD");
        List Minutas = this.jdbcTemplate.queryForList("SELECT MINUTA.* FROM Minugest.MINUTA where CODIGO_MINUTA='"+Codigo+"'");
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        String user=userDetail.getUsername();
        

       
        String Usuario= this.jdbcTemplate.queryForObject("SELECT NOMBRE_USUARIO from USUARIO where CODIGO_USUARIO='"+user+"'", String.class);
        List Recetas = this.jdbcTemplate.queryForList("SELECT RECETAMINUTA.CODIGO_RECETA,NOMBRE_RECETA,RECETAMINUTA.CANTIDAD_PORCION from RECETA inner join RECETAMINUTA on RECETA.CODIGO_RECETA=RECETAMINUTA.CODIGO_RECETA where CODIGO_MINUTA='"+Codigo+"'");
        
        
        mav.addObject("usuario",Usuario);
        mav.addObject("recetas",Recetas);
        mav.addObject("CODM",Codigo);
        mav.addObject("Minutas",Minutas);
        mav.addObject("Minuta",new Minuta());
        return mav;
    }
    
    @RequestMapping(value = "/Encargado/GenerarListado.htm",method = RequestMethod.GET)
    public ModelAndView GenerarListado(){
        ModelAndView mav= new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        String user=userDetail.getUsername();
        String rut_emp= this.jdbcTemplate.queryForObject("SELECT RUT_EMPRESA_USUARIO from USUARIO where CODIGO_USUARIO='"+user+"'", String.class);
        List casino = this.jdbcTemplate.queryForList("select CODIGO_CASINO, NOMBRE_CASINO from CASINO where RUT_EMPRESA='"+rut_emp+"'");
        mav.setViewName("Encargado/GenerarListado");
        mav.addObject("casino",casino);
        mav.addObject("EstadisticaSolicitud",new EstadisticaSolicitud());
        return mav;
    }
    
    @RequestMapping(value = "/Encargado/ListadoInsumos2.htm", method = RequestMethod.POST)
    public ModelAndView downloadExcel(@ModelAttribute("pdf") PDF pdf, BindingResult result, SessionStatus status,HttpServletRequest request ) {
        List<String> ingrediente = new ArrayList<>();
        List<String> cantidad = new ArrayList<>();
        List<String> unidad = new ArrayList<>();
        ingrediente = pdf.getIngrediente();
        cantidad = pdf.getCantidad();
        unidad = pdf.getUnidad();
        List<Ingrediente> insumosFinal = new ArrayList<Ingrediente>();
        for (int i = 0; i < ingrediente.size(); i++) {
            Ingrediente ing = new Ingrediente();
                ing.setCantidadIngrediente(cantidad.get(i));
                ing.setNombreIngrediente(ingrediente.get(i));
                ing.setUnidadMedida(unidad.get(i));
                insumosFinal.add(ing);
        }
        // return a view which will be resolved by an excel view resolver
        return new ModelAndView("pdfView", "ingredientes", insumosFinal);
    }
    
   @RequestMapping(value = "/Encargado/ListadoInsumos.htm",method = RequestMethod.POST)
    public ModelAndView ListadoInsumos(@ModelAttribute("EstadisticaSolicitud") EstadisticaSolicitud esta, BindingResult result, SessionStatus status,HttpServletRequest request){
        ModelAndView mav= new ModelAndView();
        String fechaInicio= esta.getFechaInicio();
        System.out.println("fecha inicio:"+esta.getFechaInicio());
        System.out.println("fecha fin:"+esta.getFechaFin());
        System.out.println("Codigo Casino:"+esta.getRutUser());
        String fechaFin= esta.getFechaFin();
        String codigoCasino=esta.getRutUser();
        List<Listado> insumos = new ArrayList<Listado>();
        List<Ingrediente> insumosFinal = new ArrayList<Ingrediente>();
        final List<Map<String, Object>> rows  = this.jdbcTemplate.queryForList("SELECT * from MINUTA where\n" +
"FECHA_MINUTA BETWEEN  '"+fechaInicio+"' and '"+fechaFin+"' and CODIGO_CASINO="+codigoCasino+"");
        for (Map<String, Object> row : rows) {
            Listado lista = new Listado();
            lista.setCodigo_Minuta((int) row.get("CODIGO_MINUTA"));
            insumos.add(lista);
        }
        
        for (int k = 0; k < insumos.size(); k++) {
            int cod = insumos.get(k).getCodigo_Minuta();
           String Codigo =  String.valueOf(cod);
           List<Receta> rec = getReceta(Codigo);
           for (int i = 0; i < rec.size(); i++) {
            for (int j = 0; j < rec.get(i).getIngredientes().size(); j++) {
                Ingrediente ing = new Ingrediente();
                ing.setIdIngrediente(rec.get(i).getIngredientes().get(j).getIdIngrediente());
                ing.setCantidadIngrediente(rec.get(i).getIngredientes().get(j).getCantidadIngrediente());
                ing.setNombreIngrediente(rec.get(i).getIngredientes().get(j).getNombreIngrediente());
                ing.setUnidadMedida(rec.get(i).getIngredientes().get(j).getUnidadMedida());
                boolean check = false;
                if(insumosFinal.size()>0){
                for (int l = 0; l < insumosFinal.size(); l++) {
                    if (insumosFinal.get(l).getIdIngrediente().equals(ing.getIdIngrediente())) {
                        Double CantidadIngrediente = Double.parseDouble(ing.getCantidadIngrediente());
                        Double CantidadInsumo = Double.parseDouble(insumosFinal.get(l).getCantidadIngrediente());
                        Double Suma = CantidadIngrediente+CantidadInsumo;
                        String insumoCantidad = String.valueOf(Suma);
                        insumosFinal.get(l).setCantidadIngrediente(insumoCantidad);
                        check=true;
                        }
                     }
                }
                if (check == false) {
                    insumosFinal.add(ing);
                }
            }
        }      
       }
        
        mav.setViewName("Encargado/ListaInsumos");
        mav.addObject("insumos",insumosFinal);
        mav.addObject("pdf",new PDF());
        return mav;
    }
    
    @RequestMapping(value = "/Supervisor/ListadoInsumosSolicitud.htm",method = RequestMethod.GET)
    public ModelAndView ListadoInsumosSolicitud(HttpServletRequest request){
        ModelAndView mav= new ModelAndView();
        String idSolicitud= request.getParameter("idSolicitud");
        List<Listado> insumos = new ArrayList<Listado>();
        List<Ingrediente> insumosFinal = new ArrayList<Ingrediente>();
        final List<Map<String, Object>> rows  = this.jdbcTemplate.queryForList("SELECT * from MINUTA where\n" +
"CODIGO_SOLICITUD='"+idSolicitud+"' ");
        for (Map<String, Object> row : rows) {
            Listado lista = new Listado();
            lista.setCodigo_Minuta((int) row.get("CODIGO_MINUTA"));
            insumos.add(lista);
        }
        
        for (int k = 0; k < insumos.size(); k++) {
            int cod = insumos.get(k).getCodigo_Minuta();
           String Codigo =  String.valueOf(cod);
           List<Receta> rec = getReceta(Codigo);
           for (int i = 0; i < rec.size(); i++) {
            for (int j = 0; j < rec.get(i).getIngredientes().size(); j++) {
                Ingrediente ing = new Ingrediente();
                ing.setIdIngrediente(rec.get(i).getIngredientes().get(j).getIdIngrediente());
                ing.setCantidadIngrediente(rec.get(i).getIngredientes().get(j).getCantidadIngrediente());
                ing.setNombreIngrediente(rec.get(i).getIngredientes().get(j).getNombreIngrediente());
                ing.setUnidadMedida(rec.get(i).getIngredientes().get(j).getUnidadMedida());
                boolean check = false;
                if(insumosFinal.size()>0){
                for (int l = 0; l < insumosFinal.size(); l++) {
                    if (insumosFinal.get(l).getIdIngrediente().equals(ing.getIdIngrediente())) {
                        Double CantidadIngrediente = Double.parseDouble(ing.getCantidadIngrediente());
                        Double CantidadInsumo = Double.parseDouble(insumosFinal.get(l).getCantidadIngrediente());
                        Double Suma = CantidadIngrediente+CantidadInsumo;
                        String insumoCantidad = String.valueOf(Suma);
                        insumosFinal.get(l).setCantidadIngrediente(insumoCantidad);
                        check=true;
                        }
                     }
                }
                if (check == false) {
                    insumosFinal.add(ing);
                }
            }
        }      
       }
        
        mav.setViewName("SupervisorC/ListaInsumos");
        mav.addObject("insumos",insumosFinal);
        mav.addObject("idSolicitud",idSolicitud);
        mav.addObject("pdf",new PDF());
        return mav;
    }
    
    @RequestMapping(value = "/Supervisor/ListadoInsumosSolicitud.htm", method = RequestMethod.POST)
    public ModelAndView downloadExcelSolicitud(@ModelAttribute("pdf") PDF pdf, BindingResult result, SessionStatus status,HttpServletRequest request ) {
        List<String> ingrediente = new ArrayList<>();
        List<String> cantidad = new ArrayList<>();
        List<String> unidad = new ArrayList<>();
        ingrediente = pdf.getIngrediente();
        cantidad = pdf.getCantidad();
        unidad = pdf.getUnidad();
        List<Ingrediente> insumosFinal = new ArrayList<Ingrediente>();
        for (int i = 0; i < ingrediente.size(); i++) {
            Ingrediente ing = new Ingrediente();
                ing.setCantidadIngrediente(cantidad.get(i));
                ing.setNombreIngrediente(ingrediente.get(i));
                ing.setUnidadMedida(unidad.get(i));
                insumosFinal.add(ing);
        }
        // return a view which will be resolved by an excel view resolver
        return new ModelAndView("pdfView", "ingredientes", insumosFinal);
    }
    
@RequestMapping(value = "/Encargado/CalculoIngrediente.htm",method = RequestMethod.GET)
    public ModelAndView CalculoReceta(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Encargado/test");
        String Codigo = request.getParameter("COD");
        Minuta datos = selectMinuta(Codigo);
        String Nombre=datos.getNombre_Min();
        String fecha=datos.getFecha_Min();
        
        
        List<Receta> rec = getReceta(Codigo);
        List<Receta> RecetaMinuta = new ArrayList<Receta>();
        
        for (int i = 0; i < rec.size(); i++) {
            Receta receta = new Receta();
            String cantidadOriginalS = this.jdbcTemplate.queryForObject("SELECT CANTIDAD_PORCION from RECETA where CODIGO_RECETA ='"+rec.get(i).getIdReceta()+"'", String.class);
            int cantidadOriginal=Integer.parseInt(cantidadOriginalS);
            int cantidadMinuta=Integer.parseInt(rec.get(i).getPorcionReceta());
            double facConv = cantidadMinuta/cantidadOriginal;
            for (int j = 0; j < rec.get(i).getIngredientes().size(); j++) {
                Ingrediente ing = new Ingrediente();
                ing.setIdIngrediente(rec.get(i).getIngredientes().get(j).getIdIngrediente());
                int cantidadOriginalIng = Integer.parseInt(rec.get(i).getIngredientes().get(j).getCantidadIngrediente());
                double CantidadFinal = cantidadOriginalIng*facConv;
                String cantidad=""+CantidadFinal;
                ing.setCantidadIngrediente(cantidad);
                ing.setNombreIngrediente(rec.get(i).getIngredientes().get(j).getNombreIngrediente());
                ing.setUnidadMedida(rec.get(i).getIngredientes().get(j).getUnidadMedida());
                receta.addIngrediente(ing);
            }
            receta.setIdReceta(rec.get(i).getIdReceta());
            receta.setNombreReceta(rec.get(i).getNombreReceta());
            RecetaMinuta.add(receta);
        }
        
       
        
        mav.addObject("REC",RecetaMinuta);
        mav.addObject("NombreMinuta",Nombre);
        mav.addObject("fecha",fecha);
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
     public Minuta selectMinutaSolicitud(String COD) 
    {
        final Minuta minuta = new Minuta();
        String quer="SELECT\n"
                + "     NOMBRE_MINUTA,\n"
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
	final List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        
	for (Map<String, Object> row : rows) {
		Receta receta = new Receta();
		receta.setIdReceta((String) row.get("CODIGO_RECETA").toString());
		receta.setNombreReceta((String) row.get("NOMBRE_RECETA"));
		receta.setPorcionReceta((String) row.get("CANTIDAD_PORCION"));
                String cantidadOriginalS = this.jdbcTemplate.queryForObject("SELECT CANTIDAD_PORCION from RECETA where CODIGO_RECETA ='"+receta.getIdReceta()+"'", String.class);
            int cantidadOriginal=Integer.parseInt(cantidadOriginalS);
            int cantidadMinuta=Integer.parseInt(receta.getPorcionReceta());
            double facConv = cantidadMinuta/cantidadOriginal;
                final String query="SELECT CODIGO_RECETA,RECETAINGREDIENTE.CODIGO_INGREDIENTE,CANTIDAD_INGREDIENTE,INGREDIENTE.NOMBRE_INGREDIENTE,UNIDAD_DE_MEDIDA.NOMBRE_UNIDAD from RECETAINGREDIENTE inner join INGREDIENTE on INGREDIENTE.CODIGO_INGREDIENTE=RECETAINGREDIENTE.CODIGO_INGREDIENTE inner join UNIDAD_DE_MEDIDA on UNIDAD_DE_MEDIDA.CODIGO_UNIDAD=INGREDIENTE.CODIGO_UNIDAD where RECETAINGREDIENTE.CODIGO_RECETA='"+receta.getIdReceta()+"'";
                final List<Map<String, Object>> filas = jdbcTemplate.queryForList(query);
                for (Map<String, Object> fila : filas){
                    Ingrediente ingrediente = new Ingrediente();
                    ingrediente.setIdIngrediente((String) fila.get("CODIGO_INGREDIENTE").toString());
                    ingrediente.setNombreIngrediente((String) fila.get("NOMBRE_INGREDIENTE"));
                    ingrediente.setCantidadIngrediente((String) fila.get("CANTIDAD_INGREDIENTE").toString());
                    int cantidadOriginalIng = Integer.parseInt(ingrediente.getCantidadIngrediente());
                    double CantidadFinal = cantidadOriginalIng*facConv;
                    String cantidad=""+CantidadFinal;
                    ingrediente.setCantidadIngrediente(cantidad);
                    ingrediente.setUnidadMedida((String) fila.get("NOMBRE_UNIDAD"));
                    receta.addIngrediente(ingrediente);
        }
        recetas.add(receta);
	}
	return recetas;
    }
     
     @RequestMapping(value = "/Supervisor/DetalleMinutaSolicitud.htm",method = RequestMethod.GET)
    public ModelAndView DetalleMinutaSuper(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("SupervisorC/DetalleMinuta");
        String Codigo = request.getParameter("COD");
        List Minutas = this.jdbcTemplate.queryForList("SELECT MINUTA.* FROM Minugest.MINUTA where CODIGO_MINUTA='"+Codigo+"'");
        Minuta datos = selectMinutaSolicitud(Codigo);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        String user=userDetail.getUsername();
        String Nombre=datos.getNombre_Min();
        String fecha=datos.getFecha_Min();
        String Usuario= this.jdbcTemplate.queryForObject("SELECT NOMBRE_USUARIO from USUARIO where CODIGO_USUARIO='"+user+"'", String.class);
        List Recetas = this.jdbcTemplate.queryForList("SELECT RECETAMINUTA.CODIGO_RECETA,NOMBRE_RECETA,RECETAMINUTA.CANTIDAD_PORCION from RECETA inner join RECETAMINUTA on RECETA.CODIGO_RECETA=RECETAMINUTA.CODIGO_RECETA where CODIGO_MINUTA='"+Codigo+"'");
        mav.addObject("nombre",Nombre);
        mav.addObject("fecha",fecha);
        mav.addObject("usuario",Usuario);
        mav.addObject("recetas",Recetas);
        mav.addObject("CODM",Codigo);
        mav.addObject("Minutas",Minutas);
        mav.addObject("Minuta",new Minuta(datos.getNombre_Min(),Codigo,datos.getCodigo_Casi(),datos.getFecha_Min()));
        return mav;
    }
     
}
