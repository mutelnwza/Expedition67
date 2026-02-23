package com.Expedition67.core;

import com.Expedition67.states.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the different states of the game, such as the main menu, combat, and inventory.
 */
public class GameStateManager {

    public static final int MENU_STATE = 0;
    public static final int COMBAT_STATE = 1;
    public static final int CARD_DROP_STATE = 2;
    public static final int RESULT_STATE = 3;
    public static final int CREDITS_STATE = 4;
    public static final int INVENTORY_STATE = 5;

    private GameState currentState;
    private List<GameState> gameStates;

    /**
     * Constructs a new GameStateManager and initializes all game states.
     */
    public GameStateManager() {
        loadGameStates();
        this.currentState = gameStates.get(MENU_STATE);
    }

    /**
     * Initializes and loads all game state objects into a list.
     */
    private void loadGameStates() {
        this.gameStates = new ArrayList<>();
        this.gameStates.add(new MenuState());
        this.gameStates.add(new CombatState());
        this.gameStates.add(new CardDropState());
        this.gameStates.add(new ResultState());
        this.gameStates.add(new CreditsState());
        this.gameStates.add(new InventoryState());
    }

    /**
     * Changes the current active game state.
     *
     * @param state The integer constant for the new state.
     * @param id    An optional parameter for the new state's "enter" method.
     */
    public void setCurrentState(int state, int id) {
        if (currentState instanceof InventoryState) {
            currentState = gameStates.get(state);
            return;
        }

        if (currentState != null && state != INVENTORY_STATE) {
            currentState.exit();
        }

        currentState = gameStates.get(state);
        currentState.enter(id);
    }

    /**
     * Updates the logic of the currently active game state.
     */
    public void update() {
        if (currentState != null) {
            currentState.update();
        }
    }

    /**
     * Renders the graphics of the currently active game state.
     *
     * @param g The Graphics object to draw with.
     */
    public void render(Graphics g) {
        if (currentState != null) {
            currentState.render(g);
        }
    }

    /**
     * Forwards mouse click events to the current game state.
     *
     * @param e The MouseEvent from the click.
     */
    public void mouseClicked(MouseEvent e) {
        if (currentState != null) {
            currentState.mouseClicked(e);
        }
    }

    /**
     * Forwards mouse movement events to the current game state.
     *
     * @param e The MouseEvent from the movement.
     */
    public void mouseMoved(MouseEvent e) {
        if (currentState != null) {
            currentState.mouseMoved(e);
        }
    }
}
