package com.Expedition67.ui;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * A clickable UI button component.
 */
public class GameButton implements GameComponent {

    private final int width, height;
    private final Rectangle bounds;
    private final GameText textComponent;
    private int x, y;
    private boolean isVisible, mouseOver;
    private Runnable onClick;

    /**
     * Constructs a new GameButton.
     *
     * @param label     The text to display on the button.
     * @param labelSize The font size for the label.
     * @param x         The x-coordinate of the button.
     * @param y         The y-coordinate of the button.
     * @param width     The width of the button.
     * @param height    The height of the button.
     * @param onClick   The action to perform when the button is clicked.
     */
    public GameButton(String label, float labelSize, int x, int y, int width, int height, Runnable onClick) {
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

    @Override
    public void render(Graphics g) {
        if (!isVisible) return;

        g.setColor(mouseOver ? Color.lightGray : Color.gray);
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        g.setColor(Color.white);
        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
        textComponent.render(g);
    }

    @Override
    public boolean mouseClicked(MouseEvent e) {
        if (isInside(e.getX(), e.getY())) {
            if (onClick != null) {
                onClick.run();
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseMoved(MouseEvent e) {
        if (!isVisible) return false;
        boolean isNowOver = isInside(e.getX(), e.getY());
        if (isNowOver != this.mouseOver) {
            this.mouseOver = isNowOver;
            return true;
        }
        return false;
    }

    @Override
    public void horizontallyCentering(int x, int w) {
        this.x = x + (w - this.width) / 2;
        this.bounds.setLocation(this.x, this.y);
        this.textComponent.horizontallyCentering(this.x, this.width);
    }

    @Override
    public void verticallyCentering(int y, int h) {
        this.y = y + (h - this.height) / 2;
        this.bounds.setLocation(this.x, this.y);
        this.textComponent.verticallyCentering(this.y, this.height);
    }

    @Override
    public boolean isInside(int x, int y) {
        return isVisible && bounds.contains(x, y);
    }

    @Override
    public void update() {
    }

    @Override
    public void setVisible(boolean visible) {
        this.isVisible = visible;
        if (!visible) {
            this.mouseOver = false;
        }
    }

    public void setOnClick(Runnable onClick) {
        this.onClick = onClick;
    }

    public void setLabel(String label) {
        this.textComponent.setText(label);
        this.textComponent.horizontallyCentering(x, width);
        this.textComponent.verticallyCentering(y, height);
    }
}
