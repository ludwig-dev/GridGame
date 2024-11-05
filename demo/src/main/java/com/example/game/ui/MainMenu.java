package com.example.game.ui;
import com.example.game.grid.GameGrid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Main Menu");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title Label
        JLabel titleLabel = new JLabel("Grid Game", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for better control
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        buttonPanel.setBackground(Color.DARK_GRAY);

        // New Game Button
        JButton newGameButton = new JButton("Start New Game");
        newGameButton.setFont(new Font("Arial", Font.PLAIN, 20)); // Smaller font size
        newGameButton.setPreferredSize(new Dimension(200, 50)); // Set preferred size
        newGameButton.setBackground(Color.LIGHT_GRAY);
        newGameButton.setFocusPainted(false);
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showGridSizeSelection();
            }
        });

        // Exit Button
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 20)); // Smaller font size
        exitButton.setPreferredSize(new Dimension(200, 50)); // Set preferred size
        exitButton.setBackground(Color.LIGHT_GRAY);
        exitButton.setFocusPainted(false);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Add buttons to panel with constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding

        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(newGameButton, gbc);

        gbc.gridy = 1;
        buttonPanel.add(exitButton, gbc);

        add(buttonPanel, BorderLayout.CENTER);

        // Background Color
        getContentPane().setBackground(Color.DARK_GRAY);
    }

    private void showGridSizeSelection() {
        String rowInput = JOptionPane.showInputDialog(this, "Enter number of rows:");
        String colInput = JOptionPane.showInputDialog(this, "Enter number of columns:");

        try {
            int rows = Integer.parseInt(rowInput);
            int cols = Integer.parseInt(colInput);

            GameGrid gridArr = new GameGrid(rows, cols);
            GridGameGUI gui = new GridGameGUI(gridArr);
            gui.launch();
            dispose(); // Close the main menu
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
        });
    }
}