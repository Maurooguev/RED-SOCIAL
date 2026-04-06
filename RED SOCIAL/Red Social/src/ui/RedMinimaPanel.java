package ui;

import model.*;
import algorithms.KruskalMST;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RedMinimaPanel extends JPanel {

    public RedMinimaPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#f0f2f5")); // Fondo tipo Facebook

        // Título del panel
        JLabel title = new JLabel("🌐 Red Mínima de Amistades (Kruskal)");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(Color.decode("#1877f2"));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        add(title, BorderLayout.NORTH);

        // Panel de contenido
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(Color.decode("#f0f2f5"));

        // Crear usuarios y grafo de ejemplo
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

        // Calcular MST
        KruskalMST kruskal = new KruskalMST();
        List<Arista> mst = kruskal.construirMST(grafo);

        // Mostrar cada conexión como “tarjeta”
        for (Arista ar : mst) {
            JPanel card = new JPanel();
            card.setBackground(Color.white);
            card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
            card.setBorder(BorderFactory.createLineBorder(Color.decode("#dadde1"), 1));
            card.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

            JLabel icon = new JLabel("👫");
            icon.setFont(new Font("SansSerif", Font.PLAIN, 18));
            JLabel texto = new JLabel(ar.getOrigen().getNombre() + " - " + ar.getDestino().getNombre() + 
                                       " (Fuerza amistad: " + ar.getPeso() + ")");
            texto.setFont(new Font("SansSerif", Font.PLAIN, 16));

            card.add(icon);
            card.add(texto);
            content.add(card);
            content.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        JScrollPane scroll = new JScrollPane(content);
        scroll.setBorder(null);
        add(scroll, BorderLayout.CENTER);
    }
}
