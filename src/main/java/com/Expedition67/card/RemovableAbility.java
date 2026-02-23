package com.Expedition67.card;

import com.Expedition67.unit.Unit;

/**
 * Represents an ability with a temporary effect that is removed after a set number of turns.
 */
public abstract class RemovableAbility extends CardAbility {

    protected final int turn;

    /**
     * Constructs a removable ability with a numerical value.
     *
     * @param turn     The duration of the ability in turns.
     * @param value    The value of the ability (e.g., amount of a buff).
     * @param cardType The type of the card.
     */
    public RemovableAbility(int turn, int value, CardType cardType) {
        super(value, cardType);
        this.turn = turn;
    }

    /**
     * Constructs a removable ability without a numerical value.
     *
     * @param turn     The duration of the ability in turns.
     * @param cardType The type of the card.
     */
    public RemovableAbility(int turn, CardType cardType) {
        super(cardType);
        this.turn = turn;
    }

    /**
     * Removes the ability's effect from a target unit.
     *
     * @param target The unit from which to remove the ability's effect.
     */
    public abstract void remove(Unit target);

    /**
     * Gets the duration of the ability in turns.
     *
     * @return The number of turns the ability lasts.
     */
    public int getTurn() {
        return turn;
    }
}
