package com.Expedition67.unit;

import com.Expedition67.card.Card;

public class PlayerBrain extends UnitBrain{

    private int ap;

    public PlayerBrain(){
        
    }

    public void useCard(Card c, Unit target){
        ap -= c.getAP();
        c.use(this, target);
    }

    @Override
    public void takeDamage(float amount) {
        
    }

    @Override
    protected void onTurnEnded() {
        this.ap = 4;
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
