package com.Expedition67.unit;

import com.Expedition67.card.Card;
import com.Expedition67.card.CardAbility;

public class PlayerBrain extends UnitBrain{

    private int ap;
    private final int MAX_AP = 4;
    private boolean isApplyResonance = false;

    public PlayerBrain(){
        ap = MAX_AP;
    }

    public void applyResonance(boolean b){
        isApplyResonance=b;
    }

    public void onUseCard(Card c){
        this.ap -= c.getAP();
        if( c.getAbility().getCardType()==CardAbility.CardType.ATK && isApplyResonance){
            this.addDef(6);
        }
    }

    @Override
    public float takeDamage(float amount) {
        float d = super.takeDamage(amount);
        return d;
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
}
