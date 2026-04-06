package ui;

import javax.swing.*;
import java.awt.*;

public class SideMenuPanel extends JPanel {
    private MainFrame mainFrame;

    public SideMenuPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(new GridLayout(6, 1, 0, 10));
        setPreferredSize(new Dimension(200, 0));
        setBackground(new Color(240, 242, 245));

        JButton muroBtn = new JButton("Muro de Publicaciones");
        JButton redBtn = new JButton("Red Mínima de Amigos");
        JButton recomBtn = new JButton("Recomendación de Amigos");
        JButton portadaBtn = new JButton("Optimizar Portada");
        JButton bloqueoBtn = new JButton("Simular Bloqueos");

        muroBtn.addActionListener(e -> mainFrame.getContentPanel().showPanel(new MuroPanel()));
        redBtn.addActionListener(e -> mainFrame.getContentPanel().showPanel(new RedMinimaPanel()));
        recomBtn.addActionListener(e -> mainFrame.getContentPanel().showPanel(new RecomendacionPanel()));
        portadaBtn.addActionListener(e -> mainFrame.getContentPanel().showPanel(new PortadaPanel()));
        bloqueoBtn.addActionListener(e -> mainFrame.getContentPanel().showPanel(new BloqueoPanel()));

        add(muroBtn);
        add(redBtn);
        add(recomBtn);
        add(portadaBtn);
        add(bloqueoBtn);
    }
}
