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

    public Receta() {}

    public Receta(String idReceta, String nombreReceta, String idCategoria, String descripcionReceta) {
        this.idReceta = idReceta;
        this.idCategoria = idCategoria;
        this.nombreReceta = nombreReceta;
        this.descripcionReceta = descripcionReceta;
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
    
    
    
    
}