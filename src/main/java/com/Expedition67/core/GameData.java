package com.Expedition67.core;

import com.Expedition67.unit.Unit;
import com.Expedition67.unit.player.PlayerBrain;

/**
 * Stores the overall state of the game, including room number and player data.
 */
public class GameData {

    private int room;
    private Unit player;

    /**
     * Constructs a new GameData object and initializes the game state.
     */
    public GameData() {
        reset();
    }

    /**
     * Resets the game data to its default initial state.
     */
    public void reset() {
        this.room = 0;
        this.player = null;
    }

    /**
     * Gets the current room number.
     *
     * @return The current room number.
     */
    public int getRoom() {
        return room;
    }

    /**
     * Increments the room number, moving to the next room.
     */
    public void incrementRoom() {
        this.room++;
    }

    /**
     * Decrements the room number, moving to the previous room.
     */
    public void decrementRoom() {
        this.room--;
    }

    /**
     * Gets the main player object.
     *
     * @return The player's Unit object.
     */
    public Unit getPlayer() {
        return player;
    }

    /**
     * Sets the main player object for the game.
     *
     * @param player The player's Unit object.
     */
    public void setPlayer(Unit player) {
        this.player = player;
    }

    /**
     * Gets the player's brain for direct access to player-specific logic.
     *
     * @return The PlayerBrain object, or null if the player is not set.
     */
    public PlayerBrain getPlayerBrain() {
        if (player != null) {
            return (PlayerBrain) player.getBrain();
        }
        return null;
    }
}
