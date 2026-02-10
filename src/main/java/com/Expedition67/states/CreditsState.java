package com.Expedition67.states;

import com.Expedition67.core.GameManager;
import com.Expedition67.core.GameView;
import com.Expedition67.ui.GameButton;
import com.Expedition67.ui.GameText;

import java.awt.Color;
import java.awt.Graphics;

public class CreditsState extends GameState {
    public CreditsState(GameManager gameManager) {
        super(gameManager);
    }

    @Override
    protected void loadComponents() {
        // Title (Temp)
        gameComponents.add(new GameText("Credits", 380, 500, 100f, Color.white));

        // Back Button
        gameComponents.add(new GameButton("Back to Main Menu", 24f, 380, 600, 250, 50, () -> {
            gameManager.setCurrentState(GameManager.MENU_STATE, 0);
        }));
    }

    @Override
    public void enter(int id) {
    }

    @Override
    public void exit() {
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
