package com.Expedition67.card.Attack;

import com.Expedition67.card.RemoveableAbility;
import com.Expedition67.core.CombatManager;
import com.Expedition67.core.ITickable;
import com.Expedition67.unit.Unit;

public class PoisonAbility extends RemoveableAbility implements ITickable{

    public PoisonAbility(int turn, int value, CardType cardType) {
        super(turn, value, cardType);
    }

    @Override
    public void onTick(Unit owner) {
        owner.takeDamage(value);
        CombatManager.Instance().addActionString(owner.getName() + " takes " + value + " poison damage");
    }

    @Override
    public void apply(Unit target) {
          CombatManager.Instance().addActionString(target.getName() + " is poisoned  " );
        
    }
    
}
