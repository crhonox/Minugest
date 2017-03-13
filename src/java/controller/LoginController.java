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
     public ModelAndView form(@ModelAttribute ("login") Login log,BindingResult result, SessionStatus status  )
     {
         this.loginValidate.validate(log, result);
         if(result.hasErrors())
         {
         ModelAndView mav = new ModelAndView();
        mav.setViewName("Login/login");
        mav.addObject("login",new Login());
        return mav;
         }
         else
         {
          
         
         
         }
    
         return new ModelAndView("redirect:/login.htm");
     
     
     
     
     }

}