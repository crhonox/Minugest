
package Modelos;


public class EstadisticaSolicitud {
    private String fechaInicio;
    private String fechaFin;
    private String rutUser;

    public EstadisticaSolicitud(String fechaInicio, String fechaFin, String rutUser) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.rutUser=rutUser;
    }

    public EstadisticaSolicitud() {
    }

    public String getRutUser() {
        return rutUser;
    }

    public void setRutUser(String rutUser) {
        this.rutUser = rutUser;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    
    
}
