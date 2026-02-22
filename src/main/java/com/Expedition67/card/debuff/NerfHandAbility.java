package com.Expedition67.card.debuff;

import com.Expedition67.card.CardAbility;
import com.Expedition67.card.RemovableAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.unit.Unit;

public class NerfHandAbility extends RemovableAbility {

    public NerfHandAbility(int turn, int value, CardType cardType) {
        super(turn, value, cardType);
    }

    @Override
    public void apply(Unit target) {
        CombatManager.Instance().getDeck().setHandSize(CombatManager.Instance().getDeck().getHandSize() - value);
        CombatManager.Instance().addActionString(String.format(" restricts %s's hand, reducing their hand size by %d for %d turn(s).", target.getName(), value, getTurn()));
    }

    @Override
    public void remove(Unit target) {
        CombatManager.Instance().getDeck().setHandSize(CombatManager.Instance().getDeck().getHandSize() + value);
        CombatManager.Instance().setActionString(String.format("%s's hand size has returned to normal.", target.getName()));
    }

    @Override
    public CardAbility copy() {
        return new NerfHandAbility(getTurn(), getValue(), getCardType());
    }
}
