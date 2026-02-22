package com.Expedition67.states;

import com.Expedition67.core.GameManager;
import com.Expedition67.core.GameStateManager;
import com.Expedition67.core.graphics.GameView;
import com.Expedition67.ui.GameButton;
import com.Expedition67.ui.GameText;

import java.awt.*;

public class CreditsState extends GameState {
    public CreditsState() {
        super();
    }

    @Override
    protected void loadComponents() {
        // Title
        gameComponents.add(new GameText("Made by", 0, 310, 100f, Color.white));
        gameComponents.getLast().horizontallyCentering(0, GameView.GAME_WIDTH);

        // Name
        gameComponents.add(new GameText("68050042       Khwannapat Boontub       ", 255, 385, 30f, Color.white));
        gameComponents.add(new GameText("68050075       Chayada Sriwisan         ", 255, 435, 30f, Color.white));
        gameComponents.add(new GameText("68050188       Taewich Boonrerm         ", 255, 485, 30f, Color.white));
        gameComponents.add(new GameText("68050349       Panit Phonpinit          ", 255, 535, 30f, Color.white));
        gameComponents.add(new GameText("68050306       Prangthip Utaivatcharanan", 255, 585, 30f, Color.white));

        // Special Thanks
        gameComponents.add(new GameText("Special Thanks", 0, 655, 30f, Color.white));
        gameComponents.getLast().horizontallyCentering(0, GameView.GAME_WIDTH);
        gameComponents.add(new GameText("Gemini - Nano Banana Pro (Art)", 0, 705, 30f, Color.white));
        gameComponents.getLast().horizontallyCentering(0, GameView.GAME_WIDTH);

        // Back Button
        gameComponents.add(new GameButton("Back to Main Menu", 24f, 0, 800, 250, 50, () ->
                GameManager.Instance().getGameStateManager().setCurrentState(GameStateManager.MENU_STATE, 0)
        ));
        gameComponents.getLast().horizontallyCentering(0, GameView.GAME_WIDTH);
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

        //Draw Box
        int boxWidth = 600;
        int boxHeight = 550;
        int x = (GameView.GAME_WIDTH - boxWidth) / 2;
        int y = (GameView.GAME_HEIGHT - boxHeight) / 2;
        g.setColor(Color.WHITE);
        g.drawRect(x, y, boxWidth, boxHeight);

        // Draw components
        super.render(g);
    }
}
