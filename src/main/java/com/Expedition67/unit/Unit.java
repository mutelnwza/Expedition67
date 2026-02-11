package com.Expedition67.unit;

import com.Expedition67.model.Animator;

public class Unit {
    private String name;
    private int x,y; //position in canvas
    private int width, height; // size
    private UnitStats unitStats;
    private UnitBrain unitBrain;
    private Animator animator;
    public int apCost;

    //each unit will be create once in a warehouse
    public Unit (String name, UnitStats unitStats, UnitBrain unitBrain, Animator animator, int x, int y, int w, int h){
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
    
    public void takeDamage(Unit src, float amount){
        unitBrain.takeDamage(src, amount);
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

    public void takeDamage(int damage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'takeDamage'");
    }

    public void heal(int heal) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'heal'");
    }

    public void useAp(int apCost2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'useAp'");
    }

    public void addShield(int shield) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addShield'");
    }
}
