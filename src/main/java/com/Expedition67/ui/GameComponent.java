package com.Expedition67.ui;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Defines the basic structure and behavior for all UI components in the game.
 */
public interface GameComponent {

    /**
     * Centers the component horizontally within a given area.
     *
     * @param x The x-coordinate of the container.
     * @param w The width of the container.
     */
    void horizontallyCentering(int x, int w);

    /**
     * Centers the component vertically within a given area.
     *
     * @param y The y-coordinate of the container.
     * @param h The height of the container.
     */
    void verticallyCentering(int y, int h);

    /**
     * Updates the component's state, called every frame.
     */
    void update();

    /**
     * Renders the component on the screen.
     *
     * @param g The Graphics object used for drawing.
     */
    void render(Graphics g);

    /**
     * Checks if a given point is within the component's bounds.
     *
     * @param x The x-coordinate to check.
     * @param y The y-coordinate to check.
     * @return true if the point is inside, false otherwise.
     */
    boolean isInside(int x, int y);

    /**
     * Handles mouse click events for the component.
     *
     * @param e The MouseEvent.
     * @return true if the event was handled, false otherwise.
     */
    boolean mouseClicked(MouseEvent e);

    /**
     * Handles mouse movement events for the component.
     *
     * @param e The MouseEvent.
     * @return true if the component's hover state changed, false otherwise.
     */
    boolean mouseMoved(MouseEvent e);

    /**
     * Sets the visibility of the component.
     *
     * @param visible True to show the component, false to hide it.
     */
    void setVisible(boolean visible);
}
