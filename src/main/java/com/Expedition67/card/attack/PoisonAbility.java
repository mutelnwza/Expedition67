package com.Expedition67.card.attack;

import com.Expedition67.card.CardAbility;
import com.Expedition67.card.RemovableAbility;
import com.Expedition67.core.CombatManager;
import com.Expedition67.core.ITickable;
import com.Expedition67.unit.Unit;

public class PoisonAbility extends RemovableAbility implements ITickable {

    public PoisonAbility(int turn, int value, CardType cardType) {
        super(turn, value, cardType);
    }

    @Override
    public void onTick(Unit owner) {
        owner.takeDamage(value);
        CombatManager.Instance().addActionString(String.format("%s takes %d poison damage", owner.getName(), value));
    }

    @Override
    public void apply(Unit target) {
        CombatManager.Instance().setActionString(String.format("%s is poisoned for %d turns", target.getName(), getTurn()));
    }

    @Override
    public void remove(Unit target) {
    }

    @Override
    public CardAbility copy() {
        return new PoisonAbility(getTurn(), getValue(), getCardType());
    }
}
