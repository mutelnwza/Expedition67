package com.Expedition67.unit.Enemy;

import com.Expedition67.card.CardAbility;
import com.Expedition67.card.debuff.NerfHandAbility;
import com.Expedition67.core.CombatManager;
import com.Expedition67.core.GameManager;
import com.Expedition67.storage.Warehouse;
import com.Expedition67.unit.UnitBrain;

public class BigBadBossBrain extends EnemyBrain {

    private int phase = 1;

    //phase 3 property
    private boolean isInvincible = false;
    private float stackDamage = 0;
    private boolean isDoubledTurn = false;

    private void changePhase(int phaseToChange) {
        if (phase < phaseToChange) {
            phase++;
            if (phase == 2) {
                NerfHandAbility nf = new NerfHandAbility(1, 1, CardAbility.CardType.DEBUFF);
                nf.apply(target);
                // animation or something to nofify player taht phase has changed
            } else {
                // same
            }
        }
    }

    @Override
    public void onTurnStarted() {
        super.onTurnStarted();
        if (phase == 3 && stackDamage == 0) {
            isDoubledTurn = true;
        }
        stackDamage = 0;
        isInvincible = false;
    }

    @Override
    public void onTurnEnded() {
        super.onTurnEnded();

        if (isDoubledTurn) {
            CombatManager.Instance().executeTurn();
            isDoubledTurn = false;
        }
        //automatically skip player turn
    }

    @Override
    public float takeDamage(float dmg) {
        if (isInvincible) {
            return 0;
        }

        float d = super.takeDamage(dmg);
        if (phase == 3) {
            stackDamage += d;
            if (stackDamage >= 60) {
                isInvincible = true;
            }
        }

        //check if hp reach threshold
        if(owner.getUnitStats().getHp()<owner.getUnitStats().getMaxHp()*0.33){
            changePhase(3);
        }
        else if(owner.getUnitStats().getHp()<owner.getUnitStats().getMaxHp()*0.67){
            changePhase(2);
        }

        return d;
    }

    @Override
    public void calculateNextMove() {
        target = GameManager.Instance().getPlayer();
        //js random action
        int rnd = (int)(Math.random()*3)+1;
        switch (phase) {
            case 1:
                switch (rnd) {
                    case 1 -> nextAction = Warehouse.Instance().spawnAction(owner.getName(), "ATTACK1");
                    case 2 -> nextAction = Warehouse.Instance().spawnAction(owner.getName(), "LOCK");
                    default-> nextAction = Warehouse.Instance().spawnAction(owner.getName(), "DEFENSE");
                }
                break;
            case 2:
                switch (rnd) {
                    case 1 -> nextAction = Warehouse.Instance().spawnAction(owner.getName(), "ATTACK2");
                    case 2 -> nextAction = Warehouse.Instance().spawnAction(owner.getName(), "MIMIC");
                    default-> nextAction = Warehouse.Instance().spawnAction(owner.getName(), "DEFENSE");
                }
            default:
                nextAction = Warehouse.Instance().spawnAction(owner.getName(), "ATTACK3");
        }
    }

    @Override
    public UnitBrain copy() {
        return new BigBadBossBrain();
    }

}
