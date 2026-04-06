package algorithms;

import model.*;
import java.util.*;

public class KruskalMST {

    private Map<Nodo, Nodo> parent = new HashMap<>();

    private Nodo find(Nodo n) {
        if (parent.get(n) == n)
            return n;
        return find(parent.get(n));
    }

    private void union(Nodo a, Nodo b) {
        Nodo rootA = find(a);
        Nodo rootB = find(b);
        if (rootA != rootB) {
            parent.put(rootA, rootB);
        }
    }

    public List<Arista> construirMST(Grafo g) {
        List<Arista> resultado = new ArrayList<>();
        List<Arista> aristas = new ArrayList<>(g.getAristas());
        Collections.sort(aristas);

        for (Nodo n : g.getNodos()) {
            parent.put(n, n);
        }

        for (Arista a : aristas) {
            Nodo origen = a.getOrigen();
            Nodo destino = a.getDestino();

            if (find(origen) != find(destino)) {
                resultado.add(a);
                union(origen, destino);
            }
        }
        return resultado;
    }
}
