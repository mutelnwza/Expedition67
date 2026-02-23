package com.Expedition67.card.heal;

import com.Expedition67.card.RemovableAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.unit.Unit;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an ability that heals a target and removes all active debuffs.
 */
public class HealAndRemoveDebuffAbility extends HealAbility {

    /**
     * Constructs a healing and debuff-removing ability.
     *
     * @param value    The amount of health to restore.
     * @param cardType The type of card, typically HEAL.
     */
    public HealAndRemoveDebuffAbility(int value, CardType cardType) {
        super(value, cardType);
    }

    /**
     * Applies the healing effect and removes all debuffs from the target unit.
     *
     * @param target The unit to be healed and cleansed.
     */
    @Override
    public void apply(Unit target) {
        super.apply(target);

        ArrayList<RemovableAbility> allDebuffs = target.getBrain().getCurrentDebuffs();

        if (!allDebuffs.isEmpty()) {
            List<RemovableAbility> debuffsToRemove = new ArrayList<>(allDebuffs);

            for (RemovableAbility ra : debuffsToRemove) {
                target.getBrain().removeBuffs(ra);
            }

            CombatManager.Instance().addActionString(" And cleanses all negative effects!");
        }
    }
}
