package com.Expedition67.core.graphics;

import com.Expedition67.storage.AssetManager;

import javax.swing.*;

/**
 * The main window frame for the application.
 */
public class GameWindow {

    private final GameView gameView;

    /**
     * Constructs a new GameWindow and configures the main application window.
     */
    public GameWindow() {
        this.gameView = new GameView();

        JFrame jFrame = new JFrame();

        jFrame.setTitle("Expedition 67");
        jFrame.setIconImage(AssetManager.Instance().getIcon());
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);

        jFrame.add(gameView);
        jFrame.pack();

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
     * Requests input focus for the game view.
     */
    public void requestFocus() {
        gameView.requestFocus();
    }
}
