package com.Expedition67.core.graphics;

import javax.swing.*;

public class GameWindow {

    private final GameView gameView;

    /**
     * Constructor: Creates the main application window and embeds the GameView.
     */
    public GameWindow() {
        // Create the content panel
        gameView = new GameView();

        // Configure the Window Frame
        JFrame jFrame = new JFrame();
        jFrame.setTitle("Expedition 67");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);

        // Add game content
        jFrame.add(gameView);
        jFrame.pack();

        // Center on screen and show
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    /**
     * Triggers a repaint of the game view.
     */
    public void repaint() {
        gameView.repaint();
    }

    /**
     * Request input focus for the game view.
     */
    public void requestFocus() {
        gameView.requestFocus();
    }
}
