
package controller;

import Modelos.Casino;
import Modelos.CasinoValidate;
import Modelos.Cliente;
import Modelos.Conexion;
import Modelos.Usuario;
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

@Controller
public class CasinoController {
    
    private JdbcTemplate jdbcTemplate;
    private CasinoValidate casinoValidate;
    
    public CasinoController(){ 
        this.casinoValidate=new CasinoValidate();
        Conexion con= new Conexion();
        this.jdbcTemplate=new JdbcTemplate(con.conectar());
    }
    
    @RequestMapping(value = "Administracion/listaCasino.htm")
    public ModelAndView listadoCasinoEMPRESA(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        String rut= request.getParameter("rut");
        List datos2=this.jdbcTemplate.queryForList("SELECT COMUNA_NOMBRE,REGION_NOMBRE,PROVINCIA_NOMBRE,CASINO.CODIGO_CASINO,CASINO.RUT_EMPRESA,NOMBRE_CASINO,DIRECCION_CASINO,NOMBRE_EMPRESA FROM region  inner join  provincia on REGION_ID = PROVINCIA_REGION_ID inner join comuna on PROVINCIA_ID = COMUNA_PROVINCIA_ID inner join CASINO on COMUNA_CASINO = COMUNA_ID inner join EMPRESA on CASINO.RUT_EMPRESA = EMPRESA.RUT_EMPRESA where CASINO.RUT_EMPRESA = '"+rut+"'");
        mav.addObject("datos",datos2);
        String NombreEmpresa=this.jdbcTemplate.queryForObject("SELECT NOMBRE_EMPRESA from EMPRESA where RUT_EMPRESA='"+rut+"'", String.class);
        mav.addObject("rutEmp",rut);
        mav.addObject("nomEmp",NombreEmpresa);
        Cliente datos=this.selectCliente(rut);
        mav.addObject("cliente",new Cliente(rut,datos.getNombre(),datos.getEmail(),datos.getTelefono(),datos.getComuna(),datos.getRegion(),datos.getDireccion()));
        mav.setViewName("Administracion/listaCasino");
        return mav;
    }
    
    
    @RequestMapping(value = "Administracion/AñadirCasino.htm",method = RequestMethod.GET)
    public ModelAndView añadirCasino(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        String rut = request.getParameter("rutemp");
        mav.setViewName("Administracion/AñadirCasino");
        List regiones= this.jdbcTemplate.queryForList("select REGION_ID, REGION_NOMBRE from region");
        mav.addObject("regiones",regiones);
        mav.addObject("rutemp",rut);
        mav.addObject("casino", new Casino());  
        return mav;
    }
    
    @RequestMapping(value = "Administracion/AñadirCasino.htm",method = RequestMethod.POST)
    public ModelAndView form(@ModelAttribute ("casino") Casino cas,BindingResult result, SessionStatus status ,HttpServletRequest request )
    {
        
        this.jdbcTemplate.update("INSERT INTO CASINO (RUT_EMPRESA,NOMBRE_CASINO,REGION_CASINO,PROVINCIA_CASINO,COMUNA_CASINO,DIRECCION_CASINO) VALUES (?,?,?,?,?,?)"
                                      ,cas.getRutEmpresa(),cas.getNombreCasino(),cas.getRegionCasino(),cas.getProvinciaCasino(),cas.getComunaCasino(),cas.getDireccionCasino());
       
        return new ModelAndView("redirect:listaCasino.htm?rut="+cas.getRutEmpresa());
        
        
    }
    
    @RequestMapping(value = "Administracion/eliminarCasino.htm") 
    public ModelAndView eliminarCasino(HttpServletRequest request){
        
         String cod=request.getParameter("cod");
         Casino datos=this.selectCasino(cod);
         this.jdbcTemplate.update(
                    "DELETE FROM CASINO WHERE CODIGO_CASINO=?",
                    cod);
         return new ModelAndView("redirect:listaCasino.htm?rut="+datos.getRutEmpresa());
    }
     
     @RequestMapping(value ="Administracion/editarCasino.htm" ,method=RequestMethod.GET) 
    public ModelAndView editarCasino(HttpServletRequest request)
    {
        ModelAndView mav=new ModelAndView();
        String cod=request.getParameter("cod");
        Casino datos=this.selectCasino(cod);
        List regiones= this.jdbcTemplate.queryForList("select REGION_ID, REGION_NOMBRE from region");
        mav.addObject("regiones",regiones);
        mav.addObject("COD",datos.getRutEmpresa());
        mav.setViewName("Administracion/editarCasino");
        mav.addObject("casino",new Casino(datos.getRutEmpresa(),datos.getNombreCasino(),datos.getRegionCasino(),datos.getProvinciaCasino(),datos.getComunaCasino(),datos.getDireccionCasino()));
        return mav;
    }
    
     @RequestMapping(value ="Administracion/DetalleCasino.htm" ,method=RequestMethod.GET) 
    public ModelAndView detalleCasino(HttpServletRequest request)
    {
        ModelAndView mav=new ModelAndView();
        String cod=request.getParameter("cod");
        Casino datos=this.selectCasino(cod);
        List usuarios =this.jdbcTemplate.queryForList("SELECT RUT_USUARIO,ID_CASINO,Concat(USUARIO.NOMBRE_USUARIO,' ',USUARIO.APELLIDO_USUARIO) as Nombre,USUARIO.CODIGO_PERFIL,NOMBE_PERFIL,CORREO_USUARIO from USUARIO_CASINO inner join USUARIO on RUT_USUARIO=CODIGO_USUARIO inner join PERFIL on USUARIO.CODIGO_PERFIL=PERFIL.CODIGO_PERFIL "
                + "where ID_CASINO='"+cod+"'");
        mav.addObject("usuarios",usuarios);
        mav.addObject("nomcas",datos.getNombreCasino());
        mav.addObject("casreg",datos.getRegionCasino());
        mav.addObject("caspro",datos.getProvinciaCasino());
        mav.addObject("cascom",datos.getComunaCasino());
        mav.addObject("casdir",datos.getDireccionCasino());
        mav.addObject("rutEmp",datos.getRutEmpresa());
        mav.addObject("cod",cod);
        mav.setViewName("Administracion/DetalleCasino");
        mav.addObject("casino",new Casino(datos.getRutEmpresa(),datos.getNombreCasino(),datos.getRegionCasino(),datos.getProvinciaCasino(),datos.getComunaCasino(),datos.getDireccionCasino()));
        return mav;
    }
    @RequestMapping(value="Administracion/DesasignarUsuario.htm",method = RequestMethod.GET)
    public ModelAndView formdeleteCasino(@ModelAttribute ("usuario") Usuario user,BindingResult result, SessionStatus status , HttpServletRequest request )
    {
        String rutUser=request.getParameter("RUT");
        String COD=request.getParameter("COD");
        String Query = "DELETE FROM `Minugest`.`USUARIO_CASINO` WHERE `ID_CASINO`='"+COD+"' and`RUT_USUARIO`='"+rutUser+"';";
        this.jdbcTemplate.execute(Query);
        return new ModelAndView("redirect:DetalleCasino.htm?cod="+COD+"");
    }
    
    @RequestMapping(value ="Administracion/editarCasino.htm" ,method=RequestMethod.POST)
    public ModelAndView formEditCasino
        (
                @ModelAttribute("casino") Casino cas,
                BindingResult result,
                SessionStatus status,
                HttpServletRequest request
        )
    {
        
            String cod=request.getParameter("cod");
            this.jdbcTemplate.update(
                    "UPDATE CASINO SET NOMBRE_CASINO=?,REGION_CASINO=?,PROVINCIA_CASINO=?,COMUNA_CASINO=?,DIRECCION_CASINO=? WHERE CODIGO_CASINO=?",
         cas.getNombreCasino(),cas.getRegionCasino(),cas.getProvinciaCasino(),cas.getComunaCasino(),cas.getDireccionCasino(),cod);
         return new ModelAndView("redirect:listaCasino.htm?rut="+cas.getRutEmpresa());
        
       
    }
        
        @RequestMapping(value="Administracion/UsuarioCasino.htm",method=RequestMethod.GET) 
    public ModelAndView UsuarioCasino(HttpServletRequest request)
    {
        ModelAndView mav=new ModelAndView();
        String cod=request.getParameter("cod");
        Casino datos=this.selectCasino(cod);
        List usuario = this.jdbcTemplate.queryForList("SELECT CODIGO_USUARIO,Concat(USUARIO.NOMBRE_USUARIO,' ',USUARIO.APELLIDO_USUARIO) as Nombre from USUARIO where RUT_EMPRESA_USUARIO='"+datos.getRutEmpresa()+"'");
        mav.addObject("usuario",usuario);
       Casino formc = new Casino();
       formc.setCodigoCasino(cod);
       formc.setNombreCasino(datos.getNombreCasino());
        mav.setViewName("Administracion/UsuarioCasino");
        mav.addObject("Casino",formc);
        return mav;
    }
    
     @RequestMapping(value="Administracion/UsuarioCasino.htm",method=RequestMethod.POST) 
    public ModelAndView UsuarioCasinoPost(@ModelAttribute("Casino") Casino cas, BindingResult result, SessionStatus status,HttpServletRequest request)
    {
       
      
             List<String> lista = new ArrayList<>();
             lista =cas.getCombobox();
             for (int i = 0; i < lista.size(); i++) {
            this.jdbcTemplate.update("insert into USUARIO_CASINO (RUT_USUARIO, "
                    + "ID_CASINO)"
                    + "values (?,?)", lista.get(i),cas.getCodigoCasino());
        }
             
              
            return new ModelAndView("redirect:DetalleCasino.htm?cod="+cas.getCodigoCasino()+"");
        
 
    }
    
    
    public Casino selectCasino(String cod) 
    {
        final Casino casino = new Casino();
       // String quer = "SELECT * FROM EMPRESA WHERE RUT_EMPRESA='" + rut+"'";
        String quer="SELECT NOMBRE_CASINO,RUT_EMPRESA,COMUNA_NOMBRE,REGION_NOMBRE,PROVINCIA_NOMBRE,DIRECCION_CASINO FROM CASINO inner join region on REGION_ID=REGION_CASINO inner join  provincia on PROVINCIA_CASINO = PROVINCIA_ID inner join  comuna on COMUNA_CASINO = COMUNA_ID  WHERE CODIGO_CASINO='" + cod+"'";
        return (Casino) jdbcTemplate.query
        (
                quer, new ResultSetExtractor<Casino>() 
            {
                @Override
                public Casino extractData(ResultSet rs) throws SQLException, DataAccessException 
                {
                    if (rs.next()) 
                    {
                        casino.setNombreCasino(rs.getString("NOMBRE_CASINO"));
                        casino.setRutEmpresa(rs.getString("RUT_EMPRESA"));
                        casino.setComunaCasino(rs.getString("COMUNA_NOMBRE"));
                        casino.setRegionCasino(rs.getString("REGION_NOMBRE"));
                        casino.setProvinciaCasino(rs.getString("PROVINCIA_NOMBRE"));
                        casino.setDireccionCasino(rs.getString("DIRECCION_CASINO"));
                        
                        
                    }
                    return casino;
                }


            }
        );
    }
    
    public Cliente selectCliente(String rut) 
    {
        final Cliente cliente = new Cliente();
       // String quer = "SELECT * FROM EMPRESA WHERE RUT_EMPRESA='" + rut+"'";
        String quer="SELECT\n" +
"   COMUNA_NOMBRE,\n" +
"    REGION_NOMBRE,\n" +
"    RUT_EMPRESA,\n" +
"    NOMBRE_EMPRESA,\n" +
"    TELEFONO_EMPRESA,\n" +
"    CORREO_EMPRESA,\n" +
"    DIRECCION_EMPRESA\n" +
"FROM\n" +
"    region  inner join\n" +
"    provincia on REGION_ID = PROVINCIA_REGION_ID inner join\n" +
"    comuna on PROVINCIA_ID = COMUNA_PROVINCIA_ID inner join\n" +
"    EMPRESA on COMUNA_EMPRESA = COMUNA_ID"
                + " WHERE RUT_EMPRESA='" + rut+"'";
        return (Cliente) jdbcTemplate.query
        (
                quer, new ResultSetExtractor<Cliente>() 
            {
                public Cliente extractData(ResultSet rs) throws SQLException, DataAccessException 
                {
                    if (rs.next()) 
                    {
                        cliente.setNombre(rs.getString("Nombre_EMPRESA"));
                        cliente.setEmail(rs.getString("CORREO_EMPRESA"));
                        cliente.setTelefono(rs.getString("TELEFONO_EMPRESA"));
                        cliente.setComuna(rs.getString("COMUNA_NOMBRE"));
                        cliente.setDireccion(rs.getString("DIRECCION_EMPRESA"));
                        cliente.setRegion(rs.getString("REGION_NOMBRE"));
                        
                    }
                    return cliente;
                }


            }
        );
    }
}
