package com.Expedition67.unit.Enemy;

import com.Expedition67.core.GameManager;
import com.Expedition67.unit.UnitBrain;

public class EnemyBrain extends UnitBrain {

    private String intent = "Attack: 5 DMG"; 

    public String getIntent() {
        return intent;
    }

    @Override
    protected void die() {
        System.out.println("im ded");
    }

    @Override
    public void startTurn() {
        intent = "Attack: 5 DMG"; 
    }

    @Override
    public void endTurn() {
        //end
    }

    @Override
    public UnitBrain copy() {
        return new EnemyBrain();
    }

    @Override
    protected void onTurnStarted() {
        System.out.println("START!!!");
        GameManager.Instance().getPlayer().takeDamage(5);
    }

    @Override
    protected void onTurnEnded() {
        super.onTurnEnded();
    }
}