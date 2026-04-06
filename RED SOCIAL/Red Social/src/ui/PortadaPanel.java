package ui;

import algorithms.Knapsack;
import model.Publicacion;
import model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PortadaPanel extends JPanel {

    public PortadaPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#f0f2f5"));

        // Header del feed
        JLabel title = new JLabel("📰 Publicaciones Optimas");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(Color.decode("#1877f2"));
        title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        add(title, BorderLayout.NORTH);

        // Panel central para publicaciones
        JPanel feedPanel = new JPanel();
        feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.Y_AXIS));
        feedPanel.setBackground(Color.decode("#f0f2f5"));

        // Crear usuarios y publicaciones (misma info que MuroPanel)
        Usuario florencia = new Usuario("Florencia");
        Usuario mauri = new Usuario("Mauro");
        Usuario luz = new Usuario("Luz");
        Usuario pedro = new Usuario("Pedro");
        Usuario ana = new Usuario("Ana");

        florencia.agregarPublicacion(new Publicacion("Estudiando para el parcial", 15, LocalDateTime.now().minusHours(2)));
        florencia.agregarPublicacion(new Publicacion("Nuevo proyecto en Java", 40, LocalDateTime.now().minusHours(5)));
        florencia.agregarPublicacion(new Publicacion("Tomando un café y escuchando música", 22, LocalDateTime.now().minusHours(1)));

        mauri.agregarPublicacion(new Publicacion("Café con amigos", 10, LocalDateTime.now().minusHours(1)));
        mauri.agregarPublicacion(new Publicacion("Terminando la tarea de algoritmos", 35, LocalDateTime.now().minusHours(4)));
        mauri.agregarPublicacion(new Publicacion("Aprendiendo sobre redes neuronales", 28, LocalDateTime.now().minusHours(3)));

        luz.agregarPublicacion(new Publicacion("Trabajo en equipo", 25, LocalDateTime.now().minusHours(3)));
        luz.agregarPublicacion(new Publicacion("Leyendo un libro de ciencia ficción", 18, LocalDateTime.now().minusHours(6)));
        luz.agregarPublicacion(new Publicacion("Practicar guitarra por la tarde", 12, LocalDateTime.now().minusHours(2)));

        ana.agregarPublicacion(new Publicacion("Clase de matemáticas", 5, LocalDateTime.now().minusHours(4)));
        ana.agregarPublicacion(new Publicacion("Salí a correr al parque", 12, LocalDateTime.now().minusHours(2)));
        ana.agregarPublicacion(new Publicacion("Cocinando una receta nueva", 20, LocalDateTime.now().minusHours(1)));

        pedro.agregarPublicacion(new Publicacion("Deportes en la universidad", 30, LocalDateTime.now().minusHours(6)));
        pedro.agregarPublicacion(new Publicacion("Comprando ingredientes para cocinar", 8, LocalDateTime.now().minusHours(5)));
        pedro.agregarPublicacion(new Publicacion("Preparando presentación final", 18, LocalDateTime.now().minusHours(2)));

        // Combinar todas las publicaciones
        List<Publicacion> publicaciones = new ArrayList<>();
        publicaciones.addAll(florencia.getPublicaciones());
        publicaciones.addAll(mauri.getPublicaciones());
        publicaciones.addAll(luz.getPublicaciones());
        publicaciones.addAll(ana.getPublicaciones());
        publicaciones.addAll(pedro.getPublicaciones());

        // Optimización (Knapsack) para mostrar solo algunas publicaciones
        int espacioMax = 5; // Número de publicaciones a mostrar
        Knapsack.Resultado resultado = Knapsack.optimizarPortada(publicaciones, espacioMax);

        // Agregar publicaciones seleccionadas al feed
        for (Publicacion p : resultado.getPublicacionesSeleccionadas()) {
            feedPanel.add(crearCardPublicacion(p));
        }

        JScrollPane scroll = new JScrollPane(feedPanel);
        scroll.setBorder(null);
        add(scroll, BorderLayout.CENTER);
    }

    private JPanel crearCardPublicacion(Publicacion p) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#e0e0e0")),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        // Contenido de la publicación
        JLabel contenido = new JLabel("<html><b>" + p.getContenido() + "</b></html>");
        contenido.setFont(new Font("SansSerif", Font.PLAIN, 16));
        card.add(contenido, BorderLayout.CENTER);

        // Likes
        JLabel likes = new JLabel("👍 " + p.getLikes() + " likes");
        likes.setFont(new Font("SansSerif", Font.PLAIN, 12));
        likes.setForeground(Color.GRAY);
        card.add(likes, BorderLayout.SOUTH);

        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        card.setPreferredSize(new Dimension(0, 80));
        card.setMinimumSize(new Dimension(0, 80));

        return card;
    }
}
