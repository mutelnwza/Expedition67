package com.Expedition67.ui;

import com.Expedition67.core.GameView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class GameText implements GameComponent {

    private String text;
    private int x;
    private int y;
    private float size;
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
        this.size = size;
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
    public void horizontallyCentering(int x, int w) {
        // Create a dummy image to get a Graphics context
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();

        // Setup Font
        Font font = GameView.MAIN_FONT.deriveFont(size);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();

        this.x = x + (w - fm.stringWidth(text)) / 2;

        g2d.dispose();
    }

    @Override
    public void verticallyCentering(int y, int h) {
        // Create a dummy image to get a Graphics context
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();

        // Setup Font
        Font font = GameView.MAIN_FONT.deriveFont(size);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();

        this.y = y + ((h - fm.getHeight()) / 2) + fm.getAscent();

        g2d.dispose();
    }

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
