package com.Expedition67.core.combat;

import com.Expedition67.core.GameManager;
import com.Expedition67.core.GameStateManager;
import com.Expedition67.states.CardDropState;
import com.Expedition67.states.ResultState;
import com.Expedition67.unit.Enemy.Enemy;
import com.Expedition67.unit.Unit;
import com.Expedition67.unit.UnitType;

import java.util.List;

public class CombatCompletionHandler {

    public boolean checkWinCondition() {
        Unit player = GameManager.Instance().getPlayer();
        List<Enemy> enemies = CombatManager.Instance().getEnemies();
        UnitType enemyType = CombatManager.Instance().getEnemyType();

        if (player.getUnitStats().getHp() <= 0) {
            if (player.getFlashFrame() <= 0) {
                CombatManager.Instance().endCombat();
                GameManager.Instance().getGameStateManager().setCurrentState(GameStateManager.RESULT_STATE, ResultState.LOSE);
            }
            return true;
        }

        int i = 0;
        while (i < enemies.size()) {
            Enemy currentEnemy = enemies.get(i);
            if (currentEnemy.getUnitStats().getHp() <= 0 && currentEnemy.getFlashFrame() <= 0) {
                enemies.remove(i);
                if (CombatManager.Instance().getTarget() == currentEnemy) {
                    CombatManager.Instance().setTarget(null);
                }
            } else {
                i++;
            }
        }

        if (enemies.isEmpty()) {
            CombatManager.Instance().endCombat();
            if (enemyType == UnitType.ENEMY) {
                GameManager.Instance().getGameStateManager().setCurrentState(GameStateManager.CARD_DROP_STATE, CardDropState.NORMAL_DROP);
            } else if (enemyType == UnitType.MINIBOSS) {
                GameManager.Instance().getGameStateManager().setCurrentState(GameStateManager.CARD_DROP_STATE, CardDropState.MINIBOSS_DROP);
            } else {
                GameManager.Instance().getGameStateManager().setCurrentState(GameStateManager.RESULT_STATE, ResultState.WIN);
            }
            return true;
        }
        return false;
    }
}
