package com.Expedition67.card.debuff;

import com.Expedition67.card.CardAbility;
import com.Expedition67.card.RemovableAbility;
import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;

public class NerfStatsAbility extends RemovableAbility {

    public NerfStatsAbility(int turn, int value, CardType cardType) {
        super(turn, value, cardType);
    }

    @Override
    public void apply(Unit target) {
        target.getBrain().addStr(-1 * value);
        CombatManager.Instance().addActionString(String.format(" weakens %s, reducing their Strength by %d for %d turn(s).", target.getName(), value, getTurn()));
    }

    @Override
    public void remove(Unit target) {
        target.getBrain().addStr(value);
        CombatManager.Instance().setActionString(String.format("%s's Strength has returned to normal.", target.getName()));
    }

    @Override
    public CardAbility copy() {
        return new NerfStatsAbility(getTurn(), getValue(), getCardType());
    }
}
