package com.Expedition67.states;

import com.Expedition67.core.GameManager;
import com.Expedition67.core.GameView;
import com.Expedition67.ui.GameButton;
import com.Expedition67.ui.GameText;
import com.Expedition67.card.Card;

import java.awt.Color;
import java.awt.Graphics;

public class CombatState extends GameState {

    public static final int MONSTER_ROOM = 0;
    public static final int FINAL_BOSS_ROOM = 1;
   

    // Direct references for dynamic updates
    private GameText roomTimeText;
    private GameText enemyTypeText; // Temp
    private GameText hpPlayerGameText;
     private GameText hpenemyGameText;
    private GameButton winButton; // Temp
    private GameButton useCardButton;
    private GameButton cardNameButton;
    private GameButton shffeButton;
    private GameButton endButton;
    private GameButton card1Button;
    private GameButton card2Button;
    private GameButton card3Button;
    private GameButton card4Button;
    private GameButton platerButton;
    private GameButton enemyButton;



    public CombatState() {
        super();
    }

    @Override
    protected void loadComponents() {
        // Info text
        roomTimeText = new GameText("Room: 0  Time: 00:00", 400, 740, 24f, Color.white);
        gameComponents.add(roomTimeText);
        // Win Button (Temp)
        winButton = new GameButton("Win", 24f, 800, 400, 100, 100, null); //700
        gameComponents.add(winButton);
        // Lose Button (Temp)
        gameComponents.add(new GameButton("Lose", 24f, 800, 500, 100, 100, () -> { //820
            GameManager.Instance().setCurrentState(GameManager.RESULT_STATE, ResultState.LOSE);
        }));
        //Ues CArd
        useCardButton = new GameButton("Use Card", 24f, 800, 770, 100, 110, null);
        gameComponents.add(useCardButton);
        //Card Name
        cardNameButton= new GameButton("Card Name :" , 24f, 180, 770, 590, 110, null);
        gameComponents.add(cardNameButton);
        //Shffe
        shffeButton = new GameButton("Shffe" , 24f, 50, 770, 100, 50, null);
        gameComponents.add(shffeButton);
        //End Turn
        endButton = new GameButton("End Turn" , 24f, 50, 830, 100, 50, null);
        gameComponents.add(endButton);
        //Card1
        card1Button = new GameButton("Card 1" , 24f, 120, 100, 150, 170, null);
        gameComponents.add(card1Button);
        card2Button = new GameButton("Card 2" , 24f, 300, 100, 150, 170, null);
        gameComponents.add(card2Button);
        card3Button = new GameButton("Card 3" , 24f, 480, 100, 150, 170, null);
        gameComponents.add(card3Button);
        card4Button = new GameButton("Card 4" , 24f, 660, 100, 150, 170, null);
        gameComponents.add(card4Button);
        //player
        platerButton = new GameButton("Player" , 24f, 250, 560, 90, 110, null);
        gameComponents.add(platerButton);
        //enemy
        enemyButton = new GameButton("Enemy" , 24f, 600, 560, 90, 110, null);
        gameComponents.add(enemyButton);
        //hp
        hpPlayerGameText = new GameText("HP : 100", 270, 550, 20f, Color.white);
        gameComponents.add(hpPlayerGameText);
        hpenemyGameText = new GameText("HP : 100", 620, 550, 20f, Color.white);
        gameComponents.add(hpenemyGameText);

        // Enemy Type (Temp)
        enemyTypeText = new GameText("Monster", 270, 470, 150f, Color.white);
        gameComponents.add(enemyTypeText);
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
