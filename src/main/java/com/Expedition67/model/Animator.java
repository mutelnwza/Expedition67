package com.Expedition67.model;

import java.util.HashMap;

/**
 * Manages sprite animations for a game unit.
 */
public class Animator {

    private HashMap<String, AnimatorConfig> animations = new HashMap<>();
    private String currentAction = "";
    private int tick, index, currentSpeed, currentRow, currentFrameCount;

    /**
     * Adds a new animation configuration.
     *
     * @param name       The name of the animation (e.g., "idle", "attack").
     * @param row        The row of the sprite sheet for this animation.
     * @param speed      The speed of the animation (ticks per frame).
     * @param frameCount The number of frames in the animation.
     */
    public void addAnimation(String name, int row, int speed, int frameCount) {
        animations.put(name, new AnimatorConfig(row, speed, frameCount));
    }

    /**
     * Plays a specified animation.
     *
     * @param name The name of the animation to play.
     */
    public void play(String name) {
        if (currentAction.equals(name)) {
            return;
        }
        AnimatorConfig config = animations.get(name);
        if (config != null) {
            this.currentAction = name;
            this.currentSpeed = config.speed();
            this.currentFrameCount = config.frameCount();
            this.currentRow = config.row();
            this.index = 0;
            this.tick = 0;
        } else {
            System.err.println("Animation name not found: " + name);
        }
    }

    /**
     * Updates the animation frame, called once per game tick.
     */
    public void update() {
        if (currentAction.isEmpty()) {
            return;
        }
        tick++;
        if (tick >= currentSpeed) {
            tick = 0;
            index = (index + 1) % currentFrameCount;
        }
    }

    /**
     * Copies animation configurations from another animator.
     *
     * @param source The animator to copy from.
     */
    public void copy(Animator source) {
        this.animations = new HashMap<>(source.animations);
    }

    /**
     * Gets the current animation frame index.
     *
     * @return The current frame index.
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Gets the current animation sprite sheet row.
     *
     * @return The current row.
     */
    public int getRow() {
        return this.currentRow;
    }

    /**
     * A record to store the configuration for a single animation.
     */
    public record AnimatorConfig(int row, int speed, int frameCount) {
    }
}
