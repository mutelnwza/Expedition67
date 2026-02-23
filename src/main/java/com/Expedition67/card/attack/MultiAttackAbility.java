package com.Expedition67.card.attack;

import com.Expedition67.card.CardAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.unit.Unit;

/**
 * Represents an ability that attacks a single target multiple times.
 */
public class MultiAttackAbility extends DamageAbility {

    private final int amount;

    /**
     * Constructs a multi-attack ability with a fixed damage value per hit.
     *
     * @param value    The damage for each individual hit.
     * @param cardType The type of card, typically ATK.
     * @param amount   The number of hits.
     */
    public MultiAttackAbility(int value, CardType cardType, int amount) {
        super(value, cardType);
        this.amount = amount;
    }

    /**
     * Constructs a multi-attack ability with a variable damage range per hit.
     *
     * @param min      The minimum damage for each hit.
     * @param max      The maximum damage for each hit.
     * @param cardType The type of card, typically ATK.
     * @param amount   The number of hits.
     */
    public MultiAttackAbility(int min, int max, CardType cardType, int amount) {
        super(min, max, cardType);
        this.amount = amount;
    }

    /**
     * Applies the multi-attack ability to the target unit.
     *
     * @param target The unit to be attacked.
     * @param src    The unit performing the attack.
     */
    @Override
    public void apply(Unit target, Unit src) {
        float totalDamage = 0;
        for (int i = 0; i < amount; i++) {
            totalDamage += calculateAndDealDamage(target, src);
        }
        CombatManager.Instance().addActionString(String.format(" strikes %s %d times for a total of %.2f damage!", target.getName(), amount, totalDamage));
    }

    /**
     * Creates a copy of this MultiAttackAbility.
     *
     * @return A new MultiAttackAbility instance with the same properties.
     */
    @Override
    public CardAbility copy() {
        if (getMinDmg() > 0 && getMaxDmg() > 0) {
            return new MultiAttackAbility(getMinDmg(), getMaxDmg(), getCardType(), amount);
        }
        return new MultiAttackAbility(getValue(), getCardType(), amount);
    }
}
