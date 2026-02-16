package com.Expedition67.unit;

import com.Expedition67.core.SpriteRenderer;
import com.Expedition67.model.Animator;
import com.Expedition67.ui.GameText;

import java.awt.*;

public class Unit {
    protected final String name;
    protected final int width,height; //position in canvas and size
    protected int x,y;
    protected final UnitStats unitStats;
    protected final UnitBrain unitBrain;
    protected final Animator animator;
    protected final SpriteRenderer spriteRenderer;

    protected GameText hpText;
    protected GameText apText;

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
        hpText = new GameText(hpStr, 0, y - 10, 18f, Color.WHITE);
        hpText.horizontallyCentering(x, width);
    }

    private void initApText() {
        if (getBrain() instanceof PlayerBrain pb) {
            String apStr = String.format("AP: %d", pb.getAP());
            apText = new GameText(apStr, 0, y + 130, 18f, Color.WHITE);
            apText.horizontallyCentering(x, width);
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
