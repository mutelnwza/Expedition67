package com.Expedition67.card;
import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;
import java.util.ArrayList;

public class HealAndRemoveDebuffAbility extends HealAbility {

    public HealAndRemoveDebuffAbility(int value, CardType cardType) {
        super(value, cardType);
    }

    @Override
    public void apply(Unit target) {
        super.apply(target);
        ArrayList<RemoveableAbility> allDebuffs = target.getBrain().getCurrentDebuffs();
        for(RemoveableAbility ra : allDebuffs){
            target.getBrain().removeBuffs(ra);
        }
        CombatManager.Instance().addActionString(" cleanses all negative effects " + target.getName()+ " = " + value);
    }

}