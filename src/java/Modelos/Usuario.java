package Modelos;

/**
 *
 * @author Sir Lekxas
 */
public class Usuario {
    private String RutEmpresa;
    private String rut;
    private String cod_casino;
    private String cod_perfil;
    private String nombre;
    private String correo;
    private String pass;
    private String apellido;
    
    
    public Usuario (){}
    
    public Usuario(String RutEmpresa,String rut, String codigo_casino, String codigo_perfil, String Nombre, String Correo, String Pass, String Apellido)
    {  
        this.RutEmpresa= RutEmpresa;
        this.rut = rut;
        this.cod_casino = codigo_casino;
        this.cod_perfil = codigo_perfil;
        this.nombre = Nombre;
        this.correo = Correo;
        this.pass = Pass;
        this.apellido= Apellido;
        
    
    }

    public String getRutEmpresa() {
        return RutEmpresa;
    }

    public void setRutEmpresa(String RutEmpresa) {
        this.RutEmpresa = RutEmpresa;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getCod_casino() {
        return cod_casino;
    }

    public void setCod_casino(String cod_casino) {
        this.cod_casino = cod_casino;
    }

    public String getCod_perfil() {
        return cod_perfil;
    }

    public void setCod_perfil(String cod_perfil) {
        this.cod_perfil = cod_perfil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    
}
