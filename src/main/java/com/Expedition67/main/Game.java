package com.Expedition67.main;

import com.Expedition67.core.GameManager;
import com.Expedition67.core.graphics.GameWindow;

public class Game implements Runnable {

    // --- Config ---
    private static final int FPS = 30;
    private static final double UPDATE_INTERVAL = 1000000000.0 / FPS; // 10^9 nano secs (1 sec) per frame

    // --- Core Components ---
    private final GameWindow gameWindow;

    // --- Loop Control ---
    private Thread gameThread;
    private boolean isRunning;

    /**
     * Constructor: Initializes core subsystems and starts the game loop.
     */
    public Game() {
        // Initialize main game logic handler
        GameManager.Instance();

        // Initialize visual window
        gameWindow = new GameWindow();
        gameWindow.requestFocus();

        // Begin the game loop
        start();
    }

    /**
     * Starts the separate thread for the game loop.
     */
    private void start() {
        isRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Stops the game loop safely.
     */
    public void stop() {
        isRunning = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * The core Game Loop.
     * Uses a "Delta Time" approach to runs the game at 30 FPS.
     */
    @Override
    public void run() {
        long lastFrameTime = System.nanoTime();
        double delta = 0;

        while (isRunning) {
            long currentTime = System.nanoTime();

            // Calculate how much time has passed
            delta += (currentTime - lastFrameTime) / UPDATE_INTERVAL;
            lastFrameTime = currentTime;

            // If enough time has passed for a frame, update and render
            if (delta >= 1) {
                update();
                render();
                delta--;
            }

            // Small sleep to reduce CPU usage
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
