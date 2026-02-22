package com.Expedition67.card.heal;

import com.Expedition67.card.RemovableAbility;
import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;

import java.util.ArrayList;
import java.util.List;

public class HealAndRemoveDebuffAbility extends HealAbility {

    public HealAndRemoveDebuffAbility(int value, CardType cardType) {
        super(value, cardType);
    }

    @Override
    public void apply(Unit target) {
        super.apply(target);
        ArrayList<RemovableAbility> allDebuffs = target.getBrain().getCurrentDebuffs();
        if (!allDebuffs.isEmpty()) {
            List<RemovableAbility> debuffsToRemove = new ArrayList<>(allDebuffs);
            for (RemovableAbility ra : debuffsToRemove)
                target.getBrain().removeBuffs(ra);
            CombatManager.Instance().addActionString(" And cleanses all negative effects!");
        }
    }
}
