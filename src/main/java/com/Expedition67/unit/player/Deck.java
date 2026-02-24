package com.Expedition67.unit.player;

import com.Expedition67.card.Card;
import com.Expedition67.card.CardAbility;
import com.Expedition67.core.graphics.GameView;
import com.Expedition67.storage.CardInventory;
import com.Expedition67.ui.HandUIHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Manages the player's deck, including the draw pile, hand, and discard pile.
 */
public class Deck {

    private ArrayList<Card> allCards;
    private Stack<Card> drawPile;
    private ArrayList<Card> discardPile;
    private final ArrayList<Card> hand;
    private final HandUIHandler handUI;
    private int handSize = 5;
    private int freeCardLeft = 0;

    /**
     * Constructs a new Deck.
     */
    public Deck() {
        hand = new ArrayList<>();
        handUI = new HandUIHandler(hand);
    }

    /**
     * Initializes the deck for a new combat encounter.
     */
    public void instantiate() {
        allCards = new ArrayList<>();
        drawPile = new Stack<>();
        discardPile = new ArrayList<>();
        hand.clear();

        for (Card c : CardInventory.Instance().getCardInventory()) {
            Card newCard = c.copy();
            drawPile.add(newCard);
            allCards.add(newCard);
            newCard.resetAP();
        }

        shuffle();
        handUI.horizontallyCentering(0, GameView.GAME_WIDTH);
        handUI.setVisible(true);
    }

    /**
     * Shuffles the draw pile.
     */
    public void shuffle() {
        Collections.shuffle(drawPile);
    }

    /**
     * Draws cards from the draw pile until the hand is full.
     */
    public void addToHand() {
        resetCardValues(hand);
        discardPile.addAll(hand);
        hand.clear();
        freeCardLeft = 0;

        while (hand.size() < handSize) {
            if (drawPile.isEmpty()) {
                if (discardPile.isEmpty()) break;
                recycleDiscardPile();
            }
            hand.add(drawPile.pop());
        }
        handUI.horizontallyCentering(0, GameView.GAME_WIDTH);
    }

    /**
     * Moves a used card from the hand to the discard pile.
     *
     * @param card The card that was used.
     */
    public void useCard(Card card) {
        if (hand.contains(card)) {
            hand.remove(card);
            discardPile.add(card);
            freeCardLeft--;
            handUI.resetSelection();
            handUI.horizontallyCentering(0, GameView.GAME_WIDTH);
        }
    }

    /**
     * Adds a new card to the draw pile.
     *
     * @param card The card to add.
     */
    public void addCard(Card card) {
        drawPile.add(card);
        allCards.add(card);
    }

    /**
     * Removes a card from the deck entirely.
     *
     * @param c The card to remove.
     */
    public void removeFromDeck(Card c) {
        allCards.remove(c);
        if (hand.contains(c)) {
            removeFromHand(c);
        } else if (discardPile.contains(c)) {
            discardPile.remove(c);
        } else {
            drawPile.remove(c);
        }
    }

    /**
     * Removes a card from the hand without discarding it.
     *
     * @param c The card to remove.
     */
    public void removeFromHand(Card c) {
        c.getAbility().resetValue();
        hand.remove(c);
        handUI.resetSelection();
        handUI.horizontallyCentering(0, GameView.GAME_WIDTH);
    }

    /**
     * Sets the next card(s) to be free.
     *
     * @param times   The number of free cards to grant.
     * @param current The ability granting the free card effect.
     */
    public void setFreeCard(int times, CardAbility current) {
        freeCardLeft = times;
        for (Card c : hand) {
            if (c.getAbility() != current) {
                c.setAP(0);
            }
        }
    }

    /**
     * Updates the free card status at the end of a turn or after a card is used.
     *
     * @param isEndTurn True if the turn is ending, false otherwise.
     */
    public void updateFreeCard(boolean isEndTurn) {
        if (freeCardLeft > 0 || isEndTurn) {
            freeCardLeft--;
            if (isEndTurn) freeCardLeft = 0;
            if (freeCardLeft == 0) {
                for (Card c : allCards) {
                    c.resetAP();
                }
            }
        }
    }

    private void recycleDiscardPile() {
        resetCardValues(discardPile);
        drawPile.addAll(discardPile);
        discardPile.clear();
        shuffle();
    }

    private void resetCardValues(List<Card> cards) {
        for (Card c : cards) {
            c.getAbility().resetValue();
        }
    }

    public Card getSelectedCard() {
        return handUI.getSelectedCard();
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getHandSize() {
        return handSize;
    }

    public void setHandSize(int handSize) {
        this.handSize = handSize;
    }

    public ArrayList<Card> getAllCards() {
        return allCards;
    }

    public HandUIHandler getHandUI() {
        return handUI;
    }
}
