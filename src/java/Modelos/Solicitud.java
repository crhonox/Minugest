/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author crhonox
 */
public class Solicitud {
    
    private String tiempo;
    private String asunto;
    private String contenido;
    private String destino;
    private String estado;
    private String codigo_usuario;
    private String fecha;
    private String tipo_solicitud;

    public Solicitud(String tiempo, String asunto, String contenido, String destino, String estado, String codigo_usuario) {
        this.tiempo = tiempo;
        this.asunto = asunto;
        this.contenido = contenido;
        this.destino = destino;
        this.estado = estado;
        this.codigo_usuario = codigo_usuario;
    }

    public Solicitud(String tiempo, String asunto, String contenido, String destino, String estado, String codigo_usuario, String fecha, String tipo_solicitud) {
        this.tiempo = tiempo;
        this.asunto = asunto;
        this.contenido = contenido;
        this.destino = destino;
        this.estado = estado;
        this.codigo_usuario = codigo_usuario;
        this.fecha = fecha;
        this.tipo_solicitud = tipo_solicitud;
    }

    
    public Solicitud() {
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo_solicitud() {
        return tipo_solicitud;
    }

    public void setTipo_solicitud(String tipo_solicitud) {
        this.tipo_solicitud = tipo_solicitud;
    }

    
    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigo_usuario() {
        return codigo_usuario;
    }

    public void setCodigo_usuario(String codigo_usuario) {
        this.codigo_usuario = codigo_usuario;
    }
    

}
