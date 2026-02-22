package com.Expedition67.card.defense;

import com.Expedition67.card.CardAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.unit.Unit;
import com.Expedition67.unit.UnitStats;

public class RebirthAbility extends ShieldHealAbility {

    private final int maxShield;

    public RebirthAbility(int shield, int maxShield, int heal, CardType cardType) {
        super(shield, heal, cardType);
        this.maxShield = maxShield;
    }

    @Override
    public void apply(Unit target) {
        UnitStats stats = target.getUnitStats();
        float hpPercent = (stats.getHp() / stats.getMaxHp()) * 100;

        if (hpPercent < 20) {
            super.setShieldValue(maxShield);
            CombatManager.Instance().addActionString(" triggers Rebirth, granting a massive shield!");
        }

        super.apply(target);
        super.setShieldValue(shieldVal);
    }

    @Override
    public CardAbility copy() {
        return new RebirthAbility(shieldVal, maxShield, healVal, getCardType());
    }
}
