package com.Expedition67.core;

import com.Expedition67.states.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GameStateManager {

    public static final int MENU_STATE = 0;
    public static final int COMBAT_STATE = 1;
    public static final int CARD_DROP_STATE = 2;
    public static final int RESULT_STATE = 3;
    public static final int CREDITS_STATE = 4;
    public static final int INVENTORY_STATE = 5;

    private GameState currentState;
    private List<GameState> gameStates;

    public GameStateManager() {
        loadGameStates();
        currentState = gameStates.get(MENU_STATE);
    }

    private void loadGameStates() {
        gameStates = new ArrayList<>();
        gameStates.add(new MenuState());
        gameStates.add(new CombatState());
        gameStates.add(new CardDropState());
        gameStates.add(new ResultState());
        gameStates.add(new CreditsState());
        gameStates.add(new InventoryState());
    }

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

    public void update() {
        if (currentState != null) {
            currentState.update();
        }
    }

    public void render(Graphics g) {
        if (currentState != null) {
            currentState.render(g);
        }
    }

    public void mouseClicked(MouseEvent e) {
        if (currentState != null) {
            currentState.mouseClicked(e);
        }
    }

    public void mouseMoved(MouseEvent e) {
        if (currentState != null) {
            currentState.mouseMoved(e);
        }
    }
}
