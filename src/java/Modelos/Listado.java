
package Modelos;

import java.util.List;


public class Listado {
    String Nombre_Minuta;
    int Codigo_Minuta;
    List<Receta> recetas;

    public Listado(String Nombre_Minuta, int Codigo_Minuta, List<Receta> recetas) {
        this.Nombre_Minuta = Nombre_Minuta;
        this.Codigo_Minuta = Codigo_Minuta;
        this.recetas = recetas;
    }

    public Listado() {
    }

    public String getNombre_Minuta() {
        return Nombre_Minuta;
    }

    public void setNombre_Minuta(String Nombre_Minuta) {
        this.Nombre_Minuta = Nombre_Minuta;
    }

    public int getCodigo_Minuta() {
        return Codigo_Minuta;
    }

    public void setCodigo_Minuta(int Codigo_Minuta) {
        this.Codigo_Minuta = Codigo_Minuta;
    }

    public List<Receta> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<Receta> recetas) {
        this.recetas = recetas;
    }
    
     public void addRecetas(Receta newCourse)
    {
        this.recetas.add(newCourse);
    }
    
}
