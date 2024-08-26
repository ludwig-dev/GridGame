package com.example.game;

import com.example.game.grid.GameGrid;
import com.example.game.ui.GUI;

public class App {
    public static void main(String[] args) {
        GameGrid gridArr = new GameGrid(5, 5);

        // Ensure the GUI is launched on the Event Dispatch Thread (EDT)
        javax.swing.SwingUtilities.invokeLater(() -> {
            GUI gui = new GUI(gridArr);
            gui.launch(); // Launch the GUI window
        });
    }
}
