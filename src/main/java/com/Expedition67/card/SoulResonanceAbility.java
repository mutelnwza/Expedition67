package com.Expedition67.card;
import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;
import java.util.*;

public class SoulResonanceAbility extends CardAbility {
    public SoulResonanceAbility(CardType cardType) {
        super(cardType);
    }

    @Override
    public void apply(Unit target) {
        ArrayList<Card> cardsInHand = CombatManager.Instance().getDeck().getHand();
        for(Card c : cardsInHand )
        if (c.getAbility().getCardType()==CardType.ATK) {
            c.getAbility().setValue(c.getAbility().getValue()+value);
        }
        CombatManager.Instance().addActionString(" resonance power to " + target.getName() + " = " + value);
    }
}