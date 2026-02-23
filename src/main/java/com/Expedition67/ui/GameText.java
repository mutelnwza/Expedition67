package com.Expedition67.ui;

import com.Expedition67.core.graphics.GameView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * A UI component for drawing text on the screen.
 */
public class GameText implements GameComponent {

    private String text;
    private int x, y, width;
    private final Font font;
    private Color color;
    private boolean isVisible;
    private boolean isHorizontallyCentered = false;
    private int centeredX, centeredW;

    /**
     * Constructs a new GameText object.
     *
     * @param text  The text to be displayed.
     * @param x     The initial x-coordinate.
     * @param y     The initial y-coordinate.
     * @param size  The font size.
     * @param color The color of the text.
     */
    public GameText(String text, int x, int y, float size, Color color) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.font = GameView.MAIN_FONT.deriveFont(size);
        this.color = color;
        this.isVisible = true;
        this.width = 0;
    }

    @Override
    public void render(Graphics g) {
        if (!isVisible) return;

        g.setFont(font);
        g.setColor(color);

        String[] lines = text.split("\n");
        FontMetrics fm = g.getFontMetrics(font);
        int lineHeight = fm.getHeight();
        int newX = Integer.MAX_VALUE;
        int newWidth = 0;

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            int currentX = this.x;
            if (isHorizontallyCentered) {
                currentX = centeredX + (centeredW - fm.stringWidth(line)) / 2;
            }
            g.drawString(line, currentX, y + (i * lineHeight));
            newX = Math.min(newX, currentX);
            newWidth = Math.max(newWidth, fm.stringWidth(line));
        }
        this.x = newX;
        this.width = newWidth;
    }

    @Override
    public void horizontallyCentering(int x, int w) {
        this.isHorizontallyCentered = true;
        this.centeredX = x;
        this.centeredW = w;
    }

    @Override
    public void verticallyCentering(int y, int h) {
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();

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
        this.isVisible = visible;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setX(int x) {
        this.x = x;
        this.isHorizontallyCentered = false;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }
}
