package com.Expedition67.card.Debuff;

import com.Expedition67.card.Card;
import com.Expedition67.card.CardAbility;
import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;

public class CardModifyAbility extends CardAbility{
    private CardType typeToModify;
    //for tilly bird to add cost to player's card
    public CardModifyAbility(int value, CardType cardType, CardType typeToModify) {
        super(value, cardType);
        this.typeToModify = typeToModify;
    }

    @Override
    public void apply(Unit target) {
        applyPlayerDebuff();
    }

    private void applyPlayerDebuff(){
        Card c = CombatManager.Instance().getDeck().getRandomCardFromHand(typeToModify);
        c.addCost(value);
    }
}
