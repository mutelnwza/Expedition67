package com.Expedition67.card;
import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;

public class ShieldAbility extends CardAbility {
    
    public ShieldAbility(int value, CardType cardType){
        super(value,cardType);
    }

    @Override
    public void apply(Unit target){
        target.getBrain().addDef(value);
        CombatManager.Instance().addActionString(" shield to" + target.getName() + " = " + value);
     }
}
