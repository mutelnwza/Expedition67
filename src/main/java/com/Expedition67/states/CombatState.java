package com.Expedition67.states;

import com.Expedition67.core.CombatManager;
import com.Expedition67.core.GameManager;
import com.Expedition67.core.GameView;
import com.Expedition67.storage.Warehouse;
import com.Expedition67.ui.GameButton;
import com.Expedition67.ui.GameText;
import com.Expedition67.unit.Deck;
import com.Expedition67.unit.Enemy.Enemy;

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
        gameComponents.add(new GameButton("Lose", 24f, 800, 820, 100, 40, () -> {
            GameManager.Instance().setCurrentState(GameManager.RESULT_STATE, ResultState.LOSE);
        }));

        // Use Card Button (Temp)
        gameComponents.add(new GameButton("Use Card", 24f, 800, 880, 100, 40, () -> {
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
