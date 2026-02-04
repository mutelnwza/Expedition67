package com.Expedition67.main;

import com.Expedition67.core.GameManager;
import com.Expedition67.core.GameWindow;

public class Game implements Runnable {

    private final GameWindow gameWindow;
    private final GameManager gameManager;
    private Thread gameLoopThread;
    private final int FPS = 30;

    public Game() {
        gameManager = new GameManager();
        gameWindow = new GameWindow(gameManager);
        gameWindow.requestFocus();
        start();
    }

    private void start() {
        gameLoopThread = new Thread(this);
        gameLoopThread.start();
    }

    @Override
    public void run() {
        double interval = 1000000000.0 / FPS; //10^9 nano sec = 1 sec
        long lastFrame = System.nanoTime();
        double delta = 0;

        while (gameLoopThread.isAlive()) {
            long now = System.nanoTime();
            delta += (now - lastFrame) / interval;
            lastFrame = now;

            if (delta >= 1) {
                update(); //logics
                render(); //visuals
                delta--;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        try {
            gameLoopThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void update() {
        gameManager.update();
    }

    private void render() {
        gameWindow.repaint();
    }
}
