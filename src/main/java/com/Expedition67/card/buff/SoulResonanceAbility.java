package com.Expedition67.card.buff;

import com.Expedition67.card.Card;
import com.Expedition67.card.CardAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.unit.Unit;

import java.util.ArrayList;

/**
 * Represents a buff that increases the damage of all attack cards in the player's hand.
 */
public class SoulResonanceAbility extends CardAbility {

    /**
     * Constructs a Soul Resonance ability.
     *
     * @param value    The amount of extra damage to add to attack cards.
     * @param cardType The type of card, typically BUFF.
     */
    public SoulResonanceAbility(int value, CardType cardType) {
        super(value, cardType);
    }

    /**
     * Applies the buff to all attack cards in the player's hand.
     *
     * @param target The unit using the ability.
     */
    @Override
    public void apply(Unit target) {
        ArrayList<Card> cardsInHand = CombatManager.Instance().getDeck().getHand();
        int affectedCards = 0;

        if (cardsInHand != null) {
            for (Card c : cardsInHand) {
                if (c != null && c.getAbility() != null && c.getAbility().getCardType() == CardType.ATK) {
                    c.getAbility().setTemporaryValue(c.getAbility().getValue() + this.value);
                    affectedCards++;
                }
            }
        }

        if (affectedCards > 0) {
            CombatManager.Instance().addActionString(String.format(" resonates with their soul, empowering %d attack card(s) in hand!", affectedCards));
        } else {
            CombatManager.Instance().addActionString(" resonates with their soul, but has no attack cards to empower.");
        }
    }

    /**
     * Creates a copy of this SoulResonanceAbility.
     *
     * @return A new SoulResonanceAbility instance with the same properties.
     */
    @Override
    public CardAbility copy() {
        return new SoulResonanceAbility(getValue(), getCardType());
    }
}
