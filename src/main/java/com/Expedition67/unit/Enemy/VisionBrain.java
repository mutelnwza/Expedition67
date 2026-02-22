package com.Expedition67.unit.Enemy;

import com.Expedition67.card.attack.DamageAbility;
import com.Expedition67.card.Card;
import com.Expedition67.card.CardAbility;
import com.Expedition67.core.GameManager;
import com.Expedition67.storage.Warehouse;
import com.Expedition67.unit.UnitBrain;

import java.util.Objects;

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
        if (Objects.requireNonNull(c.getAbility()) instanceof DamageAbility d) {
            dmgStack += d.getValue();
            counterAbility.setValue(dmgStack);
            nextAction = counterAbility;
            target = GameManager.Instance().getPlayer();
        } else {
            calculateNextMove();
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
        counterAbility.setValue(0);
    }
}
