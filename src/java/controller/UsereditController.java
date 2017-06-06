
package controller;

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
class UsereditController {
    private UsuarioValidate usuarioValidate;
    
    private JdbcTemplate jdbcTemplate;
    
    public UsereditController(){
        this.usuarioValidate=new UsuarioValidate(); //Implementar Validator
        Conexion con= new Conexion();
        this.jdbcTemplate=new JdbcTemplate(con.conectar());
    }
    
    @RequestMapping(value="Administracion/editarUsuario.htm",method=RequestMethod.GET) 
    public ModelAndView editarUsuario(HttpServletRequest request)
    {
        ModelAndView mav=new ModelAndView();
        String rutUser=request.getParameter("rutUser");
        Usuario datos=this.selectUsuario(rutUser);
        String sql="SELECT CODIGO_CASINO, NOMBRE_CASINO FROM Minugest.CASINO where RUT_EMPRESA = '"+datos.getRutEmpresa()+"'";
        List casinos= this.jdbcTemplate.queryForList(sql);
        List perfiles= this.jdbcTemplate.queryForList("SELECT * FROM Minugest.PERFIL");
        mav.addObject("casinos",casinos);
        mav.addObject("perfiles",perfiles);
        mav.setViewName("Administracion/editarUsuario");
        mav.addObject("usuario",new Usuario(datos.getRutEmpresa(),datos.getRut(),datos.getCod_casino(),datos.getCod_perfil(),datos.getNombre(),datos.getCorreo(),datos.getPass(),datos.getApellido()));
        return mav;
    }
    
    @RequestMapping(value="Administracion/editarUsuario.htm",method = RequestMethod.POST)
    public ModelAndView form(@ModelAttribute ("usuario") Usuario user,BindingResult result, SessionStatus status  )
    {
        this.usuarioValidate.validate(user, result);
        if(result.hasErrors())
        {
            //Volver al formulario por que los datos ingresados estan erroneos
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Administracion/editarUsuario");
         mav.addObject("usuario",new Usuario());
        return mav;
        }else{
            this.jdbcTemplate.update("UPDATE USUARIO SET CODIGO_CASINO=?, CODIGO_PERFIL=?, NOMBRE_USUARIO=?, CORREO_USUARIO=?, PASS_USUARIO=?, APELLIDO_USUARIO=? WHERE CODIGO_USUARIO=?"
                                    ,user.getCod_casino(),user.getCod_perfil(),user.getNombre(),user.getCorreo(),user.getPass(),user.getApellido(),user.getRut());
       
        return new ModelAndView("redirect:listaUsuario.htm?rut="+user.getRutEmpresa());
        }
        
    }
    
    public Usuario selectUsuario(String rut) 
    {
        final Usuario usuario = new Usuario();
       // String quer = "SELECT * FROM EMPRESA WHERE RUT_EMPRESA='" + rut+"'";
        String quer="SELECT RUT_EMPRESA,CODIGO_USUARIO, CASINO.CODIGO_CASINO, PERFIL.CODIGO_PERFIL, NOMBRE_USUARIO, APELLIDO_USUARIO, CORREO_USUARIO, PASS_USUARIO "
                + "from USUARIO inner join CASINO on USUARIO.CODIGO_CASINO = CASINO.CODIGO_CASINO inner join PERFIL on USUARIO.CODIGO_PERFIL = PERFIL.CODIGO_PERFIL"
                + " Where CODIGO_USUARIO='" + rut+"'";
        return (Usuario) jdbcTemplate.query
        (
                quer, new ResultSetExtractor<Usuario>() 
            {
                public Usuario extractData(ResultSet rs) throws SQLException, DataAccessException 
                {
                    if (rs.next()) 
                    {
                        usuario.setRutEmpresa(rs.getString("RUT_EMPRESA"));
                        usuario.setRut(rs.getString("CODIGO_USUARIO"));
                        usuario.setNombre(rs.getString("NOMBRE_USUARIO"));
                        usuario.setApellido(rs.getString("APELLIDO_USUARIO"));
                        usuario.setPass(rs.getString("PASS_USUARIO"));
                        usuario.setCorreo(rs.getString("CORREO_USUARIO"));
                        usuario.setCod_casino(rs.getString("CODIGO_CASINO"));
                        usuario.setCod_perfil(rs.getString("CODIGO_PERFIL"));
                        
                    }
                    return usuario;
                }


            }
        );
    
    }
}
