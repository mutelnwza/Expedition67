package com.Expedition67.unit.Enemy;

import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.core.graphics.GameView;
import com.Expedition67.ui.GameComponent;
import com.Expedition67.unit.*;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Enemy extends Unit implements GameComponent {

    private boolean isVisible;
    private boolean mouseOver;

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
    public void render(Graphics g) {
        if (!isVisible) return;

        if (mouseOver) {
            renderTarget(g);
        }
        super.render(g);
    }

    public void renderTarget(Graphics g) {
        int centerX = x + width / 2;
        int y = this.y - 40;
        int space = 60;

        g.setColor(Color.yellow);

        g.setFont(GameView.MAIN_FONT.deriveFont(30f));
        g.drawString("[", centerX - space - g.getFontMetrics().stringWidth("["), y);
        g.drawString("]", centerX + space, y);
    }

    @Override
    public boolean mouseClicked(MouseEvent e) {
        if (!isVisible || getUnitStats().getHp() <= 0) return false;

        if (isInside(e.getX(), e.getY())) {
            CombatManager.Instance().setTarget(this);
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
