package com.Expedition67.card.attack;

import com.Expedition67.card.Card;
import com.Expedition67.card.CardAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.unit.Deck;
import com.Expedition67.unit.Unit;

import java.util.ArrayList;
import java.util.List;

public class VoidDragonAbility extends DamageAbility {

    private final int normalDamage;
    private final int boostedDamage;

    public VoidDragonAbility(int normal, int boosted, CardType cardType) {
        super(normal, cardType);
        normalDamage = normal;
        boostedDamage = boosted;
    }

    @Override
    public void apply(Unit target, Unit src) {
        float targetHp = target.getUnitStats().getHp();
        float dealtDamage;
        String action;

        if (targetHp < target.getUnitStats().getMaxHp() * .5f) {
            dealtDamage = calculateAndDealDamage(target, src, boostedDamage);
            action = String.format(" unleashes a powerful void-infused strike, dealing %.2f damage to %s", dealtDamage, target.getName());
        } else {
            dealtDamage = calculateAndDealDamage(target, src, normalDamage);
            action = String.format(" strikes with void energy, dealing %.2f damage to %s", dealtDamage, target.getName());
        }

        Deck deck = CombatManager.Instance().getDeck();
        ArrayList<Card> allCards = deck.getAllCards();
        List<Card> cardsToRemove = new ArrayList<>();
        for (Card c : allCards)
            if (c.getAbility().getCardType() == CardType.VOID)
                cardsToRemove.add(c);

        if (!cardsToRemove.isEmpty()) {
            for (Card c : cardsToRemove)
                deck.removeFromDeck(c);
            action += String.format("\nand cleanses %d Void card(s) from the deck!", cardsToRemove.size());
        }
        CombatManager.Instance().addActionString(action);
    }

    @Override
    public CardAbility copy() {
        return new VoidDragonAbility(normalDamage, boostedDamage, getCardType());
    }
}
