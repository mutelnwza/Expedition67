package com.Expedition67.ui;

import com.Expedition67.core.graphics.GameView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class GameText implements GameComponent {

    private String text;
    private int x;
    private int y;
    private final Font font;
    private final Color color;
    private boolean isVisible;
    private boolean isHorizontallyCentered = false;
    private int centeredX;
    private int centeredW;

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
        this.isVisible = true;
    }

    // --- Setters ---

    public void setText(String text) {
        this.text = text;
    }

    public void setX(int x) {
        this.x = x;
        isHorizontallyCentered = false;
    }

    public void setY(int y) {
        this.y = y;
    }

    // --- GameComponent Implementation ---

    @Override
    public void horizontallyCentering(int x, int w) {
        isHorizontallyCentered = true;
        this.centeredX = x;
        this.centeredW = w;
    }

    @Override
    public void verticallyCentering(int y, int h) {
        // Create a dummy image to get a Graphics context
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();

        // Setup Font
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();

        String[] lines = text.split("\n");
        int totalHeight = fm.getHeight() * lines.length;

        this.y = y + ((h - totalHeight) / 2) + fm.getAscent();

        g2d.dispose();
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
        if (!isVisible) return;

        g.setFont(font);
        g.setColor(color);

        String[] lines = text.split("\n");
        int lineHeight = g.getFontMetrics(font).getHeight();

        for (int i = 0; i < lines.length; i++) {
            int currentX = this.x;
            if (isHorizontallyCentered) {
                FontMetrics fm = g.getFontMetrics(font);
                currentX = centeredX + (centeredW - fm.stringWidth(lines[i])) / 2;
            }
            g.drawString(lines[i], currentX, y + (i * lineHeight));
        }
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

    @Override
    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
