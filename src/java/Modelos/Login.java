package Modelos;
/**
 *
 * @author Sir Lekxas
 */
public class Login {
    private String Rut;
    private String Pass;
    
    
    public Login(){}
    
    public Login(String Rut, String Pass)
    {
        this.Rut = Rut;
        this.Pass = Pass;
    
    }
    
    public String getRut(){
        return Rut;
    }
    
    public void getRut(String Rut){
        this.Rut = Rut;
    }
    
    public String getPass(){
        return Pass;
    }
    
    public void getPass(String Pass){
        this.Pass = Pass;
    }
}
