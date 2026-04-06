package model;

import java.util.*;

public class Grafo {
    private List<Nodo> nodos;
    private List<Arista> aristas;

    public Grafo() {
        this.nodos = new ArrayList<>();
        this.aristas = new ArrayList<>();
    }

    public void agregarNodo(Nodo n) {
        nodos.add(n);
    }

    public void agregarArista(Nodo origen, Nodo destino, double peso) {
        Arista a = new Arista(origen, destino, peso);
        aristas.add(a);
        origen.agregarAdyacente(a);
        destino.agregarAdyacente(a); // no dirigido
    }

    public void eliminarAristas(Nodo nodo) {
        aristas.removeIf(a -> a.getOrigen().equals(nodo) || a.getDestino().equals(nodo));
    }



    public List<Nodo> getNodos() {
        return nodos;
    }

    public List<Arista> getAristas() {
        return aristas;
    }
}
