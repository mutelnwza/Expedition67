package com.Expedition67.ui;

import java.awt.*;

public class GameButton {
    private final int id;
    private String label;
    private Rectangle bounds;
    private boolean mouseOver;

    public GameButton(int id, String label, int x, int y, int width, int height) {
        this.id = id;
        this.label = label;
        this.bounds = new Rectangle(x, y, width, height);
    }

    public void render(Graphics g) {
        if (mouseOver) {
            g.setColor(Color.lightGray);
        } else {
            g.setColor(Color.gray);
        }

        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);

        g.setColor(Color.white);
        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);

        drawCenteredString(g);
    }

    private void drawCenteredString(Graphics g) {
        int stringWidth = g.getFontMetrics().stringWidth(label);
        int stringHeight = g.getFontMetrics().getAscent();

        int x = bounds.x + (bounds.width - stringWidth) / 2;
        int y = bounds.y + (bounds.height - stringHeight) / 2 + stringHeight;

        g.drawString(label, x, y);
    }

    public boolean isInside(int x, int y) {
        return bounds.contains(x, y);
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public int getId() {
        return id;
    }
}
