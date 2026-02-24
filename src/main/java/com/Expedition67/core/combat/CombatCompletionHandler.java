package com.Expedition67.core.combat;

import com.Expedition67.core.GameManager;
import com.Expedition67.core.GameStateManager;
import com.Expedition67.states.CardDropState;
import com.Expedition67.states.ResultState;
import com.Expedition67.unit.Unit;
import com.Expedition67.unit.UnitType;
import com.Expedition67.unit.enemy.Enemy;

import java.util.List;

/**
 * Handles the logic for checking if combat has ended due to a win or loss.
 */
public class CombatCompletionHandler {

    /**
     * Checks if the combat is over by player defeat or all enemies being defeated.
     *
     * @return true if combat has ended, false otherwise.
     */
    public boolean checkWinCondition() {
        Unit player = GameManager.Instance().getPlayer();
        List<Enemy> enemies = CombatManager.Instance().getEnemies();

        if (isPlayerDefeated(player)) {
            return true;
        }

        removeDefeatedEnemies(enemies);

        return areAllEnemiesDefeated(enemies);
    }

    private boolean isPlayerDefeated(Unit player) {
        if (player.getUnitStats().getHp() <= 0) {
            if (player.getFlashFrame() <= 0) {
                CombatManager.Instance().endCombat();
                GameManager.Instance().getGameStateManager().setCurrentState(GameStateManager.RESULT_STATE, ResultState.LOSE);
            }
            return true;
        }
        return false;
    }

    private void removeDefeatedEnemies(List<Enemy> enemies) {
        enemies.removeIf(enemy -> {
            if (enemy.getUnitStats().getHp() <= 0 && enemy.getFlashFrame() <= 0) {
                if (CombatManager.Instance().getTarget() == enemy) {
                    CombatManager.Instance().setTarget(null);
                }
                return true;
            }
            return false;
        });
    }

    private boolean areAllEnemiesDefeated(List<Enemy> enemies) {
        if (enemies.isEmpty()) {
            UnitType enemyType = CombatManager.Instance().getEnemyType();
            CombatManager.Instance().endCombat();

            int cardDropType = 0;
            if (enemyType == UnitType.ENEMY) {
                int enemyAmount = CombatManager.Instance().getEnemyAmount();
                cardDropType = switch (enemyAmount) {
                    case 1 -> CardDropState.ONE_ENEMY_DROP;
                    case 2 -> CardDropState.TWO_ENEMY_DROP;
                    default -> CardDropState.THREE_ENEMY_DROP;
                };
            } else if (enemyType == UnitType.MINIBOSS) {
                cardDropType = CardDropState.MINIBOSS_DROP;
            } else {
                GameManager.Instance().getGameStateManager().setCurrentState(GameStateManager.RESULT_STATE, ResultState.WIN);
            }
            GameManager.Instance().getGameStateManager().setCurrentState(GameStateManager.CARD_DROP_STATE, cardDropType);
            return true;
        }
        return false;
    }
}
