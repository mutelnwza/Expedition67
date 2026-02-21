package com.Expedition67.card.Attack;

import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;

public class StackAttackAbility extends DamageAbility {

    public StackAttackAbility(int value, int stackAdd, CardType cardType) {
        super(value, cardType);
    }
    
    @Override
    public void apply(Unit target, Unit src){
        float prevHp = target.getUnitStats().getHp();
        super.apply(target,src);
        float afterHp = target.getUnitStats().getHp();
        if(prevHp>afterHp){
            super.setDamage(value+2);
            CombatManager.Instance().addActionString("'s attack power increases");
        }
    }
}
