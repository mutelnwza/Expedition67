package com.Expedition67.card;

import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;
import com.Expedition67.unit.UnitStats;

public class RebirthAbility extends ShieldHealAbility {
    private final int maxShield;
    public RebirthAbility(int shield, int maxshield, int heal, CardType cardType) {
        super(shield, heal, cardType);
        this.maxShield = maxshield;
    }

    @Override
    public void apply(Unit target) {
        UnitStats stats = target.getUnitStats();
        float hpPercent = (stats.getHp() / stats.getMaxHp()) * 100;

        if (hpPercent < 20) {
            super.setShieldValue(maxShield);
            CombatManager.Instance().addActionString(" [REBIRTH TRIGGERED] ");
        }

        super.apply(target);
        super.setShieldValue(shieldVal);
    }
}