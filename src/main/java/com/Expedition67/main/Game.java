package com.Expedition67.main;

import com.Expedition67.core.GameManager;
import com.Expedition67.core.graphics.GameWindow;

/**
 * The main game class, responsible for the game loop and core components.
 */
public class Game implements Runnable {

    private static final int FPS = 30;
    private static final double UPDATE_INTERVAL = 1000000000.0 / FPS;

    private final GameWindow gameWindow;

    private Thread gameThread;
    private boolean isRunning;

    /**
     * Constructs a new Game, initializes core subsystems, and starts the game loop.
     */
    public Game() {
        GameManager.Instance();

        gameWindow = new GameWindow();
        gameWindow.requestFocus();

        start();
    }

    /**
     * The core game loop, running at a fixed FPS.
     */
    @Override
    public void run() {
        long lastFrameTime = System.nanoTime();
        double delta = 0;

        while (isRunning) {
            long currentTime = System.nanoTime();

            delta += (currentTime - lastFrameTime) / UPDATE_INTERVAL;
            lastFrameTime = currentTime;

            if (delta >= 1) {
                update();
                render();
                delta--;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Starts the game loop in a separate thread.
     */
    private void start() {
        isRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Updates game logic.
     */
    private void update() {
        GameManager.Instance().update();
    }

    /**
     * Triggers the window to redraw.
     */
    private void render() {
        gameWindow.repaint();
    }
}
