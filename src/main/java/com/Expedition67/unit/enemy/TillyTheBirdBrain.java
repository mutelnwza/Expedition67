package com.Expedition67.unit.enemy;

import com.Expedition67.card.CardAbility;
import com.Expedition67.core.GameManager;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.storage.Warehouse;
import com.Expedition67.unit.UnitBrain;

/**
 * The AI for Tilly the Bird.
 */
public class TillyTheBirdBrain extends EnemyBrain {

    private int phase = 1;

    @Override
    public UnitBrain copy() {
        return new TillyTheBirdBrain();
    }

    @Override
    public void calculateNextMove() {
        target = GameManager.Instance().getPlayer();
        int choice = (int) (Math.random() * 3) + 1;
        if (phase == 2) {
            phase2(choice);
        } else {
            phase1(choice);
        }
    }

    @Override
    public void onTurnStarted() {
        super.onTurnStarted();
        if (owner.getUnitStats().getHp() < owner.getUnitStats().getMaxHp() * 0.4) {
            phase = 2;
        }
    }

    @Override
    public void onUseCard(CardAbility ca) {
        if (ca.getCardType() == CardAbility.CardType.ATK) {
            addDef(4);
        }
    }

    private void phase1(int choice) {
        switch (choice) {
            case 1:
                if (CombatManager.Instance().getDeck().getHand().stream().anyMatch(c -> c.getAbility().getCardType() == CardAbility.CardType.ATK)) {
                    nextAction = Warehouse.Instance().spawnAction(owner.getName(), "DEBUFF1");
                } else {
                    nextAction = Warehouse.Instance().spawnAction(owner.getName(), "NORMALATTACK");
                }
                break;
            case 2:
                nextAction = Warehouse.Instance().spawnAction(owner.getName(), "NORMALATTACK");
                break;
            default:
                nextAction = Warehouse.Instance().spawnAction(owner.getName(), "MULTIATTACK");
                break;
        }
    }

    private void phase2(int choice) {
        nextAction = switch (choice) {
            case 1 -> Warehouse.Instance().spawnAction(owner.getName(), "DISCARD");
            case 2 -> Warehouse.Instance().spawnAction(owner.getName(), "HEAVYATTACK");
            case 3 -> Warehouse.Instance().spawnAction(owner.getName(), "NERF");
            default -> Warehouse.Instance().spawnAction(owner.getName(), "NORMALATTACK");
        };
    }
}
