package com.Expedition67.storage;

import com.Expedition67.card.Card;

import java.util.ArrayList;

/**
 * Manages the player's collection of cards.
 */
public class CardInventory {

    private static CardInventory instance;
    private final ArrayList<Card> cards;

    private CardInventory() {
        cards = new ArrayList<>();
    }

    /**
     * Gets the single instance of the CardInventory.
     *
     * @return The single instance of CardInventory.
     */
    public static CardInventory Instance() {
        if (instance == null) {
            instance = new CardInventory();
        }
        return instance;
    }

    /**
     * Adds a specified number of copies of a card to the inventory.
     *
     * @param c      The card to add.
     * @param amount The number of copies to add.
     */
    public void addCard(Card c, int amount) {
        for (int i = 0; i < amount; i++) {
            cards.add(c.copy());
        }
    }

    /**
     * Clears all cards from the inventory.
     */
    public void emptyInventory() {
        cards.clear();
    }

    /**
     * Gets the list of all cards in the inventory.
     *
     * @return An ArrayList of cards.
     */
    public ArrayList<Card> getCardInventory() {
        return cards;
    }
}
