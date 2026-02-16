package com.Expedition67.states;

import com.Expedition67.core.GameManager;
import com.Expedition67.core.GameView;
import com.Expedition67.ui.GameButton;
import com.Expedition67.ui.GameText;

import java.awt.Color;
import java.awt.Graphics;

public class MenuState extends GameState {

    private GameText Title;

    public MenuState() {
        super();
    }

    @Override
    protected void loadComponents() {
        // Button Dimensions and Layout
        int w = 300;
        int h = 50;
        int x = (GameView.GAME_WIDTH / 2) - (w / 2); // Center horizontally

        // Start Game Button
        gameComponents.add(new GameButton("START GAME", 24f, x, 400, w, h, () -> {
            GameManager.Instance().setTimeCounter(true);
            GameManager.Instance().setCurrentState(GameManager.COMBAT_STATE, CombatState.MONSTER_ROOM);
        }));

        // Credits Button
        gameComponents.add(new GameButton("CREDITS", 24f, x, 470, w, h, () -> {
            GameManager.Instance().setCurrentState(GameManager.CREDITS_STATE, 0);
        }));

        // Exit Buttons
        gameComponents.add(new GameButton("EXIT GAME", 24f, x, 540, w, h, () -> {
            System.exit(0);
        }));

        Title = new GameText("EXPEDITION67", 150, 290, 150f, Color.white);
        gameComponents.add(Title);
    }

    @Override
    public void enter(int id) {
        // Reset game stats whenever we return to the menu
        GameManager.Instance().newGame();
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
