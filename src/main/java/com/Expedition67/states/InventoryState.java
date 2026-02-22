package com.Expedition67.states;

import com.Expedition67.card.Card;
import com.Expedition67.core.GameManager;
import com.Expedition67.core.GameStateManager;
import com.Expedition67.core.graphics.GameView;
import com.Expedition67.storage.AssetManager;
import com.Expedition67.storage.CardInventory;
import com.Expedition67.ui.GameButton;
import com.Expedition67.ui.GameText;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class InventoryState extends GameState {

    public static final int ENTER_FROM_COMBAT = 0;
    public static final int ENTER_FROM_DROP = 1;

    private GameButton previousCard;
    private GameButton nextCard;
    private GameText cardInfoText;

    private int previousState;
    private ArrayList<Card> inventory;
    private int showedCardIndex;
    private Card showedCard;

    private final int CARD_WIDTH = 300;
    private final int CARD_HEIGHT = 400;
    private final int CARD_X = (GameView.GAME_WIDTH - CARD_WIDTH) / 2;
    private final int CARD_Y = (GameView.GAME_HEIGHT - CARD_HEIGHT) / 2;

    public InventoryState() {
        super();
    }

    @Override
    protected void loadComponents() {
        // Previous Card
        previousCard = new GameButton("<", 24f, 40, 0, 40, 40, this::previous);
        gameComponents.add(previousCard);
        previousCard.verticallyCentering(0, GameView.GAME_HEIGHT);

        // Next Card
        nextCard = new GameButton(">", 24f, 880, 0, 40, 40, this::next);
        gameComponents.add(nextCard);
        nextCard.verticallyCentering(0, GameView.GAME_HEIGHT);

        // Card Info
        cardInfoText = new GameText("Placeholder", 0, 0, 24f, Color.white);
        gameComponents.add(cardInfoText);

        // Back
        gameComponents.add(new GameButton("Back", 24f, 20, 20, 60, 40, () -> {
            GameManager.Instance().getGameStateManager().setCurrentState(previousState, 0);
        }));
    }

    @Override
    public void enter(int id) {
        if (id == ENTER_FROM_COMBAT)
            previousState = GameStateManager.COMBAT_STATE;
        else
            previousState = GameStateManager.CARD_DROP_STATE;

        inventory = CardInventory.Instance().getCardInventory();
        showedCardIndex = 0;
        showedCard = inventory.get(showedCardIndex);

        cardInfoText.setText(showedCard.toString());
        cardInfoText.horizontallyCentering(180, 590);
        cardInfoText.verticallyCentering(770, 110);

        previousCard.setVisible(false);
        nextCard.setVisible(true);
    }

    @Override
    public void exit() {
    }

    @Override
    public void render(Graphics g) {
        // Draw Background
        g.setColor(Color.black);
        g.fillRect(0, 0, GameView.GAME_WIDTH, GameView.GAME_HEIGHT);

        // Draw Card
        if (showedCard != null) {
            g.drawImage(AssetManager.Instance().getCard(showedCard.getName()), CARD_X, CARD_Y, CARD_WIDTH, CARD_HEIGHT, null);
        }

        // Card info border
        g.setColor(Color.white);
        g.drawRect(185, 770, 590, 110);

        // Draw components
        super.render(g);
    }

    private void previous() {
        showedCardIndex--;
        if (showedCardIndex < 0) return;
        showedCard = inventory.get(showedCardIndex);

        if (showedCardIndex == 0) previousCard.setVisible(false);
        nextCard.setVisible(true);

        cardInfoText.setText(showedCard.toString());
        cardInfoText.horizontallyCentering(180, 590);
        cardInfoText.verticallyCentering(770, 110);
    }

    private void next() {
        showedCardIndex++;
        if (showedCardIndex >= inventory.size()) return;
        showedCard = inventory.get(showedCardIndex);

        if (showedCardIndex == inventory.size() - 1) nextCard.setVisible(false);
        previousCard.setVisible(true);

        cardInfoText.setText(showedCard.toString());
        cardInfoText.horizontallyCentering(180, 590);
        cardInfoText.verticallyCentering(770, 110);
    }
}
