package ui;

import javax.swing.*;
import java.awt.*;

public class TopBarPanel extends JPanel {
    public TopBarPanel() {
        setBackground(new Color(24, 119, 242)); // azul Facebook
        setPreferredSize(new Dimension(1000, 60));
        setLayout(new BorderLayout());

        JLabel logo = new JLabel("Red Social Universitaria");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("SansSerif", Font.BOLD, 20));
        logo.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        add(logo, BorderLayout.WEST);
    }
}
