package com.Expedition67.states;

import com.Expedition67.core.GameManager;
import com.Expedition67.ui.GameComponent;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public abstract class GameState {

    protected List<GameComponent> gameComponents;

    /**
     * Constructor: Initializes the list and loads initial UI components.
     */
    public GameState() {
        this.gameComponents = new ArrayList<>();
        loadComponents();
    }

    /**
     * Abstract method for child states to add their specific buttons and text.
     */
    protected abstract void loadComponents(); 

    /**
     * Called when the game switches to this state.
     *
     * @param id An optional ID.
     */
    public abstract void enter(int id);

    /**
     * Called when the game leaves this state.
     */
    public abstract void exit();

    /**
     * Updates all components in this state.
     */
    public void update() {
        for (GameComponent gameComponent : gameComponents) {
            gameComponent.update();
        }
    }

    /**
     * Renders all components in this state.
     */
    public void render(Graphics g) {
        for (GameComponent gameComponent : gameComponents) {
            gameComponent.render(g);
        }
    }

    /**
     * Passes mouse clicks to the components.
     */
    public void mouseClicked(MouseEvent e) {
        for (GameComponent gameComponent : gameComponents) {
            if (gameComponent.mouseClicked(e)) {
                break;
            }
        }
    }

    /**
     * Passes mouse movement to the components (for hover effects).
     */
    public void mouseMoved(MouseEvent e) {
        for (GameComponent gameComponent : gameComponents) {
            if (gameComponent.mouseMoved(e)) {
                break;
            }
        }
    }
}
