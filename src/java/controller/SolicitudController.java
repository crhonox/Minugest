
package controller;

import Modelos.Conexion;
import Modelos.Receta;
import Modelos.Solicitud;
import Modelos.SolicitudValidate;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        String user=userDetail.getUsername();
        List solicitud = this.jdbcTemplate.queryForList("select *,Tipo_Solicitud.NOMBRE_TIPO,Concat(USUARIO.NOMBRE_USUARIO,' ',USUARIO.APELLIDO_USUARIO) as Nombre  from SOLICITUD inner join USUARIO on USUARIO.CODIGO_USUARIO=SOLICITUD.DESTINO inner join Tipo_Solicitud on SOLICITUD.Tipo_Solicitud=Tipo_Solicitud.idTipo_Solicitud where SOLICITUD.CODIGO_USUARIO='"+user+"' order by TIEMPO");
        
        mav.addObject("solicitud",solicitud);
        
        return mav;
    }
    
    @RequestMapping(value = "Supervisor/DetalleSolicitud.htm",method = RequestMethod.GET)
    public ModelAndView DetalleSolicitud(HttpServletRequest request)
    {
        ModelAndView mav = new ModelAndView();
        String Codigo = request.getParameter("idSolicitud");
        mav.setViewName("SupervisorC/DetalleSolicitud");
       List solicitud = this.jdbcTemplate.queryForList("select *,Tipo_Solicitud.NOMBRE_TIPO,Concat(USUARIO.NOMBRE_USUARIO,' ',USUARIO.APELLIDO_USUARIO) as Nombre  from SOLICITUD inner join USUARIO on USUARIO.CODIGO_USUARIO=SOLICITUD.DESTINO inner join Tipo_Solicitud on SOLICITUD.Tipo_Solicitud=Tipo_Solicitud.idTipo_Solicitud where idSolicitud='"+Codigo+"'");
        List Minutas = this.jdbcTemplate.queryForList("SELECT MINUTA.*,CASINO.* FROM Minugest.MINUTA inner join CASINO on MINUTA.CODIGO_CASINO=CASINO.CODIGO_CASINO where CODIGO_SOLICITUD='"+Codigo+"'");
        mav.addObject("minutas",Minutas);
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
        String rut_emp= this.jdbcTemplate.queryForObject("SELECT RUT_EMPRESA_USUARIO from USUARIO where CODIGO_USUARIO='"+user+"'", String.class);
        List encargado = this.jdbcTemplate.queryForList("SELECT RUT_EMPRESA_USUARIO,CODIGO_USUARIO,Concat(NOMBRE_USUARIO,' ',APELLIDO_USUARIO) as Nombre from USUARIO where CODIGO_PERFIL='4' and RUT_EMPRESA_USUARIO='"+rut_emp+"'");
        List tipo = this.jdbcTemplate.queryForList("SELECT * FROM Tipo_Solicitud");
        mav.addObject("tipo",tipo);
        mav.addObject("encargado",encargado);
        mav.addObject("usuario",user);
        mav.addObject("Solicitud",new Solicitud());
        
        return mav;
    }
    
     @RequestMapping(value = "Supervisor/EliminarSolicitud.htm",method = RequestMethod.GET)
    public ModelAndView EliminarReceta(HttpServletRequest request){
        String Codigo = request.getParameter("idSolicitud");
        
        String Query = "DELETE FROM SOLICITUD WHERE idSolicitud='"+Codigo+"'";
        this.jdbcTemplate.execute(Query);
        return new ModelAndView("redirect:/Supervisor/Solicitudes.htm");
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
        String rut_emp= this.jdbcTemplate.queryForObject("SELECT RUT_EMPRESA_USUARIO from USUARIO where CODIGO_USUARIO='"+user+"'", String.class);
        List encargado = this.jdbcTemplate.queryForList("SELECT RUT_EMPRESA_USUARIO,CODIGO_USUARIO,Concat(NOMBRE_USUARIO,' ',APELLIDO_USUARIO) as Nombre from USUARIO where CODIGO_PERFIL='4' and RUT_EMPRESA_USUARIO='"+rut_emp+"'");
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
         this.jdbcTemplate.update("insert into SOLICITUD  (`TIEMPO`, `ASUNTO`, `CONTENIDO`, `DESTINO`, `CODIGO_USUARIO`,Tipo_Solicitud,Fecha_Solicitada) "
                    + "values (?,?,?,?,?,?,?)" , fecha, sol.getAsunto(), sol.getContenido(), sol.getDestino(),sol.getCodigo_usuario(),sol.getTipo_solicitud(),sol.getFecha());
            return new ModelAndView("redirect:Solicitudes.htm");
        }
    }
    
    @RequestMapping(value = "Supervisor/Aprobar.htm",method = RequestMethod.GET)
    public ModelAndView AprobarReceta(HttpServletRequest request){
        String Codigo = request.getParameter("idSolicitud");
        
        String Query = "UPDATE `Minugest`.`SOLICITUD` SET `ESTADO`='1' WHERE `idSOLICITUD`='"+Codigo+"';";
        this.jdbcTemplate.update(Query);
        return new ModelAndView("redirect:Solicitudes.htm");
    }
    
    /*------------------  Encargado  ----------------------------*/
    @RequestMapping(value = "Encargado/Solicitudes.htm",method = RequestMethod.GET)
    public ModelAndView ListarSolicitudesEncargado()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Encargado/Solicitudes");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        String rut_user=userDetail.getUsername();
        List solicitud = this.jdbcTemplate.queryForList("select *,Concat(USUARIO.NOMBRE_USUARIO,' ',USUARIO.APELLIDO_USUARIO) as Nombre  from SOLICITUD inner join USUARIO on USUARIO.CODIGO_USUARIO=SOLICITUD.DESTINO where SOLICITUD.DESTINO = '"+rut_user+"'");
        mav.addObject("solicitud",solicitud);
        
        return mav;
    }
    
    @RequestMapping(value = "Encargado/DetalleSolicitud.htm",method = RequestMethod.GET)
    public ModelAndView DetalleSolicitudEncargado(HttpServletRequest request)
    {
        ModelAndView mav = new ModelAndView();
        String Codigo = request.getParameter("idSolicitud");
        mav.setViewName("Encargado/DetalleSolicitud");
        List solicitud = this.jdbcTemplate.queryForList("select *,Concat(USUARIO.NOMBRE_USUARIO,' ',USUARIO.APELLIDO_USUARIO) as Nombre  from SOLICITUD inner join USUARIO on USUARIO.CODIGO_USUARIO=SOLICITUD.CODIGO_USUARIO where idSolicitud='"+Codigo+"'");
        List Minutas = this.jdbcTemplate.queryForList("SELECT MINUTA.*,CASINO.* FROM Minugest.MINUTA inner join CASINO on MINUTA.CODIGO_CASINO=CASINO.CODIGO_CASINO where CODIGO_SOLICITUD='"+Codigo+"'");
        mav.addObject("minutas",Minutas);
        mav.addObject("solicitud",solicitud);
        
        return mav;
    }
}
