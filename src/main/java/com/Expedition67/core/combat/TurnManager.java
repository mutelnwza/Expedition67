package com.Expedition67.core.combat;

import com.Expedition67.card.CardAbility;
import com.Expedition67.unit.Unit;
import com.Expedition67.unit.enemy.Enemy;
import com.Expedition67.unit.enemy.EnemyBrain;
import com.Expedition67.unit.player.Deck;

import java.util.List;

/**
 * Manages the flow of turns in combat, alternating between player and enemies.
 */
public class TurnManager {

    private enum EnemyTurnState {
        MOVING_FORWARD,
        ATTACKING,
        MOVING_BACK,
        DONE
    }

    private boolean isPlayerTurn = true;
    private int turnCount = 0;
    private int actionTimer = 0;
    private final int ACTION_DELAY = 50;
    private int currentEnemyActionIndex = 0;
    private EnemyTurnState enemyTurnState = EnemyTurnState.DONE;
    private int originalX, originalY;

    /**
     * Starts the player's turn.
     *
     * @param player  The player unit.
     * @param enemies The list of enemies.
     */
    public void startPlayerTurn(Unit player, List<Enemy> enemies) {
        CombatManager.Instance().clearActionString();
        isPlayerTurn = true;

        if (player != null && player.getUnitStats().getHp() > 0) {
            turnCount++;
            player.getBrain().onTurnStarted();
            for (Enemy e : enemies) {
                ((EnemyBrain) e.getBrain()).calculateNextMove();
            }
        }
    }

    /**
     * Ends the player's turn and transitions to the enemies' turn.
     *
     * @param player The player unit.
     * @param deck   The player's deck.
     */
    public void endPlayerTurn(Unit player, Deck deck) {
        if (isPlayerTurn) {
            deck.addToHand();
            player.getBrain().onTurnEnded();
            isPlayerTurn = false;
            currentEnemyActionIndex = 0;
            actionTimer = ACTION_DELAY;
            CombatManager.Instance().getDeck().updateFreeCard(true);
            enemyTurnState = EnemyTurnState.MOVING_FORWARD;
        }
    }

    /**
     * Updates the enemy turns, processing each enemy's action with a delay.
     *
     * @param player  The player unit.
     * @param enemies The list of enemies.
     */
    public void updateEnemyTurns(Unit player, List<Enemy> enemies) {
        if (!isPlayerTurn) {
            actionTimer--;
            if (actionTimer <= 0) {
                if (currentEnemyActionIndex < enemies.size()) {
                    Enemy currentEnemy = enemies.get(currentEnemyActionIndex);
                    if (currentEnemy.getUnitStats().getHp() > 0) {
                        switch (enemyTurnState) {
                            case MOVING_FORWARD:
                                originalX = currentEnemy.getX();
                                originalY = currentEnemy.getY();
                                currentEnemy.setX(currentEnemy.getX() - 50);
                                enemyTurnState = EnemyTurnState.ATTACKING;
                                actionTimer = ACTION_DELAY / 2;
                                break;
                            case ATTACKING:
                                EnemyBrain eb = (EnemyBrain) currentEnemy.getBrain();
                                eb.onTurnStarted();
                                CombatManager.Instance().setActionString(currentEnemy.getName().toString());

                                CardAbility nextAction = eb.getNextAction();
                                if (nextAction.getCardType() == CardAbility.CardType.ATK) {
                                    nextAction.apply(eb.getTarget(), eb.getOwner());
                                } else {
                                    nextAction.apply(eb.getTarget());
                                }
                                eb.onTurnEnded();
                                enemyTurnState = EnemyTurnState.MOVING_BACK;
                                actionTimer = ACTION_DELAY / 2;
                                break;
                            case MOVING_BACK:
                                currentEnemy.setX(originalX);
                                currentEnemy.setY(originalY);
                                enemyTurnState = EnemyTurnState.DONE;
                                break;
                            case DONE:
                                currentEnemyActionIndex++;
                                if (currentEnemyActionIndex < enemies.size()) {
                                    enemyTurnState = EnemyTurnState.MOVING_FORWARD;
                                }
                                actionTimer = ACTION_DELAY / 2;
                                break;
                        }
                    } else {
                        currentEnemyActionIndex++;
                        if (currentEnemyActionIndex < enemies.size()) {
                            enemyTurnState = EnemyTurnState.MOVING_FORWARD;
                        }
                        actionTimer = ACTION_DELAY;
                    }
                } else {
                    startPlayerTurn(player, enemies);
                }
            }
        }
    }

    /**
     * Gets the total number of turns passed in the current combat.
     *
     * @return The current turn count.
     */
    public int getTurnCount() {
        return turnCount;
    }

    /**
     * Resets the TurnManager to its initial state for a new combat.
     */
    public void reset() {
        isPlayerTurn = true;
        turnCount = 0;
        actionTimer = 0;
        currentEnemyActionIndex = 0;
        enemyTurnState = EnemyTurnState.DONE;
    }
}
