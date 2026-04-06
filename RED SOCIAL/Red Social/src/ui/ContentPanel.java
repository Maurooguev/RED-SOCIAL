package ui;

import javax.swing.*;
import java.awt.*;

public class ContentPanel extends JPanel {

    public ContentPanel() {
        setLayout(new BorderLayout());
    }

    public void showPanel(JPanel panel) {
        removeAll();
        add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
