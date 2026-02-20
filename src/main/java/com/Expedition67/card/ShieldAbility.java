package com.Expedition67.card;
import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;

public class ShieldAbility implements CardAbility {
    private int Shield;
    
    public ShieldAbility(int Shield){
        this.Shield = Shield;
    }

    @Override
    public void apply(Unit target){
        target.getBrain().addDef(Shield);
    }
    public void apply(Unit target, Unit src){
        target.getBrain().applyCard(this,src);
        CombatManager.Instance().addActionString(" grants "+ Shield + " Shield to "+ target.getName() + ".");
    }

}
