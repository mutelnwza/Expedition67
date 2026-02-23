package com.Expedition67.states;

import com.Expedition67.ui.GameComponent;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * An abstract base class for all game states, such as the main menu or combat.
 */
public abstract class GameState {

    protected final List<GameComponent> gameComponents;

    /**
     * Constructs a new GameState and initializes its component list.
     */
    public GameState() {
        this.gameComponents = new ArrayList<>();
        loadComponents();
    }

    /**
     * An abstract method for subclasses to load their specific UI components.
     */
    protected abstract void loadComponents();

    /**
     * Called when the game enters this state.
     *
     * @param id An optional parameter for state initialization.
     */
    public abstract void enter(int id);

    /**
     * Called when the game exits this state.
     */
    public abstract void exit();

    /**
     * Updates all UI components in this state.
     */
    public void update() {
        for (GameComponent gameComponent : gameComponents) {
            gameComponent.update();
        }
    }

    /**
     * Renders all UI components in this state.
     *
     * @param g The Graphics object to draw with.
     */
    public void render(Graphics g) {
        for (GameComponent gameComponent : gameComponents) {
            gameComponent.render(g);
        }
    }

    /**
     * Forwards mouse click events to the UI components.
     *
     * @param e The MouseEvent from the click.
     */
    public void mouseClicked(MouseEvent e) {
        for (GameComponent gameComponent : gameComponents) {
            if (gameComponent.mouseClicked(e)) {
                break;
            }
        }
    }

    /**
     * Forwards mouse movement events to the UI components.
     *
     * @param e The MouseEvent from the movement.
     */
    public void mouseMoved(MouseEvent e) {
        for (GameComponent gameComponent : gameComponents) {
            if (gameComponent.mouseMoved(e)) {
                break;
            }
        }
    }
}
