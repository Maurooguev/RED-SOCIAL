package algorithms;

import model.*;
import java.util.*;

public class DijkstraShortestPath {

    public Map<Nodo, Double> dijkstra(Grafo grafo, Nodo origen) {
        Map<Nodo, Double> distancias = new HashMap<>();
        PriorityQueue<Nodo> pq = new PriorityQueue<>(Comparator.comparingDouble(distancias::get));

        // Inicializamos distancias
        for (Nodo n : grafo.getNodos()) {
            distancias.put(n, Double.POSITIVE_INFINITY);
        }
        distancias.put(origen, 0.0);
        pq.add(origen);

        while (!pq.isEmpty()) {
            Nodo actual = pq.poll();

            for (Arista a : actual.getAdyacentes()) {
                Nodo vecino = (a.getOrigen() == actual) ? a.getDestino() : a.getOrigen();
                double nuevaDistancia = distancias.get(actual) + a.getPeso();

                if (nuevaDistancia < distancias.get(vecino)) {
                    distancias.put(vecino, nuevaDistancia);
                    pq.remove(vecino);
                    pq.add(vecino);
                }
            }
        }

        return distancias;
    }
}
