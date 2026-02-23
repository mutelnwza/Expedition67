package com.Expedition67.card.attack;

import com.Expedition67.card.Card;
import com.Expedition67.card.CardAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.core.util.GameRandom;
import com.Expedition67.unit.Unit;

/**
 * Represents an attack that discards a card from hand to deal bonus damage.
 */
public class AttackAndDiscardAbility extends DamageAbility {

    /**
     * Constructs an Attack and Discard ability.
     *
     * @param value    The base damage of the attack.
     * @param cardType The type of card, typically ATK.
     */
    public AttackAndDiscardAbility(int value, CardType cardType) {
        super(value, cardType);
    }

    /**
     * Applies the ability's effect: discards a card to deal bonus damage.
     *
     * @param target The unit to be damaged.
     * @param src    The unit performing the attack.
     */
    @Override
    public void apply(Unit target, Unit src) {
        Card c = GameRandom.Instance().getRandomCardFromHand(null);

        if (c != null) {
            CombatManager.Instance().getDeck().useCard(c);

            float initialDamage = calculateAndDealDamage(target, src);
            float bonusDamage = 0;

            for (int i = 0; i < c.getAP(); i++) {
                bonusDamage += calculateAndDealDamage(target, src);
            }

            CombatManager.Instance().addActionString(String.format(" discards %s to deal a total of %.2f damage to %s", c.getName(), initialDamage + bonusDamage, target.getName()));
        } else {
            float dealtDamage = calculateAndDealDamage(target, src);
            CombatManager.Instance().addActionString(String.format(" deals %.2f damage to %s", dealtDamage, target.getName()));
        }
    }

    /**
     * Creates a copy of this AttackAndDiscardAbility.
     *
     * @return A new AttackAndDiscardAbility instance with the same properties.
     */
    @Override
    public CardAbility copy() {
        return new AttackAndDiscardAbility(getValue(), getCardType());
    }
}
