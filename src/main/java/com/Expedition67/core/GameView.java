package com.Expedition67.core;

import com.Expedition67.inputs.MouseInput;
import com.Expedition67.storage.AssetManager;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GameView extends JPanel {

    // --- Game Config Constants ---
    public static final int ORIGINAL_TILE_SIZE = 16;
    public static final int SCALE = 5;
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // 80x80 pixels

    public static final int MAX_GAME_COL = 12;
    public static final int MAX_GAME_ROW = 12;

    // Screen Dimension 960x960
    public static final int GAME_WIDTH = TILE_SIZE * MAX_GAME_COL;
    public static final int GAME_HEIGHT = TILE_SIZE * MAX_GAME_ROW;

    // Global Font
    public static final Font MAIN_FONT = AssetManager.Instance().getGameFont();

    // --- Fields ---
    private final GameManager gameManager;
    private final MouseInput mouseInput;

    /**
     * Constructor: Sets up the panel, input listeners, and dimensions.
     */
    public GameView(GameManager gameManager) {
        this.gameManager = gameManager;

        // Initialize Input
        mouseInput = new MouseInput(gameManager);
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);

        // Panel Settings
        setPanelSize();
        setFocusable(true);
        requestFocusInWindow();
    }

    /**
     * Sets the preferred size of the game panel based on the constants.
     */
    private void setPanelSize() {
        Dimension dimension = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(dimension);
    }

    /**
     * The built-in Swing painting method. Divert to GameManager.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameManager.render(g);
    }
}
