package com.Expedition67.unit;

import com.Expedition67.card.Card;
import com.Expedition67.storage.AssetManager;
import com.Expedition67.storage.CardInventory;
import com.Expedition67.ui.GameComponent;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Deck implements GameComponent {
    private Stack<Card> drawPile;
    private ArrayList<Card> discardPile;
    private ArrayList<Card> hand;

    private int selectedCard;
    private int mouseOver;

    private final int[][] HAND_POS = {
            {50, 50}, {220, 50}, {390, 50}, {560, 50}, {730, 50}
    };
    private final int CARD_WIDTH = 200;
    private final int CARD_HEIGHT = 200;

    public Deck() {
        instantiate();
    }

    public void instantiate() {
        drawPile = new Stack<>();
        discardPile = new ArrayList<>();
        hand = new ArrayList<>();

        for (Card c : CardInventory.Instance().getCardInventory()) {
            drawPile.add(new Card(c));
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(drawPile);
    }

    public void addToHand() {
        while (hand.size() < 5) {
            if (drawPile.isEmpty()) {
                addToDraw();
                if (drawPile.isEmpty()) {
                    break;
                }
            }
            hand.add(drawPile.removeLast());
        }
    }

    public void addToDraw() {
        while (!discardPile.isEmpty()) {
            drawPile.add(discardPile.removeLast());
        }
        shuffle();
    }

    public void useCard(Card card) {
        if (hand.contains(card)) {
            hand.remove(card);
            discardPile.add(card);
            selectedCard = -1;
        }
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i);
            g.drawImage(AssetManager.Instance().getCard(card.getName()), HAND_POS[i][0], HAND_POS[i][1], CARD_WIDTH, CARD_HEIGHT, null);
            if (i == selectedCard || i == mouseOver) {
                renderSelected(g, i);
            }
        }
    }

    private void renderSelected(Graphics g, int handCardIndex) {
        Graphics2D g2 = (Graphics2D) g;
        Stroke oldStroke = g2.getStroke();

        g2.setColor(Color.YELLOW);
        g2.setStroke(new BasicStroke(3));

        g2.drawRect(HAND_POS[handCardIndex][0], HAND_POS[handCardIndex][1], CARD_WIDTH, CARD_HEIGHT);

        g2.setStroke(oldStroke);
    }

    @Override
    public boolean isInside(int x, int y) {
        return false;
    }

    public boolean isInside(int x, int y, int handCardIndex) {
        return (x >= HAND_POS[handCardIndex][0] && x <= HAND_POS[handCardIndex][0] + CARD_WIDTH
                && y >= HAND_POS[handCardIndex][1] && y <= HAND_POS[handCardIndex][1] + CARD_HEIGHT);
    }

    @Override
    public boolean mouseClicked(MouseEvent e) {
        for (int i = 0; i < hand.size(); i++) {
            if (isInside(e.getX(), e.getY(), i)) {
                selectedCard = i;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean mouseMoved(MouseEvent e) {
        for (int i = 0; i < hand.size(); i++) {
            if (isInside(e.getX(), e.getY(), i)) {
                mouseOver = i;
                return true;
            }
        }
        mouseOver = -1;
        return false;
    }

    public Card getSelectedCard() {
        if (selectedCard == -1) return null;
        return hand.get(selectedCard);
    }

    public boolean isHandEmpty() {
        return hand.isEmpty();
    }
}
