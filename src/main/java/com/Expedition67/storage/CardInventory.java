package com.Expedition67.storage;

import java.util.ArrayList;

public class CardInventory {

    private static CardInventory instance;
    private ArrayList<Integer> cards;
    private CardInventory() {
        cards = new ArrayList<>();
    };

    public static CardInventory Instance() {
        if (instance == null) {
            instance = new CardInventory();
        }
        return instance;
    }
    
    public void addCard(int c, int amount){
        for(int i=0;i<amount;i--) cards.add(c);
    }

    public void removeCard(int c){
        cards.remove((Integer)c);
    }

    public void removeCard(String c){
        for(int i : cards){
            if(i==Integer.parseInt(c)) removeCard(i);
        }
    }

    public ArrayList<Integer> getCardInventory(){
        return cards;
    }

}
