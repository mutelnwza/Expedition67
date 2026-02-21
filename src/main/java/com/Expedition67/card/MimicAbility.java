package com.Expedition67.card;

import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;

public class MimicAbility extends CardAbility{

    public MimicAbility(CardType cardType) {
        super(cardType);
    }

    @Override
    public void apply(Unit target) {
        Card c = CombatManager.Instance().getDeck().getRandomCardFromHand(CardType.ATK);
        c.getAbility().apply(target);
        CombatManager.Instance().addActionString("mimic copies " + c.getName());
    }
    
}
