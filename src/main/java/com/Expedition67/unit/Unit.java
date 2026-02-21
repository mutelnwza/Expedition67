package com.Expedition67.unit;

import com.Expedition67.core.SpriteRenderer;
import com.Expedition67.model.Animator;
import com.Expedition67.ui.GameText;

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

    protected GameText nameText;
    protected GameText hpText;
    protected GameText apText;
    protected GameText damageText;

    protected int damageShowTimer;
    protected final int damageShowDeley = 60;
    protected boolean isTakeDamage;


    //for render damaged animation
    private int redFlashFrames = 0;
    private final int FLASH_DURATION = 30;

    //each unit will be create once in a warehouse
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
        initNameText();
        initHpText();
        initApText();
        initdamageText();
    }

    private void initNameText() {
        nameText = new GameText(name.toString(), 0, y - 30, 18f, Color.white);
        nameText.horizontallyCentering(x, width);
    }

    private void initHpText() {
        String hpStr = String.format("HP: %.2f/%.2f", unitStats.getHp(), unitStats.getMaxHp());
        hpText = new GameText(hpStr, 0, y - 10, 18f, Color.white);
        hpText.horizontallyCentering(x, width);
    }

    private void initApText() {
        if (getBrain() instanceof PlayerBrain pb) {
            String apStr = String.format("AP: %d", pb.getAP());
            apText = new GameText(apStr, 0, y + 30, 18f, Color.white);
            apText.horizontallyCentering(x, width);
        }
    }
    private void initdamageText(){
        damageText = new GameText("0", 0, y - 50, 18f, Color.red);
        damageText.setVisible(false);
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
        damageText.setText("-"+ String.valueOf(amount));
        damageText.horizontallyCentering(x, width);
        isTakeDamage = true;
        triggerRedFlash();
    }

    public void takeTrueDamage(float amount) {
        unitBrain.takeTrueDamage(amount);
        damageText.setText("-"+ String.valueOf(amount));
        damageText.horizontallyCentering(x, width);
        isTakeDamage = true;
        triggerRedFlash();
    }

    public void triggerRedFlash() {
        redFlashFrames = FLASH_DURATION;
    }

    // --- GameComponent Implementation ---
    public void update() {
        if (unitStats.getHp() <= 0) {
            return;
        }
        unitBrain.update();
        animator.update();

        if (redFlashFrames > 0) {
            redFlashFrames--;
        }

        hpText.setText(String.format("HP: %.2f/%.2f", unitStats.getHp(), unitStats.getMaxHp()));
        if (getBrain() instanceof PlayerBrain pb) {
            apText.setText(String.format("AP: %d", pb.getAP()));
        }
        if(isTakeDamage){
            damageText.setVisible(true);
            damageShowTimer ++;
            if(damageShowTimer >= damageShowDeley){
                damageShowTimer = 0;
                isTakeDamage = false;
                damageText.setVisible(false);

            }
        }
    }

    public void render(Graphics g) {
        spriteRenderer.unitRender((Graphics2D) g, this);
        nameText.render(g);
        hpText.render(g);
        if (apText != null) {
            apText.render(g);
        }
        damageText.render(g);
    }

    // --- Getter and Setter ---
    public UnitBrain getBrain() {
        return this.unitBrain;
    }

    public UnitType getType(){
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

    public int getFlashFrame(){
        return redFlashFrames;
    }
}
