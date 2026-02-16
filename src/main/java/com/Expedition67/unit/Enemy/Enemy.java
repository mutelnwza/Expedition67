package com.Expedition67.unit.Enemy;

import com.Expedition67.core.CombatManager;
import com.Expedition67.ui.GameComponent;
import com.Expedition67.unit.Unit;
import com.Expedition67.unit.UnitBrain;
import com.Expedition67.unit.UnitStats;
import com.Expedition67.unit.UnitType;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Enemy extends Unit implements GameComponent {
    private boolean mouseOver;

    public Enemy(String name, UnitStats unitStats, UnitBrain unitBrain,UnitType unitType, int x, int y, int w, int h) {
        super(name, unitStats, unitBrain, unitType, x, y, w, h);
    }

    @Override
    public Enemy copy(int x, int y) {
        UnitStats stats = this.getUnitStats().copy();
        UnitBrain brain = this.getBrain().copy();
        Enemy clone = new Enemy(this.getName(), stats, brain, unitType,x, y, this.getWidth(), this.getHeight());
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
        if (mouseOver) {
            renderTarget(g);
        }
        super.render(g);
    }

    public void renderTarget(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            Stroke oldStroke = g2.getStroke();

            g2.setColor(Color.YELLOW);
            g2.setStroke(new BasicStroke(3));

            g2.drawRect(getX(), getY(), getWidth(), getHeight());

            g2.setStroke(oldStroke);
    }

    @Override
    public boolean mouseClicked(MouseEvent e) {
        if (isInside(e.getX(), e.getY())) {
            CombatManager.Instance().setTarget(this);
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseMoved(MouseEvent e) {
        if (isInside(e.getX(), e.getY())) {
            mouseOver = true;
            return true;
        }
        mouseOver = false;
        return false;
    }
}
