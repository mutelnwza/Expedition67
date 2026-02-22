package com.Expedition67.core;

public class GameTimer {

    private boolean isTimeCounter;
    private int ticks;
    private int totalSeconds;

    public GameTimer() {
        reset();
    }

    public void start() {
        isTimeCounter = true;
    }

    public void stop() {
        isTimeCounter = false;
    }

    public void reset() {
        isTimeCounter = false;
        ticks = 0;
        totalSeconds = 0;
    }

    public void update() {
        if (isTimeCounter) {
            ticks++;
            if (ticks >= 30) {
                ticks = 0;
                totalSeconds++;
            }
        }
    }

    public String getTimeString() {
        int min = totalSeconds / 60;
        int sec = totalSeconds % 60;
        return String.format("%02d:%02d", min, sec);
    }
}
