package com.Expedition67.states;

import com.Expedition67.card.Card;
import com.Expedition67.core.GameManager;
import com.Expedition67.core.GameView;
import com.Expedition67.storage.AssetManager;
import com.Expedition67.storage.CardInventory;
import com.Expedition67.storage.Warehouse;
import com.Expedition67.ui.GameButton;
import com.Expedition67.ui.GameText;

import java.awt.Color;
import java.awt.Graphics;

public class CardDropState extends GameState {

    public static final int MONSTER_DROP = 0;
    public static final int TREASURE_ROOM = 1;

    private Card cardDrop;

    // Direct references for dynamic updates
    private GameText roomTimeText;
    private GameText messageText;

    private final int CARD_DROP_WIDTH = 400;
    private final int CARD_DROP_HEIGHT = 400;
    private final int CARD_DROP_X = (GameView.GAME_WIDTH - CARD_DROP_WIDTH) / 2;
    private final int CARD_DROP_Y = 200;

    public CardDropState() {
        super();
    }

    @Override
    protected void loadComponents() {
        // Message Text
        messageText = new GameText("Placeholder", 0, 125, 50f, Color.white);
        gameComponents.add(messageText);

        gameComponents.add(new GameText("Now you gonna...", 340, 710, 50f, Color.white));
        gameComponents.getLast().horizontallyCentering(0, GameView.GAME_WIDTH);

        // Next Room Button
        gameComponents.add(new GameButton("Go into the next room", 24f, 100, 745, 350, 50, () -> {
            // 15% chance to find a Treasure Room
            if (Math.random() > 0.15) {
                GameManager.Instance().setCurrentState(GameManager.COMBAT_STATE, CombatState.MONSTER_ROOM);
            } else {
                GameManager.Instance().setCurrentState(GameManager.CARD_DROP_STATE, CardDropState.TREASURE_ROOM);
            }
        }));

        // Challenge Boss Button
        gameComponents.add(new GameButton("Challenge the BOSS", 24f, 510, 745, 350, 50, () -> {
            GameManager.Instance().setCurrentState(GameManager.COMBAT_STATE, CombatState.FINAL_BOSS_ROOM);
        }));

        // Info HUD
        roomTimeText = new GameText("Room: 0  Time: 00.00", 0, 840, 24f, Color.white);
        gameComponents.add(roomTimeText);
    }

    @Override
    public void enter(int id) {
        switch (id) {
            case MONSTER_DROP:
                messageText.setText("Monster drop you...");
                break;
            case TREASURE_ROOM:
                // Enter a treasure room counts as a new room visit
                GameManager.Instance().setRoom(GameManager.Instance().getRoom() + 1);
                messageText.setText("Lucky! You found the Treasure Room. You got...");
                break;
        }
        messageText.horizontallyCentering(0, GameView.GAME_WIDTH);
        cardDrop = Warehouse.Instance().spawnRandomCard();
        CardInventory.Instance().addCard(cardDrop, 1);
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

        // Draw components
        super.render(g);
    }
}
