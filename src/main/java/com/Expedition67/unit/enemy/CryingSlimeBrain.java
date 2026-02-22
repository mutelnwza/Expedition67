package com.Expedition67.unit.enemy;

import com.Expedition67.core.GameManager;
import com.Expedition67.storage.Warehouse;
import com.Expedition67.unit.UnitBrain;

public class CryingSlimeBrain extends EnemyBrain {

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

    @Override
    public UnitBrain copy() {
        return new CryingSlimeBrain();
    }

}
