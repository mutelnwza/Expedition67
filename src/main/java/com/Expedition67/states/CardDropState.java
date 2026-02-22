package com.Expedition67.states;

import com.Expedition67.card.Card;
import com.Expedition67.core.GameManager;
import com.Expedition67.core.util.GameRandom;
import com.Expedition67.core.GameStateManager;
import com.Expedition67.core.graphics.GameView;
import com.Expedition67.storage.AssetManager;
import com.Expedition67.storage.CardInventory;
import com.Expedition67.ui.GameButton;
import com.Expedition67.ui.GameText;

import java.awt.*;

public class CardDropState extends GameState {

    public static final int NORMAL_DROP = 0;
    public static final int MINIBOSS_DROP = 1;
    public static final int TREASURE_ROOM = 2;

    private Card cardDrop;

    // Direct references for dynamic updates
    private GameText roomTimeText;
    private GameText messageText;
    private GameText cardInfoText;
    private GameText nextActionText;
    private GameButton leftButton;
    private GameButton rightButton;

    private boolean isNextRoomState;

    private final int CARD_DROP_WIDTH = 300;
    private final int CARD_DROP_HEIGHT = 400;
    private final int CARD_DROP_X = (GameView.GAME_WIDTH - CARD_DROP_WIDTH) / 2;
    private final int CARD_DROP_Y = 180;

    public CardDropState() {
        super();
    }

    @Override
    protected void loadComponents() {
        // Info HUD
        roomTimeText = new GameText("Room: 0  Time: 00.00", 0, 80, 24f, Color.white);
        gameComponents.add(roomTimeText);

        // Message Text
        messageText = new GameText("Placeholder", 0, 125, 50f, Color.white);
        gameComponents.add(messageText);

        // Card Info
        cardInfoText = new GameText("Placeholder", 0, 0, 24f, Color.white);
        gameComponents.add(cardInfoText);

        // Next Action Text
        nextActionText = new GameText("Now you gonna...", 0, 700, 50f, Color.white);
        gameComponents.add(nextActionText);
        nextActionText.horizontallyCentering(0, GameView.GAME_WIDTH);

        // Left Button
        leftButton = new GameButton("Placeholder", 24f, 100, 745, 350, 50, null);
        gameComponents.add(leftButton);

        // Right Button
        rightButton = new GameButton("Placeholder", 24f, 510, 745, 350, 50, null);
        gameComponents.add(rightButton);

        // Inventory
        gameComponents.add(new GameButton("Inventory", 24f, 0, 820, 200, 50, () -> {
            GameManager.Instance().getGameStateManager().setCurrentState(GameStateManager.INVENTORY_STATE, InventoryState.ENTER_FROM_DROP);
        }));
        gameComponents.getLast().horizontallyCentering(0, GameView.GAME_WIDTH);
    }

    @Override
    public void enter(int id) {
        if (id == TREASURE_ROOM) {
            GameManager.Instance().getGameData().incrementRoom();

            messageText.setText("Lucky! You found the Treasure Room. You got...");
            cardDrop = GameRandom.Instance().getRandomCard(null);
        } else {
            messageText.setText("Monster drop you...");
            if (id == NORMAL_DROP) cardDrop = GameRandom.Instance().getRandomCard(Card.CardTier.NORMAL);
            else cardDrop = GameRandom.Instance().getRandomCard(Card.CardTier.RARE);
        }

        messageText.horizontallyCentering(0, GameView.GAME_WIDTH);
        setGetCardUI();
    }

    @Override
    public void exit() {
    }

    @Override
    public void update() {
        // Update the HUD with current room and time
        roomTimeText.setText(String.format("Room: %d  Time: %s", GameManager.Instance().getRoom(), GameManager.Instance().getTimeString()));
        roomTimeText.horizontallyCentering(0, GameView.GAME_WIDTH);

        super.update();
    }

    @Override
    public void render(Graphics g) {
        // Draw Background
        g.setColor(Color.black);
        g.fillRect(0, 0, GameView.GAME_WIDTH, GameView.GAME_HEIGHT);

        // Draw Card
        if (cardDrop != null) {
            g.drawImage(AssetManager.Instance().getCard(cardDrop.getName()), CARD_DROP_X, CARD_DROP_Y, CARD_DROP_WIDTH, CARD_DROP_HEIGHT, null);
        }

        // Card info border
        if (!isNextRoomState) {
            g.setColor(Color.white);
            g.drawRect(185, 600, 590, 110);
        }

        // Draw components
        super.render(g);
    }

    private void obtainDropCard() {
        Card alreadyOwnCard = CardInventory.Instance().getCardInventory().stream()
                .filter(c -> c.getName().equals(cardDrop.getName()))
                .findFirst()
                .orElse(null);
        if (alreadyOwnCard != null)
            alreadyOwnCard.addUsesLeft(alreadyOwnCard.getDefaultUsesAmount());
        else
            CardInventory.Instance().addCard(cardDrop, 1);
        setNextActionUI();
    }

    private void discardDropCard() {
        setNextActionUI();
    }

    private void setGetCardUI() {
        isNextRoomState = false;
        cardInfoText.setVisible(true);
        nextActionText.setVisible(false);

        cardInfoText.setText(cardDrop.toString());
        cardInfoText.horizontallyCentering(180, 590);
        cardInfoText.verticallyCentering(600, 110);

        leftButton.setLabel("Obtain");
        leftButton.setOnClick(this::obtainDropCard);

        rightButton.setLabel("Discard");
        rightButton.setOnClick(this::discardDropCard);
    }

    private void setNextActionUI() {
        isNextRoomState = true;
        cardInfoText.setVisible(false);
        nextActionText.setVisible(true);

        leftButton.setLabel("Go into next room");
        leftButton.setOnClick(GameRandom.Instance()::enterRandomRoom);

        rightButton.setLabel("Challenge the boss");
        rightButton.setOnClick(() -> GameManager.Instance().getGameStateManager().setCurrentState(GameStateManager.COMBAT_STATE, CombatState.BOSS_ROOM));
    }
}
