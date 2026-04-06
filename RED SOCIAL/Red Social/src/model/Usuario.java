package model;

import java.util.*;

public class Usuario {
    private String nombre;
    private List<Publicacion> publicaciones;
    private List<Usuario> amigos;

    // Nodo asociado para el grafo
    private Nodo nodo;

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.publicaciones = new ArrayList<>();
        this.amigos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Usuario> getAmigos() {
        return amigos;
    }

    public void agregarAmigo(Usuario amigo) {
        if (!amigos.contains(amigo) && amigo != this) {
            amigos.add(amigo);
            amigo.getAmigos().add(this);
        }
    }

    public void agregarPublicacion(Publicacion p) {
        publicaciones.add(p);
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    // Métodos para nodo
    public void setNodo(Nodo nodo) {
        this.nodo = nodo;
    }

    public Nodo getNodo() {
        return nodo;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
