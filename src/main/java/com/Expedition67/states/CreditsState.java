package com.Expedition67.states;

import com.Expedition67.core.GameManager;
import com.Expedition67.core.GameView;
import com.Expedition67.ui.GameButton;
import com.Expedition67.ui.GameText;
import java.awt.Font;


import java.awt.Color;
import java.awt.Graphics;

public class CreditsState extends GameState {
    public CreditsState() {
        super();
    }

    @Override
    protected void loadComponents() {
        // Title (Temp)
        gameComponents.add(new GameText("Made by", 340, 310, 100f, Color.white));
        gameComponents.add(new GameText("68050042       Khwannapat Boontub", 255, 365, 30f, Color.white));
        gameComponents.add(new GameText("68050075       Chayada Sriwisan", 255, 415, 30f, Color.white));
        gameComponents.add(new GameText("68050188       Taewich Boonrerm", 255, 465, 30f, Color.white));
        gameComponents.add(new GameText("68050349       Panit Phonpinit", 255, 515, 30f, Color.white));
        gameComponents.add(new GameText("68050306       Prangthip Utaivatcharanan", 255, 565, 30f, Color.white));
        gameComponents.add(new GameText("Special Thanks",400, 635, 30f, Color.white));
        gameComponents.add(new GameText("Gemini - Nano Banana Pro(Art)", 300, 685, 30f, Color.white));

        // Back Button
        gameComponents.add(new GameButton("Back to Main Menu", 24f, 360, 750, 250, 50, () -> {
            GameManager.Instance().setCurrentState(GameManager.MENU_STATE, 0);
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

        //Draw Bok
        int boxWidth = 600;
        int boxHeight = 480;
        int x = (GameView.GAME_WIDTH - boxWidth) / 2;
        int y = (GameView.GAME_HEIGHT - boxHeight) / 2;
        g.setColor(Color.WHITE);
        g.drawRect(x, y, boxWidth, boxHeight);

        // Draw components
        super.render(g);
    }
}
