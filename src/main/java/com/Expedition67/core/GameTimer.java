package com.Expedition67.core;

/**
 * A simple timer to keep track of elapsed game time.
 */
public class GameTimer {

    private boolean isTimeCounter;
    private int ticks;
    private int totalSeconds;

    /**
     * Constructs a new GameTimer and initializes it.
     */
    public GameTimer() {
        reset();
    }

    /**
     * Starts or resumes the timer.
     */
    public void start() {
        this.isTimeCounter = true;
    }

    /**
     * Stops or pauses the timer.
     */
    public void stop() {
        this.isTimeCounter = false;
    }

    /**
     * Resets the timer to zero.
     */
    public void reset() {
        this.isTimeCounter = false;
        this.ticks = 0;
        this.totalSeconds = 0;
    }

    /**
     * Updates the timer, called once per frame from the main game loop.
     */
    public void update() {
        if (isTimeCounter) {
            ticks++;
            if (ticks >= 30) {
                ticks = 0;
                totalSeconds++;
            }
        }
    }

    /**
     * Formats the elapsed time into an "MM:SS" string.
     *
     * @return A formatted string of the current time.
     */
    public String getTimeString() {
        int min = totalSeconds / 60;
        int sec = totalSeconds % 60;
        return String.format("%02d:%02d", min, sec);
    }
}
