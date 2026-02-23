package com.Expedition67.card.debuff;

import com.Expedition67.card.CardAbility;
import com.Expedition67.card.RemovableAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.unit.Unit;

/**
 * Represents a debuff that temporarily reduces a target's Strength (STR) stat.
 */
public class NerfStatsAbility extends RemovableAbility {

    /**
     * Constructs a Nerf Stats ability.
     *
     * @param turn     The number of turns the debuff lasts.
     * @param value    The amount of Strength to reduce.
     * @param cardType The type of card, typically DEBUFF.
     */
    public NerfStatsAbility(int turn, int value, CardType cardType) {
        super(turn, value, cardType);
    }

    /**
     * Applies the Strength reduction debuff to the target unit.
     *
     * @param target The unit to be weakened.
     */
    @Override
    public void apply(Unit target) {
        target.getBrain().addStr(-1 * this.value);
        CombatManager.Instance().addActionString(String.format(" weakens %s, reducing their Strength by %d for %d turn(s).", target.getName(), this.value, getTurn()));
    }

    /**
     * Removes the Strength reduction debuff from the target unit.
     *
     * @param target The unit from which to remove the debuff.
     */
    @Override
    public void remove(Unit target) {
        target.getBrain().addStr(this.value);
        CombatManager.Instance().setActionString(String.format("%s's Strength has returned to normal.", target.getName()));
    }

    /**
     * Creates a copy of this NerfStatsAbility.
     *
     * @return A new NerfStatsAbility instance with the same properties.
     */
    @Override
    public CardAbility copy() {
        return new NerfStatsAbility(getTurn(), getValue(), getCardType());
    }
}
