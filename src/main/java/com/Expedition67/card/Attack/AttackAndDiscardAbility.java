package com.Expedition67.card.Attack;

import com.Expedition67.card.Card;
import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;

public class AttackAndDiscardAbility extends DamageAbility{

    public AttackAndDiscardAbility(int value, CardType cardType) {
        super(value, cardType);
    }
    
    @Override
    public void apply(Unit target, Unit src){
        //use to player, for boss
        Card c = CombatManager.Instance().getDeck().getRandomCardFromHand();
        CombatManager.Instance().getDeck().useCard(c);
        for(int i=0;i<c.getAP();i++){
            super.apply(target,src);
        }
    }
}
