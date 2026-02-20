package com.Expedition67.states;

import com.Expedition67.core.CombatManager;
import com.Expedition67.core.GameManager;
import com.Expedition67.core.GameView;
import com.Expedition67.storage.Warehouse;
import com.Expedition67.ui.GameButton;
import com.Expedition67.ui.GameText;
import com.Expedition67.unit.Deck;
import com.Expedition67.unit.Enemy.Enemy;
import com.Expedition67.unit.Unit;

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
    private GameText cardInfoText;
    private GameText actionText;

    private List<Enemy> enemies;
    private Deck deck;



    public CombatState() {
        super();
    }

    @Override
    protected void loadComponents() {
        // Info text
        roomTimeTurnText = new GameText("Room: 0  Time: 00:00  Turn: 0", 0, 740, 24f, Color.white);
        gameComponents.add(roomTimeTurnText);

        actionText = new GameText(" ---------------- ", 300, 360, 24f, Color.white);
        gameComponents.add(actionText);

        // Lose (Temp)
        gameComponents.add(new GameButton("Lose", 24f, 50, 650, 100, 50, () -> {
            GameManager.Instance().setCurrentState(GameManager.RESULT_STATE, ResultState.LOSE);
        }));

        // Win (Temp)
        gameComponents.add(new GameButton("Win", 24f, 50, 710, 100, 50, () -> {
            GameManager.Instance().setCurrentState(GameManager.CARD_DROP_STATE, CardDropState.MONSTER_DROP);
        }));

        // Card Info
        gameComponents.add(new GameButton("Card Name :" , 24f, 180, 770, 590, 110, null));

        // Reshuffle
        gameComponents.add(new GameButton("Reshuffle" , 24f, 50, 770, 100, 50, () -> {
            deck.reshuffle();
        }));

        // End Turn
        gameComponents.add(new GameButton("End Turn" , 24f, 50, 830, 100, 50, () -> {
            CombatManager.Instance().clearActionString();
            CombatManager.Instance().executeTurn();
        }));

        // Use Card
        gameComponents.add(new GameButton("Use Card", 24f, 800, 770, 100, 110, () -> {
            CombatManager.Instance().clearActionString();
            CombatManager.Instance().onPlayerUseCard(deck.getSelectedCard(), CombatManager.Instance().getTarget());
        }));
    }

    @Override
    public void enter(int id) {
        // Increment room count
        GameManager.Instance().setRoom(GameManager.Instance().getRoom() + 1);

        // Create an enemy based on the room type
        enemies = new ArrayList<>();
        if (id == FINAL_BOSS_ROOM) {
            enemies.add(Warehouse.Instance().spawnRandomEnemy(550, 460));
        } else {
            Random rand = new Random();
            int enemiesAmount = rand.nextInt(1, 4);
            for (int i = 0; i < enemiesAmount; i++) {
                enemies.add(Warehouse.Instance().spawnRandomEnemy((i * 120) + 550, 460));
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
        roomTimeTurnText.horizontallyCentering(0, GameView.GAME_WIDTH);

        setActionText(CombatManager.Instance().getActionString());
        CombatManager.Instance().update();
        GameManager.Instance().getPlayer().update();
        for (Enemy enemy : enemies) {
            enemy.update();
        }
        deck.update();

        super.update();
    }

    @Override
    public void render(Graphics g) {
        // Draw Background
        g.setColor(Color.black);
        g.fillRect(0, 0, GameView.GAME_WIDTH, GameView.GAME_HEIGHT);

        GameManager.Instance().getPlayer().render(g);
        for (Unit enemy : enemies) {
            enemy.render(g);
        }
        if (CombatManager.Instance().getTarget() != null) {
            CombatManager.Instance().getTarget().renderTarget(g);
        }

        deck.render(g);

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
    public void setActionText(String action){
        actionText.setText(action);
        actionText.horizontallyCentering(0,GameView.GAME_WIDTH);
    }
}
