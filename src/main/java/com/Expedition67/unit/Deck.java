package com.Expedition67.unit;

import com.Expedition67.card.Card;
import com.Expedition67.core.GameManager;
import com.Expedition67.storage.CardInventory;
import com.Expedition67.ui.GameComponent;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Deck implements GameComponent {

    private Stack<Card> drawPile;
    private ArrayList<Card> discardPile;
    private ArrayList<Card> hand;
    private int handSize;

    //mock up
    private final int CARD_WIDTH = 120;
    private final int CARD_HEIGHT = 180;
    private final int SPACING = 130;
    private final int HAND_Y = 750;

    // Calculated state
    private int currentStartX;
    private int hoveredIndex = -1;

    public Deck() {
        instantiate();
    }

    private void instantiate() {
        drawPile = new Stack<>();
        discardPile = new ArrayList<>();
        hand = new ArrayList<>();
        handSize = 5;

        for (Card c : CardInventory.Instance().getCardInventory()) {
            drawPile.add(new Card(c));
        }
        shuffle(drawPile);

    }

    private void shuffle(Stack<Card> cards) {
        Collections.shuffle(cards);
    }

    // add zone
    public void addCardToDeck(Card card) {
        drawPile.add(card);
        shuffle(drawPile);
    }

    public void addCardToHand() {
        while (hand.size() < handSize) {
            if (drawPile.isEmpty()) {
                addCardToDraw();
            }
            hand.add(drawPile.pop());
            onHandChanged();
        }
    }

    public void addCardToDraw() {
        while (!discardPile.isEmpty()) {
            drawPile.add(discardPile.removeLast());
        }
        shuffle(drawPile);
    }

    //
    public void clearHand() {
        while (!hand.isEmpty()) {
            Card c = hand.removeLast();
            if (c.isLocked()) {
                c.setLocked(false);
            }
            discardPile.add(c);
            onHandChanged();
        }
    }

    public void removeCard(Card card) {
        hand.remove(card);
        onHandChanged();
    }

    //make some cards in hand cant be play this turn
    public void lockCard(int amount) {
        int limit = Math.min(amount, hand.size());

        for (int i = 0; i < limit; i++) {
            hand.get(i).setLocked(true);
        }
    }
    //

    //call after click on a card, tell combatmanager to use
    public void onUseCard(Card card, Unit target) {
        //CombatManager.Instance().onPlayerUseCard(card,target)
        //GameManager.Instance().getPlayerBrain().useCard(card, target);
        hand.remove(card);
        discardPile.add(card);
    }

    //call after a card in hand is updated to recalculate the position
    public void onHandChanged() {
        this.currentStartX = calculateCardX(0);
    }

    public int calculateCardX(int index) {
        int startX = (960 / 2) - ((hand.size() * SPACING) / 2);
        return startX + (index * SPACING);
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
        int mx = e.getX();
        int my = e.getY();

        for (int i = hand.size() - 1; i >= 0; i--) {
            int currentX = currentStartX + (i * SPACING);

            if (mx >= currentX && mx <= currentX + CARD_WIDTH
                    && my >= HAND_Y && my <= HAND_Y + CARD_HEIGHT) {

                Card c = hand.get(i);
                if (c.isLocked() || !c.canUse(GameManager.Instance().getPlayer().getBrain()) /* || combatManager.getCurrentTarget() == null*/) {
                    return true;
                }

                //Unit target = combatManager.getCurrentTarget();
                //onUseCard(c, target);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        hoveredIndex = -1; // Reset

        for (int i = hand.size() - 1; i >= 0; i--) {
            int currentX = currentStartX + (i * SPACING);
            if (mx >= currentX && mx <= currentX + CARD_WIDTH
                    && my >= HAND_Y && my <= HAND_Y + CARD_HEIGHT) {
                hoveredIndex = i;
                return true;
            }
        }
        return false;
    }

    public void setHandSize(int n){
        handSize += n;
    }

    //getter
    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getHandY() {
        return HAND_Y;
    }

    public int getCardWidth() {
        return CARD_WIDTH;
    }

    public int getCardHeight() {
        return CARD_HEIGHT;
    }

    public int getHoveredIndex() {
        return hoveredIndex;
    }

    public int getSpacing() {
        return SPACING;
    }
}
