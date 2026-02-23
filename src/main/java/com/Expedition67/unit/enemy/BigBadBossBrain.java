package com.Expedition67.unit.enemy;

import com.Expedition67.card.CardAbility;
import com.Expedition67.card.debuff.NerfHandAbility;
import com.Expedition67.core.GameManager;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.storage.Warehouse;
import com.Expedition67.unit.UnitBrain;

/**
 * The AI for the final boss.
 */
public class BigBadBossBrain extends EnemyBrain {

    private int phase = 1;
    private boolean isInvincible = false;
    private float stackDamage = 0;
    private boolean isDoubledTurn = false;

    @Override
    public UnitBrain copy() {
        return new BigBadBossBrain();
    }

    @Override
    public void calculateNextMove() {
        target = GameManager.Instance().getPlayer();
        int rnd = (int) (Math.random() * 3) + 1;

        switch (phase) {
            case 1 -> calculatePhase1Action(rnd);
            case 2 -> calculatePhase2Action(rnd);
            default -> nextAction = Warehouse.Instance().spawnAction(owner.getName(), "ATTACK3");
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

        if (owner.getUnitStats().getHp() < owner.getUnitStats().getMaxHp() * 0.33) {
            changePhase(3);
        } else if (owner.getUnitStats().getHp() < owner.getUnitStats().getMaxHp() * 0.67) {
            changePhase(2);
        }
        return d;
    }

    private void changePhase(int phaseToChange) {
        if (phase < phaseToChange) {
            phase = phaseToChange;
            if (phase == 2) {
                NerfHandAbility nf = new NerfHandAbility(1, 1, CardAbility.CardType.DEBUFF);
                nf.apply(GameManager.Instance().getPlayer());
            }
        }
    }

    private void calculatePhase1Action(int rnd) {
        switch (rnd) {
            case 1 -> nextAction = Warehouse.Instance().spawnAction(owner.getName(), "ATTACK1");
            case 2 -> nextAction = Warehouse.Instance().spawnAction(owner.getName(), "LOCK");
            default -> {
                nextAction = Warehouse.Instance().spawnAction(owner.getName(), "DEFENSE");
                target = this.owner;
            }
        }
    }

    private void calculatePhase2Action(int rnd) {
        switch (rnd) {
            case 1 -> nextAction = Warehouse.Instance().spawnAction(owner.getName(), "ATTACK2");
            case 2 -> nextAction = Warehouse.Instance().spawnAction(owner.getName(), "MIMIC");
            default -> {
                nextAction = Warehouse.Instance().spawnAction(owner.getName(), "DEFENSE");
                target = this.owner;
            }
        }
    }
}
