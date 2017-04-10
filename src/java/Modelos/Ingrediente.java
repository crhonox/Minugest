package Modelos;

/**
 *
 * @author Bayron Cruz C
 */
public class Ingrediente 
{
    private String idIngrediente; 
    private String nombreIngrediente;
    private String unidadMedida;

    public Ingrediente() {}

    public Ingrediente(String idIngrediente, String nombreIngrediente, String unidadMedida) {
        this.idIngrediente = idIngrediente;
        this.nombreIngrediente = nombreIngrediente;
        this.unidadMedida = unidadMedida;
    }

    public String getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(String idIngrediente) {
        this.idIngrediente = idIngrediente;
    }
    
 

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
    
 
    
}
