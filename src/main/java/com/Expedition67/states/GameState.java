package com.Expedition67.states;

import com.Expedition67.core.GameManager;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class GameState {
    protected final GameManager gameManager;

    public GameState(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public abstract void update();

    public abstract void render(Graphics g);

    public abstract void mouseClicked(MouseEvent e);

    public abstract void mouseMoved(MouseEvent e);
}
