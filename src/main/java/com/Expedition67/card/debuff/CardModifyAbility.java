package com.Expedition67.card.debuff;

import com.Expedition67.card.Card;
import com.Expedition67.card.CardAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.core.util.GameRandom;
import com.Expedition67.unit.Unit;

public class CardModifyAbility extends CardAbility {

    private final CardType typeToModify;

    //for Tilly The Bird to add cost to player's card
    public CardModifyAbility(int value, CardType cardType, CardType typeToModify) {
        super(value, cardType);
        this.typeToModify = typeToModify;
    }

    @Override
    public void apply(Unit target) {
        Card c = GameRandom.Instance().getRandomCardFromHand(typeToModify);
        if (c != null) {
            c.addCost(value);
            CombatManager.Instance().addActionString(String.format(" increases the cost of %s by %d!", c.getName(), value));
        } else
            CombatManager.Instance().addActionString(" attempts to increase a card's cost, but finds no valid target.");
    }

    @Override
    public CardAbility copy() {
        return new CardModifyAbility(getValue(), getCardType(), typeToModify);
    }
}
