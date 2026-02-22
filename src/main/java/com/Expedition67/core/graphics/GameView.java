package com.Expedition67.core.graphics;

import com.Expedition67.core.GameManager;
import com.Expedition67.inputs.MouseInput;
import com.Expedition67.storage.AssetManager;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {

    public static final int GAME_WIDTH = 960;
    public static final int GAME_HEIGHT = 960;

    // Global Font
    public static final Font MAIN_FONT = AssetManager.Instance().getGameFont();

    /**
     * Constructor: Sets up the panel, input listeners, and dimensions.
     */
    public GameView() {
        // Initialize Input
        MouseInput mouseInput = new MouseInput();
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
        GameManager.Instance().render(g);
    }
}
