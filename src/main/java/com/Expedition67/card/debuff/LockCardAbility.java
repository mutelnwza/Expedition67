package com.Expedition67.card.debuff;

import com.Expedition67.card.Card;
import com.Expedition67.card.CardAbility;
import com.Expedition67.card.RemovableAbility;
import com.Expedition67.core.CombatManager;
import com.Expedition67.core.GameRandom;
import com.Expedition67.unit.Deck;
import com.Expedition67.unit.Unit;

import java.util.ArrayList;

public class LockCardAbility extends RemovableAbility {

    private ArrayList<Card> lockedCard;

    public LockCardAbility(int turn, int value, CardType cardType) {
        super(turn, value, cardType);
    }

    @Override
    public void apply(Unit target) {
        lockedCard = new ArrayList<>();
        Deck deck = CombatManager.Instance().getDeck();
        int count = 0;
        int attempts = 0;
        int maxAttempts = deck.getHandSize() * 2;

        while (count < value && attempts < maxAttempts) {
            Card c = GameRandom.Instance().getRandomCardFromHand(null);
            if (c != null && !c.isLocked() && !lockedCard.contains(c)) {
                c.setLocked(true);
                lockedCard.add(c);
                count++;
            }
            attempts++;
        }
        if (!lockedCard.isEmpty())
            CombatManager.Instance().addActionString(String.format(" locks %d card(s) in their hand!", lockedCard.size()));
        else
            CombatManager.Instance().addActionString(" attempts to lock cards, but fails.");
    }

    @Override
    public void remove(Unit target) {
        if (lockedCard != null)
            for (Card c : lockedCard)
                c.setLocked(false);
    }

    @Override
    public CardAbility copy() {
        return new LockCardAbility(getTurn(), getValue(), getCardType());
    }
}
