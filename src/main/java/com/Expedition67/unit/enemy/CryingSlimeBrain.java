package com.Expedition67.unit.enemy;

import com.Expedition67.core.GameManager;
import com.Expedition67.storage.Warehouse;
import com.Expedition67.unit.UnitBrain;

/**
 * The AI for the Crying Slime enemy.
 */
public class CryingSlimeBrain extends EnemyBrain {

    @Override
    public UnitBrain copy() {
        return new CryingSlimeBrain();
    }

    @Override
    public void calculateNextMove() {
        int choice = (int) (Math.random() * 10) + 1;
        if (choice > 4) {
            nextAction = Warehouse.Instance().spawnAction(this.getOwner().getName(), "ATTACK");
            target = GameManager.Instance().getPlayer();
        } else {
            nextAction = Warehouse.Instance().spawnAction(this.getOwner().getName(), "DEF");
            target = this.owner;
        }
    }
}
