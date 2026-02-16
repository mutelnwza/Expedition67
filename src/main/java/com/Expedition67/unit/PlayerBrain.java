package com.Expedition67.unit;

import com.Expedition67.storage.CardInventory;

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
        ap = 4;
        onTurnStarted();
    }

    @Override
    public void endTurn() {
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
