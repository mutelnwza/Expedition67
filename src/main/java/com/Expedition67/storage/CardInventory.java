package com.Expedition67.storage;

import com.Expedition67.card.Card;

import java.util.ArrayList;

public class CardInventory {

    private static CardInventory instance;
    private ArrayList<Card> cards;

    private CardInventory() {
        cards = new ArrayList<>();
    }

    ;

    public static CardInventory Instance() {
        if (instance == null) {
            instance = new CardInventory();
        }
        return instance;
    }

    public void addCard(Card c, int amount) {
        for (int i = 0; i < amount; i++) cards.add(c.copy());
    }

    public void removeCard(Card c) {
        cards.remove(c);
    }

    public void removeCard(String c) {
        for (Card card : cards) {
            if (c.equals(card.getName())) removeCard(c);
        }
    }

    public void emptyInventory() {
        cards.clear();
    }

    public ArrayList<Card> getCardInventory() {
        return cards;
    }

}
