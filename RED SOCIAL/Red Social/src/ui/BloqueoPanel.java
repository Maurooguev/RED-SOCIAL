package ui;

import model.*;
import javax.swing.*;

import algorithms.ConnectivityChecker;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BloqueoPanel extends JPanel {

    private Grafo grafo;
    private List<Usuario> usuarios;

    public BloqueoPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#f0f2f5"));

        // Título
        JLabel title = new JLabel("🚫 Simular Bloqueos");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(Color.decode("#1877f2"));
        title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);

        // Panel central
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(Color.decode("#f0f2f5"));

        // Inicializar grafo y usuarios
        grafo = new Grafo();
        usuarios = new ArrayList<>();

        // Crear usuarios y nodos
        String[] nombres = {"Florencia", "Mauri", "Luz", "Pedro", "Ana"};
        List<Nodo> nodos = new ArrayList<>();

        for (String nombre : nombres) {
            Usuario u = new Usuario(nombre);
            Nodo n = new Nodo(nombre);
            u.setNodo(n);
            grafo.agregarNodo(n);
            usuarios.add(u);
            nodos.add(n);
        }

        // Conexiones de ejemplo
        grafo.agregarArista(nodos.get(0), nodos.get(1), 2);
        grafo.agregarArista(nodos.get(0), nodos.get(2), 5);
        grafo.agregarArista(nodos.get(1), nodos.get(2), 1);
        grafo.agregarArista(nodos.get(1), nodos.get(3), 3);
        grafo.agregarArista(nodos.get(2), nodos.get(4), 2);
        grafo.agregarArista(nodos.get(3), nodos.get(4), 1);

        // Crear cards
        for (Usuario u : usuarios) {
            content.add(crearCardBloqueo(u));
            content.add(Box.createRigidArea(new Dimension(0,5)));
        }

        JScrollPane scroll = new JScrollPane(content);
        scroll.setBorder(null);
        add(scroll, BorderLayout.CENTER);
    }

    private JPanel crearCardBloqueo(Usuario u) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#e0e0e0"), 1, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        JLabel nombre = new JLabel(u.getNombre());
        nombre.setFont(new Font("SansSerif", Font.BOLD, 16));
        card.add(nombre, BorderLayout.CENTER);

        JButton bloquearBtn = new JButton("Bloquear");
        bloquearBtn.setBackground(Color.decode("#ff4d4f"));
        bloquearBtn.setForeground(Color.WHITE);
        bloquearBtn.setFocusPainted(false);
        bloquearBtn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#ff4d4f"), 1, true),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        bloquearBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bloquearBtn.setFont(new Font("SansSerif", Font.BOLD, 14));

        // Acción al bloquear
        bloquearBtn.addActionListener(e -> {
            bloquearBtn.setText("Bloqueado");
            bloquearBtn.setEnabled(false);
            bloquearBtn.setBackground(Color.GRAY);

            // Cortar aristas del nodo
            grafo.eliminarAristas(u.getNodo());

            // Verificar conectividad
            ConnectivityChecker checker = new ConnectivityChecker();
            boolean conectado = checker.esConexo(grafo);
            String mensaje = conectado ? "La red sigue conectada" : "La red se ha fragmentado";
            JOptionPane.showMessageDialog(this, "Usuario bloqueado.\n" + mensaje);
        });

        card.add(bloquearBtn, BorderLayout.EAST);

        return card;
    }
}
