package com.Expedition67.card.attack;

import com.Expedition67.card.CardAbility;
import com.Expedition67.card.CardName;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.storage.Warehouse;
import com.Expedition67.unit.Unit;
import com.Expedition67.unit.player.Deck;

public class VoidAttackAbility extends DamageAbility {

    public VoidAttackAbility(int value, CardType cardType) {
        super(value, cardType);
    }

    @Override
    public void apply(Unit target, Unit src) {
        System.out.println("ATTACKKKKKK");
        float dealtDamage = calculateAndDealDamage(target, src);
        System.out.println("ATTACKED");
        Deck d = CombatManager.Instance().getDeck();
        d.addCard(Warehouse.Instance().spawnCard(CardName.VOID));
        System.out.println("ADDED");
        CombatManager.Instance().addActionString(String.format(" deals %.2f damage to %s and curses them with a Void card!", dealtDamage, target.getName()));
    }

    @Override
    public CardAbility copy() {
        return new VoidAttackAbility(getValue(), getCardType());
    }
}
