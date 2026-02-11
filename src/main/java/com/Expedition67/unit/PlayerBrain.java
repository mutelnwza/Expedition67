package com.Expedition67.unit;

public class PlayerBrain extends UnitBrain{

    private int ap;

    public PlayerBrain(){
        
    }

    public void onUseCard(int ap){
        this.ap-=ap;
    }

    @Override
    public void takeDamage(float amount) {
        
    }

    @Override
    public void startTurn() {
        
    }

    @Override
    public void endTurn() {
        ap = 4;
    }

    @Override
    public UnitBrain copy() {
        return new PlayerBrain();
    }
    
    public int getAP(){return this.ap;}

    @Override
    protected void die() {
        
    }
}
