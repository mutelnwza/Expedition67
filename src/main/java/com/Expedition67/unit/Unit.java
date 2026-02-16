package com.Expedition67.unit;

import com.Expedition67.core.SpriteRenderer;
import com.Expedition67.model.Animator;
import com.Expedition67.ui.GameText;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Unit {
    private final String name;
    private final int width,height; //position in canvas and size
    private int x,y;
    private final UnitStats unitStats;
    private final UnitBrain unitBrain;
    private final Animator animator;
    private final SpriteRenderer spriteRenderer;

    private GameText hpText;
    private GameText apText;

    //each unit will be created once in a warehouse
    public Unit (String name, UnitStats unitStats, UnitBrain unitBrain, int x, int y, int w, int h){
        this.name = name;
        this.unitBrain = unitBrain;
        this.unitStats = unitStats;
        this.animator = new Animator();
        this.x=x;
        this.y=y;
        this.width=w;
        this.height=h;
        this.unitBrain.setOwner(this);
        this.spriteRenderer = new SpriteRenderer();

        initHpText();
        initApText();
    }

    private void initHpText() {
        String hpStr = String.format("HP: %.2f/%.2f", unitStats.getHp(), unitStats.getMaxHp());
        int hpX = x;
        int hpY = y - 10;
        hpText = new GameText(hpStr, hpX, hpY, 18f, Color.WHITE);
    }

    private void initApText() {
        if (getBrain() instanceof PlayerBrain pb) {
            String apStr = String.format("AP: %d", pb.getAP());
            int apX = x;
            int apY = y + 150;
            apText = new GameText(apStr, apX, apY, 18f, Color.WHITE);
        }
    }

    //use when clone a unit
    public Unit copy(int x, int y){
        UnitStats stats = this.unitStats.copy();
        UnitBrain brain = this.unitBrain.copy();
        Unit clone = new Unit(this.name, stats, brain, x, y, this.width, this.height);
        clone.getAnimator().copy(this.animator);
        return clone;
    }

    public void takeDamage(float amount){
        unitBrain.takeDamage(amount);
    }

    // --- GameComponent Implementation ---

    public void update() {
        unitBrain.update();
        animator.update();

        hpText.setText(String.format("HP: %.2f/%.2f", unitStats.getHp(), unitStats.getMaxHp()));
        if (getBrain() instanceof PlayerBrain pb) {
            apText.setText(String.format("AP: %d", pb.getAP()));
        }
    }

    public void render(Graphics g) {
        spriteRenderer.unitRender((Graphics2D) g, this);
        hpText.render(g);
        if (apText != null) {
            apText.render(g);
        }
    }

    // --- Getter and Setter ---

    public UnitBrain getBrain(){return  this.unitBrain;}

    public UnitStats getUnitStats(){
        return this.unitStats;
    }
    
    public Animator getAnimator() {
        return animator;
    }

    public String getName() {
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
}
