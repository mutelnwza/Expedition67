package com.Expedition67.card.attack;

import com.Expedition67.card.CardAbility;
import com.Expedition67.card.CardName;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.storage.Warehouse;
import com.Expedition67.unit.Unit;
import com.Expedition67.unit.player.Deck;

/**
 * Represents an attack that also adds a "Void" card to the player's deck.
 */
public class VoidAttackAbility extends DamageAbility {

    /**
     * Constructs a Void Attack ability.
     *
     * @param value    The base damage of the attack.
     * @param cardType The type of card, typically ATK.
     */
    public VoidAttackAbility(int value, CardType cardType) {
        super(value, cardType);
    }

    /**
     * Applies the ability's effect: deals damage and adds a Void card to the deck.
     *
     * @param target The unit to be damaged.
     * @param src    The unit performing the attack.
     */
    @Override
    public void apply(Unit target, Unit src) {
        float dealtDamage = calculateAndDealDamage(target, src);

        Deck d = CombatManager.Instance().getDeck();
        d.addCard(Warehouse.Instance().spawnCard(CardName.VOID));

        CombatManager.Instance().addActionString(String.format(" deals %.2f damage to %s and curses them with a Void card!", dealtDamage, target.getName()));
    }

    /**
     * Creates a copy of this VoidAttackAbility.
     *
     * @return A new VoidAttackAbility instance with the same properties.
     */
    @Override
    public CardAbility copy() {
        return new VoidAttackAbility(getValue(), getCardType());
    }
}
