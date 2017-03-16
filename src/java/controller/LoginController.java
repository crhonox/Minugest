package controller;

import Modelos.Login;
import Modelos.LoginValidate;
import Modelos.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;/**
 *
 * @author Sir Lekxas
 */

@Controller
@RequestMapping("login.htm")
public class LoginController{

private LoginValidate loginValidate;

private JdbcTemplate jdbcTemplate;

public LoginController(){

    this.loginValidate=new LoginValidate();
    Conexion con = new Conexion();
    this.jdbcTemplate=new JdbcTemplate(con.conectar());
}
     
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView Login()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Login/login");
        mav.addObject("login",new Login());
        return mav;
    
    }
   
     @RequestMapping(method = RequestMethod.POST)
     public ModelAndView form
        (
                @ModelAttribute ("login") Login log,
                BindingResult result,
                SessionStatus status
               
        )
     {
         this.loginValidate.validate(log, result);
         Login datos = this.loginSelect(log.getRut(),log.getPass());
         if(result.hasErrors())
         {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Login/login");
        mav.addObject("login",new Login());
        
        return mav;
         }
         else
         {
          ModelAndView mav=new ModelAndView();
          if (log.getRut().equals(datos.getRut()) && log.getPass().equals(datos.getPass()))
          {
              int cod = Integer.parseInt(datos.getPerfil());
              if(cod == 1)
              {
              return new ModelAndView("redirect:/cliente.htm");
              }
              else if(cod == 2)
              {
              return new ModelAndView("redirect:/admHome.htm");
              }
              else if(cod == 3)
              {
              return new ModelAndView("redirect:/minutaDia.htm");
              }
              else if(cod == 4)
              {
              return new ModelAndView("redirect:/encarHome.htm");
              }
              return new ModelAndView("redirect:/home.htm");
          }else
          {
              
          }
     /*     String query = "SELECT CODIGO_PERFIL FROM USUARIO WHERE CODIGO_USUARIO = '"+log.getRut()+"' and PASS_USUARIO = '"+log.getPass()+"'";
          return (Login) jdbcTemplate.query
        (
                query, new ResultSetExtractor<Login>() 
            {
                public Login extractData(ResultSet rs) throws SQLException, DataAccessException {
                    if (rs.next()) {
                        int cod = Integer.parseInt(rs.getString("CODIGO_PERFIL"));
                    }
                    
                    if(){
                        
                    }
                    return Login;
                }


            }
        );*/
          
         }
                    
         return new ModelAndView("redirect:/login.htm");
     
         }
        public Login loginSelect(String rut,String pass)
        {
        final Login login = new Login();
          String query2 = "SELECT CODIGO_USUARIO,PASS_USUARIO,CODIGO_PERFIL FROM USUARIO WHERE CODIGO_USUARIO = '"+rut+"' and PASS_USUARIO = '"+pass+"'";
          return (Login) jdbcTemplate.query( query2, new ResultSetExtractor<Login>() 
            {
                public Login extractData(ResultSet rs) throws SQLException, DataAccessException {
                    if (rs.next()) {
                        login.setRut(rs.getString("CODIGO_USUARIO"));
                        login.setPass(rs.getString("PASS_USUARIO"));
                        login.setPerfil(rs.getString("CODIGO_PERFIL"));
                    }
                    return login;
                }


            }
        );
        
        }
}