    package controller;

import Modelos.Conexion;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.stereotype.Controller;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.ModelAttribute;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.support.SessionStatus;
    import org.springframework.web.servlet.ModelAndView;

    @Controller
    public class homeController {
        private JdbcTemplate jdbcTemplate;
        
        public homeController(){
            Conexion con= new Conexion();
            this.jdbcTemplate=new JdbcTemplate(con.conectar());
        }
        
        @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
        public ModelAndView home() {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("home");
            return mav;
        }
        
        @RequestMapping(value = { "/welcome"}, method = RequestMethod.GET)
        public ModelAndView home2() {
            ModelAndView mav = new ModelAndView();
            /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            String user=userDetail.getUsername();
            int mensajes = this.jdbcTemplate.queryForObject("SELECT count(*) from SOLICITUD where visto = 0 and DESTINO ='"+user+"' ", Integer.class);
            mav.addObject("mensaje",mensajes);*/
            mav.setViewName("home_1");
            return mav;
        }

        @RequestMapping(value = "compañia.htm")
        public ModelAndView compañia() {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("Login/compañia");
            return mav;
        }

        @RequestMapping(value = "socios.htm")
        public ModelAndView socios() {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("Login/socios");
            return mav;
        }

        @RequestMapping(value = "soluciones.htm")
        public ModelAndView soluciones() {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("Login/soluciones");
            return mav;
        }

    }
