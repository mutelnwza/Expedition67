package com.Expedition67.card.special;

import com.Expedition67.card.CardAbility;
import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;

public class VoidCard extends CardAbility {

    public VoidCard(CardType cardType) {
        super(cardType);
    }

    @Override
    public void apply(Unit target) {
        CombatManager.Instance().addActionString(" use Void card! Do nothing, just disappear from your deck...");
    }

    @Override
    public CardAbility copy() {
        return new VoidCard(getCardType());
    }
}
