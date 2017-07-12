/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.util.List;


public class PDF {
    private List<String>  ingrediente;
    private List<String>  cantidad;
    private List<String>  unidad;

    public PDF() {
    }

    public PDF(List<String> ingrediente, List<String> cantidad, List<String> unidad) {
        this.ingrediente = ingrediente;
        this.cantidad = cantidad;
        this.unidad = unidad;
    }

    public List<String> getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(List<String> ingrediente) {
        this.ingrediente = ingrediente;
    }

    public List<String> getCantidad() {
        return cantidad;
    }

    public void setCantidad(List<String> cantidad) {
        this.cantidad = cantidad;
    }

    public List<String> getUnidad() {
        return unidad;
    }

    public void setUnidad(List<String> unidad) {
        this.unidad = unidad;
    }
    
    
    
}
