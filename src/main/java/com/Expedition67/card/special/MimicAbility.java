package com.Expedition67.card.special;

import com.Expedition67.card.Card;
import com.Expedition67.card.CardAbility;
import com.Expedition67.core.CombatManager;
import com.Expedition67.core.GameRandom;
import com.Expedition67.unit.Unit;

public class MimicAbility extends CardAbility {

    public MimicAbility(CardType cardType) {
        super(cardType);
    }

    @Override
    public void apply(Unit target) {
        Card c = GameRandom.Instance().getRandomCardFromHand(CardType.ATK);
        if (c != null) {
            CombatManager.Instance().addActionString(String.format(" mimics %s!", c.getName()));
            c.getAbility().apply(target);
        } else
            CombatManager.Instance().addActionString(" attempts to mimic, but finds no attack card to copy.");
    }

    @Override
    public CardAbility copy() {
        return new MimicAbility(getCardType());
    }
}
