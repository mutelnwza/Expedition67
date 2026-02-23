package com.Expedition67.card.attack;

import com.Expedition67.card.CardAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.unit.Unit;

/**
 * Represents a standard damage-dealing ability.
 */
public class DamageAbility extends CardAbility {

    private int minDmg = 0;
    private int maxDmg = 0;

    /**
     * Constructs a damage ability with a fixed amount.
     *
     * @param value    The amount of damage to deal.
     * @param cardType The type of card, typically ATK.
     */
    public DamageAbility(int value, CardType cardType) {
        super(value, cardType);
    }

    /**
     * Constructs a damage ability with a variable amount.
     *
     * @param min      The minimum possible damage.
     * @param max      The maximum possible damage.
     * @param cardType The type of card, typically ATK.
     */
    public DamageAbility(int min, int max, CardType cardType) {
        super(cardType);
        this.minDmg = min;
        this.maxDmg = max;
    }

    /**
     * Applies damage to a target unit.
     *
     * @param target The unit to receive damage.
     */
    @Override
    public void apply(Unit target) {
        float dmg = calculateDamage(value);
        target.takeDamage(dmg);
        CombatManager.Instance().addActionString(String.format(" deals %.2f damage to %s", dmg, target.getName()));
    }

    /**
     * Applies damage to a target unit from a specified source.
     *
     * @param target The unit to receive damage.
     * @param src    The unit dealing the damage.
     */
    @Override
    public void apply(Unit target, Unit src) {
        super.apply(target, src);
        float dmg = calculateAndDealDamage(target, src, value);
        CombatManager.Instance().addActionString(String.format(" deals %.2f damage to %s", dmg, target.getName()));
    }

    /**
     * Creates a copy of this DamageAbility.
     *
     * @return A new DamageAbility instance with the same properties.
     */
    @Override
    public CardAbility copy() {
        if (minDmg > 0 && maxDmg > 0) {
            return new DamageAbility(minDmg, maxDmg, getCardType());
        }
        return new DamageAbility(value, getCardType());
    }

    /**
     * Calculates and deals damage, using the ability's main value.
     *
     * @param target The unit to receive damage.
     * @param src    The unit dealing the damage.
     * @return The final damage amount dealt.
     */
    protected float calculateAndDealDamage(Unit target, Unit src) {
        return calculateAndDealDamage(target, src, this.value);
    }

    /**
     * Calculates and deals damage, allowing a custom base value.
     *
     * @param target    The unit to receive damage.
     * @param src       The unit dealing the damage.
     * @param baseValue The base damage value for calculation.
     * @return The final damage amount dealt.
     */
    protected float calculateAndDealDamage(Unit target, Unit src, float baseValue) {
        float dmg = calculateDamage(baseValue);
        dmg *= (1 + (src.getUnitStats().getStr() / 10.0f));
        if (Math.random() <= src.getUnitStats().getCrit()) {
            dmg *= 1.5F;
        }
        target.takeDamage(dmg);
        return dmg;
    }

    /**
     * Calculates the base damage, considering fixed or ranged values.
     *
     * @param val The fixed damage value.
     * @return The calculated base damage.
     */
    protected float calculateDamage(float val) {
        if (maxDmg > 0 && minDmg > 0) {
            return (float) ((Math.random() * (maxDmg - minDmg + 1)) + minDmg);
        }
        return val;
    }

    /**
     * Gets the minimum damage of the ability.
     *
     * @return The minimum damage.
     */
    public int getMinDmg() {
        return minDmg;
    }

    /**
     * Gets the maximum damage of the ability.
     *
     * @return The maximum damage.
     */
    public int getMaxDmg() {
        return maxDmg;
    }
}
