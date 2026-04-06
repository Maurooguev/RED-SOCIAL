package algorithms;

import model.*;

import java.util.*;

public class ConnectivityChecker {

    // Guarda los nodos bloqueados
    private static Set<Nodo> nodosBloqueados = new HashSet<>();

    // Verifica si el grafo es conexo
    public static boolean esConexo(Grafo grafo) {
        if (grafo.getNodos().isEmpty()) return true; // grafo vacío se considera conexo

        Set<Nodo> visitados = new HashSet<>();
        dfs(grafo.getNodos().get(0), visitados); 


        return visitados.size() == grafo.getNodos().size();
    }

    // DFS auxiliar
    private static void dfs(Nodo actual, Set<Nodo> visitados) {
        visitados.add(actual);
        // Recorremos todas las aristas adyacentes al nodo
        for (Arista a : actual.getAdyacentes()) {
            // Determinar el vecino: el nodo al otro extremo de la arista
            Nodo vecino = a.getDestino() == actual ? a.getOrigen() : a.getDestino();
            if (!visitados.contains(vecino)) {
                dfs(vecino, visitados); // DFS recursiva
            }
        }
    }

    // Bloquear conexión entre dos nodos
    public static void bloquearConexion(Grafo grafo, Nodo a, Nodo b) {
        grafo.getAristas().removeIf(arista ->
                (arista.getOrigen() == a && arista.getDestino() == b) ||
                (arista.getOrigen() == b && arista.getDestino() == a)
        );

        // Eliminamos de la lista de adyacentes de cada nodo
        a.getAdyacentes().removeIf(arista -> arista.getDestino() == b || arista.getOrigen() == b);
        b.getAdyacentes().removeIf(arista -> arista.getDestino() == a || arista.getOrigen() == a);

        // Guardar nodos bloqueados
        nodosBloqueados.add(a);
        nodosBloqueados.add(b);
    }

    // Obtener nodos bloqueados
    public static List<Nodo> getNodosBloqueados() {
        return new ArrayList<>(nodosBloqueados);
    }

    // Encontrar componentes conectados
    public static List<Set<Nodo>> obtenerComponentes(Grafo grafo) {
        Set<Nodo> visitados = new HashSet<>();
        List<Set<Nodo>> componentes = new ArrayList<>();

        for (Nodo nodo : grafo.getNodos()) {
            if (!visitados.contains(nodo)) {
                Set<Nodo> componente = new HashSet<>();
                dfs(nodo, componente); // DFS para encontrar todos los nodos conectados
                visitados.addAll(componente);
                componentes.add(componente);
            }
        }

        return componentes;
    }

    // Conectar componentes con el mínimo de nuevas aristas
    public static List<Arista> reconectarMinimamente(Grafo grafo) {
        List<Set<Nodo>> componentes = obtenerComponentes(grafo);
        List<Arista> nuevasConexiones = new ArrayList<>();

        while (componentes.size() > 1) {
            Set<Nodo> c1 = componentes.remove(0);
            Set<Nodo> c2 = componentes.remove(0);

            Nodo n1 = c1.iterator().next();
            Nodo n2 = c2.iterator().next();

            Arista nueva = new Arista(n1, n2, 1);
            nuevasConexiones.add(nueva);

            grafo.agregarArista(n1, n2, 1);

            c1.addAll(c2);
            componentes.add(c1);
        }

        return nuevasConexiones;
    }
}
