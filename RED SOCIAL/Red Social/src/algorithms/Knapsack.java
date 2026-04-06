package algorithms;

import model.Publicacion;
import java.util.*;

public class Knapsack {

    public static class Resultado {
        private int beneficioMaximo;
        private List<Publicacion> publicacionesSeleccionadas;

        public Resultado(int beneficioMaximo, List<Publicacion> publicacionesSeleccionadas) {
            this.beneficioMaximo = beneficioMaximo;
            this.publicacionesSeleccionadas = publicacionesSeleccionadas;
        }

        public int getBeneficioMaximo() {
            return beneficioMaximo;
        }

        public List<Publicacion> getPublicacionesSeleccionadas() {
            return publicacionesSeleccionadas;
        }
    }

    // Optimiza portada: publicaSubset = lista de publicaciones, espacioMax = límite total
    public static Resultado optimizarPortada(List<Publicacion> publicaSubset, int espacioMax) {
        int n = publicaSubset.size();
        int[][] dp = new int[n + 1][espacioMax + 1];

        int[] beneficios = new int[n];
        int[] tamaños = new int[n];

        // Asumimos tamaño = 1 por simplicidad; se puede cambiar si cada publicación tiene un "peso"
        for (int i = 0; i < n; i++) {
            beneficios[i] = publicaSubset.get(i).getLikes(); // beneficio = likes
            tamaños[i] = 1; // tamaño unitario
        }

        // Llenado de la tabla DP
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= espacioMax; w++) {
                if (tamaños[i - 1] <= w) {
                    dp[i][w] = Math.max(beneficios[i - 1] + dp[i - 1][w - tamaños[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Reconstrucción de las publicaciones seleccionadas
        List<Publicacion> seleccionadas = new ArrayList<>();
        int w = espacioMax;
        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                seleccionadas.add(publicaSubset.get(i - 1));
                w -= tamaños[i - 1];
            }
        }

        Collections.reverse(seleccionadas); // para mantener el orden original
        return new Resultado(dp[n][espacioMax], seleccionadas);
    }
}
