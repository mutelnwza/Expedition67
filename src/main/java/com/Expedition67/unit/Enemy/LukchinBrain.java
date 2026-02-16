package com.Expedition67.unit.Enemy;

import com.Expedition67.card.Card;
import com.Expedition67.card.DamageAbility;
import com.Expedition67.core.GameManager;
import com.Expedition67.storage.Warehouse;
import com.Expedition67.unit.UnitBrain;

public class LukchinBrain extends EnemyBrain{

    @Override
    public void calculateNextMove() {
        nextAction = Warehouse.Instance().spawnAction(owner.getName(), "ATTACK");
        target = GameManager.Instance().getPlayer();
    }

    @Override
    public void calculateNextMove(Card c){
        nextAction = c.getAbility();
        if(nextAction instanceof DamageAbility){
            target = GameManager.Instance().getPlayer();
        }
        else{
            target = this.owner;
        }
    }

    @Override
    public UnitBrain copy() {
        return new LukchinBrain();
    }
    
}
