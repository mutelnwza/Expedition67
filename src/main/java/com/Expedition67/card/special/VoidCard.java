package com.Expedition67.card.special;

import com.Expedition67.card.Card;
import com.Expedition67.card.CardAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.unit.Unit;

/**
 * Represents a "Void" card, a cursed card that does nothing and is removed upon use.
 */
public class VoidCard extends CardAbility {

    /**
     * Constructs a Void Card ability.
     *
     * @param cardType The type of card, which is VOID.
     */
    public VoidCard(CardType cardType) {
        super(cardType);
    }

    /**
     * Applies the effect of the Void card, which removes it from the deck.
     *
     * @param target The unit playing the card.
     */
    @Override
    public void apply(Unit target) {
        Card thisCard = CombatManager.Instance().getDeck().getSelectedCard();
        CombatManager.Instance().getDeck().removeFromDeck(thisCard);
        CombatManager.Instance().addActionString(" uses a Void card! It does nothing, but is now gone from the deck.");
    }

    /**
     * Creates a copy of this VoidCard ability.
     *
     * @return A new VoidCard instance.
     */
    @Override
    public CardAbility copy() {
        return new VoidCard(getCardType());
    }
}
