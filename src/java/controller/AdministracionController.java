    
package controller;

import Modelos.Cliente;
import Modelos.ClienteValidate;
import Modelos.Conexion;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdministracionController {
    
    private ClienteValidate clienteValidate;
    
    private JdbcTemplate jdbcTemplate;
    
    public AdministracionController(){
        this.clienteValidate=new ClienteValidate(); //Implementar Validator
        Conexion con= new Conexion();
        this.jdbcTemplate=new JdbcTemplate(con.conectar());
    }
    
    @RequestMapping(value = "Administracion/cliente.htm")
    public ModelAndView cliente()
    {
        ModelAndView mav= new ModelAndView();
        String sql ="SELECT\n" +
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
"    EMPRESA on COMUNA_EMPRESA = COMUNA_ID";
        List datos=this.jdbcTemplate.queryForList(sql);
        mav.addObject("datos",datos);
        mav.addObject("cliente", new Cliente());
        mav.setViewName("Administracion/cliente");
        return mav;
    }
    
    @RequestMapping(value="Administracion/cliente.htm", method = RequestMethod.POST)
    public ModelAndView formBuscarAdm (@ModelAttribute("cliente") Cliente cli, BindingResult result, SessionStatus status)  
    {
        this.clienteValidate.validate(cli, result);
        if(result.hasErrors())
        {
        ModelAndView mav= new ModelAndView();
        String sql ="SELECT\n" +
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
                        "    EMPRESA on COMUNA_EMPRESA = COMUNA_ID";
            List datos=this.jdbcTemplate.queryForList(sql);
            mav.addObject("datos",datos);
            mav.addObject("cliente", new Cliente());
            mav.setViewName("Administracion/cliente");
            return mav;
        }
        else
        {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("Administracion/cliente");
            List cliente = this.jdbcTemplate.queryForList("SELECT * FROM EMPRESA where NOMBRE_EMPRESA regexp '"+cli.getNombre()+"'");
            mav.addObject("datos", cliente);
            mav.addObject("cliente",new Cliente());
            return mav;
        }
        
    }
           
    @RequestMapping(value = "receta.htm")
    public ModelAndView receta()
    {
        ModelAndView mav= new ModelAndView();
        mav.setViewName("Administracion/receta");
        return mav;
    }
    
    @RequestMapping(value = "ingrediente.htm")
    public ModelAndView ingrediente()
    {
        ModelAndView mav= new ModelAndView();
        mav.setViewName("Administracion/ingrediente");
        return mav;
    }
    
  
    
    
    @RequestMapping(value = "Administracion/AñadirCliente.htm",method = RequestMethod.GET)
    public ModelAndView añadirCliente()
    {   
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Administracion/AñadirCliente");
        List regiones= this.jdbcTemplate.queryForList("select REGION_ID, REGION_NOMBRE from region");
        List comunas= this.jdbcTemplate.queryForList("select COMUNA_ID, COMUNA_NOMBRE from comuna");
        mav.addObject("regiones",regiones);
        mav.addObject("comunas",comunas);
        mav.addObject("cliente",new Cliente());
        return mav;
    }
    
    @RequestMapping(value = "Administracion/AñadirCliente.htm",method = RequestMethod.POST)
    public ModelAndView form(@ModelAttribute ("cliente") Cliente cli,BindingResult result, SessionStatus status  )
    {
        this.clienteValidate.validate(cli, result);
        if(result.hasErrors())
        {
            //Volver al formulario por que los datos ingresados estan erroneos
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Administracion/AñadirCliente");
        mav.addObject("cliente",new Cliente());
        return mav;
        }else{
            this.jdbcTemplate.update("insert into EMPRESA (RUT_EMPRESA,NOMBRE_EMPRESA,TELEFONO_EMPRESA,CORREO_EMPRESA,Region_Empresa,Comuna_Empresa,Direccion_Empresa) values (?,?,?,?,?,?,?)"
                                    ,cli.getRut(),cli.getNombre(),cli.getTelefono(),cli.getEmail(),cli.getRegion(),cli.getComuna(),cli.getDireccion());
       
        return new ModelAndView("redirect:/cliente.htm");
        }
        
    }
    /*@RequestMapping(method=RequestMethod.GET) 
    public ModelAndView editarCliente(HttpServletRequest request)
    {
        ModelAndView mav=new ModelAndView();
        String rut=request.getParameter("rut");
        Cliente datos=this.selectCliente(rut);
        mav.setViewName("Administracion/editarCliente");
        mav.addObject("cliente",new Cliente(rut,datos.getNombre(),datos.getEmail(),datos.getTelefono(),datos.getComuna(),datos.getRegion(),datos.getDireccion()));
        return mav;
    }
    
    public Cliente selectCliente(String rut) 
    {
        final Cliente cliente = new Cliente();
        String quer = "SELECT * FROM EMPRESAS WHERE RUT_EMPRESA='" + rut+"'";
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
    }*/
    
}
