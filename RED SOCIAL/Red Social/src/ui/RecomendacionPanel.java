package ui;

import model.*;
import algorithms.DijkstraShortestPath;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RecomendacionPanel extends JPanel {

    public RecomendacionPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#f0f2f5"));

        // Título
        JLabel title = new JLabel("🤝 Recomendación de Amigos");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(Color.decode("#1877f2"));
        title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);

        // Panel central
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(Color.decode("#f0f2f5"));

        // Crear usuarios
        Usuario u1 = new Usuario("Florencia");
        Usuario u2 = new Usuario("Mauro");
        Usuario u3 = new Usuario("Luz");
        Usuario u4 = new Usuario("Pedro");
        Usuario u5 = new Usuario("Ana");
        Usuario u6 = new Usuario("Sofia");
        Usuario u7 = new Usuario("Lucas");
        Usuario u8 = new Usuario("Valen");

        Nodo n1 = new Nodo(u1.getNombre());
        Nodo n2 = new Nodo(u2.getNombre());
        Nodo n3 = new Nodo(u3.getNombre());
        Nodo n4 = new Nodo(u4.getNombre());
        Nodo n5 = new Nodo(u5.getNombre());
        Nodo n6 = new Nodo(u6.getNombre());
        Nodo n7 = new Nodo(u7.getNombre());
        Nodo n8 = new Nodo(u8.getNombre());

        Grafo grafo = new Grafo();
        grafo.agregarNodo(n1); grafo.agregarNodo(n2); grafo.agregarNodo(n3); grafo.agregarNodo(n4);
        grafo.agregarNodo(n5); grafo.agregarNodo(n6); grafo.agregarNodo(n7); grafo.agregarNodo(n8);

        // Conexiones
        grafo.agregarArista(n1, n2, 2);
        grafo.agregarArista(n1, n3, 5);
        grafo.agregarArista(n2, n3, 1);
        grafo.agregarArista(n2, n4, 3);
        grafo.agregarArista(n3, n5, 2);
        grafo.agregarArista(n4, n5, 1);
        grafo.agregarArista(n4, n6, 4);
        grafo.agregarArista(n5, n7, 3);
        grafo.agregarArista(n6, n8, 2);
        grafo.agregarArista(n7, n8, 1);

        // Calcular distancias desde Florencia
        DijkstraShortestPath dijkstra = new DijkstraShortestPath();
        Map<Nodo, Double> distancias = dijkstra.dijkstra(grafo, n1);

        // Ordenar de menor a mayor
        List<Map.Entry<Nodo, Double>> ordenadas = distancias.entrySet()
                .stream()
                .filter(e -> e.getKey() != n1) // ignorar a sí misma
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());

        // Crear cards para cada recomendación
        for (Map.Entry<Nodo, Double> entry : ordenadas) {
            content.add(crearCardAmigo(entry.getKey(), entry.getValue()));
            content.add(Box.createRigidArea(new Dimension(0, 5))); // separación entre cards
        }

        JScrollPane scroll = new JScrollPane(content);
        scroll.setBorder(null);
        add(scroll, BorderLayout.CENTER);
    }

    private JPanel crearCardAmigo(Nodo nodo, double distancia) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#e0e0e0")),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        JLabel nombre = new JLabel(nodo.getNombre());
        nombre.setFont(new Font("SansSerif", Font.BOLD, 16));
        card.add(nombre, BorderLayout.CENTER);

        JLabel distanciaLabel = new JLabel("Distancia social: " + (int) distancia);
        distanciaLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        distanciaLabel.setForeground(Color.GRAY);
        card.add(distanciaLabel, BorderLayout.SOUTH);

        return card;
    }
}
