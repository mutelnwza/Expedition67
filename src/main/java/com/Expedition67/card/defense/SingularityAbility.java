package com.Expedition67.card.defense;

import com.Expedition67.card.CardAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.unit.Unit;

public class SingularityAbility extends CardAbility {

    public SingularityAbility(int value, CardType cardType) {
        super(value, cardType);
    }

    @Override
    public void apply(Unit target) {
        target.getBrain().addDef(value);
        CombatManager.Instance().getDeck().setFreeCard(2,this);
        CombatManager.Instance().addActionString(String.format(" creates a singularity, granting %d Block and making the next card has no AP cost!", value));
    }

    @Override
    public CardAbility copy() {
        return new SingularityAbility(getValue(), getCardType());
    }
}
