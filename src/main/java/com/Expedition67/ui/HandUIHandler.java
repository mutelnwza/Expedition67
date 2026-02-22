package com.Expedition67.ui;

import com.Expedition67.card.Card;
import com.Expedition67.core.graphics.GameView;
import com.Expedition67.storage.AssetManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class HandUIHandler implements GameComponent {

    private final ArrayList<Card> hand;
    private int selectedCardIndex = -1;
    private int mouseOverIndex = -1;
    private int[][] handPos;

    private final int CARD_WIDTH = 150;
    private final int CARD_HEIGHT = 200;

    private boolean isVisible;

    public HandUIHandler(ArrayList<Card> hand) {
        this.hand = hand;
        horizontallyCentering(0, GameView.GAME_WIDTH);
    }

    @Override
    public void horizontallyCentering(int x, int w) {
        int cardAmount = hand.size();
        handPos = new int[cardAmount][2];

        if (cardAmount == 0) {
            return;
        }

        int space = 30;
        int totalWidth = (cardAmount * CARD_WIDTH) + ((cardAmount - 1) * space);
        int startX = x + (w - totalWidth) / 2;
        for (int i = 0; i < cardAmount; i++) {
            handPos[i][0] = startX + (i * (CARD_WIDTH + space));
            int y = 40;
            handPos[i][1] = y;
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
        if (!isVisible) {
            return;
        }

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
        int x = handPos[handCardIndex][0];
        int y = handPos[handCardIndex][1];
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
        if (!isVisible) {
            return false;
        }

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
        if (!isVisible) {
            return false;
        }

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
}
