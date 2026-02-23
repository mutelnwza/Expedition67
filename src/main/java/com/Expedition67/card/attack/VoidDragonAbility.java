package com.Expedition67.card.attack;

import com.Expedition67.card.Card;
import com.Expedition67.card.CardAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.unit.Unit;
import com.Expedition67.unit.player.Deck;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a special attack that deals more damage to low-health targets and cleanses Void cards.
 */
public class VoidDragonAbility extends DamageAbility {

    private final int normalDamage;
    private final int boostedDamage;

    /**
     * Constructs a Void Dragon ability.
     *
     * @param normal   The damage dealt to targets above 50% health.
     * @param boosted  The damage dealt to targets below 50% health.
     * @param cardType The type of card, typically ATK.
     */
    public VoidDragonAbility(int normal, int boosted, CardType cardType) {
        super(normal, cardType);
        this.normalDamage = normal;
        this.boostedDamage = boosted;
    }

    /**
     * Applies the Void Dragon's attack, dealing damage and cleansing Void cards.
     *
     * @param target The unit to be damaged.
     * @param src    The unit performing the attack.
     */
    @Override
    public void apply(Unit target, Unit src) {
        float targetHp = target.getUnitStats().getHp();
        float dealtDamage;
        String action;

        if (targetHp < target.getUnitStats().getMaxHp() * 0.5f) {
            dealtDamage = calculateAndDealDamage(target, src, boostedDamage);
            action = String.format(" unleashes a powerful void-infused strike, dealing %.2f damage to %s", dealtDamage, target.getName());
        } else {
            dealtDamage = calculateAndDealDamage(target, src, normalDamage);
            action = String.format(" strikes with void energy, dealing %.2f damage to %s", dealtDamage, target.getName());
        }

        Deck deck = CombatManager.Instance().getDeck();
        ArrayList<Card> allCards = deck.getAllCards();
        List<Card> cardsToRemove = new ArrayList<>();

        for (Card c : allCards) {
            if (c.getAbility().getCardType() == CardType.VOID) {
                cardsToRemove.add(c);
            }
        }

        if (!cardsToRemove.isEmpty()) {
            for (Card c : cardsToRemove) {
                deck.removeFromDeck(c);
            }
            action += String.format("\nand cleanses %d Void card(s) from the deck!", cardsToRemove.size());
        }
        CombatManager.Instance().addActionString(action);
    }

    /**
     * Creates a copy of this VoidDragonAbility.
     *
     * @return A new VoidDragonAbility instance with the same properties.
     */
    @Override
    public CardAbility copy() {
        return new VoidDragonAbility(normalDamage, boostedDamage, getCardType());
    }
}
