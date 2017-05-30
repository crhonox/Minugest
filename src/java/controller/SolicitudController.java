
package controller;

import Modelos.Conexion;
import Modelos.Solicitud;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SolicitudController {
    private JdbcTemplate jdbcTemplate;
    
    public SolicitudController() 
    {
        
        Conexion con= new Conexion();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
    }
    
    @RequestMapping(value = "Supervisor/Solicitudes.htm",method = RequestMethod.GET)
    public ModelAndView ListarSolicitudes()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("SupervisorC/Solicitudes");
        List solicitud = this.jdbcTemplate.queryForList("select * from SOLICITUD");
        
        mav.addObject("solicitud",solicitud);
        
        return mav;
    }
    
     @RequestMapping(value = "Supervisor/CrearSolicitud.htm",method = RequestMethod.GET)
    public ModelAndView CrearSolicitud()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("SupervisorC/CrearSolicitud");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        String user=userDetail.getUsername();
        String rut_emp= this.jdbcTemplate.queryForObject("SELECT RUT_EMPRESA from USUARIO inner join CASINO on USUARIO.CODIGO_CASINO = CASINO.CODIGO_CASINO where CODIGO_USUARIO='"+user+"'", String.class);
        List encargado = this.jdbcTemplate.queryForList("SELECT RUT_EMPRESA,CODIGO_USUARIO,Concat(NOMBRE_USUARIO,' ',APELLIDO_USUARIO) as Nombre from USUARIO inner join CASINO on USUARIO.CODIGO_CASINO = CASINO.CODIGO_CASINO where CODIGO_PERFIL='3' and RUT_EMPRESA='"+rut_emp+"'");
        mav.addObject("encargado",encargado);
        mav.addObject("Solicitud",new Solicitud());
        
        return mav;
    }
    
    
     @RequestMapping(value = "Supervisor/CrearSolicitud.htm",method = RequestMethod.POST)
    public ModelAndView CrearSolicitudPost()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("SupervisorC/CrearSolicitud");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        String user=userDetail.getUsername();
        String rut_emp= this.jdbcTemplate.queryForObject("SELECT RUT_EMPRESA from USUARIO inner join CASINO on USUARIO.CODIGO_CASINO = CASINO.CODIGO_CASINO where CODIGO_USUARIO='"+user+"'", String.class);
        List encargado = this.jdbcTemplate.queryForList("SELECT RUT_EMPRESA,CODIGO_USUARIO,Concat(NOMBRE_USUARIO,' ',APELLIDO_USUARIO) as Nombre from USUARIO inner join CASINO on USUARIO.CODIGO_CASINO = CASINO.CODIGO_CASINO where CODIGO_PERFIL='3' and RUT_EMPRESA='"+rut_emp+"'");
        mav.addObject("encargado",encargado);
        mav.addObject("Solicitud",new Solicitud());
        
        return mav;
    }
}
