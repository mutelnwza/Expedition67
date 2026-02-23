package com.Expedition67.card.defense;

import com.Expedition67.card.CardAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.unit.Unit;

/**
 * Represents a basic defense ability that grants Block (defense points).
 */
public class ShieldAbility extends CardAbility {

    private int min = 0;
    private int max = 0;

    /**
     * Constructs a shield ability with a variable block amount.
     *
     * @param min      The minimum block to gain.
     * @param max      The maximum block to gain.
     * @param cardType The type of card, typically DEF.
     */
    public ShieldAbility(int min, int max, CardType cardType) {
        super(cardType);
        this.min = min;
        this.max = max;
    }

    /**
     * Constructs a shield ability with a fixed block amount.
     *
     * @param value    The amount of block to gain.
     * @param cardType The type of card, typically DEF.
     */
    public ShieldAbility(int value, CardType cardType) {
        super(value, cardType);
    }

    /**
     * Applies the shield effect to a target unit.
     *
     * @param target The unit to receive the block.
     */
    @Override
    public void apply(Unit target) {
        int blockAmount = getBlockAmount();
        target.getBrain().addDef(blockAmount);
        CombatManager.Instance().addActionString(String.format(" gains %d block!", blockAmount));
    }

    /**
     * Applies the shield effect to a target unit from a specified source.
     *
     * @param target The unit to receive the block.
     * @param src    The unit using the ability.
     */
    @Override
    public void apply(Unit target, Unit src) {
        super.apply(target, src);
        int blockAmount = getBlockAmount();
        target.getBrain().addDef(blockAmount);
        CombatManager.Instance().addActionString(String.format(" gains %d block!", blockAmount));
    }

    /**
     * Creates a copy of this ShieldAbility.
     *
     * @return A new ShieldAbility instance with the same properties.
     */
    @Override
    public CardAbility copy() {
        if (min > 0 && max > 0) {
            return new ShieldAbility(min, max, getCardType());
        }
        return new ShieldAbility(getValue(), getCardType());
    }

    /**
     * Calculates the amount of block to grant.
     *
     * @return The calculated block amount.
     */
    private int getBlockAmount() {
        if (max > 0 && min > 0) {
            return (int) (min + (Math.random() * (max - min + 1)));
        }
        return this.value;
    }
}
