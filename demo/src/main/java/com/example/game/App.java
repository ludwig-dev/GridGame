package com.example.game;

import com.example.game.grid.GameGrid;
import com.example.game.ui.GUI;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("elo");

        GameGrid gridArr = new GameGrid(5, 5);
        GUI gui = new GUI(gridArr);
        gui.printGrid();

        while (true) {
            gui.selectAndMergeCells(); // Allow the user to select and merge cells
            gui.printGrid(); // Reprint the grid after merging
        }

    }
}
