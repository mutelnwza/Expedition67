package com.Expedition67.states;

import com.Expedition67.core.GameManager;
import com.Expedition67.core.GameStateManager;
import com.Expedition67.core.graphics.GameView;
import com.Expedition67.ui.GameButton;
import com.Expedition67.ui.GameText;

import java.awt.*;

/**
 * The game state for displaying the credits screen.
 */
public class CreditsState extends GameState {

    public CreditsState() {
        super();
    }

    @Override
    protected void loadComponents() {
        GameText title = new GameText("Made by", 0, 310, 100f, Color.white);
        title.horizontallyCentering(0, GameView.GAME_WIDTH);
        gameComponents.add(title);

        gameComponents.add(new GameText("68050042       Khwannapat Boontub       ", 255, 385, 30f, Color.white));
        gameComponents.add(new GameText("68050075       Chayada Sriwisan         ", 255, 435, 30f, Color.white));
        gameComponents.add(new GameText("68050188       Taewich Boonrerm         ", 255, 485, 30f, Color.white));
        gameComponents.add(new GameText("68050349       Panit Phonpinit          ", 255, 535, 30f, Color.white));
        gameComponents.add(new GameText("68050306       Prangthip Utaivatcharanan", 255, 585, 30f, Color.white));

        GameText specialThanks = new GameText("Special Thanks", 0, 655, 30f, Color.white);
        specialThanks.horizontallyCentering(0, GameView.GAME_WIDTH);
        gameComponents.add(specialThanks);

        GameText artist = new GameText("Gemini - Nano Banana Pro (Art)", 0, 705, 30f, Color.white);
        artist.horizontallyCentering(0, GameView.GAME_WIDTH);
        gameComponents.add(artist);

        GameButton backButton = new GameButton("Back to Main Menu", 24f, 0, 800, 250, 50, () ->
                GameManager.Instance().getGameStateManager().setCurrentState(GameStateManager.MENU_STATE, 0)
        );
        backButton.horizontallyCentering(0, GameView.GAME_WIDTH);
        gameComponents.add(backButton);
    }

    @Override
    public void enter(int id) {
    }

    @Override
    public void exit() {
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, GameView.GAME_WIDTH, GameView.GAME_HEIGHT);

        int boxWidth = 600;
        int boxHeight = 550;
        int x = (GameView.GAME_WIDTH - boxWidth) / 2;
        int y = (GameView.GAME_HEIGHT - boxHeight) / 2;
        g.setColor(Color.WHITE);
        g.drawRect(x, y, boxWidth, boxHeight);

        super.render(g);
    }
}
