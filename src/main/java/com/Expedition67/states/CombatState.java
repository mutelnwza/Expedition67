package com.Expedition67.states;

import com.Expedition67.core.GameManager;
import com.Expedition67.core.GameStateManager;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.core.graphics.GameView;
import com.Expedition67.core.util.GameRandom;
import com.Expedition67.storage.Warehouse;
import com.Expedition67.ui.GameButton;
import com.Expedition67.ui.GameText;
import com.Expedition67.unit.Unit;
import com.Expedition67.unit.enemy.Enemy;
import com.Expedition67.unit.player.Deck;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * The game state for combat encounters.
 */
public class CombatState extends GameState {

    public static final int MONSTER_ROOM = 0;
    public static final int BOSS_ROOM = 1;

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
        roomTimeTurnText = new GameText("Room: 0  Time: 00:00  Turn: 0", 0, 740, 24f, Color.white);
        gameComponents.add(roomTimeTurnText);

        actionText = new GameText("Placeholder", 0, 270, 24f, Color.white);
        gameComponents.add(actionText);

        cardInfoText = new GameText("Placeholder", 0, 0, 24f, Color.white);
        gameComponents.add(cardInfoText);

        gameComponents.add(new GameButton("Inventory", 24f, 50, 770, 100, 50, () ->
                GameManager.Instance().getGameStateManager().setCurrentState(GameStateManager.INVENTORY_STATE, InventoryState.ENTER_FROM_COMBAT)
        ));

        gameComponents.add(new GameButton("End Turn", 24f, 50, 830, 100, 50, () ->
                CombatManager.Instance().executeTurn()
        ));

        gameComponents.add(new GameButton("Use Card", 24f, 800, 770, 100, 110, () ->
                CombatManager.Instance().onPlayerUseCard(deck.getSelectedCard(), CombatManager.Instance().getTarget())
        ));
    }

    @Override
    public void enter(int id) {
        GameManager.Instance().getGameData().incrementRoom();

        enemies = new ArrayList<>();
        if (id == BOSS_ROOM) {
            enemies.add(Warehouse.Instance().spawnBoss(555, 460));
        } else {
            enemies = GameRandom.Instance().getRandomEnemies(630, 460);
        }

        if (!enemies.isEmpty()) {
            GameManager.Instance().getPlayer().setX(enemies.getFirst().getX() - 300);
        }

        CombatManager.Instance().startCombat(enemies);
        deck = CombatManager.Instance().getDeck();
    }

    @Override
    public void exit() {
        if (enemies != null) {
            enemies.clear();
        }
    }

    @Override
    public void update() {
        super.update();
        updateHUD();
        CombatManager.Instance().update();
        GameManager.Instance().getPlayer().update();
        for (Enemy enemy : enemies) {
            enemy.update();
        }
        if (deck != null) {
            deck.getHandUI().update();
            updateCardInfo();
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, GameView.GAME_WIDTH, GameView.GAME_HEIGHT);

        GameManager.Instance().getPlayer().render(g);
        for (Unit enemy : enemies) {
            enemy.render(g);
        }

        if (CombatManager.Instance().getTarget() != null) {
            CombatManager.Instance().getTarget().renderTargetIndicator(g);
        }

        if (deck != null) {
            deck.getHandUI().render(g);
        }

        g.setColor(Color.white);
        g.drawRect(185, 770, 590, 110);

        super.render(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        for (Enemy enemy : enemies) {
            if (enemy.mouseClicked(e)) break;
        }
        if (deck != null) {
            deck.getHandUI().mouseClicked(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        for (Enemy enemy : enemies) {
            if (enemy.mouseMoved(e)) break;
        }
        if (deck != null) {
            deck.getHandUI().mouseMoved(e);
        }
    }

    private void updateHUD() {
        roomTimeTurnText.setText(String.format("Room: %d  Time: %s  Turn: %d", GameManager.Instance().getRoom(), GameManager.Instance().getTimeString(), CombatManager.Instance().getTurnCount()));
        roomTimeTurnText.horizontallyCentering(0, GameView.GAME_WIDTH);
        actionText.setText(CombatManager.Instance().getActionString());
        actionText.horizontallyCentering(0, GameView.GAME_WIDTH);
    }

    private void updateCardInfo() {
        if (deck.getSelectedCard() != null) {
            cardInfoText.setText(deck.getSelectedCard().toString());
        } else {
            cardInfoText.setText("No card selected");
        }
        cardInfoText.horizontallyCentering(180, 590);
        cardInfoText.verticallyCentering(770, 110);
    }
}
