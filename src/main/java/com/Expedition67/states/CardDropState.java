package com.Expedition67.states;

import com.Expedition67.core.GameManager;
import com.Expedition67.core.GameView;
import com.Expedition67.ui.GameButton;
import com.Expedition67.ui.GameText;

import java.awt.Color;
import java.awt.Graphics;

public class CardDropState extends GameState {

    public static final int MONSTER_DROP = 0;
    public static final int TREASURE_ROOM = 1;
    private GameButton cardButton;
    private GameText nowText;


    // Direct references for dynamic updates
    private GameText roomTimeText;
    private GameText messageText;

    public CardDropState() {
        super();
    }

    @Override
    protected void loadComponents() {
        // Message Text
        messageText = new GameText("Placeholder", 200, 95, 50f, Color.white);
        gameComponents.add(messageText);

        // Info HUD
        roomTimeText = new GameText("Room: 0  Time: 00.00", 400, 830, 24f, Color.white);
        gameComponents.add(roomTimeText);

        // Next Room Button
        gameComponents.add(new GameButton("Go into the next room", 24f, 113, 715, 350, 50, () -> {
            // 15% chance to find a Treasure Room
            if (Math.random() > 1) {
                GameManager.Instance().setCurrentState(GameManager.COMBAT_STATE, CombatState.MONSTER_ROOM);
            } else {
                GameManager.Instance().setCurrentState(GameManager.CARD_DROP_STATE, CardDropState.TREASURE_ROOM);
            }
        }));

        // Challenge Boss Button
        gameComponents.add(new GameButton("Challenge the BOSS", 24f, 497, 715, 350, 50, () -> {
            GameManager.Instance().setCurrentState(GameManager.COMBAT_STATE, CombatState.FINAL_BOSS_ROOM);
        }));
        // Card Button 
        cardButton = new GameButton("Card", 30f, 360, 200, 250, 300, null);
        gameComponents.add(cardButton);
        // Now you gonna 
        nowText = new GameText("Now you gonna...", 340, 650, 50f, Color.white); 
        gameComponents.add(nowText);
    }

    @Override
    public void enter(int id) {
        switch (id) {
            case MONSTER_DROP:
                messageText.setText("Monster drop you...");
                messageText.setX(300);
                cardButton.setText("Card");
                nowText.setText("Now you gonna...");
                break;
            case TREASURE_ROOM:
                // Enter a treasure room counts as a new room visit
                GameManager.Instance().setRoom(GameManager.Instance().getRoom() + 1);
                messageText.setText("Lucky! You found the Treasure Room. You got...");
                messageText.setX(70);
                cardButton.setText("Card");
                nowText.setText("Now you gonna...");
                break;
        }
    }

    @Override
    public void exit() {
    }

    @Override
    public void update() {
        // Update the HUD with current room and time
        roomTimeText.setText(String.format("Room: %d  Time: %s", GameManager.Instance().getRoom(), GameManager.Instance().getTimeString()));
        super.update();
    }

    @Override
    public void render(Graphics g) {
        // Draw Background
        g.setColor(Color.black);
        g.fillRect(0, 0, GameView.GAME_WIDTH, GameView.GAME_HEIGHT);

        // Draw components
        super.render(g);
    }
}
