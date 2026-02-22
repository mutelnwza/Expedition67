package com.Expedition67.unit.player;

import com.Expedition67.card.Card;
import com.Expedition67.card.CardAbility;
import com.Expedition67.unit.UnitBrain;

public class PlayerBrain extends UnitBrain {

    private int ap;
    private final int MAX_AP = 4;
    private int resonanceBlockAmount = 0;

    public PlayerBrain() {
        ap = MAX_AP;
    }

    public void applyResonance(int amount) {
        resonanceBlockAmount += amount;
        System.out.println("apply resonance: "+resonanceBlockAmount);
    }

    public void onUseCard(Card c) {
        this.ap -= c.getAP();
        //System.out.println("Use");
        if (c.getAbility().getCardType() == CardAbility.CardType.ATK && resonanceBlockAmount > 0) {
            this.addDef(resonanceBlockAmount);
            System.out.println("got def");
        }
    }

    @Override
    public float takeDamage(float amount) {
        return super.takeDamage(amount);
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

    public int getAP() {
        return this.ap;
    }
}
