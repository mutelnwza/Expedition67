package com.Expedition67.unit.Enemy;

import com.Expedition67.card.CardAbility;
import com.Expedition67.core.CombatManager;
import com.Expedition67.core.GameManager;
import com.Expedition67.storage.Warehouse;
import com.Expedition67.unit.UnitBrain;

public class TillyTheBirdBrain extends EnemyBrain {

    private int phase = 1;

    @Override
    public void onTurnStarted() {
        super.onTurnStarted();
        //check if hp<40% go to phase 2
        if (owner.getUnitStats().getHp() < owner.getUnitStats().getMaxHp() * 0.4) {
            phase = 2;
        }
    }

    @Override
    public void calculateNextMove() {
        target = GameManager.Instance().getPlayer();
        int choice = (int) (Math.random() * 3) + 1;
        switch (phase) {
            case 2 ->
                phase2(choice);
            default ->
                phase1(choice);
        }
    }

    @Override
    public void onUseCard(CardAbility ca) {
        //passive
        if (ca.getCardType() == CardAbility.CardType.ATK) {
            addDef(4);
        }
    }

    @Override
    public UnitBrain copy() {
        return new TillyTheBirdBrain();
    }

    private void phase1(int choice) {
        switch (choice) {
            case 1:
                if (CombatManager.Instance().getDeck().getHand().stream().anyMatch(c
                        -> c.getAbility().getCardType() == CardAbility.CardType.ATK)) {
                    //add cost debuff (write a new skill)
                    nextAction = Warehouse.Instance().spawnAction(owner.getName(), "DEBUFF1");
                    break;
                }
            case 2:
                nextAction = Warehouse.Instance().spawnAction(owner.getName(), "NORMALATTACK");
                break;
            default:
                nextAction = Warehouse.Instance().spawnAction(owner.getName(), "MULTIATTACK");
                break;
        }
    }

    private void phase2(int choice) {
        switch (choice) {
            case 1:
                nextAction = Warehouse.Instance().spawnAction(owner.getName(), "DISCARD");
            case 2:
                nextAction = Warehouse.Instance().spawnAction(owner.getName(), "HEAVYATTACK");
            case 3:
                nextAction = Warehouse.Instance().spawnAction(owner.getName(), "NERF");
        }
    }

}
