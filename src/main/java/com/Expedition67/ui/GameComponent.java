package com.Expedition67.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public interface GameComponent {

    /**
     * Centering component horizontally in bound.
     * @param x X of bound.
     * @param w Width of bound.
     */
    void horizontallyCentering(int x, int w);

    /**
     * Centering component vertically in bound.
     * @param y Y of bound.
     * @param h Height of bound.
     */
    void verticallyCentering(int y, int h);

    /**
     * Updates the logic of the component.
     */
    void update();

    /**
     * Draws the component to the screen.
     */
    void render(Graphics g);

    /**
     * Checks if a coordinate is within the component's bounds.
     */
    boolean isInside(int x, int y);

    /**
     * Handles mouse click events
     *
     * @return true if the event was consumed.
     */
    boolean mouseClicked(MouseEvent e);

    /**
     * Handles mouse movement events (for hover effects).
     *
     * @return true if the event was consumed.
     */
    boolean mouseMoved(MouseEvent e);

    void setVisible(boolean visible);
}
