package com.Expedition67.unit.player;

import com.Expedition67.card.Card;
import com.Expedition67.core.graphics.GameView;
import com.Expedition67.storage.CardInventory;
import com.Expedition67.ui.HandUIHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {

    private ArrayList<Card> allCards;
    private Stack<Card> drawPile;
    private ArrayList<Card> discardPile;
    private final ArrayList<Card> hand;
    private final HandUIHandler handUI;
    private int handSize = 5;
    private int freeCardLeft = 0;

    public Deck() {
        hand = new ArrayList<>();
        handUI = new HandUIHandler(hand);
    }

    public void instantiate() {
        allCards = new ArrayList<>();
        drawPile = new Stack<>();
        discardPile = new ArrayList<>();
        hand.clear();

        for (Card c : CardInventory.Instance().getCardInventory()) {
            Card newCard = c.copy();
            drawPile.add(newCard);
            allCards.add(newCard);
        }

        shuffle();
        handUI.horizontallyCentering(0, GameView.GAME_WIDTH);
        handUI.setVisible(true);
    }

    public void shuffle() {
        Collections.shuffle(drawPile);
    }

    public void reshuffle() {
        resetCards(hand);
        resetCards(discardPile);

        drawPile.addAll(hand);
        drawPile.addAll(discardPile);

        hand.clear();
        discardPile.clear();

        shuffle();

        addToHand();
    }

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

    public void removeFromHand(Card c) {
        c.getAbility().resetValue();
        hand.remove(c);
        handUI.resetSelection();
        handUI.horizontallyCentering(0, com.Expedition67.core.graphics.GameView.GAME_WIDTH);
    }

    public void addToHand() {
        resetCards(hand);
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

    public void recycleDiscardPile() {
        resetCards(discardPile);
        drawPile.addAll(discardPile);
        discardPile.clear();
        shuffle();
    }

    public void useCard(Card card) {
        if (hand.contains(card)) {
            hand.remove(card);
            discardPile.add(card);
            freeCardLeft--;
            handUI.resetSelection();
            handUI.horizontallyCentering(0, GameView.GAME_WIDTH);
        }
    }

    private void resetCards(List<Card> cards) {
        for (Card c : cards) {
            c.getAbility().resetValue();
        }
    }

    public void addCard(Card card) {
        System.out.println("add card"+card.getName());
        drawPile.add(card);
        allCards.add(card);
    }

    public Card getSelectedCard() {
        return handUI.getSelectedCard();
    }

    public void setFreeCard(int times) {
        freeCardLeft = times;

        for (Card c : hand) {
            c.setAP(0);
        }
    }

    public void updateFreeCard(boolean isEndTurn) {
        if (freeCardLeft > 0 || isEndTurn) {
            freeCardLeft--;
            if(isEndTurn) freeCardLeft = 0;
            if (freeCardLeft == 0) {
                for (Card c : allCards) {
                    c.resetAP();
                }
            }
        }
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
