package com.Expedition67.card.special;

import com.Expedition67.card.Card;
import com.Expedition67.card.CardAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.core.util.GameRandom;
import com.Expedition67.unit.Unit;

/**
 * Represents a special ability that mimics the effect of a random attack card in hand.
 */
public class MimicAbility extends CardAbility {

    /**
     * Constructs a Mimic ability.
     *
     * @param cardType The type of card, typically BUFF or SPECIAL.
     */
    public MimicAbility(CardType cardType) {
        super(cardType);
    }

    /**
     * Applies the mimic effect, triggering a random attack card's ability.
     *
     * @param target The target for the mimicked ability.
     */
    @Override
    public void apply(Unit target) {
        Card c = GameRandom.Instance().getRandomCardFromHand(CardType.ATK);

        if (c != null) {
            c.getAbility().apply(target);
            CombatManager.Instance().addActionString(String.format(" mimics %s!", c.getName()));
        } else {
            CombatManager.Instance().addActionString(" attempts to mimic, but finds no attack card to copy.");
        }
    }

    /**
     * Creates a copy of this MimicAbility.
     *
     * @return A new MimicAbility instance.
     */
    @Override
    public CardAbility copy() {
        return new MimicAbility(getCardType());
    }
}
