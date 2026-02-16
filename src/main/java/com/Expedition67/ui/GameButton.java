package com.Expedition67.ui;

import com.Expedition67.core.GameView;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class GameButton implements GameComponent {

    private String label;
    private float labelSize;
    private Rectangle bounds;

    // The visual text inside the button
    private GameText textComponent;

    // State
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
        this.labelSize = labelSize;
        this.bounds = new Rectangle(x, y, width, height);
        this.onClick = onClick;
    }

    /**
     * Changes the action the button performs when clicked.
     */
    public void setOnClick(Runnable onClick) {
        this.onClick = onClick;
    }

    /**
     * Calculates the exact center for the text.
     */
    private void initTextComponent(Graphics g) {
        g.setFont(GameView.MAIN_FONT.deriveFont(labelSize));
        FontMetrics fm = g.getFontMetrics();

        int stringWidth = fm.stringWidth(label);

        // Horizontal Center
        int x = bounds.x + (bounds.width - stringWidth) / 2;

        // Vertical Center
        int y = bounds.y + ((bounds.height - fm.getHeight()) / 2) + fm.getAscent();

        this.textComponent = new GameText(label, x, y, labelSize, Color.white);
    }

    // --- GameComponent Implementation ---

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
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

        // Draw Text (Initialize first if not)
        if (textComponent == null) {
            initTextComponent(g);
        }
        textComponent.render(g);
    }

    @Override
    public boolean isInside(int x, int y) {
        return bounds.contains(x, y);
    }

    @Override
    public boolean mouseClicked(MouseEvent e) {
        if (isInside(e.getX(), e.getY())) {
            onClick.run();
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseMoved(MouseEvent e) {
        this.mouseOver = isInside(e.getX(), e.getY());
        return mouseOver;
    }

    public void setText(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setText'");
    }
}
