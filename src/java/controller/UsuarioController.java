

package controller;

import Modelos.Cliente;
import Modelos.ClienteValidate;
import Modelos.Conexion;
import Modelos.Usuario;
import Modelos.UsuarioCasinoValidate;
import Modelos.UsuarioValidate;
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
public class UsuarioController {
    
    private UsuarioValidate usuarioValidate;
    private UsuarioCasinoValidate usuarioCasinoValidate;
    private JdbcTemplate jdbcTemplate;
    
    public UsuarioController(){
        this.usuarioValidate=new UsuarioValidate(); //Implementar Validator
        Conexion con= new Conexion();
        this.jdbcTemplate=new JdbcTemplate(con.conectar());
    }
    
   /*  @RequestMapping(value = "Administracion/infoCliente.htm") 
    public ModelAndView infoCliente(HttpServletRequest request)
    {
        ModelAndView mav=new ModelAndView();
        String rut=request.getParameter("rut");
        Cliente datos=this.selectCliente(rut);
        mav.setViewName("Administracion/infoCliente");
        mav.addObject("cliente",new Cliente(rut,datos.getNombre(),datos.getNombreLargo(),datos.getEmail(),datos.getTelefono(),datos.getComuna(),datos.getRegion(),datos.getDireccion()));
        return mav;
    }*/
       
    @RequestMapping(value = "Administracion/listaUsuario.htm")
    public ModelAndView listaUsuario(HttpServletRequest request)
    {
       ModelAndView mav= new ModelAndView();
       String rut=request.getParameter("rut");
       Cliente datos=this.selectCliente(rut);
        String sql ="SELECT CODIGO_USUARIO,NOMBE_PERFIL, NOMBRE_USUARIO, APELLIDO_USUARIO, CORREO_USUARIO, PASS_USUARIO from USUARIO inner join PERFIL on USUARIO.CODIGO_PERFIL = PERFIL.CODIGO_PERFIL where RUT_EMPRESA_USUARIO='"+rut+"'";
        List datos2=this.jdbcTemplate.queryForList(sql);
        String NombreEmpresa=this.jdbcTemplate.queryForObject("SELECT NOMBRE_EMPRESA from EMPRESA where RUT_EMPRESA='"+rut+"'", String.class);
        mav.addObject("datos",datos2);
        mav.addObject("rutEmp",rut);
        mav.addObject("nomEmp",NombreEmpresa);
        mav.setViewName("Administracion/listaUsuario");
        mav.addObject("cliente",new Cliente(rut,datos.getNombre(),datos.getEmail(),datos.getTelefono(),datos.getComuna(),datos.getRegion(),datos.getDireccion()));
        return mav;
    }
    
    @RequestMapping(value = "Administracion/DetalleUsuarioCasino.htm")
    public ModelAndView listaUsuarioCasino(HttpServletRequest request)
    {
       ModelAndView mav= new ModelAndView();
       String rutUser=request.getParameter("rutUser");
        Usuario datos=this.selectUsuario(rutUser);
        List datos2=this.jdbcTemplate.queryForList("SELECT RUT_USUARIO,NOMBRE_CASINO,ID_CASINO from USUARIO_CASINO inner join CASINO on ID_CASINO=CODIGO_CASINO where RUT_USUARIO='"+rutUser+"'");
        String perfiles= this.jdbcTemplate.queryForObject("SELECT NOMBE_PERFIL FROM Minugest.PERFIL inner join USUARIO on USUARIO.CODIGO_PERFIL = PERFIL.CODIGO_PERFIL where CODIGO_USUARIO='"+rutUser+"'",String.class);
        mav.addObject("nombre",datos.getNombre());
        mav.addObject("apellido",datos.getApellido());
        mav.addObject("rut",datos.getRut());
        mav.addObject("perfil",perfiles);
        mav.addObject("correo",datos.getCorreo());
        mav.addObject("pass",datos.getPass());
        mav.addObject("rutEmp",datos.getRutEmpresa());
        mav.addObject("datos2",datos2);
        mav.setViewName("Administracion/DetalleUsuarioCasino");
        return mav;
    }
    
    @RequestMapping(value="Administracion/AñadirUsuario.htm",method = RequestMethod.GET)
    public ModelAndView añadirUsuario(HttpServletRequest request)
    {   
        ModelAndView mav = new ModelAndView();
        String rut=request.getParameter("rutEmpresa");
        mav.setViewName("Administracion/AñadirUsuario");
        List perfiles= this.jdbcTemplate.queryForList("SELECT * FROM Minugest.PERFIL");
        mav.addObject("rutEmpresa",rut);
        mav.addObject("perfiles",perfiles);
        mav.addObject("usuario",new Usuario());
        return mav;
    }
    
    @RequestMapping(value="Administracion/AñadirUsuario.htm",method = RequestMethod.POST)
    public ModelAndView form(@ModelAttribute ("usuario") Usuario user,BindingResult result, SessionStatus status  )
    {
        
        this.jdbcTemplate.update("INSERT INTO USUARIO (CODIGO_USUARIO, CODIGO_PERFIL, NOMBRE_USUARIO, CORREO_USUARIO, PASS_USUARIO, APELLIDO_USUARIO,RUT_EMPRESA_USUARIO) VALUES (?,?,?,?,?,?,?)"
                                    ,user.getRut(),user.getCod_perfil(),user.getNombre(),user.getCorreo(),user.getPass(),user.getApellido(),user.getRutEmpresa());
       
        return new ModelAndView("redirect:listaUsuario.htm?rut="+user.getRutEmpresa());
        
        
    }
    
    @RequestMapping(value="Administracion/DesasignarCasino.htm",method = RequestMethod.GET)
    public ModelAndView formdeleteCasino(@ModelAttribute ("usuario") Usuario user,BindingResult result, SessionStatus status , HttpServletRequest request )
    {
        String rutUser=request.getParameter("RUT");
        String COD=request.getParameter("COD");
        String Query = "DELETE FROM `Minugest`.`USUARIO_CASINO` WHERE `ID_CASINO`='"+COD+"' and`RUT_USUARIO`='"+rutUser+"';";
        this.jdbcTemplate.execute(Query);
        return new ModelAndView("redirect:DetalleUsuarioCasino.htm?rutUser="+rutUser+"");
    }
    
    @RequestMapping(value="Administracion/editarUsuario.htm",method=RequestMethod.GET) 
    public ModelAndView editarUsuario(HttpServletRequest request)
    {
        ModelAndView mav=new ModelAndView();
        String rutUser=request.getParameter("rutUser");
        Usuario datos=this.selectUsuario(rutUser);
        List perfiles= this.jdbcTemplate.queryForList("SELECT * FROM Minugest.PERFIL");
        mav.addObject("perfiles",perfiles);
        mav.setViewName("Administracion/editarUsuario");
        mav.addObject("usuario",new Usuario(datos.getRutEmpresa(),datos.getRut(),datos.getCod_perfil(),datos.getNombre(),datos.getCorreo(),datos.getPass(),datos.getApellido()));
        return mav;
    }
    
    @RequestMapping(value="Administracion/editarUsuario.htm",method = RequestMethod.POST)
    public ModelAndView formeditUsuario(@ModelAttribute ("usuario") Usuario user,BindingResult result, SessionStatus status  )
    {
        
        this.jdbcTemplate.update("UPDATE USUARIO SET CODIGO_PERFIL=?, NOMBRE_USUARIO=?, CORREO_USUARIO=?, PASS_USUARIO=?, APELLIDO_USUARIO=? WHERE CODIGO_USUARIO=?"
                                    ,user.getCod_perfil(),user.getNombre(),user.getCorreo(),user.getPass(),user.getApellido(),user.getRut());
       
        return new ModelAndView("redirect:listaUsuario.htm?rut="+user.getRutEmpresa());
        
        
    }
    
    @RequestMapping(value="Administracion/CasinoUsuario.htm",method=RequestMethod.GET) 
    public ModelAndView UsuarioCasino(HttpServletRequest request)
    {
        ModelAndView mav=new ModelAndView();
        String rutUser=request.getParameter("rutUser");
        Usuario datos=this.selectUsuario(rutUser);
        List casino = this.jdbcTemplate.queryForList("SELECT CODIGO_CASINO,NOMBRE_CASINO from CASINO where RUT_EMPRESA='"+datos.getRutEmpresa()+"'");
        mav.addObject("Casino",casino);
       
        mav.setViewName("Administracion/CasinoUsuario");
        mav.addObject("Usuario",new Usuario(rutUser,datos.getRutEmpresa()));
        return mav;
    }
    
     @RequestMapping(value="Administracion/CasinoUsuario.htm",method=RequestMethod.POST) 
    public ModelAndView UsuarioCasinoPost(@ModelAttribute("Usuario") Usuario usuario, BindingResult result, SessionStatus status,HttpServletRequest request)
    {
       
      
             List<String> lista = new ArrayList<>();
             lista =usuario.getCombobox();
             for (int i = 0; i < lista.size(); i++) {
            this.jdbcTemplate.update("insert into USUARIO_CASINO (RUT_USUARIO, "
                    + "ID_CASINO)"
                    + "values (?,?)" ,usuario.getRut() ,lista.get(i));
        }
             
              
            return new ModelAndView("redirect:DetalleUsuarioCasino.htm?rutUser="+usuario.getRut()+"");
        
 
    }
    
    public Usuario selectUsuario(String rut) 
    {
        final Usuario usuario = new Usuario();
       // String quer = "SELECT * FROM EMPRESA WHERE RUT_EMPRESA='" + rut+"'";
        String quer="SELECT RUT_EMPRESA_USUARIO,CODIGO_USUARIO, PERFIL.CODIGO_PERFIL, NOMBRE_USUARIO, APELLIDO_USUARIO, CORREO_USUARIO, PASS_USUARIO "
                + "from USUARIO  inner join PERFIL on USUARIO.CODIGO_PERFIL = PERFIL.CODIGO_PERFIL"
                + " Where CODIGO_USUARIO='" + rut+"'";
        return (Usuario) jdbcTemplate.query
        (
                quer, new ResultSetExtractor<Usuario>() 
            {
                public Usuario extractData(ResultSet rs) throws SQLException, DataAccessException 
                {
                    if (rs.next()) 
                    {
                        usuario.setRutEmpresa(rs.getString("RUT_EMPRESA_USUARIO"));
                        usuario.setRut(rs.getString("CODIGO_USUARIO"));
                        usuario.setNombre(rs.getString("NOMBRE_USUARIO"));
                        usuario.setApellido(rs.getString("APELLIDO_USUARIO"));
                        usuario.setPass(rs.getString("PASS_USUARIO"));
                        usuario.setCorreo(rs.getString("CORREO_USUARIO"));
                        
                        usuario.setCod_perfil(rs.getString("CODIGO_PERFIL"));
                        
                    }
                    return usuario;
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
"    DIRECCION_EMPRESA,RAZON_SOCIAL\n" +
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
                        cliente.setNombreLargo(rs.getString("RAZON_SOCIAL"));
                        
                    }
                    return cliente;
                }


            }
        );
    }
    
    
}
