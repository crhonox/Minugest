
package Modelos;

import java.util.List;


public class Casino {
    String RutEmpresa;
    String CodigoCasino;
    String NombreCasino;
    String RegionCasino;
    String ProvinciaCasino;
    String ComunaCasino;
    String DireccionCasino;
 private List<String>  combobox;

    public List<String> getCombobox() {
        return combobox;
    }

    public void setCombobox(List<String> combobox) {
        this.combobox = combobox;
    }
 
    public Casino() {
    }
    
    /*public Casino(String NombreCasino, String CodigoCasino) {
        this.NombreCasino=NombreCasino;
        this.CodigoCasino=CodigoCasino;
    }*/

    public String getCodigoCasino() {
        return CodigoCasino;
    }

    public void setCodigoCasino(String CodigoCasino) {
        this.CodigoCasino = CodigoCasino;
    }

    public String getRegionCasino() {
        return RegionCasino;
    }

    public void setRegionCasino(String RegionCasino) {
        this.RegionCasino = RegionCasino;
    }

    public String getProvinciaCasino() {
        return ProvinciaCasino;
    }

    public void setProvinciaCasino(String ProvinciaCasino) {
        this.ProvinciaCasino = ProvinciaCasino;
    }

    public String getComunaCasino() {
        return ComunaCasino;
    }

    public void setComunaCasino(String ComunaCasino) {
        this.ComunaCasino = ComunaCasino;
    }

    public String getDireccionCasino() {
        return DireccionCasino;
    }

    public void setDireccionCasino(String DireccionCasino) {
        this.DireccionCasino = DireccionCasino;
    }

    public Casino(String RutEmpresa, String NombreCasino, String RegionCasino, String ProvinciaCasino, String ComunaCasino, String DireccionCasino) {
        this.RutEmpresa = RutEmpresa;
        this.NombreCasino = NombreCasino;
        this.RegionCasino = RegionCasino;
        this.ProvinciaCasino = ProvinciaCasino;
        this.ComunaCasino = ComunaCasino;
        this.DireccionCasino = DireccionCasino;
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
