package com.Expedition67.states;

import com.Expedition67.card.Card;
import com.Expedition67.core.GameManager;
import com.Expedition67.core.GameStateManager;
import com.Expedition67.core.graphics.GameView;
import com.Expedition67.storage.AssetManager;
import com.Expedition67.storage.CardInventory;
import com.Expedition67.ui.GameButton;
import com.Expedition67.ui.GameText;

import java.awt.*;
import java.util.ArrayList;

/**
 * The game state for viewing the player's card inventory.
 */
public class InventoryState extends GameState {

    public static final int ENTER_FROM_COMBAT = 0;
    public static final int ENTER_FROM_DROP = 1;

    private GameButton previousCardButton, nextCardButton;
    private GameText cardInfoText;

    private int previousState;
    private ArrayList<Card> inventory;
    private int showedCardIndex;
    private Card showedCard;

    public InventoryState() {
        super();
    }

    @Override
    protected void loadComponents() {
        previousCardButton = new GameButton("<", 24f, 40, 0, 40, 40, this::showPreviousCard);
        previousCardButton.verticallyCentering(0, GameView.GAME_HEIGHT);
        gameComponents.add(previousCardButton);

        nextCardButton = new GameButton(">", 24f, 880, 0, 40, 40, this::showNextCard);
        nextCardButton.verticallyCentering(0, GameView.GAME_HEIGHT);
        gameComponents.add(nextCardButton);

        cardInfoText = new GameText("Placeholder", 0, 0, 24f, Color.white);
        gameComponents.add(cardInfoText);

        gameComponents.add(new GameButton("Back", 24f, 20, 20, 60, 40, () ->
                GameManager.Instance().getGameStateManager().setCurrentState(previousState, 0)
        ));
    }

    @Override
    public void enter(int id) {
        previousState = (id == ENTER_FROM_COMBAT) ? GameStateManager.COMBAT_STATE : GameStateManager.CARD_DROP_STATE;
        inventory = CardInventory.Instance().getCardInventory();
        showedCardIndex = 0;
        updateDisplayedCard();
    }

    @Override
    public void exit() {
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, GameView.GAME_WIDTH, GameView.GAME_HEIGHT);

        if (showedCard != null) {
            int width = 300;
            int height = 400;
            int x = (GameView.GAME_WIDTH - width) / 2;
            int y = (GameView.GAME_HEIGHT - height) / 2;
            g.drawImage(AssetManager.Instance().getCard(showedCard.getName()), x, y, width, height, null);
        }

        g.setColor(Color.white);
        g.drawRect(185, 770, 590, 110);

        super.render(g);
    }

    private void showPreviousCard() {
        if (showedCardIndex > 0) {
            showedCardIndex--;
            updateDisplayedCard();
        }
    }

    private void showNextCard() {
        if (showedCardIndex < inventory.size() - 1) {
            showedCardIndex++;
            updateDisplayedCard();
        }
    }

    private void updateDisplayedCard() {
        if (inventory.isEmpty()) {
            showedCard = null;
            cardInfoText.setText("Inventory is empty");
            previousCardButton.setVisible(false);
            nextCardButton.setVisible(false);
        } else {
            showedCard = inventory.get(showedCardIndex);
            cardInfoText.setText(showedCard.toString());
            previousCardButton.setVisible(showedCardIndex > 0);
            nextCardButton.setVisible(showedCardIndex < inventory.size() - 1);
        }
        cardInfoText.horizontallyCentering(180, 590);
        cardInfoText.verticallyCentering(770, 110);
    }
}
