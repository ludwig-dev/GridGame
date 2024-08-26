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

    public GameGrid(int row, int col) {
        grid = new Cell[row][col];
        score = new Score();
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
                 int randomNumber = random.nextInt(5) + 1;
                //int randomNumber = 1;
                Color associatedColor = numberToColorMap.get(randomNumber);
                this.grid[i][j] = new Cell(randomNumber, associatedColor);
            }
        }
        System.out.print("done\n");
    }

    private void removeCell(int row, int col) {
        // If there are cells above the selectedCell, move them one step down.
        int topCell = row;

        for (int i = row; i > 0; i--) {
            if (grid[i - 1][col] == null) {
                grid[i][col] = null;
                return;
            }
            grid[i][col] = grid[i - 1][col];
            System.out.println("Swapped Cell: (" + i + ", " + col + ") with " + "(" + (i - 1) + ", " + col + ")");
            topCell--;
        }

        grid[topCell][col] = null;
        // System.out.println("Removed Cell: (" + topCell + ", " + col + ")");

    }

    private boolean isValidSelection(int row1, int col1, int row2, int col2) {
        // Ensure the selections are not null
        if (this.grid[row1][col1] == null || this.grid[row2][col2] == null){
            System.out.println("Selected a empty sqaure!");
            return false;
        }

        //Ensure the selections is not the same
        if(row1 == row2 && col1 == col2){
            System.out.println("Selected the same square!");
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

    public boolean mergeCells(int row1, int col1, int row2, int col2) {
        // row1 and col1 is the cordniates of the first selected cell
        if (isValidSelection(row1, col1, row2, col2)) {
            Cell cell1 = grid[row1][col1];
            Cell cell2 = grid[row2][col2];
            if (cell1.getNumber() == cell2.getNumber()) {
                cell2.setNumber(cell2.getNumber() + 1);
                cell2.setColor(numberToColorMap.get(cell2.getNumber()));
                System.out.println("Upgraded Cell: (" + row2 + ", " + col2 + ") from: " + (cell2.getNumber()-1) + " to: " + cell2.getNumber());
                removeCell(row1, col1);
                score.incrementScore(cell2.getNumber()-1);

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
