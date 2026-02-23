package com.Expedition67.ui;

import com.Expedition67.card.Card;
import com.Expedition67.core.SoundManager;
import com.Expedition67.core.graphics.GameView;
import com.Expedition67.storage.AssetManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Handles the UI for the player's hand of cards.
 */
public class HandUIHandler implements GameComponent {

    private static final int CARD_WIDTH = 150;
    private static final int CARD_HEIGHT = 200;
    private static final int CARD_SPACING = 30;
    private static final int CARD_Y_POSITION = 40;

    private final ArrayList<Card> hand;
    private int[][] handCardPositions;
    private int selectedCardIndex = -1;
    private int mouseOverIndex = -1;
    private boolean isVisible = true;

    /**
     * Constructs a new HandUIHandler.
     *
     * @param hand The list of cards in the player's hand.
     */
    public HandUIHandler(ArrayList<Card> hand) {
        this.hand = hand;
        recalculateCardPositions(0, GameView.GAME_WIDTH);
    }

    @Override
    public void render(Graphics g) {
        if (!isVisible) return;

        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i);
            int cardX = handCardPositions[i][0];
            int cardY = handCardPositions[i][1];

            g.drawImage(AssetManager.Instance().getCard(card.getName()), cardX, cardY, CARD_WIDTH, CARD_HEIGHT, null);

            if (i == selectedCardIndex || i == mouseOverIndex) {
                renderHighlight(g, i);
            }
        }
    }

    @Override
    public boolean mouseClicked(MouseEvent e) {
        if (!isVisible) return false;

        for (int i = 0; i < hand.size(); i++) {
            if (isInside(e.getX(), e.getY(), i)) {
                selectedCardIndex = (selectedCardIndex == i) ? -1 : i;
                SoundManager.Instance().playSelectSound();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean mouseMoved(MouseEvent e) {
        if (!isVisible) return false;

        int currentlyOver = -1;
        for (int i = 0; i < hand.size(); i++) {
            if (isInside(e.getX(), e.getY(), i)) {
                currentlyOver = i;
                break;
            }
        }

        if (mouseOverIndex != currentlyOver) {
            mouseOverIndex = currentlyOver;
            return true;
        }
        return false;
    }

    @Override
    public void horizontallyCentering(int x, int w) {
        recalculateCardPositions(x, w);
    }

    @Override
    public void verticallyCentering(int y, int h) {
    }

    @Override
    public void update() {
    }

    @Override
    public boolean isInside(int x, int y) {
        return false;
    }

    public boolean isInside(int x, int y, int handCardIndex) {
        if (handCardIndex < 0 || handCardIndex >= handCardPositions.length) {
            return false;
        }
        int cardX = handCardPositions[handCardIndex][0];
        int cardY = handCardPositions[handCardIndex][1];
        return (x >= cardX && x <= cardX + CARD_WIDTH && y >= cardY && y <= cardY + CARD_HEIGHT);
    }

    @Override
    public void setVisible(boolean visible) {
        this.isVisible = visible;
        if (!visible) {
            resetSelection();
        }
    }

    public Card getSelectedCard() {
        if (hand.isEmpty() || selectedCardIndex < 0 || selectedCardIndex >= hand.size()) {
            return null;
        }
        return hand.get(selectedCardIndex);
    }

    public void resetSelection() {
        selectedCardIndex = -1;
        mouseOverIndex = -1;
    }

    private void recalculateCardPositions(int x, int w) {
        int cardAmount = hand.size();
        handCardPositions = new int[cardAmount][2];
        if (cardAmount == 0) return;

        int totalWidth = (cardAmount * CARD_WIDTH) + ((cardAmount - 1) * CARD_SPACING);
        int startX = x + (w - totalWidth) / 2;

        for (int i = 0; i < cardAmount; i++) {
            handCardPositions[i][0] = startX + (i * (CARD_WIDTH + CARD_SPACING));
            handCardPositions[i][1] = CARD_Y_POSITION;
        }
    }

    private void renderHighlight(Graphics g, int handCardIndex) {
        Graphics2D g2 = (Graphics2D) g;
        int x = handCardPositions[handCardIndex][0];
        int y = handCardPositions[handCardIndex][1];
        int cornerSize = 20;
        Stroke oldStroke = g2.getStroke();

        g2.setColor(Color.YELLOW);
        g2.setStroke(new BasicStroke(3));

        g2.drawLine(x, y, x + cornerSize, y);
        g2.drawLine(x, y, x, y + cornerSize);
        g2.drawLine(x + CARD_WIDTH, y, x + CARD_WIDTH - cornerSize, y);
        g2.drawLine(x + CARD_WIDTH, y, x + CARD_WIDTH, y + cornerSize);
        g2.drawLine(x, y + CARD_HEIGHT, x + cornerSize, y + CARD_HEIGHT);
        g2.drawLine(x, y + CARD_HEIGHT, x, y + CARD_HEIGHT - cornerSize);
        g2.drawLine(x + CARD_WIDTH, y + CARD_HEIGHT, x + CARD_WIDTH - cornerSize, y + CARD_HEIGHT);
        g2.drawLine(x + CARD_WIDTH, y + CARD_HEIGHT, x + CARD_WIDTH, y + CARD_HEIGHT - cornerSize);

        g2.setStroke(oldStroke);
    }
}
