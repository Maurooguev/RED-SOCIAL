package model;

import java.util.*;

public class Nodo {
    private String nombre;
    private List<Arista> adyacentes;

    public Nodo(String nombre) {
        this.nombre = nombre;
        this.adyacentes = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Arista> getAdyacentes() {
        return adyacentes;
    }

    public void agregarAdyacente(Arista a) {
        adyacentes.add(a);
    }

    @Override
    public String toString() {
        return nombre;
    }
}
