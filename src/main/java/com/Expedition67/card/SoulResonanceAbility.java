package com.Expedition67.card;
import java.util.*;
import com.Expedition67.unit.Deck;
import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;

public class SoulResonanceAbility extends CardAbility {
    private Deck deck;
    private Card card;
    public SoulResonanceAbility(CardType cardType) {
        super(cardType);
    }

    @Override
    public void apply(Unit target) {
        // TODO: รอทำระบบ บวกดาเมจให้การ์ด Attack 
        deck = CombatManager.Instance().getDeck();
        card = deck.getSelectedCard();
        if (card.getAbility().getCardType() == CardType.ATK) {
            super.value += 6;
        }
        CombatManager.Instance().addActionString(" resonance power to " + target.getName() + " = " + value);
    }
}