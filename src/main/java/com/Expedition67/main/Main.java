package com.Expedition67.main;

import java.awt.*;

/**
 * The main entry point of the application.
 */
public class Main {

    /**
     * The main method, which starts the game.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(Game::new);
    }
}
