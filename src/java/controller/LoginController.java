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
    public ModelAndView Login(HttpServletRequest request)
    {
        ModelAndView mav = new ModelAndView();
        String rut=request.getParameter("rut");
        mav.setViewName("Login/login");
        mav.addObject("login",new Login());
        return mav;
    
    }
   
     @RequestMapping(method = RequestMethod.POST)
     public ModelAndView form
        (
                @ModelAttribute ("login") Login log,
                BindingResult result,
                SessionStatus status, 
                HttpServletRequest request
        )
     {
         this.loginValidate.validate(log, result);
         if(result.hasErrors())
         {
            ModelAndView mav = new ModelAndView();
            String rut=request.getParameter("rut");
            String pass=request.getParameter("pass");
            mav.setViewName("Login/login");
            mav.addObject("login",new Login(rut, pass));
            return mav;
         }
         else
         {
          ModelAndView mav=new ModelAndView();
          String rut=request.getParameter("rut");
          String pass=request.getParameter("pass");
          String query = "SELECT CODIGO_PERFIL FROM USUARIO WHERE CODIGO_USUARIO = '"+rut+"' and PASS_USUARIO = '"+pass+"'";
          
          
          /*if (query == "1")
          {
             mav.setViewName("Administracion/Cliente");
             return mav;
          }
          else if (query == "2")
          {
              mav.setViewName("Administracion2/admHome");
              return mav;
          }
          else if (query == "3")
          { 
              mav.setViewName("Encargado/encarHome");
              return mav;
          }
          else if (query == "4")
          {
              mav.setViewName("SupervisorC/minutaDia");
              return mav;
          }
          */
         }
                    
         return new ModelAndView("redirect:/login.htm");
     
         }

}