package com.Expedition67.card.heal;

import com.Expedition67.card.CardAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.unit.Unit;

/**
 * Represents an ability that restores a target's health.
 */
public class HealAbility extends CardAbility {

    private int minHeal = 0;
    private int maxHeal = 0;

    /**
     * Constructs a healing ability with a fixed amount.
     *
     * @param value    The amount of health to restore.
     * @param cardType The type of card, typically HEAL.
     */
    public HealAbility(int value, CardType cardType) {
        super(value, cardType);
    }

    /**
     * Constructs a healing ability with a variable amount.
     *
     * @param minHeal  The minimum health to restore.
     * @param maxHeal  The maximum health to restore.
     * @param cardType The type of card, typically HEAL.
     */
    public HealAbility(int minHeal, int maxHeal, CardType cardType) {
        super(cardType);
        this.minHeal = minHeal;
        this.maxHeal = maxHeal;
    }

    /**
     * Applies the healing effect to a target unit.
     *
     * @param target The unit to be healed.
     */
    @Override
    public void apply(Unit target) {
        int healAmount = getHealAmount();
        target.getBrain().heal(healAmount);
        CombatManager.Instance().addActionString(String.format(" restores %d HP.", healAmount));
    }

    /**
     * Applies the healing effect to a target unit from a specified source.
     *
     * @param target The unit to be healed.
     * @param src    The unit using the ability.
     */
    @Override
    public void apply(Unit target, Unit src) {
        super.apply(target, src);
        int healAmount = getHealAmount();
        target.getBrain().heal(healAmount);
        CombatManager.Instance().addActionString(String.format(" restores %d HP.", healAmount));
    }

    /**
     * Creates a copy of this HealAbility.
     *
     * @return A new HealAbility instance with the same properties.
     */
    @Override
    public CardAbility copy() {
        if (minHeal > 0 && maxHeal > 0) {
            return new HealAbility(minHeal, maxHeal, getCardType());
        }
        return new HealAbility(getValue(), getCardType());
    }

    /**
     * Calculates the amount of health to restore.
     *
     * @return The calculated healing amount.
     */
    private int getHealAmount() {
        if (minHeal > 0 && maxHeal > 0) {
            return (int) (minHeal + (Math.random() * (maxHeal - minHeal + 1)));
        }
        return this.value;
    }
}
