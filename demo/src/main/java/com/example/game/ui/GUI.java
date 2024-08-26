package com.example.game.ui;
import java.util.Scanner;

import com.example.game.grid.GameGrid;
import com.example.game.model.Cell;


public class GUI {
    private GameGrid gameGrid;

    public GUI(GameGrid gameGrid) {
        this.gameGrid = gameGrid;
    }

    public void printGrid() {
        Cell[][] grid = gameGrid.getGrid();


        // Print the grid with row headers
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != null) {
                    System.out.print(grid[i][j].getColoredNumber() + " ");
                } else {
                    System.out.print("  "); // Space for null cells
                }
            }
            System.out.println();
        }
    }

    public void selectAndMergeCells() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the coordinates of the first cell to merge (row and column): ");
        int row1 = scanner.nextInt() - 1;
        int col1 = scanner.nextInt() - 1;
        System.out.print("Enter the coordinates of the second cell to merge (row and column): ");
        int row2 = scanner.nextInt() - 1;
        int col2 = scanner.nextInt() - 1;

        boolean merged = gameGrid.mergeCells(row1, col1, row2, col2);
        if (merged) {
            System.out.println("Cells merged successfully!");
        } else {
            System.out.println("Merge failed! The cells are either not adjacent or do not have the same number.");
        }
    }
}
