package com.Expedition67.unit;

import com.Expedition67.card.Card;
import com.Expedition67.storage.CardInventory;
import com.Expedition67.ui.GameComponent;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Deck implements GameComponent{
    private Stack<Card> drawPile;
    private ArrayList<Card> discardPile;
    private ArrayList<Card> hand;

    public Deck(){
        instantiate();
    }

    private void instantiate(){
        drawPile = new Stack<>();
        discardPile = new ArrayList<>();
        hand = new ArrayList<>();
        
        for(Card c : CardInventory.Instance().getCardInventory()){
            drawPile.add(new Card(c));
        }
        shuffle(drawPile);
        
    }

    private void shuffle(Stack<Card> cards){
        Collections.shuffle(cards);
    }

    public void addToHand(){
        for(int i=0; i<5; i++){
            hand.add(drawPile.removeLast());
            if(drawPile.isEmpty()) addToDraw();
        }
    }

    public void addToDraw(){
        while(!discardPile.isEmpty()){
            drawPile.add(discardPile.removeLast());
        }
        shuffle(drawPile);
    }

    @Override
    public void update() {
        
    }

    @Override
    public void render(Graphics g) {
        
    }

    @Override
    public boolean isInside(int x, int y) {
        return true;
    }

    @Override
    public boolean mouseClicked(MouseEvent e) {
        return true;
    }

    @Override
    public boolean mouseMoved(MouseEvent e) {
        return true;
    }
}
