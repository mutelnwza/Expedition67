package com.Expedition67.card.Debuff;

import com.Expedition67.card.RemoveableAbility;
import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;

public class NerfHandAbility extends RemoveableAbility{

    public NerfHandAbility(int turn, int value, CardType cardType) {
        super(turn, value, cardType);
    }

    @Override
    public void apply(Unit target) {
        CombatManager.Instance().getDeck().setHandSize(CombatManager.Instance().getDeck().getHandSize()-value);
        CombatManager.Instance().addActionString(" hand size -" + value);
    }


    @Override
    public void remove(Unit target){
        CombatManager.Instance().getDeck().setHandSize(CombatManager.Instance().getDeck().getHandSize()+value);
        CombatManager.Instance().addActionString(" hand size +" + value);
    }
    
}
