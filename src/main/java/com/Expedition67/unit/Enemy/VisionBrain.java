package com.Expedition67.unit.Enemy;

import com.Expedition67.card.Attack.DamageAbility;
import com.Expedition67.card.BuffAbility;
import com.Expedition67.card.Card;
import com.Expedition67.card.CardAbility;
import com.Expedition67.core.GameManager;
import com.Expedition67.storage.Warehouse;
import com.Expedition67.unit.UnitBrain;

public class VisionBrain extends EnemyBrain {

    int dmgStack = 0;
    private final DamageAbility counterAbility = new DamageAbility(0,CardAbility.CardType.ATK);

    @Override
    public void onPlayerUseCard(Card c) {
        super.onPlayerUseCard(c);
    }

    @Override
    public void calculateNextMove() {
        nextAction = Warehouse.Instance().spawnAction(owner.getName(), "HEAL");
        target = this.owner;
    }

    @Override
    public void calculateNextMove(Card c) {
        switch (c.getAbility()) {
            case DamageAbility d -> {
                dmgStack += d.getDamage();
                counterAbility.setDamage(dmgStack);
                nextAction = counterAbility;
                target = GameManager.Instance().getPlayer();
            }
            case BuffAbility b -> {
                nextAction = b;
                target = this.owner;
            }
            default -> {
                calculateNextMove();
            }
        }
    }

    @Override
    public UnitBrain copy() {
        return new VisionBrain();
    }

    @Override
    public void onTurnEnded() {
        super.onTurnEnded();
        dmgStack = 0;
        counterAbility.setDamage(0);
    }
}
