package com.Expedition67.card.debuff;

import com.Expedition67.card.Card;
import com.Expedition67.card.CardAbility;
import com.Expedition67.card.RemovableAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.core.util.GameRandom;
import com.Expedition67.unit.Unit;
import com.Expedition67.unit.player.Deck;

import java.util.ArrayList;

/**
 * Represents a debuff that locks a random number of cards in the player's hand.
 */
public class LockCardAbility extends RemovableAbility {

    private ArrayList<Card> lockedCards;

    /**
     * Constructs a Lock Card ability.
     *
     * @param turn     The number of turns the cards will be locked.
     * @param value    The number of cards to lock.
     * @param cardType The type of card, typically DEBUFF.
     */
    public LockCardAbility(int turn, int value, CardType cardType) {
        super(turn, value, cardType);
    }

    /**
     * Applies the lock debuff to the target unit.
     *
     * @param target The unit being affected.
     */
    @Override
    public void apply(Unit target) {
        this.lockedCards = new ArrayList<>();
        Deck deck = CombatManager.Instance().getDeck();
        int count = 0;
        int attempts = 0;
        int maxAttempts = deck.getHandSize() * 2;

        while (count < this.value && attempts < maxAttempts) {
            Card c = GameRandom.Instance().getRandomCardFromHand(null);
            if (c != null && !c.isLocked() && !lockedCards.contains(c)) {
                c.setLocked(true);
                lockedCards.add(c);
                count++;
            }
            attempts++;
        }

        if (!lockedCards.isEmpty()) {
            CombatManager.Instance().addActionString(String.format(" locks %d card(s) in their hand!", lockedCards.size()));
        } else {
            CombatManager.Instance().addActionString(" attempts to lock cards, but fails.");
        }
    }

    /**
     * Removes the lock debuff from the target unit.
     *
     * @param target The unit from which to remove the debuff.
     */
    @Override
    public void remove(Unit target) {
        if (lockedCards != null) {
            for (Card c : lockedCards) {
                c.setLocked(false);
            }
        }
    }

    /**
     * Creates a copy of this LockCardAbility.
     *
     * @return A new LockCardAbility instance with the same properties.
     */
    @Override
    public CardAbility copy() {
        return new LockCardAbility(getTurn(), getValue(), getCardType());
    }
}
