package controller;

import Modelos.ClienteValidate;
import Modelos.Comuna;
import Modelos.Conexion;
import Modelos.Provincia;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.jdbc.core.JdbcTemplate;
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

/**
 *
 * @author Sir Lekxas
 */
@Controller 
public class Administrador2Controller 
{
    private final ClienteValidate clienteValidate;

    private final JdbcTemplate jdbcTemplate;
    
    public Administrador2Controller (){
        this.clienteValidate = new ClienteValidate(); //Implementar Validator
        Conexion con = new Conexion();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
    }
    
    @RequestMapping(value = "AdministracionCliente/buscarProvincia.do", method = RequestMethod.GET)
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

    @RequestMapping(value = "AdministracionCliente/buscarComuna.do", method = RequestMethod.GET)
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

    
}