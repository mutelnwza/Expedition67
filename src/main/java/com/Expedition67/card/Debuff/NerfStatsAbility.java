package com.Expedition67.card.Debuff;

import com.Expedition67.card.RemoveableAbility;
import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;

public class NerfStatsAbility extends RemoveableAbility{

    public NerfStatsAbility(int turn, int value, CardType cardType){
        super(turn,value, cardType);
    }

    @Override
    public void apply(Unit target) {
        target.getBrain().addStr(-1*value);
        CombatManager.Instance().addActionString(" strength -" + value);
    }
    
    @Override
    public void remove(Unit target){
        target.getBrain().addStr(1*value);
        CombatManager.Instance().addActionString(" strength +" + value);
    }
}
