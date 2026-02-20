package com.Expedition67.unit;

import com.Expedition67.card.Card;
import com.Expedition67.card.CardAbility;
import com.Expedition67.core.GameView;
import com.Expedition67.storage.AssetManager;
import com.Expedition67.storage.CardInventory;
import com.Expedition67.ui.GameComponent;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck implements GameComponent {

    private Stack<Card> drawPile;
    private ArrayList<Card> discardPile;
    private ArrayList<Card> hand;
    private int handSize = 5;

    private int selectedCardIndex = -1;
    private int mouseOverIndex = -1;

    private int[][] handPos;

    private final int CARD_Y = 80;
    private final int CARD_WIDTH = 200;
    private final int CARD_HEIGHT = 200;
    private final int CARD_SPACING = -30;

    private boolean isVisible;

    public Deck() {
    }

    public void instantiate() {
        drawPile = new Stack<>();
        discardPile = new ArrayList<>();
        hand = new ArrayList<>();

        for (Card c : CardInventory.Instance().getCardInventory()) {
            drawPile.add(c.copy());
        }

        shuffle();
        horizontallyCentering(0, GameView.GAME_WIDTH);

        isVisible = true;
    }

    public void shuffle() {
        Collections.shuffle(drawPile);
    }

    public void reshuffle() {
        drawPile.addAll(hand);
        drawPile.addAll(discardPile);

        hand.clear();
        discardPile.clear();

        shuffle();

        selectedCardIndex = -1;
        mouseOverIndex = -1;

        addToHand();
    }

    public void removeFromHand(Card c) {
        hand.remove(c);
        selectedCard = -1;
        horizontallyCentering(0, GameView.GAME_WIDTH);
    }

    public void addToHand() {
        discardPile.addAll(hand);
        hand.clear();

        while (hand.size() < handSize) {
            if (drawPile.isEmpty()) {
                if (discardPile.isEmpty()) {
                    break;
                }
                recycleDiscardPile();
            }
            hand.add(drawPile.pop());
        }
        horizontallyCentering(0, GameView.GAME_WIDTH);
    }

    public void recycleDiscardPile() {
        drawPile.addAll(discardPile);
        discardPile.clear();
        shuffle();
    }

    public void discardHand() {
        discardPile.addAll(hand);
        hand.clear();
        selectedCardIndex = -1;
        horizontallyCentering(0, GameView.GAME_WIDTH);
    }

    public void useCard(Card card) {
        if (hand.contains(card)) {
            hand.remove(card);
            discardPile.add(card);
            selectedCardIndex = -1;
            horizontallyCentering(0, GameView.GAME_WIDTH);
        }
    }

    public void addCard(Card card) {
        drawPile.add(card);
    }

    public Card getRandomCardFromHand(CardAbility.CardType type) {
        List<Card> matches = hand.stream()
                .filter(c -> c.getAbility().getCardType() == type)
                .toList();

        if (matches.isEmpty()) {
            return null;
        }

        return matches.get(new java.util.Random().nextInt(matches.size()));
    }

    public Card getRandomCardFromHand() {
        List<Card> matches = hand.stream()
                .filter(c -> c.isLocked() != true)
                .toList();

        if (matches.isEmpty()) {
            return null;
        }

        return matches.get(new java.util.Random().nextInt(matches.size()));
    }

    public Card getSelectedCard() {
        if (hand.isEmpty() || selectedCardIndex < 0 || selectedCardIndex >= hand.size()) return null;
        return hand.get(selectedCardIndex);
    }

    @Override
    public void horizontallyCentering(int x, int w) {
        int cardAmount = hand.size();
        handPos = new int[cardAmount][2];

        if (cardAmount == 0) {
            return;
        }

        int totalWidth = (cardAmount * CARD_WIDTH) + ((cardAmount - 1) * CARD_SPACING);
        int startX = x + (w - totalWidth) / 2;
        for (int i = 0; i < cardAmount; i++) {
            handPos[i][0] = startX + (i * (CARD_WIDTH + CARD_SPACING));
            handPos[i][1] = CARD_Y;
        }
    }

    @Override
    public void verticallyCentering(int y, int h) {
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
        if (!isVisible) return;

        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i);
            g.drawImage(AssetManager.Instance().getCard(card.getName()), handPos[i][0], handPos[i][1], CARD_WIDTH, CARD_HEIGHT, null);
            if (i == selectedCardIndex || i == mouseOverIndex) {
                renderSelected(g, i);
            }
        }
    }

    private void renderSelected(Graphics g, int handCardIndex) {
        Graphics2D g2 = (Graphics2D) g;
        Stroke oldStroke = g2.getStroke();

        g2.setColor(Color.YELLOW);
        g2.setStroke(new BasicStroke(3));

        g2.drawRect(handPos[handCardIndex][0], handPos[handCardIndex][1], CARD_WIDTH, CARD_HEIGHT);

        g2.setStroke(oldStroke);
    }

    @Override
    public boolean isInside(int x, int y) {
        return false;
    }

    public boolean isInside(int x, int y, int handCardIndex) {
        return (x >= handPos[handCardIndex][0] && x <= handPos[handCardIndex][0] + CARD_WIDTH
                && y >= handPos[handCardIndex][1] && y <= handPos[handCardIndex][1] + CARD_HEIGHT);
    }

    @Override
    public boolean mouseClicked(MouseEvent e) {
        if (!isVisible) return false;

        for (int i = 0; i < hand.size(); i++) {
            if (isInside(e.getX(), e.getY(), i)) {
                selectedCardIndex = i;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean mouseMoved(MouseEvent e) {
        if (!isVisible) return false;

        for (int i = 0; i < hand.size(); i++) {
            if (isInside(e.getX(), e.getY(), i)) {
                mouseOverIndex = i;
                return true;
            }
        }
        mouseOverIndex = -1;
        return false;
    }

    @Override
    public void setVisible(boolean visible) {
        isVisible = visible;
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
}
