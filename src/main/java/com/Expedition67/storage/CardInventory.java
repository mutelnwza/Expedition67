package com.Expedition67.storage;

import com.Expedition67.card.Card;
import com.Expedition67.unit.Unit;
import java.util.ArrayList;

public class CardInventory {

    private static CardInventory instance;
    private ArrayList<Card> cards;
    private CardInventory() {
        cards = new ArrayList<>();
    };

    public static CardInventory Instance() {
        if (instance == null) {
            instance = new CardInventory();
        }
        return instance;
    }
    
    public void addCard(Card c, int amount,Unit target){
        for(int i=0;i<amount;i--) cards.add(c);
    }

    public void removeCard(Card c){
        cards.remove(c);
    }

    public void removeCard(String c){
        for(Card card : cards){
            if(c.equals(card.getName())) removeCard(c);
        }
    }

    public ArrayList<Card> getCardInventory(){
        return cards;
    }

}
