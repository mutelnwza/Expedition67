package com.Expedition67.states;

import com.Expedition67.core.GameManager;
import com.Expedition67.core.GameStateManager;
import com.Expedition67.core.graphics.GameView;
import com.Expedition67.ui.GameButton;
import com.Expedition67.ui.GameComponent;
import com.Expedition67.ui.GameText;

import java.awt.Color;
import java.awt.Graphics;

public class MenuState extends GameState {

    public MenuState() {
        super();
    }

    @Override
    protected void loadComponents() {
        // Game Title
        gameComponents.add(new GameText("EXPEDITION67", 0, 360, 150f, Color.white));

        // Start Game Button
        gameComponents.add(new GameButton("START GAME", 24f, 0, 470, 300, 50, () -> {
            GameManager.Instance().newGame();
            GameManager.Instance().getGameStateManager().setCurrentState(GameStateManager.COMBAT_STATE, CombatState.MONSTER_ROOM);
        }));

        // Credits Button
        gameComponents.add(new GameButton("CREDITS", 24f, 0, 540, 300, 50, () -> {
            GameManager.Instance().getGameStateManager().setCurrentState(GameStateManager.CREDITS_STATE, 0);
        }));

        // Exit Buttons
        gameComponents.add(new GameButton("EXIT GAME", 24f, 0, 610, 300, 50, () -> {
            System.exit(0);
        }));

        // Make all centered horizontally
        for (GameComponent gc : gameComponents) {
            gc.horizontallyCentering(0, GameView.GAME_WIDTH);
        }
    }

    @Override
    public void enter(int id) {
    }

    @Override
    public void exit() {
        // No cleanup needed
    }

    @Override
    public void render(Graphics g) {
        // Draw Background
        g.setColor(Color.black);
        g.fillRect(0, 0, GameView.GAME_WIDTH, GameView.GAME_HEIGHT);

        // Draw components
        super.render(g);


    }
}
