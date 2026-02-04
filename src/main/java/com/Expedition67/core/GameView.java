package com.Expedition67.core;

import com.Expedition67.inputs.MouseInput;

import java.awt.*;
import javax.swing.JPanel;

public class GameView extends JPanel {
    private final GameManager gameManager;
    private final MouseInput mouseInput;

    public static final int ORIGINAL_TILE_SIZE = 16; // 16x16 tile
    public static final int SCALE = 5;
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // 80x80 tile
    public static final int MAX_GAME_COL = 12;
    public static final int MAX_GAME_ROW = 12;
    public static final int GAME_WIDTH = TILE_SIZE * MAX_GAME_COL; // 960 pixels
    public static final int GAME_HEIGHT = TILE_SIZE * MAX_GAME_ROW; // 960 pixels

    public GameView(GameManager gameManager) {
        this.gameManager = gameManager;

        mouseInput = new MouseInput(gameManager);
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);

        setPanelSize();
        setFocusable(true);
        requestFocusInWindow();
    }

    private void setPanelSize() {
        Dimension dimension = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(dimension);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameManager.render(g);
    }
}
