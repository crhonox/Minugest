package controller;


import Modelos.Minuta;
import Modelos.MinutaValidate;
import Modelos.Conexion;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
/**
 *
 * @author Sir Lekxas
 */
@Controller
public class SupervisorController {
    private MinutaValidate minutaValidate;
    
    private JdbcTemplate jdbcTemplate;
    
    public SupervisorController(){
        this.minutaValidate=new MinutaValidate(); //Implementar Validator
        Conexion con= new Conexion();
        this.jdbcTemplate=new JdbcTemplate(con.conectar());
    }
    
    
    @RequestMapping(value = "minutaDia.htm")
    public ModelAndView minutaDia()
    {
       
        long milis = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date resultdate = new Date(milis);
        
        
        ModelAndView mav= new ModelAndView();
        String sql ="SELECT\n" +
                "NOMBRE_MINUTA,\n" +
                "CODIGO_MINUTA,\n" +
                "CODIGO_CASINO,\n" +
                "CODIGO_USUARIO,\n" +
                "FECHA_MINUTA\n" +
                "FROM\n " +
                "MINUTA " +
                "WHERE FECHA_MINUTA ='"+sdf.format(resultdate)+"'";
        
        RowCountCallbackHandler count = new RowCountCallbackHandler();
        jdbcTemplate.query(sql,count);
        int rowCount = count.getRowCount();
        List datos=this.jdbcTemplate.queryForList(sql);
        if(rowCount == 0)
        {
            mav.addObject("fecha",sdf.format(resultdate));
            mav.setViewName("SupervisorC/minutaDiaError");
             return mav;
        }
        else
        {
        mav.addObject("datos",datos);
        mav.addObject("fecha",sdf.format(resultdate));
        mav.setViewName("SupervisorC/minutaDia");
        return mav;
        }
    }
    
    
    @RequestMapping(value = "consultaExito.htm")
    public ModelAndView consultaExito()
    {
        ModelAndView mav= new ModelAndView();
        mav.setViewName("SupervisorC/consultaExito");
        return mav;
    }
    
    @RequestMapping(value = "consultaError.htm")
    public ModelAndView consultaError()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("SupervisorC/consultaError");
        return mav;
    }
 
    @RequestMapping(value = "minutaDiaError.htm")
    public ModelAndView minutaDiaError()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("SupervisorC/minutaDiaError");
        return mav;
    }
    
    @RequestMapping(value = "SupervisorC/consultaMinuta.htm",method=RequestMethod.GET) 
    public ModelAndView consultarMinuta()
    {
        ModelAndView mav=new ModelAndView();
        mav.addObject("minuta", new Minuta());
        mav.setViewName("SupervisorC/consultaMinuta");
        return mav;
    }
    
    @RequestMapping(value = "SupervisorC/consultaMinuta.htm",method=RequestMethod.POST) 
    public ModelAndView conMinuta(@ModelAttribute ("minuta") Minuta minu)
    {
        String Fecha = minu.getFecha_Min();
        String sql = "select * from Minuta where FECHA_MINUTA = '"+Fecha+"'";
        ModelAndView mav=new ModelAndView();
        
        List datos= this.jdbcTemplate.queryForList(sql);
        mav.addObject("datos", datos);
        mav.setViewName("SupervisorC/consultaExito");
        return mav;
    }
    
}