package com.Expedition67.ui;

import com.Expedition67.core.GameView;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class GameText implements GameComponent {

    private String text;
    private int x;
    private int y;
    private final Font font;
    private final Color color;

    /**
     * Constructor: Creates a text label
     *
     * @param text  The string to display.
     * @param x     X position.
     * @param y     Y position.
     * @param size  The font size.
     * @param color The text color.
     */
    public GameText(String text, int x, int y, float size, Color color) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.font = GameView.MAIN_FONT.deriveFont(size);
        this.color = color;
    }

    // --- Setters ---

    public void setText(String text) {
        this.text = text;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    // --- GameComponent Implementation ---

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
        g.setFont(font);
        g.setColor(color);
        g.drawString(text, x, y);
    }

    @Override
    public boolean isInside(int x, int y) {
        return false;
    }

    @Override
    public boolean mouseClicked(MouseEvent e) {
        return false;
    }

    @Override
    public boolean mouseMoved(MouseEvent e) {
        return false;
    }
}
