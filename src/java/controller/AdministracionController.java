package controller;
 
import Modelos.Cliente;
import Modelos.Provincia;
import Modelos.ClienteValidate;
import Modelos.Comuna;
import Modelos.Conexion;
import Modelos.JSONValid;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.dao.DataAccessException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.stereotype.Controller;
 
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class AdministracionController {

    private final ClienteValidate clienteValidate;

    private final JdbcTemplate jdbcTemplate;

    public AdministracionController() {
        this.clienteValidate = new ClienteValidate(); //Implementar Validator
        Conexion con = new Conexion();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
    }

    @RequestMapping(value = "Administracion/cliente.htm", method = RequestMethod.GET)
    public ModelAndView cliente() {
        ModelAndView mav = new ModelAndView();
        String sql = "SELECT COMUNA_NOMBRE,REGION_NOMBRE,PROVINCIA_NOMBRE,RUT_EMPRESA,NOMBRE_EMPRESA,RAZON_SOCIAL,TELEFONO_EMPRESA,CORREO_EMPRESA,DIRECCION_EMPRESA FROM region inner join  provincia on REGION_ID = PROVINCIA_REGION_ID inner join comuna on PROVINCIA_ID = COMUNA_PROVINCIA_ID inner join EMPRESA on COMUNA_EMPRESA = COMUNA_ID order by NOMBRE_EMPRESA";
        List datos = this.jdbcTemplate.queryForList(sql);
        mav.addObject("datos", datos);
        mav.addObject("cliente", new Cliente());
        mav.setViewName("Administracion/cliente");
        return mav;
    }

    @RequestMapping(value = "Administracion/cliente.htm", method = RequestMethod.POST)
    public ModelAndView formBuscarAdm( @ModelAttribute("cliente") Cliente cli, BindingResult result, SessionStatus status) {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("Administracion/cliente");
        String sql = "SELECT COMUNA_NOMBRE,REGION_NOMBRE,RUT_EMPRESA,NOMBRE_EMPRESA,TELEFONO_EMPRESA,CORREO_EMPRESA,DIRECCION_EMPRESA FROM region inner join  provincia on REGION_ID = PROVINCIA_REGION_ID inner join comuna on PROVINCIA_ID = COMUNA_PROVINCIA_ID inner join EMPRESA on COMUNA_EMPRESA = COMUNA_ID where NOMBRE_EMPRESA regexp '" + cli.getNombre() + "'";
        List cliente = this.jdbcTemplate.queryForList(sql);
        mav.addObject("datos", cliente);
        String Nombre = cli.getNombre();
        mav.addObject("NomEmp", Nombre);
        mav.addObject("cliente", new Cliente());
        return mav;

    }
    
     @RequestMapping(value = "Administracion/infoCliente.htm") 
    public ModelAndView infoCliente(HttpServletRequest request)
    {
        ModelAndView mav=new ModelAndView();
        String rut=request.getParameter("rut");
        Cliente datos=this.selectCliente(rut);
        mav.setViewName("Administracion/infoCliente");
        mav.addObject("cliente",new Cliente(rut,datos.getNombre(),datos.getNombreLargo(),datos.getEmail(),datos.getTelefono(),datos.getComuna(),datos.getRegion(),datos.getProvincia(),datos.getDireccion()));
        return mav;
    }

    @RequestMapping(value = "receta.htm")
    public ModelAndView receta() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Administracion/receta");
        return mav;
    }

    @RequestMapping(value = "ingrediente.htm")
    public ModelAndView ingrediente() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Administracion/ingrediente");
        return mav;
    }

    @RequestMapping(value = "Administracion/AñadirCliente.htm", method = RequestMethod.GET)
    public ModelAndView añadirCliente() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Administracion/AñadirCliente");
        List regiones = this.jdbcTemplate.queryForList("select REGION_ID, REGION_NOMBRE from region");
        List comunas = this.jdbcTemplate.queryForList("select COMUNA_ID, COMUNA_NOMBRE from comuna");
        mav.addObject("regiones", regiones);
        mav.addObject("comunas", comunas);
        mav.addObject("cliente", new Cliente());
        return mav;
    }

    @RequestMapping(value = "Administracion/buscarProvincia.do", method = RequestMethod.GET)
    @ResponseBody
    public void buscarProvincia(@RequestParam(value = "regionID") String region, HttpServletRequest request,
            HttpServletResponse response) {
        String sql = "SELECT PROVINCIA_ID,PROVINCIA_NOMBRE FROM provincia where PROVINCIA_REGION_ID=" + region + " ";
        List<Provincia> provincias = jdbcTemplate.query(
                sql, new RowMapper<Provincia>() {
            @Override
            public Provincia mapRow(ResultSet rs, int rowNum) throws SQLException {
                Provincia c = new Provincia();
                c.setProvinciaId(Integer.parseInt(rs.getString("PROVINCIA_ID")));
                c.setProvinciaName(rs.getString("PROVINCIA_NOMBRE"));
                return c;
            }
        });

        //List<Provincia> provincia= this.jdbcTemplate.queryForObject("SELECT PROVINCIA_ID,PROVINCIA_NOMBRE FROM provincia where PROVINCIA_REGION_ID="+region+" ",Provincia);
        String json = null;
        json = new Gson().toJson(provincias);
        response.setContentType("Administracion/AñadirCliente");
        try {
            response.getWriter().write(json);
        } catch (IOException ex) {
            Logger.getLogger(AdministracionController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @RequestMapping(value = "Administracion/buscarComuna.do", method = RequestMethod.GET)
    public void buscarComuna(@RequestParam(value = "provinciaID") String provincia, HttpServletRequest request,
            HttpServletResponse response) {

        String sql = "select COMUNA_ID, COMUNA_NOMBRE from comuna where COMUNA_PROVINCIA_ID=" + provincia + " ";
        List<Comuna> comunas = jdbcTemplate.query(
                sql, new RowMapper<Comuna>() {
            @Override
            public Comuna mapRow(ResultSet rs, int rowNum) throws SQLException {
                Comuna c = new Comuna();
                c.setComunaId(Integer.parseInt(rs.getString("COMUNA_ID")));
                c.setComunaName(rs.getString("COMUNA_NOMBRE"));
                return c;
            }
        });

        String json = null;
        json = new Gson().toJson(comunas);
        response.setContentType("Administracion/AñadirCliente");
        try {
            response.getWriter().write(json);
        } catch (IOException ex) {
            Logger.getLogger(AdministracionController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @RequestMapping(value = "Administracion/AñadirCliente.htm", method = RequestMethod.POST)
    public ModelAndView form(@ModelAttribute("cliente") Cliente cli, BindingResult result, SessionStatus status) {
        
            this.jdbcTemplate.update("insert into EMPRESA (RUT_EMPRESA,NOMBRE_EMPRESA,RAZON_SOCIAL,TELEFONO_EMPRESA,CORREO_EMPRESA,Region_Empresa,ProvinciaEmpresa,Comuna_Empresa,Direccion_Empresa) values (?,?,?,?,?,?,?,?,?)",
                     cli.getRut(), cli.getNombre(), cli.getNombreLargo(), cli.getTelefono(), cli.getEmail(), cli.getRegion(), cli.getProvincia(), cli.getComuna(), cli.getDireccion());

            return new ModelAndView("redirect:cliente.htm");
        

    }
    
    @RequestMapping(value = "Administracion/ValidarCliente.do", method = RequestMethod.GET)
    @ResponseBody
    public void ValidarClienteRut(@RequestParam(value = "rut") String Ingrediente, HttpServletRequest request,
            HttpServletResponse response) {
        String Nombre= request.getParameter("rut");
        JSONValid valida= new JSONValid();
        int ingrediente = this.jdbcTemplate.queryForObject("SELECT COUNT(*) from EMPRESA where RUT_EMPRESA='"+Nombre+"'", Integer.class);
        Boolean valid = true;
        if (ingrediente != 0) {
            valid=false;
        }
        valida.setValid(valid);
        String json = null;
        json = new Gson().toJson(valida);
        response.setContentType("Administracion/AñadirCliente");
        try {
            response.getWriter().write(json);
        } catch (IOException ex) {
            Logger.getLogger(AdministracionController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
     public Cliente selectCliente(String rut) 
    {
        final Cliente cliente = new Cliente();
       // String quer = "SELECT * FROM EMPRESA WHERE RUT_EMPRESA='" + rut+"'";
        String quer="SELECT\n" +
"    COMUNA_NOMBRE,\n" +
"    REGION_NOMBRE,PROVINCIA_NOMBRE,\n" +
"    RUT_EMPRESA,\n" +
"    NOMBRE_EMPRESA,\n" +
"    TELEFONO_EMPRESA,\n" +
"    CORREO_EMPRESA,\n" +
"    DIRECCION_EMPRESA,RAZON_SOCIAL\n" +
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
                        cliente.setProvincia(rs.getString("PROVINCIA_NOMBRE"));
                        cliente.setNombreLargo(rs.getString("RAZON_SOCIAL"));
                        
                    }
                    return cliente;
                }
 
 
            }
        );
    }

}
 
