package com.Expedition67.states;

import com.Expedition67.core.GameManager;
import com.Expedition67.core.GameStateManager;
import com.Expedition67.core.graphics.GameView;
import com.Expedition67.ui.GameButton;
import com.Expedition67.ui.GameComponent;
import com.Expedition67.ui.GameText;

import java.awt.*;

/**
 * The game state for the main menu.
 */
public class MenuState extends GameState {

    public MenuState() {
        super();
    }

    @Override
    protected void loadComponents() {
        gameComponents.add(new GameText("EXPEDITION67", 0, 360, 150f, Color.white));

        gameComponents.add(new GameButton("START GAME", 24f, 0, 470, 300, 50, () -> {
            GameManager.Instance().newGame();
            GameManager.Instance().getGameStateManager().setCurrentState(GameStateManager.COMBAT_STATE, CombatState.MONSTER_ROOM);
        }));

        gameComponents.add(new GameButton("CREDITS", 24f, 0, 540, 300, 50, () ->
                GameManager.Instance().getGameStateManager().setCurrentState(GameStateManager.CREDITS_STATE, 0)
        ));

        gameComponents.add(new GameButton("EXIT GAME", 24f, 0, 610, 300, 50, () ->
                System.exit(0)
        ));

        for (GameComponent gc : gameComponents) {
            gc.horizontallyCentering(0, GameView.GAME_WIDTH);
        }
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
        super.render(g);
    }
}
