package com.Expedition67.unit.Enemy;

import com.Expedition67.storage.Warehouse;
import com.Expedition67.unit.UnitBrain;

public class CryingSlimeBrain extends EnemyBrain{

    @Override
    public void calculateNextMove() {
        int choice = (int)(Math.random() * 10)+1;
        if(choice > 4){
            nextAction = Warehouse.Instance().spawnAction(this.getOwner().getName(), "ATTACK");
        }
        else{
            nextAction = Warehouse.Instance().spawnAction(this.getOwner().getName(), "DEF");
        }
    }

    @Override
    public UnitBrain copy() {
        return new CryingSlimeBrain();
    }
    
}
