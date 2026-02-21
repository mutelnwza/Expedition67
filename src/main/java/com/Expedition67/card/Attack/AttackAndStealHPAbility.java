package com.Expedition67.card.Attack;

import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;

public class AttackAndStealHPAbility extends DamageAbility {
    private int steal;

    public AttackAndStealHPAbility(int value, int steal, CardType cardType) {
        super(value, cardType);
        this.steal=steal;
    }
    
    @Override
    public void apply(Unit target, Unit src){
        if(target.getUnitStats().getDef()==0){
            //steal life if target got no block
            target.getUnitStats().setMaxHp(target.getUnitStats().getMaxHp()-steal);
            src.getUnitStats().setMaxHp(src.getUnitStats().getHp()+steal);
        }
        super.apply(target,src);
        CombatManager.Instance().addActionString(" steals " + steal + " HP from " + target.getName());
    }
}   
