

package controller;

import Modelos.Cliente;
import Modelos.ClienteValidate;
import Modelos.Conexion;
import Modelos.Usuario;
import Modelos.UsuarioValidate;
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

@Controller
public class UsuarioController {
    
    private UsuarioValidate usuarioValidate;
    private JdbcTemplate jdbcTemplate;
    
    public UsuarioController(){
        this.usuarioValidate=new UsuarioValidate(); //Implementar Validator
        Conexion con= new Conexion();
        this.jdbcTemplate=new JdbcTemplate(con.conectar());
    }
    
     @RequestMapping(value = "Administracion/infoCliente.htm") 
    public ModelAndView infoCliente(HttpServletRequest request)
    {
        ModelAndView mav=new ModelAndView();
        String rut=request.getParameter("rut");
        Cliente datos=this.selectCliente(rut);
        mav.setViewName("Administracion/infoCliente");
        mav.addObject("cliente",new Cliente(rut,datos.getNombre(),datos.getEmail(),datos.getTelefono(),datos.getComuna(),datos.getRegion(),datos.getDireccion()));
        return mav;
    }
       
    @RequestMapping(value = "Administracion/listaUsuario.htm")
    public ModelAndView listaUsuario(HttpServletRequest request)
    {
       ModelAndView mav= new ModelAndView();
       String rut=request.getParameter("rut");
       Cliente datos=this.selectCliente(rut);
        String sql ="SELECT RUT_EMPRESA,CODIGO_USUARIO, NOMBRE_CASINO, NOMBE_PERFIL, NOMBRE_USUARIO, APELLIDO_USUARIO, CORREO_USUARIO, PASS_USUARIO from USUARIO inner join CASINO on USUARIO.CODIGO_CASINO = CASINO.CODIGO_CASINO inner join PERFIL on USUARIO.CODIGO_PERFIL = PERFIL.CODIGO_PERFIL where RUT_EMPRESA='"+rut+"'";
        List datos2=this.jdbcTemplate.queryForList(sql);
        mav.addObject("datos",datos2);
        mav.setViewName("Administracion/listaUsuario");
         mav.addObject("cliente",new Cliente(rut,datos.getNombre(),datos.getEmail(),datos.getTelefono(),datos.getComuna(),datos.getRegion(),datos.getDireccion()));
        return mav;
    }
    
    @RequestMapping(value="Administracion/AñadirUsuario.htm",method = RequestMethod.GET)
    public ModelAndView añadirUsuario(HttpServletRequest request)
    {   
        ModelAndView mav = new ModelAndView();
        String rut=request.getParameter("rutEmpresa");
        mav.setViewName("Administracion/AñadirUsuario");
        String sql="SELECT CODIGO_CASINO, NOMBRE_CASINO FROM Minugest.CASINO where RUT_EMPRESA = '"+rut+"'";
        List casinos= this.jdbcTemplate.queryForList(sql);
        List perfiles= this.jdbcTemplate.queryForList("SELECT * FROM Minugest.PERFIL");
        mav.addObject("rutEmpresa",rut);
        mav.addObject("casinos",casinos);
        mav.addObject("perfiles",perfiles);
        mav.addObject("usuario",new Usuario());
        return mav;
    }
    
    @RequestMapping(value="Administracion/AñadirUsuario.htm",method = RequestMethod.POST)
    public ModelAndView form(@ModelAttribute ("usuario") Usuario user,BindingResult result, SessionStatus status  )
    {
        this.usuarioValidate.validate(user, result);
        if(result.hasErrors())
        {
            //Volver al formulario por que los datos ingresados estan erroneos
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Administracion/AñadirUsuario");
         mav.addObject("usuario",new Usuario());
        return mav;
        }else{
            this.jdbcTemplate.update("INSERT INTO USUARIO (CODIGO_USUARIO, CODIGO_CASINO, CODIGO_PERFIL, NOMBRE_USUARIO, CORREO_USUARIO, PASS_USUARIO, APELLIDO_USUARIO) VALUES (?,?,?,?,?,?,?)"
                                    ,user.getRut(),user.getCod_casino(),user.getCod_perfil(),user.getNombre(),user.getCorreo(),user.getPass(),user.getApellido());
       
        return new ModelAndView("redirect:listaUsuario.htm?rut="+user.getRutEmpresa());
        }
        
    }
    
   /* @RequestMapping(method=RequestMethod.GET) 
    public ModelAndView listaCasino(HttpServletRequest request)
    {
        ModelAndView mav=new ModelAndView();
        String rut=request.getParameter("rut");
        Cliente datos=this.selectCliente(rut);
        mav.setViewName("Administracion/infoCasino");
        mav.addObject("cliente",new Cliente(rut,datos.getNombre(),datos.getEmail(),datos.getTelefono(),datos.getComuna(),datos.getRegion(),datos.getDireccion()));
        return mav;
    }*/
   
    
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
