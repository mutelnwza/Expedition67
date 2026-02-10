package com.Expedition67.core;

import com.Expedition67.states.*;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GameManager {

    // --- Constants for Game States ---
    public static final int MENU_STATE = 0;
    public static final int COMBAT_STATE = 1;
    public static final int CARD_DROP_STATE = 2;
    public static final int RESULT_STATE = 3;
    public static final int CREDITS_STATE = 4;

    // --- Fields ---
    private GameState currentState;
    private List<GameState> gameStates;

    private boolean isTimeCounter;
    private int ticks;
    private int totalSeconds;
    private int room;

    /**
     * Constructor: Initializes the game states and sets the starting state.
     */
    public GameManager() {
        loadGameStates();
        // Start the game with the Menu State
        setCurrentState(MENU_STATE, 0);
    }

    /**
     * Creates instances of all game states and adds to the list.
     */
    private void loadGameStates() {
        gameStates = new ArrayList<>();
        gameStates.add(new MenuState(this));
        gameStates.add(new CombatState(this));
        gameStates.add(new CardDropState(this));
        gameStates.add(new ResultState(this));
        gameStates.add(new CreditsState(this));
    }

    /**
     * Switches the current game state.
     *
     * @param state The index of the new state (use constants)
     * @param id    An optional identifier to pass to the new state
     */
    public void setCurrentState(int state, int id) {
        if (currentState != null) {
            currentState.exit();
        }
        currentState = gameStates.get(state);
        currentState.enter(id);
    }

    /**
     * Enables or disables the game timer.
     */
    public void setTimeCounter(boolean timeCounter) {
        isTimeCounter = timeCounter;
    }

    /**
     * Returns the formatted time string (MM:SS)
     */
    public String getTimeString() {
        int min = totalSeconds / 60;
        int sec = totalSeconds % 60;
        return String.format("%02d:%02d", min, sec);
    }

    /**
     * Resets game variables
     */
    public void newGame() {
        isTimeCounter = false;
        ticks = 0;
        totalSeconds = 0;
        room = 0;
    }

    /**
     * The main update loop. Handles state updates and time counting.
     */
    public void update() {
        if (currentState != null) {
            currentState.update();
        }

        // Handle the time counter
        if (isTimeCounter) {
            ticks++;
            // FPS = 30
            if (ticks >= 30) {
                ticks = 0;
                totalSeconds++;
            }
        }
    }

    /**
     * Delegates rendering to the current game state.
     */
    public void render(Graphics g) {
        if (currentState != null) {
            currentState.render(g);
        }
    }

    /**
     * Passes mouse click events to the current game state.
     */
    public void mouseClicked(MouseEvent e) {
        if (currentState != null) {
            currentState.mouseClicked(e);
        }
    }

    /**
     * Passes mouse movement events to the current game state.
     */
    public void mouseMoved(MouseEvent e) {
        if (currentState != null) {
            currentState.mouseMoved(e);
        }
    }

    // --- Getters and Setters ---

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }
}
