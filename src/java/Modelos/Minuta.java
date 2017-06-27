package Modelos;

import java.util.List;

public class Minuta {

    private String Nombre_Min;
    private String Codigo_Min;
    private String Codigo_Casi;
    private String Codigo_User;
    private String Fecha_Min;
    private List<String>  combobox;
    private List<String> Cantidad;
    private int idSolicitud;

    
    

public Minuta(){}

public Minuta(String Nombre_Min, String Codigo_Min, String Codigo_Casi, String Codigo_User, String Fecha_Min )
{
    this.Nombre_Min = Nombre_Min;
    this.Codigo_Min = Codigo_Min;
    this.Codigo_Casi = Codigo_Casi;
    this.Codigo_User = Codigo_User;
    this.Fecha_Min = Fecha_Min;
}

    public Minuta(String Nombre_Min, String Codigo_Min, String Codigo_Casi, String Codigo_User, String Fecha_Min, List<String> combobox, List<String> Cantidad, int idSolicitud) {
        this.Nombre_Min = Nombre_Min;
        this.Codigo_Min = Codigo_Min;
        this.Codigo_Casi = Codigo_Casi;
        this.Codigo_User = Codigo_User;
        this.Fecha_Min = Fecha_Min;
        this.combobox = combobox;
        this.Cantidad = Cantidad;
        this.idSolicitud = idSolicitud;
    }

  

public Minuta (String Nombre_Min, String Codigo_Min,String Codigo_Casi,String Fecha_Min)
{
    this.Nombre_Min = Nombre_Min;
    this.Codigo_Min = Codigo_Min;
    this.Codigo_Casi = Codigo_Casi;
    this.Fecha_Min = Fecha_Min;
}
public String getNombre_Min() {
        return Nombre_Min;
    }

    public void setNombre_Min(String Nombre_Min) {
        this.Nombre_Min = Nombre_Min;
    }

public String getCodigo_Min() {
        return Codigo_Min;
    }

    public void setCodigo_Min(String Codigo_Min) {
        this.Codigo_Min = Codigo_Min;
    }    
  
public String getCodigo_Casi() {
        return Codigo_Casi;
    }

    public void setCodigo_Casi(String Codigo_Casi) {
        this.Codigo_Casi = Codigo_Casi;
    } 
    
public String getCodigo_User() {
        return Codigo_User;
    }

    public void setCodigo_User(String Codigo_User) {
        this.Codigo_User = Codigo_User;
    }    
    
public String getFecha_Min() {
        return Fecha_Min;
    }

    public void setFecha_Min(String Fecha_Min) {
        this.Fecha_Min = Fecha_Min;
    } 

    public List<String> getCantidad() {
        return Cantidad;
    }

    public void setCantidad(List<String> Cantidad) {
        this.Cantidad = Cantidad;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

      public List<String> getCombobox() {
        return combobox;
    }

    public void setCombobox(List<String> combobox) {
        this.combobox = combobox;
    }

   
}
