package com.Expedition67.states;

import com.Expedition67.core.GameManager;
import com.Expedition67.core.GameStateManager;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.core.graphics.GameView;
import com.Expedition67.core.util.GameRandom;
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

public class CombatState extends GameState {

    public static final int MONSTER_ROOM = 0;
    public static final int BOSS_ROOM = 1;

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

        // Action Text
        actionText = new GameText("Placeholder", 0, 270, 24f, Color.white);
        gameComponents.add(actionText);

        // Reshuffle (Temp)
        gameComponents.add(new GameButton("Reshuffle", 24f, 50, 590, 100, 50, () ->
                deck.reshuffle()
        ));

        // Lose (Temp)
        gameComponents.add(new GameButton("Lose", 24f, 50, 650, 100, 50, () ->
                GameManager.Instance().getPlayer().takeDamage(10000000)
        ));

        // Win (Temp)
        gameComponents.add(new GameButton("Win", 24f, 50, 710, 100, 50, () ->
                CombatManager.Instance().clearEnemies()
        ));

        // Card Info
        cardInfoText = new GameText("Placeholder", 0, 0, 24f, Color.white);
        gameComponents.add(cardInfoText);

        // Inventory
        gameComponents.add(new GameButton("Inventory", 24f, 50, 770, 100, 50, () ->
                GameManager.Instance().getGameStateManager().setCurrentState(GameStateManager.INVENTORY_STATE, InventoryState.ENTER_FROM_COMBAT)
        ));

        // End Turn
        gameComponents.add(new GameButton("End Turn", 24f, 50, 830, 100, 50, () ->
                CombatManager.Instance().executeTurn()
        ));

        // Use Card
        gameComponents.add(new GameButton("Use Card", 24f, 800, 770, 100, 110, () ->
                CombatManager.Instance().onPlayerUseCard(deck.getSelectedCard(), CombatManager.Instance().getTarget())
        ));
    }

    @Override
    public void enter(int id) {
        // Increment room count
        GameManager.Instance().getGameData().incrementRoom();

        // Create an enemy based on the room type
        enemies = new ArrayList<>();
        if (id == BOSS_ROOM) enemies.add(Warehouse.Instance().spawnBoss(555, 460));
        else enemies = GameRandom.Instance().getRandomEnemies(630, 460);

        GameManager.Instance().getPlayer().setX(enemies.getFirst().getX() - 300);

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

        // Update card info text with current selected card
        if (deck.getSelectedCard() != null) cardInfoText.setText(deck.getSelectedCard().toString());
        else cardInfoText.setText("No card selected");
        cardInfoText.horizontallyCentering(180, 590);
        cardInfoText.verticallyCentering(770, 110);

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

        // Card info border
        g.setColor(Color.white);
        g.drawRect(185, 770, 590, 110);

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

    public void setActionText(String action) {
        actionText.setText(action);
        actionText.horizontallyCentering(0, GameView.GAME_WIDTH);
    }
}
