package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private SideMenuPanel sideMenu;
    private ContentPanel contentPanel;
    private TopBarPanel topBar;

    public MainFrame() {
        setTitle("Red Social Universitaria");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Barra superior
        topBar = new TopBarPanel();
        add(topBar, BorderLayout.NORTH);

        // Menú lateral
        sideMenu = new SideMenuPanel(this);
        add(sideMenu, BorderLayout.WEST);

        // Panel central
        contentPanel = new ContentPanel();
        add(contentPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public ContentPanel getContentPanel() {
        return contentPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
