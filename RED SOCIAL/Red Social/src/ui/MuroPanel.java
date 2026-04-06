package ui;

import model.Publicacion;
import model.Usuario;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MuroPanel extends JPanel {

    public MuroPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#f0f2f5"));

        JLabel title = new JLabel("📜 Muro de Publicaciones");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(Color.decode("#1877f2"));
        title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        add(title, BorderLayout.NORTH);

        JPanel feedPanel = new JPanel();
        feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.Y_AXIS));
        feedPanel.setBackground(Color.decode("#f0f2f5"));

        // Crear usuarios
        Usuario florencia = new Usuario("Florencia");
        Usuario mauri = new Usuario("Mauro");
        Usuario luz = new Usuario("Luz");
        Usuario pedro = new Usuario("Pedro");
        Usuario ana = new Usuario("Ana");

        // Publicaciones de Florencia
        florencia.agregarPublicacion(new Publicacion("Estudiando para el parcial", 15, LocalDateTime.now().minusHours(2)));
        florencia.agregarPublicacion(new Publicacion("Nuevo proyecto en Java", 40, LocalDateTime.now().minusHours(5)));
        florencia.agregarPublicacion(new Publicacion("Tomando un café y escuchando música", 22, LocalDateTime.now().minusHours(1)));

        // Publicaciones de Mauro
        mauri.agregarPublicacion(new Publicacion("Café con amigos", 10, LocalDateTime.now().minusHours(1)));
        mauri.agregarPublicacion(new Publicacion("Terminando la tarea de algoritmos", 35, LocalDateTime.now().minusHours(4)));
        mauri.agregarPublicacion(new Publicacion("Aprendiendo sobre redes neuronales", 28, LocalDateTime.now().minusHours(3)));

        // Publicaciones de Luz
        luz.agregarPublicacion(new Publicacion("Trabajo en equipo", 25, LocalDateTime.now().minusHours(3)));
        luz.agregarPublicacion(new Publicacion("Leyendo un libro de ciencia ficción", 18, LocalDateTime.now().minusHours(6)));
        luz.agregarPublicacion(new Publicacion("Practicar guitarra por la tarde", 12, LocalDateTime.now().minusHours(2)));

        // Publicaciones de Ana
        ana.agregarPublicacion(new Publicacion("Clase de matemáticas", 5, LocalDateTime.now().minusHours(4)));
        ana.agregarPublicacion(new Publicacion("Salí a correr al parque", 12, LocalDateTime.now().minusHours(2)));
        ana.agregarPublicacion(new Publicacion("Cocinando una receta nueva", 20, LocalDateTime.now().minusHours(1)));

        // Publicaciones de Pedro
        pedro.agregarPublicacion(new Publicacion("Deportes en la universidad", 30, LocalDateTime.now().minusHours(6)));
        pedro.agregarPublicacion(new Publicacion("Comprando ingredientes para cocinar", 8, LocalDateTime.now().minusHours(5)));
        pedro.agregarPublicacion(new Publicacion("Preparando presentación final", 18, LocalDateTime.now().minusHours(2)));

        // Combinar todas las publicaciones
        List<Publicacion> todas = new ArrayList<>();
        todas.addAll(florencia.getPublicaciones());
        todas.addAll(mauri.getPublicaciones());
        todas.addAll(luz.getPublicaciones());
        todas.addAll(ana.getPublicaciones());
        todas.addAll(pedro.getPublicaciones());

        // Desordenar publicaciones para simular feed real
        Collections.shuffle(todas);

        // Agregar publicaciones como cards
        for (Publicacion p : todas) {
            feedPanel.add(crearCardPublicacion(p));
        }

        JScrollPane scroll = new JScrollPane(feedPanel);
        scroll.setBorder(null);
        add(scroll, BorderLayout.CENTER);
    }

    private JPanel crearCardPublicacion(Publicacion p) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#e0e0e0")),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel contenido = new JLabel("<html><b>" + p.getContenido() + "</b></html>");
        contenido.setFont(new Font("SansSerif", Font.PLAIN, 16));
        card.add(contenido, BorderLayout.CENTER);

        JLabel likes = new JLabel("👍 " + p.getLikes() + " likes");
        likes.setFont(new Font("SansSerif", Font.PLAIN, 12));
        likes.setForeground(Color.GRAY);
        card.add(likes, BorderLayout.SOUTH);

        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        return card;
    }
}
