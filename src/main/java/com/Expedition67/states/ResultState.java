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
        resultHeader = new GameText("", 320, 460, 100f, Color.white); //340,450
        gameComponents.add(resultHeader);

        // Stats
        roomsClearedText = new GameText("Room Cleared: 0", 420, 510, 24f, Color.white); //440,500
        gameComponents.add(roomsClearedText);

        finalTimeText = new GameText("Time: 00:00", 440, 540, 24f, Color.white); //460 , 530
        gameComponents.add(finalTimeText);

        // Back Button
        gameComponents.add(new GameButton("Back to Main Menu", 24f, 370, 610, 250, 50, () -> {
            GameManager.Instance().setCurrentState(GameManager.MENU_STATE, 0); //380, 620
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


        int boxWidth = 400;
        int boxHeight = 200;
        int x = (GameView.GAME_WIDTH - boxWidth) / 2;
        int y = (GameView.GAME_HEIGHT - boxHeight) / 2;
        g.setColor(Color.WHITE);
        g.drawRect(x, y, boxWidth, boxHeight);

        // Draw components
        super.render(g);
    }
}
