package com.Expedition67.unit;

public class PlayerBrain extends UnitBrain{

    private int ap;
    private final int MAX_AP = 4;

    public PlayerBrain(){
        ap = MAX_AP;
    }

    public void onUseCard(int ap){
        this.ap -= ap;
    }

    @Override
    public void takeDamage(float amount) {
        
    }

    @Override
    public void onTurnEnded() {
        super.onTurnEnded();
        this.ap = MAX_AP;
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
