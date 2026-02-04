package com.Expedition67.core;

import javax.swing.*;

public class GameWindow {
    private final GameView gameView;
    private final JFrame jFrame;

    public GameWindow(GameManager gameManager) {
        gameView = new GameView(gameManager);

        jFrame = new JFrame();
        jFrame.setSize(GameView.GAME_WIDTH, GameView.GAME_HEIGHT);
        jFrame.add(gameView);

        jFrame.setResizable(false);
        jFrame.setTitle("Expedition 67");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    public void repaint() {
        gameView.repaint();
    }

    public void requestFocus() {
        gameView.requestFocus();
    }
}
