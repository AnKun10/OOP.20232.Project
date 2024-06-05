package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Tree Visualization");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setJMenuBar(createMenuBar());

        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        CardLayout cardLayout = new CardLayout();
        JPanel cards = new JPanel(cardLayout);
        mainPanel.add(cards, BorderLayout.CENTER);

        TreePanel treePanel = new TreePanel();
        cards.add(treePanel, "TreePanel");

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(cards, "MainMenu"));
        mainPanel.add(backButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        JMenuItem menuItemTree = new JMenuItem("Tree Visualization");
        menuItemTree.addActionListener(e -> new TreePanel());
        menu.add(menuItemTree);

        JMenuItem menuItemHelp = new JMenuItem("Help");
        menuItemHelp.addActionListener(e -> JOptionPane.showMessageDialog(this, "Help content..."));
        menu.add(menuItemHelp);

        JMenuItem menuItemQuit = new JMenuItem("Quit");
        menuItemQuit.addActionListener(e -> System.exit(0));
        menu.add(menuItemQuit);

        menuBar.add(menu);
        return menuBar;
    }
}
