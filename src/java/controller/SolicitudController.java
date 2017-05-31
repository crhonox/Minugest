
package controller;

import Modelos.Conexion;
import Modelos.Receta;
import Modelos.Solicitud;
import Modelos.SolicitudValidate;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SolicitudController {
    private SolicitudValidate solicitudValidate;
    private JdbcTemplate jdbcTemplate;
    
    public SolicitudController() 
    {
        this.solicitudValidate= new SolicitudValidate();
        Conexion con= new Conexion();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
    }
    
    @RequestMapping(value = "Supervisor/Solicitudes.htm",method = RequestMethod.GET)
    public ModelAndView ListarSolicitudes()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("SupervisorC/Solicitudes");
        List solicitud = this.jdbcTemplate.queryForList("select *,Concat(USUARIO.NOMBRE_USUARIO,' ',USUARIO.APELLIDO_USUARIO) as Nombre  from SOLICITUD inner join USUARIO on USUARIO.CODIGO_USUARIO=SOLICITUD.DESTINO");
        
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
        mav.addObject("usuario",user);
        mav.addObject("Solicitud",new Solicitud());
        
        return mav;
    }
    
    
     @RequestMapping(value = "Supervisor/CrearSolicitud.htm",method = RequestMethod.POST)
    public ModelAndView CrearSolicitudPost(@ModelAttribute("Solicitud") Solicitud sol, BindingResult result, SessionStatus status)
    {
        
        this.solicitudValidate.validate(sol, result);
        if(result.hasErrors())
        {
                ModelAndView mav = new ModelAndView();
        mav.setViewName("SupervisorC/CrearSolicitud");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        String user=userDetail.getUsername();
        String rut_emp= this.jdbcTemplate.queryForObject("SELECT RUT_EMPRESA from USUARIO inner join CASINO on USUARIO.CODIGO_CASINO = CASINO.CODIGO_CASINO where CODIGO_USUARIO='"+user+"'", String.class);
        List encargado = this.jdbcTemplate.queryForList("SELECT RUT_EMPRESA,CODIGO_USUARIO,Concat(NOMBRE_USUARIO,' ',APELLIDO_USUARIO) as Nombre from USUARIO inner join CASINO on USUARIO.CODIGO_CASINO = CASINO.CODIGO_CASINO where CODIGO_PERFIL='3' and RUT_EMPRESA='"+rut_emp+"'");
        mav.addObject("encargado",encargado);
        mav.addObject("usuario",user);
        mav.addObject("Solicitud",new Solicitud());
        
        return mav;
        }else{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        String user=userDetail.getUsername();
            long milis = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date resultdate = new Date(milis);
        String fecha = sdf.format(resultdate);
         this.jdbcTemplate.update("insert into SOLICITUD  (`TIEMPO`, `ASUNTO`, `CONTENIDO`, `DESTINO`, `CODIGO_USUARIO`) "
                    + "values (?,?,?,?,?)" , fecha, sol.getAsunto(), sol.getContenido(), sol.getDestino(),sol.getCodigo_usuario());
            return new ModelAndView("redirect:Solicitudes.htm");
        }
    }
}
