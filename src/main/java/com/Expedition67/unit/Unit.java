package com.Expedition67.unit;

import com.Expedition67.core.SoundManager;
import com.Expedition67.core.graphics.SpriteRenderer;
import com.Expedition67.model.Animator;
import com.Expedition67.ui.UnitUIHandler;

import java.awt.*;

/**
 * Represents a single character or entity in the game, such as the player or an enemy.
 */
public class Unit {

    protected final UnitName name;
    protected final int width, height;
    protected int x, y;
    protected final UnitStats unitStats;
    protected final UnitBrain unitBrain;
    protected final Animator animator;
    protected final SpriteRenderer spriteRenderer;
    protected final UnitType unitType;
    protected final UnitUIHandler uiHandler;

    private int redFlashFrames = 0;

    /**
     * Constructs a new Unit.
     *
     * @param name      The name of the unit.
     * @param unitStats The stats of the unit.
     * @param unitBrain The brain (logic) of the unit.
     * @param unitType  The type of unit (e.g., PLAYER, ENEMY).
     * @param x         The initial x-coordinate.
     * @param y         The initial y-coordinate.
     * @param w         The width of the unit.
     * @param h         The height of the unit.
     */
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

    /**
     * Creates a copy of this unit at a new position.
     *
     * @param x The new x-coordinate.
     * @param y The new y-coordinate.
     * @return A new Unit instance with copied attributes.
     */
    public Unit copy(int x, int y) {
        UnitStats stats = this.unitStats.copy();
        UnitBrain brain = this.unitBrain.copy();
        Unit clone = new Unit(this.name, stats, brain, unitType, x, y, this.width, this.height);
        clone.getAnimator().copy(this.animator);
        return clone;
    }

    /**
     * Updates the unit's logic and animations.
     */
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

    /**
     * Renders the unit on the screen.
     *
     * @param g The Graphics object to draw with.
     */
    public void render(Graphics g) {
        spriteRenderer.unitRender((Graphics2D) g, this);
        uiHandler.render(g);
    }

    /**
     * Inflicts damage on the unit.
     *
     * @param amount The amount of damage to take.
     */
    public void takeDamage(float amount) {
        unitBrain.takeDamage(amount);
        SoundManager.Instance().playTakeDamageSound();
        triggerRedFlash();
    }

    /**
     * Triggers a red flash effect on the unit, typically after taking damage.
     */
    public void triggerRedFlash() {
        int FLASH_DURATION = 30;
        if (unitStats.getHp() <= 0) {
            redFlashFrames = FLASH_DURATION * 2;
        } else {
            redFlashFrames = FLASH_DURATION;
        }
    }

    /**
     * Displays a status indicator on the unit.
     *
     * @param amount     The value to display.
     * @param statusType The type of status (e.g., HEAL, DAMAGE).
     */
    public void showStatus(float amount, int statusType) {
        uiHandler.showStatus(amount, statusType);
    }

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

    public void setY(int y) {
        this.y = y;
        uiHandler.updatePosition();
    }
}
