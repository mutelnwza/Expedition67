package com.Expedition67.core;

import com.Expedition67.states.*;
import com.Expedition67.storage.CardInventory;
import com.Expedition67.storage.Warehouse;
import com.Expedition67.unit.Unit;

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
    private static GameManager instance;

    private GameState currentState;
    private List<GameState> gameStates;

    private boolean isTimeCounter;
    private int ticks;
    private int totalSeconds;
    private int room;

    private Unit player;

    /**
     * Constructor: Initializes the game states and sets the starting state.
     */
    private GameManager() {
        loadGameStates();
        // Start the game with the Menu State
        currentState = gameStates.get(MENU_STATE);
    }

    public static GameManager Instance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    /**
     * Creates instances of all game states and adds to the list.
     */
    private void loadGameStates() {
        gameStates = new ArrayList<>();
        gameStates.add(new MenuState());
        gameStates.add(new CombatState());
        gameStates.add(new CardDropState());
        gameStates.add(new ResultState());
        gameStates.add(new CreditsState());
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
        isTimeCounter = true;
        ticks = 0;
        totalSeconds = 0;
        room = 0;

        player = Warehouse.Instance().spawnPlayer(300, 460);
        CombatManager.initNew();
        CardInventory.Instance().emptyInventory();
        // Temp
        CardInventory.Instance().addCard(Warehouse.Instance().spawnCard("Remnant Hit"), 5);
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

    public Unit getPlayer() {
        return player;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }
}
