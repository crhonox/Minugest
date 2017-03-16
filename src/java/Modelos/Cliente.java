
package Modelos;

public class Cliente {
    private String Nombre;
    private String Direccion;
    private String Region;
    private String Comuna;
    private String rut;
    private String email;
    private String Telefono;
    
    public Cliente(){}

    public Cliente(String rut,String Nombre,String email,String Telefono, String Region, String Comuna, String Direccion )
    {
        this.Nombre = Nombre;
        this.Direccion = Direccion;
        this.Region = Region;
        this.Comuna = Comuna;
        this.rut = rut;
        this.email = email;
        this.Telefono = Telefono;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String Region) {
        this.Region = Region;
    }

    public String getComuna() {
        return Comuna;
    }

    public void setComuna(String Comuna) {
        this.Comuna = Comuna;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }
    
    
    
}
