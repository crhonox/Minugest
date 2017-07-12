/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Modelos.Conexion;
import Modelos.EstadisticaSolicitud;
import Modelos.Solicitud;
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

/**
 *
 * @author crhonox
 */
@Controller
public class EstadisticasController {
     private JdbcTemplate jdbcTemplate;
    
    public EstadisticasController() 
    {
        Conexion con= new Conexion();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
    }
    
    @RequestMapping(value = "Supervisor/Estadisticas.htm",method = RequestMethod.GET)
    public ModelAndView EstadisticasSolicitud()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("SupervisorC/estadisticaSolicitud");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        String user=userDetail.getUsername();
        mav.addObject("rutUser",user);
        mav.addObject("EstadisticaSolicitud",new EstadisticaSolicitud());
        
        return mav;
    }
    
    @RequestMapping(value = "Supervisor/Estadisticas.htm",method = RequestMethod.POST)
    public ModelAndView EstadisticasSolicitudPost(@ModelAttribute("EstadisticaSolicitud") EstadisticaSolicitud esta, BindingResult result, SessionStatus status)
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("SupervisorC/resultadoSolicitud");
        int Bodas,Banquetes,Otras,Servicio;
        Bodas = this.jdbcTemplate.queryForObject("Select count(*) from SOLICITUD where Tipo_Solicitud = 1  AND TIEMPO BETWEEN '"+esta.getFechaInicio()+"' and '"+esta.getFechaFin()+"' and CODIGO_USUARIO='"+esta.getRutUser()+"' ",Integer.class);
        Banquetes = this.jdbcTemplate.queryForObject("Select count(*) from SOLICITUD where Tipo_Solicitud = 2 AND TIEMPO BETWEEN '"+esta.getFechaInicio()+"' and '"+esta.getFechaFin()+"' and CODIGO_USUARIO='"+esta.getRutUser()+"' ",Integer.class);
        Otras = this.jdbcTemplate.queryForObject("Select count(*) from SOLICITUD where Tipo_Solicitud = 3 AND TIEMPO BETWEEN '"+esta.getFechaInicio()+"' and '"+esta.getFechaFin()+"' and CODIGO_USUARIO='"+esta.getRutUser()+"'",Integer.class);
        Servicio = this.jdbcTemplate.queryForObject("Select count(*) from SOLICITUD where Tipo_Solicitud = 4 AND TIEMPO BETWEEN '"+esta.getFechaInicio()+"' and '"+esta.getFechaFin()+"' and CODIGO_USUARIO='"+esta.getRutUser()+"'",Integer.class);
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        String user=userDetail.getUsername();
        mav.addObject("fecha1",esta.getFechaInicio());
        mav.addObject("fecha2",esta.getFechaFin());
        mav.addObject("Bodas",Bodas);
        mav.addObject("Banquetes",Banquetes);
        mav.addObject("Otras",Otras);
        mav.addObject("Servicio",Servicio);
        mav.addObject("rutUser",user);
        mav.addObject("EstadisticaSolicitud",new EstadisticaSolicitud());
        
        return mav;
    }
    
    
    
    
    
}
