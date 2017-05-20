
package controller;

import Modelos.Casino;
import Modelos.CasinoValidate;
import Modelos.Cliente;
import Modelos.Conexion;
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
public class CasinoController {
    
    private JdbcTemplate jdbcTemplate;
    private CasinoValidate casinoValidate;
    
    public CasinoController(){ 
        this.casinoValidate=new CasinoValidate();
        Conexion con= new Conexion();
        this.jdbcTemplate=new JdbcTemplate(con.conectar());
    }
    
    @RequestMapping(value = "listaCasino.htm")
    public ModelAndView listadoCasino(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        String rut= request.getParameter("rut");
        List datos2=this.jdbcTemplate.queryForList("SELECT CASINO.RUT_EMPRESA,CODIGO_CASINO, NOMBRE_CASINO,NOMBRE_EMPRESA FROM Minugest.CASINO inner join EMPRESA on CASINO.RUT_EMPRESA = EMPRESA.RUT_EMPRESA where CASINO.RUT_EMPRESA = '"+rut+"'");
        mav.addObject("datos",datos2);
        Cliente datos=this.selectCliente(rut);
        mav.addObject("cliente",new Cliente(rut,datos.getNombre(),datos.getEmail(),datos.getTelefono(),datos.getComuna(),datos.getRegion(),datos.getDireccion()));
        mav.setViewName("Administracion/listaCasino");
        return mav;
    }
    
    @RequestMapping(value = "AñadirCasino.htm",method = RequestMethod.GET)
    public ModelAndView añadirCasino(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        String rut = request.getParameter("rutemp");
        mav.setViewName("Administracion/AñadirCasino");
        mav.addObject("rutemp",rut);
        mav.addObject("casino", new Casino());  
        return mav;
    }
    
    @RequestMapping(value = "AñadirCasino.htm",method = RequestMethod.POST)
    public ModelAndView form(@ModelAttribute ("casino") Casino cas,BindingResult result, SessionStatus status  )
    {
        this.casinoValidate.validate(cas, result);
        if(result.hasErrors())
        {
            //Volver al formulario por que los datos ingresados estan erroneos
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Administracion/AñadirCasino");
         mav.addObject("casino",new Casino());
        return mav;
        }else{
            this.jdbcTemplate.update("INSERT INTO CASINO (RUT_EMPRESA,NOMBRE_CASINO) VALUES (?,?)"
                                      ,cas.getRutEmpresa(),cas.getNombreCasino());
       
        return new ModelAndView("redirect:/listaCasino.htm?rut="+cas.getRutEmpresa());
        }
        
    }
    
    public Cliente selectCliente(String rut) 
    {
        final Cliente cliente = new Cliente();
       // String quer = "SELECT * FROM EMPRESA WHERE RUT_EMPRESA='" + rut+"'";
        String quer="SELECT\n" +
"   COMUNA_NOMBRE,\n" +
"    REGION_NOMBRE,\n" +
"    RUT_EMPRESA,\n" +
"    NOMBRE_EMPRESA,\n" +
"    TELEFONO_EMPRESA,\n" +
"    CORREO_EMPRESA,\n" +
"    DIRECCION_EMPRESA\n" +
"FROM\n" +
"    region  inner join\n" +
"    provincia on REGION_ID = PROVINCIA_REGION_ID inner join\n" +
"    comuna on PROVINCIA_ID = COMUNA_PROVINCIA_ID inner join\n" +
"    EMPRESA on COMUNA_EMPRESA = COMUNA_ID"
                + " WHERE RUT_EMPRESA='" + rut+"'";
        return (Cliente) jdbcTemplate.query
        (
                quer, new ResultSetExtractor<Cliente>() 
            {
                public Cliente extractData(ResultSet rs) throws SQLException, DataAccessException 
                {
                    if (rs.next()) 
                    {
                        cliente.setNombre(rs.getString("Nombre_EMPRESA"));
                        cliente.setEmail(rs.getString("CORREO_EMPRESA"));
                        cliente.setTelefono(rs.getString("TELEFONO_EMPRESA"));
                        cliente.setComuna(rs.getString("COMUNA_NOMBRE"));
                        cliente.setDireccion(rs.getString("DIRECCION_EMPRESA"));
                        cliente.setRegion(rs.getString("REGION_NOMBRE"));
                        
                    }
                    return cliente;
                }


            }
        );
    }
}
