package com.Expedition67.card;
import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;

public class DamageAbility extends CardAbility {

    public DamageAbility(int value, CardType cardType) {
        super(value, cardType);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void apply(Unit target){
        target.takeDamage(value);
        CombatManager.Instance().addActionString(" deal damage to " + target.getName()+ " = " + value);
    }

    public void setDamage(int newDmg){
        this.value=newDmg;
    }
    public int getDamage(){
        return value;
    }
}
