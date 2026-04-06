package model;

import java.time.LocalDateTime;

public class Publicacion {
    private String contenido;
    private int likes;
    private LocalDateTime fecha;

    public Publicacion(String contenido, int likes, LocalDateTime fecha) {
        this.contenido = contenido;
        this.likes = likes;
        this.fecha = fecha;
    }

    public String getContenido() {
        return contenido;
    }

    public int getLikes() {
        return likes;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public double getRelevancia() {
        // Ejemplo simple: likes * 0.7 + antigüedad inversa * 0.3
        long horas = java.time.Duration.between(fecha, LocalDateTime.now()).toHours();
        double antiguedad = 1.0 / (1 + horas);
        return likes * 0.7 + antiguedad * 0.3;
    }

    @Override
    public String toString() {
        return contenido + " (" + likes + " likes, " + fecha + ")";
    }
}
