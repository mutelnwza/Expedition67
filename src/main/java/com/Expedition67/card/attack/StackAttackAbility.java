package com.Expedition67.card.attack;

import com.Expedition67.card.CardAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.unit.Unit;

/**
 * Represents an attack that also increases the user's strength stat.
 */
public class StackAttackAbility extends DamageAbility {

    private final int stackAdd;

    /**
     * Constructs a Stack Attack ability.
     *
     * @param value    The base damage of the attack.
     * @param stackAdd The amount of strength to gain on a successful hit.
     * @param cardType The type of card, typically ATK.
     */
    public StackAttackAbility(int value, int stackAdd, CardType cardType) {
        super(value, cardType);
        this.stackAdd = stackAdd;
    }

    /**
     * Applies the ability's effect: deals damage and increases the user's strength.
     *
     * @param target The unit to be damaged.
     * @param src    The unit performing the attack.
     */
    @Override
    public void apply(Unit target, Unit src) {
        float dealtDamage = calculateAndDealDamage(target, src);
        String action = String.format(" deals %.2f damage to %s", dealtDamage, target.getName());

        if (dealtDamage > 0) {
            src.getBrain().addStr(stackAdd);
            action += " and grows stronger!";
        }
        CombatManager.Instance().addActionString(action);
    }

    /**
     * Creates a copy of this StackAttackAbility.
     *
     * @return A new StackAttackAbility instance with the same properties.
     */
    @Override
    public CardAbility copy() {
        return new StackAttackAbility(value, stackAdd, getCardType());
    }
}
