package com.Expedition67.card;

import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;
import com.Expedition67.unit.UnitStats;

public class RebirthAbility extends ShieldHealAbility {

    public RebirthAbility(CardType cardType) {
        super(0, 0, cardType);
    }

    @Override
    public void apply(Unit target) {
        // 1. Calculate the 20% threshold
        // Note: Assumes your Unit class has these getter methods.
        UnitStats stats = target.getBrain().getStats();
        float currentHp = stats.getHp();
        float maxHp = stats.getMaxHp();
        float hpPercent = (currentHp / maxHp) * 100;

        int finalHeal = 28;
        int finalDef;

        if (hpPercent < 20) {
            finalDef = 30; 
            CombatManager.Instance().addActionString(" [REBIRTH TRIGGERED] ");
        } else {
            finalDef = 10; 
        }

        // 3. Apply the results to the unit
        target.getBrain().heal(finalHeal);
        target.getBrain().addDef(finalDef);

        // 4. Log the result
        String logMsg = String.format(" %s: Rebirth restored %d HP and %d Shield", 
                                      target.getName(), finalHeal, finalDef);
        CombatManager.Instance().addActionString(logMsg);
    }
}