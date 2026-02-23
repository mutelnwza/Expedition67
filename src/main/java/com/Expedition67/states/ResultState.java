package com.Expedition67.states;

import com.Expedition67.core.GameManager;
import com.Expedition67.core.GameStateManager;
import com.Expedition67.core.graphics.GameView;
import com.Expedition67.ui.GameButton;
import com.Expedition67.ui.GameText;

import java.awt.*;

/**
 * The game state for displaying the win or lose screen.
 */
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
        resultHeader = new GameText("Placeholder", 0, 470, 100f, Color.white);
        gameComponents.add(resultHeader);

        roomsClearedText = new GameText("Room Cleared: 0", 0, 520, 24f, Color.white);
        gameComponents.add(roomsClearedText);

        finalTimeText = new GameText("Time: 00:00", 0, 550, 24f, Color.white);
        gameComponents.add(finalTimeText);

        GameButton backButton = new GameButton("Back to Main Menu", 24f, 0, 610, 250, 50, () ->
                GameManager.Instance().getGameStateManager().setCurrentState(GameStateManager.MENU_STATE, 0)
        );
        backButton.horizontallyCentering(0, GameView.GAME_WIDTH);
        gameComponents.add(backButton);
    }

    @Override
    public void enter(int id) {
        GameManager.Instance().getGameTimer().stop();

        if (id == WIN) {
            resultHeader.setText("You Won!");
        } else {
            GameManager.Instance().getGameData().decrementRoom();
            resultHeader.setText("You Lost!");
        }
        resultHeader.horizontallyCentering(0, GameView.GAME_WIDTH);

        roomsClearedText.setText(String.format("Room Cleared: %d", GameManager.Instance().getRoom()));
        roomsClearedText.horizontallyCentering(0, GameView.GAME_WIDTH);
        finalTimeText.setText(String.format("Time: %s", GameManager.Instance().getTimeString()));
        finalTimeText.horizontallyCentering(0, GameView.GAME_WIDTH);
    }

    @Override
    public void exit() {
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, GameView.GAME_WIDTH, GameView.GAME_HEIGHT);

        int boxWidth = 400;
        int boxHeight = 200;
        int x = (GameView.GAME_WIDTH - boxWidth) / 2;
        int y = (GameView.GAME_HEIGHT - boxHeight) / 2;
        g.setColor(Color.WHITE);
        g.drawRect(x, y, boxWidth, boxHeight);

        super.render(g);
    }
}
