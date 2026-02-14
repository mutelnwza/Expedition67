package com.Expedition67.states;

import com.Expedition67.core.GameManager;
import com.Expedition67.core.GameView;
import com.Expedition67.ui.GameButton;
import com.Expedition67.ui.GameText;

import java.awt.Color;
import java.awt.Graphics;

public class ResultState extends GameState {

    public static final int WIN = 0;
    public static final int LOSE = 1;

    private GameText resultHeader;
    private GameText roomsClearedText;
    private GameText finalTimeText;

    public ResultState() {
        super();
    }

    @Override
    protected void loadComponents() {
        // Result Header (Win/Lose)
        resultHeader = new GameText("", 340, 450, 100f, Color.white);
        gameComponents.add(resultHeader);

        // Stats
        roomsClearedText = new GameText("Room Cleared: 0", 440, 500, 24f, Color.white);
        gameComponents.add(roomsClearedText);

        finalTimeText = new GameText("Time: 00:00", 460, 530, 24f, Color.white);
        gameComponents.add(finalTimeText);

        // Back Button
        gameComponents.add(new GameButton("Back to Main Menu", 24f, 380, 600, 250, 50, () -> {
            GameManager.Instance().setCurrentState(GameManager.MENU_STATE, 0);
        }));
    }

    @Override
    public void enter(int id) {
        // Stop the timer
        GameManager.Instance().setTimeCounter(false);

        // Set Header Text
        if (id == WIN) {
            resultHeader.setText("You Won!");
        } else {
            resultHeader.setText("You Lost!");
        }

        // Set Stats
        roomsClearedText.setText(String.format("Room Cleared: %d", GameManager.Instance().getRoom()));
        finalTimeText.setText(String.format("Time: %s", GameManager.Instance().getTimeString()));
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
