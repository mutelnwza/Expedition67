package com.Expedition67.states;

import com.Expedition67.card.Card;
import com.Expedition67.core.GameManager;
import com.Expedition67.core.GameStateManager;
import com.Expedition67.core.graphics.GameView;
import com.Expedition67.core.util.GameRandom;
import com.Expedition67.storage.AssetManager;
import com.Expedition67.storage.CardInventory;
import com.Expedition67.ui.GameButton;
import com.Expedition67.ui.GameText;

import java.awt.*;

/**
 * The game state for when the player receives a new card.
 */
public class CardDropState extends GameState {

    public static final int NORMAL_DROP = 0;
    public static final int MINIBOSS_DROP = 1;
    public static final int TREASURE_ROOM = 2;

    private Card cardDrop;
    private GameText roomTimeText, messageText, cardInfoText, nextActionText;
    private GameButton leftButton, rightButton;
    private boolean isNextRoomState;

    public CardDropState() {
        super();
    }

    @Override
    protected void loadComponents() {
        roomTimeText = new GameText("Room: 0  Time: 00.00", 0, 80, 24f, Color.white);
        gameComponents.add(roomTimeText);

        messageText = new GameText("Placeholder", 0, 125, 50f, Color.white);
        gameComponents.add(messageText);

        cardInfoText = new GameText("Placeholder", 0, 0, 24f, Color.white);
        gameComponents.add(cardInfoText);

        nextActionText = new GameText("Now you gonna...", 0, 700, 50f, Color.white);
        nextActionText.horizontallyCentering(0, GameView.GAME_WIDTH);
        gameComponents.add(nextActionText);

        leftButton = new GameButton("Placeholder", 24f, 100, 745, 350, 50, null);
        gameComponents.add(leftButton);

        rightButton = new GameButton("Placeholder", 24f, 510, 745, 350, 50, null);
        gameComponents.add(rightButton);

        GameButton inventoryButton = new GameButton("Inventory", 24f, 0, 820, 200, 50, () ->
                GameManager.Instance().getGameStateManager().setCurrentState(GameStateManager.INVENTORY_STATE, InventoryState.ENTER_FROM_DROP)
        );
        inventoryButton.horizontallyCentering(0, GameView.GAME_WIDTH);
        gameComponents.add(inventoryButton);
    }

    @Override
    public void enter(int id) {
        if (id == TREASURE_ROOM) {
            GameManager.Instance().getGameData().incrementRoom();
            messageText.setText("Lucky! You found the Treasure Room. You got...");
            cardDrop = GameRandom.Instance().getRandomCard(null);
        } else {
            messageText.setText("Monster drop you...");
            cardDrop = (id == NORMAL_DROP) ? GameRandom.Instance().getRandomCard(Card.CardTier.NORMAL) : GameRandom.Instance().getRandomCard(Card.CardTier.RARE);
        }

        messageText.horizontallyCentering(0, GameView.GAME_WIDTH);
        setGetCardUI();
    }

    @Override
    public void exit() {
    }

    @Override
    public void update() {
        super.update();
        roomTimeText.setText(String.format("Room: %d  Time: %s", GameManager.Instance().getRoom(), GameManager.Instance().getTimeString()));
        roomTimeText.horizontallyCentering(0, GameView.GAME_WIDTH);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, GameView.GAME_WIDTH, GameView.GAME_HEIGHT);

        if (cardDrop != null) {
            int width = 300;
            int height = 400;
            int x = (GameView.GAME_WIDTH - width) / 2;
            int y = 180;
            g.drawImage(AssetManager.Instance().getCard(cardDrop.getName()), x, y, width, height, null);
        }

        if (!isNextRoomState) {
            g.setColor(Color.white);
            g.drawRect(185, 600, 590, 110);
        }

        super.render(g);
    }

    private void obtainDropCard() {
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
