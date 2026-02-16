package com.Expedition67.states;

import com.Expedition67.core.CombatManager;
import com.Expedition67.core.GameManager;
import com.Expedition67.core.GameView;
import com.Expedition67.storage.Warehouse;
import com.Expedition67.ui.GameButton;
import com.Expedition67.ui.GameText;
import com.Expedition67.unit.Deck;
import com.Expedition67.unit.Enemy.Enemy;
import com.Expedition67.card.Card;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CombatState extends GameState {

    public static final int MONSTER_ROOM = 0;
    public static final int FINAL_BOSS_ROOM = 1;

    // Direct references for dynamic updates
    private GameText roomTimeTurnText;
    private List<Enemy> enemies;
    private Deck deck;
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
        roomTimeTurnText = new GameText("Room: 0  Time: 00:00  Turn: 0", 380, 740, 24f, Color.white);
        gameComponents.add(roomTimeTurnText);

        // End Turn (Temp)
        gameComponents.add(new GameButton("End Turn", 24f, 60, 820, 100, 100, () -> {
            CombatManager.Instance().executeTurn();
        }));

        // Lose Button (Temp)
        gameComponents.add(new GameButton("Lose", 24f, 800, 820, 100, 100, () -> {
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

        // Create an enemy based on the room type
        enemies = new ArrayList<>();
        if (id == FINAL_BOSS_ROOM) {
            enemies.add(Warehouse.Instance().spawnEnemy("test", 550, 460));
        } else {
            Random rand = new Random();
            int enemiesAmount = rand.nextInt(1, 4);
            for (int i = 0; i < enemiesAmount; i++) {
                enemies.add(Warehouse.Instance().spawnEnemy("test", 550 + (i * 120), 460));
            }
        }

        CombatManager.Instance().startCombat(enemies);
        deck = CombatManager.Instance().getDeck();
    }

    @Override
    public void exit() {
        enemies.clear();
    }

    @Override
    public void update() {
        // Update the HUD with current room and time
        roomTimeTurnText.setText(String.format("Room: %d  Time: %s  Turn: %d", GameManager.Instance().getRoom(), GameManager.Instance().getTimeString(), CombatManager.Instance().getTurnCount()));

        CombatManager.Instance().update();

        super.update();
    }

    @Override
    public void render(Graphics g) {
        // Draw Background
        g.setColor(Color.black);
        g.fillRect(0, 0, GameView.GAME_WIDTH, GameView.GAME_HEIGHT);

        CombatManager.Instance().render(g);

        // Draw components
        super.render(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (Enemy enemy : enemies) {
            if (enemy.mouseClicked(e)) {
                break;
            }
        }

        deck.mouseClicked(e);

        super.mouseClicked(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (Enemy enemy : enemies) {
            if (enemy.mouseMoved(e)) {
                break;
            }
        }

        deck.mouseMoved(e);

        super.mouseMoved(e);
    }
}
