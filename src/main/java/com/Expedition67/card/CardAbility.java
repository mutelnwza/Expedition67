package com.Expedition67.card;

import com.Expedition67.unit.Unit;

/**
 * Defines the basic structure for all card abilities in the game.
 */
public abstract class CardAbility {

    private final CardType cardType;
    protected int value;
    protected int baseValue;

    /**
     * Constructs a card ability with a numerical value.
     *
     * @param value    The initial value of the ability.
     * @param cardType The type of card (e.g., ATK, DEF).
     */
    public CardAbility(int value, CardType cardType) {
        this.cardType = cardType;
        this.value = value;
        this.baseValue = value;
    }

    /**
     * Constructs a card ability without a numerical value.
     *
     * @param cardType The type of card.
     */
    public CardAbility(CardType cardType) {
        this.cardType = cardType;
    }

    /**
     * Applies the ability's effect to a target unit.
     *
     * @param target The unit to be affected by the ability.
     */
    public abstract void apply(Unit target);

    /**
     * Applies the ability's effect, specifying both the source and target units.
     *
     * @param target The unit to be affected by the ability.
     * @param src    The unit using the ability.
     */
    public void apply(Unit target, Unit src) {
        src.getBrain().onUseCard(this);
    }

    /**
     * Creates a copy of this card ability.
     *
     * @return A new instance of the CardAbility subclass.
     */
    public abstract CardAbility copy();

    /**
     * Resets the ability's value to its original base value.
     */
    public void resetValue() {
        value = baseValue;
    }

    /**
     * Gets the type of the card ability.
     *
     * @return The CardType enum value.
     */
    public CardType getCardType() {
        return cardType;
    }

    /**
     * Gets the current value of the ability.
     *
     * @return The ability's current numerical value.
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets a new permanent value for the ability.
     *
     * @param newValue The new value for the ability.
     */
    public void setValue(int newValue) {
        this.value = newValue;
        this.baseValue = newValue;
    }

    /**
     * Sets a temporary value for the ability without changing the base value.
     *
     * @param newValue The new temporary value.
     */
    public void setTemporaryValue(int newValue) {
        this.value = newValue;
    }

    /**
     * Represents the different types of card abilities.
     */
    public enum CardType {
        ATK, DEF, HEAL, BUFF, VOID, DEBUFF
    }
}
