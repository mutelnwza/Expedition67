package com.Expedition67.unit;

import com.Expedition67.model.Animator;

public class Unit {
    private final String name;
    private final int width,height; //position in canvas and size
    private int x,y;
    private final UnitStats unitStats;
    private final UnitBrain unitBrain;
    private final Animator animator;

    //each unit will be create once in a warehouse
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
    }
    
    //will be called from gamemanager or smth that hold all the unit references
    public void update(){
        unitBrain.update();
        animator.update();
    }

    //use when clone a unit
    public Unit copy(int x, int y){
        UnitStats stats = this.unitStats.copy();
        UnitBrain brain = this.unitBrain.copy();
        Unit clone = new Unit(this.name, stats, brain, x, y, this.width, this.height);
        clone.getAnimator().copy(this.animator);
        return clone;
    }

    public UnitBrain getBrain(){return  this.unitBrain;}
    
    public void takeDamage(float amount){
        unitBrain.takeDamage(amount);
    }

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
