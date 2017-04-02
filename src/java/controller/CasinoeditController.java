
package controller;

import Modelos.Casino;
import Modelos.CasinoValidate;
import Modelos.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class CasinoeditController {
    private CasinoValidate casinoValidate;
    private JdbcTemplate jdbcTemplate;
    
     public CasinoeditController(){
        this.casinoValidate=new CasinoValidate(); //Implementar Validator
        Conexion con= new Conexion();
        this.jdbcTemplate=new JdbcTemplate(con.conectar());
    }
    
    @RequestMapping("eliminarCasino.htm") 
    public ModelAndView eliminarCasino(HttpServletRequest request){
        
         String cod=request.getParameter("cod");
         Casino datos=this.selectCasino(cod);
         this.jdbcTemplate.update(
                    "DELETE FROM CASINO WHERE CODIGO_CASINO=?",
                    cod);
         return new ModelAndView("redirect:/listaCasino.htm?rut="+datos.getRutEmpresa());
    }
     
     @RequestMapping(method=RequestMethod.GET) 
    public ModelAndView editarCasino(HttpServletRequest request)
    {
        ModelAndView mav=new ModelAndView();
        String cod=request.getParameter("cod");
        Casino datos=this.selectCasino(cod);
        mav.setViewName("Administracion/editarCasino");
        mav.addObject("casino",new Casino(datos.getRutEmpresa(),datos.getNombreCasino()));
        return mav;
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView form
        (
                @ModelAttribute("casino") Casino cas,
                BindingResult result,
                SessionStatus status,
                HttpServletRequest request
        )
    {
        this.casinoValidate.validate(cas, result);
        if(result.hasErrors())
        {
            ModelAndView mav=new ModelAndView();
            String cod=request.getParameter("cod");
            Casino datos=this.selectCasino(cod);
            mav.setViewName("Administracion/editarCasino");
            mav.addObject("casino",new Casino(datos.getRutEmpresa(),datos.getNombreCasino()));
            return mav;
        }else
        {
            String cod=request.getParameter("cod");
            this.jdbcTemplate.update(
                    "UPDATE CASINO SET NOMBRE_CASINO=? WHERE CODIGO_CASINO=?",
         cas.getNombreCasino(),cod);
         return new ModelAndView("redirect:/listaCasino.htm?rut="+cas.getRutEmpresa());
        }
       
    }
    
    public Casino selectCasino(String cod) 
    {
        final Casino casino = new Casino();
       // String quer = "SELECT * FROM EMPRESA WHERE RUT_EMPRESA='" + rut+"'";
        String quer="SELECT RUT_EMPRESA,NOMBRE_CASINO FROM CASINO WHERE CODIGO_CASINO='" + cod+"'";
        return (Casino) jdbcTemplate.query
        (
                quer, new ResultSetExtractor<Casino>() 
            {
                public Casino extractData(ResultSet rs) throws SQLException, DataAccessException 
                {
                    if (rs.next()) 
                    {
                        casino.setNombreCasino(rs.getString("NOMBRE_CASINO"));
                        casino.setRutEmpresa(rs.getString("RUT_EMPRESA"));
                        
                        
                    }
                    return casino;
                }


            }
        );
    }
    
}
