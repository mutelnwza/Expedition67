package com.Expedition67.card.buff;

import com.Expedition67.card.Card;
import com.Expedition67.card.CardAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.unit.Unit;

import java.util.ArrayList;

public class SoulResonanceAbility extends CardAbility {

    public SoulResonanceAbility(int value, CardType cardType) {
        super(value, cardType);
    }

    @Override
    public void apply(Unit target) {
        ArrayList<Card> cardsInHand = CombatManager.Instance().getDeck().getHand();
        int affectedCards = 0;
        if (cardsInHand != null)
            for (Card c : cardsInHand)
                if (c != null && c.getAbility() != null && c.getAbility().getCardType() == CardType.ATK) {
                    c.getAbility().setTemporaryValue(c.getAbility().getValue() + value);
                    affectedCards++;
                }
        if (affectedCards > 0)
            CombatManager.Instance().addActionString(String.format(" resonates with their soul, empowering %d attack card(s) in hand!", affectedCards));
        else
            CombatManager.Instance().addActionString(" resonates with their soul, but has no attack cards to empower.");
    }

    @Override
    public CardAbility copy() {
        return new SoulResonanceAbility(getValue(), getCardType());
    }
}
