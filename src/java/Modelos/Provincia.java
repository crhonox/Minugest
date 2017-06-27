/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;


public class Provincia {
    private int ProvinciaId;
    private String ProvinciaName;

    public Provincia() {
    }

    public int getProvinciaId() {
        return ProvinciaId;
    }

    public void setProvinciaId(int ProvinciaId) {
        this.ProvinciaId = ProvinciaId;
    }

    public String getProvinciaName() {
        return ProvinciaName;
    }

    public void setProvinciaName(String ProvinciaName) {
        this.ProvinciaName = ProvinciaName;
    }

    public Provincia(int ProvinciaId, String ProvinciaName) {
        this.ProvinciaId = ProvinciaId;
        this.ProvinciaName = ProvinciaName;
    }
    
}
