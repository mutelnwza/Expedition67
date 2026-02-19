package com.Expedition67.states;

import com.Expedition67.core.GameManager;
import com.Expedition67.core.GameView;
import com.Expedition67.ui.GameButton;
import com.Expedition67.ui.GameText;

import java.awt.Color;
import java.awt.Graphics;

public class CombatState extends GameState {

    public static final int MONSTER_ROOM = 0;
    public static final int FINAL_BOSS_ROOM = 1;

    // Direct references for dynamic updates
    private GameText roomTimeText;
    private GameText enemyTypeText; // Temp
    private GameText intentText;
    private GameButton winButton; // Temp

    public CombatState() {
        super();
    }

    @Override
    protected void loadComponents() {
        // Info text
        roomTimeText = new GameText("Room: 0  Time: 00:00", 400, 740, 24f, Color.white);
        gameComponents.add(roomTimeText);

        // Win Button (Temp)
        winButton = new GameButton("Win", 24f, 800, 700, 100, 100, null);
        gameComponents.add(winButton);

        // Lose Button (Temp)
        gameComponents.add(new GameButton("Lose", 24f, 800, 820, 100, 100, () -> {
            GameManager.Instance().setCurrentState(GameManager.RESULT_STATE, ResultState.LOSE);
        }));

        // Enemy Type (Temp)
        enemyTypeText = new GameText("Monster", 270, 470, 150f, Color.white);
        gameComponents.add(enemyTypeText);

        intentText = new GameText("Intent: Attack 5 DMG", 270, 520, 24f, Color.red);
        gameComponents.add(intentText);
    }

    @Override
    public void enter(int id) {
        // Increment room count
        GameManager.Instance().setRoom(GameManager.Instance().getRoom() + 1);

        // Configure components based on the room type
        switch (id) {
            case MONSTER_ROOM:
                // Temp
                enemyTypeText.setText("Monster");
                enemyTypeText.setX(270);
                winButton.setOnClick(() -> {
                    GameManager.Instance().setCurrentState(GameManager.CARD_DROP_STATE, CardDropState.MONSTER_DROP);
                });
                break;

            case FINAL_BOSS_ROOM:
                // Temp
                enemyTypeText.setText("Final Boss");
                enemyTypeText.setX(230);
                winButton.setOnClick(() -> {
                    GameManager.Instance().setCurrentState(GameManager.RESULT_STATE, ResultState.WIN);
                });
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

        //Intent บนหน้าจอชั่วคราว
        intentText.setText("Intent: Attack 5 DMG");
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
