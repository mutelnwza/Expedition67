package com.Expedition67.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class GameButton implements GameComponent {

    private String label;
    private int x;
    private int y;
    private int width;
    private int height;
    private Rectangle bounds;

    // The visual text inside the button
    private GameText textComponent;

    // State
    private boolean isVisible;
    private boolean mouseOver;
    private Runnable onClick;

    /**
     * Constructor: Creates a clickable button.
     *
     * @param label     The text on the button.
     * @param labelSize The font size.
     * @param x         X position.
     * @param y         Y position.
     * @param width     Width.
     * @param height    Height.
     * @param onClick   The method to run when clicked.
     */
    public GameButton(String label, float labelSize, int x, int y, int width, int height, Runnable onClick) {
        this.label = label;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bounds = new Rectangle(x, y, width, height);
        this.onClick = onClick;
        this.isVisible = true;

        this.textComponent = new GameText(label, 0, 0, labelSize, Color.white);
        this.textComponent.horizontallyCentering(x, width);
        this.textComponent.verticallyCentering(y, height);
    }

    /**
     * Changes the action the button performs when clicked.
     */
    public void setOnClick(Runnable onClick) {
        this.onClick = onClick;
    }

    public void setLabel(String label) {
        this.label = label;
        this.textComponent.setText(label);
        this.textComponent.horizontallyCentering(x, width);
        this.textComponent.verticallyCentering(y, height);
    }

    // --- GameComponent Implementation ---

    @Override
    public void horizontallyCentering(int x, int w) {
        this.x = x + (w - width) / 2;
        bounds.setLocation(this.x, y);
        textComponent.horizontallyCentering(this.x, width);
    }

    @Override
    public void verticallyCentering(int y, int h) {
        this.y = y + (h - height) / 2;
        bounds.setLocation(x, this.y);
        textComponent.verticallyCentering(this.y, height);
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
        if (!isVisible) return;

        // Draw Background
        if (mouseOver) {
            g.setColor(Color.lightGray); // Hover
        } else {
            g.setColor(Color.gray); // Normal
        }
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);

        // Draw Border
        g.setColor(Color.white);
        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);

        // Draw label
        textComponent.render(g);
    }

    @Override
    public boolean isInside(int x, int y) {
        return bounds.contains(x, y);
    }

    @Override
    public boolean mouseClicked(MouseEvent e) {
        if (!isVisible) return false;

        if (isInside(e.getX(), e.getY())) {
            onClick.run();
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseMoved(MouseEvent e) {
        if (!isVisible) return false;

        this.mouseOver = isInside(e.getX(), e.getY());
        return mouseOver;
    }

    @Override
    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
