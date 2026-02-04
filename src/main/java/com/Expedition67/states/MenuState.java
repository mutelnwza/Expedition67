package com.Expedition67.states;

import com.Expedition67.core.GameManager;
import com.Expedition67.core.GameView;
import com.Expedition67.ui.GameButton;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MenuState extends GameState {
    private List<GameButton> gameButtons;

    public MenuState(GameManager gameManager) {
        super(gameManager);
        gameButtons = new ArrayList<>();
        loadButtons();
    }

    private void loadButtons() {
        int w = 300;
        int h = 50;
        int x = (GameView.GAME_WIDTH / 2) - (w / 2);

        gameButtons.add(new GameButton(1, "START GAME", x, 400, w, h));
        gameButtons.add(new GameButton(2, "EXIT GAME", x, 470, w, h));
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, GameView.GAME_WIDTH, GameView.GAME_HEIGHT);

        for (GameButton gameButton : gameButtons) {
            gameButton.render(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (GameButton gameButton : gameButtons) {
            if (gameButton.isInside(e.getX(), e.getY())) {
                applyAction(gameButton.getId());
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (GameButton gameButton : gameButtons) {
            gameButton.setMouseOver(gameButton.isInside(e.getX(), e.getY()));
        }
    }

    private void applyAction(int id) {
        if (id == 1) {

        } else if (id == 2) {
            System.exit(0);
        }
    }
}
