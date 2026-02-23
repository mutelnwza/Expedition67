package com.Expedition67.card;

import com.Expedition67.unit.Unit;
import com.Expedition67.unit.player.PlayerBrain;

/**
 * Represents a single card in the game, with a name, cost, and ability.
 */
public class Card {

    private final CardName name;
    private final int baseAP;
    private final CardAbility ability;
    private final CardTier cardTier;
    private final String description;

    private int apCost;
    private boolean locked = false;

    /**
     * Constructs a new card.
     *
     * @param name        The name of the card from the CardName enum.
     * @param apCost      The base Action Point cost to use the card.
     * @param ability     The object that defines the card's effect.
     * @param cardTier    The tier of the card (e.g., NORMAL, RARE).
     * @param description A short description of the card's effect.
     */
    public Card(CardName name, int apCost, CardAbility ability, CardTier cardTier, String description) {
        this.name = name;
        this.baseAP = apCost;
        this.apCost = apCost;
        this.ability = ability;
        this.cardTier = cardTier;
        this.description = description;
    }

    /**
     * Constructs a copy of an existing card.
     *
     * @param c The card to copy.
     */
    public Card(Card c) {
        this.name = c.name;
        this.baseAP = c.baseAP;
        this.apCost = c.apCost;
        this.ability = c.ability;
        this.cardTier = c.cardTier;
        this.description = c.description;
        this.locked = c.locked;
    }

    /**
     * Uses the card's ability on a target unit.
     *
     * @param playerBrain The brain of the player using the card.
     * @param target      The unit to apply the card's ability to.
     */
    public void use(PlayerBrain playerBrain, Unit target) {
        target.getBrain().applyCard(ability, playerBrain.getOwner());
    }

    /**
     * Adds to the current AP cost of the card.
     *
     * @param cost The amount to add to the AP cost (can be negative).
     */
    public void addCost(int cost) {
        apCost += cost;
    }

    /**
     * Resets the AP cost to its base value.
     */
    public void resetAP() {
        apCost = baseAP;
    }

    /**
     * Creates a copy of this card.
     *
     * @return A new Card object with the same attributes.
     */
    public Card copy() {
        return new Card(this);
    }

    /**
     * Checks if the card is locked.
     *
     * @return true if the card is locked, false otherwise.
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * Sets the card's locked state.
     *
     * @param state The new locked state.
     */
    public void setLocked(boolean state) {
        locked = state;
    }

    /**
     * Gets the card's name.
     *
     * @return The card's CardName enum value.
     */
    public CardName getName() {
        return name;
    }

    /**
     * Gets the card's current AP cost.
     *
     * @return The current AP cost.
     */
    public int getAP() {
        return apCost;
    }

    /**
     * Sets the card's AP cost.
     *
     * @param newAP The new AP cost.
     */
    public void setAP(int newAP) {
        apCost = newAP;
    }

    /**
     * Gets the card's ability.
     *
     * @return The card's CardAbility.
     */
    public CardAbility getAbility() {
        return ability;
    }

    /**
     * Gets the card's tier.
     *
     * @return The card's CardTier.
     */
    public CardTier getTier() {
        return cardTier;
    }

    /**
     * Returns a string representation of the card.
     *
     * @return A formatted string with the card's details.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name);
        if (locked) {
            sb.append(" (Locked)");
        }
        sb.append("\nAP: ").append(apCost).append("\n");
        sb.append(description);
        return sb.toString();
    }

    /**
     * Represents the tier or rarity of a card.
     */
    public enum CardTier {
        NORMAL, RARE, DEBUFF
    }
}
