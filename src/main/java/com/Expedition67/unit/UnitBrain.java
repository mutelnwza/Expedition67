package com.Expedition67.unit;

public abstract class UnitBrain {
    //use to manage the logic of unit, both player and enemies
    
    protected Unit owner;
    public UnitBrain(){};

    public final void setOwner(Unit owner) {
        this.owner = owner;
    }

    public void update(){

    }

    public abstract void takeDamage(Unit src, float amount);
    public abstract void startTurn();
    public abstract void endTurn();
    public abstract UnitBrain copy();

    public void heal(float amount){

    }

    public void addDef(float amount, int forTurn){

    }

    public void addAgi(float amount, int forTurn){
        
    }

    public void addCritRate(float amount, int forTurn){

    }

    public void addCritDmg(float amount, int forTurn){

    }

    private void onTurnStarted(){

    }

    private void onTurnEnded(){
        
    }
}
