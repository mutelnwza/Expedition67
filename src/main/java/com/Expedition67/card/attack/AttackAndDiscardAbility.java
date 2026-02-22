package com.Expedition67.card.attack;

import com.Expedition67.card.Card;
import com.Expedition67.card.CardAbility;
import com.Expedition67.core.CombatManager;
import com.Expedition67.core.GameRandom;
import com.Expedition67.unit.Unit;

public class AttackAndDiscardAbility extends DamageAbility {

    public AttackAndDiscardAbility(int value, CardType cardType) {
        super(value, cardType);
    }

    @Override
    public void apply(Unit target, Unit src) {
        // use to player, for boss
        Card c = GameRandom.Instance().getRandomCardFromHand(null);
        if (c != null) {
            CombatManager.Instance().getDeck().useCard(c);
            float initialDamage = calculateAndDealDamage(target, src);
            float bonusDamage = 0;
            for (int i = 0; i < c.getAP(); i++)
                bonusDamage += calculateAndDealDamage(target, src);
            CombatManager.Instance().addActionString(String.format(" discards %s to deal a total of %.2f damage to %s", c.getName(), initialDamage + bonusDamage, target.getName()));
        } else {
            float dealtDamage = calculateAndDealDamage(target, src);
            CombatManager.Instance().addActionString(String.format(" deals %.2f damage to %s", dealtDamage, target.getName()));
        }
    }

    @Override
    public CardAbility copy() {
        return new AttackAndDiscardAbility(getValue(), getCardType());
    }
}
