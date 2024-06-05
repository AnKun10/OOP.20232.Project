package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.*;

public class TreePanel extends JPanel {
    private JComboBox<String> treeTypeComboBox;
    private JTextArea outputArea;
    private TreeVisualizer treeVisualizer;
    private Tree currentTree;

    public TreePanel() {
        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        add(controlPanel, BorderLayout.NORTH);

        treeTypeComboBox = new JComboBox<>(new String[]{"Generic Tree", "Binary Tree", "Balanced Tree", "Balanced Binary Tree"});
        controlPanel.add(treeTypeComboBox);

        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createTree();
            }
        });
        controlPanel.add(createButton);

        JButton insertButton = new JButton("Insert");
        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Insert node logic
            }
        });
        controlPanel.add(insertButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Delete node logic
            }
        });
        controlPanel.add(deleteButton);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Update node logic
            }
        });
        controlPanel.add(updateButton);

        JButton traverseButton = new JButton("Traverse");
        traverseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Traverse tree logic
            }
        });
        controlPanel.add(traverseButton);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Search node logic
            }
        });
        controlPanel.add(searchButton);

        outputArea = new JTextArea();
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        treeVisualizer = new TreeVisualizer();
        add(treeVisualizer, BorderLayout.SOUTH);
    }

    private void createTree() {
        String selectedType = (String) treeTypeComboBox.getSelectedItem();
        switch (selectedType) {
            case "Generic Tree":
                currentTree = new GenericTree();
                break;
            case "Binary Tree":
                currentTree = new BinaryTree();
                break;
            case "Balanced Tree":
                currentTree = new BalancedTree();
                break;
            case "Balanced Binary Tree":
                currentTree = new BalancedBinaryTree();
                break;
        }
        displayMessage("Created a " + selectedType);
    }

    private void displayMessage(String message) {
        outputArea.append(message + "\n");
    }
}
