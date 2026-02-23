package com.Expedition67.card.debuff;

import com.Expedition67.card.Card;
import com.Expedition67.card.CardAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.core.util.GameRandom;
import com.Expedition67.unit.Unit;

/**
 * Represents a debuff that modifies a random card in the player's hand.
 */
public class CardModifyAbility extends CardAbility {

    private final CardType typeToModify;

    /**
     * Constructs a Card Modify ability.
     *
     * @param value        The amount to increase the card's AP cost by.
     * @param cardType     The type of this ability's card, typically DEBUFF.
     * @param typeToModify The card type to target in the player's hand.
     */
    public CardModifyAbility(int value, CardType cardType, CardType typeToModify) {
        super(value, cardType);
        this.typeToModify = typeToModify;
    }

    /**
     * Applies the cost increase effect to a random card in the player's hand.
     *
     * @param target The unit being affected.
     */
    @Override
    public void apply(Unit target) {
        Card c = GameRandom.Instance().getRandomCardFromHand(typeToModify);

        if (c != null) {
            c.addCost(this.value);
            CombatManager.Instance().addActionString(String.format(" increases the cost of %s by %d!", c.getName(), this.value));
        } else {
            CombatManager.Instance().addActionString(" attempts to increase a card's cost, but finds no valid target.");
        }
    }

    /**
     * Creates a copy of this CardModifyAbility.
     *
     * @return A new CardModifyAbility instance with the same properties.
     */
    @Override
    public CardAbility copy() {
        return new CardModifyAbility(getValue(), getCardType(), typeToModify);
    }
}
