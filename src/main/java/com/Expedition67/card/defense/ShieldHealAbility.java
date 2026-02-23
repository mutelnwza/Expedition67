package com.Expedition67.card.defense;

import com.Expedition67.card.CardAbility;
import com.Expedition67.card.heal.HealAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.unit.Unit;

/**
 * Represents an ability that combines both shielding and healing effects.
 */
public class ShieldHealAbility extends CardAbility {

    private final ShieldAbility shield;
    private final HealAbility heal;

    protected final int shieldVal;
    protected final int healVal;

    /**
     * Constructs a Shield and Heal ability.
     *
     * @param shield   The amount of block to grant.
     * @param heal     The amount of health to restore.
     * @param cardType The type of card, typically DEF or HEAL.
     */
    public ShieldHealAbility(int shield, int heal, CardType cardType) {
        super(cardType);
        this.shield = new ShieldAbility(shield, cardType);
        this.heal = new HealAbility(heal, cardType);
        this.shieldVal = shield;
        this.healVal = heal;
    }

    /**
     * Applies both the shield and heal effects to the target unit.
     *
     * @param target The unit to receive the shield and heal.
     */
    @Override
    public void apply(Unit target) {
        shield.apply(target);
        CombatManager.Instance().addActionString(" And");
        heal.apply(target);
    }

    /**
     * Creates a copy of this ShieldHealAbility.
     *
     * @return A new ShieldHealAbility instance with the same properties.
     */
    @Override
    public CardAbility copy() {
        return new ShieldHealAbility(shieldVal, healVal, getCardType());
    }

    /**
     * Sets a temporary shield value for the ability.
     *
     * @param newVal The new temporary shield value.
     */
    protected void setShieldValue(int newVal) {
        this.shield.setValue(newVal);
    }
}
