package com.example.game.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.example.game.grid.GameGrid;
import com.example.game.model.Cell;

public class GridGameGUI extends JFrame {
    private GameGrid gameGrid;
    private JPanel gridPanel;
    private JLabel scoreLabel;
    private int[] firstSelectedCell = null;

    public GridGameGUI(GameGrid gameGrid) {
        this.gameGrid = gameGrid;
        initializeUI();
    }

    public void launch() {
        setVisible(true);  // Make the window visible
    }

    private void initializeUI() {
        setTitle("Grid Game");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(Color.DARK_GRAY);

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 23));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setForeground(Color.WHITE);

        // Create a panel to hold the score label and the restart button
        JPanel scorePanel = new JPanel(new BorderLayout());
        scorePanel.setBackground(Color.DARK_GRAY);
        scorePanel.add(scoreLabel, BorderLayout.CENTER);

        // Add Restart Button
        JButton restartButton = new JButton("Restart");
        restartButton.setFont(new Font("Arial", Font.PLAIN, 12));
        restartButton.addActionListener(e -> restartGame());
        scorePanel.add(restartButton, BorderLayout.EAST);

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(gameGrid.getGrid().length, gameGrid.getGrid()[0].length));
        add(gridPanel);

        getContentPane().add(scorePanel, BorderLayout.NORTH);
        getContentPane().add(gridPanel, BorderLayout.CENTER);

        printGrid(); // Display the grid
    }

    private void restartGame() {
        gameGrid = new GameGrid(gameGrid.getGrid().length, gameGrid.getGrid()[0].length); // Recreate the game grid
        firstSelectedCell = null; // Reset the selected cell
        printGrid(); // Refresh the grid
        scoreLabel.setText("Score: 0"); // Reset the score label
    }

    public void printGrid() {
        gridPanel.removeAll(); // Clear previous components
        Cell[][] grid = gameGrid.getGrid();
        Font labelFont = new Font("Arial", Font.BOLD, 45);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                JLabel cellLabel = new JLabel();
                if (grid[i][j] != null) {
                    cellLabel.setText(String.valueOf(grid[i][j].getNumber()));
                    cellLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    cellLabel.setOpaque(true);
                    cellLabel.setForeground(grid[i][j].getAwtColor());
                    cellLabel.setFont(labelFont); // Set the larger font
                    cellLabel.setBackground(Color.GRAY);
                } else {
                    cellLabel.setText(" "); // Empty cell
                }

                cellLabel.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK));
                gridPanel.add(cellLabel);

                final int row = i;
                final int col = j;
                cellLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handleCellClick(row, col);
                    }
                });
            }
        }

        gridPanel.revalidate(); // Refresh the panel
        gridPanel.repaint(); // Repaint to reflect changes
        scoreLabel.setText("Score: " + gameGrid.getScore());
    }

    private void handleCellClick(int row, int col) {
        if (firstSelectedCell == null) {
            // First cell is selected
            firstSelectedCell = new int[]{row, col};
            System.out.println("First cell selected at (" + row + ", " + col + ")");
            // Optionally, provide some visual feedback for the first selected cell
            highlightCell(row, col, Color.ORANGE);
        } else {
            // Second cell is selected
            int row1 = firstSelectedCell[0];
            int col1 = firstSelectedCell[1];

            System.out.println("Second cell selected at (" + row + ", " + col + ")");
            highlightCell(row, col, Color.ORANGE);

            // Attempt to merge the cells
            if (gameGrid.mergeCells(row1, col1, row, col)) {
                System.out.println("Cells merged successfully!");
            } else {
                System.out.println("Merge failed!");
            }

            // Clear selection
            firstSelectedCell = null;
            printGrid(); // Refresh the grid to reflect changes
        }
    }

    private void highlightCell(int row, int col, Color color) {
        Component component = gridPanel.getComponent(row * gameGrid.getGrid()[0].length + col);
        if (component instanceof JLabel) {
            component.setBackground(color);
        }
    }
}