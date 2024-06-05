package gui;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    public ControlPanel() {
        setLayout(new FlowLayout());

        JComboBox<String> graphTypeBox = new JComboBox<>(new String[]{"Directed", "Undirected"});
        JTextField nodeCountField = new JTextField(5);
        JButton generateButton = new JButton("Generate Graph");
        JButton resetButton = new JButton("Reset");

        add(new JLabel("Graph Type:"));
        add(graphTypeBox);
        add(new JLabel("Nodes:"));
        add(nodeCountField);
        add(generateButton);
        add(resetButton);
    }
}
