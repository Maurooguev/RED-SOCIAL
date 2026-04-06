package algorithms;

import model.Publicacion;
import java.util.*;

public class Sorter {

    // Ordena publicaciones por relevancia o por fecha
    public static void mergeSort(List<Publicacion> lista, Comparator<Publicacion> comparador) {
        if (lista.size() > 1) {
            int mid = lista.size() / 2;
            List<Publicacion> izquierda = new ArrayList<>(lista.subList(0, mid));
            List<Publicacion> derecha = new ArrayList<>(lista.subList(mid, lista.size()));

            mergeSort(izquierda, comparador);
            mergeSort(derecha, comparador);

            merge(lista, izquierda, derecha, comparador);
        }
    }

    private static void merge(List<Publicacion> lista, List<Publicacion> izq, List<Publicacion> der, Comparator<Publicacion> comp) {
        int i = 0, j = 0, k = 0;

        while (i < izq.size() && j < der.size()) {
            if (comp.compare(izq.get(i), der.get(j)) <= 0) {
                lista.set(k++, izq.get(i++));
            } else {
                lista.set(k++, der.get(j++));
            }
        }

        while (i < izq.size()) lista.set(k++, izq.get(i++));
        while (j < der.size()) lista.set(k++, der.get(j++));
    }
}
