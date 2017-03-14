
package controller;

import Modelos.Cliente;
import Modelos.ClienteValidate;
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
@RequestMapping("editarCliente.htm")
public class ClienteController {
    private ClienteValidate clienteValidate;
    
    private JdbcTemplate jdbcTemplate;
    
    public ClienteController(){
        this.clienteValidate=new ClienteValidate(); //Implementar Validator
        Conexion con= new Conexion();
        this.jdbcTemplate=new JdbcTemplate(con.conectar());
    }
    
    @RequestMapping(method=RequestMethod.GET) 
    public ModelAndView editarCliente(HttpServletRequest request)
    {
        ModelAndView mav=new ModelAndView();
        String rut=request.getParameter("rut");
        Cliente datos=this.selectCliente(rut);
        mav.setViewName("Administracion/editarCliente");
        mav.addObject("cliente",new Cliente(rut,datos.getNombre(),datos.getEmail(),datos.getTelefono(),datos.getComuna(),datos.getRegion(),datos.getDireccion()));
        return mav;
    }
    
    @RequestMapping(method=RequestMethod.POST)
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
            mav.addObject("usuarios",new Cliente(rut,datos.getNombre(),datos.getEmail(),datos.getTelefono(),datos.getComuna(),datos.getRegion(),datos.getDireccion()));
            return mav;
        }else
        {
            String rut=request.getParameter("rut");
        this.jdbcTemplate.update(
                    "update Empresa "
                + "set Nombre_EMPRESA=?,"
                + "CORREO_EMPRESA=?,"
                + "TELEFONO_EMPRESA=? "
                + "COMUNA_EMPRESA=? "
                + "REGION_EMPRESA=? "
                + "DIRECCION_EMPRESA=? "
                + "where "
                + "rut=? ",
         cli.getNombre(),cli.getEmail(),cli.getTelefono(),cli.getComuna(),cli.getRegion(),cli.getDireccion(),rut);
         return new ModelAndView("redirect:/cliente.htm");
        }
       
    }
    
    public Cliente selectCliente(String rut) 
    {
        final Cliente cliente = new Cliente();
        String quer = "SELECT * FROM EMPRESA WHERE RUT_EMPRESA='" + rut+"'";
        return (Cliente) jdbcTemplate.query
        (
                quer, new ResultSetExtractor<Cliente>() 
            {
                public Cliente extractData(ResultSet rs) throws SQLException, DataAccessException {
                    if (rs.next()) {
                        cliente.setNombre(rs.getString("Nombre_EMPRESA"));
                        cliente.setEmail(rs.getString("CORREO_EMPRESA"));
                        cliente.setTelefono(rs.getString("TELEFONO_EMPRESA"));
                        cliente.setComuna(rs.getString("COMUNA_EMPRESA"));
                        cliente.setDireccion(rs.getString("DIRECCION_EMPRESA"));
                        cliente.setRegion(rs.getString("REGION_EMPRESA"));
                        
                    }
                    return cliente;
                }


            }
        );
    }
    
}
