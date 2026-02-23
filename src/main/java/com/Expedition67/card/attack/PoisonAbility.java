package com.Expedition67.card.attack;

import com.Expedition67.card.CardAbility;
import com.Expedition67.card.RemovableAbility;
import com.Expedition67.core.ITickable;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.unit.Unit;

/**
 * Represents a poison ability that deals damage over time.
 */
public class PoisonAbility extends RemovableAbility implements ITickable {

    /**
     * Constructs a poison ability.
     *
     * @param turn     The number of turns the poison lasts.
     * @param value    The amount of damage to deal each turn.
     * @param cardType The type of card, typically DEBUFF.
     */
    public PoisonAbility(int turn, int value, CardType cardType) {
        super(turn, value, cardType);
    }

    /**
     * Applies the poison effect to the target unit.
     *
     * @param target The unit to be poisoned.
     */
    @Override
    public void apply(Unit target) {
        CombatManager.Instance().setActionString(String.format("%s is poisoned for %d turns", target.getName(), getTurn()));
    }

    /**
     * Removes the poison effect from the target unit.
     *
     * @param target The unit from which to remove the poison.
     */
    @Override
    public void remove(Unit target) {
        CombatManager.Instance().setActionString(String.format("%s is no longer poisoned", target.getName()));
    }

    /**
     * Called each game tick to apply poison damage.
     *
     * @param owner The unit that is currently poisoned.
     */
    @Override
    public void onTick(Unit owner) {
        owner.takeDamage(value);
        CombatManager.Instance().addActionString(String.format("%s takes %d poison damage", owner.getName(), value));
    }

    /**
     * Creates a copy of this PoisonAbility.
     *
     * @return A new PoisonAbility instance with the same properties.
     */
    @Override
    public CardAbility copy() {
        return new PoisonAbility(getTurn(), getValue(), getCardType());
    }
}
