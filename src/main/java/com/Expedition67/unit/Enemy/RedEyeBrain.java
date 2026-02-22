package com.Expedition67.unit.Enemy;

import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.core.GameManager;
import com.Expedition67.storage.Warehouse;
import com.Expedition67.unit.UnitBrain;

public class RedEyeBrain extends EnemyBrain{
    private boolean rage = false;
    @Override
    public void calculateNextMove() {
        nextAction = Warehouse.Instance().spawnAction(owner.getName(), "ATTACK");
        target = GameManager.Instance().getPlayer();
    }
    
    @Override
    public void onTurnStarted(){
        super.onTurnStarted();
        if(owner.getUnitStats().getHp()<owner.getUnitStats().getMaxHp()/2){
            rage = true;
            addCrit(0.15f);
        }
        if(CombatManager.Instance().getTurnCount()%3==0){
            addDef(15);
            if(rage) addDef(10);
        }
    }

    @Override
    public void onTurnEnded(){
        super.onTurnEnded();
        if(rage){
            addDef(Math.max(5, owner.getUnitStats().getDef()*1.05f));
        }
    }

    @Override
    public UnitBrain copy() {
        return new RedEyeBrain();
    }
    
}
