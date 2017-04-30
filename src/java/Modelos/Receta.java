package Modelos;

/**
 *
 * @author Bayron Cruz C
 */
public class Receta
{
 
    private String idReceta;
    private String idCategoria;
    private String nombreReceta;
    private String descripcionReceta;
    private String porcionReceta;

    public Receta() {}

    public Receta(String idReceta, String nombreReceta, String idCategoria, String descripcionReceta, String porcionReceta) {
        this.idReceta = idReceta;
        this.idCategoria = idCategoria;
        this.nombreReceta = nombreReceta;
        this.descripcionReceta = descripcionReceta;
        this.porcionReceta = porcionReceta;
    }

    public String getDescripcionReceta() {
        return descripcionReceta;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public String getIdReceta() {
        return idReceta;
    }

    public String getNombreReceta() {
        return nombreReceta;
    }

    public String getPorcionReceta() {
        return porcionReceta;
    }
    
    
    public void setDescripcionReceta(String descripcionReceta) {
        this.descripcionReceta = descripcionReceta;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public void setIdReceta(String idReceta) {
        this.idReceta = idReceta;
    }

    public void setNombreReceta(String nombreReceta) {
        this.nombreReceta = nombreReceta;
    }

    public void setPorcionReceta(String porcionReceta) {
        this.porcionReceta = porcionReceta;
    }
    
    
    
    
    
}