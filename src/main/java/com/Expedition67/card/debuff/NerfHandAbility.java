package com.Expedition67.card.debuff;

import com.Expedition67.card.CardAbility;
import com.Expedition67.card.RemovableAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.unit.Unit;

/**
 * Represents a debuff that temporarily reduces the player's hand size.
 */
public class NerfHandAbility extends RemovableAbility {

    /**
     * Constructs a Nerf Hand ability.
     *
     * @param turn     The number of turns the hand size reduction lasts.
     * @param value    The amount to reduce the hand size by.
     * @param cardType The type of card, typically DEBUFF.
     */
    public NerfHandAbility(int turn, int value, CardType cardType) {
        super(turn, value, cardType);
    }

    /**
     * Applies the hand size reduction debuff to the target unit.
     *
     * @param target The unit being affected.
     */
    @Override
    public void apply(Unit target) {
        int currentHandSize = CombatManager.Instance().getDeck().getHandSize();
        CombatManager.Instance().getDeck().setHandSize(currentHandSize - this.value);

        CombatManager.Instance().addActionString(String.format(" restricts %s's hand, reducing their hand size by %d for %d turn(s).", target.getName(), this.value, getTurn()));
    }

    /**
     * Removes the hand size reduction debuff from the target unit.
     *
     * @param target The unit from which to remove the debuff.
     */
    @Override
    public void remove(Unit target) {
        int currentHandSize = CombatManager.Instance().getDeck().getHandSize();
        CombatManager.Instance().getDeck().setHandSize(currentHandSize + this.value);

        CombatManager.Instance().setActionString(String.format("%s's hand size has returned to normal.", target.getName()));
    }

    /**
     * Creates a copy of this NerfHandAbility.
     *
     * @return A new NerfHandAbility instance with the same properties.
     */
    @Override
    public CardAbility copy() {
        return new NerfHandAbility(getTurn(), getValue(), getCardType());
    }
}
