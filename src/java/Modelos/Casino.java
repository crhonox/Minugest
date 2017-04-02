
package Modelos;


public class Casino {
    String RutEmpresa;
    String NombreCasino;

    public Casino() {
    }

    public Casino(String RutEmpresa, String NombreCasino) {
        this.RutEmpresa = RutEmpresa;
        this.NombreCasino = NombreCasino;
    }

    public String getRutEmpresa() {
        return RutEmpresa;
    }

    public void setRutEmpresa(String RutEmpresa) {
        this.RutEmpresa = RutEmpresa;
    }


    public String getNombreCasino() {
        return NombreCasino;
    }

    public void setNombreCasino(String NombreCasino) {
        this.NombreCasino = NombreCasino;
    }
    
    
    
}
