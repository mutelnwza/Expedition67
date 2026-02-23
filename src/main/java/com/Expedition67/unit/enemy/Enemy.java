package com.Expedition67.unit.enemy;

import com.Expedition67.core.SoundManager;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.core.graphics.GameView;
import com.Expedition67.ui.GameComponent;
import com.Expedition67.unit.*;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Represents an enemy unit in the game.
 */
public class Enemy extends Unit implements GameComponent {

    private boolean isVisible;
    private boolean mouseOver;

    /**
     * Constructs a new Enemy.
     *
     * @param name      The name of the enemy.
     * @param unitStats The stats of the enemy.
     * @param unitBrain The brain (logic) of the enemy.
     * @param unitType  The type of unit.
     * @param x         The initial x-coordinate.
     * @param y         The initial y-coordinate.
     * @param w         The width of the enemy.
     * @param h         The height of the enemy.
     */
    public Enemy(UnitName name, UnitStats unitStats, UnitBrain unitBrain, UnitType unitType, int x, int y, int w, int h) {
        super(name, unitStats, unitBrain, unitType, x, y, w, h);
        this.isVisible = true;
    }

    @Override
    public Enemy copy(int x, int y) {
        UnitStats stats = this.getUnitStats().copy();
        UnitBrain brain = this.getBrain().copy();
        Enemy clone = new Enemy(this.getName(), stats, brain, unitType, x, y, this.getWidth(), this.getHeight());
        clone.getAnimator().copy(this.getAnimator());
        return clone;
    }

    @Override
    public void render(Graphics g) {
        if (!isVisible) return;

        if (mouseOver) {
            renderTargetIndicator(g);
        }
        super.render(g);
    }

    @Override
    public boolean mouseClicked(MouseEvent e) {
        if (!isVisible || getUnitStats().getHp() <= 0) return false;

        if (isInside(e.getX(), e.getY())) {
            CombatManager.Instance().setTarget(this);
            SoundManager.Instance().playSelectSound();
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
    public boolean isInside(int x, int y) {
        return x >= getX() && x <= getX() + getWidth() && y >= getY() && y <= getY() + getHeight();
    }

    @Override
    public void horizontallyCentering(int x, int w) {
        super.x = x + (w - width) / 2;
    }

    @Override
    public void verticallyCentering(int y, int h) {
        this.y = y + (h - height) / 2;
    }

    @Override
    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    /**
     * Renders a target indicator above the enemy.
     *
     * @param g The Graphics object to draw on.
     */
    public void renderTargetIndicator(Graphics g) {
        int centerX = x + width / 2;
        int y = this.y - 70;
        int space = 60;

        g.setColor(Color.yellow);
        g.setFont(GameView.MAIN_FONT.deriveFont(25f));
        g.drawString("[", centerX - space - g.getFontMetrics().stringWidth("["), y);
        g.drawString("]", centerX + space, y);
    }
}
