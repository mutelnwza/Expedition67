package com.Expedition67.core.graphics;

import com.Expedition67.core.GameManager;
import com.Expedition67.inputs.MouseInput;
import com.Expedition67.storage.AssetManager;

import javax.swing.*;
import java.awt.*;

/**
 * The main canvas where the entire game is drawn.
 */
public class GameView extends JPanel {

    public static final int GAME_WIDTH = 960;
    public static final int GAME_HEIGHT = 960;
    public static final Font MAIN_FONT = AssetManager.Instance().getGameFont();

    /**
     * Constructs a new GameView, setting up its properties and input listeners.
     */
    public GameView() {
        MouseInput mouseInput = new MouseInput();
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);

        setPanelSize();
        setFocusable(true);
        requestFocusInWindow();
    }

    /**
     * Overrides the default paintComponent to render the game via the GameManager.
     *
     * @param g The Graphics object to draw with.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        GameManager.Instance().render(g);
    }

    private void setPanelSize() {
        Dimension dimension = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(dimension);
    }
}
