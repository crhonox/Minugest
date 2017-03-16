package Modelos;
/**
 *
 * @author Sir Lekxas
 */
public class Login {
    private String Rut;
    private String Pass;
    private String perfil;
    
    
    public Login(){}
    
    public Login(String Rut, String Pass, String perfil)
    {
        this.Rut = Rut;
        this.Pass = Pass;
        this.perfil = perfil;
    
    }

    public void setRut(String Rut) {
        this.Rut = Rut;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }
    
    public String getRut(){
        return Rut;
    }
    
 
    
    public String getPass(){
        return Pass;
    }
    
    
    
  
}
