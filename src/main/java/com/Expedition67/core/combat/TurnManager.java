package com.Expedition67.core.combat;

import com.Expedition67.unit.Deck;
import com.Expedition67.unit.Enemy.Enemy;
import com.Expedition67.unit.Enemy.EnemyBrain;
import com.Expedition67.unit.Unit;

import java.util.List;

public class TurnManager {

    private boolean isPlayerTurn = true;
    private int turnCount = 0;
    private int actionTimer = 0;
    private final int ACTION_DELAY = 50;
    private int currentEnemyActionIndex = 0;

    public void startPlayerTurn(Unit player, List<Enemy> enemies, Deck deck) {
        CombatManager.Instance().clearActionString();
        isPlayerTurn = true;
        deck.addToHand();
        if (player != null && player.getUnitStats().getHp() > 0) {
            turnCount++;
            player.getBrain().onTurnStarted();
            for (Enemy e : enemies) {
                ((EnemyBrain) e.getBrain()).calculateNextMove();
            }
        }
    }

    public void endPlayerTurn(Unit player) {
        if (isPlayerTurn) {
            player.getBrain().onTurnEnded();
            isPlayerTurn = false;
            currentEnemyActionIndex = 0;
            actionTimer = ACTION_DELAY;
        }
    }

    public void updateEnemyTurns(Unit player, List<Enemy> enemies, Deck deck) {
        if (!isPlayerTurn) {
            actionTimer--;
            if (actionTimer <= 0) {
                if (currentEnemyActionIndex < enemies.size()) {
                    Enemy currentEnemy = enemies.get(currentEnemyActionIndex);
                    if (currentEnemy.getUnitStats().getHp() > 0) {
                        EnemyBrain eb = (EnemyBrain) currentEnemy.getBrain();
                        eb.onTurnStarted();
                        CombatManager.Instance().setActionString(currentEnemy.getName().toString());
                        eb.getNextAction().apply(eb.getTarget());
                        eb.onTurnEnded();
                    }
                    currentEnemyActionIndex++;
                    actionTimer = ACTION_DELAY;
                } else {
                    startPlayerTurn(player, enemies, deck);
                }
            }
        }
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void reset() {
        isPlayerTurn = true;
        turnCount = 0;
        actionTimer = 0;
        currentEnemyActionIndex = 0;
    }
}
