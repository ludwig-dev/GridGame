package com.example.game.grid;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.example.game.model.Cell;
import com.example.game.model.Color;
import com.example.game.model.Score;

public class GameGrid {
    private Cell[][] grid;
    private Map<Integer, Color> numberToColorMap;
    private Score score;
    private int gridRow;
    private int gridCol;

    public GameGrid(int row, int col) {
        grid = new Cell[row][col];
        score = new Score();
        this.gridRow = row;
        this.gridCol = col;
        initializeNumberToColorMap();
        createGridArray();
    }

    private void initializeNumberToColorMap() {
        numberToColorMap = new HashMap<>();
        numberToColorMap.put(1, Color.BLUE);
        numberToColorMap.put(2, Color.GREEN);
        numberToColorMap.put(3, Color.RED);
        numberToColorMap.put(4, Color.YELLOW);
        numberToColorMap.put(5, Color.PURPLE);
    }

    private void createGridArray() {
        Random random = new Random();
        // System.out.print("\nCreating 2d array...");
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                int randomNumber = random.nextInt(4) + 1;
                // int randomNumber = 2;
                Color associatedColor = numberToColorMap.get(randomNumber);
                this.grid[i][j] = new Cell(randomNumber, associatedColor);
            }
        }
        System.out.print("done\n");
    }

    private void addCellAtTopRow(int row1, int col1, int row2, int col2) {
        // Fill the top with new cells and push them down
        Random random = new Random();
        int counter = 0;
        for (int i = 0; i < gridCol; i++) {
            if (i == col1) { // cells is horizontel
                moveCellsDownCol2(col1);

            } else if (i == col2 && !(col1 == col2))
                moveCellsDownCol(row2, col2);

            if (grid[0][i] == null) {
                while ((counter != (this.gridRow - 1)) && grid[counter + 1][i] == null) {
                    counter++;
                }
                // counter++;
                int randomNumber = random.nextInt(3) + 1;
                Color associatedColor = numberToColorMap.get(randomNumber);
                grid[counter][i] = new Cell(randomNumber, associatedColor);
                counter = 0;
            }
        }
    }

    private void moveCellsDownCol2(int col) {
        int numRows = grid.length;

        // Start from the bottom and move upwards
        for (int row = numRows - 1; row > 0; row--) {
            if (grid[row][col] == null) {
                // Find the topmost non-null cell above the current row
                for (int topRow = row - 1; topRow >= 0; topRow--) {
                    if (grid[topRow][col] != null) {
                        grid[row][col] = grid[topRow][col]; // Move the cell down
                        grid[topRow][col] = null; // Set the original position to null
                        System.out.println("Moved Cell: (" + topRow + ", " + col + ") to (" + row + ", " + col + ")");
                        break; // Move to the next row down
                    }
                }
            }
        }
    }

    private void moveCellsDownCol(int row, int col) {
        int topCell = row;
        for (int i = row; i > 0; i--) {
            if (grid[i - 1][col] == null) {
                grid[topCell][col] = null;
                return;
            }
            grid[i][col] = grid[i - 1][col];
            System.out.println("Moved Cell: (" + (i - 1) + ", " + col + ") to " + "(" + i + ", " + col + ")");
            topCell--;

        }
        grid[topCell][col] = null;
    }

    // TODO just create a function that sets a cell to null.

    public boolean mergeCells(int row1, int col1, int row2, int col2) {
        // row1 and col1 is the cordniates of the first selected cell
        if (isValidSelection(row1, col1, row2, col2)) {
            Cell cell1 = grid[row1][col1];
            Cell cell2 = grid[row2][col2];

            if (cell1.getNumber() == 5) {
                score.incrementScore(cell2.getNumber() - 1);
                grid[row1][col1] = null;
                grid[row2][col2] = null;
                addCellAtTopRow(row1, col1, row2, col2);
            } else {
                cell2.setNumber(cell2.getNumber() + 1);
                cell2.setColor(numberToColorMap.get(cell2.getNumber()));
                System.out.println("Upgraded Cell: (" + row2 + ", " + col2 + ") from: " + (cell2.getNumber() - 1)
                        + " to: " + cell2.getNumber());
                score.incrementScore(cell2.getNumber() - 1);
                this.grid[row1][col1] = null;
                moveCellsDownCol(row1, col1);

            }

            return true;

        }
        return false;
    }

    private boolean isValidSelection(int row1, int col1, int row2, int col2) {
        // Ensure the selections are not null
        if (this.grid[row1][col1] == null || this.grid[row2][col2] == null) {
            System.out.println("Selected a empty sqaure!");
            return false;
        }
        if (grid[row1][col1].getNumber() != grid[row2][col2].getNumber()) {
            System.out.println("Numbers are not equal!");
            return false;
        }
        // Ensure the selections are within bounds
        if (row1 >= 0 && row1 < grid.length && col1 >= 0 && col1 < grid[0].length &&
                row2 >= 0 && row2 < grid.length && col2 >= 0 && col2 < grid[0].length) {
            // Check if the cells are adjacent
            if ((Math.abs(row1 - row2) == 1 && col1 == col2) || // vertical
                    (Math.abs(col1 - col2) == 1 && row1 == row2) || // horizontal
                    (Math.abs(row1 - row2) == 1 && Math.abs(col1 - col2) == 1)) { // diagonal
                return true;
            }
        }
        return false;
    }

    public Cell[][] getGrid() {
        return this.grid;
    }

    public int getScore() {
        return score.getScore();
    }
}
