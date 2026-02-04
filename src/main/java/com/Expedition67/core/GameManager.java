package com.Expedition67.core;

import com.Expedition67.states.GameState;
import com.Expedition67.states.MenuState;

import java.awt.*;
import java.awt.event.MouseEvent;

public class GameManager {
    private GameState currentState;
    private MenuState menuState;

    public GameManager() {
        menuState = new MenuState(this);

        currentState = menuState;
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

    public void setCurrentState(GameState state) {
        currentState = state;
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

    public MenuState getMenuState() {
        return menuState;
    }
}
