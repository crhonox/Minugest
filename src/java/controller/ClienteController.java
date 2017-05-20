
package controller;

import Modelos.Cliente;
import Modelos.ClienteValidate;
import Modelos.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
public class ClienteController {
    private ClienteValidate clienteValidate;
    
    private JdbcTemplate jdbcTemplate;
    
    public ClienteController(){
        this.clienteValidate=new ClienteValidate(); //Implementar Validator
        Conexion con= new Conexion();
        this.jdbcTemplate=new JdbcTemplate(con.conectar());
    }
    
    @RequestMapping(value="editarCliente.htm",method=RequestMethod.GET) 
    public ModelAndView editarCliente(HttpServletRequest request)
    {
        ModelAndView mav=new ModelAndView();
        String rut=request.getParameter("rut");
        Cliente datos=this.selectCliente(rut);
        List regiones= this.jdbcTemplate.queryForList("select REGION_ID, REGION_NOMBRE from region");
        List comunas= this.jdbcTemplate.queryForList("select COMUNA_ID, COMUNA_NOMBRE from comuna");
        List clicom= this.jdbcTemplate.queryForList("SELECT COMUNA_NOMBRE,REGION_NOMBRE,COMUNA_ID,REGION_ID FROM region  inner join provincia on REGION_ID = PROVINCIA_REGION_ID inner join comuna on PROVINCIA_ID = COMUNA_PROVINCIA_ID inner join EMPRESA on COMUNA_EMPRESA = COMUNA_ID WHERE RUT_EMPRESA='"+rut+"'");
        mav.addObject("regiones",regiones);
        mav.addObject("comunas",comunas);
        mav.addObject("clicoms",clicom);
        mav.setViewName("Administracion/editarCliente");
        mav.addObject("cliente",new Cliente(rut,datos.getNombre(),datos.getEmail(),datos.getTelefono(),datos.getComuna(),datos.getRegion(),datos.getDireccion()));
        return mav;
    }
    
    @RequestMapping(value="editarCliente.htm",method=RequestMethod.POST)
    public ModelAndView form
        (
                @ModelAttribute("cliente") Cliente cli,
                BindingResult result,
                SessionStatus status,
                HttpServletRequest request
        )
    {
        this.clienteValidate.validate(cli, result);
        if(result.hasErrors())
        {
            ModelAndView mav=new ModelAndView();
            String rut=request.getParameter("rut");
            Cliente datos=this.selectCliente(rut);
            mav.setViewName("Administracion/editarCliente");
            mav.addObject("cliente",new Cliente(rut,datos.getNombre(),datos.getEmail(),datos.getTelefono(),datos.getComuna(),datos.getRegion(),datos.getDireccion()));
            return mav;
        }else
        {
            String rut=request.getParameter("rut");
            this.jdbcTemplate.update(
                    "update EMPRESA set NOMBRE_EMPRESA=?,CORREO_EMPRESA=?,TELEFONO_EMPRESA=?,COMUNA_EMPRESA=? ,REGION_EMPRESA=? ,DIRECCION_EMPRESA=? where RUT_EMPRESA=? ",
         cli.getNombre(),cli.getEmail(),cli.getTelefono(),cli.getComuna(),cli.getRegion(),cli.getDireccion(),rut);
         return new ModelAndView("redirect:/cliente.htm");
        }
       
    }
    
    public Cliente selectCliente(String rut) 
    {
        final Cliente cliente = new Cliente();
       // String quer = "SELECT * FROM EMPRESA WHERE RUT_EMPRESA='" + rut+"'";
        String quer="SELECT\n" +
"   COMUNA_NOMBRE,COMUNA_ID,\n" +
"    REGION_NOMBRE,REGION_ID,\n" +
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
                        cliente.setComuna(rs.getString("COMUNA_ID"));
                        cliente.setDireccion(rs.getString("DIRECCION_EMPRESA"));
                        cliente.setRegion(rs.getString("REGION_ID"));
                        
                    }
                    return cliente;
                }


            }
        );
    }
    

    
}
