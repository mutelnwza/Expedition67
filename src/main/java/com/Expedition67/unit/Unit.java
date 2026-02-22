package com.Expedition67.unit;

import com.Expedition67.core.graphics.SpriteRenderer;
import com.Expedition67.model.Animator;
import com.Expedition67.ui.UnitUIHandler;

import java.awt.*;

public class Unit {

    protected final UnitName name;
    protected final int width, height; //position in canvas and size
    protected int x, y;
    protected final UnitStats unitStats;
    protected final UnitBrain unitBrain;
    protected final Animator animator;
    protected final SpriteRenderer spriteRenderer;
    protected final UnitType unitType;
    protected final UnitUIHandler uiHandler;

    //for render damaged animation
    private int redFlashFrames = 0;

    //each unit will be created once in a warehouse
    public Unit(UnitName name, UnitStats unitStats, UnitBrain unitBrain, UnitType unitType, int x, int y, int w, int h) {
        this.name = name;
        this.unitBrain = unitBrain;
        this.unitStats = unitStats;
        this.unitType = unitType;
        this.animator = new Animator();
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.unitBrain.setOwner(this);
        this.spriteRenderer = new SpriteRenderer();
        this.uiHandler = new UnitUIHandler(this);
    }

    //use when clone a unit
    public Unit copy(int x, int y) {
        UnitStats stats = this.unitStats.copy();
        UnitBrain brain = this.unitBrain.copy();
        Unit clone = new Unit(this.name, stats, brain, unitType, x, y, this.width, this.height);
        clone.getAnimator().copy(this.animator);
        return clone;
    }

    //handle take damage
    public void takeDamage(float amount) {
        unitBrain.takeDamage(amount);
        triggerRedFlash();
    }

//    public void takeTrueDamage(float amount) {
//        unitBrain.takeTrueDamage(amount);
//        uiHandler.showStatus(amount);
//        triggerRedFlash();
//    }

    public void triggerRedFlash() {
        int FLASH_DURATION = 30;
        if (unitStats.getHp() <= 0)
            redFlashFrames = FLASH_DURATION * 2;
        else
            redFlashFrames = FLASH_DURATION;
    }

    public void showStatus(float amount, int statusType) {
        uiHandler.showStatus(amount, statusType);
    }

    // --- GameComponent Implementation ---
    public void update() {
        if (unitStats.getHp() <= 0 && redFlashFrames <= 0) {
            return;
        }
        unitBrain.update();
        animator.update();
        uiHandler.update();

        if (redFlashFrames > 0) {
            redFlashFrames--;
        }
    }

    public void render(Graphics g) {
        spriteRenderer.unitRender((Graphics2D) g, this);
        uiHandler.render(g);
    }

    // --- Getters and Setters ---
    public UnitBrain getBrain() {
        return this.unitBrain;
    }

    public UnitType getType() {
        return this.unitType;
    }

    public UnitStats getUnitStats() {
        return this.unitStats;
    }

    public Animator getAnimator() {
        return animator;
    }

    public UnitName getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getFlashFrame() {
        return redFlashFrames;
    }

    public void setX(int x) {
        this.x = x;
        uiHandler.updatePosition();
    }
}
