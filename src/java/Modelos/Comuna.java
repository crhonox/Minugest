
package Modelos;


public class Comuna {
    private int ComunaId;
    private String ComunaName;

    public Comuna(int ComunaId, String ComunaName) {
        this.ComunaId = ComunaId;
        this.ComunaName = ComunaName;
    }

    public Comuna() {
    }

    public int getComunaId() {
        return ComunaId;
    }

    public void setComunaId(int ComunaId) {
        this.ComunaId = ComunaId;
    }

    public String getComunaName() {
        return ComunaName;
    }

    public void setComunaName(String ComunaName) {
        this.ComunaName = ComunaName;
    }
    
}
