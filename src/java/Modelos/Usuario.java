package Modelos;

/**
 *
 * @author Sir Lekxas
 */
public class Usuario {
    private String codigo;
    private String cod_casino;
    private String cod_perfil;
    private String nombre;
    private String correo;
    private String pass;
    
    public Usuario (){}
    
    public Usuario(String codigo, String codigo_casino, String codigo_perfil, String Nombre, String Correo, String Pass)
    {   
        this.codigo = codigo;
        this.cod_casino = codigo_casino;
        this.cod_perfil = codigo_perfil;
        this.nombre = Nombre;
        this.correo = Correo;
        this.pass = Pass;
    
    
    }
    
    public String getNombre()
    {
        return nombre;
    }
    
    public void setNombre(String Nombre)
    {
        this.nombre = Nombre;
    }
    
    public String getCodigo()
    {
        return codigo;
    }
    
    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }
    
    public String getCod_Casino()
    {
        return cod_casino;
    }
    
    public void setCod_Casino(String codigo_casino)
    {
        this.cod_casino = codigo_casino;
    }
    
    public String getCod_Perfil()
    {
        return cod_perfil;
    }
    
    public void setCod_Perfil(String codigo_perfil)
    {
        this.cod_perfil = codigo_perfil;
    }
    
    public String getCorreo()
    {
        return correo;
    }
    
    public void setCorreo(String Correo)
    {
        this.correo = Correo;
    }
    
    public String getPass()
    {
        return pass;
    }
    
    public void setPass(String Pass)
    {
        this.pass = Pass;
    }
}
