package com.Expedition67.card.defense;

import com.Expedition67.card.CardAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.unit.Unit;
import com.Expedition67.unit.UnitStats;

/**
 * Represents a defensive ability that provides a larger shield at low health.
 */
public class RebirthAbility extends ShieldHealAbility {

    private final int maxShield;

    /**
     * Constructs a Rebirth ability.
     *
     * @param shield    The normal amount of shield to grant.
     * @param maxShield The amount of shield to grant when health is below 20%.
     * @param heal      The amount of health to restore.
     * @param cardType  The type of card, typically DEF or HEAL.
     */
    public RebirthAbility(int shield, int maxShield, int heal, CardType cardType) {
        super(shield, heal, cardType);
        this.maxShield = maxShield;
    }

    /**
     * Applies the Rebirth ability to the target unit.
     *
     * @param target The unit to apply the effect to.
     */
    @Override
    public void apply(Unit target) {
        UnitStats stats = target.getUnitStats();
        float hpPercent = (stats.getHp() / stats.getMaxHp()) * 100;

        if (hpPercent < 20) {
            super.setShieldValue(this.maxShield);
            CombatManager.Instance().addActionString(" triggers Rebirth, granting a massive shield!");
        }

        super.apply(target);

        super.setShieldValue(this.shieldVal);
    }

    /**
     * Creates a copy of this RebirthAbility.
     *
     * @return A new RebirthAbility instance with the same properties.
     */
    @Override
    public CardAbility copy() {
        return new RebirthAbility(this.shieldVal, this.maxShield, this.healVal, getCardType());
    }
}
