package Modelos;

import java.util.List;

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
    private Integer remove; // boolean flag
    private Minuta  minuta;
    private List<Ingrediente> ingredientes;
    private List<String>  combobox;
    private List<String> Cantidad;

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    
    public List<String> getCombobox() {
        return combobox;
    }

    public void setCombobox(List<String> combobox) {
        this.combobox = combobox;
    }

    public List<String> getCantidad() {
        return Cantidad;
    }

    public void setCantidad(List<String> Cantidad) {
        this.Cantidad = Cantidad;
    }
   
    
    public Receta() {}

    public Receta(String idReceta, String nombreReceta, String idCategoria, String descripcionReceta, String porcionReceta) {
        this.idReceta = idReceta;
        this.idCategoria = idCategoria;
        this.nombreReceta = nombreReceta;
        this.descripcionReceta = descripcionReceta;
        this.porcionReceta = porcionReceta;
    }

    public Minuta getMinuta() {
        return minuta;
    }

    public void setMinuta(Minuta minuta) {
        this.minuta = minuta;
    }

    public Integer getRemove() {
        return remove;
    }

    public void setRemove(Integer remove) {
        this.remove = remove;
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